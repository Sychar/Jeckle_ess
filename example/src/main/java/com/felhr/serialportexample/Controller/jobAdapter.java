package com.felhr.serialportexample.Controller;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.felhr.serialportexample.DatenBank.Jobs;
import com.felhr.serialportexample.R;

import java.util.ArrayList;


public class jobAdapter extends ArrayAdapter<Jobs> {
    private View listitem;
    private static Jobs jobs;
    private TextView id;
    private TextView jobname;
    private TextView time ;
    public  jobAdapter (Activity context , ArrayList<Jobs> jobs){
        super(context,0,jobs);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        listitem =convertView;
        if (listitem == null) {
            listitem = LayoutInflater.from(getContext()).inflate(R.layout.item_for_job, parent, false);
        }
        id=listitem.findViewById(R.id.job_nr);
        jobname=listitem.findViewById(R.id.job_name);
        time=listitem.findViewById(R.id.job_time);

        jobs = getItem(position);

        id.setText(jobs.getNum());
        jobname.setText(jobs.getName());
        time.setText(jobs.getTime());
        return listitem;
    }
}
