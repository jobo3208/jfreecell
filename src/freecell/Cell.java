package freecell;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.util.NoSuchElementException;

import javax.swing.JPanel;

public class Cell extends JPanel implements CardSource, CardDestination {
	private static final long serialVersionUID = 4656851991098138209L;
	
	private Card card;
	private boolean selected;
	
	public Cell() {
		super();
		
		Dimension size = new Dimension(80, 120);
		setSize(size);
		setPreferredSize(size);
		setMinimumSize(size);
		
		selected = false;
	}
	
	public Card remove() {
		if (card == null) {
			throw new NoSuchElementException();
		}
		
		Card ret = card;
		card = null;
		
		repaint();
		
		return ret;
	}
	
	public Card peek() {
		return card;
	}
	
	public boolean canRemove() {
		return card != null;
	}
	
	public void select() {
		selected = true;
		repaint();
	}
	
	public void unselect() {
		selected = false;
		repaint();
	}
	
	public void add(Card card) {
		if (this.card != null) {
			throw new ArrayIndexOutOfBoundsException();
		}
		
		this.card = card;
		repaint();
	}
	
	public boolean canAdd(Card card) {
		return this.card == null;
	}
	
	public void reset() {
		card = null;
		selected = false;
		
		repaint();
	}
	
	public void paintComponent(Graphics g) {
		if (card == null) {
			g.setColor(FreeCell.BACKGROUND_COLOR);
			g.fillRect(0, 0, getWidth(), getHeight());
			
			g.setColor(FreeCell.CELL_OUTLINE_COLOR);
			g.drawRect(0, 0, getWidth() - 1, getHeight() - 1);
		}
		else {
			card.drawGraphics(g, new Point(0, 0));
			
			if (selected) {
				g.setColor(FreeCell.SELECTED_COLOR);
				g.drawRect(0, 0, getWidth() - 1, getHeight() - 1);
				g.drawRect(1, 1, getWidth() - 3, getHeight() - 3);
			}
		}
	}
}
