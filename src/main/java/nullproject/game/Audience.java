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

public class Audience {

    public void start(Stage stage){
        Pane pane = new Pane();
        Scene scene = new Scene(pane, Config.WINDOW_WIDTH, Config.WINDOW_HEIGHT);

        ImageView viewZoomSleepyBoy = new ImageView(InitImage.imageInLectureHallSleepyBoy);
        ImageView viewWelcomeToQuest = new ImageView(InitImage.imageInLectureHallWelcomeToQuest);

        viewWelcomeToQuest.setOpacity(0);

        //Set fixed width and height
        viewWelcomeToQuest.fitWidthProperty().bind(scene.widthProperty());
        viewWelcomeToQuest.fitHeightProperty().bind(scene.heightProperty());

        //Set fixed width and height
        viewZoomSleepyBoy.fitWidthProperty().bind(scene.widthProperty());
        viewZoomSleepyBoy.fitHeightProperty().bind(scene.heightProperty());

        Animation.fadeTransition(viewWelcomeToQuest, 5, 0, 1);
        Animation.getFadeTransition().setOnFinished(e->{
            Animation.getFadeTransition().stop();
        });

        pane.getChildren().addAll(viewZoomSleepyBoy, viewWelcomeToQuest);
        scene.setFill(Color.BLACK);
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
    }
}
