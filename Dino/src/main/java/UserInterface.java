import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import java.io.InputStream;

public class UserInterface extends Application {
    public static int WIDTH = 800;
    public static int HEIGHT = 500;
    public static Stage stage;
    public static Scene scene;
    public static BorderPane root;
    Ground ground;
    @Override
    public void start(Stage primaryStage) throws Exception {
        stage = primaryStage;
        stage.setTitle("dino");
        stage.setWidth(WIDTH);
        stage.setHeight(HEIGHT);

        InputStream iconStream = getClass().getResourceAsStream("/images/Dino-stand.png");
        Image image = new Image(iconStream);
        stage.getIcons().add(image);
        ground = new Ground(HEIGHT, WIDTH);

//        Label helloWorldLabel = new Label("Hello world!");
//        helloWorldLabel.setAlignment(Pos.TOP_CENTER);
//        Scene primaryScene = new Scene(helloWorldLabel);
//        primaryStage.setScene(primaryScene);

//        root = new BorderPane();       // корневой узел
//        scene = new Scene(root);  // создание Scene
//        stage.setScene(scene);
        stage.setResizable(false);


        stage.show();
    }
    public static void main(String[] args) {
        Application.launch();
    }
}
