package com.felhr.serialportexample.View;

import org.xml.sax.DTDHandler;

import java.util.ArrayList;
import java.util.List;

public class Details {
    private String titel;
    private int image;



    public Details(String titel, int image){
        this.image=image;

        this.titel=titel;

    }

    public int getImage() {
        return image;
    }

    public String getTitel() {
        return titel;
    }
}
