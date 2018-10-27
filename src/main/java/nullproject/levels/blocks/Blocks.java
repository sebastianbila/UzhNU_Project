package nullproject.levels.blocks;

import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import nullproject.config.GameConfigs;
import nullproject.game.Game;

public class Blocks extends Pane {
    Image blocksImg = new Image(getClass().getResourceAsStream("../../../scene/game/door.png"));
    ImageView block;

    public Blocks() {
    }

    public enum BlockType {
        DOOR, PLATFORM, STAR, DARK_PLATFORM
    }

    public Blocks(BlockType blockType, int x, int y) {
        block = new ImageView(blocksImg);
        block.setFitWidth(GameConfigs.BLOCK_SIZE + 1);
        block.setFitHeight(GameConfigs.BLOCK_SIZE);
        setTranslateX(x);
        setTranslateY(y);

        switch (blockType) {
            case DOOR:
                block.setViewport(new Rectangle2D(0, 0, 32, 32));
                break;
            case PLATFORM:
                block.setViewport(new Rectangle2D(128, 0, 32, 32));
                break;
            case STAR:
                block.setViewport(new Rectangle2D(224, 32, 32, 32));
                break;
            case DARK_PLATFORM:
                block.setViewport(new Rectangle2D(192, 0, 32, 32));
                break;
        }
        getChildren().add(block);
        Game.platforms.add(this);
        Game.gameRoot.getChildren().add(this);

    }
}
