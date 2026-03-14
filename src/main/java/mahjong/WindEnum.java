package mahjong;

public enum WindEnum {
    EAST(27),
    SOUTH(28),
    WEST(29),
    NORTH(30);

    private final int yakuValue; //used for certain yakus

    WindEnum(int yakuValue){
        this.yakuValue = yakuValue;
    }

    public int getYakuValue(){
        return this.yakuValue;
    }
}
