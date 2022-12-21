// Class: GraphicsPanel
// Written by: Sam Hecht
// Date: 6/11/2019
// Description: This class is the main class for this project.  It extends the Jpanel class and will be drawn on
// 				on the JPanel in the GraphicsMain class. 
//

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.Font;


public class GraphicsPanel extends JPanel implements KeyListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1779436070426648549L;
	private Timer t;							 
	private Screens screens;
	private Player p1;
	private int direction;
	char letter;
	private int count;
	private int[] score;
	private boolean keyPressed;
	private int index;
	private boolean[] key;
	public boolean start;
	private boolean isBig;
	private ImageIcon keyPic;
	private ImageIcon ballUp;
	private ImageIcon ballDown;
	private ImageIcon arrow;
	private ImageIcon arrowUp;
	private ImageIcon explosion;
	private String guess;
	private String [] answer;
	private Rectangle button;
	private Puzzle puzzle;
	private boolean startPuzzle;
	private int nextPart;
	private Rectangle[] mazeBoundaries;
	private Player boss;
	private Player boss2;
	private boolean shooting;
	private Rectangle [] flashingLights;
	private int[] hintIndexes;
	private int hintCount;
	private int oldHintCount;
	private int difficulty;
	private String[][] hints = {{"You just got a hint!"},
			{"s","e","c","r","e","t"},
			{"1","6"},
			{"0","1", "2", "3","4","5","6","7","8","9","10","11","12","13","14","15"},
			{"Smush them all", "6","1","3", "9", "11", "12", "15", "16"},
			{"Type them fast", "Difficulty was decreased", "Difficulty was decreased again"},
			{"Click space on the torch ", "Light the chandelier, each problem corresponds to a candle", "So for #1, click spacebar on the 8th candle", "% symbol means remainder after division","#7 has to do with how you speak the numbers", "8" , "1", "6", "5", "3", "2", "11", "4" , "7", "9", "10"},
			{"Look at the Bookcases", "b", "r","o","w","n"},
			{"Press space to shoot when the boss stops shooting","There are 5 phases, if you hit the boss, he grows hair", "2", "3", "4", "5"}};



	public GraphicsPanel(){


		setPreferredSize(new Dimension(1024,576));    

		t = new Timer(2, new ClockListener(this));   

		this.setFocusable(true);					 
		this.addKeyListener(this);
		mazeBoundaries = new Rectangle[16];
		mazeBoundaries[0] = (new Rectangle (792, 332, 232, 244));
		mazeBoundaries[1] = (new Rectangle (792, 100, 28, 220));
		mazeBoundaries[2] = (new Rectangle (532, 84, 292, 8));
		mazeBoundaries[3] = (new Rectangle (916, 9, 104, 228));
		mazeBoundaries[4] = (new Rectangle (2, 2, 446, 114));
		mazeBoundaries[5] = (new Rectangle (340, 120, 108, 52));
		mazeBoundaries[6] = (new Rectangle (404, 184, 300, 32));
		mazeBoundaries[7] = (new Rectangle (404, 220, 60, 148));
		mazeBoundaries[8] = (new Rectangle (468, 300, 108, 68));
		mazeBoundaries[9] = (new Rectangle (660, 220, 44, 280));
		mazeBoundaries[10] = (new Rectangle (276, 452, 380, 48));
		mazeBoundaries[11] = (new Rectangle (276, 264, 44, 180));
		mazeBoundaries[12] = (new Rectangle (148, 264, 108, 172));
		mazeBoundaries[13] = (new Rectangle (84, 200, 172, 48));
		mazeBoundaries[14] = (new Rectangle (2, 332, 62, 244));
		mazeBoundaries[15] = (new Rectangle (68, 520, 124, 56));
		screens = new Screens();
		p1 = new Player();
		t.start();
		count = 0;
		score = new int[5];
		keyPressed = false;
		index = 0;
		key = new boolean[10];
		for(int i = 0; i<key.length; i++)
			key[i] = false;
		flashingLights = new Rectangle [11];
		flashingLights[0] = new Rectangle(576,120,12,12);
		flashingLights[1] = new Rectangle(456,76,12,12);
		flashingLights[2] = new Rectangle(540,148,12,12);
		flashingLights[3] = new Rectangle(520,132,12,12);
		flashingLights[4] = new Rectangle(488,108,12,12);
		flashingLights[5] = new Rectangle(472,92,12,12);
		flashingLights[6] = new Rectangle(624,76,12,12);
		flashingLights[7] = new Rectangle(504,120,12,12);
		flashingLights[8] = new Rectangle(560,132,12,12);
		flashingLights[9] = new Rectangle(592,108,12,12);
		flashingLights[10] = new Rectangle(608,92,12,12);


		ClassLoader cldr = this.getClass().getClassLoader();	
		String imagePath =  "images/key.png";				
		URL imageURL = cldr.getResource(imagePath);				
		keyPic = new ImageIcon(imageURL);
		imagePath = "images/ballUp.png";
		imageURL = cldr.getResource(imagePath);
		ballUp = new ImageIcon(imageURL);
		imagePath = "images/ballDown.png";
		imageURL = cldr.getResource(imagePath);
		ballDown = new ImageIcon(imageURL);
		imagePath = "images/arrow.png";
		imageURL = cldr.getResource(imagePath);
		arrow = new ImageIcon(imageURL);
		imagePath = "images/arrowUp.png";
		imageURL = cldr.getResource(imagePath);
		arrowUp = new ImageIcon(imageURL);
		imagePath = "images/explosion.png";
		imageURL = cldr.getResource(imagePath);
		explosion = new ImageIcon(imageURL);
		shooting = false;
		guess = "";
		answer = new String[3];
		answer[0] = "secret";
		answer[1] = "16";
		answer[2] = "brown";
		button = new Rectangle(464, 220, 68, 60);
		puzzle = new Puzzle(4);
		direction = 0;
		startPuzzle = false;
		nextPart = 0;
		boss = new Player("images/sprite_0.png");
		boss.resetBoss();
		boss2 = new Player("images/sprite_12.png");
		boss2.resetBoss();
		hintIndexes = new int[9];
		hintCount = 0;
		oldHintCount = 0;
		difficulty = 1;
		isBig = true;
	}



	// method: paintComponent
	// description: This method will paint the items onto the graphics panel.  This method is called when the panel is
	//   			first rendered.  It can also be called by this.repaint()
	// parameters: Graphics g - This object is used to draw your images onto the graphics panel.
	public void paintComponent(Graphics g)
	{
		screens.drawScreen(g, this);
		Graphics2D g2 = (Graphics2D) g;
		g2.drawString("" +screens.getIndex(), 20, 20);
		g2.setFont(new Font("Times New Roman", Font.BOLD, 40));
		g2.setColor(Color.MAGENTA);
		//Draws a key in the corner if they've received the key for their level
		if(key[screens.getIndex()])
			keyPic.paintIcon(this, g2, 0, 0);
		//Moves the players legs if they are moving
		if(p1.move(direction))
			p1.nextImage();
		p1.drawItem(g2, this);
		//Goes to the next room
		if(key[screens.getIndex()] && p1.getX() >= 984 && p1.getY()+32 >238 && p1.getY()+32<338) {
			screens.nextIndex();
			p1.nextRoom();
			index = 0;
			hintCount+=oldHintCount;
			oldHintCount = 0;
			start = false;
			
		}
		//Goes to the previous room
		else if(!start && (screens.getIndex() == 3 && key[3] && p1.getX() < 0 && p1.getY()+32 >238 && p1.getY()+32<338) ||
				(!start && screens.getIndex() != 3 && screens.getIndex() >0 && key[screens.getIndex()-1] && p1.getX() < 0 && p1.getY()+32 >238 && p1.getY()+32<338)) {
			screens.prevIndex();
			p1.prevRoom();
			nextPart = 0;
		}
		//The next lines have to do with drawing the hints, most rooms had to be done differently
		if(screens.getIndex() == 5)
			difficulty = hintIndexes[5]+1;
		if(screens.getIndex()<9) {
			for(int i = 0; i<hintIndexes[screens.getIndex()]; i++) {
				if(screens.getIndex() == 4 && i == 0)
					g2.drawString(hints[screens.getIndex()][i], 0, 200);
				else if(screens.getIndex() == 4 && i>0)
					g2.drawString(hints[screens.getIndex()][i], -36+40*i, 375);
				else if(screens.getIndex() == 6 && i <=4 ) {
					g2.setColor(Color.CYAN);
					g2.drawString(hints[screens.getIndex()][i], 0, 320+ 50*i);
				}
				else if(screens.getIndex() == 6)
					g2.drawString(hints[screens.getIndex()][i], -250+50*i, 570);
				else if(screens.getIndex() == 7 && i==0)
					g2.drawString(hints[screens.getIndex()][i], 0, 358);
				else if(screens.getIndex() == 7 && i>0)
					g2.drawString(hints[screens.getIndex()][i], -28 +i*28, 398);
				else if(screens.getIndex() == 8 && i <= 1 && !start) {
					g2.drawString(hints[screens.getIndex()][i], 0, 358 + 50*i);
					oldHintCount = hintIndexes[screens.getIndex()];
				}
				else if(screens.getIndex() == 5) {
					g2.drawString(hints[screens.getIndex()][i], 0, 358 + 50*i);
				}
				else if(screens.getIndex()!=3 && screens.getIndex() != 4 && screens.getIndex()!=8)
					g2.drawString(hints[screens.getIndex()][i], i*18, 358);
				else if(screens.getIndex()==3)
					g2.draw(mazeBoundaries[i]);
			}
		}
		if(screens.getIndex() == 8 && hintIndexes[8] > 2 && !start)
			g2.drawString("The boss will now begin in phase " + hints[screens.getIndex()][hintIndexes[8]-1], 0, 568);
		//This switch statement switches the room which the player is in
		switch(screens.getIndex()) {
		case 0:
			//Tutorial room, directions are explained here
			count++;
			g2.setColor(Color.MAGENTA);
			if(nextPart == 0) {
				arrow.paintIcon(this, g2, p1.getX()-arrow.getIconWidth()-(count/10)%50, (int)p1.getY() + 20);
				g2.drawString("This is your avatar, you can move with the arrow keys,", 0, 30);
				g2.drawString("Action is spacebar", 0, 100);
				g2.drawString("Click Spacebar for more information", 0, 570);
			}
			else if(nextPart == 1) {
				g2.drawString("Most levels have no explanation, it's on you to figure it out", 0 , 30);
				g2.drawString("Click Spacebar for more information", 0, 570);
			}
			else if(nextPart == 2) {
				g2.drawString("If you type an answer, it will appear on screen as you type", 0, 30);
				g2.drawString("To submit your answer, press enter", 0, 100);
				g2.drawString("Click Spacebar for more information", 0, 570);
			}
			else if(nextPart == 3) {
				g2.drawString("Often, you may have to press this button", 0, 30);
				arrow.paintIcon(this, g2, (int)button.getX()-arrow.getIconWidth()-(count/10)%50, (int)button.getY() + 20);
				g2.drawString("Click Spacebar for more information", 0, 570);
			}
			else if(nextPart == 4) {
				g2.drawString("If you need some help", 0, 30);
				g2.drawString("Press \'?\' or \'/\' for a hint", 0, 100);
				g2.drawString("Try to use as few as possible", 0, 500);
				g2.drawString("Click Spacebar for more information", 0, 570);

			}
			else if(nextPart == 5) {
				key[0] = true;
				g2.drawString("When this key appears, you can move to the next room", 40, 30);
				g2.drawString("Through the door on the right", 40, 100);
				g2.drawString("There's no need to move on top of the key", 40, 500);
				g2.drawString("If it appears, the door is open", 40, 570);
				arrowUp.paintIcon(this, g2, 10, 20+(count/10)%50);
				arrow.paintIcon(this, g2, 920-(count/10)%50 , 576/2-15);


			}
			else if(nextPart == 6) {
				g2.drawString("Go ahead!", 40, 30);
				arrow.paintIcon(this, g2, 920-(count/10)%50 , 576/2-15);

			}
			break;

		case 1: 
			//First riddle room
			count = 0;
			g2.setColor(Color.GREEN);
			g2.drawString(guess, 30, 248);
			g2.drawString("If you have me, you want to share me.", 40, 30);
			g2.drawString("If you share me, you haven't got me.", 40, 500);
			g2.drawString( "What am I?", 30, 570);

			break;
		case 2:
			//Second riddle room
			g2.setColor(Color.CYAN);
			g2.drawString(guess, 30, 248);
			g2.drawString("A father's age is the reverse of his son's", 150, 30);
			g2.drawString("If their ages add up to 77, how old is", 190, 100);
			g2.drawString("the son if the son's age doubled is the", 150, 500);
			g2.drawString("cube of its square root divided by 2?", 150, 570);
			break;
		case 3:
			//Invisible maze room
			if(key[3]) {
				
				g2.setColor(Color.YELLOW);
				g2.draw(p1.getBounds());
				for(Rectangle r: mazeBoundaries) 
					g2.draw(r);

			}
			for(Rectangle r : mazeBoundaries)
				if(r.intersects(p1.getBounds()))
					p1.setMiddle();
			if(!p1.getBounds().intersects(button))
				screens.nextScreen("images/buttonUp.png");
			if(p1.getBounds().intersects(new Rectangle(1000,258,24,60)))
				key[3] = true;
			break;
		case 4:
			//The puzzle room with the smushing of the circles
			g2.setColor(Color.BLUE);
			if(startPuzzle) {
				g2.setColor(Color.BLACK);


				g2.fillRect(330, 160, 4*104, 72*4);
				p1.drawItem(g2, this);
				g2.setColor(Color.BLUE);
				for(int i = 0; i<puzzle.getBoard().length; i++) {
					for(int j = 0; j<puzzle.getBoard()[i].length; j++) {
						if(!puzzle.getBoard()[i][j])
							ballUp.paintIcon(this, g2, 5+310 + 104*j + (int)puzzle.getRects()[i][j].getWidth()/2, 146 + 71*i + (int)puzzle.getRects()[i][j].getHeight()/2);
						else
							ballDown.paintIcon(this, g2, 5+(int)puzzle.getRects()[i][j].getWidth()/2 +310 + 104*j, 146 + 71*i + (int)puzzle.getRects()[i][j].getHeight()/2);
						g2.draw(puzzle.getRects()[i][j]);
					}
				}
				g2.drawRect(1, 1, 100, 100);
				g2.drawString("Reset", 1, 50);
			}



			break;
		case 5: 
			//The fast typing room
			int sum = 0;
			if(!start && p1.getBounds().intersects(button)) {

				score = new int[5];
				sum = 0;
				index = 0;
				start = false;
			}

			else if(start){
				
				
				if(count == 0)
					letter = (char)((int)(Math.random()*26) + 97);
				if(!key[5]) {
					g2.drawString(letter + "", 500, 500);
					g2.drawRect(490, 470, 40, 40);
					count+=t.getDelay();
					if(keyPressed) {
						score[index] = difficulty * (1000-count);
						if(score[index]<0)
							score[index] = 0;
						count = 0;
						index++;
						keyPressed = false;
					}					


				}
				sum = score[0];
				for(int i = 0; i<score.length; i++)
					sum+=score[i];
				g2.setColor(Color.YELLOW);
				g2.fillRect(920, 0, 24, (sum/5));
				if(index == 5 && sum>2880)
					key[5] = true;
				else if(index == 5) {
					score = new int[5];
					sum = 0;
					index = 0;
					start = false;
				}
			}
			break;
		case 6: 
			//The chandelier lighting room
			g2.setColor(Color.red);
			for(int i = 0; i<index; i++) 
				g2.fill(flashingLights[i]);
			
			if(!key[6])
				key[6] = index == 11;
			if(key[6]) {
				for(int i = 0; i<11; i++) 
					g2.fill(flashingLights[i]);
				if (!isBig) {
					p1.goBig();
					t.setDelay(2);
					isBig = true;
				}
			}


			break;
		case 7:
			//The final riddle room
			g2.setColor(Color.CYAN);
			g2.drawString("What should be the same, but yet, is not", 200, 30);
			g2.drawString("The color it is you should jot", 250, 570);
			g2.drawString(guess, 30, 248);
			break;
		case 8:
			//The boss fight room
			g2.setColor(Color.BLUE);
			if(start && !key[8]) { 
				t.setDelay(2);
				hintIndexes[8] = 0;
				if(count==0) {
					count = 248;
				}
				count+=t.getDelay();

				if(count<10250 && count % 1000 < 250) {
					g2.setColor(Color.RED);
					for(Rectangle r : boss.getLasers())
						g2.fill(r);


					if(boss.getImageNum()>=12) {
						g2.setColor(Color.YELLOW);
						for(Rectangle r : boss2.getLasers())
							g2.fill(r);
						boss2.attack(p1);
					}
					boss.attack(p1);


				}

				else if(count%1000 == 250 && count<10000) {
					if(boss.getImageNum()>=12)
						boss2.teleport(p1, false);
					boss.teleport(p1, false);
				}
				else if(count%4 == 0 && count<10000) {
					g2.setColor(Color.GREEN);
					for(Rectangle r : boss.getLasers())
						g2.draw(r);
					if(boss.getImageNum()>=12) {
						g2.setColor(Color.ORANGE);
						for(Rectangle r : boss2.getLasers())
							g2.draw(r);
					}
				}
				else if(count == 10250) {
					boss.teleport(p1, true);
					if(boss.getImageNum()>=12)
						boss2.teleport(p1, true);
				}
				else if(count > 10250 && count<11250 && count%4 == 0) {
					g2.setColor(Color.MAGENTA);
					for(Rectangle r : p1.getLasers())
						g2.draw(r);

				}

				else if(shooting && count > 10250 && count<11250) {
					g2.setColor(Color.BLUE);
					p1.Dead();
					for(Rectangle r : p1.getLasers())
						g2.fill(r);
					p1.attack(boss);
				}

				else if(!boss.deadOrAlive()) {
					boss.newLife();
					key[8] = boss.nextPhase();
					boss2.nextPhase();
					p1.newLife();
					count = 0;
					shooting = false;
				}

				else if(count==11250) {
					p1.newLife();
					count = 0;
					shooting = false;
				}
				else if(!p1.deadOrAlive()) {
					start = false;
					boss.resetBoss();
					boss2.resetBoss();
					count = 0;
					p1.newLife();
					p1.setMiddle();
					shooting = false;
					boss.setImageNum(0);
					boss2.setImageNum(0);
					boss.resetImage();
					boss2.resetImage();
				}
				if(!key[8]) {
					boss.drawItem(g2, this);
					boss.nextImage();
					if(boss.getImageNum()>=12 && count%4 == 0) {
						boss2.drawItem(g2, this);
						boss2.nextImage();
					}
				}
				else {
					count = 0;

				}

			}

			if(key[8] && count<1000) {
				count+=t.getDelay();
				explosion.paintIcon(this,g2,boss.getX(),boss.getY());
			}
			break;
		case 9:
			//The beat the game room
			g2.drawString("CONGRATS! YOU DID IT!", 290, 380);
			if(hintCount == 0) {
				g2.drawString("I salute you, for doing a hintless run :)", 200, 500);
				g2.drawString("You have now fully beaten the game!" , 200, 570);
				g2.setFont(new Font("Brush Script MT",Font.ITALIC, 50));
				g2.setColor(Color.CYAN);
				//A little easter egg for those overachievers :)
				g2.drawString("Jonathan Mosenco", 0, 35);
				g2.drawString("Sam Hecht", 820, 35);
				g2.setColor(Color.MAGENTA);
				g2.setFont(new Font("Times New Roman", Font.BOLD, 40));
			}
			else if(hintCount == 63) {
				g2.drawString("You used " + hintCount + " hints... that's the max", 250, 430);
				g2.drawString("If you want to try for less, play again", 220, 570);
				g2.drawRect(665, 544, 191, 29);
			}
			else {
				g2.drawString("You used " + hintCount + " hints to help you", 250, 500);
				g2.drawString("Nice Job! If you want to try for less, play again", 100, 570);
				g2.drawRect(716, 544, 191, 29);
			}
		}
	}







	// method:clock
	// description: This method is called by the clocklistener every 5 milliseconds.  You should update the coordinates
	//				of one of your characters in this method so that it moves as time changes.  After you update the
	//				coordinates you should repaint the panel.
	public void clock(){

		this.repaint();
	}

	// method: keyPressed()
	// description: This method is called when a key is pressed. You can determine which key is pressed using the 
	//				KeyEvent object and .  For example if(e.getKeyCode() == KeyEvent.VK_LEFT) would test to see if
	//				the left key was pressed.
	// parameters: KeyEvent e
	@Override

	public void keyPressed(KeyEvent e) {
		switch(e.getKeyCode()){
		//This case deals specifically with getting hints
		case KeyEvent.VK_SLASH:
			if(screens.getIndex() != 9 && screens.getIndex() != 8 
				&& screens.getIndex() != 0 && hints[screens.getIndex()].length > hintIndexes[screens.getIndex()])
				hintCount++;
			if(screens.getIndex() == 8 && hintIndexes[8] >= 2 && hintIndexes[8]<=5) {
				boss2.nextPhase();
				boss.nextPhase();
				ClassLoader cldr = this.getClass().getClassLoader();	
				String imagePath = "images/sprite_" +boss2.getImageNum() + ".png";			
				URL imageURL = cldr.getResource(imagePath);
				boss2.setImage(new ImageIcon(imageURL));
				imagePath = "images/sprite_" + boss.getImageNum() + ".png";
				imageURL = cldr.getResource(imagePath);
				boss.setImage(new ImageIcon(imageURL));
			}
			if(screens.getIndex() != 9 && hints[screens.getIndex()].length > hintIndexes[screens.getIndex()])
				hintIndexes[screens.getIndex()]++;
			break;
			//This case has to do with the movement of the player
		case KeyEvent.VK_LEFT: 
			if(direction == 0)
				direction = -1;
			else if(direction == -2)
				direction = -3;
			else if(direction == 2)
				direction = -4;
			break;
			//This case has to do with the movement of the player

		case KeyEvent.VK_RIGHT: 
			if(direction == 0)
				direction = 1;
			else if(direction == -2)
				direction = 3;
			else if(direction == 2)
				direction = 4;
			break;
			//This case has to do with the movement of the player

		case KeyEvent.VK_DOWN: 
			if(direction == 0)
				direction = -2;
			else if(direction == -1)
				direction = -3;
			else if(direction == 1)
				direction = 3;
			break;
			//This case has to do with the movement of the player

		case KeyEvent.VK_UP: 
			if(direction == 0)
				direction = 2;
			else if(direction == -1)
				direction = -4;
			else if(direction == 1)
				direction = 4;
			break;
			//This case has to do with any actions the player would be making in any room

		case KeyEvent.VK_SPACE:
			//This switch statement changes what action occurs depending on what room it's in

			switch(screens.getIndex()) {
			case 0:
				nextPart++;
				if(p1.getBounds().intersects(button))
					screens.nextScreen("images/buttonDown.png");
				break;
			case 3:
				if(p1.getBounds().intersects(button))
					screens.nextScreen("images/maze.png");
				break;
			case 4:
				if(startPuzzle)
					key[4] = puzzle.flip(p1);
				if(p1.getBounds().intersects(button)) {
					screens.nextScreen("images/background.png");
					startPuzzle = true;
				}
				if(p1.getBounds().intersects(new Rectangle(1,1,100,100)))
					puzzle = new Puzzle(4);
				break;
			case 5:
				if(!start && p1.getBounds().intersects(button)) {
					start = true;
					screens.nextScreen("images/buttonDown.png");
				}
				break;
			case 6:
				if(p1.getBounds().intersects(new Rectangle(480, 400, 80, 64))) {
					p1.goSmall();
					t.setDelay(4);
					isBig = false;
				}
				if(!key[6] && index < flashingLights.length && p1.getSmallBounds().intersects(flashingLights[index])) 
					index++;
				else
					index=0;

				break;			
			case 8:
				if(p1.getBounds().intersects(button) && count == 0) {
					screens.nextScreen("images/buttonDown.png");
					start = true;
				}
				else if(count>10250) {
					shooting = true;
				}

				break;

			case 9:
				if(p1.getBounds().intersects(new Rectangle(665, 544, 242, 29))) {
					screens = new Screens();
					p1 = new Player();
					t.start();
					count = 0;
					score = new int[5];
					keyPressed = false;
					index = 0;
					key = new boolean[10];
					for(int i = 0; i<key.length; i++)
						key[i] = false;
					puzzle = new Puzzle(4);
					direction = 0;
					startPuzzle = false;
					nextPart = 0;
					boss = new Player("images/sprite_0.png");
					boss.resetBoss();
					boss.setImageNum(0);
					boss2 = new Player("images/sprite_12.png");
					boss2.resetBoss();
					boss2.setImageNum(0);
					for(int i = 0; i<hintIndexes.length; i++)
						hintIndexes[i] = 0;

					hintCount = 0;
					oldHintCount = 0;
					start = false;
				}
				break;
			}
			break;
			//This case has to do with the entering of guesses for riddles

		case KeyEvent.VK_ENTER:
			switch(screens.getIndex()) {
			case 1:
				if(guess.toLowerCase().equals(answer[screens.getIndex()-1]) || guess.toLowerCase().equals("a "+ answer[screens.getIndex()-1])) 
					key[screens.getIndex()] = true;
				guess = "";
				break;

			case 2: 
				if(guess.toLowerCase().equals(answer[screens.getIndex()-1]) || guess.toLowerCase().equals("a "+ answer[screens.getIndex()-1])) 
					key[screens.getIndex()] = true;
				guess = "";
				break;
			case 7:
				if(guess.equals(answer[2]))
					key[screens.getIndex()] = true;
				guess = "";
				break;
			}
			break;
			//This case deletes the last letter of a string

		case KeyEvent.VK_BACK_SPACE:
			if(guess.length()>0) {
				guess = guess.substring(0,guess.length()-1);
			}
			break;
		}

		this.repaint();

	}

	public void keyReleased(KeyEvent e) {
		switch(e.getKeyCode()){
		//This case has to do with the movement of the player

		case KeyEvent.VK_LEFT: 
			if(direction == -3)
				direction = -2;
			else if(direction == -4)
				direction = 2;
			else
				direction = 0;
			break;
			//This case has to do with the movement of the player

		case KeyEvent.VK_RIGHT: 
			if(direction == 3)
				direction = -2;
			else if(direction == 4)
				direction = 2;
			else
				direction = 0;
			break;
			//This case has to do with the movement of the player

		case KeyEvent.VK_DOWN: 
			if(direction == -3)
				direction = -1;
			else if(direction == 3)
				direction = 1;
			else
				direction = 0;
			break;
			//This case has to do with the movement of the player

		case KeyEvent.VK_UP: 
			if(direction == -4)
				direction = -1;
			else if(direction == 4)
				direction = 1;
			else
				direction = 0;
			break;


			//This case resets the picture to the button up
		case KeyEvent.VK_SPACE:
			if(screens.getIndex()!=6 && screens.getIndex()!=1 && screens.getIndex()!=2 && screens.getIndex() != 7)
				screens.nextScreen("images/buttonUp.png");
			break;
		}
		this.repaint();
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		switch(screens.getIndex()) {
		//This case has to do with the guessing in the riddles
		case 1:
			if(!(e.getKeyChar() == '\n') && !(e.getKeyChar() == '\b') && !(e.getKeyChar() == '?') && !(e.getKeyChar() == '/')) 
				guess = guess + e.getKeyChar();
			break;
		//This case has to do with the guessing in the riddles
		case 2:
			if(!(e.getKeyChar() == '\n') && !(e.getKeyChar() == '\b') && !(e.getKeyChar() == '?') && !(e.getKeyChar() == '/'))
				guess =guess+ e.getKeyChar();
			break;
		//This case has to do with the typing in the fast typing game
		case 5:
			if(start && !(e.getKeyChar() == ' ')) {
				keyPressed = e.getKeyChar() == letter;
				start = keyPressed;
			}
			break;
		//This case has to do with the guessing in the riddles
		case 7:
			if(!(e.getKeyChar() == '\n') && !(e.getKeyChar() == '\b') && !(e.getKeyChar() == '?') && !(e.getKeyChar() == '/')) 
				guess = guess + e.getKeyChar();
			break;

		}
	}
}
