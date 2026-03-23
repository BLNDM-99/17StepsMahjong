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
        return hand.getFu == 20;
    }
    */

    //English name: seven pairs
    public boolean isChiitoitsu() {
        HandValidator hv = new HandValidator();
        return HandValidator.canFormSevenPairs(hand);
    }

    //English name: all triplets
    public boolean isToitoi() {
        for (int i = 0; i < 34; i++) {
            if (counts[i] >= 2) {
                counts[i] -= 2;

                if (HandValidator.canFormOnlyTriplets(counts)) {
                    counts[i] += 2;
                    return true;
                }

                counts[i] += 2;
            }
        }
        return false;
    }

    //English name: full flush
    public boolean isChinitsu(){
        //this function assumes the hand is sorted
        Tile firstTile = hand.getTiles().get(0);

        if (firstTile.getSuit() == Tile.Suit.WIND || firstTile.getSuit() == Tile.Suit.DRAGON){
            return false;
        }

        for (Tile t : hand.getTiles()){
            if (t.getSuit() != firstTile.getSuit()){
                return false;
            }
        }
        return true;
    }

    public boolean isHonitsu(){
        //this function assumes the hand is sorted
        Tile firstTile = hand.getTiles().get(0);

        if (firstTile.getSuit() == Tile.Suit.WIND || firstTile.getSuit() == Tile.Suit.DRAGON){
            return false;
        }

        for (Tile t : hand.getTiles()){
            if (t.getSuit() == Tile.Suit.WIND || t.getSuit() == Tile.Suit.DRAGON){
                return true;
            }
            else if (t.getSuit() != firstTile.getSuit()){
                return false;
            }
        }
        return false;
    }

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

    //English name: thirteen orphans single wait
    public boolean isKokushiMusou() {
        int[] tempCounts = counts.clone();
        tempCounts[hand.getWinningTileIndex()]--;

        int[] legalIndexes = {0, 8, 9, 17, 18, 26, 27, 28, 29, 30, 31, 32, 33};

        for (int li : legalIndexes){
            if (tempCounts[li] == 2){
                System.out.println("Is single wait thirteen orphans");
                return true;
            }
        }
        System.out.println("Is not single wait thirteen orphans");
        return false;
    }

    //English name: big three dragons
    public boolean isDaisangen() {
        for (int i = 31; i <= 33; i++){
            if (counts[i] < 3){
                return false;
            }
        }
        return true;
    }

    //English name: small four winds
    public boolean isShousuushii() {
        int[] tempCounts = counts.clone();

        for (int i = 27; i <= 30; i++){
            if (tempCounts[i] == 4){
                tempCounts[i] = 3;
            }
        }
        return tempCounts[27] + tempCounts[28] + tempCounts[29] + tempCounts[30] == 11;
    }

    //----------------------------------------------------DOUBLE YAKUMAN-------------------------------------------------------

    //English name: thirteen orphans 13-sided wait
    public boolean isKokushiJuusanmen() {
        //tbh this whole function might be necessary because if something is thirteen orphans but not kokushi musou
        //then by process of elimination it's kokushi juusanmen
        int[] tempCounts = counts.clone();
        tempCounts[hand.getWinningTileIndex()]--;

        int[] legalIndexes = {0, 8, 9, 17, 18, 26, 27, 28, 29, 30, 31, 32, 33};

        for (int li : legalIndexes){
            if (tempCounts[li] != 1){
                System.out.println("Is not 13-sided thirteen orphans");
                return false;
            }
        }
        System.out.println("Is 13-sided thirteen orphans");
        return true;
    }

    //English name: big four winds
    public boolean isDaisuushii() {
        int[] tempCounts = counts.clone();

        for (int i = 27; i <= 30; i++){
            if (tempCounts[i] == 4){
                tempCounts[i] = 3;
            }
        }
        return tempCounts[27] + tempCounts[28] + tempCounts[29] + tempCounts[30] == 12;
    }
}
