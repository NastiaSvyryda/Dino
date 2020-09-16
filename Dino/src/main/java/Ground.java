import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.util.Duration;

import java.io.InputStream;
import java.util.ArrayList;

public class Ground {

    private class GroundImage {
        Image image;
        int x;
    }

    public static int GROUND_Y;

    private Image image;

    private ArrayList<GroundImage> groundImageSet;


    public Ground(int panelHeight, int panelWidth) {
        GROUND_Y = (int)(panelHeight - 0.15 * panelHeight);

        try{
            InputStream iconStream = getClass().getResourceAsStream("/images/Ground.png");
            image = new Image(iconStream);
        } catch(Exception e) {e.printStackTrace();}

        groundImageSet = new ArrayList<GroundImage>();

        //first ground image:
        for(int i=0; i<3; i++) {
            GroundImage obj = new GroundImage();
            obj.image = image;
            obj.x = 0;
            groundImageSet.add(obj);
        }
        ImageView imageView = new ImageView();
        imageView.setImage(image);
        imageView.setX(0);
        imageView.setY(GROUND_Y);
        imageView.setFitWidth(panelWidth);
        imageView.setPreserveRatio(true);
        BorderPane root = new BorderPane(imageView);
        Scene scene = new Scene(root, 595, 370);
        UserInterface.stage.setScene(scene);
        UserInterface.stage.show();


        double sceneWidth = scene.getWidth();
        double textWidth = imageView.getLayoutBounds().getWidth();

        // Define the Durations
        Duration startDuration = Duration.ZERO;
        Duration endDuration = Duration.seconds(10);

        // Create the start and end Key Frames
        KeyValue startKeyValue = new KeyValue(imageView.translateXProperty(), sceneWidth);
        KeyFrame startKeyFrame = new KeyFrame(startDuration, startKeyValue);
        KeyValue endKeyValue = new KeyValue(imageView.translateXProperty(), -1.0 * textWidth);
        KeyFrame endKeyFrame = new KeyFrame(endDuration, endKeyValue);

        // Create a Timeline
        Timeline timeline;
        timeline = new Timeline(startKeyFrame, endKeyFrame);
        // Let the animation run forever
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }
}