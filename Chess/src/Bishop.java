//Class: Bishop
//Written By: Sam Hecht
//Date: 2/27/19
//Description: The Bishop class extends the piece class and has the functionality of a bishop piece in chess
import java.util.ArrayList;

public class Bishop extends Piece {
	public Bishop() {
		this(1,"images2/bishop1.png");
	}
	public Bishop(int player) {
		super(player, "images2/bishop" + player + ".png");
	}
	public Bishop(int player, String imagePath) {
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
		ArrayList<Location> l = new ArrayList<>();
		//Down right
		for(int i = from.getRow(), j = from.getColumn(); i<to.getRow() && j<to.getColumn(); i++, j++) {
			if((board[i][j] instanceof Space) || (board[i][j] instanceof Bishop && board[i][j].getPlayer() == board[from.getRow()][from.getColumn()].getPlayer())) {
				l.add(new Location(i,j));
			}
			else
				break;

		}
		//Up right
		for(int i = from.getRow(), j =from.getColumn(); i>to.getRow()&& j<to.getColumn();j++, i--) {
			if((board[i][j] instanceof Space) || (board[i][j] instanceof Bishop && board[i][j].getPlayer() == board[from.getRow()][from.getColumn()].getPlayer())) {
				l.add(new Location(i,j));
			}
			else
				break;
		}
		//Down left
		for(int i = from.getRow(), j =from.getColumn(); i<to.getRow()&& j>to.getColumn();j--, i++) {
			if((board[i][j] instanceof Space)  || (board[i][j] instanceof Bishop && board[i][j].getPlayer() == board[from.getRow()][from.getColumn()].getPlayer())) {
				l.add(new Location(i,j));
			}
			else
				break;
		}
		//Up left
		for(int i = from.getRow(), j =from.getColumn(); i>to.getRow()&& j>to.getColumn();j--, i--) {
			if((board[i][j] instanceof Space) || (board[i][j] instanceof Bishop && board[i][j].getPlayer() == board[from.getRow()][from.getColumn()].getPlayer())) {
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
		return false;
	}
}
