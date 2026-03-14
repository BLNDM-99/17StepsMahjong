package app;

import mahjong.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static mahjong.Tile.SORT_BY_ORDER;
//https://www.reddit.com/r/kaiji/comments/fosyx7/one_poker_and_17_steps_rules/

public class Main {
    public static void main(String[] args){
        /*
        Tile t1 = new Tile(Tile.Suit.CHARACTER, 1);
        Tile t2 = new Tile(Tile.Suit.WIND, 3);
        Tile t3 = new Tile(Tile.Suit.DRAGON, 3);
        Tile t4 = new Tile(Tile.Suit.DRAGON, 4); //dragon of rank 4 doesn't exist
        Tile t5 = new Tile(Tile.Suit.CHARACTER, 5);

        System.out.println(t1);
        System.out.println(t2);
        System.out.println(t3);
        System.out.println(t4);
        System.out.println(t5);
        */

        /*
        Hand hando = new Hand();

        hando.drawTile(t1);
        hando.drawTile(t2);
        hando.drawTile(t3);
        hando.drawTile(t4);
        hando.drawTile(t5);

        System.out.println(hando);

        hando.sortHand();
        System.out.println(hando);
         */

        Hand hand = new Hand();

        hand.getTiles().add(new Tile(Tile.Suit.CHARACTER,2));
        hand.getTiles().add(new Tile(Tile.Suit.CHARACTER,2)); // pair

        hand.getTiles().add(new Tile(Tile.Suit.CHARACTER,2));
        hand.getTiles().add(new Tile(Tile.Suit.CHARACTER,3));
        hand.getTiles().add(new Tile(Tile.Suit.CHARACTER,4));

        hand.getTiles().add(new Tile(Tile.Suit.BAMBOO,5));
        hand.getTiles().add(new Tile(Tile.Suit.BAMBOO,6));
        hand.getTiles().add(new Tile(Tile.Suit.BAMBOO,7));

        hand.getTiles().add(new Tile(Tile.Suit.DOT,3));
        hand.getTiles().add(new Tile(Tile.Suit.DOT,3));
        hand.getTiles().add(new Tile(Tile.Suit.DOT,3));

        hand.getTiles().add(new Tile(Tile.Suit.DOT,6));
        hand.getTiles().add(new Tile(Tile.Suit.DOT,7));
        hand.getTiles().add(new Tile(Tile.Suit.DOT,8));

        //System.out.println(HandValidator.isWinningHand(hand));
        HandValidator.isWinningHand(hand.getTiles());
        YakuChecker checker = new YakuChecker(hand);
        System.out.println(checker.isTanyao());

        hand.getTiles().remove(13);
        HandValidator.isTenpai(hand.getTiles());

        List<Tile> fullSet = TileFactory.createFullSet();
        fullSet.sort(SORT_BY_ORDER);
        System.out.println(fullSet);

        Collections.shuffle(fullSet);
        System.out.println(fullSet);

        Wall w = new Wall();
        w.getTiles().add(new Tile(Tile.Suit.DRAGON, 3));
        System.out.println(w);

        Wall w2 = new Wall(fullSet.subList(0, 34));
        System.out.println(w2);

        Wall w3 = new Wall(fullSet.subList(34, 34 + 34));
        System.out.println(w3);

        Player p1 = new Player();
        p1.setWall(w2);
        System.out.println("PLAYER 1 WALL: " + w2);

        Player p2 = new Player();
        p2.setWall(w3);
        System.out.println("PLAYER 2 WALL: " + w3);
        System.out.println(w3.getTiles().get(1));

        p1.selectTilesFromWallConsoleVersion();
        System.out.println(p1.getHand());

        YakuChecker checkerForPlayer = new YakuChecker(p1);
        System.out.println(checkerForPlayer.isTanyao());
        System.out.println(checkerForPlayer.isYakuhai());
    }
}
