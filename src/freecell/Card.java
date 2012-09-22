package freecell;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Hashtable;

import javax.imageio.ImageIO;

public class Card {
	public static enum Suit {
		CLUBS, DIAMONDS, HEARTS, SPADES;
	};
	
	private static Hashtable<Suit, BufferedImage> suitImages = new Hashtable<Suit, BufferedImage>();
	static {
		// Load suit images
		BufferedImage iClubs = null, iDiamonds = null, iHearts = null, iSpades = null; 
		try {
			iClubs = ImageIO.read(Card.class.getResource("images/clubs.gif"));
			iDiamonds = ImageIO.read(Card.class.getResource("images/diamonds.gif"));
			iHearts = ImageIO.read(Card.class.getResource("images/hearts.gif"));
			iSpades = ImageIO.read(Card.class.getResource("images/spades.gif"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		suitImages.put(Suit.CLUBS, iClubs);
		suitImages.put(Suit.DIAMONDS, iDiamonds);
		suitImages.put(Suit.HEARTS, iHearts);
		suitImages.put(Suit.SPADES, iSpades);
	}
	
	private int rank;
	private Suit suit;
	
	public Card(int rank, Suit suit) {
		if (rank < 1 || rank > 13) {
			throw new IllegalArgumentException();
		}
		
		this.rank = rank;
		this.suit = suit;
	}
	
	public int getRank() { return rank; }
	public Suit getSuit() { return suit; }
	public Color getColor() { return (suit == Suit.CLUBS || suit == Suit.SPADES) ? Color.BLACK : Color.RED; }

	private String getShortRankString() {
		switch (rank) {
			case 1: return "A";
			case 11: return "J";
			case 12: return "Q";
			case 13: return "K";
			default: return Integer.toString(rank);
		}
	}
	
	public void drawGraphics(Graphics g, Point start) {
		// Outline
		g.setColor(Color.BLACK);
		g.drawRect(start.x, start.y, 79, 119);
		
		// White surface
		g.setColor(Color.WHITE);
		g.fillRect(start.x + 1, start.y + 1, 78, 118);
		
		// Suit
		g.setColor(getColor());
		g.drawImage(suitImages.get(getSuit()), start.x + 7, start.y + 7, null);
		g.drawImage(suitImages.get(getSuit()), start.x + 45, start.y + 98, null);
		
		// Rank
		g.setFont(g.getFont().deriveFont(Font.BOLD));
		g.drawString(getShortRankString(), start.x + 27, start.y + 20);
		g.drawString(getShortRankString(), start.x + 65, start.y + 110);
	}
}
