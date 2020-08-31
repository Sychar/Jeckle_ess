package com.felhr.serialportexample.View;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.felhr.serialportexample.Controller.DatenObjekte;
import com.felhr.serialportexample.Controller.DatenObjekteJob;
import com.felhr.serialportexample.Controller.DatenObjekteSend;
import com.felhr.serialportexample.Controller.ErrorMessages;
import com.felhr.serialportexample.Controller.ErrorMessagesEng;
import com.felhr.serialportexample.Controller.UsbService;
import com.felhr.serialportexample.Controller.MainActivity_Controller;
import com.felhr.serialportexample.DatenBank.InfoContract;
import com.felhr.serialportexample.DatenBank.JobContract;
import com.felhr.serialportexample.R;

import java.nio.charset.Charset;
import java.text.SimpleDateFormat;

import java.util.TimeZone;

public class MainActivity extends AppCompatActivity implements BlankFragment.OnFragmentInteractionListener , BetriebsArt.OnFragmentInteractionListener,Verfahren.OnFragmentInteractionListener{
    //locale Variablen

    private TextView txtProgress;
    private ProgressBar progressBarPlus;
    private ProgressBar progressBarMinus;
    private ProgressBar progressBar;
    private int pStatus = 0;
    private Handler handler = new Handler();
    private Handler newHandler = new Handler();
    private Button circle_button;
    private Button minus_button;
    private Button m_min;
    private Button volt;
    private Button testbtn;
    private Intent intent;
    private Button betribsart;
    private Button data;
    private Button plus;
    private Button verfahren1;
    private Button backHome;
    private int len;
    byte[] c = new byte[14];
    private UsbService usbService;
    private Button droessel;
    private BroadcastReceiver mReceiver;
    private BroadcastReceiver mReceiver1;
    private BroadcastReceiver mReceiver2;
    private BroadcastReceiver mReceiver3;
    private TextView anzeige1;
    private TextView anzeige2;
    private TextView anzeige3;
    private TextView textView4;
    private TextView txtprogress;
    private ImageButton Setting;
    private View frame;
    private    View kenn_fragment;
    private View betriebsart_fragement;
    private Button kennlinie;
    private UsbService.MyHandler mHandler;


    //static Variablem

    private static String temp = "DrahtDM";
    private static Boolean prograssHeid = false;
    private static boolean cheack = true;
    static boolean  verfahren_gedrückt =false;
    private static int rannum = 0;
    public static int headercounter = 0;
    private static String cj = "";
    private static String js = "";
    private static String tempstr = "";
    private static String tempstr1 = "";
    private static int exp;
    private static int exp1;
    private static int random = 0;
    public static String msg_for_me = "";
    public static String msg_for_me1 = "";
    public static String msg_for_me2 = "";
    public static String msg_for_can = "";
    public static String msg_for_can1 = "";
    public static int getCRCval;
    private static String fulljobstring = "";
    private static byte[] fulljobba;
    private static int countFrame = 0;
    private static Charset iso88591charset = Charset.forName("ISO-8859-1");







