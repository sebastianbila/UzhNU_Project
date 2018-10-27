package nullproject.game_scene;

import com.sun.org.apache.xerces.internal.impl.dv.xs.AnyURIDV;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import nullproject.anim.Animation;
import nullproject.config.Config;
import nullproject.config.InitImage;

public class ReadBookScene {

    private static ReadBookScene ourInstance = new ReadBookScene(); //Class instance

    public static ReadBookScene getInstance() {
        return ourInstance;
    } //Static method for working with a class

    //Forbid to create an instance
    private ReadBookScene() {
    }

    public void start(Stage stage) {
        Pane pane = new Pane();
        Scene scene = new Scene(pane, Config.WINDOW_WIDTH, Config.WINDOW_HEIGHT);

        ImageView viewOpenedBook = new ImageView(InitImage.imageInLectureHallOpenedBook);

        viewOpenedBook.setOpacity(0);

        //Set fixed width and height
        viewOpenedBook.fitWidthProperty().bind(scene.widthProperty());
        viewOpenedBook.fitHeightProperty().bind(scene.heightProperty());

        Animation.fadeTransition(viewOpenedBook, 5, 0, 1);
        Animation.getFadeTransition().setOnFinished(e->{
            Animation.getFadeTransition().stop();
        });

        //Initialize keyboard click for scene
        scene.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.S) {
                Animation.fadeTransition(pane, 1, 1, 0);
                Animation.getFadeTransition().setOnFinished(event -> {
                    Animation.getFadeTransition().stop();
                });
            }
        });

        pane.getChildren().addAll(viewOpenedBook);
        scene.setFill(Color.BLACK);
        scene.getStylesheets().add(InLectureHall.class.getResource("../../style.css").toExternalForm());
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
    }
}
