
/**
 * @Autor Sakhr Aljendi
 * @Version 1.0
 * 09.02.2020
 */

package com.felhr.serialportexample.Controller;

import java.nio.charset.Charset;
import android.util.Log;

public class DatenObjekte {

    public static byte[] bb = new byte[19];
    //private static String strCanId = "";
    //private static byte[] check1;
    public static byte[] str1;
    public static String gethex = "";
    private static int countertoken = 0;
    public static int jobtoken = 0;
    public static int checktoken = 0;
    public static String stringjob = "";
    private static int counterjobframe = 0;
    public static byte[] longArr = new byte[350];
    public static int y = 0;
    public static int HFound = 0;

    private static int HeaderFound1 = 0;
    private static int LengthFound1 = 0;
  public static int LengthProtocol1 = 0;
    public static int LengthProtocol2 =0;
    private static int CounterData1 = 0;

    //private static String canid = "";
    public static int Stromtest;

    //SV 1
    public static String Verfahren = "";
    public static String Betriebsart = "";
    public static String Drahtdurchmesser = "";
    public static String Gas = "";
    public static String Werkstoff;
    public static String Reglertyp;
    public static String StatusMSR;
    public static String StatusMSRbit0;
    public static String StatusMSRbit1;
    public static String StatusMSRbit2;
    public static String StatusMSRbit3;
    public static String StatusMSRbit4;
    public static String StatusMSRbit5;
    public static String StatusMSRbit6;
    public static String StatusMSRbit7;
    public static String StatusFLG;
    public static String StatusFLGbit0;
    public static String StatusFLGbit1;
    public static String StatusFLGbit2;
    public static String StatusFLGbit3;
    public static String StatusFLGbit4;
    public static String StatusFLGbit5;
    public static String StatusFLGbit6;
    public static String StatusFLGbit7;

    public static int SV1pos1;
    public static int SV1pos2;
    public static int SV1pos3;
    public static int SV1pos4;
    public static int SV1pos5;
    public static int SV1pos6;
    public static int SV1pos7;
    public static int SV1pos8;
    public static String s1pos7 = "";
    public static String s1pos8 = "";

    //SV 2
    public static int Kennliniennummer;
    public static int Jobnummer;
    public static char KennlinienTyp;
    public static char SV2pos1;
    public static char SV2pos2;
    public static char SV2pos3;
    public static char SV2pos4;
    public static char SV2pos5;
    public static char SV2pos6;
    public static char SV2pos7;
    public static char SV2pos8;
    public static String strKennlinienTyp = "";
    public static String KennlinienTypbit0;
    public static String KennlinienTypbit1;
    public static String KennlinienTypbit2;
    public static String KennlinienTypbit3;
    public static String KennlinienTypbit4;
    public static String KennlinienTypbit5;
    public static String KennlinienTypbit6;
    public static String KennlinienTypbit7;

    public static char JobKommando;
    public static char JobStatus;
    public static String JobStatusbit0;
    public static String JobStatusbit1;
    public static String JobStatusbit2;
    public static String JobStatusbit3;
    public static String JobStatusbit4;
    public static String JobStatusbit5;
    public static String JobStatusbit6;
    public static String JobStatusbit7;
    public static String strJobStatus = "";
    public static char Verriegelungsstufe;

    //SV 3
    public static char Gasvorströmen;
    public static char Gasnachströmen;
    public static char EinschleichenAbsolut;
    public static char EinschleichenKorrektur;
    public static char UpSlope;
    public static char DownSlope;
    public static char Zündenergie;
    public static char Endkraterenergie;

    //SV 4
    public static char GebirgeStatus;
    public static char SchweißState;
    public static char Freibrand;
    public static char FreibandKorrektur;
    public static char KorrekturPulsamplitude;
    public static char KorrekturDrossel  ;
    public static char Einfädeln;
    public static char GastestZeit;

    //Sv 5
    public static int PausenZeit;
    public static int Punktzeit;
    public static char ZündDauer;
    public static char EndkraterDauer;
    public static char SynergieVorgabe;
    public static char AnzahlLeistungsmodule;

    //SV 6
    public static char PowerpulsEinAus;
    public static char PowerpulsE2;
    public static char PowerpulsT1E1;
    public static char PowerpulsT2E1;
    public static char PowerpulsLBKorrE2;
    public static char PowerpulsUpSlope;
    public static char PowerpulsDownSlope;
    public static char JobSlope;
    public static String strPowerpulsEinAus = "";

    //SV 7
    public static int ZündStrom;
    public static int ZündSpannung;
    public static int ZündEnergie;
    public static char ZündDrossel;
    public static char ZündLichtbogenkorrektur;

    //SV 8
    public static int Strom1;
    public static int Spannung1;
    public static int Energie1;
    public static char Drossel1;
    public static int Lichtbogenkorrektur1;

    //SV 9
    public static int Strom2;
    public static int Spannung2;
    public static int Energie2;
    public static char Drossel2;
    public static char Lichtbogenkorrektur2;

