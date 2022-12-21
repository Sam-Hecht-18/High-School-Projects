//Class: Space
//Written By: Sam Hecht
//Date: 2/27/19
//Description: The Space class extends the piece class and does nothing except take up empty spaces.
//It also has a player of 0 so it's on neither team.
public class Space extends Piece {
	public Space() {
		this(0,"images2/space.png");
	}
	public Space(int player, String imagePath) {
		super(player, imagePath);
	}
}
