//Class: Pawn
//Written By: Justin Robertson
//Date: 2/27/19
//Description: The Pawn class extends the piece class and has the functionality of a pawn piece in chess, it also adds an instance field
//of int count since the first move of a pawn can be 2 spaces instead of one

public class Pawn extends Piece {
	private int count;
	public Pawn() {
		this(1, 0);

	}
	public Pawn(int player) {
		super(player, "images2/pawn" + player + ".png");
	}
	public Pawn(int player, int count) {
		super(player);
		this.count = count;

	}
	//Method: isValidMove
	//Description: checks to see if the move input by the player is an allowed move in chess
	//Parameters: Location from, Location to, Piece[][] board
	//Return type: boolean
	public boolean isValidMove(Location from, Location to, Piece[][] board){
		//Returns false if you're trying to move onto your own piece
		if(board[to.row][to.column].getPlayer() == board[from.row][from.column].getPlayer())
			return false;
		//Forward 2 white or black
		else if(from.column == to.column && count == 0 && 
				((board[from.row][from.column].getPlayer() == 1 &&  to.row == from.row+2 && board[from.row+1][from.column] instanceof Space)||
						(board[from.row][from.column].getPlayer() == 2 && to.row == from.row-2 && board[from.row-1][from.column] instanceof Space)) &&
				board[to.row][to.column] instanceof Space)     //If there are none of your own pieces directly in front of the pawn return true
			return true;
		//Pawn Promotion
//		else if(from.column == to.column && ((board[from.row][from.column].getPlayer() == 1 && to.row == from.row+1) ||
//				(board[from.row][from.column].getPlayer() == 2 && to.row == from.row-1))&& board[to.row][to.column] instanceof Space &&
//				(to.row == 0 || to.row == 7)) 
//			
//			return true;
		
		//Forward 1 white or black
		else if(from.column == to.column && ((board[from.row][from.column].getPlayer() == 1 && to.row == from.row+1) ||
				(board[from.row][from.column].getPlayer() == 2 && to.row == from.row-1))&& board[to.row][to.column] instanceof Space)
			return true;
		
		//Diagnal Black or White
		else if((from.column == to.column+1 || from.column == to.column-1)&& ((board[from.row][from.column].getPlayer() == 2 && to.row == from.row-1) 
				|| (board[from.row][from.column].getPlayer() == 1 && to.row == from.row+1)) && 
				board[to.row][to.column].getPlayer() != board[from.row][from.column].getPlayer() && !(board[to.row][to.column] instanceof Space))
			return true;
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
