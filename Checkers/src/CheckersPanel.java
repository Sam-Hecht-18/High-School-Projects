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

public class CheckersPanel extends JPanel implements MouseListener{

	private int turn;		// will equal -1 or 1 to represent who's turn it is. use -1 for blue 1 for red
	private int[][] board;  // two-dimensional array of ints.  All values should be initialized to 0.  When
	// blue places a checker value should be changed to -1.  When red places a checker
	// value should be changed to 1.
	private static final int WIDTH = 801;
	private static final int HEIGHT = 801;
	private Location from;
	private Location to;

	// method: ConnectFourPanel Constructor
	// description: This 'method' runs when a new instance of this class in instantiated.  It sets default values  
	// that are necessary to run this project.  
	public CheckersPanel(){
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		this.setFocusable(true);			// for mouselistener
		this.addMouseListener(this);
		board = new int[8][8];
		for(int i = 0; i<board[0].length; i+=2)
			board[0][i] = -1;
		for(int i = 1; i<board[1].length; i+=2)
			board[1][i] = -1;
		for(int i = 0; i<board[2].length; i+=2)
			board[2][i] = -1;
		for(int i = 1; i<board[5].length; i+=2)
			board[5][i] = 1;
		for(int i = 0; i<board[6].length; i+=2)
			board[6][i] = 1;
		for(int i = 1; i<board[7].length; i+=2)
			board[7][i] = 1;
		turn = 1;
		from = new Location(0,1);
		to = new Location(0,3);
	}

	// method: paintComponent
	// description: This method is called when the Panel is painted.  It contains 
	// code that draws shapes onto the panel.
	// parameters: Graphics g - this object is used to draw shapes onto the JPanel.
	// return: void
	public void paintComponent(Graphics g){
		Graphics2D g2 = (Graphics2D) g;

		
		for(int i = 0; i<board.length; i++) {
			for(int j = 0; j<board[i].length; j++) {
				if((i+j)%2 == 0) {
					g2.setColor(Color.DARK_GRAY);
					g2.fillRect(i*100, j*100, 100, 100);
					System.out.println("HAWWW");
				}
				else {
					System.out.println("YEEEE");
					g2.setColor(Color.WHITE);
					g2.fillRect(i*100, j*100, 100, 100);
				}
			}
		}
		// this code loads an image so that you can later paint it onto your component.
		// this would load a picture named blue.png that should be saved in a folder
		// named images, which should be located in your src folder.
		ImageIcon blueImage;
		ImageIcon redImage;
		ImageIcon greenImage;
		ImageIcon yellowImage;

		ClassLoader cldr = this.getClass().getClassLoader();
		String imagePath = "images/blue.png";
		URL imageURL = cldr.getResource(imagePath);
		blueImage = new ImageIcon(imageURL);

		imagePath = "images/red.png";
		imageURL = cldr.getResource(imagePath);
		redImage = new ImageIcon(imageURL);

		imagePath = "images/green.png";
		imageURL = cldr.getResource(imagePath);
		greenImage = new ImageIcon(imageURL);
		
		imagePath = "images/yellow.png";
		imageURL = cldr.getResource(imagePath);
		yellowImage = new ImageIcon(imageURL);
		// draws the connect four board	
		
		// this is how you paint your image. the last two parameters are the x and y coordinates 
		// of the top left hand corner of the image.  Right now this will only draw one blue checker
		// and one red image drawn on the board.  You should instead use nested for loops to loop 
		// through your two-dimensional array and paint blue checkers where ever there is -1 and 
		// a red checker wherever there is 1 in the array.
		g2.setColor(Color.YELLOW);
		if((from.getColumn()+from.getRow())%2 == 0 && board[from.getRow()][from.getColumn()] == turn)
			g2.fillRect(from.getColumn()*100, from.getRow()*100, 100, 100);
		for(int r = 0; r < board.length; r++){
			for(int c = 0; c < board[0].length; c++){
				if(board[r][c] == -1 )
					blueImage.paintIcon(this, g, c*100+10, r*100+10);
				else if(board[r][c] == 1 )
					redImage.paintIcon(this, g, c*100+10, r*100+10);
				else if(board[r][c] == 2 )
					yellowImage.paintIcon(this, g, c*100+10, r*100+10);
				else if(board[r][c] == -2 )
					greenImage.paintIcon(this, g, c*100+10, r*100+10);
					
			}
		}
		
		// Display a message if either red or blue has won the game.
		g2.setFont(new Font("Verdana", 0, 40));
		if(!checkWinner() && turn == -1) {
			g2.setColor(Color.red);
			g2.drawString("Red", 400, 400);
			g2.drawString("Wins!", 375, 450);
		}
		if(!checkWinner() && turn == 1) {
			g2.setColor(Color.blue);
			g2.drawString("Blue", 385, 400);
			g2.drawString("Wins!", 375, 450);
		}
	}

