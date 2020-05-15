import java.util.*;
import java.util.ArrayList;
import java.lang.Math;
public class UNOgame {
   static ArrayList<String> cards = new ArrayList<String>();
   public static void main(String args[]) {
      cards = fillCards(cards);
      System.out.println(cards.toString());
   }
   
   public static ArrayList<String> fillCards(ArrayList<String> cards) {
      for (int i = 0; i < 9; i++) {
         cards.add("red" + i);
      }
      for (int i = 0; i < 9; i++) {
         cards.add("blue" + i);
      }
      for (int i = 0; i < 9; i++) {
         cards.add("green" + i);
      }
      for (int i = 0; i < 9; i++) {
         cards.add("yellow" + i);
      }
      return cards;
   }
}