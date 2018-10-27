package nullproject.menu;

import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import nullproject.anim.Animation;
import nullproject.config.Config;
import nullproject.config.InitImage;
import nullproject.config.Status;
import nullproject.game.Game;
import nullproject.game.GameMenu;
import nullproject.game_scene.InLectureHall;

public class MainMenu {

    private static MainMenu ourInstance = new MainMenu(); //Class instance

    public static MainMenu getInstance() {
        return ourInstance;
    } //Static method for working with a class

    //Forbid to create an instance
    private MainMenu() {
    }

    public void start() {
        Stage stage = new Stage();
        Pane pane = new Pane();
        Scene scene = new Scene(pane, Config.WINDOW_WIDTH, Config.WINDOW_HEIGHT);

        ImageView viewMain = new ImageView(InitImage.imageMainMenu);

        //Set fixed width and height
        viewMain.fitWidthProperty().bind(scene.widthProperty());
        viewMain.fitHeightProperty().bind(scene.heightProperty());

        //Initialize keyboard click for scene
        scene.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.S) {
                Animation.fadeTransition(pane, 1, 1, 0);
                Animation.getFadeTransition().setOnFinished(event -> {
//                    InLectureHall.getInstance().start(stage);
//                    Game.getInstance().startGame(stage, Status.LEVEL_1);
                    GameMenu.getInstance().start(stage);
                });
            }
        });

        pane.getChildren().addAll(viewMain);
        scene.setFill(Color.BLACK);
        stage.centerOnScreen();
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }
}