	// Check to see if red or blue won
	//Returns true when a winner is found
	public boolean checkWinner(){
		for(int r = 0; r<board.length; r++) {
			for(int c = 0; c<board.length; c++) {
				if(board[r][c] == turn)
					return true;
				else if(board[r][c] == turn*2)
					return true;
			}
		}
		return false;
	}
	//function will search through 'column' until it finds an empty space or
	//reaches the top of the column. if there is an empt space, 'add a piece'
	//to the board by inserting -1 or 1 into the two dimensional array at that location and return true
	//Otherwise return false
	public int king() {
		for(int c = 0; c<board[0].length; c+=2)
			if(board[0][c] == 1)
				return c;
		for(int c = 1; c<board[board.length-1].length; c+=2)
			if(board[board.length-1][c] == -1)
				return c;
		return -1;
	}
	public boolean kingJumpPiece() {
		if(Math.abs((to.getColumn() - from.getColumn())) == 2 && Math.abs(to.getRow()-from.getRow()) == 2 &&
				((board[from.getRow() + (to.getRow()-from.getRow())/2][from.getColumn() + (to.getColumn()-from.getColumn())/2] == -turn)
				|| (board[from.getRow() + (to.getRow()-from.getRow())/2][from.getColumn() + (to.getColumn()-from.getColumn())/2] == -2*turn)))
			return true;
		return false;
	}
	public boolean kingDoubleJump() {
		if(board[from.getRow()][from.getColumn()] == -2*turn && (board[to.getRow()][to.getColumn()] == 0 && (to.getRow()+to.getColumn())%2 == 0
				&& Math.abs(to.getColumn()-from.getColumn()) == 2 && Math.abs(to.getRow()-from.getRow()) == 2 && 
				((board[from.getRow() + ((to.getRow()-from.getRow())/2)][from.getColumn() + (to.getColumn()-from.getColumn())/2] == turn) || 
				(board[from.getRow() + (to.getRow()-from.getRow())/2][from.getColumn() + (to.getColumn()-from.getColumn())/2] == 2*turn))))
			return true;
		return false;
	}
	public boolean doubleJump() {
		System.out.println(board[from.getRow()][from.getColumn()]+ " = " + -turn);
		if(board[from.getRow()][from.getColumn()] == -turn && (board[to.getRow()][to.getColumn()] == 0 && (to.getRow()+to.getColumn())%2 == 0
				&& to.getColumn()-from.getColumn() == 2 && to.getRow()-from.getRow() == 2*turn && (board[from.getRow()+turn][from.getColumn()+1] == turn) || 
				(board[from.getRow()+turn][from.getColumn()+1] == turn*2)))
				return true;
		else if(board[from.getRow()][from.getColumn()] == -turn && board[to.getRow()][to.getColumn()] == 0 && (to.getRow()+to.getColumn())%2 == 0 &&
				to.getColumn()-from.getColumn() == -2 && to.getRow()-from.getRow() == 2*turn && (board[from.getRow()+turn][from.getColumn()-1] == turn
				|| board[from.getRow()+turn][from.getColumn()-1] == 2*turn))
			return true;
		return false;
	}
	public boolean kingNormalMove() {
		if(Math.abs(to.getColumn()-from.getColumn())<=1 && Math.abs(to.getRow()-from.getRow())<=1 )
			return true;
		return false;
	}
	public boolean kingLegalSpot() {
		if(board[from.getRow()][from.getColumn()] == 2*turn && board[to.getRow()][to.getColumn()] == 0 && (to.getRow()+to.getColumn())%2 == 0)
			return true;
		return false;
	}
	public boolean legalSpot(){
		
		if(board[from.getRow()][from.getColumn()] == turn && board[to.getRow()][to.getColumn()] == 0 && (to.getRow()+to.getColumn())%2 == 0)
			return true;

		else
			return false;
	}
	public boolean jumpPiece() {
		if(to.getColumn()-from.getColumn() == 2 && to.getRow()-from.getRow() == -2*turn && ((board[from.getRow()-turn][from.getColumn()+1] == -turn) || 
				(board[from.getRow()-turn][from.getColumn()+1] == -turn*2)))
			return true;
		else if(to.getColumn()-from.getColumn() == -2 && to.getRow()-from.getRow() == -2*turn && (board[from.getRow()-turn][from.getColumn()-1] == -turn
				|| board[from.getRow()-turn][from.getColumn()-1] == -2*turn))
			return true;
		
		else
			return false;
	}
			/*if(legalSpot()) {
			if(to.getColumn()<from.getColumn() && board[to.getRow()][to.getColumn()] == board[from.getRow()-2*turn][from.getColumn()-2])
				if(board[from.getRow()-turn][from.getColumn()+1] == -turn)
					return true;
			if(to.getColumn()>from.getColumn() && (board[to.getRow()][to.getColumn()] == board[from.getRow()-2*turn][from.getColumn()+2]))
				if(board[from.getRow()-turn][from.getColumn()-1] == -turn)
					return true;
		}

		if(board[from.getRow()-turn][from.getColumn()+1] == -turn || board[from.getRow()-turn][from.getColumn()-1] == -turn)
			 */	
			//		if((board[from.getRow()-turn][from.getColumn()+1] == -turn || board[from.getRow()-turn][from.getColumn()-1] == -turn)
			//				&& ((board[to.getRow()][to.getColumn()] == board[from.getRow()+2*turn][from.getColumn()+2]) 
			//						|| (board[to.getRow()][to.getColumn()] == board[from.getRow()+2*turn][from.getColumn()-2])))
	public boolean normalMove() {
		if(to.getRow() - from.getRow() == -turn && Math.abs(to.getColumn()-from.getColumn()) == 1)
			return true;
		return false;
	}
	

