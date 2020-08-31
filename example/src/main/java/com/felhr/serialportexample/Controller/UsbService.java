package com.felhr.serialportexample.Controller;

import android.app.PendingIntent;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.hardware.usb.UsbDevice;
import android.hardware.usb.UsbDeviceConnection;
import android.hardware.usb.UsbManager;
import android.os.Binder;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;

import com.felhr.usbserial.CDCSerialDevice;
import com.felhr.usbserial.UsbSerialDevice;
import com.felhr.usbserial.UsbSerialInterface;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.zip.CRC32;
import java.util.zip.Checksum;

public class UsbService extends Service {

    public static final String ACTION_USB_READY = "com.felhr.connectivityservices.USB_READY";
    public static final String ACTION_USB_ATTACHED = "android.hardware.usb.action.USB_DEVICE_ATTACHED";
    public static final String ACTION_USB_DETACHED = "android.hardware.usb.action.USB_DEVICE_DETACHED";
    public static final String ACTION_USB_NOT_SUPPORTED = "com.felhr.usbservice.USB_NOT_SUPPORTED";
    public static final String ACTION_NO_USB = "com.felhr.usbservice.NO_USB";
    public static final String ACTION_USB_PERMISSION_GRANTED = "com.felhr.usbservice.USB_PERMISSION_GRANTED";
    public static final String ACTION_USB_PERMISSION_NOT_GRANTED = "com.felhr.usbservice.USB_PERMISSION_NOT_GRANTED";
    public static final String ACTION_USB_DISCONNECTED = "com.felhr.usbservice.USB_DISCONNECTED";
    public static final String ACTION_CDC_DRIVER_NOT_WORKING = "com.felhr.connectivityservices.ACTION_CDC_DRIVER_NOT_WORKING";
    public static final String ACTION_USB_DEVICE_NOT_WORKING = "com.felhr.connectivityservices.ACTION_USB_DEVICE_NOT_WORKING";
    public static final int MESSAGE_FROM_SERIAL_PORT = 0;
    private static final String ACTION_USB_PERMISSION = "com.android.example.USB_PERMISSION";
    private static final int BAUD_RATE = 921600; // BaudRate. Change this value if you need //921600
    public static boolean SERVICE_CONNECTED = false;
    public static String data2 = null;

    private IBinder binder = new UsbBinder();

    private static Context context;
    private Handler mHandler;
    private Handler neuHandler;
    private UsbManager usbManager;
    private UsbDevice device;
    private UsbDeviceConnection connection;

    public static int UpdateJobFlag=0;
    public static boolean token = false;
    public static int LengthProtocol=0;
    public static int HeaderFound=0;
    public static int CounterData=0;
    public static int LengthFound=0;
    public static byte[] ByteArray = new byte[25];
    private static String sdata = "";
    private static String sdata1 = "";
    private static byte Input;
    private static String stringforjob = "";
    private static String stringforjob1 = "";
    private static String stringforjob2 = "";
    private static String stringforjob3 = "";
    private static String stringforjob4 = "";
    private static byte[]jobcheck;
    private static byte[]jobcheck1;
    private static byte[]jobcheck2;
    public static byte[] check4;
    private static byte[] ccheck;
    private static byte[] ccheck1;
    public static String data1;
    private static MyHandler myHandler;
    private Context gg;
    private static String chardata;
    private static byte ch;
    private static String sdataFrame = "";
    public static String currentDateandTime;

    public UsbService(Context cont) {
        this.gg = cont ;
    }

    public UsbService() {}

    private static Charset iso88591charset = Charset.forName("ISO-8859-1");

    private UsbSerialDevice serialPort;

    private boolean serialPortConnected;

