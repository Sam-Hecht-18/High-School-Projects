// Class: Puzzle
// Written by: Sam Hecht
// Date: 6/11/2019
// Description: This class creates the 4x4 puzzle with the smushing of the circles
// It has capabilities to make a board of any size, and contains a 2D array of booleans and rectangles.
//
import java.awt.Rectangle;

public class Puzzle {
	private boolean[][] board;
	private Rectangle[][] rects;
	public Puzzle() {
		this.board = new boolean[4][4];
		this.rects = new Rectangle[4][4];
	}
	public Puzzle(int num) {
		board = (new boolean[num][num]);
		rects = (new Rectangle[num][num]);
		for(int i = 0; i<rects.length; i++)
			for(int j = 0; j<rects[i].length; j++)
				rects[i][j] = new Rectangle(330 + 104*j, 160 + 71*i, 104, 75);
	}
	public boolean[][] getBoard() {
		return board;
	}
	public void setBoard(boolean[][] board) {
		this.board = board;
	}
	public Rectangle[][] getRects() {
		return rects;
		
	}
	public void setRects(Rectangle[][] rects) {
		this.rects = rects;
	}
	//A method which flips the pieces depending on the players location
	public boolean flip(Player p1) {
		int[] dx = {-1,0,1,0};
		int[] dy = {0,1,0,-1};
		for(int i = 0; i<rects.length; i++) {
			for(int j = 0; j<rects[i].length; j++) {
				if(rects[i][j].contains(p1.getBounds())) {
					board[i][j] = !board[i][j];
					for(int k = 0; k<dx.length; k++)
						if(j+dx[k] >= 0 && j+dx[k]<board.length && i+dy[k] >= 0 && i+dy[k]<board.length)
							board[i+dy[k]][j+dx[k]] = !board[i+dy[k]][j+dx[k]];
				}
			}
		}
		for(int i = 0; i<board.length; i++)
			for(int j =0; j<board.length; j++)
				if(!board[i][j])
					return false;
		return true;
	}
	
	
 }
