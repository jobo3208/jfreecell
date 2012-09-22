package freecell;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;

import javax.swing.JPanel;

import freecell.Card.Suit;

public class FinishedCell extends JPanel implements CardDestination {
	private static final long serialVersionUID = 1502461858912278004L;
	
	private Card topCard;
	private Suit suit;
	
	public FinishedCell() {
		super();

		Dimension size = new Dimension(80, 120);
		setSize(size);
		setPreferredSize(size);
		setMinimumSize(size);
	}
	
	public void add(Card card) {
		if (!canAdd(card)) {
			throw new IllegalArgumentException();
		}
		
		if (suit == null) {
			suit = card.getSuit();
		}
		
		topCard = card;
		
		repaint();
	}
	
	public boolean canAdd(Card card) {
		int currentRank = (topCard == null) ? 0 : topCard.getRank();
		
		if (card.getRank() == currentRank + 1) {
			if (suit == null || card.getSuit() == suit) {
				return true;
			}
		}
		
		return false;
	}
	
	public Card getTopCard() {
		return topCard;
	}
	
	public boolean isComplete() {
		return topCard == null ? false : topCard.getRank() == 13;
	}
	
	public void reset() {
		topCard = null;
		
		repaint();
	}
	
	public void paintComponent(Graphics g) {
		if (topCard == null) {
			g.setColor(FreeCell.BACKGROUND_COLOR);
			g.fillRect(0, 0, getWidth(), getHeight());
			
			g.setColor(FreeCell.CELL_OUTLINE_COLOR);
			g.drawRect(0, 0, getWidth() - 1, getHeight() - 1);
		}
		else {
			topCard.drawGraphics(g, new Point(0, 0));
		}
	}
}
