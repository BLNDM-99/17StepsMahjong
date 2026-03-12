package app;

import mahjong.*;
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

        Hand hand = new Hand();

        hand.drawTile(t1);
        hand.drawTile(t2);
        hand.drawTile(t3);
        hand.drawTile(t4);
        hand.drawTile(t5);

        System.out.println(hand);

        hand.sortHand();
        System.out.println(hand);
    }
}
