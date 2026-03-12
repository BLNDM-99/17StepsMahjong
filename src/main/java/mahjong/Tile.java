package mahjong;

import java.util.*;

public class Tile {

    public enum Suit{
        CHARACTER(0),
        BAMBOO(9),
        DOT(18),
        WIND(27),
        DRAGON(31);

        private final int sortingValue; //used to determine how tiles should be sorted

        Suit(int sortingValue){
            this.sortingValue = sortingValue;
        }

        public int getSortingValue(){
            return this.sortingValue;
        }
    }

    private final Suit suit;
    private final int rank;
    private final int sortingValue;
    private boolean isDora;
    private final boolean isRed;

    public Tile(Suit suit, int rank){
        this.suit = suit;
        this.rank = rank;
        this.sortingValue = suit.getSortingValue() + rank; //used to sort tiles
        this.isRed = false;
        this.isDora = false;
    }

    //Getters
    public Suit getSuit() { return suit; }
    public int getRank() { return rank;}
    public int getSortingValue() { return sortingValue; }
    public boolean isDora() { return isDora; }
    public boolean isRed() { return isRed; }

    //Setters
    public void setDora(boolean dora) { isDora = dora; }

    @Override
    public String toString() {
        switch (suit) {
            case CHARACTER: case BAMBOO: case DOT:
                return rank + " OF " + suit;
            case WIND:
                switch(rank) {
                    case 1: return "EAST WIND";
                    case 2: return "SOUTH WIND";
                    case 3: return "WEST WIND";
                    case 4: return "NORTH WIND";
                }
                break;
            case DRAGON:
                switch(rank) {
                    case 1: return "WHITE DRAGON";
                    case 2: return "GREEN DRAGON";
                    case 3: return "RED DRAGON";
                }
                break;
        }
        return "UNKNOWN TILE";
    }

    //Used to sort by order (characters first, then bamboo, then dot, then wind, then dragon)
    public static final Comparator<Tile> SORT_BY_ORDER =
            Comparator.comparingInt(Tile::getSortingValue);
}
