package mahjong;

import java.util.ArrayList;
import java.util.List;

public class TileFactory {
    public static List<Tile> createFullSet(){
        List<Tile> tiles = new ArrayList<>();

        for (int i = 1; i <= 9; i++){
            for (int j = 0; j < 4; j++) {
                if (i == 5 && j == 0) {
                    tiles.add(new Tile(Tile.Suit.CHARACTER, i, true));
                    tiles.add(new Tile(Tile.Suit.BAMBOO, i, true));
                    tiles.add(new Tile(Tile.Suit.DOT, i, true));
                }
                else {
                    tiles.add(new Tile(Tile.Suit.CHARACTER, i));
                    tiles.add(new Tile(Tile.Suit.BAMBOO, i));
                    tiles.add(new Tile(Tile.Suit.DOT, i));
                }
                if (i <= 4){
                    tiles.add(new Tile(Tile.Suit.WIND, i));
                }
                if (i <= 3){
                    tiles.add(new Tile(Tile.Suit.DRAGON, i));
                }
            }
        }
        return tiles;
    }
}
