//Class: Knight
//Written By: Luke Hu
//Date: 2/27/19
//Description: The Knight class extends the piece class and has the functionality of a knight piece in chess
public class Knight extends Piece {
	public Knight() {
		this(1, "images2/knight1.png");
	}
	public Knight(int player) {
		super(player, "images2/knight" + player + ".png");
	}
	public Knight(int player, String imagePath) {
		super(player, imagePath);
	}
	//Method: isValidMove
	//Description: checks to see if the move input by the player is an allowed move in chess
	//Parameters: Location from, Location to, Piece[][] board
	//Return type: boolean
	public boolean isValidMove(Location from, Location to, Piece[][] board) {
		//Returns false if you're trying to move onto your own piece
		if(board[to.row][to.column].getPlayer() == board[from.row][from.column].getPlayer())
			return false;
		if((from.row-2 == to.row && from.column-1 == to.column)||//Left 1 Up 2
				(from.row-1 == to.row && from.column-2 == to.column)||//Left 2 Up 1
				(from.row-2 == to.row && from.column+1 == to.column)||//Right 1 Up 2
				(from.row-1 == to.row && from.column+2 == to.column)||//Right 2 Up 1
				//Downs
				(from.row+2 == to.row && from.column+1 == to.column)||//Right 1 Down 2
				(from.row+2 == to.row && from.column-1 == to.column)||//Left 1 Down 2
				(from.row+1 == to.row && from.column+2 == to.column)||//Right 2 Down 1
				(from.row+1 == to.row && from.column-2 == to.column)//Left 2 Down 1
				)
			return true;

		return false;
	}
}

