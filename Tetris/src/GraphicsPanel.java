import java.awt.event.KeyListener;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;




public class GraphicsPanel extends JPanel implements KeyListener{ 
	private Timer t;
	public static HashMap<String, int[][]> rotations;
	private int[][] board;
	private Piece currentPiece;
	private Piece nextPiece;
	private ArrayList<Integer> currentXs;
	private ArrayList<Integer> currentYs;
	private boolean timeToGoDown;
	private ImageIcon[] images;
	private int flash;
	private int flashingRow;
	private int score;
	private int level;
	private boolean gameOver;

	public GraphicsPanel() {
		rotations = new HashMap<>();
		timeToGoDown = false;
		gameOver = false;
		flashingRow = -1;
		flash = 0;
		score = 0;
		level = 0;
		int[][] location = new int[4][4];

		int[][] location1 = new int[4][4];
		int[][] location2 = new int[4][4];
		int[][] location3 = new int[4][4];
		int[][] location4 = new int[4][4];
		int[][] location5 = new int[4][4];
		int[][] location6 = new int[4][4];
		int[][] location7 = new int[4][4];
		int[][] location8 = new int[4][4];
		int[][] location9 = new int[4][4];
		int[][] location10 = new int[4][4];
		int[][] location11 = new int[4][4];
		int[][] location12 = new int[4][4];
		int[][] location13 = new int[4][4];
		int[][] location14 = new int[4][4];
		int[][] location15 = new int[4][4];
		int[][] location16 = new int[4][4];
		int[][] location17 = new int[4][4];
		int[][] location18 = new int[4][4];

		location[0][0] = 9;
		location[0][1] = 9;
		location[0][2] = 9;
		location[0][3] = 9;


		rotations.put("I1", location);

		location1[0][1] = 9;
		location1[1][1] = 9;
		location1[2][1] = 9;
		location1[3][1] = 9;

		rotations.put("I2", location1);

		location2[2][0] = 9;
		location2[0][1] = 9;
		location2[1][1] = 9;
		location2[2][1] = 9;

		rotations.put("J1", location2);

		location3[0][0] = 9;
		location3[1][0] = 9;
		location3[1][1] = 9;
		location3[1][2] = 9;

		rotations.put("J2", location3);

		location4[0][0] = 9;
		location4[0][1] = 9;
		location4[1][0] = 9;
		location4[2][0] = 9;

		rotations.put("J3", location4);

		location5[0][0] = 9;
		location5[0][1] = 9;
		location5[0][2] = 9;
		location5[1][2] = 9;

		rotations.put("J4", location5);

		location6[0][0] = 9;
		location6[0][1] = 9;
		location6[0][2] = 9;
		location6[1][0] = 9;

		rotations.put("L1", location6);

		location7[0][0] = 9;
		location7[0][1] = 9;
		location7[1][1] = 9;
		location7[2][1] = 9;

		rotations.put("L2", location7);

		location8[1][0] = 9;
		location8[1][1] = 9;
		location8[1][2] = 9;
		location8[0][2] = 9;

		rotations.put("L3", location8);

		location9[0][0] = 9;
		location9[1][0] = 9;
		location9[2][0] = 9;
		location9[2][1] = 9;

		rotations.put("L4", location9);

		location10[0][0] = 9;
		location10[0][1] = 9;
		location10[1][1] = 9;
		location10[1][0] = 9;

		rotations.put("O1", location10);

		location11[1][0] = 9;
		location11[1][1] = 9;
		location11[0][1] = 9;
		location11[0][2] = 9;

		rotations.put("S1", location11);

		location12[0][0] = 9;
		location12[1][0] = 9;
		location12[1][1] = 9;
		location12[2][1] = 9;

		rotations.put("S2", location12);

		location13[1][0] = 9;
		location13[1][1] = 9;
		location13[1][2] = 9;
		location13[0][1] = 9;

		rotations.put("T1", location13);

		location14[0][1] = 9;
		location14[1][1] = 9;
		location14[2][1] = 9;
		location14[1][2] = 9;

		rotations.put("T2", location14);

		location15[1][0] = 9;
		location15[1][1] = 9;
		location15[1][2] = 9;
		location15[2][1] = 9;

		rotations.put("T3", location15);

		location16[1][0] = 9;
		location16[0][1] = 9;
		location16[1][1] = 9;
		location16[2][1] = 9;

		rotations.put("T4", location16);

		location17[0][0] = 9;
		location17[0][1] = 9;
		location17[1][1] = 9;
		location17[1][2] = 9;

		rotations.put("Z1", location17);

		location18[2][0] = 9;
		location18[1][0] = 9;
		location18[1][1] = 9;
		location18[0][1] = 9;

		rotations.put("Z2", location18);



		images = new ImageIcon[7];
		images[0] = new ImageIcon("images/Red Dot.png");
		images[1] = new ImageIcon("images/Yellow Dot.png");
		images[2] = new ImageIcon("images/Green Dot.png");
		images[3] = new ImageIcon("images/Turquoise Dot.png");
		images[4] = new ImageIcon("images/Blue Dot.png");
		images[5] = new ImageIcon("images/Dark Blue Dot.png");
		images[6] = new ImageIcon("images/Purple Dot.png");

		t = new Timer(500,new ClockListener(this));
		t.start();
		board = new int[18][10];
		setPreferredSize(new Dimension(560,720));
		this.setFocusable(true);					
		this.addKeyListener(this);
		currentPiece = spawnPiece();
		nextPiece = spawnPiece();
		currentXs = new ArrayList<>();
		currentYs = new ArrayList<>();
		for(int i = 0; i < currentPiece.getPiece().length; i++) {
			for(int j = 0; j < currentPiece.getPiece()[i].length; j++) {
				if(currentPiece.getPiece()[i][j] == 2) {
					board[i + currentPiece.getY()][j + currentPiece.getX()] = 2;
					currentXs.add(j + currentPiece.getX());
					currentYs.add(i + currentPiece.getY());
				}
			}
		}
		currentXs.trimToSize();
		currentYs.trimToSize();
		

	}
	public void paintComponent(Graphics g) {

		t.setDelay(500 - (level/10) * 100);
		Graphics2D g2 = (Graphics2D) g;
		
		g2.fillRect(400, 0, 160, 720);
		g2.setColor(Color.RED);
		g2.setFont(new Font("Times New Roman", Font.BOLD, 20));
		g2.drawString("Level", 450, 200);
		g2.drawString(1 + level/10 + "", 470, 225);

		g2.drawString("Score", 450, 260);
		g2.drawString(score + "", 470, 285);

		g2.drawString("Rows Cleared", 420, 320);
		g2.drawString(level + "", 470, 345);

		allowedToGoDown();
		for(int i = 0; i < board.length; i++) {
			for(int j = 0; j < board[i].length; j++) {
				if(board[i][j] != 0 && board[i][j] != 9) {
					images[board[i][j]-1].paintIcon(this, g2, j*40, i*40);
				}
				else if(board[i][j] == 9) {
					images[currentPiece.getType()-1].paintIcon(this, g2, j*40, i*40);
				}
			}
		}
		for(int i = 0; i < nextPiece.getPiece().length; i++) {
			for(int j = 0; j < nextPiece.getPiece()[i].length; j++) {
				if(nextPiece.getPiece()[i][j] == 9)
					images[nextPiece.getType()-1].paintIcon(this, g2, 400 + 40 * j, 20 + 40 * i);
			}
		}
		
		if(flash >= 0 && flash <= 100 && flash%2 == 0) {
			flash++;
			g2.setColor(Color.WHITE);
			g2.fillRect(0, flashingRow * 40, 400, 40);
			this.repaint();

		}
		else if(flash >= 0 && flash <= 100 && flash%2 == 1) {
			flash++;
			g2.setColor(Color.GRAY);
			g2.fillRect(0, flashingRow * 40, 400, 40);
			this.repaint();

		}
		if(gameOver) {
			t.stop();
			g2.setFont(new Font("Times New Roman", Font.BOLD, 50));
			g2.setColor(Color.BLACK);
			g2.drawString("Game Over", 80, 200);
			g2.drawString("Press r to restart", 20, 300);
		}


	}
	public Piece spawnPiece() {
		int randPiece = (int)(Math.random()*7);
		return new Piece(randPiece);


	}
	public void allowedToGoDown() {
		boolean stop = false;

		for(int i = 0; i < currentXs.size(); i++) {
			if(currentYs.get(i) == board.length-1 || (board[currentYs.get(i) + 1][currentXs.get(i)] != 9 && board[currentYs.get(i) + 1][currentXs.get(i)] != 0)) {
				stop = true;
			}
		}
		if(stop && timeToGoDown) {
			for(int i = 0; i < currentXs.size(); i++) {
				if(currentYs.get(i) == 0) {
					gameOver = true;
				}
				board[currentYs.get(i)][currentXs.get(i)] = currentPiece.getType();
			}
			checkForDeletions();
			currentPiece = nextPiece;
			nextPiece = spawnPiece();
			currentXs.clear();
			currentYs.clear();
			timeToGoDown = false;
			this.repaint();
			return;
		}
		if(timeToGoDown) {
			currentPiece.increaseY();
			timeToGoDown = false;
		}
		for(int i = 0; i < currentXs.size(); i++) {
			board[currentYs.get(i)][currentXs.get(i)] = 0;
		}
		currentXs.clear();
		currentYs.clear();
		for(int i = 0; i < currentPiece.getPiece().length; i++) {
			for(int j = 0; j < currentPiece.getPiece()[i].length; j++) {
				if(currentPiece.getPiece()[i][j] == 9 && i + currentPiece.getY() <  board.length && 
						(board[i+currentPiece.getY()][j + currentPiece.getX()] == 0 || board[i+currentPiece.getY()][j + currentPiece.getX()] == 9)) {
					board[i + currentPiece.getY()][j + currentPiece.getX()] = 9;
					currentXs.add(j + currentPiece.getX());
					currentYs.add(i + currentPiece.getY());	

				}
			}
		}
		


	}
	public void checkForDeletions() {
		boolean deleteRow = false;
		int scoreCount = 0;
		for(int i = 0; i < currentYs.size(); i++) {
			for(int j = 0; j < board[currentYs.get(i)].length; j++) {
				if(board[currentYs.get(i)][j] == 0)
					break;
				else if(j == board[currentYs.get(i)].length-1) {
					deleteRow = true;
				}

			}
			if(deleteRow) {
				scoreCount++;
				flashingRow = currentYs.get(i);
				flash = 0;
				for(int j = 0; j < board[currentYs.get(i)].length; j++) {
					board[currentYs.get(i)][j] = 0;
				}
				for(int f = currentYs.get(i); f > 0; f--) {
					for(int j = 0; j < board[currentYs.get(i)].length; j++) {
						board[f][j] = board[f-1][j];
					}
				}
				level++;
				deleteRow = false;

			}


		}
		if(scoreCount == 1) {
			score += 40 + level/10 * 40;
		}
		else if(scoreCount == 2) {
			score += 100 + level/10 * 100;
		}
		else if(scoreCount == 3) {
			score += 300 + level/10 * 300;
		}
		else if(scoreCount == 4) {
			score += 1200 + level/10 * 1200;
		}
	}
	public void clock() {
		this.repaint();
		timeToGoDown = true;
	}
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(!gameOver) {
			switch(e.getKeyCode()) {
			case KeyEvent.VK_LEFT:
				for(int i = 0; i < currentXs.size(); i++) {
					if(currentXs.get(i) <= 0 || (board[currentYs.get(i)][currentXs.get(i) - 1] != 9 && board[currentYs.get(i)][currentXs.get(i) - 1] != 0)) {
						return;
					}
				}

				currentPiece.moveX(-1);
				this.repaint();
				break;
			case KeyEvent.VK_RIGHT:
				for(int i = 0; i < currentXs.size(); i++) {
					if(currentXs.get(i) >= board[0].length-1 || (board[currentYs.get(i)][currentXs.get(i) + 1] != 9 && board[currentYs.get(i)][currentXs.get(i) + 1] != 0)) {
						return;
					}
				}

				currentPiece.moveX(1);
				this.repaint();
				break;
			case KeyEvent.VK_UP:
				currentPiece.rotate(board);
				this.repaint();
				break;
			case KeyEvent.VK_DOWN:
				timeToGoDown = true;
				allowedToGoDown();
				this.repaint();
				break;
			case KeyEvent.VK_P:
				if(t.isRunning()) {
					t.stop();
				}
				else {
					t.start();
				}
			}
		}
		if(e.getKeyCode() == KeyEvent.VK_R && gameOver) {
			gameOver = false;
			board = new int[18][10];
			currentPiece = spawnPiece();
			nextPiece = spawnPiece();
			score = 0;
			level = 0;
			t.start();
		}

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}
}