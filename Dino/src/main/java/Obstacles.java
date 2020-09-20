import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

import java.util.ArrayList;

public class Obstacles {
    public AnimationTimer cactusAnimation;
    private ArrayList<ImageView> imageListCactus;
    private ArrayList<ImageView> imageListClouds;
    int metres = 300;

    public Obstacles() {
        int rand = 0;
        imageListCactus = new ArrayList<ImageView>();
        imageListClouds = new ArrayList<ImageView>();

        for (int i =0; i < 5; i++) {
            ImageView imageView_cactus = new ImageView();
            ImageView imageViewCloud = new ImageView(new Image("/images/cloud.png"));
            imageView_cactus.setImage(new Image("/images/Cactus-" + i +".png"));
            imageView_cactus.setLayoutY(Ground.GROUND_Y - new Image("/images/Cactus-" + i +".png").getHeight() + 10);
            rand = UserInterface.WIDTH + (int)(Math.random() * 10)*300;
            for (ImageView y : imageListCactus) {
                if (Math.abs(rand - y.getLayoutX()) < 300)
                    rand += 300*10;
            }
            imageView_cactus.setLayoutX(rand);
            imageViewCloud.setLayoutY(20 + 20*i);
            imageViewCloud.setLayoutX(rand * 2);
            UserInterface.root.getChildren().addAll(imageView_cactus, imageViewCloud);
            imageListCactus.add(imageView_cactus);
            imageListClouds.add(imageViewCloud);
        }
        cactusAnimation();
        Menu.timeline_collision = checkCollision();
    }
    private Timeline checkCollision() {
        Timeline collision = new Timeline(new KeyFrame(Duration.millis(2),
                new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent t) {
                        for (ImageView i : imageListCactus) {
                            if (Menu.dino.imageView_dino.getBoundsInParent().intersects(
                                    i.getLayoutX() +10,
                                    i.getLayoutY()+10,
                                    i.getBoundsInParent().getWidth() - 15,
                                    i.getBoundsInParent().getHeight() - 10)) {
                                Menu.pause_click = true;
                                Menu.timeline_run.pause();
                                Menu.timeline_ground.pause();
                                cactusAnimation.stop();
                                Menu.timeline_collision.stop();
                                Menu.timeline_score.stop();
                                Menu.dino.jumpTimer.stop();
                                Menu.restart.setVisible(true);
                                Menu.pause.setVisible(false);
                                Menu.dino.imageView_dino.setImage(Menu.dino.deadDino);
                            }
                        }
                    }
                }));
        collision.setCycleCount(Timeline.INDEFINITE);
        return collision;
    }
    private void cactusAnimation() {
        cactusAnimation = new AnimationTimer(){
            @Override
            public void handle(long now) {
                double rand = 0;
                int index = 0;
                for (ImageView i : imageListCactus) {
                    i.setLayoutX(i.getLayoutX() - 4.44);
                    imageListClouds.get(index).setLayoutX(imageListClouds.get(index).getLayoutX() - 2);
                    rand = UserInterface.WIDTH + (int)(Math.random() * 10)*metres;
                    if (i.getLayoutX() < -50) {
                        for (ImageView y : imageListCactus) {
                            if (Math.abs(rand - y.getLayoutX()) < metres)
                                rand += metres*10;
                        }
                        if (metres > 150)
                            metres -= 10;
                        i.setLayoutX(rand);
                    }
                    if (imageListClouds.get(index).getLayoutX() < -100) {
                        imageListClouds.get(index).setLayoutX(rand*2);
                    }
                    index++;
                }
            }
        };
    }
}
