//import java.awt.Graphics;
//import java.awt.Rectangle;
//import java.awt.image.BufferedImage;
//import java.io.InputStream;
//import java.util.ArrayList;
//
//import javafx.scene.image.Image;
//import javafx.scene.paint.Color;
//
//
//public class Dino {
//    private static int dinoBaseY, dinoTopY, dinoStartX, dinoEndX;
//    private static int dinoTop, dinoBottom, topPoint;
//
//    private static boolean topPointReached;
//    private static int jumpFactor = 20;
//
//    public static final int STAND_STILL = 1,
//            RUNNING = 2,
//            JUMPING = 3,
//            DIE = 4;
//    private final int LEFT_FOOT = 1,
//            RIGHT_FOOT = 2,
//            NO_FOOT = 3;
//
//    private static int state;
//
//    private int foot;
//
//    static Image image;
//    Image leftFootDino;
//    Image rightFootDino;
//    Image deadDino;
//
//    public Dino() {
//        InputStream iconStream = getClass().getResourceAsStream("/images/Dino-stand.png");
//        image = new Image(iconStream);
//        iconStream = getClass().getResourceAsStream("/images/Dino-left-up.png");
//        leftFootDino =  new Image(iconStream);
//        iconStream = getClass().getResourceAsStream("/images/Dino-right-up.png");
//        rightFootDino =  new Image(iconStream);
//        iconStream = getClass().getResourceAsStream("/images/Dino-big-eyes.png");
//        deadDino =  new Image(iconStream);
//
//        dinoBaseY = Ground.GROUND_Y + 5;
//        dinoTopY = Ground.GROUND_Y - (int)image.getHeight() + 5;
//        dinoStartX = 100;
//        dinoEndX = dinoStartX + (int)image.getWidth();
//        topPoint = dinoTopY - 120;
//
//        state = 1;
//        foot = NO_FOOT;
//    }
//
//}