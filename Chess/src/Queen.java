//Class: Queen
//Written By: Sam Hecht
//Date: 2/27/19
//Description: The Queen class extends the piece class and has the functionality of a queen piece in chess

import java.awt.Component;
import java.awt.Graphics;
import java.util.ArrayList;

public class Queen extends Piece {
	public Queen() {
		this(1,"images2/queen1.png");
	}
	public Queen(int player) {
		super(player, "images2/queen" + player + ".png");
	}
	public Queen(int player, String imagePath) {
		super(player, imagePath);
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
		//Down Right
		ArrayList<Location> l = new ArrayList<>();
		for(int i = from.getRow(), j = from.getColumn(); i<to.getRow() && j<to.getColumn(); i++, j++) {
			if((board[i][j] instanceof Space) || (board[i][j] instanceof Queen && board[i][j].getPlayer() == board[from.getRow()][from.getColumn()].getPlayer())) {
				l.add(new Location(i,j));
			}
			else
				break;

		}
		//Up right
		for(int i = from.getRow(), j =from.getColumn(); i>to.getRow()&& j<to.getColumn();j++, i--) {
			if((board[i][j] instanceof Space) || (board[i][j] instanceof Queen && board[i][j].getPlayer() == board[from.getRow()][from.getColumn()].getPlayer())) {
				l.add(new Location(i,j));
			}
			else
				break;
		}
		//Down left
		for(int i = from.getRow(), j =from.getColumn(); i<to.getRow()&& j>to.getColumn();j--, i++) {
			if((board[i][j] instanceof Space)  || (board[i][j] instanceof Queen && board[i][j].getPlayer() == board[from.getRow()][from.getColumn()].getPlayer())) {
				l.add(new Location(i,j));
			}
			else
				break;
		}
		//Up left
		for(int i = from.getRow(), j =from.getColumn(); i>to.getRow()&& j>to.getColumn();j--, i--) {
			if((board[i][j] instanceof Space) || (board[i][j] instanceof Queen && board[i][j].getPlayer() == board[from.getRow()][from.getColumn()].getPlayer())) {
				l.add(new Location(i,j));
			}
			else
				break;

		}
		//Now l is full of all of the spaces it can go, and this checks to see if the place they clicked is one of those or is 
		//one more in a diagnal
		for(int i = 0; i<l.size(); i++) {

			if((to.getColumn() == l.get(i).getColumn()+1 && to.getRow() == l.get(i).getRow()+1) ||
					(to.getColumn() == l.get(i).getColumn()-1 && to.getRow() == l.get(i).getRow()+1) ||
					(to.getColumn() == l.get(i).getColumn()+1 && to.getRow() == l.get(i).getRow()-1) ||
					(to.getColumn() == l.get(i).getColumn()-1 && to.getRow() == l.get(i).getRow()-1) || 
					(to.getColumn() == l.get(i).getColumn() && to.getRow() == l.get(i).getRow()))
				return true;
		}
		l.clear();
		//Right
		for(int i = from.getColumn(); i<to.getColumn(); i++) {
			if((board[from.getRow()][i] instanceof Space) || (board[from.getRow()][i] instanceof Queen && board[from.getRow()][i].getPlayer() == board[from.getRow()][from.getColumn()].getPlayer()))
				l.add(new Location(from.getRow(),i));
			else
				break;
		}
		//Left
		for(int i = from.getColumn(); i>to.getColumn(); i--) {
			if((board[from.getRow()][i] instanceof Space) || (board[from.getRow()][i] instanceof Queen && board[from.getRow()][i].getPlayer() == board[from.getRow()][from.getColumn()].getPlayer()))
				l.add(new Location(from.getRow(), i));
			else
				break;
		}
		//Down
		for(int i = from.getRow(); i<to.getRow(); i++) {
			if((board[i][from.getColumn()] instanceof Space) || (board[i][from.getColumn()] instanceof Queen && board[i][from.getColumn()].getPlayer() == board[from.getRow()][from.getColumn()].getPlayer()))
				l.add(new Location(i,from.getColumn()));
			else
				break;
		}
		//Up
		for(int i = from.getRow(); i>to.getRow(); i--) {
			if((board[i][from.getColumn()] instanceof Space) || (board[i][from.getColumn()] instanceof Queen && board[i][from.getColumn()].getPlayer() == board[from.getRow()][from.getColumn()].getPlayer()))
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

}
