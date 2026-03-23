package app;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import mahjong.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static mahjong.Tile.SORT_BY_ORDER;

public class MainFX extends Application {

    @Override
    public void start(Stage primaryStage) {

        // Create the wall tiles
        List<Tile> fullSet = TileFactory.createFullSet();
        fullSet.sort(SORT_BY_ORDER);
        Collections.shuffle(fullSet);

        Wall wall = new Wall(new ArrayList<>(fullSet.subList(0, 34)));
        Hand playerHand = new Hand();

        // Layout containers
        VBox root = new VBox(10);

        HBox row1 = new HBox(5);
        HBox row2 = new HBox(5);
        HBox handRow = new HBox(5);

        for (int i = 0; i < wall.getTiles().size(); i++) {
            Tile tile = wall.getTiles().get(i);

            // Determine filename (red vs normal)
            String filename = tile.isRed()
                    ? tile.getSortingValue() + "red.png"
                    : tile.getSortingValue() + ".png";

            // Load tile image
            Image tileImage = new Image(
                    getClass().getResource("/tiles/" + filename).toExternalForm()
            );

            ImageView tileView = new ImageView(tileImage);
            tileView.setFitWidth(60);
            tileView.setFitHeight(90);
            tileView.setPreserveRatio(true);
            tileView.setSmooth(true);

            // Optional front overlay
            Image frontImage = new Image(
                    getClass().getResource("/tiles/Front.png").toExternalForm()
            );

            ImageView frontView = new ImageView(frontImage);
            frontView.setFitWidth(60);
            frontView.setFitHeight(90);
            frontView.setPreserveRatio(true);
            frontView.setSmooth(true);

            // Stack tile + overlay
            StackPane tileStack = new StackPane(frontView, tileView);

            // CLICK HANDLER → move tile to hand
            tileStack.setOnMouseClicked(e -> {
                if (handRow.getChildren().size() < 14) {

                    // safely remove from current parent
                    if (tileStack.getParent() instanceof Pane) {
                        Pane parent = (Pane) tileStack.getParent();
                        parent.getChildren().remove(tileStack);
                    }

                    wall.getTiles().remove(tile);
                    playerHand.getTiles().add(tile);
                    playerHand.sortHand();
                    // add to hand
                    rebuildHandUI(handRow, playerHand);
                }
            });

            // Add to wall rows
            if (i < 17) {
                row1.getChildren().add(tileStack);
            } else {
                row2.getChildren().add(tileStack);
            }
        }

        root.getChildren().addAll(row1, row2, handRow);

        Scene scene = new Scene(root, 1100, 300);
        primaryStage.setTitle("Mahjong Wall");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void rebuildHandUI(HBox handRow, Hand hand) {

        handRow.getChildren().clear();

        for (Tile t : hand.getTiles()) {

            String filename = t.isRed()
                    ? t.getSortingValue() + "red.png"
                    : t.getSortingValue() + ".png";

            Image img = new Image(getClass().getResource("/tiles/" + filename).toExternalForm());
            Image frontImage = new Image(
                    getClass().getResource("/tiles/Front.png").toExternalForm()
            );

            ImageView iv = new ImageView(img);
            iv.setFitWidth(60);
            iv.setFitHeight(90);
            iv.setPreserveRatio(true);
            iv.setSmooth(true);

            ImageView frontView = new ImageView(frontImage);
            frontView.setFitWidth(60);
            frontView.setFitHeight(90);
            frontView.setPreserveRatio(true);
            frontView.setSmooth(true);

            StackPane tileStack = new StackPane(frontView, iv);

            handRow.getChildren().addAll(tileStack);
        }
        System.out.println(hand);
        //this works but checking for tenpai doesn't work...
        if (hand.getTiles().size() == 14) {
            System.out.println(HandValidator.isWinningHand(hand));
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}