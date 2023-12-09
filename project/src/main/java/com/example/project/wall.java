package com.example.project;

public class wall{

    int wall_length;
    int wall_colour;
    public wall(int wall_length, int wall_colour) {
        this.wall_length = wall_length;
        this.wall_colour = wall_colour;
    }

    public void increase_wall(){
        wall_length=wall_length+1;
    }
    public void decrease_wall(){
        wall_length=wall_length-1;
    }


}