    /*
     *  Data received from serial port will be received here. Just populate onReceivedData with your code
     *  In this particular example. byte stream is converted to String and send to UI thread to
     *  be treated there.
     */
    private UsbSerialInterface.UsbReadCallback mCallback = new UsbSerialInterface.UsbReadCallback() {
        @Override
        public void onReceivedData(byte[] arg0) {
            try {
                if(token) { //if token is true
                    data2 = new String(arg0, "ISO-8859-1");
                    if (mHandler != null)
                        mHandler.obtainMessage(MESSAGE_FROM_SERIAL_PORT, data2).sendToTarget();
                }
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
    };

    /*
     * Different notifications from OS will be received here (USB attached, detached, permission responses...)
     * About BroadcastReceiver: http://developer.android.com/reference/android/content/BroadcastReceiver.html
     */
    private final BroadcastReceiver usbReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context arg0, Intent arg1) {
            if (arg1.getAction().equals(ACTION_USB_PERMISSION)) {
                boolean granted = arg1.getExtras().getBoolean(UsbManager.EXTRA_PERMISSION_GRANTED);
                if (granted) // User accepted our USB connection. Try to open the device as a serial port
                {
                    Intent intent = new Intent(ACTION_USB_PERMISSION_GRANTED);
                    arg0.sendBroadcast(intent);
                    connection = usbManager.openDevice(device);
                    serialPortConnected = true;
                    new ConnectionThread().run();
                } else // User not accepted our USB connection. Send an Intent to the Main Activity
                {
                    Intent intent = new Intent(ACTION_USB_PERMISSION_NOT_GRANTED);
                    arg0.sendBroadcast(intent);
                }
            } else if (arg1.getAction().equals(ACTION_USB_ATTACHED)) {
                if (!serialPortConnected)
                    findSerialPortDevice(); // A USB device has been attached. Try to open it as a Serial port
            } else if (arg1.getAction().equals(ACTION_USB_DETACHED)) {
                // Usb device was disconnected. send an intent to the Main Activity
                Intent intent = new Intent(ACTION_USB_DISCONNECTED);
                arg0.sendBroadcast(intent);
                serialPortConnected = false;
                serialPort.close();
            }
        }
    };

/*    public void changeParam(String canid, int data, int position){ //canid in hex

        StringBuilder sbchangeParam = new StringBuilder();

        String ci1 = canid.substring(0,1); //pos 1
        String ci2 = canid.substring(2,3); //pos 2

        for (int i = 0; i <19; i++) {
            if (i==3)  sdata=sbchangeParam.append(ci1).toString(); //msb CanId
            else if (i==4)  sdata=sbchangeParam.append(ci2).toString(); //lsb CanId
            else if ((position!=i-6))  sdata = sbchangeParam.append(Integer.toHexString((int)ByteArray[i])).toString();
            else if ((position==i-6)) sdata = sbchangeParam.append(Integer.toHexString(data)).toString();
        }

        Intent var = new Intent("Main.Activity2").putExtra("change_param",sdata); //send hex content to Main Activity
        context.sendBroadcast(var);
    }*/

    public static String changeJob(){
        StringBuilder sbjob = new StringBuilder();
        StringBuilder sbjob1 = new StringBuilder();
        byte[] frameArr = new byte[19];

        frameArr[0]=36;//Header 0x24
        frameArr[1]=19;//19 bytes
        frameArr[2]=2;//frameid
        frameArr[3]=6;//msb canid
        frameArr[4]=(byte)224;//lsb canid
        frameArr[5]=8;//can data length
        frameArr[6]=22;
        frameArr[7]=1;
        frameArr[8]=1;
        frameArr[9]=18;
        frameArr[10]=0;
        frameArr[11]=4;
        frameArr[12]=(byte)207;
        frameArr[13]=2;
        frameArr[14]=35;

        for (int i = 0; i < 15; i++) {//checksum ascii string for parameterId and valueId
            stringforjob = sbjob.append((char)(frameArr[i]&0xFF)).toString();
        }
        jobcheck = stringforjob.getBytes(iso88591charset);
        Checksum jobchecksum = new CRC32();
        jobchecksum.update(jobcheck, 0, jobcheck.length);// update the current checksum with the specified array of bytes
        long jobchecksumValue = jobchecksum.getValue();// get the current checksum value

        frameArr[14]=(byte)((jobchecksumValue >>> 24)&0xFF);
        frameArr[15]=(byte)((jobchecksumValue >>> 16)&0xFF);
        frameArr[16]=(byte)((jobchecksumValue >>> 8)&0xFF);
        frameArr[17]=(byte)(jobchecksumValue&0xFF);
        frameArr[18]=35;//footer 0x23

        for (int i = 0; i < 19; i++) {//parameterId and valueId ascii string
            stringforjob1 = sbjob1.append((char)(frameArr[i]&0xFF)).toString();
        }
        return stringforjob1;
    }

