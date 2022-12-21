//Class: Rook
//Written By: Sam Hecht
//Date: 2/27/19
//Description: The Rook class extends the piece class and has the functionality of a rook piece in chess, it also adds an instance field
//of int count for castling purposes.

import java.awt.Component;
import java.awt.Graphics;
import java.util.ArrayList;

public class Rook extends Piece {
	private int count;
	public Rook() {
		this(1, "images2/rook2.png",0);
	}
	public Rook(int player) {
		super(player , "images2/rook" + player + ".png");
		count = 0;
	}
	public Rook(int player, String imagePath, int count) {
		super(player, imagePath);
		this.count = count;
	}
	//Method: isValidMove
	//Description: checks to see if the move input by the player is an allowed move in chess
	//Parameters: Location from, Location to, Piece[][] board
	//Return type: boolean
	public boolean isValidMove(Location from, Location to, Piece[][] board) {
		//Returns false if you're trying to move onto your own piece
		if(board[to.getRow()][to.getColumn()].getPlayer() == board[from.getRow()][from.getColumn()].getPlayer()) {
			return false;

		}
		
		ArrayList<Location> l = new ArrayList<>();
		//Right
		for(int i = from.getColumn(); i<to.getColumn(); i++) {
			if((board[from.getRow()][i] instanceof Space) || (board[from.getRow()][i] instanceof Rook && board[from.getRow()][i].getPlayer() == board[from.getRow()][from.getColumn()].getPlayer()))
				l.add(new Location(from.getRow(),i));
			else
				break;
		}
		//Left
		for(int i = from.getColumn(); i>to.getColumn(); i--) {
			if((board[from.getRow()][i] instanceof Space) || (board[from.getRow()][i] instanceof Rook && board[from.getRow()][i].getPlayer() == board[from.getRow()][from.getColumn()].getPlayer()))
				l.add(new Location(from.getRow(), i));
			else
				break;
		}
		//Down
		for(int i = from.getRow(); i<to.getRow(); i++) {
			if((board[i][from.getColumn()] instanceof Space) || (board[i][from.getColumn()] instanceof Rook && board[i][from.getColumn()].getPlayer() == board[from.getRow()][from.getColumn()].getPlayer()))
				l.add(new Location(i,from.getColumn()));
			else
				break;
		}
		//Up
		for(int i = from.getRow(); i>to.getRow(); i--) {
			if((board[i][from.getColumn()] instanceof Space) || (board[i][from.getColumn()] instanceof Rook && board[i][from.getColumn()].getPlayer() == board[from.getRow()][from.getColumn()].getPlayer()))
				l.add(new Location(i,from.getColumn()));
			else
				break;
		}
		//Now l is full of all of the spaces it can go, and this checks to see if the place they clicked is one of those or is 
		//one more in a direction
		for(int i = 0; i<l.size(); i++) {
			if((to.getColumn() == l.get(i).getColumn()+1 && to.getRow() == l.get(i).getRow()) ||
					(to.getColumn() == l.get(i).getColumn()-1 && to.getRow() == l.get(i).getRow()) ||
					(to.getColumn() == l.get(i).getColumn() && to.getRow() == l.get(i).getRow()+1) ||
					(to.getColumn() == l.get(i).getColumn() && to.getRow() == l.get(i).getRow()-1) ||
					(to.getColumn() == l.get(i).getColumn() && to.getRow() == l.get(i).getRow()))
				return true;
		}
		
			
		return false;
	}
	//Method: getCount
	//Description: returns the count of the piece (if its moved or not)
	//Parameters: None
	//Return Type: int
	public int getCount() {
		return count;
	}
	//Method: setCount
	//Description: sets the count of the piece (if its moved or not)
	//Parameters: int count
	//Return Type: void
	public void setCount(int count) {
		this.count = count;
	}
	
}

