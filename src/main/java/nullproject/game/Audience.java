package nullproject.game;

import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import nullproject.anim.Animation;
import nullproject.config.Config;
import nullproject.config.GameConfigs;
import nullproject.config.InitImage;
import nullproject.game_scene.InLectureHall;
import nullproject.game_scene.ReadBookScene;
import nullproject.player.Player;

public class Audience {

    private static Audience ourInstance = new Audience();

    public static Audience getInstance() {
        return ourInstance;
    }

    private Audience() {}

    public void start() {
        Stage stage = new Stage();
        Pane pane = new Pane();
        Scene scene = new Scene(pane, Config.WINDOW_WIDTH, Config.WINDOW_HEIGHT);

        ImageView viewGameAudience = new ImageView(InitImage.imageInGameAudience);

        //Set fixed width and height
        viewGameAudience.fitWidthProperty().bind(scene.widthProperty());
        viewGameAudience.fitHeightProperty().bind(scene.heightProperty());

        //Set fixed width and height
        viewGameAudience.fitWidthProperty().bind(scene.widthProperty());
        viewGameAudience.fitHeightProperty().bind(scene.heightProperty());

        pane.getChildren().addAll(viewGameAudience);
        scene.setFill(Color.BLACK);
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
    }

}
