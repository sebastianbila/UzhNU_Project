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
import nullproject.config.Status;
import nullproject.game_scene.ReadBookScene;
import nullproject.levels.Level1;
import nullproject.levels.Level2;
import nullproject.levels.blocks.Blocks;
import nullproject.levels.blocks.Door;
import nullproject.levels.blocks.Text;
import nullproject.player.Player;

import java.util.ArrayList;
import java.util.HashMap;


public class Game {

    //Collections for the blocks
    public static ArrayList<Blocks> platforms = new ArrayList<>();
    public static ArrayList<Door> doors = new ArrayList<>();
    public static ArrayList<Text> text = new ArrayList<>();

    //Key event
    private HashMap<KeyCode, Boolean> keys = new HashMap<>();

    //Pane
    public static Pane appRoot = new Pane();
    public static Pane gameRoot = new Pane();

    //Player
    public Player player;

    private int playerSpeed = 3;
    private boolean isCanExit = false;
    private int status = 1;

    //ImageView
    private ImageView viewDialogOpenDoor = new ImageView(InitImage.imageInGameAudienceOpenDoor);
    private ImageView viewDialogDoorIsClosed = new ImageView(InitImage.imageInGameAudienceDoorClose);
    private ImageView viewTextOnDesk = new ImageView(InitImage.imageInGameAudienceTextOnDesk);

    private Scene scene = new Scene(appRoot, Config.WINDOW_WIDTH, Config.WINDOW_HEIGHT);
    private Stage mainStage = new Stage();

    private static Game ourInstance = new Game();

    public static Game getInstance() {
        return ourInstance;
    }

    private Game() {
    }

    public void startGame(Stage gameStage, Status status) {

        if (status == Status.LEVEL_1) {
            gameInitialization();
        } else if (status == Status.LEVEL_2) {
            gameInitializationL2();
        }

        mainStage = gameStage;
        scene.setOnKeyPressed(event -> keys.put(event.getCode(), true));
        scene.setOnKeyReleased(event -> {
            keys.put(event.getCode(), false);
            player.animation.stop();

        });

        player.setScaleX(GameConfigs.playerSize);
        player.setScaleY(GameConfigs.playerSize);

        //Set fixed width and height
        viewDialogDoorIsClosed.fitWidthProperty().bind(scene.widthProperty());
        viewDialogDoorIsClosed.fitHeightProperty().bind(scene.heightProperty());

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
        viewDialogDoorIsClosed.setOpacity(0);
        viewTextOnDesk.setOpacity(0);

        player = new Player();
        ImageView viewInAudience = new ImageView(InitImage.imageInGameAudience);

        playerSpeed = 3;
        //Set fixed width and height
        viewInAudience.fitWidthProperty().bind(scene.widthProperty());
        viewInAudience.fitHeightProperty().bind(scene.heightProperty());

        Level2.level2();
        player.setTranslateX(150);
        player.setTranslateY(400);

        gameRoot.getChildren().addAll(viewInAudience, viewDialogDoorIsClosed, player);
    }

    public void gameInitializationL2() {
        player = new Player();
        playerSpeed = 5;
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
            player.animation.setOffsetY(144);
            player.moveY(-playerSpeed);
        } else if (isPressed(KeyCode.DOWN)) {
            player.animation.play();
            player.animation.setOffsetY(0);
            player.moveY(playerSpeed);
        } else if (isPressed(KeyCode.RIGHT)) {
            player.animation.play();
            player.animation.setOffsetY(96);
            player.moveX(playerSpeed);

        } else if (isPressed(KeyCode.LEFT)) {
            player.animation.play();
            player.animation.setOffsetY(48);
            player.moveX(-playerSpeed);

        } else {
            player.animation.stop();
        }
        player.isDoorOpen();
        player.isDoorOpen();
    }


    public void showDialog() {
        viewDialogOpenDoor.setFitWidth(540);
        viewDialogOpenDoor.setFitHeight(150);

        viewDialogOpenDoor.setTranslateX(500);
        viewDialogOpenDoor.setTranslateY(420);

        viewDialogOpenDoor.setOpacity(1);

        if (isPressed(KeyCode.N)) {
            if (isCanExit() == false) {
                dialogForbidExit();
            } else {
                gameRoot.getChildren().clear();
                appRoot.getChildren().clear();
                platforms.clear();
                doors.clear();
                playerSpeed = 3;
                Game.getInstance().startGame(mainStage, Status.LEVEL_2);
            }
        }
    }

    public void dialogForbidExit() {
        viewDialogDoorIsClosed.setOpacity(1);
        if (isPressed(KeyCode.C)) {
            System.out.println("Click ");
            player.setTranslateX(150);
            player.setTranslateY(400);
            gameRoot.getChildren().clear();
            appRoot.getChildren().clear();
            playerSpeed = 3;
            platforms.clear();
            doors.clear();
            Game.getInstance().startGame(mainStage, Status.LEVEL_1);
        }
    }

    public void dialogShowTextOnDesk() {
        viewTextOnDesk.setOpacity(1);

    }


    public boolean isCanExit() {
        return isCanExit;
    }

    public void setCanExit(boolean canExit) {
        isCanExit = canExit;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    private boolean isPressed(KeyCode key) {
        return keys.getOrDefault(key, false);
    }

}

