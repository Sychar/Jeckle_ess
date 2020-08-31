package com.felhr.serialportexample.Controller;

public class DatenObjekteSend {

    private byte VALTOKEN;//1(yes)
    private byte PARAMTOKEN;//1(yes)
    private byte FRAMEVAL;
    private byte FRAMEEXTRA;
    private int FIRVAL;
    private int SECVAL;
    private int THDVAL;

    public void ChangeParameter(int num,int value){

        switch(num){
            case 1: //Strom
                FRAMEVAL = 0;
                FRAMEEXTRA = 34;//22H
                FIRVAL = value&0xFF;//lsb
                SECVAL = (value>>8)&0xFF;//msb
                THDVAL = 51; //33H
                PARAMTOKEN = 1;
                VALTOKEN= 1;
                break;
            case 2: //Energie (m/min)
                FRAMEVAL = 1;
                FRAMEEXTRA = 34;
                FIRVAL = value&0xFF;//lsb
                SECVAL = 0;//msb
                THDVAL = 51; //33H
                PARAMTOKEN = 1;
                VALTOKEN= 1;
                break;
            case 3: //mm
                //Log.i("Change","mm");
                FRAMEVAL = 2;
                FRAMEEXTRA = 34;
                FIRVAL = value&0xFF;//lsb
                SECVAL = 0;//msb
                THDVAL = 51; //33H
                PARAMTOKEN = 1;
                VALTOKEN= 1;
                break;
            case 4: //job(no value)
                //Log.i("Change","Job");
                FRAMEVAL = 1;
                FRAMEEXTRA = 11;
                FIRVAL = 0;
                SECVAL = 0;
                THDVAL = 0;
                PARAMTOKEN = 1;
                VALTOKEN= 0; // no value id
                break;
            case 5: //job activate
                FRAMEVAL = 5;
                FRAMEEXTRA = 11;
                FIRVAL = 0;
                SECVAL = 0;
                THDVAL = 0;
                PARAMTOKEN = 1;
                VALTOKEN= 0; // no value id
                break;
            case 6: //job deactivate
                FRAMEVAL = 3;
                FRAMEEXTRA = 11;
                FIRVAL = 0;
                SECVAL = 0;
                THDVAL = 0;
                PARAMTOKEN = 1;
                VALTOKEN= 0; // no value id
                break;
            case 7: //spannung
                FRAMEVAL = 4;
                FRAMEEXTRA = 0;
                FIRVAL = value&0xFF;//lsb
                SECVAL = (value>>8)&0xFF;//msb
                THDVAL = 50;//32H
                PARAMTOKEN = 1;
                VALTOKEN= 1;
                break;
            case 8: //Drossel
                FRAMEVAL = 4;
                FRAMEEXTRA = 0;
                FIRVAL = value&0xFF;//lsb
                SECVAL = 0;//msb
                THDVAL = 52;//34H
                PARAMTOKEN = 1;
                VALTOKEN= 1;
                break;
            case 9://Lichtbogenkorr(bei 2 Takt)
                FRAMEVAL = 2;
                FRAMEEXTRA = 0;
                FIRVAL = value&0xFF;//lsb
                SECVAL = (value>>8)&0xFF;//msb
                THDVAL = 53;//35H
                PARAMTOKEN = 1;
                VALTOKEN= 1;
                break;
            case 10://CrNi(bei synergie)
                FRAMEVAL = 0;
                FRAMEEXTRA = 0;
                FIRVAL = 1;//lsb
                SECVAL = 0;//msb
                THDVAL = 4;//04H
                PARAMTOKEN = 0;//no param id
                VALTOKEN= 1;
                break;
            case 11://AlMg(bei synergie)
                FRAMEVAL = 0;
                FRAMEEXTRA = 0;
                FIRVAL = 2;//lsb
                SECVAL = 0;//msb
                THDVAL = 4;
                PARAMTOKEN = 0;//no param id
                VALTOKEN= 1;
                break;
            case 12://AlSi(bei synergie)
                FRAMEVAL = 0;
                FRAMEEXTRA = 0;
                FIRVAL = 3;//lsb
                SECVAL = 0;//msb
                THDVAL = 4;
                PARAMTOKEN = 0;//no param id
                VALTOKEN= 1;
                break;
            case 13://Spz(bei synergie)
                FRAMEVAL = 0;
                FRAMEEXTRA = 0;
                FIRVAL = 4;//lsb
                SECVAL = 0;//msb
                THDVAL = 4;
                PARAMTOKEN = 0;//no param id
                VALTOKEN= 1;
                break;
            case 14://Fe(bei synergie)
                FRAMEVAL = 0;
                FRAMEEXTRA = 0;
                FIRVAL = 10;//lsb
                SECVAL = 0;//msb
                THDVAL = 4;
                PARAMTOKEN = 0;//no param id
                VALTOKEN= 1;
                break;
            case 15: //voltage
                FRAMEVAL = 0;
                FRAMEEXTRA = 0;
                FIRVAL = value&0xFF;//lsb
                SECVAL = 0;//msb
                THDVAL = 52;//34H
                PARAMTOKEN = 0;
                VALTOKEN= 1;
                break;
            case 16: //A
                FRAMEVAL = 0;
                FRAMEEXTRA = 0;
                FIRVAL = value&0xFF;//lsb
                SECVAL = 0;//msb
                THDVAL = 34;//22H
                PARAMTOKEN = 0;
                VALTOKEN= 1;
                break;
            case 17: //mm
                FRAMEVAL = 0;
                FRAMEEXTRA = 0;
                FIRVAL = 0;//lsb
                SECVAL = 0;//msb
                THDVAL = 2;//2H
                PARAMTOKEN = 0;
                VALTOKEN= 1;
                break;
            case 18: //m/min
                FRAMEVAL = 0;
                FRAMEEXTRA = 0;
                FIRVAL = 0;//lsb
                SECVAL = 0;//msb
                THDVAL = 1;//2H
                PARAMTOKEN = 0;
                VALTOKEN= 1;
                break;
            case 19: // %
                FRAMEVAL = 0;
                FRAMEEXTRA = 0;
                FIRVAL = value&0xFF;//lsb
                SECVAL = 0;//msb
                THDVAL = 50;//32H
                PARAMTOKEN = 0;
                VALTOKEN= 1;
                break;
            case 20: // job change
                FRAMEVAL = 11;
                FRAMEEXTRA =11;
                FIRVAL = 0;//lsb
                SECVAL = 0;//msb
                THDVAL = 0;//bH
                PARAMTOKEN = 1;
                VALTOKEN= 0;
                break;

            case 21: //Lichtbogenkorrektur(bei synergie)
                FRAMEVAL = 0;
                FRAMEEXTRA = 0;
                FIRVAL = value&0xFF;//lsb
                SECVAL = (value>>8)&0xFF;//msb
                THDVAL = 53;//35H
                PARAMTOKEN = 0;
                VALTOKEN = 1;
                break;
            case 22: //energie 1(bei synergie)
                FRAMEVAL = 1;
                FRAMEEXTRA = 0;
                FIRVAL = value&0xFF;//lsb
                SECVAL = (value>>8)&0xFF;//msb
                THDVAL = 27;//1BH
                PARAMTOKEN = 1;
                VALTOKEN = 1;
                break;

            case 23: //Draht(bei synergie)
                FRAMEVAL = 0;
                FRAMEEXTRA = 0;
                FIRVAL = value&0xFF;
                SECVAL = 0;
                THDVAL = 2;
                PARAMTOKEN = 0;
                VALTOKEN = 1;
                break;
            case 24: //Elekto(bei 2.Pic)
                FRAMEVAL = 0;
                FRAMEEXTRA = 0;
                FIRVAL = value&0xFF;
                SECVAL = 0;
                THDVAL = 89;//59H
                PARAMTOKEN = 0;
                VALTOKEN = 1;
                break;
            case 25: //Synergie
                FRAMEVAL = 1;
                FRAMEEXTRA = 0;
                FIRVAL = 0;
                SECVAL = 0;
                THDVAL = 0;
                PARAMTOKEN = 1;
                VALTOKEN = 0;//no value id
                break;
            case 26: //2 Takt
                FRAMEVAL = 2;
                FRAMEEXTRA = 0;
                FIRVAL = 0;
                SECVAL = 0;
                THDVAL = 0;
                PARAMTOKEN = 1;
                VALTOKEN = 0;//no value id
                break;
            case 27: //2. Pic
                FRAMEVAL = 3;
                FRAMEEXTRA = 0;
                FIRVAL = 0;
                SECVAL = 0;
                THDVAL = 0;
                PARAMTOKEN = 1;
                VALTOKEN = 0;//no value id
                break;
            case 28: //Norm
                FRAMEVAL = 4;
                FRAMEEXTRA = 0;
                FIRVAL = 0;
                SECVAL = 0;
                THDVAL = 0;
                PARAMTOKEN = 1;
                VALTOKEN = 0;//no value id
                break;
            case 29: //volt(bei norm)
                FRAMEVAL = 0;
                FRAMEEXTRA = 0;
                FIRVAL = value&0xFF;//lsb
                SECVAL = (value>>8)&0xFF;//msb
                THDVAL = 50;//32H
                PARAMTOKEN = 0;
                VALTOKEN = 1;
                break;
            case 30: //Verfahren(bei norm)
                FRAMEVAL = 0;
                FRAMEEXTRA = 0;
                FIRVAL = value&0xFF;//01/02/03/05
                SECVAL = 0;
                THDVAL = 1;
                PARAMTOKEN = 0;
                VALTOKEN = 1;
                break;
            case 31: //Energie2
                FRAMEVAL = 48;//30H
                FRAMEEXTRA = 0;
                FIRVAL = 30;//1EH
                SECVAL = 0;
                THDVAL = 0;
                PARAMTOKEN = 1;
                VALTOKEN = 1;
                break;
            case 32: //Lichtbogenkorr (bei 2 takt blinking)
                FRAMEVAL = 0;
                FRAMEEXTRA = 0;
                FIRVAL = value&0xFF;
                SECVAL = 0;
                THDVAL = 26;//1AH
                PARAMTOKEN = 0;
                VALTOKEN = 1;
                break;
            case 33: //Lichtbogenkorr2 (bei 2 takt)
                FRAMEVAL = 0;
                FRAMEEXTRA = 0;
                FIRVAL = value&0xFF;//lsb
                SECVAL = (value>>>8)&0xFF;//msb
                THDVAL = 53;//35H
                PARAMTOKEN = 0;
                VALTOKEN = 1;
                break;
        }
        UsbService.canSend(VALTOKEN,PARAMTOKEN,FRAMEVAL,FRAMEEXTRA,(byte)FIRVAL,(byte)SECVAL,(byte)THDVAL);
    }
}