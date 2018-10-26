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

public class InLectureHall {

    private static InLectureHall ourInstance = new InLectureHall(); //Class instance

    public static InLectureHall getInstance() {
        return ourInstance;
    } //Static method for working with a class

    //Forbid to create an instance
    private InLectureHall() {
    }

    private Button buttonNext = new Button();
    private Pane pane = new Pane();

    /**
     * Main method
     *
     * @param stage
     */
    public void start(Stage stage) {

        pane.setOpacity(0);

        Animation.fadeTransition(pane, 1, 0, 1);
        Animation.getFadeTransition().setOnFinished(e -> {
            Animation.getFadeTransition().stop();
            showDialog(pane);
        });

        Scene scene = new Scene(pane, Config.WINDOW_WIDTH, Config.WINDOW_HEIGHT);

        ImageView viewMain = new ImageView(InitImage.imageInLectureHall);

        //Set fixed width and height
        viewMain.fitWidthProperty().bind(scene.widthProperty());
        viewMain.fitHeightProperty().bind(scene.heightProperty());

        onClick();

        pane.getChildren().addAll(viewMain);
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
    private void showDialog(Pane pane) {
        ImageView viewTeacherDialog = new ImageView(InitImage.imageInLectureHallTeacherDialog);
        ImageView viewTeacherText = new ImageView(InitImage.imageInLectureHallTeacherText);
        ImageView viewButtonText = new ImageView(InitImage.imageInLectureHallButtonNext);
        viewButtonText.setFitWidth(200);
        viewButtonText.setFitHeight(100);


        buttonNext.setGraphic(viewButtonText);
        buttonNext.setId("buttonNext");
        buttonNext.setTranslateX(755);
        buttonNext.setTranslateY(0);
        buttonNext.setOpacity(0);

        viewTeacherDialog.setFitWidth(540);
        viewTeacherDialog.setFitHeight(150);

        viewTeacherText.setFitWidth(540);
        viewTeacherText.setFitHeight(150);

        viewTeacherDialog.setTranslateX(Config.WINDOW_WIDTH);
        viewTeacherDialog.setTranslateY(Config.WINDOW_HEIGHT);

        viewTeacherText.setTranslateX(430);
        viewTeacherText.setTranslateY(385);

        viewTeacherText.setOpacity(0);

        Animation.translateTransition(viewTeacherDialog, 2, 1000, 430, 385, 385);
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

        pane.getChildren().addAll(viewTeacherDialog, viewTeacherText, buttonNext);
    }

    public void onClick() {
        buttonNext.setOnAction(e -> {
            System.out.println("click");
            ImageView viewZoomSleepyBoy = new ImageView(InitImage.imageInLectureHall);
            viewZoomSleepyBoy.setScaleX(0.5);
            viewZoomSleepyBoy.setScaleY(0.5);
            viewZoomSleepyBoy.setTranslateX(0);
            viewZoomSleepyBoy.setTranslateY(0);
            pane.getChildren().addAll(viewZoomSleepyBoy);
        });
    }
}
