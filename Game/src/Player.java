// Class: Player
// Written by: Sam Hecht
// Date: 6/11/2019
// Description: This class contains a player, an x and y coordinate, an imageNum, and a boolean life
// These variables combine with the methods to make this class function as the player and the bosses in the game
//
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.net.URL;
import javax.swing.ImageIcon;

public class Player{
	private ImageIcon image;
	private int x;
	private int y;
	private int imageNum;
	private boolean life;
	public Player() {
		this("images/1st.png");
		x = 100;
		y = 100;
		imageNum = 0;
		life = true;

	}
	public Player(String name) {
		ClassLoader cldr = this.getClass().getClassLoader();	
		String imagePath =  name;				
		URL imageURL = cldr.getResource(imagePath);				
		image = new ImageIcon(imageURL);
		x = 0;
		y = 0;
		imageNum = 0;
		life = true;
	}
	//This methods swithces the image of the sprite
	public void nextImage() {
		if(image.getDescription().substring(20).contains("1st")) {
			ClassLoader cldr = this.getClass().getClassLoader();	
			String imagePath = "images/2nd.png";				 
			URL imageURL = cldr.getResource(imagePath);				
			image = new ImageIcon(imageURL);
		}
		else if(image.getDescription().substring(20).contains("2nd")) {
			ClassLoader cldr = this.getClass().getClassLoader();	
			String imagePath = "images/3rd.png";				 
			URL imageURL = cldr.getResource(imagePath);				
			image = (new ImageIcon(imageURL));
		}
		else if(image.getDescription().substring(20).contains("3rd")) {
			ClassLoader cldr = this.getClass().getClassLoader();	
			String imagePath = "images/4th.png";				
			URL imageURL = cldr.getResource(imagePath);			
			image = (new ImageIcon(imageURL));
		}
		else if(image.getDescription().substring(20).contains("4th")) {
			ClassLoader cldr = this.getClass().getClassLoader();	
			String imagePath = "images/1st.png";				
			URL imageURL = cldr.getResource(imagePath);				
			image = (new ImageIcon(imageURL));
		}
		else if(image.getDescription().substring(20).contains("Boi" + imageNum)) {
			imageNum++;
			if(imageNum % 4 == 0)
				imageNum-=4;
			ClassLoader cldr = this.getClass().getClassLoader();	
			String imagePath = "images/smallBoi" + imageNum + ".png";				
			URL imageURL = cldr.getResource(imagePath);				
			image = (new ImageIcon(imageURL));
		}
		
		
		
		else if(image.getDescription().substring(20).contains(imageNum+"") || image.getDescription().substring(20).contains(imageNum-4+"")) {
			imageNum++;
			if(imageNum % 4 == 0)
				imageNum-=4;
			ClassLoader cldr = this.getClass().getClassLoader();	
			String imagePath = "images/sprite_" + imageNum +".png";		
			URL imageURL = cldr.getResource(imagePath);				
			image = (new ImageIcon(imageURL));
		}


	}
	//This method makes the player small for the chandelier lighting level
	public void goSmall() {
		ClassLoader cldr = this.getClass().getClassLoader();	
		String imagePath = "images/smallBoi0.png";				
		URL imageURL = cldr.getResource(imagePath);				
		image = (new ImageIcon(imageURL));
		imageNum = 0;
	}
	//This method makes the player go back to normal size
	public void goBig() {
		ClassLoader cldr = this.getClass().getClassLoader();	
		String imagePath = "images/1st.png";				
		URL imageURL = cldr.getResource(imagePath);				
		image = (new ImageIcon(imageURL));
		imageNum = 1;

	}
	//This method moves the player, it returns whether it's moving or not
	public boolean move(int direction) {
		if(life) {
			switch(direction) {
			case -1:
				if(x>-15)
					x +=(-1);
				break;

			case 1:
				if(x<984)
					x +=(1);
				break;

			case -2:
				if(y < 576 - image.getIconHeight()/2)
					y +=(1);
				break;

			case 2:
				if(y > (0 - image.getIconHeight()/2))
					y +=(-1);
				break;

			case 3:
				if(x<984) 
					x +=(1);
				if(y<576 - image.getIconHeight()/2)
					y +=(1);
				break;

			case -3:
				if(y<576 - image.getIconHeight()/2)
					y +=(1);
				if(x>-15)
					x +=(-1);
				break;

			case 4:
				if(y > 0 - image.getIconHeight()/2)
					y +=(-1);
				if(x<984)
					x +=(1);
				break;

			case -4:
				if(y > 0 - image.getIconHeight()/2)
					y +=(-1);
				if(x>-15)
					x +=(-1);
				break;
			}
		}
		return direction!=0;
	}
	//Resets the location of the player
	public void nextRoom() {
		setX(-x);
	}
	//Resets the location of the player
	public void prevRoom(){
		setX(984);
	}
	//Draws the player's image
	public void drawItem(Graphics g, Component c) {
		image.paintIcon(c, g, x, y);
	}
	//Returns a 2x2 hitbox for the player
	public Rectangle getBounds() {
		return new Rectangle(x+31,y+31,2, 2);
	}
	//Returns a 64x64 hitbox for the player
	public Rectangle getBigBounds() {
		return new Rectangle(x,y,64,64);
	}
	//Returns a 2x2 hitbox for the player when it goes small
	public Rectangle getSmallBounds() {
		return new Rectangle(x+2,y+2,16,16);
	}
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	public void setImage(ImageIcon i) {
		image = i;
	}
	
