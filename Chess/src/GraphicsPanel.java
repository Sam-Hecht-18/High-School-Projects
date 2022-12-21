// Class: GraphicsPanel
// Written by: Mr. Swope & Sam Hecht
// Date: 12/2/15 & 2/27/19
// Description: This class is the main class for this project.  It extends the Jpanel class and will be drawn on
// 				on the JPanel in the GraphicsMain class. This is where all of the checking for moves and drawing of the board and pieces is done
//
// Since you will modify this class you should add comments that describe when and how you modified the class.  
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.Color;
import javax.swing.JPanel;

public class GraphicsPanel extends JPanel implements MouseListener{




	private final int SQUARE_WIDTH = 90;    // The width of one space on the board.  Constant used for drawing board.
	private final int OFFSET = 30;			//Constant used on the edges of the board to give a little space
	private Location from;   			    // holds the coordinates of the square that the user would like to move from.
	private Location to;   				    // holds the coordinates of the square that the user would like to move to.
	private Piece[][] board; 				// an 8x8 board of 'Pieces'.  Each spot should be filled by one of the chess pieces or a 'space'. 
	private boolean from1;					//A boolean variable which only runs the isValidMove after to has just been set and not after only from has been set
	private int turn;						//An int which represents whose turn it is
	private Location lightUp;				//A location variable used for checking for checking for check and lighting up possible moves
	private boolean check;					//A boolean variable which evaluates to true if a king is in check
	private Piece oldPiece;					//A Piece oldPiece which is used for resetting the board if the player does in invalid move while in check
	public GraphicsPanel()
	{
		setPreferredSize(new Dimension(SQUARE_WIDTH*8+2*(OFFSET+2),SQUARE_WIDTH*8+2*(OFFSET+2)));   // Set these dimensions to the width 
		// of your background picture.   
		this.setFocusable(true);					 // for keylistener
		this.addMouseListener(this);
		board = new Piece[8][8];
		from = new Location(4,4);
		to = new Location(4,4);
		turn = 1;
		//Puts in spaces in every place and then the correct pieces in the correct places after
		for(int i = 0; i<8; i++) {
			for(int j = 0; j<8; j++) {
				board[i][j] = new Space();
			}
		}
		board[0][2] = new Bishop(1);
		board[0][5] = new Bishop(1);
		board[7][2] = new Bishop(2);
		board[7][5] = new Bishop(2);

		board[0][4] = new King(1);
		board[7][4] = new King(2);

		board[0][3] = new Queen(1);
		board[7][3] = new Queen(2);

		board[0][0] = new Rook(1);
		board[0][7] = new Rook(1);
		board[7][0] = new Rook(2);
		board[7][7] = new Rook(2);

		board[1][0] = new Pawn(1);
		board[1][1] = new Pawn(1);
		board[1][2] = new Pawn(1);
		board[1][3] = new Pawn(1);
		board[1][4] = new Pawn(1);
		board[1][5] = new Pawn(1);
		board[1][6] = new Pawn(1);
		board[1][7] = new Pawn(1);

		board[6][0] = new Pawn(2);
		board[6][1] = new Pawn(2);
		board[6][2] = new Pawn(2);
		board[6][3] = new Pawn(2);
		board[6][4] = new Pawn(2);
		board[6][5] = new Pawn(2);
		board[6][6] = new Pawn(2);
		board[6][7] = new Pawn(2);

		board[0][1] = new Knight(1);
		board[0][6] = new Knight(1);
		board[7][1] = new Knight(2);
		board[7][6] = new Knight(2);

		from1 = true;
		lightUp = new Location(0,0);
		check = false;
		oldPiece = new Space();
		// instantiate the instance variables.
	}

	// method: paintComponent
	// description: This method will paint the items onto the graphics panel.  This method is called when the panel is
	//   			first rendered.  It can also be called by this.repaint()
	// parameters: Graphics g - This object is used to draw your images onto the graphics panel.
	public void paintComponent(Graphics g)
	{
		Graphics2D g2 = (Graphics2D) g;

		// Draw the board
		g2.setColor(Color.gray);
		g2.drawLine(SQUARE_WIDTH*8+OFFSET, OFFSET, SQUARE_WIDTH*8+OFFSET, SQUARE_WIDTH*8+OFFSET);
		g2.drawLine(OFFSET, SQUARE_WIDTH*8+OFFSET, SQUARE_WIDTH*8+OFFSET, SQUARE_WIDTH*8+OFFSET);
		g2.drawLine(OFFSET, OFFSET, SQUARE_WIDTH*8+OFFSET, 0+OFFSET);
		g2.drawLine(OFFSET, OFFSET, OFFSET, SQUARE_WIDTH*8+OFFSET);
		//This for loop and the next draw the gray squares onto the board
		for(int i = 0; i <8; i+=2)
			for (int j = 0; j<8; j+=2)
			{
				g2.setColor(Color.gray);
				g2.fillRect(i*SQUARE_WIDTH+OFFSET,j*SQUARE_WIDTH+OFFSET,SQUARE_WIDTH,SQUARE_WIDTH);
			}

		for(int i = 1; i <8; i+=2)
			for (int j = 1; j<8; j+=2){
				g2.setColor(Color.gray);
				g2.fillRect(i*SQUARE_WIDTH+OFFSET,j*SQUARE_WIDTH+OFFSET,SQUARE_WIDTH,SQUARE_WIDTH);
			}

		//Draws the yellow square of possible moves and draws a red square for check by iterating through every piece and checking if any piece's 
		//valid move is onto a king for check, and just using whichever piece the player clicked and checking all spots on the board to see where 
		//isValidMove returns true and lighting them up so the user can easily see where they can move
		for(int f = 0; f<8; f++) {
			for(int h = 0; h<8; h++) {
				for(int i = 0; i<8; i++) {
					for(int j = 0; j<8; j++) {
						lightUp.setRow(i);
						lightUp.setColumn(j);
						if(board[from.getRow()][from.getColumn()].isValidMove(from, lightUp, board)) {
							g2.setColor(Color.YELLOW);
							g2.fillRect(lightUp.getColumn()*90 + OFFSET+10, lightUp.getRow()*90 + OFFSET+10, SQUARE_WIDTH-20, SQUARE_WIDTH-20);
						}
						if(board[f][h].isValidMove(new Location(f,h), lightUp, board) && board[lightUp.getRow()][lightUp.getColumn()] instanceof King) {
							g2.setColor(Color.RED);
							g2.fillRect(lightUp.getColumn()*90 + OFFSET+10, lightUp.getRow()*90 + OFFSET+10, SQUARE_WIDTH-20, SQUARE_WIDTH-20);
							check = true;
						}

					}
				}
			}
		}
		//Draws the top left rectangle
		g2.setColor(Color.gray);
		g2.drawRect(0,0, 30, 30);
		//Fills it in depending on whether it's black or white's turn
		if(turn == 2) {
			g2.setColor(Color.black);
			g2.fillRect(1, 1, 29, 29);
		}
		g2.setColor(Color.gray);
		//Draws the pieces on
		for(int i = 0; i<8; i++) {
			for(int j = 0; j<8; j++) {
				board[i][j].draw(g2, this, new Location(i,j));
			}
		}



	}

