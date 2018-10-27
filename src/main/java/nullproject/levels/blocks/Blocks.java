package nullproject.levels.blocks;

import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import nullproject.config.GameConfigs;
import nullproject.game.Game;

public class Blocks extends Pane {
    Image blocksImg = new Image(getClass().getResourceAsStream("../../../scene/game/tiles.png"));
    ImageView block;

    public Blocks() {
    }

    public enum BlockType {
        INVISIBLE_BLOCK
    }

    public Blocks(BlockType blockType, int x, int y) {
        block = new ImageView(blocksImg);
        block.setFitWidth(GameConfigs.BLOCK_SIZE);
        block.setFitHeight(GameConfigs.BLOCK_SIZE);
        setTranslateX(x);
        setTranslateY(y);

        switch (blockType) {
            case INVISIBLE_BLOCK:
                block.setViewport(new Rectangle2D(0, 0, 32, 32));
                break;
        }
        getChildren().add(block);
        Game.platforms.add(this);
        Game.gameRoot.getChildren().add(this);

    }
}