    private final ServiceConnection usbConnection = new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName arg0, IBinder arg1) {
            usbService = ((UsbService.UsbBinder) arg1).getService();
            usbService.setHandler(mHandler);
            usbService.setHandler1(newHandler);
        }

        @Override
        public void onServiceDisconnected(ComponentName arg0) {
            usbService = null;
        }
    };
    MainActivity_Controller mainActivityController = new MainActivity_Controller(MainActivity.this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        final Intent  intent = new Intent(this, DataLoger.class);
        startActivity(intent);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        variablen_init();
        progrssinit();
        setVisibility();



        mHandler = new UsbService.MyHandler();

       Setting= findViewById(R.id.setting);
         Setting.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 mainActivityController.onClick_newActivity(Setting.class);
             }
         });


        circle_button.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                shortclick();
                return false;
            }
        });

        View view= findViewById(R.id.fragment_test);
       view.setVisibility(View.INVISIBLE);

        final Button button_menu = (Button) findViewById(R.id.button_menu);
        button_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              v=findViewById(R.id.framelayout);
                allgemeinOnclick(v);
                ausblinden(kenn_fragment,betriebsart_fragement);
            }
        });




        final ProgressBar progress = (ProgressBar) findViewById(R.id.progress);

        progress.setVisibility(View.GONE);

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (pStatus <= 100) {
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            progressBar.setProgress(pStatus);

                        }
                    });
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    pStatus++;
                }
            }
        }).start();
        Thread t = new Thread() {
            @Override
            public void run() {
                try {
                    while (!isInterrupted()) {
                        Thread.sleep(1000);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                TextView tdate = (TextView) findViewById(R.id.date);
                                TextView tdate2 = (TextView) findViewById(R.id.date2);

                             long date = System.currentTimeMillis();

                                SimpleDateFormat sdf = new SimpleDateFormat("hh:mm a");
                                String dateString = sdf.format(date);
                                SimpleDateFormat sdf2 = new SimpleDateFormat("dd/MM/yyyy");
                                String dateString2 = sdf2.format(date);
                                tdate.setText(dateString);
                                tdate2.setText(dateString2);

                            }
                        });
                    }
                } catch (InterruptedException e) {
                }
            }
        };
        t.start();

        betribsart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                droessel.setEnabled(false);
              allgemeinOnclick(betriebsart_fragement);
              ausblinden(frame,kenn_fragment);
              ausblinden_drossel();
            }
        });

 verfahren1.setOnClickListener(new View.OnClickListener() {
     @Override
     public void onClick(View view) {

         if(!verfahren_gedrückt) {
             verfahren1.setTextColor(Color.GREEN);
             verfahren_gedrückt=true;
         }
         else if(verfahren_gedrückt){
             verfahren1.setTextColor(Color.WHITE);
             verfahren_gedrückt=false;
         }


     }
 });
 kennlinie = (Button) findViewById(R.id.kenn);
 kennlinie.setOnClickListener(new View.OnClickListener() {
     @Override
     public void onClick(View view) {
       view=findViewById(R.id.fragment_test);
       allgemeinOnclick(view);
       ausblinden(frame,betriebsart_fragement);
       ausblinden_drossel();
     }
 });


        droessel = (Button) findViewById(R.id.drossel);
        droessel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if (prograssHeid) {
                    progressBarPlus.setVisibility(View.GONE);
                    progressBarMinus.setVisibility(View.INVISIBLE);
                  //  circle_button.setVisibility(View.GONE);
                    minus_button.setVisibility(View.GONE);
                    plus.setVisibility(View.INVISIBLE);
                    prograssHeid = false;
                } else {
                    progressBarPlus.setVisibility(View.VISIBLE);
                    progressBarMinus.setVisibility(View.VISIBLE);
                  //  circle_button.setVisibility(View.VISIBLE);
                    minus_button.setVisibility(View.VISIBLE);
                    plus.setVisibility(View.VISIBLE);
                    prograssHeid = true;
                }
            }
        });

        backHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                droessel.setEnabled(true);
                View layout =(View) findViewById(R.id.zweitelayout);
                layout.setVisibility(View.VISIBLE);
                frame.setVisibility(View.INVISIBLE);

                kenn_fragment.setVisibility(View.INVISIBLE);
                View layout1 = findViewById(R.id.circleButton);
                betriebsart_fragement.setVisibility(View.INVISIBLE);
                layout1.setVisibility(View.VISIBLE);
            }
        });
        testbtn = (Button)findViewById(R.id.testbutton);
        testbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatenObjekteSend sendobj = new DatenObjekteSend();


                cj = UsbService.changeJob();
                js = UsbService.changeJob1(13); //get job params

                if (DatenObjekte.checktoken == 0){
                    if  (cj != null && !cj.equals("")) { //first job frame
                        if (usbService != null) usbService.write(cj.getBytes(iso88591charset));
                    }
                    delayInMilli(50); //delay 0.05s
                    if (js != null && !js.equals("")) { //second job frame
                        if (usbService != null) usbService.write(js.getBytes(iso88591charset));
                    }
                    DatenObjekte.checktoken = 1;
                }

                delayInMilli(50); //delay 0.05s
                DatenObjekteJob.DataBase();
                System.out.println("daten ist :" +DatenObjekteJob.uiJobNr);
                JobsDetails jobsDetails=new JobsDetails();

               jobsDetails.iint_datejob();
                ContentValues values = new ContentValues();
                if (DatenObjekteJob.uiJobNr!=0) {
                    for (int i = 0; i < 112; i++) {
                        System.out.println(JobsDetails.modiJobsdeateils[i]);
                        values.put(jobsDetails.jobdetails[i], JobsDetails.modiJobsdeateils[i]);
                    }
                    getContentResolver().insert(JobContract.jobEntry.CONTENT_URI, values);
                }

                if (msg_for_can != null && !msg_for_can.equals("")) { //send parameter frame to the machine
                    if (usbService != null) usbService.write(msg_for_can.getBytes(iso88591charset));
                    msg_for_can = "";
                }
                delayInMilli(50); //delay 0.05s
                if (msg_for_can1 != null && !msg_for_can1.equals("")) { //send value frame to the machine
                    if (usbService != null) usbService.write(msg_for_can1.getBytes(iso88591charset));
                    msg_for_can1 = "";
                }
                //random++;
                //UsbService.token = true;
            }
        });
        newHandler.post(runnableCode);
    }

    public void shortclick() {
        PopupMenu popupMenu = new PopupMenu(MainActivity.this, circle_button);
        popupMenu.getMenuInflater().inflate(R.menu.menu1, popupMenu.getMenu());
        popupMenu.show();
    }

    static void delayInMilli(int DELAY_MILLISEC){
        try {
            Thread.sleep(DELAY_MILLISEC);
        }catch(InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
    }

    public void ChangeTextprogressBar(Button btn) {

        int id = btn.getId();
        if (id == R.id.btn_korrektur) {

            TextView textView =  findViewById(R.id.korrektur);
            String s = textView.getText().toString();
            String s1 = btn.getText().toString();
            String s2 = txtProgress.getText().toString();
            textView.setText(temp);
            btn.setText(s2);
            txtProgress.setText(s1);
            temp = s;
            cheack = false;
        }
        if (id == R.id.btn_min_m) {

            TextView textView =  findViewById(R.id.MproMin);
            String s = textView.getText().toString();
            String s1 = btn.getText().toString();
            String s2 = txtProgress.getText().toString();
            textView.setText(temp);
            txtProgress.setText(s1);
            btn.setText(s2);
            temp = s;
            cheack = false;
        }
        if (id == R.id.btn_volt) {
            TextView textView =  findViewById(R.id.volt);
            String s = textView.getText().toString();
            String s1 = btn.getText().toString();
            String s2 = txtProgress.getText().toString();
            textView.setText(temp);
            txtProgress.setText(s1);
            btn.setText(s2);
            temp = s;
            cheack = false;
        }
    }



    @Override
    public void onResume() {
        super.onResume();
        setFilters();  // Start listening notifications from UsbService
        UsbService ooo = new UsbService(this);//mine
        ooo.startService(UsbService.class, usbConnection, null); // Start UsbService(if it was not started before) and Bind it//mine
        //startService(UsbService.class, usbConnection, null);
    }

    @Override
    public void onPause() {
        super.onPause();
        unregisterReceiver(mUsbReceiver);
        unbindService(usbConnection);
        this.unregisterReceiver(this.mReceiver); //unregister our receiver
        this.unregisterReceiver(this.mReceiver1); //unregister our receiver
        this.unregisterReceiver(this.mReceiver2); //unregister my receiver
        this.unregisterReceiver(this.mReceiver3); //unregister my receiver
    }

    protected void onStop() {
        super.onStop();
        Toast.makeText(getApplicationContext(), "onStop called", Toast.LENGTH_LONG).show();
    }

    private final BroadcastReceiver mUsbReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            switch (intent.getAction()) {
                case UsbService.ACTION_USB_PERMISSION_GRANTED: // USB PERMISSION GRANTED
                    Toast.makeText(context, "USB Ready", Toast.LENGTH_SHORT).show();
                    break;
                case UsbService.ACTION_USB_PERMISSION_NOT_GRANTED: // USB PERMISSION NOT GRANTED
                    Toast.makeText(context, "USB Permission not granted", Toast.LENGTH_SHORT).show();
                    break;
                case UsbService.ACTION_NO_USB: // NO USB CONNECTED
                    Toast.makeText(context, "No USB connected", Toast.LENGTH_SHORT).show();
                    break;
                case UsbService.ACTION_USB_DISCONNECTED: // USB DISCONNECTED
                    Toast.makeText(context, "USB disconnected", Toast.LENGTH_SHORT).show();
                    break;
                case UsbService.ACTION_USB_NOT_SUPPORTED: // USB NOT SUPPORTED
                    Toast.makeText(context, "USB device not supported", Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    };

    private void setFilters() {
        IntentFilter filter = new IntentFilter();
        filter.addAction(UsbService.ACTION_USB_PERMISSION_GRANTED);
        filter.addAction(UsbService.ACTION_NO_USB);
        filter.addAction(UsbService.ACTION_USB_DISCONNECTED);
        filter.addAction(UsbService.ACTION_USB_NOT_SUPPORTED);
        filter.addAction(UsbService.ACTION_USB_PERMISSION_NOT_GRANTED);
        registerReceiver(mUsbReceiver, filter);

        IntentFilter intfilter = new IntentFilter("Main.Activity");

        mReceiver = new BroadcastReceiver() { //my code
            @Override
            public void onReceive(Context context, Intent intent) {
                msg_for_me = intent.getStringExtra("msg_service");//extract our message from intent
            }
        };
        this.registerReceiver(mReceiver, intfilter);//registering our receiver //my code

        IntentFilter intfilter1 = new IntentFilter("Main.Activity1"); // my code
        mReceiver1 = new BroadcastReceiver() { //my code
            @Override
            public void onReceive(Context context, Intent intent) {
                msg_for_me1 = intent.getStringExtra("msg_service1");//extract our message from intent
            }
        };
        this.registerReceiver(mReceiver1, intfilter1);//registering our receiver //my code

        IntentFilter intfilter2 = new IntentFilter("Main.Activity2"); // my code
        mReceiver2 = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                msg_for_can = intent.getStringExtra("change_param");//extract modified hex data from intent
            }
        };
        this.registerReceiver(mReceiver2, intfilter2);//registering our receiver

        IntentFilter intfilter3 = new IntentFilter("Main.Activity3");
        mReceiver3 = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                msg_for_can1 = intent.getStringExtra("change_param1");
            }
        };
        this.registerReceiver(mReceiver3, intfilter3);//registering our receiver
    }

    private Runnable runnableCode = new Runnable() {
        @Override
        public void run() {
            UsbService.token = false;
            //Log.i("Timer Handler","Running");

            DatenObjekte callmethod = new DatenObjekte(); //create object of class DatenObjekte //my code
            ErrorMessages ListOfErrorMessageclass = new ErrorMessages();
            ErrorMessagesEng engobj = new ErrorMessagesEng();

            if (msg_for_me1 != null && !msg_for_me1.equals("")) { //data in ascii string

                msg_for_me1 = "";
            }

            if (msg_for_me != null && !msg_for_me.equals("")) { //data in hex string

                rannum++;
                final TextView textView =  findViewById(R.id.anzeige1);
                TextView textView2 =  findViewById(R.id.anzeige2);
                TextView textView3 =  findViewById(R.id.anzeige3);

                if (cheack) {
                    txtProgress.setText(String.valueOf(DatenObjekte.Drahtdurchmesser));
                    textView.setText(String.valueOf(DatenObjekte.Betriebsart));
                    textView2.setText(String.valueOf(DatenObjekte.Verfahren));
                    textView3.setText(String.valueOf(DatenObjekte.Gas) + "          " + String.valueOf(DatenObjekte.Drahtdurchmesser));

                    minus_button.setText(String.valueOf(DatenObjekte.Lichtbogenkorrektur1));

                    m_min.setText(String.valueOf(DatenObjekte.Stromtest));

                    volt.setText(String.valueOf(DatenObjekte.Spannung1));

                }

                ListOfErrorMessageclass.ListOfErrorMessage(msg_for_me);
                engobj.ListOfErrorMessageEng(msg_for_me);

                minus_button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        ChangeTextprogressBar(minus_button);
                    }
                });

                m_min.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        new MainActivity_Controller().ChangeTextprogressBar(m_min,txtProgress,temp,cheack);
                        ChangeTextprogressBar(m_min );
                    }
                });

                volt.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ChangeTextprogressBar(volt);
                    }
                });

                long date = System.currentTimeMillis();
                SimpleDateFormat sdf = new SimpleDateFormat("h-mm-ss a");
                sdf.setTimeZone(TimeZone.getTimeZone("Germany/Berlin"));
                String dateString = sdf.format(date);
                String s= dateString;
                len = DatenObjekte.LengthProtocol2;
                String len1=len+" ";
                ContentValues values =new ContentValues();
                values.put(InfoContract.infoEntry.COLUMN_LEN_,len1);
                values.put(InfoContract.infoEntry.COLUMN_CANID,DatenObjekte.gethex);
                values.put(InfoContract.infoEntry.COLUMN_TIME_,s );
                values.put(InfoContract.infoEntry.COLUMN_CANDATA_," ");

                getContentResolver().insert(InfoContract.infoEntry.CONTENT_URI,values);




                msg_for_me = "";


            }

            if (UsbService.HeaderFound == 1) {
                headercounter++;
                if (headercounter > 10) {
                    UsbService.HeaderFound = 0;
                    headercounter = 0;
                }
            }

            if (DatenObjekte.HFound == 1) {
                //debugtext.setText("Job HeaderFound");
                countFrame++;
                if (countFrame > 1000) {//&& (DatenObjekte.jobtoken != 1)) { //more than 1s and data is not complete
                    countFrame = 0;
                    DatenObjekte.HFound = 0;
                    DatenObjekte.jobtoken = 0;
                    //debugtext.setText("Timer 1 second");
                }
            }
            newHandler.postDelayed(runnableCode, 1); //reads data from service every 1ms
            UsbService.token = true;
        }
    };






    void allgemeinOnclick(View view ){
        View layout = findViewById(R.id.zweitelayout);
        View layout1 = findViewById(R.id.circleButton);
        layout.setVisibility(View.INVISIBLE);
        layout1.setVisibility(View.INVISIBLE);
        view.setVisibility(View.VISIBLE);

    }


    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    public void onpressed_Minus_plus(View view ) {
       mainActivityController.minus_plus_interagieren(progressBarMinus,progressBarPlus,view);
    }



    private  void  progrssinit(){
        progressBarMinus.setProgress(0);
        progressBarPlus.setProgress(0);

    }

    private  void variablen_init(){
        kenn_fragment =findViewById(R.id.fragment_test);
        betriebsart_fragement=findViewById(R.id.frgment_Betriebsart);
        plus=findViewById(R.id.plus);
        progressBarMinus=findViewById(R.id.progress);
        progressBarPlus=findViewById(R.id.progress1);
        verfahren1 =findViewById(R.id.verfahren1);
        minus_button = findViewById(R.id.minus);
        volt = findViewById(R.id.btn_volt);
        m_min = findViewById(R.id.btn_min_m);
        backHome = findViewById(R.id.backhome);
        betribsart = findViewById(R.id.Betribsart);
        progressBar = findViewById(R.id.progressBar);
        circle_button = findViewById(R.id.button1);
        txtProgress = findViewById(R.id.txtProgress);
        frame = findViewById(R.id.framelayout);
        anzeige1 = findViewById(R.id.anzeige1);
        anzeige2 = findViewById(R.id.anzeige2);
        anzeige3 = findViewById(R.id.anzeige3);
        txtprogress = findViewById(R.id.txtProgress);
    }

    private void setVisibility(){
        betriebsart_fragement.setVisibility(View.INVISIBLE);
        plus.setVisibility(View.INVISIBLE);
        volt.setVisibility(View.VISIBLE);
        progressBarPlus.setVisibility(View.INVISIBLE);
        progressBarMinus.setVisibility(View.INVISIBLE);
        frame.setVisibility(View.INVISIBLE);
        circle_button.setVisibility(View.GONE);
        minus_button.setVisibility(View.GONE);
    }
    private  void ausblinden(View view1, View view2){
        view1.setVisibility(View.INVISIBLE);
        view2.setVisibility(View.INVISIBLE);
    }
    void ausblinden_drossel(){
        progressBarPlus.setVisibility(View.GONE);
        progressBarMinus.setVisibility(View.INVISIBLE);
        //  circle_button.setVisibility(View.GONE);
        minus_button.setVisibility(View.GONE);
        plus.setVisibility(View.INVISIBLE);
        droessel.setEnabled(false);
    }
}