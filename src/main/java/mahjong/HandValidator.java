package mahjong;

import java.util.*;

public class HandValidator {

    public static boolean isWinningHand(List<Tile> tiles) {

        int[] counts = buildCounts(tiles);

        // try every possible pair
        for (int i = 0; i < 34; i++) {

            if (counts[i] >= 2) {

                counts[i] -= 2;

                if (canFormSets(counts)) {
                    System.out.println("Valid hand!");
                    return true;
                }

                counts[i] += 2;
            }
        }
        if (canFormSevenPairs(counts)){
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
                    return true;
                }

                counts[i]++;
                counts[i+1]++;
                counts[i+2]++;
            }
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

    //Checks if 1 tile away from winning. Not sure if I'll actually use this for anything yet
    public static boolean isTenpai(List<Tile> tiles){
        int counts[] = buildCounts(tiles);

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