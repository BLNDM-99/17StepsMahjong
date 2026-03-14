package mahjong;

import java.util.*;

public class Player {
    private final Hand hand;
    private Wall wall;
    private List<Tile> discardedTiles; //probably will use for furiten
    private int points;

    public Player(){
        this.hand = new Hand();
        this.wall = new Wall();
        this.points = 0;
    }

    //Getters
    public Hand getHand() { return hand; }
    public Wall getWall() { return wall; }
    public List<Tile> getDiscardedTiles() { return discardedTiles; }
    public int getPoints() { return points; }

    //Setters
    public void setWall(Wall wall) { this.wall = wall; }
    public void setDiscardedTiles(List<Tile> discardedTiles) { this.discardedTiles = discardedTiles; }
    public void setPoints(int points) { this.points = points; }

    public void selectTilesFromWallConsoleVersion() {
        Scanner scanner = new Scanner(System.in);
        List<Integer> selectedIndexes = new ArrayList<>();
        int MAX_SELECTION = 13;

        // Loop until user selects 13 tiles
        while (selectedIndexes.size() < MAX_SELECTION) {
            System.out.println("Wall:");
            for (int i = 0; i < wall.getTiles().size(); i++) {
                System.out.println(i + ": " + wall.getTiles().get(i));
            }
            System.out.println("Select a tile index (" + (MAX_SELECTION - selectedIndexes.size()) + " remaining):");

            int index;
            try {
                index = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Please enter a valid integer.");
                scanner.next(); // clear invalid input
                continue;
            }

            // Validate index
            if (index < 0 || index >= wall.getTiles().size()) {
                System.out.println("Invalid index. Try again.");
                continue;
            }
            if (selectedIndexes.contains(index)) {
                System.out.println("You already selected this tile. Try again.");
                continue;
            }

            // Accept selection
            selectedIndexes.add(index);
            System.out.println("Selected: " + wall.getTiles().get(index));
        }

        // Transfer selected tiles to hand
        List<Tile> selectedTiles = new ArrayList<>();
        // Remove tiles from wall in descending order to avoid shifting indexes
        selectedIndexes.sort(Collections.reverseOrder());
        for (int i : selectedIndexes) {
            Tile t = wall.getTiles().remove(i);
            selectedTiles.add(t);
        }

        // Add tiles to hand
        for (Tile t : selectedTiles) {
            hand.drawTile(t);
        }

        // Sort hand
        hand.sortHand();

        System.out.println("Hand selection complete!");
    }
}