    public static int calcCRC16(byte[] buf, int len){
        int crc = 0xFFFF;

        for (int pos = 0; pos < len; pos++) {
            crc ^= (int)buf[pos] & 0xFF;   // XOR byte into least sig. byte of crc

            for (int i = 8; i != 0; i--) {    // Loop over each bit
                if ((crc & 0x0001) != 0) {      // If the LSB is set
                    crc >>= 1;                    // Shift right and XOR 0xA001
                    crc ^= 0xA001;
                }
                else                            // Else LSB is not set
                    crc >>= 1;                    // Just shift right
            }
        }
        // Note, this number has low and high bytes swapped, so use it accordingly (or swap bytes)
        return crc;
    }

    public static String changeJob1(int jobnum){
        StringBuilder sbjob = new StringBuilder();
        StringBuilder sbjob1 = new StringBuilder();
        StringBuilder sbjob2 = new StringBuilder();
        byte[] frameArr = new byte[19];

        frameArr[0]=36;//Header 0x24
        frameArr[1]=19;//19 bytes
        frameArr[2]=2;//frameid
        frameArr[3]=6;//msb canid
        frameArr[4]=(byte)224;//lsb canid
        frameArr[5]=8;//can data length
        frameArr[6]=(byte)(jobnum & 0xFF);//lsb jobnum
        frameArr[7]=(byte)(jobnum>>>8 & 0xFF);//msb jobnum
        frameArr[8]=1;
        frameArr[9]=0;

        for(int i=6;i<10;i++) {
            stringforjob2 = sbjob.append((char)(frameArr[i]&0xFF)).toString();
        }
        jobcheck1 = stringforjob2.getBytes(iso88591charset);
        int res = calcCRC16(jobcheck1,jobcheck1.length);//call the method

        frameArr[10]=(byte)((res>>>8)&0xFF);
        frameArr[11]=(byte)(res&0xFF);
        frameArr[12]=3;
        frameArr[13]=4;
        frameArr[14]=35;//footer 0x23

        for (int i = 0; i < 15; i++){
            stringforjob3 = sbjob1.append((char)(frameArr[i]&0xFF)).toString();
        }

        jobcheck2 = stringforjob3.getBytes(iso88591charset);
        Checksum jobchecksum = new CRC32();
        jobchecksum.update(jobcheck2, 0, jobcheck2.length);// update the current checksum with the specified array of bytes
        long jobchecksumValue = jobchecksum.getValue();// get the current checksum value
        frameArr[14]=(byte)((jobchecksumValue >>> 24)&0xFF);
        frameArr[15]=(byte)((jobchecksumValue >>> 16)&0xFF);
        frameArr[16]=(byte)((jobchecksumValue >>> 8)&0xFF);
        frameArr[17]=(byte)(jobchecksumValue&0xFF);
        frameArr[18]=35;     				//Footer 0x23

        for (int i = 0; i < 19; i++){
            stringforjob4 = sbjob2.append((char)(frameArr[i]&0xFF)).toString();
        }
        return stringforjob4;
    }

