package nullproject.game_scene;

import javafx.scene.Scene;
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
    private InLectureHall() {}

    public void start(Stage stage) {
        Pane pane = new Pane();

        pane.setOpacity(0);

        Animation.fadeTransition(pane, 1, 0, 1);
        Animation.getFadeTransition().setOnFinished(e -> {
            Animation.getFadeTransition().stop();
        });

        Scene scene = new Scene(pane, Config.WINDOW_WIDTH, Config.WINDOW_HEIGHT);

        ImageView viewMain = new ImageView(InitImage.imageInLectureHall);

        //Set fixed width and height
        viewMain.fitWidthProperty().bind(scene.widthProperty());
        viewMain.fitHeightProperty().bind(scene.heightProperty());

        pane.getChildren().addAll(viewMain);
        scene.setFill(Color.BLACK);
        stage.setScene(scene);
        stage.show();
    }

}
