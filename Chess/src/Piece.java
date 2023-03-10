// Class: Piece
// Written by: Mr. Swope
// Date: 10/28/15
// Description: This class implements a Piece.  This Piece will be drawn onto a graphics panel. 
// 
// If you modify this class you should add comments that describe when and how you modified the class.  

import java.awt.Component;
import java.awt.Graphics;
import java.net.URL;

import javax.swing.ImageIcon;

public class Piece {
	private ImageIcon image;			// The ImageIcon will be used to hold the Character's png.
										// This png must be saved in the images folder and will be loaded 
										// in the constructor.
	
	private int player;					// This int will represent which team the piece is, 1 for yellow team, 
									    // 2 for black team. 
	
	// method: Default constructor - see packed constructors comments for a description of parameters.
	public Piece(){
		this(1);
	}
		
	// method: Character's packed constructor
	// description: Initialize a new Character object.
	// parameters: int player - should be either 1 or 2. 1 for yellow team, 2 for black team.
	public Piece(int player){
		
		setImageIcon("images/king1.png");
		this.setPlayer(player);			
	}
	
	// method: Character's packed constructor
	// description: Initialize a new Character object.
	// parameters: int player - should be either 1 or 2. 1 for yellow team, 2 for black team.
	public Piece(int player, String imagePath){
		
		setImageIcon(imagePath);
		this.setPlayer(player);			
	}
	
	protected void setImageIcon(String imagePath){
		ClassLoader cldr = this.getClass().getClassLoader();	
		
		URL imageURL = cldr.getResource(imagePath);				
        image = new ImageIcon(imageURL);
	}
	
	// method: isValidMove
	// description: This method checks to see if a move is valid.
	// Returns whether or not the attempted move is valid.
	// @param - Location from - the location that the piece will be moved from
	// @param - Location to - the location that the piece will be moved to
	// @param - Piece[][]b - the chess board.  a two dimensional array of pieces.
	// return - boolean - true if the move is valid 
	public boolean isValidMove(Location from, Location to, Piece[][]b){
		return false;
	}
	
	// method: draw
	// description: This method is used to draw the image onto the GraphicsPanel.  
	//This method was slightly modified so that all pieces are drawn in the center of the square
	// parameters: Graphics g - this object draw's the image.
	//			   Component c - this is the component that the image will be drawn onto.
	//			   Location l - a Location that determines where to draw the piece.
	
	public void draw(Graphics g, Component c, Location l) {
		if(this instanceof Rook || this instanceof Pawn)
			image.paintIcon(c, g, l.column*90 + 42, l.row*90 + 35);
		else
			image.paintIcon(c, g, l.column*90 + 35, l.row*90 + 35);// you'll need to update the last two parameters so that it will 
        											  // correctly draw the piece in the right location.
    }
	//Method: getPlayer
	//Description: returns the player
	//Parameters: none
	//Return type: int
	public int getPlayer() {
		return player;
	}
	//Method: setPlayer
	//Description: sets the player to the new input player
	//Parameters: int player
	//Return type: void 
	public void setPlayer(int player) {
		this.player = player;
	}
	//Method: getCount
	//Description: returns 0 but is overridden in a few of the pieces and used there
	//Parameters: none
	//Return type: int
	public int getCount() {
		return 0;
	}
	//Method: setCount
	//Description: Does nothing but is overrideenin a few of the pieces and used there
	//Parameters: int count
	//Return type: void
	public void setCount(int count) {
		
	}
}
