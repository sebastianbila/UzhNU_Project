package nullproject.anim;

import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.Node;
import javafx.util.Duration;

public class Animation {

    private static FadeTransition fadeTransition = new FadeTransition(); //Opacity animation
    private static TranslateTransition translateTransition = new TranslateTransition(); //Translate animation

    public static void fadeTransition(Object node, int seconds, int fromValue, int toValue){
        fadeTransition.setDuration(Duration.seconds(seconds));
        fadeTransition.setFromValue(fromValue);
        fadeTransition.setToValue(toValue);
        fadeTransition.setNode((Node) node);
        fadeTransition.play();
    }

    public static FadeTransition getFadeTransition() {
        return fadeTransition;
    }
}