	public void setX(int num) {
		x+=num;
	}
	//Resets the location of the player
	public void setMiddle() {
		x = 465;
		y = 221;
	}
	//Resets the location of the boss
	public void resetBoss() {
		x = 800;
		y = 221;
	} 
	//Puts the boss into the next Phase
	public boolean nextPhase() {
		if(imageNum<20)
			imageNum += 4;
		return imageNum>=20;
		
	}
	public void setImageNum(int imageNum) {
		this.imageNum = imageNum;
	}
	public int getImageNum() {
		return imageNum;
	}
	//Kills the player or the boss
	public void Dead() {
		life = false;
	}
	//Returns whether the player/boss is alive
	public boolean deadOrAlive() {
		return life;
	}
	//Sets the life to true
	public void newLife() {
		life = true;
	}
	//Teleports the boss so one of its lasers will hit the player everytime
	public void teleport(Player p1, boolean rand) {
		//Align the x-coordinate with the player
		if (!rand) {
			if (((int)(Math.random() * 2)) % 2 == 0) {
				x = p1.getX() + (int)(Math.random()*100) - 50;
				y = 50 + (int)(Math.random()*476);
			}
			//Algin the y-coordinate with the player
			else {
				x = 50 + (int)(Math.random()* 924);
				y = p1.getY() + (int)(Math.random()*100) - 50;
			}
			if (x < 50) {
				x = 50;
			}
			else if (x > 974) {
				x = 974;
			}
			if (y < 50) {
				y = 50;
			}
			else if (y > 524) {
				y = 524;
			}
		}
		else {
			x = 50 + (int)(Math.random()* 924);
		 	y = 50 + (int)(Math.random()*476);
		}
		
		// while(!((p1.getX()>x && p1.getX()-50<x) || 
		// 		(p1.getX()<x && p1.getX()+50>x) ||
		// 		(p1.getY()>y && p1.getY()-50<y) ||
		// 		(p1.getY()<y && p1.getY()+50>y))) {
		// 	x = (int)(Math.random()* 924);
		// 	y = (int)(Math.random()*476);
		// }

	}
	//Checks to see if the boss/player hits the boss/player and kills the other one if necessary
	public boolean attack(Player p1) {
		for(Rectangle r : getLasers())
			if(p1.getBigBounds().intersects(r))
				p1.Dead();
		return p1.deadOrAlive();

	}
	//Resets the image
	public void resetImage() {
		ClassLoader cldr = this.getClass().getClassLoader();	
		String imagePath = "images/sprite_" + imageNum +".png";		
		URL imageURL = cldr.getResource(imagePath);				
		image = (new ImageIcon(imageURL));
	}
	//Returns an array of rectangles that represent the lasers
	public Rectangle[] getLasers() {
		Rectangle[] r = new Rectangle[4];
		r[0] = new Rectangle(x+16,0,32,(y+16));
		r[1] = new Rectangle(0,y+16,x+16,32);
		r[2] = new Rectangle(x+16,y+48,32,576-(y+48));
		r[3] = new Rectangle(x+48,y+16,1024-(x+48),32);
		return r;
	}
}
