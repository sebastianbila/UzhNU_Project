package nullproject.game;

import javafx.animation.AnimationTimer;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import nullproject.anim.Animation;
import nullproject.config.Config;
import nullproject.config.GameConfigs;
import nullproject.config.InitImage;
import nullproject.game_scene.ReadBookScene;
import nullproject.levels.Level1;
import nullproject.levels.blocks.Blocks;
import nullproject.levels.blocks.Door;
import nullproject.player.Player;

import java.util.ArrayList;
import java.util.HashMap;


public class Game {

    //Collections for the blocks
    public static ArrayList<Blocks> platforms = new ArrayList<>();
    public static ArrayList<Door> doors = new ArrayList<>();

    //Key event
    private HashMap<KeyCode, Boolean> keys = new HashMap<>();

    //Pane
    public static Pane appRoot = new Pane();
    public static Pane gameRoot = new Pane();

    //Player
    public Player player;

    //ImageView
    private ImageView viewDialogOpenDoor = new ImageView(InitImage.imageInGameAudienceOpenDoor);
    private Scene scene = new Scene(appRoot, Config.WINDOW_WIDTH, Config.WINDOW_HEIGHT);


    private static Game ourInstance = new Game();

    public static Game getInstance() {
        return ourInstance;
    }

    private Game() {
        player = new Player();
    }

    public void startGame(Stage gameStage) {
        gameInitialization();

        scene.setOnKeyPressed(event -> keys.put(event.getCode(), true));
        scene.setOnKeyReleased(event -> {
            keys.put(event.getCode(), false);
            player.animation.stop();

        });

        player.setScaleX(GameConfigs.playerSize);
        player.setScaleY(GameConfigs.playerSize);

        appRoot.getChildren().addAll(gameRoot);

        gameStage.setResizable(false);
        gameStage.centerOnScreen();
        gameStage.setScene(scene);
        AnimationTimer animationTimer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                update();
            }
        };
        animationTimer.start();
        gameStage.show();

    }

    public void gameInitialization() {
        Level1.level1();
        player.setTranslateX(50);
        player.setTranslateY(350);
        player.translateXProperty().addListener((obs, old, newValue) -> {
            int offset = newValue.intValue();
            if (offset > 640 && offset < GameConfigs.levelsWidth - 640) {
                gameRoot.setLayoutX(-(offset - 640));
            }
        });

        gameRoot.getChildren().addAll(player, viewDialogOpenDoor);


    }

    private void update() {
        viewDialogOpenDoor.setOpacity(0);

        if (isPressed(KeyCode.UP)) {
            player.animation.play();
            player.animation.setOffsetY(96);
            player.moveY(-5);
        } else if (isPressed(KeyCode.DOWN)) {
            player.animation.play();
            player.animation.setOffsetY(0);
            player.moveY(5);
        } else if (isPressed(KeyCode.RIGHT)) {
            player.animation.play();
            player.animation.setOffsetY(64);
            player.moveX(5);
        } else if (isPressed(KeyCode.LEFT)) {
            player.animation.play();
            player.animation.setOffsetY(32);
            player.moveX(-5);
        } else {
            player.animation.stop();
        }


        player.isDoorOpen();
    }

    private boolean isPressed(KeyCode key) {
        return keys.getOrDefault(key, false);
    }

    public void showDialog() {

        viewDialogOpenDoor.setFitWidth(540);
        viewDialogOpenDoor.setFitHeight(150);

        viewDialogOpenDoor.setTranslateX(500);
        viewDialogOpenDoor.setTranslateY(420);

        viewDialogOpenDoor.setOpacity(1);

        if (isPressed(KeyCode.N)) {

        }

    }

}

