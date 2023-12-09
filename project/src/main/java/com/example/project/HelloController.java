package com.example.project;

import javafx.animation.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Bounds;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;


public class HelloController implements Initializable {
    private
    @FXML
    Button play ;

    static score_s s = score_s.get_instance();
    @FXML
    private ImageView cherry;
    @FXML
    private ImageView character;
    @FXML
    Rectangle stick;
    @FXML
    Rectangle one_block ;
    @FXML
    Rectangle second_block ;
    @FXML
    Button restart;
    @FXML
    Text score;
    double sx;
    double sy;
    Random rand=new Random();


    media_sound media_sound=new media_sound();
//    score score= new score();
    public double random_number_generator(int max, int min){
        double randno=(Math.random() *(max - min + 1) + min);
        return randno;
    }

    Timeline timeline;
    Timeline timeline1;
    Timeline timeline_sound;
    Timeline time_winning;
    Timeline cherry_time;
    int score_1=0;
    int cherry_count=0;
//    int high_score= com.example.project.score.highscore;

    public void start_game(MouseEvent play) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));

        Scene scene1 = new Scene(fxmlLoader.load(), 800, 600);
        Stage stage = (Stage)    ((Node) play.getSource()  ).getScene().getWindow();
        stage.setScene(scene1);
        stage.setResizable(false);
        stage.show();
        //OOPS IMPLEMENTATION
        wall a2=new wall(1,2);
        score.setText(String.valueOf(highscore));

    }
    public void char_rotate(Scene scene) throws IOException {
        scene.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.DOWN) {
                System.out.println("The 'DOWN' key was pressed");
                character.setRotate(180); // Rotate the character 180 degrees
                character.setLayoutY(character.getLayoutY() + 30); // Move the character down

            } else if (e.getCode() == KeyCode.UP) {
                System.out.println("The 'UP' key was pressed");
                // Optionally, you can handle UP key behavior here
                // (e.g., rotate character, move character up, etc.)
            }
        });
    }
    public void moveCharacterAlongRope(Scene scene) throws IOException {
        final double ropeHeight = 300; // Adjust this value based on your game's setup
        final double movementStep = 10;

        double characterY = character.getLayoutY();

        scene.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.DOWN) {
                // If the character is above the rope, move down
                if (character.getLayoutY() < stick.getY()) {
                    System.out.println("Moving down");
                    character.setLayoutY(character.getLayoutY() + 30);
                }
            } else if (e.getCode() == KeyCode.UP) {
                // If the character is below the top of the scene, move up
                if (character.getLayoutY() > 0) {
                    System.out.println("Moving up");
                    character.setLayoutY(character.getLayoutY() - 30);
                }
            }
        });
    }



