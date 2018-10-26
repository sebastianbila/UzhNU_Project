package nullproject;

import javafx.application.Application;
import javafx.stage.Stage;
import nullproject.menu.MainMenu;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        MainMenu.getInstance().start();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
