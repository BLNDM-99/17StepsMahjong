package mahjong;

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
    private boolean isUraDora;
    private final boolean isRed;

    public Tile(Suit suit, int rank, boolean isRed){
        this.suit = suit;
        this.rank = rank;
        this.sortingValue = suit.getSortingValue() + rank; //used to sort tiles
        this.isRed = isRed;
        this.isDora = false;
        this.isUraDora = false;
    }

    //Getters
    public Suit getSuit() { return suit; }
    public int getRank() {return rank;}
    public int getSortingValue() { return sortingValue; }
    public boolean isDora() { return isDora; }
    public boolean isUraDora() { return isUraDora; }
    public boolean isRed() { return isRed; }

    //Setters
    public void setDora(boolean dora) { isDora = dora; }
    public void setUraDora(boolean uraDora) { isUraDora = uraDora; }
}