//    public void char_rotate(Scene scene) throws IOException{
//        scene.setOnKeyPressed(e -> {
//            if (e.getCode() == KeyCode.DOWN) {
//                System.out.println("The 'pg down' key was pressed");
//                character.setRotate(180);
//            }
//            else if (e.getCode() == KeyCode.UP) {
//                System.out.println("The 'pg down' key was pressed");
//                character.setRotate(180);
//                character.setLayoutY(character.getLayoutY()-10);
//                character.setRotate(0);
//            }
//        });
//    }
    private int highscore = 0;

    @FXML
    Text highscore_1;


    @FXML
    public void enter(MouseEvent event) {
            timeline.play();
        highscore_1.setText(String.valueOf(s.getHigh()));

    }
    @FXML
    void exit(MouseEvent event) throws IOException {
        timeline.stop();
        exchange_val();

    }
    public void exchange_val() throws IOException {
        sx=stick.getHeight();
        System.out.println(sx);
        stick.setHeight(stick.getWidth());
        stick.setWidth(sx);
        stick.setY(287);
        travel();

    }
    public void travel() throws IOException {
        char_rotate(character.getScene());
        TranslateTransition translate= new TranslateTransition();
        translate.setNode(character);
        translate.setByX(stick.getWidth()+25);
        translate.play();
        System.out.println(stick.getWidth());
        System.out.println(second_block.getWidth());
        System.out.println(second_block.getX());
        if ((stick.getWidth())+80>=second_block.getX() && stick.getWidth()+80<=(second_block.getX()+second_block.getWidth())){
        translate.setOnFinished(actionEvent -> next_level_success());
    }else {
            translate.setOnFinished(actionEvent -> {
                try {
                    no_level_fail();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
        }
    }
    public void no_level_fail() throws IOException {
        TranslateTransition translate3 = new TranslateTransition();
        try {
            translate3.setNode(character);
            translate3.setByY(+500);
            translate3.setCycleCount(1);
            translate3.setDuration(Duration.seconds(2));
            translate3.play();
            timeline_sound.play();
            highscore1();
//            media_sound.playHITSOUND("src/main/resources/sound/lose");
        }
        finally {
            restart.setVisible(true);

        }
    }
    public void highscore1(){
        if(score_1 > highscore){
            highscore = score_1;
            highscore_1.setText(String.valueOf(highscore));
            s.setHigh(highscore);
        }

    }
    public void restart(ActionEvent e) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene1 = new Scene(fxmlLoader.load(), 800, 600);
        Stage stage = (Stage)character.getScene().getWindow();
        stage.setScene(scene1);
        stage.setResizable(false);
        stage.show();




}


    public void next_level_success() {
        double stick_width = stick.getWidth();
        stick.setHeight(0);
        stick.setWidth(0);

        // Store the current positions of the first and second blocks
        double firstBlockX = one_block.getX();
        double secondBlockX = second_block.getX();

        // Set initial position of the second block to be visible on the screen
        cherry.setVisible(true);
        cherry.setX(rand.nextDouble(one_block.getWidth() + 30, second_block.getX() - 70));
        second_block.setX(0);
        TranslateTransition translate_CHAR = new TranslateTransition();
        translate_CHAR.setNode(character);
        translate_CHAR.setByX(-stick_width -20);
        translate_CHAR.setDuration(Duration.seconds(1));

        TranslateTransition translate1 = new TranslateTransition();
        translate1.setNode(second_block);
        translate1.setByX(-(second_block.getX() - firstBlockX)); // Move second block to the position of the first block
        translate1.setDuration(Duration.seconds(1));
        translate1.setCycleCount(1);

        // Set the new position of the first block after the second block has moved
        translate1.setOnFinished(event -> {
            // Exchange characteristics and positions
            double tempX = one_block.getX();
            one_block.setX(random_number_generator(500, 400));
            second_block.setX(tempX);

            // Exchange blocks
            Rectangle tempBlock = one_block;
            one_block = second_block;
            second_block = tempBlock;
            stick.setX(80);
            stick.setHeight(10);
            stick.setWidth(8);
            character.setLayoutX(10);
            character.setLayoutX(tempX+10);
            score_1++;
            score.setText(String.valueOf(score_1));
            time_winning.play();
//            media_sound.playHITSOUND("win sound");
        });

        translate_CHAR.play();
        translate1.play();
    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if(highscore_1 != null) highscore_1.setText(String.valueOf(s.getHigh()));


        timeline = new Timeline(new KeyFrame(Duration.millis(40), e -> {
        stick.setY(stick.getY() - 15);
        stick.setHeight(stick.getHeight()+15);
        }));
        timeline1 =new Timeline(new KeyFrame(Duration.millis(40),e->{

        }));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline_sound= new Timeline(new KeyFrame(Duration.millis(40), e->{
            media_sound.playHITSOUND("C:\\Users\\ASUS\\Desktop\\project\\src\\main\\resources\\sound\\lose.mp3");
        })) ;
        time_winning=new Timeline(new KeyFrame(Duration.millis(1), e->{
            media_sound.playHITSOUND("C:\\Users\\ASUS\\Desktop\\project\\src\\main\\resources\\sound\\win.wav");
        })) ;
        cherry_time=new Timeline(new KeyFrame(Duration.millis(30),e->{
            Bounds cherryBounds = cherry.getBoundsInParent();
            Bounds characterBounds = character.getBoundsInParent();

            // Check if the bounds intersect
            if (cherryBounds.intersects(characterBounds)) {

                // Collision detected
                cherry_count++;
                cherry.setVisible(false);

                System.out.println("Cherry collected! Cherry Count: " + cherry_count);

                // Add any other actions you want to perform when a collision occurs
            }

        }));
    }
}