    public static String sdataBuilder(byte frameIDmode,byte frameMODE, byte frameAdd){

        String schecksum = "";
        StringBuilder sbcanSend = new StringBuilder();
        StringBuilder sbcanSend1 = new StringBuilder();
        byte[] frameArray = new byte[15];

        frameArray[0]=36;    		//Header 0x24
        frameArray[1]=15;    		//LengthProtocol
        frameArray[2]=2;     		//Frame ID
        frameArray[3]=1;     		//msb CAN ID
        frameArray[4]=(byte)144;    //lsb CAN ID
        frameArray[5]=4;     		//CAN Length
        frameArray[6]=frameIDmode;  //frameextra
        frameArray[7]=0;     		//
        frameArray[8]=frameMODE;    //frameval
        frameArray[9]=frameAdd;     //
        frameArray[10]=35;     		//Footer 0x23

        for (int i = 0; i < 11; i++) {//checksum ascii string for parameterId and valueId
            sdata = sbcanSend.append((char)(frameArray[i]&0xFF)).toString();
        }

        ccheck = sdata.getBytes(iso88591charset);
        Checksum cchecksum = new CRC32();
        cchecksum.update(ccheck, 0, ccheck.length);// update the current checksum with the specified array of bytes
        long cchecksumValue = cchecksum.getValue();// get the current checksum value

        schecksum=Long.toHexString(cchecksumValue);
        for (int i = Long.toHexString(cchecksumValue).length() ; i < 8; i++) {
            schecksum="0"+schecksum;
        }
        frameArray[10]=(byte)((cchecksumValue >>> 24)&0xFF);
        frameArray[11]=(byte)((cchecksumValue >>> 16)&0xFF);
        frameArray[12]=(byte)((cchecksumValue >>> 8)&0xFF);
        frameArray[13]=(byte)(cchecksumValue&0xFF);
        frameArray[14]=35;     						//Footer 0x23

        for (int i = 0; i < 15; i++) {//parameterId and valueId ascii string
            sdata1 = sbcanSend1.append((char)(frameArray[i]&0xFF)).toString();
        }
        return sdata1;
    }

    public static void canSend(byte valtoken,byte paramtoken,byte frameval,byte frameextra,byte firstval,byte secondval,byte thirdval){

        if (paramtoken == 1) {
            Intent var = new Intent("Main.Activity2").putExtra("change_param", sdataBuilder(frameextra, frameval, (byte) 0)); //send parameter ascii string
            context.sendBroadcast(var);
            Log.i("ParamData", sdata1);
        }

        if (valtoken == 1) {
            Intent var1 = new Intent("Main.Activity3").putExtra("change_param1", sdataBuilder(thirdval,firstval,secondval)); //send value ascii string
            context.sendBroadcast(var1);
            Log.i("valueData",sdata1);
        }
    }

    public static void buffParsing(String RXBuff) {
        byte[] str = RXBuff.getBytes(iso88591charset);
        for (int i = 0; i < RXBuff.length(); i++) {
            byte c =str[i];//get char from string
            dataCombiner(c);
        }
    }

    public static void dataCombiner(byte dataRX) {
        Input = dataRX;
        if (HeaderFound == 0) {
            int ByteCompare = Byte.compare(dataRX, (byte)36);
            if (ByteCompare == 0) {
                HeaderFound = 1;
                ByteArray[CounterData] = Input;
                CounterData++;
            }
        } else if (LengthFound == 0) {
            LengthFound = 1;
            ch = Input;
            LengthProtocol = (int) ch;
            ByteArray[CounterData] = Input;
            CounterData++;
        } else if (CounterData < LengthProtocol-1 && CounterData< 19) {
                ByteArray[CounterData] = Input;
                CounterData++;
        } else if (CounterData == LengthProtocol-1 && CounterData< 19) { //at this point, CounterData = 18
            ByteArray[CounterData] = Input;
            //System.out.println( "the last data = " + ByteArray[CounterData]);
            int ByteCompare1 = Byte.compare(ByteArray[CounterData], (byte) 35); //check footer
            if (ByteCompare1 == 0) { //received footer

                if(LengthProtocol>11 && LengthProtocol<20) { //length between 12 and 19
                    String scheckCRC4 = "";
                    String[] value = new String[19];

                    for (int i = 0; i < LengthProtocol-5; i++) {
                        //checkCRC4.append((char) ((ByteArray[i]) & 0xFF));
                        scheckCRC4 = scheckCRC4 + (char)((ByteArray[i]) & 0xFF);
                    }
                    scheckCRC4 = scheckCRC4 + (char) ((ByteArray[LengthProtocol-1])&0xFF);

                    check4 = scheckCRC4.getBytes(iso88591charset);
                    Checksum checksum4 = new CRC32();
                    checksum4.update(check4, 0, check4.length);// update the current checksum with the specified array of bytes
                    long checksumValue4 = checksum4.getValue();// get the current checksum value

                    long res4 = ((ByteArray[LengthProtocol - 5] & 0xFFL) << 24) |//get the long value of crc from frame data
                            ((ByteArray[LengthProtocol - 4] & 0xFFL) << 16) |
                            ((ByteArray[LengthProtocol - 3] & 0xFFL) << 8) |
                            ((ByteArray[LengthProtocol - 2] & 0xFFL) << 0);

                    if (checksumValue4 == res4) {
                        StringBuilder sbdataFrame = new StringBuilder(); //data in hex
                        StringBuilder sbchardata = new StringBuilder(); //data in ascii char

                        for (int i = 0; i < LengthProtocol; i++) {
                            value[i] = String.format("%02x", (int) ((ByteArray[i]) & 0xFF));
                            sdataFrame = sbdataFrame.append(value[i]).toString();
                            chardata = sbchardata.append((char) ((ByteArray[i]) & 0xFF)).toString();
                        }

                        if(sdataFrame!=null && !sdataFrame.equals("")) {
                            if (UpdateJobFlag!=1) {
                                DatenObjekte.buffParsing(chardata);
                                DatenObjekte.callme(sdataFrame);
                            }
                        }
                        Intent iii = new Intent("Main.Activity").putExtra("msg_service", sdataFrame); //send hex content to Main Activity
                        context.sendBroadcast(iii);

                        Intent iiii = new Intent("Main.Activity1").putExtra("msg_service1", chardata); //send ascii content to Main Activity
                        context.sendBroadcast(iiii);
                    }
                }
            }
            resetValue();
        } else if (CounterData>18) resetValue();
    }

