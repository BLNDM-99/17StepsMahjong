package app;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
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
            Rectangle front = new Rectangle(40, 40.0*(4.0/3)); // width 40, height 60
            Rectangle rect = new Rectangle(40, 40.0*(4.0/3)); // width 40, height 60
            // load from resources folder on classpath

            String filename = tile.isRed() ? tile.getSortingValue() + "red.png" : tile.getSortingValue() + ".png";
            Image tileImage = new Image(getClass().getResource("/tiles/" + filename).toExternalForm());
            ImageView iv = new ImageView(tileImage);
            iv.setFitWidth(100);     // width you want
            iv.setFitHeight(150);    // height you want
            iv.setPreserveRatio(true);
            iv.setSmooth(true);      // smooth scaling
            root.getChildren().add(iv);
            Image tileImageFront = new Image(getClass().getResource("/tiles/Front.png").toExternalForm());
            ImageView iv1 = new ImageView(tileImageFront);
            iv1.setFitWidth(100);     // width you want
            iv1.setFitHeight(150);    // height you want
            iv1.setPreserveRatio(true);
            iv1.setSmooth(true);      // smooth scaling
            root.getChildren().add(iv1);

            // Label with tile info
            Label label = new Label(""+ tile.getSortingValue()); // make sure Tile.toString() shows suit+rank
            label.setStyle("-fx-font-size: 12; -fx-font-weight: bold;");

            // StackPane to overlay label on rectangle
            StackPane stack = new StackPane(iv1, iv);

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