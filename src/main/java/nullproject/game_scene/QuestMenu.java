package nullproject.game_scene;

import javafx.animation.TranslateTransition;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import nullproject.anim.Animation;
import nullproject.config.Config;
import nullproject.config.InitImage;

public class QuestMenu {

    private static QuestMenu ourInstance = new QuestMenu(); //Class instance

    public static QuestMenu getInstance() {
        return ourInstance;
    } //Static method for working with a class

    //Forbid to create an instance
    private QuestMenu() {
    }

    public void start(Stage stage) {
        Pane pane = new Pane();
        Scene scene = new Scene(pane, Config.WINDOW_WIDTH, Config.WINDOW_HEIGHT);

        ImageView viewWelcomeToQuest = new ImageView(InitImage.imageInLectureHallWelcomeToQuest);
        viewWelcomeToQuest.setOpacity(1);

        //Set fixed width and height
        viewWelcomeToQuest.fitWidthProperty().bind(scene.widthProperty());
        viewWelcomeToQuest.fitHeightProperty().bind(scene.heightProperty());

        Rectangle rectangleTop = new Rectangle(0, 270, 960, 270);
        rectangleTop.setFill(Color.BLACK);

        Rectangle rectangleBottom = new Rectangle(0, 270, 960, 270);
        rectangleBottom.setFill(Color.RED);

        Animation.translateTransition(rectangleTop, 2, 0, 0, 270, 700);

        pane.getChildren().addAll(viewWelcomeToQuest, rectangleTop);
        scene.setFill(Color.BLACK);
        scene.getStylesheets().add(InLectureHall.class.getResource("../../style.css").toExternalForm());
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
    }
}
