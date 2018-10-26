package nullproject;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class InLectureHall {

    private final int WINDOW_WIDTH = 960 ;
    private final int WINDOW_HEIGHT = 540;

    public void start(){
        Stage stage = new Stage();
        Pane pane = new Pane();
        Scene scene = new Scene(pane, WINDOW_WIDTH, WINDOW_HEIGHT);

        stage.setScene(scene);
        stage.show();
    }
}
