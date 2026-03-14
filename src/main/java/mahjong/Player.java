package mahjong;

import java.util.List;

public class Player {
    private Hand hand;
    private Wall wall;
    private List<Tile> pool;
    private List<Tile> discardedTiles; //probably will use for furiten
    private int points;
}
