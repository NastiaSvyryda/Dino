import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class Obstacles {
    private Image image;
    public Obstacles() {
        image = new Image("/images/Cactus-1.png");

        ImageView imageView = new ImageView();
        imageView.setImage(image);
        //imageView.setX(UserInterface.WIDTH - image.getWidth());
        imageView.setY(Ground.GROUND_Y - (int)image.getHeight() + 7);
        imageView.setPreserveRatio(true);
        UserInterface.root.getChildren().addAll(imageView);

        Duration startDuration = Duration.ZERO;
        Duration endDuration = Duration.seconds(3.1);

        KeyFrame startKeyFrame = new KeyFrame(startDuration,
                new KeyValue(imageView.translateXProperty(), UserInterface.WIDTH));
        KeyFrame endKeyFrame = new KeyFrame(endDuration,
                new KeyValue(imageView.translateXProperty(), -1 * image.getWidth()));

        UserInterface.timeline_cactus = new Timeline(startKeyFrame, endKeyFrame);
        UserInterface.timeline_cactus.setCycleCount(Timeline.INDEFINITE);
    }
}
