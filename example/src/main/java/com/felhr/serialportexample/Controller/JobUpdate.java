package com.felhr.serialportexample.Controller;

import android.util.Log;

import com.felhr.serialportexample.Controller.DatenObjekte;
import com.felhr.serialportexample.Controller.UsbService;

import java.util.zip.CRC32;
import java.util.zip.Checksum;

public class JobUpdate {

private static byte[] jobupdate = new byte[350];
private static byte[] jobupdate1 = new byte[350];
public static byte[] wholeframe = new byte[800];
public static byte[] wholeframe1 = new byte[800];
private static String temp = "";
public static UsbService uService;

    public static int findCRC16(byte[] buf, int len){
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

    public static void firstjobsend(){

        wholeframe[0] = 36; //0x24
        wholeframe[1] = 19; //0x13
        wholeframe[2] = 2;
        wholeframe[3] = 6; //can id
        wholeframe[4] = (byte) 224; //0x90
        wholeframe[5] = 8; //data length
        wholeframe[6] = 22; //0x16 Header
        wholeframe[7] = 1;
        wholeframe[8] = 1;
        wholeframe[9] = 19; //0x13
        wholeframe[10] = 1; //Header data
        wholeframe[11] = 40; //0x28
        wholeframe[12] = 32; //0x20
        wholeframe[13] = 2;
        wholeframe[14] = 35; //0x23

        Checksum tempcheck = new CRC32();
        tempcheck.update(wholeframe, 0, 15);// update the current checksum with the specified array of bytes
        long tempValue = tempcheck.getValue();// get the current checksum value //checksum is correct
        Log.i("tempValue",String.valueOf(tempValue));
        wholeframe[14] = (byte) ((tempValue >>> 24) & 0xFF); //msb
        wholeframe[15] = (byte) ((tempValue >>> 16) & 0xFF);
        wholeframe[16] = (byte) ((tempValue >>> 8) & 0xFF);
        wholeframe[17] = (byte) (tempValue & 0xFF); //lsb
        wholeframe[18] = 35; //0x23

        int num = 8;
        int nr = 19;
        for (int k = 0; k < 36; k++) { //send 36 frames to the machine

            wholeframe[nr] = 36; //0x24
            wholeframe[nr+1] = 19; //0x13
            wholeframe[nr+2] = 2;
            wholeframe[nr+3] = 6; //msb can id
            wholeframe[nr+4] = (byte) 224; //0x90 lsb can id
            wholeframe[nr+5] = 8; //data length
            wholeframe[nr+6] = DatenObjekte.longArr[num];
            wholeframe[nr+7] = DatenObjekte.longArr[num + 1];
            wholeframe[nr+8] = DatenObjekte.longArr[num + 2];
            wholeframe[nr+9] = DatenObjekte.longArr[num + 3];
            wholeframe[nr+10] = DatenObjekte.longArr[num + 4];
            wholeframe[nr+11] = DatenObjekte.longArr[num + 5];
            wholeframe[nr+12] = DatenObjekte.longArr[num + 6];
            wholeframe[nr+13] = DatenObjekte.longArr[num + 7];
            wholeframe[nr+14] = 35; //0x23

            Checksum tempcheck1 = new CRC32();
            tempcheck1.update(wholeframe, nr, 15);// update the current checksum with the specified array of bytes
            long tempValue1 = tempcheck1.getValue();// get the current checksum value
            wholeframe[nr+14] = (byte) ((tempValue1 >>> 24) & 0xFF); //msb checksum
            wholeframe[nr+15] = (byte) ((tempValue1 >>> 16) & 0xFF);
            wholeframe[nr+16] = (byte) ((tempValue1 >>> 8) & 0xFF);
            wholeframe[nr+17] = (byte) (tempValue1 & 0xFF); //lsb
            wholeframe[nr+18] = 35; //0x23

            num = num + 8; //last num is 296
            nr = nr +19;
            }

        for(int i=0; i<294; i++){ //store data bytes
                jobupdate[i] = DatenObjekte.longArr[i + 8];
        }

        //calculate CRC16
        int getCRC = findCRC16(jobupdate,294); //total 294 bytes of data
        wholeframe[703] = 36; //0x24
        wholeframe[704] = 19; //0x13
        wholeframe[705] = 2;
        wholeframe[706] = 6; //can id
        wholeframe[707] = (byte) 224; //0x90
        wholeframe[708] = 8; //data length
        wholeframe[709] = DatenObjekte.longArr[296];
        wholeframe[710] = DatenObjekte.longArr[297];
        wholeframe[711] = DatenObjekte.longArr[298];
        wholeframe[712] = DatenObjekte.longArr[299];
        wholeframe[713] = DatenObjekte.longArr[300];
        wholeframe[714] = DatenObjekte.longArr[301];
        wholeframe[715] = (byte)(getCRC&0xFF); //LSB
        wholeframe[716] = (byte)((getCRC>>>8)&0xFF); //MSB
        wholeframe[717] = 35; //0x23

        Checksum tempcheck2 = new CRC32();
        tempcheck2.update(wholeframe, 703, 15);// update the current checksum with the specified array of bytes
        long tempValue2 = tempcheck2.getValue();// get the current checksum value
        wholeframe[717] = (byte) ((tempValue2 >>> 24) & 0xFF); //msb
        wholeframe[718] = (byte) ((tempValue2 >>> 16) & 0xFF);
        wholeframe[719] = (byte) ((tempValue2 >>> 8) & 0xFF);
        wholeframe[720] = (byte) (tempValue2 & 0xFF); //lsb
        wholeframe[721] = 35; //0x23

        wholeframe[722] = 36; //0x24
        wholeframe[723] = 19; //0x13
        wholeframe[724] = 2;
        wholeframe[725] = 6; // msb can id
        wholeframe[726] = (byte) 224; //0xf0 lsb can id
        wholeframe[727] = 8; //data length
        wholeframe[728] = 0; //Footer Data
        wholeframe[729] = 0;
        wholeframe[730] = 3;
        wholeframe[731] = 4;
        wholeframe[732] = 22; //0x16 Footer
        wholeframe[733] = 1;
        wholeframe[734] = 1;
        wholeframe[735] = 19; //0x13
        wholeframe[736] = 35; //0x23

        Checksum tempcheck3 = new CRC32();
        tempcheck3.update(wholeframe, 722, 15);// update the current checksum with the specified array of bytes
        long tempValue3 = tempcheck3.getValue();// get the current checksum value
        wholeframe[736] = (byte) ((tempValue3 >>> 24) & 0xFF); //msb
        wholeframe[737] = (byte) ((tempValue3 >>> 16) & 0xFF);
        wholeframe[738] = (byte) ((tempValue3 >>> 8) & 0xFF);
        wholeframe[739] = (byte) (tempValue3 & 0xFF); //lsb
        wholeframe[740] = 35; //0x23
    }

    public static void secondjobsend(){

        wholeframe1[0] = 36; //0x24
        wholeframe1[1] = 19; //0x13
        wholeframe1[2] = 2;
        wholeframe1[3] = 6; //msb can id
        wholeframe1[4] = (byte) 224; //0xf0 lsb can id
        wholeframe1[5] = 8; //data length
        wholeframe1[6] = 1; //Header data
        wholeframe1[7] = 40; //0x28
        wholeframe1[8] = 32; //0x20
        wholeframe1[9] = 2;
        wholeframe1[10] = DatenObjekte.longArr[8];
        wholeframe1[11] = DatenObjekte.longArr[9];
        wholeframe1[12] = DatenObjekte.longArr[10];
        wholeframe1[13] = DatenObjekte.longArr[11];
        wholeframe1[14] = 35; //0x23

        Checksum tempcheck = new CRC32();
        tempcheck.update(wholeframe1, 0, 15);// update the current checksum with the specified array of bytes
        long tempValue = tempcheck.getValue();// get the current checksum value //checksum is correct
        //Log.i("tempValue",String.valueOf(tempValue));
        wholeframe1[14] = (byte) ((tempValue >>> 24) & 0xFF); //msb
        wholeframe1[15] = (byte) ((tempValue >>> 16) & 0xFF);
        wholeframe1[16] = (byte) ((tempValue >>> 8) & 0xFF);
        wholeframe1[17] = (byte) (tempValue & 0xFF); //lsb
        wholeframe1[18] = 35; //0x23

        int num = 12;
        for (int k = 0; k < 36; k++) { //send 36 frames to the machine

            wholeframe1[0] = 36; //0x24
            wholeframe1[1] = 19; //0x13
            wholeframe1[2] = 2;
            wholeframe1[3] = 6; //msb can id
            wholeframe1[4] = (byte) 224; //0xf0 lsb can id
            wholeframe1[5] = 8; //data length
            wholeframe1[6] = DatenObjekte.longArr[num];
            wholeframe1[7] = DatenObjekte.longArr[num + 1];
            wholeframe1[8] = DatenObjekte.longArr[num + 2];
            wholeframe1[9] = DatenObjekte.longArr[num + 3];
            wholeframe1[10] = DatenObjekte.longArr[num + 4];
            wholeframe1[11] = DatenObjekte.longArr[num + 5];
            wholeframe1[12] = DatenObjekte.longArr[num + 6];
            wholeframe1[13] = DatenObjekte.longArr[num + 7];
            wholeframe1[14] = 35; //0x23

            Checksum tempcheck1 = new CRC32();
            tempcheck1.update(wholeframe1, 0, 15);// update the current checksum with the specified array of bytes
            long tempValue1 = tempcheck1.getValue();// get the current checksum value
            //Log.i("tempValue1", String.valueOf(tempValue1));
            wholeframe1[14] = (byte) ((tempValue1 >>> 24) & 0xFF); //msb checksum
            wholeframe1[15] = (byte) ((tempValue1 >>> 16) & 0xFF);
            wholeframe1[16] = (byte) ((tempValue1 >>> 8) & 0xFF);
            wholeframe1[17] = (byte) (tempValue1 & 0xFF); //lsb
            wholeframe1[18] = 35; //0x23

            num = num + 8; //last num is 296
        }

        for(int i=0; i<294; i++){ //store data bytes
            jobupdate1[i] = DatenObjekte.longArr[i + 8];
        }

        //calculate CRC16
        int getCRC1 = findCRC16(jobupdate1,294); //total 294 bytes of data
        //Log.i("checksum1",String.valueOf(getCRC)); //correct checksum
        wholeframe1[0] = 36; //0x24
        wholeframe1[1] = 19; //0x13
        wholeframe1[2] = 2;
        wholeframe1[3] = 6; //msb can id
        wholeframe1[4] = (byte) 224; //0xf0 lsb can id
        wholeframe1[5] = 8; //data length
        wholeframe1[6] = DatenObjekte.longArr[300];
        wholeframe1[7] = DatenObjekte.longArr[301];
        wholeframe1[8] = (byte)(getCRC1&0xFF); //LSB
        wholeframe1[9] = (byte)((getCRC1>>>8)&0xFF); //MSB
        wholeframe1[10] = 0;
        wholeframe1[11] = 0;
        wholeframe1[12] = 3;
        wholeframe1[13] = 4;
        wholeframe1[14] = 35; //0x23

        Checksum tempcheck2 = new CRC32();
        tempcheck2.update(wholeframe1, 0, 15);// update the current checksum with the specified array of bytes
        long tempValue2 = tempcheck2.getValue();// get the current checksum value
        //Log.i("tempValue2", String.valueOf(tempValue2)); //correct checksum
        wholeframe1[14] = (byte) ((tempValue2 >>> 24) & 0xFF); //msb
        wholeframe1[15] = (byte) ((tempValue2 >>> 16) & 0xFF);
        wholeframe1[16] = (byte) ((tempValue2 >>> 8) & 0xFF);
        wholeframe1[17] = (byte) (tempValue2 & 0xFF); //lsb
        wholeframe1[18] = 35; //0x23
    }
}
