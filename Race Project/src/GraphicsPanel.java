// Class: GraphicsPanel
// Written by: Mr. Swope
// Date: 10/28/15
// Description: This class is the main class for this project.  It extends the Jpanel class and will be drawn on
// 				on the JPanel in the GraphicsMain class.  Your project should have at least one character that moves
//				with the arrow keys and one character that moves with the clock.  Finally, you should detect if the
//				two items intersect and have something happen if they do intersect.
//
// Since you will modify this class you should add comments that describe when and how you modified the class.  

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.net.URL;


import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;


public class GraphicsPanel extends JPanel implements KeyListener{

	int background_x;
	private Timer t;							 // The timer is used to move objects at a consistent time interval.
	private Character jeep;						 // A jeep
	private Character megaMan;						 // A megaMan
	private Character spaceship;
	private Character rock;
	private Character invinc;
	private int score;
	private int shipSpeed;
	private boolean over;
	private int row;
	private int direction;
	private boolean invin;
	private int count;
	private int negPos;
	private int invincLength;
	private int megaDelay;
	private int jeepSpeed;
	private int jeepDelay;
	
	public GraphicsPanel(){
		direction = 0;
		over = false;
		row = (int)(Math.random()*3) + 1;
		jeep = new Character(0, 800, 120);
		megaMan = new Character(1, 50, 180);
		spaceship = new Character(2, 50, 115);
		rock = new Character(3, -50, 175);
		invinc = new Character(4,-100,0);
		background_x=0;
		invin = true;
		count = 0;
		negPos = 1;
		megaDelay = 1;
		jeepSpeed = 2;
		jeepDelay = 1;

		setPreferredSize(new Dimension(950,343));    // Set these dimensions to the width 
		// of your background picture.   
		shipSpeed = 0;
		t = new Timer(5, new ClockListener(this));   // t is a timer.  This object will call the ClockListener's
		score = 0;	
		// action performed method every 5 milliseconds once the 
		// timer is started. You can change how frequently this
		// method is called by changing the first parameter.
		t.start();
		this.setFocusable(true);					 // for keylistener
		this.addKeyListener(this);
	}

	// method: paintComponent
	// description: This method will paint the items onto the graphics panel.  This method is called when the panel is
	//   			first rendered.  It can also be called by this.repaint()
	// parameters: Graphics g - This object is used to draw your images onto the graphics panel.
	public void paintComponent(Graphics g)
	{
		Graphics2D g2 = (Graphics2D) g;

		ClassLoader cldr = this.getClass().getClassLoader();	// These five lines of code load the background picture.
		String imagePath = "images/background.png";				// Change this line if you want to use a different 
		URL imageURL = cldr.getResource(imagePath);				// background image.  The image should be saved in the
		
		
		

		ImageIcon background1Image = new ImageIcon(imageURL);	// Two ImageIcon's are used to scroll the background.
		background1Image.paintIcon(this, g2, background_x, 0);
		ImageIcon background2Image = new ImageIcon(imageURL);
		background2Image.paintIcon(this, g2, background_x-1365, 0);

		imagePath = "images/Warning.png";				
		imageURL = cldr.getResource(imagePath);			
		ImageIcon warningSign = new ImageIcon(imageURL);
		
		rock.draw(g2, this);
		if(row == 1 && spaceship.getX() >=900)
			warningSign.paintIcon(this, g2, 20, 138);
		if(row == 2 && spaceship.getX() >=900)
			warningSign.paintIcon(this, g2, 20, 188);
		if(row == 3 && spaceship.getX() >=900)
			warningSign.paintIcon(this, g2, 20, 238);
		//if(randInt == 33)
			//shock.paintIcon(this, g2, 500,138);//(int)(Math.random()*500) + 200, 50+50*((int)(Math.random()*3) + 1));
		if(invin)
			jeep.draw(g2, this);
		else if(negPos == 1)
			jeep.draw(g2, this);
		if(!megaMan.getBounds().intersects(rock.getBounds()) || negPos == -1)
			megaMan.draw(g2, this);
		spaceship.draw(g2, this);
		
		invinc.draw(g2, this);
		
		if(!invin) {
			g2.setColor(Color.YELLOW);
			g2.fillRect(jeep.getX()+15, jeep.getY()+95, (int)invincLength*3, 5);
		}
		g2.setFont(new Font("TimesRoman", Font.BOLD, 30));
		g2.setColor(Color.RED);
		g2.drawString(""+score, 50, 50);
		if(score == 0) {
			g2.setFont(new Font("TimesRoman", Font.BOLD, 20));
			g2.drawString("Megaman is escaping! Catch HIM!", 300, 50);
		
		}
		if(over) {
			t.stop();
			g2.setColor(Color.BLUE);
			g2.setFont(new Font("TimesRoman", Font.BOLD, 64));
			g2.drawString("Game Over, You Scored:" + score, 100, 220);
			g2.setColor(Color.RED);
			g2.setFont(new Font("TimesRoman", Font.BOLD, 32));
			g2.drawString("To play again, press space. To exit, press esc", 180, 250);
			
		}

	}

