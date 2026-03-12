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
                    return true;
                }

                counts[i] += 2;
            }
        }

        return false;
    }

    private static int tileToIndex(Tile t) {

        switch (t.getSuit()) {

            case CHARACTER:
                return t.getRank() - 1;

            case BAMBOO:
                return 9 + (t.getRank() - 1);

            case DOT:
                return 18 + (t.getRank() - 1);

            case WIND:
                return 27 + (t.getRank() - 1);

            case DRAGON:
                return 31 + (t.getRank() - 1);
        }

        throw new IllegalArgumentException("Invalid tile");
    }

    private static int[] buildCounts(List<Tile> tiles) {

        int[] counts = new int[34];

        for (Tile t : tiles) {
            counts[tileToIndex(t)]++;
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

        // ----- try triplets -----
        if (counts[i] >= 3) {

            counts[i] -= 3;

            if (canFormSets(counts)) {
                return true;
            }

            counts[i] += 3;
        }

        // ----- try sequences -----
        if (i <= 24 && (i % 9) <= 6) {

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
}