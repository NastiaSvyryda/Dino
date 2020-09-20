import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class UserInterface extends Application {
    public static int WIDTH = 800;
    public static int HEIGHT = 500;
    public static Image image_dino;
    public static Group root;
    public static Scene scene;
    public static Stage stage;

    @Override
    public void start(Stage primaryStage){
        stage = primaryStage;
        image_dino = new Image("/images/Dino-stand.png");
        Menu menu = new Menu();
        stagePreferences();
        stage.setResizable(false);
        stage.show();
    }

    private void stagePreferences() {
        stage.setTitle("dino");
        stage.setWidth(WIDTH);
        stage.setHeight(HEIGHT);
        stage.getIcons().add(image_dino);
    }

    public static void main(String[] args) {
        launch();
    }
}
