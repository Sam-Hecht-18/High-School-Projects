// Class: Character
// Written by: Mr. Swope
// Date: 10/28/15
// Description: This class implements a Character.  This Character will be drawn onto a graphics panel. 
// 
// If you modify this class you should add comments that describe when and how you modified the class.  

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.net.URL;
import javax.swing.ImageIcon;

public class Character {
	private ImageIcon image;			// The ImageIcon will be used to hold the Character's png.
	private int imageChoice;			// This png must be saved in the images folder and will be loaded 
	// in the constructor.

	private int x_coordinate;			// These ints will be used for the drawing the png on the graphics panel.
	private int y_coordinate;			// When the Character's move method is called you should update one or both
	private int count;
	private boolean reverse;
	// of these instance variables.  (0,0) is the top left hand corner of the
	// panel.  x increases as you move to the right, y increases as you move
	// down.

	// method: Default constructor - see packed constructors comments for a description of parameters.
	public Character(){
		this(0, 200, 300);
	}

	// method: Character's packed constructor
	// description: Initialize a new Character object.
	// parameters: imageChoice - used to determine which image to load when a Character is instantiated.  You can change
	//			   existing options or add other options. 0 - pirate, 1 - parrot.
	//			   x_coordinate - the initial x-coordinate for Character.
	//			   y_coordinate - the initial y-coordinate for Character.
	public Character(int imageChoice, int x_coordinate, int y_coordinate){

		ClassLoader cldr = this.getClass().getClassLoader();	// These eight lines of code load the Character's png
		String imagePath;										// so that it later be painted on the graphics panel
		// when draw method is called.  You should modify
		// the imagePath if you change the Character's png.
		this.imageChoice = imageChoice;
		if(imageChoice == 0)									// if statement that determines which image to use for		
			imagePath = "images/jeepNormal.png";				// a Character object.  You can add other options as well.
		else if(imageChoice == 1)
			imagePath = "images/sprites_megaman_-running-0.png";
		else if(imageChoice == 2)
			imagePath = "images/spaceship.png";
		else if(imageChoice == 3)
			imagePath = "images/Coal.png";
		else 
			imagePath = "images/Invincibility.png";
		count = 1;
		URL imageURL = cldr.getResource(imagePath);				
		image = new ImageIcon(imageURL);						
		reverse = false;
		this.x_coordinate = x_coordinate;						// Initial coordinates for the Character.
		this.y_coordinate = y_coordinate;  
		
	}

	// method: getBounds
	// description: This method will return the coordinates of a rectangle that would be drawn around the 
	// 				Character's png.  This rectangle can be used to check to see if the Character bumps into 
	//				another character on your panel by calling the Rectangle's intersects method:
	//
	//							p.getBounds().intersects(c.getBounds());
	//
	//				in this example p is an instance of the Character class and c is an instance of another
	//				class that has a getBounds method that also returns a Rectangle, so p.getBounds and
	//				c.getBounds would both return or evaluate to Rectangle objects.  The intersects method
	//				return true if the two rectangles overlap, false if they do not.
	// return: A Rectangle - This rectangle would be like drawing a rectangle around the Character's image.
	
	public Rectangle getBounds(){
		Rectangle r = new Rectangle();
		if(imageChoice == 0) {
			//Rectangle for body of the car
			return new Rectangle(x_coordinate+15, y_coordinate+55,90,45);

		}
		else if(imageChoice == 1) {
			//Rectangle for Gold
			return new Rectangle(x_coordinate-10, y_coordinate-8, 45, 45);

		}
		else if(imageChoice == 2) {
			//Rectangle for Spaceship
			return new Rectangle(x_coordinate+5,y_coordinate+10, 60, 45);
		}
		else if(imageChoice == 3) {
			//Rectangle for coal
			return new Rectangle(x_coordinate+5,y_coordinate, 40, 45);
		}
		else if(imageChoice == 4) {
			return new Rectangle(x_coordinate+5, y_coordinate, image.getIconWidth()-10, image.getIconHeight());
		}
		//return new Rectangle(x_coordinate, y_coordinate, image.getIconWidth(), image.getIconHeight());

		return r;
	}

	// method: getX
	// description:  This method will return the x-coordinate of the top left hand corner of the the image.
	// return: int - the x-coordinate of the top left hand corner of the the image.
	public int getX(){
		return x_coordinate;
	}
	public void setX(int x_coordinate) {
		this.x_coordinate = x_coordinate;
	}
	public void setY(int y_coordinate) {
		this.y_coordinate = y_coordinate;
	}

	// method: getY
	// description:  This method will return the y-coordinate of the top left hand corner of the the image.
	// return: int - the y-coordinate of the top left hand corner of the the image.
	public int getY(){
		return y_coordinate;
	}

	// method: keyPressedMove
	// description: This method should modify the Character's x or y (or perhaps both) coordinates.  When the 
	//				graphics panel is repainted the Character will then be drawn in it's new location.
	// parameters: int direction - This parameter should represent the direction that you want to move
	//			   the Character, so decide on a standard for what each integer value will stand for and then
	//			   add comments below that describe these integer values, for example...
	//			   1 - move Character to the right.
	public void keyPressedMove(int direction, int speed){
		if(direction == -2 && y_coordinate < 170) {
			y_coordinate +=50;
		}
		else if(direction == 2 && y_coordinate > 70) {
			y_coordinate -=50;
		}
		else if(direction == -1 && x_coordinate >= 40)
			x_coordinate -=1+speed;
		else if(direction == 1 && x_coordinate <= 800)
			x_coordinate +=1+speed;

	}

