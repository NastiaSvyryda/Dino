import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

import java.util.ArrayList;

public class Obstacles {
    public AnimationTimer cactusAnimation;
    private ArrayList<ImageView> imageList;
    //public ImageView cactus;
    int metres = 300;

    public Obstacles() {
        int rand = 0;
        imageList = new ArrayList<ImageView>();
        for (int i =0; i < 5; i++) {
            ImageView imageView_cactus = new ImageView();
            imageView_cactus.setImage(new Image("/images/Cactus-" + i +".png"));
            imageView_cactus.setLayoutY(Ground.GROUND_Y - new Image("/images/Cactus-" + i +".png").getHeight() + 10);
            rand = UserInterface.WIDTH + (int)(Math.random() * 10)*300;
            for (ImageView y : imageList) {
                if (Math.abs(rand - y.getLayoutX()) < 300)
                    rand += 300*10;
            }
            imageView_cactus.setLayoutX(rand);
            UserInterface.root.getChildren().addAll(imageView_cactus);
            imageList.add(imageView_cactus);
        }

//        imageView_cactus.setImage(image);
        //imageView.setX(UserInterface.WIDTH - image.getWidth());
        //imageView_cactus.setPreserveRatio(true);
        //cactus = imageList.get((int)(Math.random() * 5));
        cactusAnimation = new AnimationTimer(){
            @Override
            public void handle(long now) {
                double rand = 0;
                for (ImageView i : imageList) {
                    i.setLayoutX(i.getLayoutX() - 4.44);
                    if (i.getLayoutX() < -50) {
                        rand = UserInterface.WIDTH + (int)(Math.random() * 10)*metres;
                        for (ImageView y : imageList) {
                            if (Math.abs(rand - y.getLayoutX()) < metres)
                                rand += metres*10;
                        }
                        if (metres > 150)
                            metres -= 10;
                        //i = imageList.get((int) (Math.random() * 5));
                        i.setLayoutX(rand);
                        //metres -= 10;
                    }
                }
            }
        };
        UserInterface.timeline_collision = new Timeline(new KeyFrame(Duration.millis(2),
                new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent t) {
                        for (ImageView i : imageList) {
                            if (UserInterface.dino.imageView_dino.getBoundsInParent().intersects(
                                    i.getLayoutX() +10,
                                    i.getLayoutY()+10,
                                    i.getBoundsInParent().getWidth() - 15,
                                    i.getBoundsInParent().getHeight() - 10)) {
                                UserInterface.timeline_run.pause();
                                UserInterface.timeline_ground.pause();
                                cactusAnimation.stop();
                                UserInterface.dino.jumpTimer.stop();
                                UserInterface.timeline_collision.stop();
                                UserInterface.timeline_result.stop();
                                UserInterface.dino.imageView_dino.setImage(UserInterface.dino.deadDino);
                                System.out.println("Dino + Obstacle " + "\n\n");
                            }
                        }
                    }
                }));
        UserInterface.timeline_collision.setCycleCount(Timeline.INDEFINITE);

//        Duration startDuration = Duration.ZERO;
//        Duration endDuration = Duration.seconds(3.1);
//
//        KeyFrame startKeyFrame = new KeyFrame(startDuration,
//                new KeyValue(imageView_cactus.translateXProperty(), UserInterface.WIDTH));
//        KeyFrame endKeyFrame = new KeyFrame(endDuration,
//                new KeyValue(imageView_cactus.translateXProperty(), -1 * image.getWidth()));
//
//        UserInterface.timeline_cactus = new Timeline(startKeyFrame, endKeyFrame);
//        UserInterface.timeline_cactus.setCycleCount(Timeline.INDEFINITE);
    }
//    public Rectangle2D getCactus() {
//        Rectangle2D obstacle = new Rectangle2D(cactus.getTranslateX(), cactus.getTranslateY(),new Image("/images/Cactus-2.png").getWidth(), new Image("/images/Cactus-2.png").getHeight());
//        return obstacle;
//    }
}