    //SV 10
    public static int Strom3;
    public static int Spannung3;
    public static int Energie3;
    public static char Drossel3;
    public static char Lichtbogenkorrektur3;

    //SV 11
    public static int EndkraterStrom;
    public static int EndkraterSpannung;
    public static int EndkraterEnergie;
    public static char EndkraterDrossel;
    public static char EndKraterLichtbogenkorrektur;

    //SV 12
    public static int VorschubSetwert;
    public static int VorschubIstwert;
    public static int VorschubHoldwert;
    public static char VorschubStatus;
    public static char VorschubAusKennlinie;

    //SV 13
    public static int StromSetwert ;
    public static int StromIstwert;
    public static int StromHoldwert=100;
    public static char StromStatus;
    public static char StromInkremental;

    //SV 14
    public static int SpannungSetwert;
    public static int SpannungIstwert;
    public static int SpannungHoldwert;
    public static char SpannungStatus;
    public static char SpannungInkremental;

    //SV 15
    public static int BlechdickeSetwert;
    public static int BlechdickeIstwert;
    public static int BlechdickeHoldwert;
    public static char BlechdickeStatus;
    public static char Reset;
    public static String Resetbit0 = "";

    //SV 16
    public static int ElektrodeStromSetwert;
    public static int ElektrodeStromIstwert;
    public static char HotstartDauer;
    public static char Hotstart;
    public static char ArcForce;
    public static char InnenwiderstandfürElektrode;

    //SV 17
    public static int RMTPosAmplitude;
    public static int RMTNegAmplitude;
    public static int StartAmplitude;
    public static char StartZeit;
    public static char StartÜberhöhung;

    //SV 18
    public static char InnenwiderstandfürDossel;
    public static char Überblendzeit;
    public static char DrosselAbfall;
    public static char MotorFlanke;
    public static char DrosselDynamic;
    public static char MAGACPositiveZeit;
    public static char MAGACStromschwellwert;
    public static char LBRMode;

    //SV 19
    public static char MAGACBetriebsart;
    public static String strMAGACBetriebsart = "";
    public static char MAGACKältewert;
    public static char MAGACNegativZeit;
    public static char MAGACKurzschlusserkennung;
    public static char MAGACKurzschlussaufhebung;
    public static char MACAGVerweilzeitPosNeg;
    public static char MACAGVerweilzeitNegPos;

    //SV 20
    public static int WIGSpeedPulsFrequenz;
    public static char WIGSpeedPulsI1Anteil;
    public static char WIGSpeedPulsI3;
    public static char GasSollwert;
    public static char UserNummer;
    public static char WIGACStromoffset;
    public static char WIGStatus;
    public static String WIGStatusbit0 = "";
    public static String WIGStatusbit1 = "";
    public static String WIGStatusbit2 = "";
    public static String WIGStatusbit3 = "";
    public static String WIGStatusbit4 = "";
    public static String WIGStatusbit5 = "";
    public static String WIGStatusbit6 = "";
    public static String WIGStatusbit7 = "";

    //SV 21
    public static int WIGACFrequenz;
    public static char WIGACBalance;
    public static char WIGDurchmesserWolframElektrode;
    public static char WIGBetriebsartWechselrichter;
    public static char KaltdrahtpulsenT1SV21_5;
    public static int WIGStromLimit;
    public static String strWIGBetriebsartWechselrichter = "";

    //SV 22
    public static char KHMode;
    public static char VerzögerungsZeitKaltdrahtEin;
    public static char VerzögerungsZeitKaltdrahtAus;
    public static char VerzögerungsZeitHeißdrahtÜberwachung;
    public static char Vorpositionierungsstrecke;
    public static char Rückzugsstrecke;
    public static char KaltdrahtpulsenT1SV22_7;
    public static char KHStatus;
    public static String strKHMode = "";
    public static String strKHStatus = "";
    public static String strKHModebit0 = "";
    public static String strKHModebit1 = "";
    public static String strKHModebit2 = "";
    public static String strKHModebit3 = "";
    public static String strKHModebit4 = "";
    public static String strKHModebit5 = "";
    public static String strKHModebit6 = "";
    public static String strKHModebit7 = "";
    public static String strKHStatusbit0 = "";
    public static String strKHStatusbit1 = "";
    public static String strKHStatusbit2 = "";
    public static String strKHStatusbit4 = "";
    public static String strKHStatusbit5 = "";
    public static String strKHStatusbit6 = "";
    public static String strKHStatusbit7 = "";

    public static int counterTest = 0;

    private static Charset iso88591charset = Charset.forName("ISO-8859-1");

