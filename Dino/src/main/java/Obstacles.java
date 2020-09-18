import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

import java.util.ArrayList;

public class Obstacles {
    public AnimationTimer cactusAnimation;
    private ArrayList<ImageView> imageList;
    ImageView cactus;

    public Obstacles() {
        imageList = new ArrayList<ImageView>();
        for (int i =0; i < 5; i++) {
            ImageView imageView_cactus = new ImageView();
            imageView_cactus.setImage(new Image("/images/Cactus-" + i +".png"));
            imageView_cactus.setLayoutY(Ground.GROUND_Y - new Image("/images/Cactus-" + i +".png").getHeight() + 7);
            imageView_cactus.setLayoutX(UserInterface.WIDTH + Math.random() * (500 - 100) + 100);
            UserInterface.root.getChildren().addAll(imageView_cactus);
            imageList.add(imageView_cactus);
        }

//        imageView_cactus.setImage(image);
        //imageView.setX(UserInterface.WIDTH - image.getWidth());
        //imageView_cactus.setPreserveRatio(true);
        cactus = imageList.get((int)(Math.random() * 5));
        cactusAnimation = new AnimationTimer(){
            @Override
            public void handle(long now) {
                cactus.setLayoutX(cactus.getLayoutX() - 3.33);
                if (cactus.getLayoutX() < - 50) {
                    cactus = imageList.get((int)(Math.random() * 5));
                    cactus.setLayoutX(UserInterface.WIDTH + Math.random() * (500 - 100) + 100);
                }
            }
        };

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
//        Rectangle2D obstacle = new Rectangle2D(imageView_cactus.getTranslateX(), imageView_cactus.getTranslateY(),image.getWidth(), image.getHeight());
//        return obstacle;
//    }
}
