package freecell;

import java.util.Random;

public class Deck {
	private Card[] cards;
	private int cardsLeft;
	private Random rng;
	
	public Deck() {
		cards = new Card[52];
		cardsLeft = 52;
		
		rng = new Random();
		
		int count = 0;
		for (int r = 1; r < 14; r++) {
			for (Card.Suit s : Card.Suit.values()) {
				cards[count++] = new Card(r, s);
			}
		}
	}
	
	public boolean isEmpty() { return cardsLeft == 0; }
	
	public Card draw() {
		int drawIndex = rng.nextInt(cardsLeft);
		
		Card tmp = cards[drawIndex];
		cards[drawIndex] = cards[--cardsLeft];
		cards[cardsLeft] = tmp;
		
		return tmp;
	}
	
	public void shuffle() {
		cardsLeft = 52;
	}
}
