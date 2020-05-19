import java.util.*;
import java.util.ArrayList;
import java.lang.Math;
public class UNOgame {
   static ArrayList<String> cards = new ArrayList<String>();
   static ArrayList<ArrayList<String> > players =  new ArrayList<ArrayList<String> >();
   static int nPlayers = 4;
   static int nGames = 10000;
   static String[] colors = {"red", "blue", "green", "yellow"};
   public static void main(String args[]) {

      //win count
      ArrayList<Integer> wins = new ArrayList<Integer>();
      
      //single game instance
      
      //generates shuffled deck
      cards = fillCards(cards);
      Collections.shuffle(cards);
      
      //deals cards to players
      for (int j = 0; j < nPlayers; j++) {
            ArrayList<String> hand = new ArrayList<String>();
            for (int k = 0; k < 7; k++){
               hand.add(cards.get(cards.size()-1));
               cards.remove(cards.size()-1);
            }
            players.add(hand);
      }    
             
      //initializes discard pile
      ArrayList<String> discardDeck = new ArrayList<String>();
      discardDeck.add(cards.get(cards.size()-1));
      cards.remove(cards.size()-1);
       
       
      //starts game
      
      boolean winCondition = false;
      //boolean to keep track of player turn direction (true = 1-2-3-4, false = 4-3-2-1)
      boolean direction = true;
      //player turn index
      int pIndex = 0;
      while (winCondition == false) {
         //reshuffles discard deck into draw deck if draw deck is empty
         if (cards.size() == 1) {
            String card = discardDeck.get(discardDeck.size()-1);
            ArrayList<String> newCards = discardDeck;
            System.out.println(newCards.toString());
            cards = newCards;
            discardDeck.clear();
            discardDeck.add(card);
            Collections.shuffle(cards);
         }
         
         
         //player turn    
         boolean played = false;
         String dcard = discardDeck.get(discardDeck.size()-1);
         
         System.out.println("top of discard pile: " + dcard);
         System.out.println("discard size: " + discardDeck.size());
         System.out.println(players.toString());
         
         for (int k = 0; k < players.get(pIndex).size(); k++) {
            String card = players.get(pIndex).get(k);
            
            if (dcard.equals("reverse")) {
               discardDeck.add(card);
               players.get(pIndex).remove(k);
               //ends turn 
               played = true;
               System.out.println("card played by " + pIndex + ", card was: " + card);  
               k = players.get(pIndex).size();
            }
            //player plays card if top of discard card is matching in color or number;
            else if (card.substring(0, card.length()-1).equals(dcard.substring(0, dcard.length()-1)) //color case
            || card.substring(card.length()-1).equals(dcard.substring(dcard.length()-1))) { //num case
               discardDeck.add(card);
               players.get(pIndex).remove(k);
               //ends turn 
               played = true;
               System.out.println("card played by " + pIndex + ", card was: " + card);  
               k = players.get(pIndex).size();
               
            } //player plays reverse card if they cant play a colored num card
            else if (card.equals("reverse")) {
               discardDeck.add(card);
               players.get(pIndex).remove(k);
               if (direction == false) {
                  direction = true;
               } else {
                  direction = false;
               }
               //ends turn 
               played = true;
               System.out.println("card played by " + pIndex + ", card was: " + card);  
               k = players.get(pIndex).size();
               
            } else { //sets played condition to false player could not play a card
               played = false;
            }
         } 
         // player draws card if he could not do anything
         if (played == false) {
            players.get(pIndex).add(cards.get(cards.size()-1));
            cards.remove(cards.size()-1);
         }
         
         
         //moves on to next player's turn
         if (direction == true) {
            if (pIndex == players.size()-1) {
               pIndex = 0;
            } else {
               pIndex++;
            }
            //implements reverse order if a reverse card was played)
         } else {
            if (pIndex == 0) {
               pIndex = players.size()-1;
            } else {
               pIndex--;
            }
         }
         
         //checks if a player has won
         for (int j = 0; j < players.size(); j++) {
            if (players.get(j).size() == 0) {
               winCondition = true;
               wins.add(j+1);
            }
         }

        // System.out.println(players.toString());
      }
      
      //main game loop
      /*
      for (int i = 0; i <= nGames; i++) {
         //generates shuffled deck

      }*/
      
   }
   
   //method to make deck of cards
   public static ArrayList<String> fillCards(ArrayList<String> cards) {
      for (int i = 1; i <= 9; i++) {
         cards.add("red" + i);
      }
      for (int i = 1; i <= 9; i++) {
         cards.add("blue" + i);
      }
      for (int i = 1; i <= 9; i++) {
         cards.add("green" + i);
      }
      for (int i = 1; i <= 9; i++) {
         cards.add("yellow" + i);
      }
      /*for (int i = 1; i <= 8; i++) {
         cards.add("wild");
      }
      */
      for (int i = 1; i <= 8; i++) {
         cards.add("reverse");
      }
      return cards;
   }
}