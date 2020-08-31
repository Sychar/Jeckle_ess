
/**
 * *@Autor Sakhr Aljendi
 * @Version 1.0
 * 13.01.2020
 * */
package com.felhr.serialportexample.View;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.PopupMenu;

import com.felhr.serialportexample.DatenBank.CSVWriter;
import com.felhr.serialportexample.DatenBank.InfoContract;
import com.felhr.serialportexample.DatenBank.InfoDataBase;
import com.felhr.serialportexample.IO.Exit;
import com.felhr.serialportexample.R;

import java.io.File;
import java.io.FileWriter;

/** Klass D_Base
 * hier ist einfach ist die HauptMenu für Daten Bank hier kann man auf Job und logger Daten zugreifen
 */
public class D_Base extends AppCompatActivity implements Exit {
private InfoDataBase datenReader;
private  Button btn;
private  Uri uri;
private  String [] JobsArray  = new String[113];
    private  String [] FinalArray  = new String[112];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_d__base);

        datenReader=new InfoDataBase(this);
        btn =(Button)findViewById(R.id.export);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OnClick_Eport_Data();
            }
        });

    }
    /**
     * @methode
     * für neu Activity (Logger Daten) starten
     * @param view
     */
    public void onClick(View view) {
        Intent  intent = new Intent(this, DataLoger.class);
        startActivity(intent);
    }
    /**
     * @methode
     * für neu Activity ( Jobs)starten
     * @param view
     */
    public void onClickJobs(View view) {
        Intent  intent = new Intent(this, JobView.class);
        startActivity(intent);
    }
    /**
     * Exit methode
     * @param view
     */
    public  void close(View view){
        getContentResolver().delete(InfoContract.infoEntry.CONTENT_URI,null,null);

        D_Base.this.finish();
    }




    public void OnClick_Eport_Data(){
        PopupMenu popupMenu = new PopupMenu(D_Base.this,btn);
        popupMenu.getMenuInflater().inflate(R.menu.export_databank, popupMenu.getMenu());
        popupMenu.show();
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                if(menuItem.getItemId()==R.id.datalogger){
                    
                }
                if (menuItem.getItemId()==R.id.Jobs){
                     exportDB();
                }
                return false;
            }
        });
    }


    private  void exportDB(){
       InfoDataBase infoDataBase = new InfoDataBase(getApplicationContext());
        File exportDir = new File (Environment.getExternalStorageDirectory(),"");
        if (!exportDir.exists()){
            exportDir.mkdir();
        }
        File file =new File(exportDir,"JOBS.csv");
        try {
            file.createNewFile();
            CSVWriter csvWriter = new CSVWriter(new FileWriter(file));
            uri = Uri.parse("content://com.felhr.serialportexample.jobs/jobs/");
            Cursor cursor = getContentResolver().query(uri, JobsDetails.jobdetails, null, null, null);
            while (cursor.moveToNext()){
                for (int i = 0;i<112;i++){
                    JobsArray[i]=cursor.getString(cursor.getColumnIndexOrThrow(JobsDetails.jobdetails[i]));
                    FinalArray[i]=JobsDetails.jobdetails[i] + ":"+ JobsArray[i] ;
                    System.out.println(JobsArray[i]);

                }
                JobsArray[112]="-----------------------------------------------------------------------";

                csvWriter.writeNext(FinalArray);


            }
            csvWriter.close();
        }catch (Exception E){
            E.printStackTrace();
        }
    }
        }



