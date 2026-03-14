package mahjong;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class YakuChecker {
    /*
    0 - 8 characters
    9 - 17 bamboo
    18 - 26 dot
    27 - 30 wind (east, south, west, north)
    31 - 33 dragon (white, green, red)
     */

    private Hand hand;
    private int[] counts;
    private WindEnum playerWind;
    private WindEnum prevailingWind;
    private Tile winningTile;

    public YakuChecker(Player player){
        this.hand = player.getHand();
        this.counts = buildCounts(hand.getTiles());
        this.playerWind = player.getSeat();
    }

    public YakuChecker(Hand hand){
        this.hand = hand;
        this.counts = buildCounts(hand.getTiles());
        this.playerWind = WindEnum.EAST; //placeholder
    }

    //Reused code, essentially depicts the hand as an array
    private static int[] buildCounts(List<Tile> tiles) {

        int[] counts = new int[34];

        for (Tile t : tiles) {
            counts[t.getSortingValue()]++;
        }

        return counts;
    }

    public boolean isTanyao() {
        //Illegal indexes meaning terminals or honors. There are fewer illegal indexes than legal so we check for illegal indexes
        int[] illegalIndexes = {0, 8, 9, 17, 18, 26, 27, 28, 29, 30, 31, 32, 33};

        for (int i : illegalIndexes){
            if (counts[i] > 0){
                return false;
            }
        }

        return true;
    }

    public boolean isYakuhai() {
        //Legal indexes meaning player's wind, round's wind, or dragons.
        int[] legalIndexes = {playerWind.getYakuValue(), 31, 32, 33};

        for (int i : legalIndexes){
            if (counts[i] >= 3){
                return true;
            }
        }

        return false;
    }

    /*
    public boolean isPinfu() {

    }
    */

    //----------------------------------------------------YAKUMAN-------------------------------------------------------

    //English name: all green
    public boolean isRyuuiisou() {
        Set<Integer> legalIndexes = new HashSet<>();
        legalIndexes.add(10); legalIndexes.add(11); legalIndexes.add(12);
        legalIndexes.add(14); legalIndexes.add(16); legalIndexes.add(32);

        for (int i = 0; i < counts.length; i++) {
            if (counts[i] > 0 && !legalIndexes.contains(i)) return false;
        }
        return true;
    }
}
