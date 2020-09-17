import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.InputStream;
import java.util.ArrayList;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
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

    private static int state;

    private int foot;
    Image leftFootDino;
    Image rightFootDino;
    Image deadDino;
    public ImageView imageView_dino;

    public Dino() {
        leftFootDino =  new Image("/images/Dino-left-up.png");
        rightFootDino =  new Image("/images/Dino-right-up.png");
        deadDino =  new Image("/images/Dino-big-eyes.png");

        dinoBaseY = Ground.GROUND_Y + 5;
        dinoTopY = Ground.GROUND_Y - (int)UserInterface.image_dino.getHeight() + 7;
        dinoStartX = 75;
        dinoEndX = dinoStartX + (int)UserInterface.image_dino.getWidth();
        topPoint = dinoTopY - 120;

        state = 1;
        foot = NO_FOOT;
        ImageView imageView_dino = new ImageView();
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
                        imageView_dino.setImage(leftFootDino);
                    }
                }), new KeyFrame(Duration.seconds(0.2),
                new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent t) {
                        imageView_dino.setImage(rightFootDino);
                    }
                }));
//        // Let the animation run forever
        UserInterface.timeline_run.setCycleCount(Timeline.INDEFINITE);

    }

}