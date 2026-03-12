package mahjong;

public class Hand {
    private List<Tiles> tiles;

    public void drawTile(Tile t) {tiles.add(t); }
    public void discardTile(Tile t) { tiles.remove(t); }
    public boolean isFull() {
        int MAX_HAND_SIZE = 13;
        return tiles.size() == MAX_HAND_SIZE; }
}
