package nullproject.game_scene;

import com.sun.org.apache.xml.internal.security.Init;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import nullproject.anim.Animation;
import nullproject.config.Config;
import nullproject.config.InitImage;

public class InLectureHall {

    private static InLectureHall ourInstance = new InLectureHall(); //Class instance

    public static InLectureHall getInstance() {
        return ourInstance;
    } //Static method for working with a class

    //Forbid to create an instance
    private InLectureHall() {
    }

    private Pane pane = new Pane();

    /**
     * Main method
     *
     * @param stage
     */
    public void start(Stage stage) {
        pane.setOpacity(0);
        Scene scene = new Scene(pane, Config.WINDOW_WIDTH, Config.WINDOW_HEIGHT);

        Button buttonNext = new Button();
        Animation.fadeTransition(pane, 1, 0, 1);
        Animation.getFadeTransition().setOnFinished(e -> {
            Animation.getFadeTransition().stop();
            showDialog(pane, InitImage.imageInLectureHallTeacherText, stage, buttonNext);
        });

        ImageView viewInLectureHall = new ImageView(InitImage.imageInLectureHall);

        buttonNext.setOnAction(e -> {
            SleepyBoyScene.getInstance().start(stage);
        });

        //Set fixed width and height
        viewInLectureHall.fitWidthProperty().bind(scene.widthProperty());
        viewInLectureHall.fitHeightProperty().bind(scene.heightProperty());

        pane.getChildren().addAll(viewInLectureHall);
        scene.setFill(Color.BLACK);
        scene.getStylesheets().add(InLectureHall.class.getResource("../../style.css").toExternalForm());
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
    }

    /**
     * Show teacher dialog
     *
     * @param pane
     */
    public void showDialog(Pane pane, Image teacherImage, Stage stage, Button buttonNext) {
        ImageView viewDialog = new ImageView(InitImage.imageInLectureHallDialog);
        ImageView viewTeacherText = new ImageView(teacherImage);
        ImageView viewButtonText = new ImageView(InitImage.imageInLectureHallButtonNext);
        viewButtonText.setFitWidth(200);
        viewButtonText.setFitHeight(100);

        buttonNext.setGraphic(viewButtonText);
        buttonNext.setId("buttonNext");
        buttonNext.setTranslateX(755);
        buttonNext.setTranslateY(0);
        buttonNext.setOpacity(0);

        viewDialog.setFitWidth(540);
        viewDialog.setFitHeight(150);

        viewTeacherText.setFitWidth(540);
        viewTeacherText.setFitHeight(150);

        viewDialog.setTranslateX(Config.WINDOW_WIDTH);
        viewDialog.setTranslateY(Config.WINDOW_HEIGHT);

        viewTeacherText.setTranslateX(430);
        viewTeacherText.setTranslateY(385);

        viewTeacherText.setOpacity(0);

        Animation.translateTransition(viewDialog, 2, 1000, 430, 385, 385);
        Animation.getTranslateTransition().setOnFinished(e -> {
            Animation.getTranslateTransition().stop();
            Animation.fadeTransition(viewTeacherText, 1, 0, 1);
            Animation.getFadeTransition().setOnFinished(e2 -> {
                Animation.getFadeTransition().stop();
                Animation.fadeTransition(buttonNext, 1, 0, 1);
                Animation.getFadeTransition().setOnFinished(e3 -> {
                    Animation.getFadeTransition().stop();
                });
            });

        });

        pane.getChildren().addAll(viewDialog, viewTeacherText, buttonNext);

    }

}
