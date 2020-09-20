import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.InputStream;
import java.util.ArrayList;

import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.util.Duration;


public class Dino {
    private static int dinoTopY, dinoStartX;
    public int gravity = 0;
    public Image leftFootDino;
    public Image rightFootDino;
    public Image deadDino;
    public ImageView imageView_dino;
    public AnimationTimer jumpTimer;

    public Dino() {
        leftFootDino =  new Image("/images/Dino-left-up.png");
        rightFootDino =  new Image("/images/Dino-right-up.png");
        deadDino =  new Image("/images/Dino-big-eyes.png");
        dinoTopY = Ground.GROUND_Y - (int)UserInterface.image_dino.getHeight() + 8;
        dinoStartX = 75;
        imageView_dino = new ImageView();
        imageView_dino.setImage(UserInterface.image_dino);
        imageView_dino.setX(dinoStartX);
        imageView_dino.setY(dinoTopY);
        UserInterface.root.getChildren().addAll(imageView_dino);
        Menu.timeline_run = run();
        UserInterface.root.addEventFilter(KeyEvent.KEY_PRESSED, event->{
            if (event.getCode() == KeyCode.SPACE && gravity == 0 && !Menu.pause_click) {
                Menu.timeline_run.pause();
                imageView_dino.setImage(UserInterface.image_dino);
                double ypreviousPos = imageView_dino.getTranslateY();
                jumpTimer = new AnimationTimer(){
                    @Override
                    public void handle(long now) {
                        gravity += 1;
                        imageView_dino.setTranslateY(imageView_dino.getTranslateY() - 16 + gravity);
                        if (ypreviousPos == imageView_dino.getTranslateY()) {
                            jumpTimer.stop();
                            Menu.timeline_run.play();
                            gravity = 0;
                        }
                    }
                };
                jumpTimer.start();
            }
        });
    }
    private Timeline run() {
        Timeline run = new Timeline(new KeyFrame(Duration.seconds(0.1),
                new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent t) {
                        imageView_dino.setImage(UserInterface.image_dino);
                    }
                }), new KeyFrame(Duration.seconds(0.1),
                new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent t) {
                        imageView_dino.setImage(leftFootDino);
                    }
                }), new KeyFrame(Duration.seconds(0.2),
                new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent t) {
                        imageView_dino.setImage(rightFootDino);
                    }
                }));
        run.setCycleCount(Timeline.INDEFINITE);
        return run;
    }
}