    public static void callme(String msgReceiver){//msgReceiver is in hex String

        char pos_7 = msgReceiver.charAt(6);
        char pos_8 = msgReceiver.charAt(7);
        char pos_9 = msgReceiver.charAt(8);
        char pos_10 = msgReceiver.charAt(9);

        StringBuilder joinchar = new StringBuilder();
        gethex = joinchar.append(pos_7).append(pos_8).append(pos_9).append(pos_10).toString();

        if(gethex.equals("06f0")){
            counterTest++;
            counterjobframe++;
            if((bb[6]==22) && (bb[7]==1) && (counterjobframe==1) && (HFound==0)){
                countertoken = 1;
                HFound = 1; //Header is found
                for(int i=6; i<14; i++){
                    longArr[y] = bb[i];// in bytes
                    y++;
                }
              //  Log.i("Start frame",String.valueOf(y));
            }else if(countertoken==1 && counterjobframe<=38){
                for(int i=6; i<14; i++){
                    longArr[y] = bb[i];// in bytes
                    y++;
                }
              //  Log.i("Middle frame",String.valueOf(y));
            }
            if(bb[8]==3 && bb[9]==4 && counterjobframe==39){
                counterjobframe = 0;
                countertoken = 0;
                HFound = 0; // reset header
                for(int i=6; i<10; i++){
                    longArr[y] = bb[i];// in bytes
                    y++; //last y = 307
                }
                checktoken = 1;
                jobtoken = 1; //Data is complete
                Log.i("End frame", String.valueOf(y));
                y = 0;
            }
        }

        if(gethex.equals("0181")){
            SV1pos1 = (int)bb[6];//pos 1
            switch(SV1pos1){
                case 0:
                    Verfahren = "NONE";
                    break;
                case 1:
                    Verfahren = "MAG_Normal";
                    break;
                case 2:
                    Verfahren = "MAG-Synergie";
                    break;
                case 3:
                    Verfahren = "MAG Puls";
                    break;
                case 4:
                    Verfahren = "Elektrode";
                    break;
                case 5:
                    Verfahren = "WIG";
                    break;
                case 6:
                    Verfahren = "WIG-Puls";
                    break;
                case 7:
                    Verfahren = "WIG-Speed-Puls";
                    break;
                case 8:
                    Verfahren = "WIG-Speed_Puls + Puls";
                    break;
                case 9:
                    Verfahren = "HC_MAG";
                    break;
                default:
                    Verfahren = "Error";
                    break;
            }


            SV1pos2 = (int)bb[7];//pos 2
            switch(SV1pos2){
                case 0:
                    Betriebsart = "NONE";
                    break;
                case 1:
                    Betriebsart = "2-Takt";
                    break;
                case 2:
                    Betriebsart = "4-Takt";
                    break;
                case 3:
                    Betriebsart = "4-Takt Sonder";
                    break;
                case 4:
                    Betriebsart = "Programm";
                    break;
                case 5:
                    Betriebsart = "Punkten";
                    break;
                case 6:
                    Betriebsart = "Intervall";
                    break;
                case 7:
                    Betriebsart = "Extern";
                    break;
                case 8:
                    Betriebsart = "2-Takt + HF";
                    break;
                case 9:
                    Betriebsart = "4-Takt + HF";
                    break;
                default:
                    Betriebsart = "Error";
                    break;
            }

            SV1pos3 = (int)bb[8];//pos 3
            switch(SV1pos3){
                case 0:
                    Drahtdurchmesser = "NONE";
                    break;
                case 1:
                    Drahtdurchmesser = "0,6mm";
                    break;
                case 2:
                    Drahtdurchmesser = "0,8mm";
                    break;
                case 3:
                    Drahtdurchmesser = "0,9mm";
                    break;
                case 4:
                    Drahtdurchmesser = "1,0mm";
                    break;
                case 5:
                    Drahtdurchmesser = "1,2mm";
                    break;
                case 6:
                    Drahtdurchmesser = "1,4mm";
                    break;
                case 7:
                    Drahtdurchmesser = "1,6mm";
                    break;
                case 8:
                    Drahtdurchmesser = "2,0mm";
                    break;
                case 9:
                    Drahtdurchmesser = "2,4mm";
                    break;
                case 10:
                    Drahtdurchmesser = "Spezial";
                    break;
                default:
                    Drahtdurchmesser = "Error";
                    break;
            }

            SV1pos4 = (int)bb[9];//pos 4
            switch(SV1pos4){
                case 0:
                    Gas = "82% Argon 18% CO2";
                    break;
                case 1:
                    Gas = "98% Argon 2% CO2";
                    break;
                case 2:
                    Gas = "100% Argon";
                    break;
                case 3:
                    Gas = "100% CO2";
                    break;
                case 4:
                    Gas = "92% Argon 8% CO2";
                    break;
                case 5:
                    Gas = "99% Argon 1% O2";
                    break;
                case 6:
                    Gas = "98% Argon 2% O2";
                    break;
                case 7:
                    Gas = "97% Argon 3% O2";
                    break;
                case 8:
                    Gas = "92% Argon 8% O2";
                    break;
                case 9:
                    Gas = "90% Argon 5% O2 5% CO2";
                    break;
                case 10:
                    Gas = "100% Helium";
                    break;
                case 11:
                    Gas = "80% Argon 20% He";
                    break;
                case 12:
                    Gas = "69% Argon 30% He 1% O2";
                    break;
                case 13:
                    Gas = "50% Argon 50% Helium";
                    break;
                case 14:
                    Gas = "98% Argon 2% H2";
                    break;
                case 15:
                    Gas = "94% Argon 6% H2";
                    break;
                case 16:
                    Gas = "50% Argon 50% H2";
                    break;
                case 17:
                    Gas = "30% Argon 70% H2";
                    break;
                case 18:
                    Gas = "Spezial";
                    break;
                default: Gas = "Error";
                    break;
            }

            //Log.d("SV1pos4 ", Gas);

            SV1pos5 = (int)bb[10];//pos 5
            switch(SV1pos5){
                case 0:
                    Werkstoff = "NONE";
                    break;
                case 1:
                    Werkstoff = "Fe";
                    break;
                case 2:
                    Werkstoff = "CrNi";
                    break;
                case 3:
                    Werkstoff = "AlMg";
                    break;
                case 4:
                    Werkstoff = "AlSi";
                    break;
                case 5:
                    Werkstoff = "CuSi";
                    break;
                case 6:
                    Werkstoff = "AlMg3";
                    break;
                case 7:
                    Werkstoff = "AlMg5";
                    break;
                case 8:
                    Werkstoff = "AlMg4,5Mn";
                    break;
                case 9:
                    Werkstoff = "AlBz";
                    break;
                case 10:
                    Werkstoff = "Spezial";
                    break;
                default:
                    Werkstoff = "Error";
                    break;
            }

            SV1pos6 = (int)bb[11];//pos 6
            switch(SV1pos6){
                case 0:
                    Reglertyp = "U/I - Regler ohne Lichtbogen-Regler";
                    break;
                case 1:
                    Reglertyp = "I/I - Regler ohne Lichtbogen-Regler";
                    break;
                case 2:
                    Reglertyp = "U/I - Regler mit Lichtbogen-Regler";
                    break;
                case 3:
                    Reglertyp = "I/I - Regler mit Lichtbogen-Regler";
                    break;
                default:
                    Reglertyp = "Error";
                    break;
            }

            int iSMSR = (int)bb[12];//pos 7
            int result = iSMSR;

            if((result & 1) == 1 ){
                StatusMSRbit0 = "Schweißen Ein";
            }

            result = result >>> 1;//shift first time

            if((result & 1) == 0 ){
                StatusMSRbit1 = "akt. Koffer 1";
            }else{
                StatusMSRbit1 = "akt. Koffer 2";
            }

            result = result >>> 1;//shift second time

            if((result & 1) == 0 ){
                StatusMSRbit2 = "Synergie Ein";
            }else{
                StatusMSRbit2 = "Synergie Aus";
            }

            result = result >>> 1;//shift third time

            if((result & 1) == 1 ){
                StatusMSRbit3 = "Einfädeln Vor";
            }

            result = result >>> 1;//shift fourth time

            if((result & 1) == 1 ){
                StatusMSRbit4 = "Kühlen";
            }

            result = result >>> 1;//shift fifth time

            if((result & 1) == 1 ){
                StatusMSRbit5 = "Einfädeln Zurück";
            }

            result = result >>> 1;//shift sixth time

            if((result & 1) == 1 ){
                StatusMSRbit6 = "Gas-Test";
            }

            result = result >>> 1;//shift seventh time

            if((result & 1) == 1 ){
                StatusMSRbit7 = "Tastenklick Ein";
            }

            int iSFLG = (int)bb[13];//pos 8
            int result1 = iSFLG;

            if((result1 & 1) == 1 ){
                StatusFLGbit0 = "FLG im Gebirge-Mode";
            }

            result1 = result1 >>> 1;//shift first time

            if((result1 & 1) == 1 ){
                StatusFLGbit1 = "FLG mit Gebirge";
            }

            result1 = result1 >>> 1;//shift second time

            if((result1 & 1) == 1 ){
                StatusFLGbit2 = "Zünden Aus";
            }

            result1 = result1 >>> 1;//shift third time

            if((result1 & 1) == 1 ){
                StatusFLGbit3 = "Rückzugs-Zündung Aus";
            }

            result1 = result1 >>> 1;//shift fourth time

            if((result1 & 1) == 1 ){
                StatusFLGbit4 = "Endpuls Aus";
            }

            result1 = result1 >>> 1;//shift fifth time

            if((result1 & 1) == 1 ){
                StatusFLGbit5 = "Locking-Edit_Mode";
            }

            result1 = result1 >>> 1;//shift sixth time

            if((result1 & 1) == 1 ){
                StatusFLGbit6 = "Wasser fließt";
            }

            result1 = result1 >>> 1;//shift seventh time

            if((result1 & 1) == 1 ){
                StatusFLGbit7 = "Freiband-Warnung";
            }

        }else if(gethex.equals("0182")){

            Kennliniennummer = (int)bb[6];//pos 1 or 2
            Jobnummer = (int)bb[8];//pos 3 or 4

            int iKT = (int)bb[10];//pos 5
            int result2 = iKT;

            if((result2 & 1) == 1 ){
                KennlinienTypbit0 = "Typ-Bit 1 = 2^0";
            }

            result2 = result2 >>> 1;//shift first time

            if((result2 & 1) == 1 ){
                KennlinienTypbit1 = "Typ-Bit 2 = 2^1";
            }

            result2 = result2 >>> 1;//shift second time

            if((result2 & 1) == 1 ){
                KennlinienTypbit2 = "Typ-Bit 3 = 2^2";
            }

            result2 = result2 >>> 1;//shift third time

            if((result2 & 1) == 1 ){
                KennlinienTypbit3 = "Typ-Bit 4 = 2^4";
            }

            result2 = result2 >>> 1;//shift fourth time

            if((result2 & 1) == 1 ){
                KennlinienTypbit4 = "Res.";
            }

            result2 = result2 >>> 1;//shift fifth time

            if((result2 & 1) == 1 ){
                KennlinienTypbit5 = "RMT-Verfahren ist ein";
            }

            result2 = result2 >>> 1;//shift sixth time

            if((result2 & 1) == 1 ){
                KennlinienTypbit6 = "HC-MAG ist ein";
            }

            result2 = result2 >>> 1;//shift seventh time

            if((result2 & 1) == 1 ){
                KennlinienTypbit7 = "Kennlinie sichtbar";
            }

            JobKommando = (char)bb[11];//pos 6

            int iJS = (char)bb[12];//pos 5
            int result3 = iJS;

            if((result3 & 1) == 1 ){
                JobStatusbit0 = "Jobschweißen aktiv";
            }

            result3 = result3 >>> 1;//shift first time

            if((result3 & 1) == 1 ){
                JobStatusbit1 = "Job-Edit";
            }

            result3 = result3 >>> 1;//shift second time

            if((result3 & 1) == 0 ){
                JobStatusbit2 = "Job-Nr. Frei";
            }else{
                JobStatusbit2 = "Job-Nr. belegt";
            }

            result3 = result3 >>> 1;//shift third time

            if((result3 & 1) == 1 ){
                JobStatusbit3 = "Keine Jobs im Speicher";
            }

            result3 = result3 >>> 1;//shift fourth time

            if((result3 & 1) == 1 ){
                JobStatusbit4 = "Job gespeichert";
            }

            result3 = result3 >>> 1;//shift fifth time

            if((result3 & 1) == 1 ){
                JobStatusbit5 = "Display";
            }

            result3 = result3 >>> 1;//shift sixth time

            if((result3 & 1) == 1 ){
                JobStatusbit6 = "Job Extern";
            }

            Verriegelungsstufe = (char)bb[13];//pos 8

        }else if(gethex.equals("0183")){

            Gasvorströmen = (char)bb[6];//pos 1
            Gasnachströmen = (char)bb[7];//pos 2
            EinschleichenAbsolut = (char)bb[8];//pos 3
            EinschleichenKorrektur = (char)bb[9];//pos 4
            UpSlope = (char)bb[10];//pos 5
            DownSlope = (char)bb[11];//pos 6
            Zündenergie = (char)bb[12];//pos 7
            Endkraterenergie = (char)bb[13];//pos 8

        }else if(gethex.equals("0184")){

            GebirgeStatus = (char)bb[6];//pos 1
            SchweißState = (char)bb[7];//pos 2
            Freibrand = (char)bb[8];//pos 3
            FreibandKorrektur = (char)bb[9];//pos 4
            KorrekturPulsamplitude = (char)bb[10];//pos 5
            KorrekturDrossel = (char)bb[11];//pos 6
            Einfädeln = (char)bb[12];//pos 7
            GastestZeit = (char)bb[13];//pos 8

        }else if(gethex.equals("0185")){

            PausenZeit = (int)bb[6];//pos 1 or 2
            Punktzeit = (int)bb[8];//pos 3 or 4
            ZündDauer = (char)bb[10];//pos 5
            EndkraterDauer = (char)bb[11];//pos 6
            SynergieVorgabe = (char)bb[12];//pos 7
            AnzahlLeistungsmodule = (char)bb[13];//pos 8

        }else if(gethex.equals("0281")){

            PowerpulsEinAus = (char)bb[6];//pos 1
            if(PowerpulsEinAus == '0'){
                strPowerpulsEinAus = "Aus";
            }else if(PowerpulsEinAus == '1'){
                strPowerpulsEinAus = "Aktiv";
            }
            PowerpulsE2 = (char)bb[7];//pos 2
            PowerpulsT1E1 = (char)bb[8];//pos 3
            PowerpulsT2E1 = (char)bb[9];//pos 4
            PowerpulsLBKorrE2 = (char)bb[10];//pos 5
            PowerpulsUpSlope = (char)bb[11];//pos 6
            PowerpulsDownSlope = (char)bb[12];//pos 7
            JobSlope = (char)bb[13];//pos 8

        }else if(gethex.equals("0282")){

            ZündStrom = (int)bb[6];//pos 1 or 2
            ZündSpannung = (int)bb[8];//pos 3 or 4
            ZündEnergie = (int)bb[10];//pos 5 or 6
            ZündDrossel = (char)bb[12];//pos 7
            ZündLichtbogenkorrektur = (char)bb[13];//pos 8

        }else if(gethex.equals("0283")){

            Strom1 = (int)bb[6];//pos 1 or 2
            Spannung1 = (int)bb[8];//pos 3 or 4
            Energie1 = (int)bb[10];//pos 5 or 6
            Drossel1 = (char)bb[12];//pos 7
            Lichtbogenkorrektur1 = (int)bb[13];//pos 8

        }else if(gethex.equals("0201")){

            Stromtest = ((int)(bb[6]))&0xFF;
            //Stromtest = 59;

        } else if(gethex.equals("0284")){

            Strom2 = (int)bb[6];//pos 1 or 2
            Spannung2 = (int)bb[8];//pos 3 or 4
            Energie2 = (int)bb[10];//pos 5 or 6
            Drossel2 = (char)bb[12];//pos 7
            Lichtbogenkorrektur2 = (char)bb[13];//pos 8

        }else if(gethex.equals("0285")){

            Strom3 = (int)bb[6];//pos 1 or 2
            Spannung3 = (int)bb[8];//pos 3 or 4
            Energie3 = (int)bb[10];//pos 5 or 6
            Drossel3 = (char)bb[12];//pos 7
            Lichtbogenkorrektur3 = (char)bb[13];//pos 8

        }else if(gethex.equals("0286")){

            EndkraterStrom = (int)bb[6];//pos 1 or 2
            EndkraterSpannung = (int)bb[8];//pos 3 or 4
            EndkraterEnergie = (int)bb[10];//pos 5 or 6
            EndkraterDrossel = (char)bb[12];//pos 7
            EndKraterLichtbogenkorrektur = (char)bb[13];//pos 8

        }else if(gethex.equals("0481")){

            VorschubSetwert = (int)bb[6];//pos 1 or 2
            VorschubIstwert = (int)bb[8];//pos 3 or 4
            VorschubHoldwert = (int)bb[10];//pos 5 or 6
            VorschubStatus = (char)bb[12];//pos 7
            VorschubAusKennlinie = (char)bb[13];//pos 8

        }else if(gethex.equals("0482")){

            StromSetwert =(int)bb[6]+(int)bb[7]*256;;//pos 1 or 2
            //System.out.println("strom wert = " +(int)bb[6]+(int)bb[7]);
            StromIstwert = (int)bb[8];//pos 3 or 4
            StromHoldwert = (int)bb[10];//pos 5 or 6
            StromStatus = (char)bb[12];//pos 7
            StromInkremental = (char)bb[13];//pos 8

        }else if(gethex.equals("0483")){

            SpannungSetwert = (int)bb[6];//pos 1 or 2
            SpannungIstwert = (int)bb[8];//pos 3 or 4
            SpannungHoldwert = (int)bb[10];//pos 5 or 6
            SpannungStatus = (char)bb[12];//pos 7
            SpannungInkremental = (char)bb[13];//pos 8

        }else if(gethex.equals("0484")){

            BlechdickeSetwert = (int)bb[6];//pos 1 or 2
            BlechdickeIstwert = (int)bb[8];//pos 3 or 4
            BlechdickeHoldwert = (int)bb[10];//pos 5 or 6
            BlechdickeStatus = (char)bb[12];//pos 7

            Reset = (char)bb[13];//pos 8
            int result4 = (int)Reset;
            if((result4 & 1) == 1){
                Resetbit0 = "Error-Reset";
            }

        }else if(gethex.equals("0186")){

            ElektrodeStromSetwert = (int)bb[6];//pos 1 or 2
            ElektrodeStromIstwert = (int)bb[8];//pos 3 or 4
            HotstartDauer = (char)bb[10];//pos 5
            Hotstart = (char)bb[11];//pos 6
            ArcForce = (char)bb[12];//pos 7
            InnenwiderstandfürElektrode = (char)bb[13];//pos 8

        }else if(gethex.equals("0385")){

            RMTPosAmplitude = (int)bb[6];//pos 1 or 2
            RMTNegAmplitude = (int)bb[8];//pos 3 or 4
            StartAmplitude = (int)bb[10];//pos 5 or 6
            StartZeit = (char)bb[12];//pos 7
            StartÜberhöhung = (char)bb[13];//pos 8

        }else if(gethex.equals("0386")){

            InnenwiderstandfürDossel = (char)bb[6];//pos 1
            Überblendzeit = (char)bb[7];//pos 2
            DrosselAbfall = (char)bb[8];//pos 3
            MotorFlanke = (char)bb[9];//pos 4
            DrosselDynamic = (char)bb[10];//pos 5
            MAGACPositiveZeit = (char)bb[11];//pos 6
            MAGACStromschwellwert = (char)bb[12];//pos 7
            LBRMode = (char)bb[13];//pos 8

        }else if(gethex.equals("0485")){

            MAGACBetriebsart = (char)bb[6];//pos 1
            if(MAGACBetriebsart == '0'){
                strMAGACBetriebsart = "DC+(entspricht AUS)";
            }else if(MAGACBetriebsart == '1'){
                strMAGACBetriebsart = "DC-";
            }else if(MAGACBetriebsart == '2'){
                strMAGACBetriebsart = "AC";
            }

            MAGACKältewert = (char)bb[7];//pos 2
            MAGACNegativZeit = (char)bb[8];//pos 3 or 4
            MAGACKurzschlusserkennung = (char)bb[10];//pos 5
            MAGACKurzschlussaufhebung = (char)bb[11];//pos 6
            MACAGVerweilzeitPosNeg = (char)bb[12];//pos 7
            MACAGVerweilzeitNegPos = (char)bb[13];//pos 8

        }else if(gethex.equals("0301")){

            WIGSpeedPulsFrequenz = (int)bb[6];//pos 1 or 2
            WIGSpeedPulsI1Anteil = (char)bb[8];//pos 3
            WIGSpeedPulsI3 = (char)bb[9];//pos 4
            GasSollwert = (char)bb[10];//pos 5
            UserNummer = (char)bb[11];//pos 6
            WIGACStromoffset = (char)bb[12];//pos 7

            WIGStatus = (char)bb[13];//pos 8
            int iWIGStatus = (int)WIGStatus;
            int result5 = iWIGStatus;

            if((result5 & 1) == 1 ){
                WIGStatusbit0 = "WIG-Brennertaste 2 ein";
            }

            result5 = result5 >>> 1;//shift first time

            if((result5 & 1) == 1 ){
                WIGStatusbit1 = "Puls I2";
            }

            result5 = result5 >>> 1;//shift second time

            if((result5 & 1) == 1 ){
                WIGStatusbit2 = "Fußpedal vorhanden";
            }

            result5 = result5 >>> 1;//shift third time

            if((result5 & 1) == 1 ){
                WIGStatusbit3 = "Automatisierung";
            }

            result5 = result5 >>> 1;//shift fourth time

            if((result5 & 1) == 1 ){
                WIGStatusbit4 = "Wechselrichter vorhanden";
            }

            result5 = result5 >>> 1;//shift fifth time

            if((result5 & 1) == 1 ){
                WIGStatusbit5 = "I2 ist in % von I1";
            }

            result5 = result5 >>> 1;//shift sixth time

            if((result5 & 1) == 1 ){
                WIGStatusbit6 = "Reserve";
            }

            result5 = result5 >>> 1;//shift seventh time

            if((result5 & 1) == 1 ){
                WIGStatusbit7 = "Reserve";
            }

        }else if(gethex.equals("0302")){

            WIGACFrequenz = (int)bb[6];//pos 1 or 2
            WIGACBalance = (char)bb[8];//pos 3
            WIGDurchmesserWolframElektrode = (char)bb[9];//pos 4

            WIGBetriebsartWechselrichter = (char)bb[10];//pos 5
            if(WIGBetriebsartWechselrichter == '0'){
                strWIGBetriebsartWechselrichter = "NONE";
            }else if(WIGBetriebsartWechselrichter == '1'){
                strWIGBetriebsartWechselrichter = "DC-Minus";
            }else if(WIGBetriebsartWechselrichter == '2'){
                strWIGBetriebsartWechselrichter = "DC-Plus";
            }else if(WIGBetriebsartWechselrichter == '3'){
                strWIGBetriebsartWechselrichter = "AC-Sinus";
            }else if(WIGBetriebsartWechselrichter == '4'){
                strWIGBetriebsartWechselrichter = "AC-Rechteck";
            }else if(WIGBetriebsartWechselrichter == '5'){
                strWIGBetriebsartWechselrichter = "AC-MIX";
            }

            KaltdrahtpulsenT1SV21_5 = (char)bb[11];//pos 6
            WIGStromLimit = (int)bb[12];//pos 7 or 8

        }else if(gethex.equals("0303")){

            KHMode = (char)bb[6];//pos 1
            int iKHMode = (int)KHMode;
            int result6 = iKHMode;

            if((result6 & 1) == 1 ){
                strKHModebit0 = "Kaltdraht Freigabe";
            }

            result6 = result6 >>> 1;//shift first time

            if((result6 & 1) == 1 ){
                strKHModebit1 = "Kaltdraht Start Roboter";
            }

            result6 = result6 >>> 1;//shift second time

            if((result6 & 1) == 0 ){
                strKHModebit2 = "Kaltdraht Stop wenn Min. Unterschr";
            }

            result6 = result6 >>> 1;//shift third time

            if((result6 & 1) == 1 ){
                strKHModebit3 = "Kaltdraht Pulsen an Strom gekoppelt";
            }

            result6 = result6 >>> 1;//shift fourth time

            if((result6 & 1) == 1 ){
                strKHModebit4 = "Heißdraht Freigabe";
            }

            result6 = result6 >>> 1;//shift fifth time

            if((result6 & 1) == 1 ){
                strKHModebit5 = "Heißdraht Start Roboter";
            }

            result6 = result6 >>> 1;//shift sixth time

            if((result6 & 1) == 1 ){
                strKHModebit6 = "Kaltdraht Pulsen Freigabe";
            }

            result6 = result6 >>> 1;//shift seventh time

            if((result6 & 1) == 1 ){
                strKHModebit7 = "Kaltdraht V2 Absolut in m/min";
            }

            VerzögerungsZeitKaltdrahtEin = (char)bb[7];
            VerzögerungsZeitKaltdrahtAus = (char)bb[8];
            VerzögerungsZeitHeißdrahtÜberwachung = (char)bb[9];
            Vorpositionierungsstrecke = (char)bb[10];
            Rückzugsstrecke = (char)bb[11];
            KaltdrahtpulsenT1SV22_7 = (char)bb[12];

            KHStatus = (char)bb[13];//pos 8
            int iKHStatus = (int)KHStatus;
            int result7 = iKHStatus;
            if((result7 & 1) == 1 ){
                strKHStatusbit0 = "Kaltdraht ok(DVC im System";
            }

            result7 = result7 >>> 1;//shift first time

            if((result7 & 1) == 1 ){
                strKHStatusbit1 = "Kalt-Draht Ein";
            }

            result7 = result7 >>> 1;//shift second time

            if((result7 & 1) == 0 ){
                strKHStatusbit2 = "Kalt-Draht fördert";
            }

            result7 = result7 >>> 1;//shift third time

            result7 = result7 >>> 1;//shift fourth time

            if((result7 & 1) == 1 ){
                strKHStatusbit4 = "Heißdraht ok(MSRC-HW DVC im System)";
            }

            result7 = result7 >>> 1;//shift fifth time

            if((result7 & 1) == 1 ){
                strKHStatusbit5 = "Heiß-Draht Ein";
            }

            result7 = result7 >>> 1;//shift sixth time

            if((result7 & 1) == 1 ){
                strKHStatusbit6 = "Heiß-Draht Strom fließt";
            }

            result7 = result7 >>> 1;//shift seventh time

            if((result7 & 1) == 1 ){
                strKHStatusbit7 = "Heiß-Draht-Strom ok";
            }

        }

    }

/*    public  void FromSaha(String var, int value){ // call in Main Activity

        if(var.equals("Verfahren")){

            canid = "0181";
            pos = 0;
            //usbObject.changeParam(canid, value, pos);

        }else if(var.equals("Betriebsart")){

            canid = "0181";
            pos = 2;
            //usbObject.changeParam(canid, value, pos);

        }else if(var.equals("Kennlinie")){

            canid = "0182";
            pos = 5;
            //usbObject.changeParam(canid, value, pos);

        }
    }*/

