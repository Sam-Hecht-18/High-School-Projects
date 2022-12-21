import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics2D;
import java.net.URL;
import java.util.ArrayList;

import javax.swing.ImageIcon;

public class Card {
	private int value;
	private ImageIcon image;
	private char suit;
	private char character;
	private ImageIcon faceDown;
	public Card() {
		setValue(2);
		suit = 'c';
		character = '2';
		ClassLoader cldr = this.getClass().getClassLoader();	
		String imagePath =  "Card Images/" + character + suit + ".gif";				
		URL imageURL = cldr.getResource(imagePath);				
		image = new ImageIcon(imageURL);	
		imagePath =  "Card Images/faceDown.png";				
		imageURL = cldr.getResource(imagePath);				
		faceDown = new ImageIcon(imageURL);
	
	}
	public Card(char character, char suit) {
		if((int)character > 49 && (int)character<58) {
			setValue((int)character-48);
		}
		else if(character == 'a') {
			setValue(11);
		}
		else {
			setValue(10);
		}
		this.suit = suit;
		this.character = character;
		ClassLoader cldr = this.getClass().getClassLoader();	
		String imagePath =  "Card Images/" + character + suit + ".gif";		
		URL imageURL = cldr.getResource(imagePath);				
		image = new ImageIcon(imageURL);
		imagePath =  "Card Images/faceDown.png";				
		imageURL = cldr.getResource(imagePath);				
		faceDown = new ImageIcon(imageURL);
	}
	public String toString() {
		return "" + character + suit;
	}
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
	public boolean checkAce() {
		if(value == 11) {
			value = 1;
			return true;
		}
		return false;
	}
	public void draw(Component c, Graphics2D g2, int index, boolean isDealer, boolean flip, int split, boolean stand, boolean bust) {
		if(!flip && isDealer && index == 0) {
			faceDown.paintIcon(c, g2, 400, 0);
		}
		else if(isDealer) {
			image.paintIcon(c, g2, 400 + index*25, index*25);
		}
		else {
			image.paintIcon(c, g2, 400 + index*25, 480-index*25-split*200);
			if(stand || bust) {
				g2.setColor(Color.RED);
				g2.drawRect(400 + index*25, 480-index*25-split*200, 72, 97);
			}
			else {
				g2.setColor(Color.GREEN);
				g2.drawRect(400 + index*25, 480-index*25-split*200, 72, 97);
			}
		}
	}
}
