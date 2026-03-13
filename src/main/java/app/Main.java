package app;

import mahjong.*;

import java.util.ArrayList;
import java.util.List;

import static mahjong.Tile.SORT_BY_ORDER;
//https://www.reddit.com/r/kaiji/comments/fosyx7/one_poker_and_17_steps_rules/

public class Main {
    public static void main(String[] args){
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

        Hand hando = new Hand();

        hando.drawTile(t1);
        hando.drawTile(t2);
        hando.drawTile(t3);
        hando.drawTile(t4);
        hando.drawTile(t5);

        System.out.println(hando);

        hando.sortHand();
        System.out.println(hando);

        List<Tile> hand = new ArrayList<>();

        hand.add(new Tile(Tile.Suit.CHARACTER,1));
        hand.add(new Tile(Tile.Suit.CHARACTER,1)); // pair

        hand.add(new Tile(Tile.Suit.CHARACTER,1));
        hand.add(new Tile(Tile.Suit.CHARACTER,2));
        hand.add(new Tile(Tile.Suit.CHARACTER,3));

        hand.add(new Tile(Tile.Suit.BAMBOO,5));
        hand.add(new Tile(Tile.Suit.BAMBOO,6));
        hand.add(new Tile(Tile.Suit.BAMBOO,7));

        hand.add(new Tile(Tile.Suit.DOT,3));
        hand.add(new Tile(Tile.Suit.DOT,3));
        hand.add(new Tile(Tile.Suit.DOT,3));

        hand.add(new Tile(Tile.Suit.DOT,6));
        hand.add(new Tile(Tile.Suit.DOT,7));
        hand.add(new Tile(Tile.Suit.DOT,8));

        //System.out.println(HandValidator.isWinningHand(hand));
        HandValidator.isWinningHand(hand);

        List<Tile> fullSet = TileFactory.createFullSet();
        fullSet.sort(SORT_BY_ORDER);
        System.out.println(fullSet);
    }
}