    public static void buffParsing(String Receiverdata) {

        str1 = Receiverdata.getBytes(iso88591charset);//convert String to byte array

        for (int c = 0; c < Receiverdata.length(); c++) {
            byte ccb = str1[c];//get char from string
            dataCombiner1(ccb);
        }
    }

    public static void dataCombiner1(byte DataRec){

        byte Inn = DataRec;

        if(HeaderFound1==0){
            int ByteCompareee=Byte.compare(DataRec,(byte)36);
            if(ByteCompareee==0){
                HeaderFound1=1;
                bb[CounterData1]=Inn;
                CounterData1++;
            }
        }else if(LengthFound1==0){
            LengthFound1=1;
            byte abcd = Inn;
            LengthProtocol1=(int)abcd;
            bb[CounterData1]=Inn;
            CounterData1++;

        }else if(CounterData1<LengthProtocol1 - 1){ //counter data starts to count from 2 to 17
            bb[CounterData1]=Inn;
            CounterData1++;
        }
        if(CounterData1==LengthProtocol1 - 1){
            LengthProtocol2=LengthProtocol1;
            bb[CounterData1]=Inn; //bb[18]=Inn;

            int ByteCompare2=Byte.compare(bb[CounterData1],(byte)35); //int ByteCompare2=Byte.compare(bb[18],(byte)35);
            if(ByteCompare2==0){ //received footer

                HeaderFound1=0;
                CounterData1=0;
                LengthFound1=0;
                LengthProtocol1=0;
            }
        }
    }
}