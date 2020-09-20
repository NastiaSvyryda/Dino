import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.util.Duration;

public class Menu {
    public static int score;
    public static Timeline timeline_ground;
    public static Timeline timeline_run;
    public static Timeline timeline_collision;
    public static Timeline timeline_score;
    public static Label score_label;
    public static boolean pause_click = false;
    public static Ground ground;
    public static Dino dino;
    public static Obstacles cactus;
    public static Button restart;
    public static Button pause;
    public static Button play;

    public Menu() {
        UserInterface.root = new Group();
        UserInterface.scene = new Scene(UserInterface.root);
        UserInterface.stage.setScene(UserInterface.scene);
        pauseButton();
        restartButton();
        createScoreLabel();
        timelineScore();
        playButton();
        UserInterface.root.getChildren().addAll(score_label, play, pause, restart);
        startGame();
    }
    private void createScoreLabel() {
        score_label = new Label();
        score_label.setLayoutX(UserInterface.WIDTH - 100);
        score_label.setStyle("-fx-font-family: 'Courier New'; -fx-font-weight: bold; -fx-font-size: 25px");
        score_label.setTextFill(Color.grayRgb(100));


    }
    private void playButton() {
        play = new Button("Play");
        play.setLayoutX(UserInterface.WIDTH / 2 - 50);
        play.setLayoutY(UserInterface.HEIGHT / 2 - 100);
        final String playStyle = "-fx-font-family: 'Courier New'; -fx-font-weight: bold;" +
                " -fx-font-size: 30px; -fx-border-color: gray;" +
                " -fx-border-radius: 5px;-fx-background-color: transparent;";
        final String playHoverStyle = "-fx-font-family: 'Courier New'; -fx-font-weight: bold;" +
                " -fx-font-size: 30px; -fx-border-color: gray; -fx-border-radius: 5px;" +
                "-fx-background-color: -fx-shadow-highlight-color, -fx-outer-border, -fx-inner-border, -fx-body-color;";
        play.setStyle(playStyle);
        play.setOnMouseEntered(e -> play.setStyle(playHoverStyle));
        play.setOnMouseExited(e -> play.setStyle(playStyle));
        play.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                timeline_ground.play();
                timeline_run.play();
                timeline_collision.play();
                cactus.cactusAnimation.start();
                play.setVisible(false);
                pause.setVisible(true);
                score_label.setText("hallo");
                timeline_score.play();
            }
        });
    }
    private void timelineScore() {
        timeline_score = new Timeline(new KeyFrame(Duration.millis(100),
                new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent t) {
                        String score_str = String.valueOf(score);
                        for (int i = score_str.length(); i < 5; i++) {
                            score_str = 0 + score_str;
                        }
                        score_label.setText(score_str);
                        score++;
                    }
                }));
        timeline_score.setCycleCount(Timeline.INDEFINITE);
    }
    private void pauseButton() {
        pause = new Button("Pause");
        final String pauseStyle = "-fx-font-family: 'Courier New'; -fx-font-weight: bold;" +
                " -fx-font-size: 15px; -fx-border-color: gray; " +
                "-fx-border-radius: 5px;-fx-background-color: transparent;";
        final String pauseHoverStyle = "-fx-font-family: 'Courier New'; -fx-font-weight: bold;" +
                " -fx-font-size: 15px; -fx-border-color: gray; -fx-border-radius: 5px;" +
                "-fx-background-color: -fx-shadow-highlight-color, -fx-outer-border, -fx-inner-border, -fx-body-color;";
        pause.setLayoutX(5);
        pause.setLayoutY(5);
        pause.setVisible(false);
        pause.setStyle(pauseStyle);
        pause.setOnMouseEntered(e -> pause.setStyle(pauseHoverStyle));
        pause.setOnMouseExited(e -> pause.setStyle(pauseStyle));

        pause.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (pause_click) {
                    pause_click = false;
                    timeline_run.play();
                    timeline_ground.play();
                    cactus.cactusAnimation.start();
                    timeline_collision.play();
                    timeline_score.play();
                    if (dino.gravity != 0)
                        dino.jumpTimer.start();
                } else {
                    pause_click = true;
                    timeline_run.pause();
                    timeline_ground.pause();
                    cactus.cactusAnimation.stop();
                    timeline_collision.pause();
                    timeline_score.pause();
                    dino.jumpTimer.stop();
                }
            }

        });
    }

    public void restartButton() {
        ImageView restartIm = new ImageView(new Image("/images/game_restart.png"));
        restart = new Button("", restartIm);
        restart.setVisible(false);
        restart.setStyle("-fx-background-color: transparent;");
        restart.setLayoutX(UserInterface.WIDTH / 2 - 50);
        restart.setLayoutY(UserInterface.HEIGHT / 2 - 100);
        restart.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Menu menu = new Menu();
            }

        });

    }

    public void startGame() {
        score = 0;
        pause_click = false;
        ground = new Ground(UserInterface.HEIGHT, UserInterface.WIDTH);
        dino = new Dino();
        cactus = new Obstacles();
    }
}