    public static void resetValue(){
        HeaderFound = 0;
        CounterData = 0; //reset regardless of checksum
        LengthFound = 0;
        LengthProtocol = 0;
    }

    public void startService(Class<?> service, ServiceConnection serviceConnection, Bundle extras) {//mine
        if (!UsbService.SERVICE_CONNECTED) {
            Intent startService = new Intent(gg , service);
            if (extras != null && !extras.isEmpty()) {
                Set<String> keys = extras.keySet();
                for (String key : keys) {
                    String extra = extras.getString(key);
                    startService.putExtra(key, extra);
                }
            }
            gg.startService(startService);
        }
        Intent bindingIntent = new Intent(gg, service);
        gg.bindService(bindingIntent, serviceConnection, Context.BIND_AUTO_CREATE);
    }

    /*
     * onCreate will be executed when service is started. It configures an IntentFilter to listen for
     * incoming Intents (USB ATTACHED, USB DETACHED...) and it tries to open a serial port.
     */
    @Override
    public void onCreate() {
        this.context = this;
        serialPortConnected = false;

        UsbService.SERVICE_CONNECTED = true;
        setFilter();
        usbManager = (UsbManager) getSystemService(Context.USB_SERVICE);
        findSerialPortDevice();

        Log.i("Success: ", "Start service is called");
        myHandler = new UsbService.MyHandler();
        context = getApplicationContext();//my code

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd HH:mm");
        currentDateandTime = sdf.format(new Date()); //safe current date and time to string
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) { //my code
        Toast.makeText(this, "service starting", Toast.LENGTH_SHORT).show();

        return Service.START_STICKY;
    }

    /* MUST READ about services
     * http://developer.android.com/guide/components/services.html
     * http://developer.android.com/guide/components/bound-services.html
     */
    @Override
    public IBinder onBind(Intent intent) {
        // We don't provide binding, so return null
        return binder;// return Null
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //unbindService(usbConnection);
        UsbService.SERVICE_CONNECTED = false;
        Toast.makeText(this, "service done", Toast.LENGTH_SHORT).show();
    }

    /*
     * This function will be called from MainActivity to write data through Serial Port
     */
    public void write(byte[] data) {
        if (serialPort != null)
            serialPort.write(data);
    }

    public void setHandler(Handler mHandler) {
        this.mHandler = mHandler;
    }

    public void setHandler1(Handler aHandler) {
        this.neuHandler = aHandler;
    }

