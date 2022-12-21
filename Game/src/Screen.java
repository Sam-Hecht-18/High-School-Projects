// Class: Screen
// Written by: Sam Hecht
// Date: 6/11/2019
// Description: This class contains an imageIcon and a constructor which makes the Screens class easier to use
//
import java.awt.Component;
import java.awt.Graphics;
import java.net.URL;
import javax.swing.ImageIcon;

public class Screen{
	private ImageIcon image;
	public Screen(int num) {
		if(num == 1) {
			ClassLoader cldr = this.getClass().getClassLoader();	
			String imagePath = "images/background.png";				 
			URL imageURL = cldr.getResource(imagePath);				
			image = new ImageIcon(imageURL);	
			
		
		}
		else if(num == 2) {
			ClassLoader cldr = this.getClass().getClassLoader();	
			String imagePath = "images/buttonUp.png";				
			URL imageURL = cldr.getResource(imagePath);				
			image = new ImageIcon(imageURL);	
		}
		else if(num == 3) {
			ClassLoader cldr = this.getClass().getClassLoader();	
			String imagePath = "images/torchRoom.png";				
			URL imageURL = cldr.getResource(imagePath);				
			image = new ImageIcon(imageURL);	
		}
		
	}
	public void drawScreen(Graphics g, Component c) {

		image.paintIcon(c, g, 0, 0);
		
	}
	
	public void nextImage(String imagePath) {
		if(!image.getDescription().contains("background")) {
			ClassLoader cldr = this.getClass().getClassLoader();				
			URL imageURL = cldr.getResource(imagePath);
			image = new ImageIcon(imageURL);
		}
		
	}
	



}
