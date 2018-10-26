package nullproject.game_scene;

import com.sun.org.apache.xml.internal.security.Init;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import nullproject.anim.Animation;
import nullproject.config.Config;
import nullproject.config.InitImage;

public class SleepyBoyScene {

    private static SleepyBoyScene ourInstance = new SleepyBoyScene(); //Class instance

    public static SleepyBoyScene getInstance() {
        return ourInstance;
    } //Static method for working with a class

    //Forbid to create an instance
    private SleepyBoyScene() {
    }

    public void start(Stage stage) {
        Pane pane = new Pane();
        Scene scene = new Scene(pane, Config.WINDOW_WIDTH, Config.WINDOW_HEIGHT);

        ImageView viewZoomSleepyBoy = new ImageView(InitImage.imageInLectureHallSleepyBoy);
        ImageView viewInLectureHall = new ImageView(InitImage.imageInLectureHall);

        //Set fixed width and height
        viewZoomSleepyBoy.fitWidthProperty().bind(scene.widthProperty());
        viewZoomSleepyBoy.fitHeightProperty().bind(scene.heightProperty());

        //Set fixed width and height
        viewInLectureHall.fitWidthProperty().bind(scene.widthProperty());
        viewInLectureHall.fitHeightProperty().bind(scene.heightProperty());

        viewZoomSleepyBoy.setScaleX(0);
        viewZoomSleepyBoy.setScaleY(0);
        viewZoomSleepyBoy.setTranslateX(0);
        viewZoomSleepyBoy.setTranslateY(0);

        Button buttonNext = new Button();

        Animation.scaleTransition(viewZoomSleepyBoy, 0.2, 0, 1, 0, 1);
        Animation.getScaleTransition().setOnFinished(e2 -> {
            Animation.getScaleTransition().stop();
            InLectureHall.getInstance().showDialog(pane, InitImage.imageInLectureHallBoyText, stage, buttonNext);
        });

        buttonNext.setOnAction(e -> {
            QuestMenu.getInstance().start(stage);
        });

        pane.getChildren().addAll(viewInLectureHall, viewZoomSleepyBoy);
        scene.setFill(Color.BLACK);
        scene.getStylesheets().add(InLectureHall.class.getResource("../../style.css").toExternalForm());
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
    }
}
