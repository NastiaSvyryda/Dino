import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class Ground {


    public static int GROUND_Y;

    private Image image;



    public Ground(int panelHeight, int panelWidth) {
        GROUND_Y = (int)(panelHeight - 0.15 * panelHeight);

        try{
            image = new Image("/images/Ground.png");
        } catch(Exception e) {e.printStackTrace();}


        //first ground image:


        ImageView imageView = new ImageView();
        imageView.setImage(image);
        imageView.setX(0);
        imageView.setY(GROUND_Y);
        imageView.setFitWidth(panelWidth);
//        imageView.setPreserveRatio(true);

        ImageView imageView_2 = new ImageView();
        imageView_2.setImage(image);
        imageView_2.setX(0);
        imageView_2.setY(GROUND_Y);
        imageView_2.setFitWidth(panelWidth);
//        imageView_2.setPreserveRatio(true);

//        Group root = new Group(buttonBox,imageView, imageView_2);
        UserInterface.root.getChildren().addAll(imageView, imageView_2);
        // Define the Durations
        Duration startDuration = Duration.ZERO;
        Duration endDuration = Duration.seconds(3);

        // Create the start and end Key Frames
        KeyFrame startKeyFrame = new KeyFrame(startDuration,
                new KeyValue(imageView.translateXProperty(), 0));
        KeyFrame endKeyFrame = new KeyFrame(endDuration,
                new KeyValue(imageView.translateXProperty(), -1 * panelWidth));

        KeyFrame startKeyFrame_2 = new KeyFrame(startDuration,
                new KeyValue(imageView_2.translateXProperty(), panelWidth));
        KeyFrame endKeyFrame_2 = new KeyFrame(endDuration,
                new KeyValue(imageView_2.translateXProperty(), 0));
        // Create a Timeline
        UserInterface.timeline_ground = new Timeline(startKeyFrame, endKeyFrame, startKeyFrame_2,endKeyFrame_2);
        // Let the animation run forever
        UserInterface.timeline_ground.setCycleCount(Timeline.INDEFINITE);

    }
}