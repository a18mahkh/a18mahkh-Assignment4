package com.example.brom.listviewjsonapp;

/*class Mountain {
    public Mountain(String k2) {
    }
}
*/
public class Mountain {
    private String name;
    private String location;
    private  int height;

    public Mountain(String inName, String inLocation, int inHeight){
        name=inName;
        location=inLocation;
        height=inHeight;

    }
    public  Mountain(String inName){
        name=inName;
        location="";
        height=-1;

    }

    //member methods
    public  String toString(){
        return name;

    }

    public String info(){
        String str=name;
        str+=" is located in ";
        str=location;
        str+=" and has an height of ";
        str=Integer.toString(height);
        str+=" m.";
        return str;
    }

    public void setHeight(int newHeigth){
        height=newHeigth;
    }

}