    private void findSerialPortDevice() {
        // This snippet will try to open the first encountered usb device connected, excluding usb root hubs
        HashMap<String, UsbDevice> usbDevices = usbManager.getDeviceList();
        if (!usbDevices.isEmpty()) {
            boolean keep = true;
            for (Map.Entry<String, UsbDevice> entry : usbDevices.entrySet()) {
                device = entry.getValue();
                int deviceVID = device.getVendorId();
                int devicePID = device.getProductId();

                if (deviceVID != 0x1d6b && (devicePID != 0x0001 || devicePID != 0x0002 || devicePID != 0x0003)) {
                    // There is a device connected to our Android device. Try to open it as a Serial Port.
                    requestUserPermission();
                    keep = false;
                } else {
                    connection = null;
                    device = null;
                }

                if (!keep)
                    break;
            }
            if (!keep) {
                // There is no USB devices connected (but usb host were listed). Send an intent to MainActivity.
                Intent intent = new Intent(ACTION_NO_USB);
                sendBroadcast(intent);
            }
        } else {
            // There is no USB devices connected. Send an intent to MainActivity
            Intent intent = new Intent(ACTION_NO_USB);
            sendBroadcast(intent);
        }
    }

    private void setFilter() {
        IntentFilter filter = new IntentFilter();
        filter.addAction(ACTION_USB_PERMISSION);
        filter.addAction(ACTION_USB_DETACHED);
        filter.addAction(ACTION_USB_ATTACHED);
        registerReceiver(usbReceiver, filter);
    }

    /*
     * Request user permission. The response will be received in the BroadcastReceiver
     */
    private void requestUserPermission() {
        PendingIntent mPendingIntent = PendingIntent.getBroadcast(this, 0, new Intent(ACTION_USB_PERMISSION), 0);
        usbManager.requestPermission(device, mPendingIntent);
    }

    public class UsbBinder extends Binder {
        public UsbService getService() {
            return UsbService.this;
        }
    }

    /*
     * A simple thread to open a serial port.
     * Although it should be a fast operation. moving usb operations away from UI thread is a good thing.
     */
    private class ConnectionThread extends Thread {
        @Override
        public void run() {
            serialPort = UsbSerialDevice.createUsbSerialDevice(device, connection);

            if (serialPort != null) {
                if (serialPort.open()) {
                    serialPort.setBaudRate(BAUD_RATE);
                    serialPort.setDataBits(UsbSerialInterface.DATA_BITS_8);
                    serialPort.setStopBits(UsbSerialInterface.STOP_BITS_1);
                    serialPort.setParity(UsbSerialInterface.PARITY_NONE);
                    serialPort.setFlowControl(UsbSerialInterface.FLOW_CONTROL_OFF);
                    serialPort.read(mCallback);

                    // Everything went as expected. Send an intent to MainActivity
                    Intent intent = new Intent(ACTION_USB_READY);
                    context.sendBroadcast(intent);
                } else {
                    // Serial port could not be opened, maybe an I/O error or if CDC driver was chosen, it does not really fit
                    // Send an Intent to Main Activity
                    if (serialPort instanceof CDCSerialDevice) {
                        Intent intent = new Intent(ACTION_CDC_DRIVER_NOT_WORKING);
                        context.sendBroadcast(intent);
                    } else {
                        Intent intent = new Intent(ACTION_USB_DEVICE_NOT_WORKING);
                        context.sendBroadcast(intent);
                    }
                }
            } else {
                // No driver for given device, even generic CDC driver could not be loaded
                Intent intent = new Intent(ACTION_USB_NOT_SUPPORTED);
                context.sendBroadcast(intent);
            }
        }
    }

    /*
     * This handler will be passed to UsbService. Data received from serial port is displayed through this handler
     */

    public static class MyHandler extends Handler {

        @Override
        public void handleMessage(Message msg) {

            switch (msg.what) {
                case MESSAGE_FROM_SERIAL_PORT://case UsbService.MESSAGE_FROM_SERIAL_PORT:
                    data1 = (String) msg.obj; // send byte per byte
                   buffParsing(data1);
                    break;
            }
        }
    }
}