import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Solitaire {
    public static  void main (String[] args) throws FileNotFoundException {
        ArrayDeque<Card> deck;
        ArrayList<ArrayDeque<Card>> piles = new ArrayList<>();
        Scanner sc = new Scanner(new File("squeezebox.dat"));
        while (sc.hasNextLine()) {
            deck = getData(sc);
            if (deck.isEmpty()) break;
            while (!deck.isEmpty()) {
                Card card = deck.removeFirst();
                ArrayDeque<Card> pile = new ArrayDeque<>();
                pile.addFirst(card);
                piles.add(pile);
            }
            boolean one;
            boolean three;
            for (int i = 0; i < piles.size(); i++) {
                one = false;
                three = false;
                if (piles.get(i).isEmpty()) {
                    piles.remove(i);
                    i--;
                }
                if (i >= 3) {
                    if (piles.get(i).getFirst().match(piles.get(i - 3).getFirst())) three = true;
                }
                if (i >= 1) {
                    if (piles.get(i).getFirst().match(piles.get(i - 1).getFirst())) one = true;
                }
                if (three) {
                    ArrayDeque<Card> pileToMoveFrom = piles.get(i);
                    ArrayDeque<Card> receivingPile = piles.get(i - 3);
                    receivingPile.addFirst(pileToMoveFrom.removeFirst());
                    piles.set(i, pileToMoveFrom);
                    piles.set(i - 3, receivingPile);
                    if (pileToMoveFrom.isEmpty()) piles.remove(i);
                    i = -1;
                } else if (one) {
                    ArrayDeque<Card> pileToMoveFrom = piles.get(i);
                    ArrayDeque<Card> receivingPile = piles.get(i - 1);
                    receivingPile.addFirst(pileToMoveFrom.removeFirst());
                    piles.set(i, pileToMoveFrom);
                    piles.set(i - 1, receivingPile);
                    if (pileToMoveFrom.isEmpty()) piles.remove(i);
                    i = -1;
                }
            }
            System.out.print(piles.size() + " piles remaining: ");
            for (ArrayDeque<Card> pile:piles){
                System.out.print(pile.size()+ " ");
            }
            System.out.println();
            piles.clear();
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
        return val+suit;
    }
    public boolean match(Card c){
        return c.getSuit().equals(this.getSuit()) || c.getVal().equals(this.getVal());
    }
    String val;
    String suit;
}