	// method: timerMove
	// description: This method should modify the Character's x or y (or perhaps both) coordinates.  When the 
	//				graphics panel is repainted the Character will then be drawn in it's new location.
	// parameters: int direction - This parameter should represent the direction that you want to move
	//			   the Character, so decide on a standard for what each integer value will stand for and then
	//			   add comments below that describe these integer values, for example...
	//			   1 - move Character to the right.
	public void timerMove(int rand, int speed){
		if(imageChoice == 2 && x_coordinate >=1345) {
			x_coordinate = 50;
			y_coordinate = 65 + 50*rand;
		}
		if(imageChoice == 2)
			x_coordinate +=speed;
		if(imageChoice == 3 && x_coordinate >=900) {
			x_coordinate = -50;
		}
		if(imageChoice == 3)
			x_coordinate ++;
		if(imageChoice == 4) {
			x_coordinate = 450+(int)(Math.random()*400);
			y_coordinate = 125 + (int)(Math.random()*3);
		}
			
		
	}
	public void nextImage() {
//		URL imageURL = cldr.getResource(imagePath);				
//		image = new ImageIcon(imageURL);
		ClassLoader cldr = this.getClass().getClassLoader();
		String imagePath = "";
		ImageIcon i;
		
		if(count == 1 && imageChoice == 0)
			imagePath = "images/JeepNormal2.png";
		else if(count == 1)
			imagePath = "images/sprites_megaman_-running-1 copy.png";
		else if(count == 2 && imageChoice == 0)
			imagePath = "images/JeepNormal3.png";
		else if(count == 2)
			imagePath = "images/sprites_megaman_-running-1 copy 2.png";
		else if(count == 3 && imageChoice == 0)
			imagePath = "images/JeepNormal4.png";
		else if(count == 3)
			imagePath = "images/sprites_megaman_-running-1 copy 3.png";
		else if(count == 4 && imageChoice == 0)
			imagePath = "images/JeepNormal.png";
		else if(count == 4)
			imagePath = "images/sprites_megaman_-running-1 copy 4.png";
		else if(count == 5)
			imagePath = "images/sprites_megaman_-running-1 copy 5.png";
		else if(count == 6)
			imagePath = "images/sprites_megaman_-running-1 copy 6.png";
		else if(count == 7)
			imagePath = "images/sprites_megaman_-running-1 copy 7.png";
		else if(count == 8)
			imagePath = "images/sprites_megaman_-running-1 copy 8.png";
		else if(count == 9)
			imagePath = "images/sprites_megaman_-running-1 copy 9.png";
		else if(count == 10)
			imagePath = "images/sprites_megaman_-running-0.png";
		count++;
		if(count == 5 && imageChoice == 0)
			count = 1;
		else if(count == 11)
			count = 1;
		URL imageURL = cldr.getResource(imagePath);				
		i = new ImageIcon(imageURL);
		image = i;
		reverse = false;
	}
	public void nextImageReverse() {
//		URL imageURL = cldr.getResource(imagePath);				
//		image = new ImageIcon(imageURL);
		ClassLoader cldr = this.getClass().getClassLoader();
		String imagePath = "";
		ImageIcon i;
		
		if(count == 1)
			imagePath = "images/JeepNormal.png";
		if(count == 2)
			imagePath = "images/JeepNormal4.png";
		if(count == 3)
			imagePath = "images/JeepNormal3.png";
		if(count == 4)
			imagePath = "images/JeepNormal2.png";
		count++;
		if(count == 5)
			count = 1;
		URL imageURL = cldr.getResource(imagePath);				
		i = new ImageIcon(imageURL);
		image = i;
		reverse = true;
	}
	public void reset(int rand) {
		
		if(imageChoice == 0) {
			x_coordinate = 800;
			y_coordinate = 120;
		}
		else if(imageChoice == 1)
			y_coordinate = 130 + (rand)*50;
		else if(imageChoice == 2) {
			x_coordinate = 900;
			y_coordinate = 65 + 50*rand;
		}
		else if(imageChoice == 3) {
			x_coordinate = -50;
			y_coordinate = 125 + (rand)*50;
			
		}
		else if(imageChoice == 4) {
			x_coordinate = -100;
		}
	}
	// method: draw
	// description: This method is used to draw the image onto the GraphicsPanel.  You shouldn't need to 
	//				modify this method.
	// parameters: Graphics g - this object draw's the image.
	//			   Component c - this is the component that the image will be drawn onto.
	public void draw(Graphics g, Component c) {
		//fourth image needs to go up and 1st image needs to go down
		if(imageChoice == 1)
			image.paintIcon(c, g, x_coordinate-15, y_coordinate-15);
		else if(count == 1 && !reverse)
			image.paintIcon(c, g, x_coordinate, y_coordinate-1);
		else if(count == 4 && !reverse)
			image.paintIcon(c, g, x_coordinate, y_coordinate+1);
		else if(count == 4 && imageChoice == 0)
			image.paintIcon(c, g, x_coordinate, y_coordinate-1);
		else if(count == 4 && imageChoice == 0)
			image.paintIcon(c, g, x_coordinate, y_coordinate+1);
		else 
			image.paintIcon(c, g, x_coordinate, y_coordinate);
	}
}
