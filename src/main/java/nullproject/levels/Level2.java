package nullproject.levels;

import nullproject.config.GameConfigs;
import nullproject.levels.blocks.Blocks;
import nullproject.levels.blocks.Door;
import nullproject.levels.blocks.Text;
import nullproject.player.Player;

public class Level2 {

    static String[] LEVEL2 = new String[]{
            "0,1,1,1,1,1,1,1,1,1,1,1",
            "1,1,1,1,1,1,1,1,1,1,1,1",
            "0,0,0,0,0,5,5,0,0,0,0,1",
            "0,0,0,0,0,5,5,0,0,0,0,1",
            "9,0,0,0,0,0,0,0,0,0,0,1",
            "0,0,0,0,0,0,0,0,0,0,0,1",
            "0,0,0,0,0,0,0,0,0,0,0,1",
            "0,0,0,0,0,0,0,0,0,0,0,1", "0,0,0,0,0,0,0,0,0,0,0,1", "0,0,0,0,0,0,0,0,0,0,0,1", "0,0,0,0,0,0,0,0,0,0,0,1", "0,0,0,0,0,0,0,0,0,0,0,1", "0,0,0,0,0,0,9,0,0,0,0,1", "0,1,1,1,1,1,1,1,1,1,1,1",};

    public static String[][] levels = new String[][]{LEVEL2};

    public static void level2() {
        for (int i = 0; i < GameConfigs.level1Length; i++) {
            String line = Level2.levels[GameConfigs.lvl][i];
            for (int j = 0; j < line.length(); j++) {
                switch (line.charAt(j)) {
                    case '0':
                        break;
                    case '1':
                        Blocks platform = new Blocks(Blocks.BlockType.INVISIBLE_BLOCK, j * GameConfigs.BLOCK_SIZE, i * GameConfigs.BLOCK_SIZE);
                        break;
                    case '9':
                        Door exit = new Door(j * GameConfigs.BLOCK_SIZE, i * GameConfigs.BLOCK_SIZE);
                        break;
                    case '5':
                        Text textOnDesk = new Text(j * GameConfigs.BLOCK_SIZE, i * GameConfigs.BLOCK_SIZE);
                        break;
                }
            }
        }
    }
}
