//Class: King
//Written By: Sam Hecht
//Date: 2/27/19
//Description: The King class extends the piece class and has the functionality of a king piece in chess, it also adds an instance field
//of int count for castling purposes.
public class King extends Piece {
	private int count;
	public King() {
		this(1,"images2/king1.png",0);
	}
	public King(int player) {
		super(player, "images2/king" + player + ".png");
		setCount(0);
	}
	public King(int player, String imagePath, int count) {
		super(player, imagePath);
		this.setCount(count);
	}
	//Method: isValidMove
	//Description: checks to see if the move input by the player is an allowed move in chess
	//Parameters: Location from, Location to, Piece[][] board
	//Return type: boolean
	public boolean isValidMove(Location from, Location to, Piece[][] board) {
		//Returns false if trying to move on top of your own piece
		if(board[to.getRow()][to.getColumn()].getPlayer() == board[from.getRow()][from.getColumn()].getPlayer())
			return false;
		//Returns true if it is a normal move of the king
		if((to.getRow()+1 == from.getRow() || to.getRow()-1 == from.getRow() || to.getRow() == from.getRow())
				&& (to.getColumn()+1 == from.getColumn() || to.getColumn()-1 == from.getColumn() || to.getColumn() == from.getColumn())
				&& (board[to.getRow()][to.getColumn()].getPlayer() != board[from.getRow()][from.getColumn()].getPlayer())) {
			return true;
		}
		//Returns true if the king hasn't moved yet and is in a position to castle
		if(count == 0 && to.getRow() == from.getRow() && ((getPlayer() == 1 && to.getColumn() == from.getColumn()+2 && board[0][5] instanceof Space && board[0][6] instanceof Space && board[0][7] instanceof Rook && board[0][7].getCount() == 0)
				|| (getPlayer() == 1 && to.getColumn() == from.getColumn()-2 && board[0][3] instanceof Space && board[0][2] instanceof Space && board[0][1] instanceof Space && board[0][0] instanceof Rook && board[0][0].getCount() == 0)
				|| (getPlayer() == 2 && to.getColumn() == from.getColumn()+2 && board[7][5] instanceof Space && board[7][6] instanceof Space && board[7][7] instanceof Rook && board[7][7].getCount() == 0)
				|| (getPlayer() == 2 && to.getColumn() == from.getColumn()-2 && board[7][3] instanceof Space && board[7][2] instanceof Space && board[7][1] instanceof Space && board[7][0] instanceof Rook && board[7][0].getCount() == 0)))
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
