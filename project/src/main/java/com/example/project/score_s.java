package com.example.project;
//singleton

public class score_s {
    static score_s s;

    private static int high =0 ;

    public int getHigh() {
        return high;
    }

    public void setHigh(int high) {
        this.high = high;
    }

    private score_s(){
    };

    static public score_s get_instance(){
        if(s==null ) s = new score_s();
        return s;
    }


}
