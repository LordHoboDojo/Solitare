import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.logging.FileHandler;

public class Solitaire {
    public static  void main (String[] args) throws FileNotFoundException {
        ArrayDeque<Card> deck;
        LinkedList<ArrayDeque<Card>> piles = new LinkedList<>();
        Scanner sc = new Scanner(new File("squeezebox.dat"));
        while (sc.hasNextLine())
        {
             deck = getData(sc);
             if (!deck.isEmpty()){
                 while (!deck.isEmpty()){
                     Card card = deck.removeFirst();
                     ArrayList<String> moves  =new ArrayList<>();
                     if (piles.isEmpty())
                     {
                         ArrayDeque<Card> firstPile = new ArrayDeque<>();
                         firstPile.addFirst(card);
                         piles.add(firstPile);
                     }
                     else if (piles.size()>=2){
                         

                     }
                 }
             }
        }
    }

    private static ArrayDeque<Card> getData(Scanner sc) {
        ArrayDeque<Card> deck = new ArrayDeque<>();
        String lineOne = sc.nextLine();
        String lineTwo;
        if (!lineOne.equals("#"))
        {
            lineTwo = sc.nextLine();
            String deckString = lineOne +" "+ lineTwo;
            System.out.println(deckString);
            String[] deckArray = deckString.split(" ");
            for (String s: deckArray)
            {
                deck.addLast(new Card(s.substring(0,1) ,s.substring(1)));
            }
        }
        return deck;
    }


}
class Card{
    public String getVal() {
        return val;
    }

    public String getSuit() {
        return suit;
    }

    public Card(String val, String suit) {
        this.val = val;
        this.suit = suit;
    }

    @Override
    public String toString() {
        return "Card{" +
                "val=" + val +
                ", suit='" + suit + '\'' +
                '}';
    }
    public boolean match(Card c){
        return c.getSuit().equals(this.getSuit()) || c.getVal().equals(this.getVal());
    }
    String val;
    String suit;
}
