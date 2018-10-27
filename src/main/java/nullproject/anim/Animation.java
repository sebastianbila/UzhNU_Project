package nullproject.anim;

import javafx.animation.FadeTransition;
import javafx.animation.ParallelTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.Node;
import javafx.util.Duration;

public class Animation {

    private static FadeTransition fadeTransition = new FadeTransition(); //Opacity animation
    private static TranslateTransition translateTransition = new TranslateTransition(); //Translate animation
    private static ScaleTransition scaleTransition = new ScaleTransition(); //Scale animation

    public static void fadeTransition(Object node, int seconds, int fromValue, int toValue) {
        fadeTransition.setDuration(Duration.seconds(seconds));
        fadeTransition.setFromValue(fromValue);
        fadeTransition.setToValue(toValue);
        fadeTransition.setNode((Node) node);
        fadeTransition.play();
    }

    public static void translateTransition(Object node, int seconds, int fromX, int toX, int fromY, int toY) {
        translateTransition.setDuration(Duration.seconds(seconds));
        translateTransition.setFromX(fromX);
        translateTransition.setToX(toX);
        translateTransition.setFromY(fromY);
        translateTransition.setToY(toY);
        translateTransition.setNode((Node) node);
        translateTransition.play();
    }

    public static void scaleTransition(Object node, double seconds, int fromX, int toX, int fromY, int toY) {
        scaleTransition.setDuration(Duration.seconds(seconds));
        scaleTransition.setFromX(fromX);
        scaleTransition.setToX(toX);
        scaleTransition.setFromY(fromY);
        scaleTransition.setToY(toY);
        scaleTransition.setNode((Node) node);
        scaleTransition.play();
    }

    public static FadeTransition getFadeTransition() {
        return fadeTransition;
    }

    public static TranslateTransition getTranslateTransition() {
        return translateTransition;
    }

    public static ScaleTransition getScaleTransition() {
        return scaleTransition;
    }
}
