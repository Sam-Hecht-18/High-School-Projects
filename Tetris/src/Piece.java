import java.awt.Component;
import java.awt.Graphics2D;
import java.util.HashMap;

import javax.swing.ImageIcon;

public class Piece {

	private int[][] piece;
	private String name;
	private int picShowing;
	private int numOfPics;
	private int x;
	private int y;
	private String color;
	private ImageIcon image;
	private int type;
	public Piece(int type) {
		this.type = type + 1;
		y = 0;
		x = 4;
		piece = new int[4][4];
		picShowing = 1;
		
		
		switch(type){
		//Red J
		case 0: 
			color = "Red";
			numOfPics = 4;
			name = "J";
			break;
		//Yellow T
		case 1: 
			color = "Yellow";
			numOfPics = 4;
			name = "T";
			break;
		//Green L
		case 2: 
			color = "Green";
			numOfPics = 4;
			name = "L";
			break;
		//Turquoise S
		case 3: 
			color = "Turquoise";
			numOfPics = 2;
			name = "S";
			break;
		//Light Blue I
		case 4: 
			color = "Blue";
			numOfPics = 2;
			name = "I";
			break;
		//Blue O
		case 5: 
			color = "Dark Blue";
			numOfPics = 1;
			name = "O";
			break;
		//Purple Z
		case 6: 
			color = "Purple";
			numOfPics = 2;
			name = "Z";
			break;
		}
		piece = GraphicsPanel.rotations.get(name + picShowing);
		image = new ImageIcon("images/" + color + " Dot.png");
	}
	public int getType() {
		return type;
	}
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	public void increaseY() {
		y++;
	}
	
	public void moveX(int direction) {
		x+=direction;
	}
	public void rotate(int[][] board) {
		
		if(picShowing == numOfPics) {
			picShowing = 1;
		}
		else {
			picShowing++;
		}
		
		piece = GraphicsPanel.rotations.get(name + picShowing + "");
		for(int i = 0; i < piece.length; i++) {
			for(int j = 0; j < piece[i].length; j++) {
				if(piece[i][j] == 9 && (i + y >= board.length || j + x >= board[i+y].length || j + x < 0 || (board[i + y][j + x] != 9 && board[i + y][j + x] != 0))) {
					if(picShowing == 1)
						picShowing = numOfPics;
					else {
						picShowing--;
					}
					piece = GraphicsPanel.rotations.get(name + picShowing + "");
					return;
				}
			}
		}
		
	}
	public int[][] getPiece() {
		return piece;
	}
	public void draw(Component c, Graphics2D g, int x, int y) {
		image.paintIcon(c, g, x*40, y*40);
	}
	
}