	// method:clock
	// description: This method is called by the clocklistener every 5 milliseconds.  You should update the coordinates
	//				of one of your characters in this method so that it moves as time changes.  After you update the
	//				coordinates you should repaint the panel.
	public void clock(){
		Rectangle oldSpot = new Rectangle(jeep.getBounds());
		if(background_x<1365)
			background_x++;
		else
			background_x = 0;
		
		int rand = (int)(Math.random()*3);
		if(spaceship.getX() <=900)
			row = (int)(Math.random()*3) + 1;
		jeep.keyPressedMove(direction, jeepSpeed);
		if(oldSpot.equals(jeep.getBounds()) && megaDelay==0) {
			jeep.nextImage();
		}
		
		else if(oldSpot.getX()>jeep.getBounds().getX() && jeepDelay%10 == 0) {
			jeep.nextImage();
		}
		else if(oldSpot.getX()<jeep.getBounds().getX() && jeepDelay == 0) {
			jeep.nextImageReverse();
		}
		if(megaDelay == 0)
			megaMan.nextImage();
		
		
		if(score >= 300 && Math.random()>.9992 && invinc.getX()<450 && invin) {
			invinc.timerMove(0,0);
		}
		if(jeep.getBounds().intersects(megaMan.getBounds())){	// This code will detect if the pirate and parrot have
			score+= 100;									// collided.  Make something happen if they do intersect.
			jeep.reset(0);
			spaceship.reset(row);
			megaMan.reset(rand);
			rock.reset(rand);
		}
		
		if(invin && (jeep.getBounds().intersects(spaceship.getBounds()) || jeep.getBounds().intersects(rock.getBounds()))){	// This code will detect if the pirate and parrot have
			over = true;									// collided.  Make something happen if they do intersect.
			jeep.reset(0);
			direction = 0;
		} 
		if(jeep.getBounds().intersects(invinc.getBounds())) {
			invinc.reset(0);
			invin = false;
		}
		if(!invin) {
			count+=t.getDelay();
			invincLength = 1+(3000-count)/100;
		}
		if(count >= 3000) {
			invin = true;
			count = 0;
		}
		//Changes the shipSpeed of spaceship
		shipSpeed = score/100 + 2;
		//Moves the spaceship and rock
		spaceship.timerMove(row, shipSpeed);
		rock.timerMove(rand, shipSpeed);
		//Iterates negPos
		negPos = -negPos;
		//Adds to megaDelay and resets it 
		megaDelay++;
		if(megaDelay == 15)
			megaDelay = 0;
		jeepDelay+=jeepSpeed;
		if(jeepDelay >= 30)
			jeepDelay = 0;
//		if(spaceship.getX() >=1345 && row  == 1) {
//			spaceship.setX(50);
//			spaceship.setY(115);
//			row = (int)(Math.random()*3) + 1;
//		}
//		else if(spaceship.getX() >=1345 && row  == 2) {
//			spaceship.setX(50);
//			spaceship.setY(165);
//			row = (int)(Math.random()*3) + 1;
//		}
//		else if(spaceship.getX() >=1345 && row  == 3) {
//			spaceship.setX(50);
//			spaceship.setY(215);
//			row = (int)(Math.random()*3) + 1;
//		}
		
		this.repaint();



	}

	// method: keyPressed()
	// description: This method is called when a key is pressed. You can determine which key is pressed using the 
	//				KeyEvent object and .  For example if(e.getKeyCode() == KeyEvent.VK_LEFT) would test to see if
	//				the left key was pressed.
	// parameters: KeyEvent e
	@Override
	public void keyPressed(KeyEvent e) {
		//Makes the jeep go down if the game isn't over
		if(e.getKeyCode() == KeyEvent.VK_DOWN && !over) 
			jeep.keyPressedMove(-2, jeepSpeed);
		//Makes the jeep go up if the game isn't over
		else if(e.getKeyCode() == KeyEvent.VK_UP && !over) 
			jeep.keyPressedMove(2, jeepSpeed);
		//Makes the jeep go right if the game isn't over
		else if(e.getKeyCode() == KeyEvent.VK_RIGHT && !over) 
			direction = 1;
		//Makes the jeep go left if the game isn't over
		else if(e.getKeyCode() == KeyEvent.VK_LEFT && !over)
			direction = -1;
		//Closes the game
		else if(over && e.getKeyCode() == KeyEvent.VK_ESCAPE)
			System.exit(0);
		//Resets the game
		else if(over && e.getKeyCode() == KeyEvent.VK_SPACE) {
			t.start();
			over = false;
			int rand = (int) (Math.random()*3);
			score = 0;									// collided.  Make something happen if they do intersect.
			jeep.reset(0);
			spaceship.reset(row);
			megaMan.reset(rand);
			rock.reset(rand);
			invinc.reset(0);
			jeepSpeed = 2;
		}
		//A cheat to activate invincibility
		else if(e.getKeyCode() == KeyEvent.VK_SHIFT) {
			invin = false;
			count = 0;
		}
		else if(e.getKeyCode() == KeyEvent.VK_W) {
			jeepSpeed++;
		}
		else if(e.getKeyCode() == KeyEvent.VK_S && jeepSpeed >=2) {
			jeepSpeed--;
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}



	@Override
	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_DOWN && !over) 
			jeep.keyPressedMove(0, 0);
		else if(e.getKeyCode() == KeyEvent.VK_UP && !over) 
			jeep.keyPressedMove(0, 0);
		else if(e.getKeyCode() == KeyEvent.VK_RIGHT && !over)
			direction = 0;
		else if(e.getKeyCode() == KeyEvent.VK_LEFT && !over)
			direction = 0;
	}
}
