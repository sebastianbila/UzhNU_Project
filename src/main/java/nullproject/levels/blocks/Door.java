package nullproject.levels.blocks;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import nullproject.anim.SpriteAnimation;
import nullproject.config.GameConfigs;
import nullproject.game.Game;

public class Door extends Blocks {

    private Image blockDoor = new Image(getClass().getResourceAsStream("../../../scene/game/door.png"));
    private ImageView viewDoor = new ImageView(blockDoor);

    public Door(int x, int y) {

        viewDoor.setFitWidth(GameConfigs.BLOCK_SIZE + 1);
        viewDoor.setFitHeight(GameConfigs.BLOCK_SIZE);
        setTranslateX(x);
        setTranslateY(y);

        getChildren().add(viewDoor);
        Game.doors.add(this);
        Game.gameRoot.getChildren().add(this);

    }
}
