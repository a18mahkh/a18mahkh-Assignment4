package com.example.brom.listviewjsonapp;
public class Mountain {
    private String name;
    private String location;
    private  int height;
    //private  String imgUrl;


    public Mountain(String inName, String inLocation, int inHeight){
        name=inName;
        location=inLocation;
        height=inHeight;

    }
    //member methods
    public  String toString(){
        return name;

    }

    public String info(){
        String str=name +" is located in " + location + " and has an height of " + height + "m.";

        return str;
    }

}