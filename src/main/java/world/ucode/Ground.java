package world.ucode;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class Ground {
    public static int GROUND_Y;
    private final Image image;
    public Timeline timeline_ground;

    public Ground(int panelHeight, int panelWidth, Group root) {
        GROUND_Y = (int)(panelHeight - 0.15 * panelHeight);
        image = new Image("/images/Ground.png");

        ImageView imageView = createImageviewGround(panelWidth);
        ImageView imageView_2 = createImageviewGround(panelWidth);

        root.getChildren().addAll(imageView, imageView_2);
        timeline_ground = timelineGround(panelWidth, imageView, imageView_2);

    }

    private ImageView createImageviewGround(int width) {
        ImageView imageView = new ImageView();

        imageView.setImage(image);
        imageView.setX(0);
        imageView.setY(GROUND_Y);
        imageView.setFitWidth(width);
        return imageView;
    }
    private Timeline timelineGround(int width, ImageView imageView, ImageView imageView_2) {
        Duration startDuration = Duration.ZERO;
        Duration endDuration = Duration.seconds(3);

        KeyFrame startKeyFrame = new KeyFrame(startDuration,
                new KeyValue(imageView.translateXProperty(), 0));
        KeyFrame endKeyFrame = new KeyFrame(endDuration,
                new KeyValue(imageView.translateXProperty(), -1 * width));

        KeyFrame startKeyFrame_2 = new KeyFrame(startDuration,
                new KeyValue(imageView_2.translateXProperty(), width));
        KeyFrame endKeyFrame_2 = new KeyFrame(endDuration,
                new KeyValue(imageView_2.translateXProperty(), 0));
        Timeline timeline_ground = new Timeline(startKeyFrame, endKeyFrame, startKeyFrame_2,endKeyFrame_2);
        timeline_ground.setCycleCount(Timeline.INDEFINITE);
        return timeline_ground;
    }
}