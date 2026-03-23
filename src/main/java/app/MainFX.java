package app;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import mahjong.Tile;
import mahjong.TileFactory;
import mahjong.Wall;

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

        Wall wall = new Wall(fullSet.subList(0, 34));

        // VBox to hold two rows
        VBox root = new VBox(5); // 5px spacing between rows

        // Split into two rows
        HBox row1 = new HBox(5);
        HBox row2 = new HBox(5);

        for (int i = 0; i < wall.getTiles().size(); i++) {
            Tile tile = wall.getTiles().get(i);

            // Create rectangle for the tile
            Rectangle rect = new Rectangle(40, 60); // width 40, height 60
            rect.setFill(Color.BEIGE);
            rect.setStroke(Color.BLACK);

            // Label with tile info
            Label label = new Label(""+ tile.getSortingValue()); // make sure Tile.toString() shows suit+rank
            label.setStyle("-fx-font-size: 12; -fx-font-weight: bold;");

            // StackPane to overlay label on rectangle
            StackPane stack = new StackPane(rect, label);

            // Add to appropriate row
            if (i < 17) {
                row1.getChildren().add(stack);
            } else {
                row2.getChildren().add(stack);
            }
        }

        root.getChildren().addAll(row1, row2);

        Scene scene = new Scene(root, 800, 200);
        primaryStage.setTitle("Mahjong Wall");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}