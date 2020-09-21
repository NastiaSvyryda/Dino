package world.ucode;

import javafx.animation.AnimationTimer;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import world.ucode.UserInterface;

import java.util.ArrayList;

public class Obstacles {
    public AnimationTimer cactusAnimation;
    private final ArrayList<ImageView> imageListCactus;
    private final ArrayList<ImageView> imageListClouds;
    int metres = 300;

    public Obstacles(Dino dino, Group root) {
        int rand = 0;
        imageListCactus = new ArrayList<ImageView>();
        imageListClouds = new ArrayList<ImageView>();

        for (int i =0; i < 5; i++) {
            ImageView imageView_cactus = new ImageView();
            ImageView imageViewCloud = new ImageView(new Image("/images/cloud.png"));
            imageView_cactus.setImage(new Image("/images/Cactus-" + i +".png"));
            imageView_cactus.setLayoutY(Ground.GROUND_Y - new Image("/images/Cactus-" + i +".png").getHeight() + 10);
            rand = UserInterface.WIDTH + (int)(Math.random() * 10)*300;
            for (ImageView y : imageListCactus) {
                if (Math.abs(rand - y.getLayoutX()) < 300)
                    rand = -50;
            }
            imageView_cactus.setLayoutX(rand);
            imageViewCloud.setLayoutY(20 + 20*i);
            imageViewCloud.setLayoutX(rand * 2);
            root.getChildren().addAll(imageView_cactus, imageViewCloud);
            imageListCactus.add(imageView_cactus);
            imageListClouds.add(imageViewCloud);
        }
        cactusAnimation();
    }

    public boolean checkCollision(ImageView dinoImageview) {
        for (ImageView i : imageListCactus) {
            if (dinoImageview.getBoundsInParent().intersects(
                    i.getLayoutX() +10,
                    i.getLayoutY()+10,
                    i.getBoundsInParent().getWidth() - 15,
                    i.getBoundsInParent().getHeight() - 10)) {
                cactusAnimation.stop();
                return true;
            }
        }
        return false;
    }

    private void cactusAnimation() {
        cactusAnimation = new AnimationTimer(){
            @Override
            public void handle(long now) {
                if (!Menu.pause_click) {
                    double rand = 0;
                    int index = 0;
                    for (ImageView i : imageListCactus) {
                        i.setLayoutX(i.getLayoutX() - 4.44);
                        imageListClouds.get(index).setLayoutX(imageListClouds.get(index).getLayoutX() - 2);
                        rand = UserInterface.WIDTH + (int) (Math.random() * 10) * metres;
                        if (i.getLayoutX() < -50) {
                            for (ImageView y : imageListCactus) {
                                if (Math.abs(rand - y.getLayoutX()) < metres)
                                    rand = -50;
                            }
                            if (metres > 150)
                                metres -= 10;
                            i.setLayoutX(rand);
                        }
                        if (imageListClouds.get(index).getLayoutX() < -100)
                            imageListClouds.get(index).setLayoutX(rand * 2);
                        index++;
                    }
                }
            }
        };
    }
}
