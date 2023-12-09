package com.example.project;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;

public class media_sound {
    MediaPlayer mediaPlayer;
    public void playHITSOUND(String s_file){
//        String path=getClass().getResource(s_file).getPath();
        Media media= new Media(new File(s_file).toURI().toString());
        mediaPlayer= new MediaPlayer(media);
        mediaPlayer.play();

    }
    public void stopHITSOUND(String s_file){
//        String path=getClass().getResource(s_file).getPath();
        Media media= new Media(new File(s_file).toURI().toString());
        mediaPlayer= new MediaPlayer(media);
        mediaPlayer.stop();

    }
    String s_file="win sound";

}
