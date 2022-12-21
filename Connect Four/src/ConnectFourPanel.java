// ConnectFour Panel
// Written by: Mr. Swope
// Date: May 13th, 2016
// This project extends the Jpanel class. In order to draw items on this panel you need use the Graphics2D's methods.
// Update these comments by writing when, who and how you modified this class.
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class ConnectFourPanel extends JPanel implements MouseListener{
	
	private int turn;		// will equal -1 or 1 to represent who's turn it is. use -1 for blue 1 for red
	private int[][] board;  // two-dimensional array of ints.  All values should be initialized to 0.  When
							// blue places a checker value should be changed to -1.  When red places a checker
							// value should be changed to 1.
	private static final int WIDTH = 701;
	private static final int HEIGHT = 601;
	private boolean gameOver;
	
	// method: ConnectFourPanel Constructor
	// description: This 'method' runs when a new instance of this class in instantiated.  It sets default values  
	// that are necessary to run this project.  
	public ConnectFourPanel(){
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
        this.setFocusable(true);			// for mouselistener
		this.addMouseListener(this);
	
		board = new int[6][7];
		
		turn = -1;
	}
	
	// method: paintComponent
	// description: This method is called when the Panel is painted.  It contains 
	// code that draws shapes onto the panel.
	// parameters: Graphics g - this object is used to draw shapes onto the JPanel.
	// return: void
	public void paintComponent(Graphics g){
		Graphics2D g2 = (Graphics2D) g;
	
		g2.setColor(Color.LIGHT_GRAY);
		g2.fillRect(0, 0, WIDTH, HEIGHT); 
		
		// this code loads an image so that you can later paint it onto your component.
		// this would load a picture named blue.png that should be saved in a folder
		// named images, which should be located in your src folder.
		ImageIcon blueImage;
		ImageIcon redImage;
		
		ClassLoader cldr = this.getClass().getClassLoader();
		String imagePath = "images/blue.png";
		URL imageURL = cldr.getResource(imagePath);
		blueImage = new ImageIcon(imageURL);
		
		imagePath = "images/red.png";
		imageURL = cldr.getResource(imagePath);
		redImage = new ImageIcon(imageURL);
		
		// draws the connect four board	
		for(int r = 0; r < board.length; r++){
			for(int c = 0; c < board[r].length; c++){
				g2.setColor(Color.GRAY);
				g2.drawLine(c*100, 0, c*100, HEIGHT);
				g2.drawOval(c*100+5, r*100+5, 90, 90);
				g2.fillOval(c*100+9, r*100+9, 82, 82);
			}
			g2.drawLine(0, r*100, WIDTH, r*100);
		}
		
		// this is how you paint your image. the last two parameters are the x and y coordinates 
		// of the top left hand corner of the image.  Right now this will only draw one blue checker
		// and one red image drawn on the board.  You should instead use nested for loops to loop 
		// through your two-dimensional array and paint blue checkers where ever there is -1 and 
		// a red checker wherever there is 1 in the array.
		for(int r = 0; r < board.length; r++){
			for(int c = 0; c < board[0].length; c++){
				if(board[r][c] == -1 )
					blueImage.paintIcon(this, g, c*100+10, r*100+10);
				else if(board[r][c] == 1 )
					redImage.paintIcon(this, g, c*100+10, r*100+10);
			}
		}
		// Display a message if either red or blue has won the game.
		g2.setFont(new Font("Verdana", 0, 40));
		
		
	}
	
	// Check to see if red or blue won
	//Returns true when a winner is found
	public boolean checkWinner(){
		int count = 0;
		//Checks for horizontal 4 in a row by iterating through the columns per one row and returning true
		//if count reaches 4
		for(int row = 0; row<board.length; row++) {
			for(int column = 0; column<board[row].length; column++) {
				if(board[row][column] == turn)
					count++;
				else
					count = 0;
				if(count == 4)
					return true;
			}
			count = 0;
		}
		//Checks for vertical 4 in a row by iterating through the rows per one column and returning true
		//if count reaches 4
		for(int column = 0; column<board[0].length; column++) {
			for(int row = 0; row<board.length; row++) {
				if(board[row][column] == turn)
					count++;
				else
					count = 0;
				if(count == 4)
					return true;
				
			}
			count = 0;
		}
		for(int column = 0, row = 0; row<board.length && column<board[row].length; row++, column++) {
			if(board[row][column] == turn)
				count++;
			else
				count = 0;
			if(count == 4)
				return true;
		}
		for(int i = 0; i<board.length; i++) {
			for(int j = 0, k = i; j<board[i].length && k<board[i].length; j++, k++) {
				if((i+j)<board[i].length && (i+k)<board.length && board[i+k][i+j] == turn)
					count++;
				if(count == 4)	
					return true;
			}
			count = 0;
		}
		return false;
	}
	//function will search through 'column' until it finds an empty space or
	//reaches the top of the column. if there is an empt space, 'add a piece'
	//to the board by inserting -1 or 1 into the two dimensional array at that location and return true
	//Otherwise return false
	public boolean addPiece(int column){
		int row = board.length - 1;
		while(row >= 0 && board[row][column]!=0)
			row-=1;
		if(row>=0) {
			board[row][column]=turn;
			return true;
		}
		return false;
		
	}
	
	// This method should detect which column the user clicked in, add their piece to the board, check to see
	// if anybody has won, switch who's turn it is and then repaint the board.  Also, you shouldn't allow anything to happen
	// if either player has won.
	public void mouseClicked(MouseEvent e) {	
		System.out.println("mouse clicked x-value = " + e.getX() + " y-value = " + e.getY());
		if(addPiece(e.getX()/100)) 
			turn = -turn;
		if(!checkWinner())
			this.repaint();
		if(checkWinner())
			System.out.println("YEAHHHH BOIIIIIII");
		
	}

	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