	// This method should detect which column the user clicked in, add their piece to the board, check to see
	// if anybody has won, switch who's turn it is and then repaint the board.  Also, you shouldn't allow anything to happen
	// if either player has won.
	public void mouseClicked(MouseEvent e) {	
		System.out.println("mouse clicked x-value = " + e.getX() + " y-value = " + e.getY());
		for(int i = 0; i<board.length; i++) {
			for(int j = 0; j<board[i].length; j++)
				System.out.print(board[i][j] + " ");
			System.out.println();
		}
		System.out.println(turn);
		System.out.println(board[e.getX()/100][e.getY()/100]);
		//Sets what piece is being moved if the player clicks their own piece
		if(board[e.getY()/100][e.getX()/100] == turn || board[e.getY()/100][e.getX()/100] == 2*turn) {
			from.setRow(e.getY()/100);
			from.setColumn(e.getX()/100);
			System.out.println("Setting 'from' to last click");

		}
		//Sets where the piece is going if player clicks on an empty space
		else {
			to.setRow(e.getY()/100);
			to.setColumn(e.getX()/100);
			System.out.println("Setting 'to' to last click");
		}
		System.out.println(from.getRow());
		System.out.println(to.getRow());
		System.out.println(from.getColumn());
		System.out.println(to.getColumn());
		//Checks to make sure pieces are going where they're allowed
		if(doubleJump()) {
			board[from.getRow()][from.getColumn()] = 0;
			board[to.getRow()][to.getColumn()] = -turn;
			board[from.getRow()+turn][(from.getColumn()+ ((to.getColumn()-from.getColumn())/2))] = 0;
			from.setRow(to.getRow());
			from.setColumn(to.getColumn());
			System.out.println("ur insane");
		}
		
		else if(legalSpot() && jumpPiece()){
			board[from.getRow()][from.getColumn()] = 0;
			board[to.getRow()][to.getColumn()] = turn;
			board[from.getRow()-turn][(from.getColumn()+ ((to.getColumn()-from.getColumn())/2))] = 0;
			turn = -turn;
			from.setRow(to.getRow());
			from.setColumn(to.getColumn());
			System.out.println("Eighth");
			
		}
		//Moves the piece (from) from (to) to
		else if(legalSpot() && normalMove()){
			board[from.getRow()][from.getColumn()] = 0;
			board[to.getRow()][to.getColumn()] = turn;
			System.out.println("Fifth");
			turn = -turn;

		}
		else if(kingDoubleJump()) {
			board[from.getRow()][from.getColumn()] = 0;
			board[to.getRow()][to.getColumn()] = -2*turn;
			board[from.getRow() + (to.getRow()-from.getRow())/2][from.getColumn() + (to.getColumn()-from.getColumn())/2] = 0;
			from.setRow(to.getRow());
			from.setColumn(to.getColumn());
		}
		else if(kingLegalSpot() && kingNormalMove()) {
			board[from.getRow()][from.getColumn()] = 0;
			board[to.getRow()][to.getColumn()] = 2*turn;
			turn = -turn;
			System.out.println("YAY!");
		}
		else if(kingLegalSpot() && kingJumpPiece()) {
			board[from.getRow()][from.getColumn()] = 0;
			board[to.getRow()][to.getColumn()] = 2*turn;
			board[from.getRow() + (to.getRow()-from.getRow())/2][from.getColumn() + (to.getColumn()-from.getColumn())/2] = 0;
			from.setRow(to.getRow());
			from.setColumn(to.getColumn());
			System.out.println(from.getRow());
			System.out.println(from.getColumn());
			turn = -turn;
		}
			
			
		if(king() != -1 && turn == 1)
			board[7][king()] = -2;
		else if(king() != -1 && turn == -1)
			board[0][king()] = 2;
		this.repaint();

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

