import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;
import java.io.InputStream;

public class UserInterface extends Application {
    public static int WIDTH = 800;
    public static int HEIGHT = 500;
    public static Stage stage;
    public static Scene scene;
    public static Group root;
    public static Timeline timeline_ground;
    public static Timeline timeline_run;
    public static Timeline timeline_cactus;
    public static Timeline timeline_jump;
    public static Image image_dino;
    public static int count;
    Ground ground;
    Dino dino;
    Obstacles cactus;
    @Override

    public void start(Stage primaryStage) throws Exception {
        count = 0;
        stage = primaryStage;
        stage.setTitle("dino");
        stage.setWidth(WIDTH);
        stage.setHeight(HEIGHT);

        image_dino = new Image("/images/Dino-stand.png");
        stage.getIcons().add(image_dino);
        Button play = new Button("Play");
        Button pause = new Button("Pause");
//        pause.setAlignment(Pos.TOP_LEFT);
        pause.alignmentProperty();
        pause.setVisible(false);

//        pause.setOnAction(new EventHandler<ActionEvent>()
//        {
//            public void handle(ActionEvent event)
//            {
//                //if(event.getEventType() != KeyCode.SPACE)
//                timeline_ground.pause();
//                timeline_run.pause();
//                play.setVisible(true);
//                pause.setVisible(false);
//                //status.setText("Current State: " + timeline.getStatus());
//            }
//        });
        HBox buttonBox = new HBox();
        // Set Spacing to 10 pixels
        buttonBox.setSpacing(10);
        // Add the Children to the HBox
        buttonBox.getChildren().addAll(play,pause);

        root = new Group(buttonBox);
//        root.addEventFilter(KeyEvent.KEY_PRESSED, event->{
//            if (event.getCode() == KeyCode.SPACE) {
//                timeline_run.pause();
//                dino.imageView_dino.setImage(image_dino);
//                timeline_jump.play();
//            }
//        });
        ground = new Ground(HEIGHT, WIDTH);
        dino = new Dino();
        cactus = new Obstacles();
        play.setOnAction(new EventHandler<ActionEvent>()
        {
            public void handle(ActionEvent event)
            {
                timeline_ground.play();
                timeline_run.play();
                cactus.cactusAnimation.start();
                play.setVisible(false);
                pause.setVisible(true);
                //status.setText("Current State: " + timeline.getStatus());
            }
        });
//        collision!!!!!!!
//        if (dino.getDino().intersects(cactus.getCactus())) {
//            timeline_run.pause();
//            timeline_ground.pause();
//            dino.imageView_dino.setImage(dino.deadDino);
//            System.out.println("Dino + Obstacle " + "\n\n");
//
//        }
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args) {
        Application.launch();
    }
}
