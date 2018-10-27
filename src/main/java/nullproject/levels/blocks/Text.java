package nullproject.levels.blocks;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import nullproject.config.GameConfigs;
import nullproject.game.Game;

public class Text extends Blocks {

    private Image blockDoor = new Image(getClass().getResourceAsStream("../../../scene/game/tiles.png"));
    private ImageView viewDoor = new ImageView(blockDoor);

    public Text(int x, int y) {

        viewDoor.setFitWidth(GameConfigs.BLOCK_SIZE + 1);
        viewDoor.setFitHeight(GameConfigs.BLOCK_SIZE);
        setTranslateX(x);
        setTranslateY(y);

        getChildren().add(viewDoor);
        Game.text.add(this);
        Game.gameRoot.getChildren().add(this);

    }
}
