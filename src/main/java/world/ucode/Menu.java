package world.ucode;

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
    private final Button restart;
    private final Button pause;
    private final Button play;
    private Ground ground;
    private Obstacles cactus;
    private Dino dino;
    private final Label score_label;
    private final Label game_over;
    private int score;
    public static Timeline timeline_collision;
    public static Timeline timeline_update;
    public static boolean pause_click;
    public static boolean collision;

    public Menu() {
        Group root = new Group();
        Scene scene = new Scene(root);
        UserInterface.stage.setScene(scene);
        root.getStylesheets().add("https://fonts.googleapis.com/css2?family=Press+Start+2P&display=swap");
        pause = pauseButton();
        restart = restartButton();
        score_label = createScoreLabel();
        game_over = gameOver();
        timeline_update = timelineUpdate();
        play = playButton();
        root.getChildren().addAll(score_label, play, pause, restart,game_over);
        startGame(root);
        timeline_collision = timelineCollision(dino);
    }
    private Label createScoreLabel() {
        Label score_label = new Label();
        score_label.setLayoutX(UserInterface.WIDTH - 90);
        score_label.setLayoutY(10);
        score_label.setStyle("-fx-font-family: 'Press Start 2P'; -fx-font-weight: bold; -fx-font-size: 15px");
        score_label.setTextFill(Color.grayRgb(100));
        return score_label;
    }
    private Button playButton() {
        Button playBut = new Button("Play");
        playBut.setLayoutX(UserInterface.WIDTH / 2 - 80);
        playBut.setLayoutY(UserInterface.HEIGHT / 2 - 100);
        final String playStyle = "-fx-font-family: 'Press Start 2P'; -fx-font-weight: bold;" +
                " -fx-font-size: 30px; -fx-border-color: gray;" +
                " -fx-border-radius: 5px;-fx-background-color: transparent;";
        final String playHoverStyle = "-fx-font-family: 'Press Start 2P'; -fx-font-weight: bold;" +
                " -fx-font-size: 30px; -fx-border-color: gray; -fx-border-radius: 5px;" +
                "-fx-background-color: -fx-shadow-highlight-color, -fx-outer-border, -fx-inner-border, -fx-body-color;";
        playBut.setStyle(playStyle);
        playBut.setOnMouseEntered(e -> playBut.setStyle(playHoverStyle));
        playBut.setOnMouseExited(e -> playBut.setStyle(playStyle));
        playBut.setOnAction(playHandle());
        return playBut;
    }

    private EventHandler<ActionEvent> playHandle() {
        return new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                ground.timeline_ground.play();
                dino.timeline_run.play();
                timeline_collision.play();
                cactus.cactusAnimation.start();
                play.setVisible(false);
                pause.setVisible(true);
                timeline_update.play();
            }
        };
    }

    private Timeline timelineUpdate() {
        Timeline timeline_update = new Timeline(new KeyFrame(Duration.millis(100),
                new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent t) {
                        StringBuilder score_str = new StringBuilder(String.valueOf(score));
                        for (int i = score_str.length(); i < 5; i++)
                            score_str.insert(0, 0);
                        score_label.setText(score_str.toString());
                        score++;
                        if (collision) {
                            timeline_collision.stop();
                            dino.timeline_run.stop();
                            dino.imageView_dino.setImage(dino.deadDino);
                            ground.timeline_ground.stop();
                            restart.setVisible(true);
                            pause.setVisible(false);
                            game_over.setVisible(true);
                            collision = false;
                            pause_click = true;
                            Menu.timeline_update.stop();
                        }
                    }
                }));
        timeline_update.setCycleCount(Timeline.INDEFINITE);
        return timeline_update;
    }
    private Button pauseButton() {
        Button pause = new Button("Pause");
        final String pauseStyle = "-fx-font-family: 'Press Start 2P'; -fx-font-weight: bold;" +
                " -fx-font-size: 15px; -fx-border-color: gray; " +
                "-fx-border-radius: 5px;-fx-background-color: transparent;";
        final String pauseHoverStyle = "-fx-font-family: 'Press Start 2P'; -fx-font-weight: bold;" +
                " -fx-font-size: 15px; -fx-border-color: gray; -fx-border-radius: 5px;" +
                "-fx-background-color: -fx-shadow-highlight-color, -fx-outer-border, -fx-inner-border, -fx-body-color;";
        pause.setLayoutX(5);
        pause.setLayoutY(5);
        pause.setVisible(false);
        pause.setStyle(pauseStyle);
        pause.setOnMouseEntered(e -> pause.setStyle(pauseHoverStyle));
        pause.setOnMouseExited(e -> pause.setStyle(pauseStyle));
        pause.setOnMousePressed(pauseHandle());
        return pause;
    }

    private EventHandler<MouseEvent> pauseHandle() {
        return new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (pause_click) {
                    dino.timeline_run.play();
                    ground.timeline_ground.play();
                    timeline_collision.play();
                    timeline_update.play();
                    pause_click = false;
                } else {
                    dino.timeline_run.pause();
                    ground.timeline_ground.pause();
                    timeline_collision.pause();
                    timeline_update.pause();
                    pause_click = true;
                }
            }

        };
    }

    private Button restartButton() {
        ImageView restartIm = new ImageView(new Image("/images/game_restart.png"));
        Button restart = new Button("", restartIm);
        restart.setVisible(false);
        restart.setLayoutX(UserInterface.WIDTH / 2 - 50);
        restart.setLayoutY(UserInterface.HEIGHT / 2 - 50);
        restart.setStyle("-fx-background-color: transparent;");
        restart.setOnMousePressed(restartHandle());
        return restart;
    }

    private EventHandler<MouseEvent> restartHandle() {
        return new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Menu menu = new Menu();
            }

        };
    }

    private Label gameOver() {
        Label gameOver = new Label("Game over");
        gameOver.setLayoutX(UserInterface.WIDTH / 2 - 150);
        gameOver.setLayoutY(UserInterface.HEIGHT / 2 - 100);
        gameOver.setStyle("-fx-font-family: 'Press Start 2P'; -fx-font-weight: bold; -fx-font-size: 35px");
        gameOver.setVisible(false);
        return gameOver;
    }

    private void startGame(Group root) {
        score = 0;
        pause_click = false;
        ground = new Ground(UserInterface.HEIGHT, UserInterface.WIDTH, root);
        dino = new Dino(root);
        cactus = new Obstacles(dino, root);
    }

    private Timeline timelineCollision(Dino dino) {
        Timeline collision = new Timeline(new KeyFrame(Duration.millis(2),
                new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent t) {
                        if (cactus.checkCollision(dino.imageView_dino)) {
                            Menu.collision = true;
                        }
                    }
                }));
        collision.setCycleCount(Timeline.INDEFINITE);
        return collision;
    }


}
