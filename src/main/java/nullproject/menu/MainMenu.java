package nullproject.menu;

import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.HashMap;

public class MainMenu {

    private final int WINDOW_WIDTH = 960; // Windows width
    private final int WINDOW_HEIGHT = 540; //Window height

    private HashMap<KeyCode, Boolean> keys = new HashMap<>(); //For key pressed

    private Image imageMain = new Image(getClass().getResourceAsStream("../../main.png"));
    private ImageView viewMain = new ImageView(imageMain);

    private static MainMenu ourInstance = new MainMenu(); //Class instance

    public static MainMenu getInstance() {
        return ourInstance;
    } //Static method for working with a class

    private MainMenu() {
    } //Forbid to create an instance

    public void start() {
        Stage stage = new Stage();
        Pane pane = new Pane();
        Scene scene = new Scene(pane, WINDOW_WIDTH, WINDOW_HEIGHT);

        //Set fixed width and height
        viewMain.fitWidthProperty().bind(scene.widthProperty());
        viewMain.fitHeightProperty().bind(scene.heightProperty());

        //Initialize keyboard click for scene
        scene.setOnKeyPressed(event -> keys.put(event.getCode(), true));
        scene.setOnKeyReleased(event -> {
            keys.put(event.getCode(), false);

        });

        //The Animation timer always checks for a click on the keyboard
        AnimationTimer animationTimer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                initKeyboard();
            }
        };

        pane.getChildren().addAll(viewMain);
        stage.setScene(scene);
        animationTimer.start();
        stage.show();
    }

    private boolean isKeyPressed(KeyCode key) {
        return keys.getOrDefault(key, false);
    }

    private void initKeyboard() {
        if (isKeyPressed(KeyCode.S)) {
            System.out.println("Key pressed");
        }
    }
}
