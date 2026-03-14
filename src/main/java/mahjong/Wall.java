package mahjong;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static mahjong.Tile.SORT_BY_ORDER;

public class Wall {
    private final List<Tile> tiles;
    public static final int MAX_WALL_SIZE = 34;

    public Wall() {
        this.tiles = new ArrayList<>();
    }

    public Wall(List<Tile> tiles) {
        this.tiles = tiles;
    }

    public List<Tile> getTiles() {
        return tiles;
    }

    public boolean isFull() { return tiles.size() == MAX_WALL_SIZE; }

    public void discardTile(Tile t) {
        tiles.remove(t);
    }

    @Override
    public String toString(){
        return tiles.toString();
    }
}