	@Override
	public void mouseClicked(MouseEvent e) {

		//Setting the from position
		if(from1 && board[(e.getY()-30)/90][(e.getX()-30)/90].getPlayer() == turn) {
			from.setColumn((e.getX()-30)/90);
			from.setRow((e.getY()-30)/90);
			from1 = false;
		}
		//Setting the to position
		else if(!(board[(e.getY()-30)/90][(e.getX()-30)/90] instanceof King)){
			to.setColumn(e.getX()/100);
			to.setRow(e.getY()/100);
			from1 = true;
		}
		//Checks for check and stops other moves from happening. First, it makes sure the king is in check and where the player clicked is normally a valid move
		//Then, it does the move like normal and everything normal. Next it finds where the king is located and then checks to see if it's still in check
		//If the king is still in check that means the move the player did wasn't legal and it undoes whatever move they did and resets everything, otherwise it allows the move
		if(check && board[from.getRow()][from.getColumn()].isValidMove(from, to, board) && from1) {
			oldPiece = board[to.getRow()][to.getColumn()];
			board[to.getRow()][to.getColumn()] = board[from.getRow()][from.getColumn()];
			board[from.getRow()][from.getColumn()] = new Space();
			turn++;
			check = false;
			board[to.getRow()][to.getColumn()].setCount(board[to.getRow()][to.getColumn()].getCount()+1);
			int kingRow = 0;
			int kingCol = 0;
			//Finds the location of the king
			for(int i = 0; i<8; i++) {
				for(int j = 0; j<8; j++) {
					if(board[i][j] instanceof King && board[i][j].getPlayer() == turn-1) {
						kingCol = j;
						kingRow = i;
					}
				}
			}
			//Checks if the king becomes no longer in check and if not undoes the move
			for(int f = 0; f<8 && !check; f++) {
				for(int h = 0; h<8 && !check; h++) {
					if(board[f][h].isValidMove(new Location(f,h), new Location(kingRow,kingCol), board)) {
						board[from.getRow()][from.getColumn()] = board[to.getRow()][to.getColumn()];
						board[to.getRow()][to.getColumn()] = oldPiece;
						turn--;
						check = true;
						board[from.getRow()][from.getColumn()].setCount(board[from.getRow()][from.getColumn()].getCount()-1);

					}
				}
			}
		}
		//Does a normal move
		else if(board[from.getRow()][from.getColumn()].isValidMove(from, to, board) && from1) {
			board[to.getRow()][to.getColumn()] = board[from.getRow()][from.getColumn()];
			board[from.getRow()][from.getColumn()] = new Space();
			turn++;
			check = false;
			board[to.getRow()][to.getColumn()].setCount(1);
		}

		//Castles
		if(board[to.getRow()][to.getColumn()] instanceof King  && board[to.getRow()][to.getColumn()].getPlayer() == 1 && to.getColumn() - 2 == from.getColumn()) {
			board[0][5] = board[0][7];
			board[0][7] = new Space();
		}
		//Castles
		else if(board[to.getRow()][to.getColumn()] instanceof King && board[to.getRow()][to.getColumn()].getPlayer() == 1 && to.getColumn() + 2 == from.getColumn()) {
			board[0][3] = board[0][0];
			board[0][0] = new Space();
		}
		//Castles
		else if(board[to.getRow()][to.getColumn()] instanceof King && to.getColumn() + 2 == from.getColumn() && from.getRow() == to.getRow()) {
			board[7][3] = board[7][0];
			board[7][0] = new Space();
		}
		//Castles
		else if(board[to.getRow()][to.getColumn()] instanceof King && to.getColumn() - 2 == from.getColumn() && from.getRow() == to.getRow()) {
			board[7][5] = board[7][7];
			board[7][7] = new Space();
		}
		//Pawn Promotion
		for(int i = 0; i<8; i++) {
			if(board[0][i] instanceof Pawn)
				board[0][i] = new Queen(board[0][i].getPlayer());
			if(board[7][i] instanceof Pawn)
				board[7][i] = new Queen(board[7][i].getPlayer());
		}
		//Iterates the turn back to 1 if it gets to 3
		if(turn == 3)
			turn = 1;

		this.repaint();
	}

	@Override
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
