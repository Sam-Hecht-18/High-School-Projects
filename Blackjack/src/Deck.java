

import java.awt.Component;
import java.awt.Graphics2D;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.ImageIcon;

public class Deck {
	private ArrayList<Card> deck;
	private int numDecks;
	private ImageIcon image;
	public Deck() {
		numDecks = 1;
		deck = new ArrayList<>();
		for(int i = 0; i<numDecks; i++) {
			for(int j = 2; j<10; j++) 
				deck.add(new Card((char) (j+48),'c'));
			for(int j = 2; j<10; j++) 
				deck.add(new Card((char) (j+48),'s'));
			for(int j = 2; j<10; j++) 
				deck.add(new Card((char) (j+48),'d'));
			for(int j = 2; j<10; j++) 
				deck.add(new Card((char) (j+48),'h'));
			deck.add(new Card('j','c'));
			deck.add(new Card('j','h'));
			deck.add(new Card('j','d'));
			deck.add(new Card('j','s'));

			deck.add(new Card('q','c'));
			deck.add(new Card('q','h'));
			deck.add(new Card('q','d'));
			deck.add(new Card('q','s'));

			deck.add(new Card('k','c'));
			deck.add(new Card('k','h'));
			deck.add(new Card('k','d'));
			deck.add(new Card('k','s'));

			deck.add(new Card('t','c'));
			deck.add(new Card('t','h'));
			deck.add(new Card('t','d'));
			deck.add(new Card('t','s'));
			
			deck.add(new Card('a','c'));
			deck.add(new Card('a','h'));
			deck.add(new Card('a','d'));
			deck.add(new Card('a','s'));
			ClassLoader cldr = this.getClass().getClassLoader();	
			String imagePath =  "Card Images/deckImage.png";				
			URL imageURL = cldr.getResource(imagePath);				
			image = new ImageIcon(imageURL);
			shuffle();
			deck.add(0, new Card('2','c'));
			deck.add(2, new Card('2','c'));
		}
		
	}
	public void shuffle() {
		Collections.shuffle(deck);
	}
	public String toString(){
		String returnString = "";
		for(Card c: deck) {
			returnString+=c.toString();
		}
		return returnString;
			
	}
	public Card drawTop() {
		return deck.remove(0);
	}
	public void draw(Component c, Graphics2D g2) {
		for(int i = 0; i<deck.size(); i++) {
			image.paintIcon(c, g2, 100, 100 + i);
		}
		
		
	}
	public int size() {
		return deck.size();
	}
	
	

}
