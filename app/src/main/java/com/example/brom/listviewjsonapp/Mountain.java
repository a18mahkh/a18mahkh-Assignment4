package com.example.brom.listviewjsonapp;

import android.graphics.Bitmap;

public class Mountain {
    private String name;
    private String location;
    private  int height;
    //private String imgUrl;
   // private Bitmap image;
    //private MountainAdapter ima;

    public Mountain(String inName, String inLocation, int inHeight){
        name=inName;
        location=inLocation;
        height=inHeight;
        //this.imgUrl=imgUrl;
        //to be load later
        //this.image=null;

    }
    //member methods
    public  String toString(){
        return name;

    }

   /* public String getImgUrl(){
        return imgUrl;
    }
    public String setImgUrl(String imgUrl){
        return imgUrl;
    }
    public Bitmap getImage(){
        return image;
    }*/

    public String info(){
        String str=name +" is located in " + location + " and has an height of " + height + "m.";

        return str;
    }

}