package nullproject.config;

import nullproject.levels.Level1;

public class GameConfigs {

    //Player position and block size
    public static final int PLAYER_SIZE = 45;
    public static final int BLOCK_SIZE = 32;

    public static int lvl = 0;
    public static int levelsWidth = Level1.levels[lvl][0].length() * BLOCK_SIZE;

    public static int level1Length = Level1.levels[lvl].length;

    public static int playerSize = 1;


}
