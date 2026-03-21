package mahjong;

import java.util.*;

public class HandValidator {

    static int tripletsCount = 0;
    static int sequencesCount = 0;

    public static boolean isWinningHand(List<Tile> tiles) {

        int[] counts = buildCounts(tiles);

        // try every possible pair
        for (int i = 0; i < 34; i++) {

            if (counts[i] >= 2) {

                Tile temp = new Tile(i); //the value of the tile that produces the valid pair. 0 - 1 of character, 1 - 2 of character, etc.
                counts[i] -= 2;

                if (canFormSets(counts)) {
                    System.out.println("Valid hand!");
                    System.out.println("Valid pair was: " + temp + ", " + temp);
                    tripletsCount = 0;
                    sequencesCount = 0;
                    return true;
                }

                counts[i] += 2;
            }
        }
        if (canFormSevenPairs(counts)){
            System.out.println("Valid seven pairs!");
            return true;
        }
        if (canFormThirteenOrphans(counts)){
            System.out.println("Valid thirteen orphans!");
            return true;
        }

        System.out.println("Not valid");
        return false;
    }

    private static int[] buildCounts(List<Tile> tiles) {

        int[] counts = new int[34];

        for (Tile t : tiles) {
            counts[t.getSortingValue()]++;
        }

        return counts;
    }

    private static boolean canFormSets(int[] counts) {

        int i;

        // find first tile that still exists
        for (i = 0; i < 34; i++) {
            if (counts[i] > 0) break;
        }

        if (i == 34) {
            return true; // all tiles used
        }

        // try triplets
        if (counts[i] >= 3) {

            counts[i] -= 3;

            if (canFormSets(counts)) {
                tripletsCount++;
                System.out.println("Triplets: " + tripletsCount);
                return true;
            }

            counts[i] += 3;
        }

        // try sequences
        if (i <= 24 && (i % 9) <= 6) { //only check if it's between 1-7 inclusive, because you can't check 8-9-10 or 9-10-11

            if (counts[i+1] > 0 && counts[i+2] > 0) {

                counts[i]--;
                counts[i+1]--;
                counts[i+2]--;

                if (canFormSets(counts)) {
                    sequencesCount++;
                    System.out.println("Sequences " + sequencesCount);
                    return true;
                }

                counts[i]++;
                counts[i+1]++;
                counts[i+2]++;
            }
        }
        return false;
    }

    //Will potentially be used for pinfu
    protected static boolean canFormOnlySequences(int[] counts) {

        int i;

        // find first tile that still exists
        for (i = 0; i < 34; i++) {
            if (counts[i] > 0) break;
        }

        if (i == 34) {
            return true; // all tiles used
        }

        // try sequences
        if (i <= 24 && (i % 9) <= 6) { //only check if it's between 1-7 inclusive, because you can't check 8-9-10 or 9-10-11

            if (counts[i+1] > 0 && counts[i+2] > 0) {

                counts[i]--;
                counts[i+1]--;
                counts[i+2]--;

                if (canFormOnlySequences(counts)) {
                    return true;
                }

                counts[i]++;
                counts[i+1]++;
                counts[i+2]++;
            }
        }
        return false;
    }

    //Will most likely be used for all triplets yaku
    public static boolean canFormOnlyTriplets(int[] counts) {

        int i;

        // find first tile that still exists
        for (i = 0; i < 34; i++) {
            if (counts[i] > 0) break;
        }

        if (i == 34) {
            return true; // all tiles used
        }

        // try triplets
        if (counts[i] >= 3) {

            counts[i] -= 3;

            if (canFormOnlyTriplets(counts)) {
                return true;
            }

            counts[i] += 3;
        }

        return false;
    }

    protected static boolean canFormSevenPairs(int[] counts){
        int i;
        int pairCount = 0;

        for (i = 0; i < 34; i++){
            if (counts[i] == 2){
                pairCount++;
            }
        }
        return pairCount == 7;
    }

    protected static boolean canFormThirteenOrphans(int[] counts){
        int[] legalIndexes = {0, 8, 9, 17, 18, 26, 27, 28, 29, 30, 31, 32, 33};
        boolean foundPair = false;

        for (int li : legalIndexes){
            if (counts[li] < 1 || counts[li] >= 3){ //false if the tile isn't there or if it's a triplet or quad
                return false;
            }
            if (counts[li] == 2 && !foundPair){ //in thirteen orphans we can only have one pair
                foundPair = true;
                continue;
            }
            if (counts[li] == 2 && foundPair){ //if we find two of the same tile again after we already found our pair, the hand is not valid
                return false;
            }
        }
        return true; //if we made it this far then this is thirteen orphans
    }

    //Checks if 1 tile away from winning. Not sure if I'll actually use this for anything yet
    public static boolean isTenpai(List<Tile> tiles){
        int[] counts = buildCounts(tiles);

        for (int i = 0; i < 34; i++){
            counts[i]++;
            if (HandValidator.canFormSets(counts)){
                counts[i]--;
                return true;
            }
            counts[i]--;
        }
        return false;
    }
}