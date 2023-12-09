package com.example.project;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("scene2.fxml"));
        Scene scene1 = new Scene(fxmlLoader.load(), 800, 600);
        stage.setScene(scene1);
        stage.setResizable(false);
        stage.show();
    }
    //public void start()

    public static void main(String[] args) {
        launch(args);
    }
}