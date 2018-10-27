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
import nullproject.levels.Level2;
import nullproject.levels.Level3;
import nullproject.levels.Level4;
import nullproject.levels.blocks.*;
import nullproject.player.Player;

import java.util.ArrayList;
import java.util.HashMap;


public class Game {

    //Collections for the blocks
    public static ArrayList<Blocks> platforms = new ArrayList<>();
    public static ArrayList<Door> doors = new ArrayList<>();
    public static ArrayList<Text> text = new ArrayList<>();
    public static ArrayList<DoorBack> doorsBack = new ArrayList<>();
    public static ArrayList<DoorNext> doorsNext = new ArrayList<>();
    public static ArrayList<DoorBackBack> doorsBackBack = new ArrayList<>();

    //Key event
    private HashMap<KeyCode, Boolean> keys = new HashMap<>();

    //Pane
    public static Pane appRoot = new Pane();
    public static Pane gameRoot = new Pane();

    //Player
    public Player player;

    private static int playerSpeed = 3;
    private boolean isCanExit = false;

    //ImageView
    private ImageView viewDialogOpenDoor = new ImageView(InitImage.imageInGameAudienceOpenDoor);
    private ImageView viewDialogDoorIsClosed = new ImageView(InitImage.imageInGameAudienceDoorClose);
    private ImageView viewTextOnDesk = new ImageView(InitImage.imageInGameAudienceTextOnDesk);
    private ImageView viewOnCorridor = new ImageView(InitImage.imageInGameAudienceInCorridor);
    private ImageView viewInHall3 = new ImageView(InitImage.imageInGameAudience3);

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
            gameInitializationLevel2();
        } else if (status == Status.LEVEL_3) {
            gameInitializationLevel3();
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

        //Set fixed width and height
        viewOnCorridor.fitWidthProperty().bind(scene.widthProperty());
        viewOnCorridor.fitHeightProperty().bind(scene.heightProperty());
        setPlayerSpeed(3);
        //Set fixed width and height
        viewInHall3.fitWidthProperty().bind(scene.widthProperty());
        viewInHall3.fitHeightProperty().bind(scene.heightProperty());

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

        //Set fixed width and height
        viewInAudience.fitWidthProperty().bind(scene.widthProperty());
        viewInAudience.fitHeightProperty().bind(scene.heightProperty());
        setPlayerSpeed(3);
        Level2.level2();
        player.setTranslateX(150);
        player.setTranslateY(400);

        gameRoot.getChildren().addAll(viewInAudience, viewDialogDoorIsClosed, viewTextOnDesk, viewDialogOpenDoor, player);
    }

    public void gameInitializationLevel2() {
        viewOnCorridor.setOpacity(1);

        player = new Player();
        Level3.level3();
        player.setTranslateX(300);
        player.setTranslateY(250);
        player.translateXProperty().addListener((obs, old, newValue) -> {
            int offset = newValue.intValue();
            if (offset > 640 && offset < GameConfigs.levelsWidth - 640) {
                gameRoot.setLayoutX(-(offset - 640));
            }
        });
        setPlayerSpeed(3);
        gameRoot.getChildren().addAll(viewDialogOpenDoor, viewDialogDoorIsClosed, viewOnCorridor, player);
    }

    public void gameInitializationLevel3() {
        viewInHall3.setOpacity(1);
        setPlayerSpeed(3);
        player = new Player();
        Level4.level4();
        player.setTranslateX(300);
        player.setTranslateY(250);
        player.translateXProperty().addListener((obs, old, newValue) -> {
            int offset = newValue.intValue();
            if (offset > 640 && offset < GameConfigs.levelsWidth - 640) {
                gameRoot.setLayoutX(-(offset - 640));
            }
        });

        gameRoot.getChildren().addAll(viewInHall3, player);
    }

    private void update() {
        viewDialogOpenDoor.setOpacity(0);
        viewTextOnDesk.setOpacity(0);
        setPlayerSpeed(3);
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
        player.onText();
        player.onDoorBack();
        player.onDoorNext();
        player.onDoorNext2();
    }

    public void nextLevel() {
        gameRoot.getChildren().clear();
        appRoot.getChildren().clear();
        platforms.clear();
        doorsNext.clear();
        doorsBack.clear();
        text.clear();
        doors.clear();
        Game.getInstance().startGame(mainStage, Status.LEVEL_2);
    }

    public void dialogShowTextOnDesk() {
        viewTextOnDesk.setOpacity(1);

        viewTextOnDesk.setTranslateX(340);
        viewTextOnDesk.setTranslateY(60);
        setPlayerSpeed(3);
        if (isPressed(KeyCode.K)) {
            setCanExit(true);
        }
    }

    public void dialogShow() {
        viewDialogOpenDoor.setOpacity(1);

        viewDialogOpenDoor.setTranslateX(0);
        viewDialogOpenDoor.setTranslateY(0);
        setPlayerSpeed(3);
        if (isPressed(KeyCode.N)) {
            if (isCanExit() == false) {
                dialogForbidExit();
            } else {
                nextLevel();
            }
        }

    }

    public void dialogForbidExit() {
        viewDialogDoorIsClosed.setOpacity(1);
        player.setTranslateX(150);
        player.setTranslateY(400);

        System.out.println("heeeee");

        scene.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.C) {
                System.out.println("A key was pressed");
                System.out.println("Fdd");
                gameRoot.getChildren().clear();
                appRoot.getChildren().clear();
                platforms.clear();
                doorsNext.clear();
                doorsBack.clear();
                text.clear();
                setPlayerSpeed(3);
                doors.clear();
                Game.getInstance().startGame(mainStage, Status.LEVEL_1);
            }
        });
    }

    public void dialogBackDoor() {
        viewDialogOpenDoor.setOpacity(1);

        viewDialogOpenDoor.setTranslateX(0);
        viewDialogOpenDoor.setTranslateY(0);


        if (isPressed(KeyCode.N)) {
            gameRoot.getChildren().clear();
            appRoot.getChildren().clear();
            platforms.clear();
            doorsNext.clear();
            doorsBack.clear();
            text.clear();
            setPlayerSpeed(3);
            doors.clear();
            Game.getInstance().startGame(mainStage, Status.LEVEL_1);
        }
    }

    public void dialogBackNext() {
        viewInHall3.setOpacity(1);

        viewInHall3.setTranslateX(0);
        viewInHall3.setTranslateY(0);

        if (isPressed(KeyCode.N)) {
            gameRoot.getChildren().clear();
            appRoot.getChildren().clear();
            platforms.clear();
            setPlayerSpeed(3);
            doorsNext.clear();
            doorsBack.clear();
            text.clear();
            doors.clear();
            Game.getInstance().startGame(mainStage, Status.LEVEL_2);
        }
    }


    public void dialogBackBack() {
        if (isPressed(KeyCode.N)) {
            setPlayerSpeed(3);
            gameRoot.getChildren().clear();
            appRoot.getChildren().clear();
            platforms.clear();
            doorsNext.clear();
            doorsBack.clear();
            text.clear();
            doors.clear();
            Game.getInstance().startGame(mainStage, Status.LEVEL_3);
        }
    }

    public boolean isCanExit() {
        return isCanExit;
    }

    public void setCanExit(boolean canExit) {
        isCanExit = canExit;
    }

    private boolean isPressed(KeyCode key) {
        return keys.getOrDefault(key, false);
    }

    public static void setPlayerSpeed(int playerSpeed) {
        Game.playerSpeed = playerSpeed;
    }
}

