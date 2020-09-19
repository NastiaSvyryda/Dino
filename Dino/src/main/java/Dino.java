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
    private static int dinoBaseY, dinoTopY, dinoStartX, dinoEndX;
    private static int dinoTop, dinoBottom, topPoint;

    private static boolean topPointReached;
    private static int jumpFactor = 20;

    public static final int STAND_STILL = 1,
            RUNNING = 2,
            JUMPING = 3,
            DIE = 4;
    private final int LEFT_FOOT = 1,
            RIGHT_FOOT = 2,
            NO_FOOT = 3;

    public int state = 0;
    public int gravity = 0;

    private int foot;
    public Image leftFootDino;
    public Image rightFootDino;
    public Image deadDino;
    public ImageView imageView_dino;
    public AnimationTimer jumpTimer;

    public Dino() {
        leftFootDino =  new Image("/images/Dino-left-up.png");
        rightFootDino =  new Image("/images/Dino-right-up.png");
        deadDino =  new Image("/images/Dino-big-eyes.png");

        dinoBaseY = Ground.GROUND_Y + 5;
        dinoTopY = Ground.GROUND_Y - (int)UserInterface.image_dino.getHeight() + 8;
        dinoStartX = 75;

        imageView_dino = new ImageView();
        imageView_dino.setImage(UserInterface.image_dino);
        imageView_dino.setX(dinoStartX);
        imageView_dino.setY(dinoTopY);
        //imageView_dino.setPreserveRatio(true);
        UserInterface.root.getChildren().addAll(imageView_dino);
        UserInterface.timeline_run = new Timeline(new KeyFrame(Duration.seconds(0.1),
                new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent t) {
                        imageView_dino.setImage(UserInterface.image_dino);
                    }
                }), new KeyFrame(Duration.seconds(0.1),
                new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent t) {
                        state = RUNNING;imageView_dino.setImage(leftFootDino);
                    }
                }), new KeyFrame(Duration.seconds(0.2),
                new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent t) {
                        state = RUNNING;
                        imageView_dino.setImage(rightFootDino);
                    }
                }));
//        // Let the animation run forever
        UserInterface.timeline_run.setCycleCount(Timeline.INDEFINITE);
        UserInterface.root.addEventFilter(KeyEvent.KEY_PRESSED, event->{
            if (event.getCode() == KeyCode.SPACE && gravity == 0) {
                    UserInterface.timeline_run.pause();
                    imageView_dino.setImage(UserInterface.image_dino);
                    double ypreviousPos = imageView_dino.getTranslateY();
                    jumpTimer = new AnimationTimer(){
                        @Override
                        public void handle(long now) {
                            gravity += 1;
                            imageView_dino.setTranslateY(imageView_dino.getTranslateY() - 16 + gravity);
                            if (ypreviousPos == imageView_dino.getTranslateY()) {
                                jumpTimer.stop();
                                UserInterface.timeline_run.play();
                                gravity = 0;
                            }
                        }
                    };
                    jumpTimer.start();
            }
        });
    }
    public Rectangle2D getDino() {
        Rectangle2D obstacle = new Rectangle2D(imageView_dino.getTranslateX(), imageView_dino.getTranslateY(),UserInterface.image_dino.getWidth(), UserInterface.image_dino.getHeight());
        return obstacle;
    }
}