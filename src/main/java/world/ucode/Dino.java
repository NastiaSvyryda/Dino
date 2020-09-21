package world.ucode;

import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.util.Duration;
import world.ucode.Ground;
import world.ucode.Menu;
import world.ucode.UserInterface;


public class Dino {
    public int gravity = 0;
    private final Image leftFootDino;
    private final Image rightFootDino;
    public Image deadDino;
    public ImageView imageView_dino;
    public AnimationTimer jumpTimer;
    public Timeline timeline_run;

    public Dino(Group root) {
        int dinoTopY = Ground.GROUND_Y - (int) UserInterface.image_dino.getHeight() + 8;
        int dinoStartX = 75;
        leftFootDino =  new Image("/images/Dino-left-up.png");
        rightFootDino =  new Image("/images/Dino-right-up.png");
        deadDino =  new Image("/images/Dino-big-eyes.png");
        imageView_dino = new ImageView();
        imageView_dino.setImage(UserInterface.image_dino);
        imageView_dino.setX(dinoStartX);
        imageView_dino.setY(dinoTopY);
        root.getChildren().addAll(imageView_dino);
        timeline_run = run();
        jump(root);
    }

    private void jump(Group root) {
        root.addEventFilter(KeyEvent.KEY_PRESSED, event->{
            if (event.getCode() == KeyCode.SPACE && gravity == 0 && !Menu.pause_click) {
                timeline_run.pause();
                imageView_dino.setImage(UserInterface.image_dino);
                double ypreviousPos = imageView_dino.getTranslateY();
                jumpTimer = new AnimationTimer(){
                    @Override
                    public void handle(long now) {
                        if (Menu.collision)
                            jumpTimer.stop();
                        if (!Menu.pause_click) {
                            gravity += 1;
                            imageView_dino.setTranslateY(imageView_dino.getTranslateY() - 16 + gravity);
                            if (ypreviousPos == imageView_dino.getTranslateY()) {
                                jumpTimer.stop();
                                timeline_run.play();
                                gravity = 0;
                            }
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