package mahjong;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static mahjong.Tile.SORT_BY_ORDER;

public class Hand {
    private final List<Tile> tiles;
    private int winningTileIndex;
    private int han;
    private int fu;
    public static final int MAX_HAND_SIZE = 13;

    public Hand() {
        this.tiles = new ArrayList<>();
        this.han = 0;
        this.fu = 0;
    }

    public List<Tile> getTiles() {
        return tiles;
    }

    public int getWinningTileIndex() {
        return winningTileIndex;
    }

    public void setWinningTileIndex(int winningTileIndex) {
        this.winningTileIndex = winningTileIndex;
    }

    public boolean isFull() { return tiles.size() == MAX_HAND_SIZE; }

    public void drawTile(Tile t) {
        if (!isFull()) {
            tiles.add(t);
        }
    }

    public void discardTile(Tile t) {
        tiles.remove(t);
    }

    public void sortHand(){
        this.tiles.sort(SORT_BY_ORDER);
    }

    @Override
    public String toString(){
        return tiles.toString();
    }
}
