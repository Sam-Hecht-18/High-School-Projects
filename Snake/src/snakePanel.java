
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.net.URL;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;


public class snakePanel extends JPanel implements KeyListener{


	private Timer t;							 // The timer is used to move objects at a consistent time interval.
	private snakeHead head;
	private dot dot;
	private ArrayList<snakeBody> heads;
	private ArrayList<Point> turningPoints;
	private int direction;
	private boolean over;
	private int score;
	private int count;
	private int addCount;
	private boolean paused;
	private boolean first;
	private boolean reset;
	public snakePanel(){
		direction = 0;
		dot = new dot((int)(Math.random()*23)+1,(int)(Math.random()*23)+1);
		setPreferredSize(new Dimension(500,500));    // Set these dimensions to the width 
		head = new snakeHead((int)(Math.random()*23)+1,(int)(Math.random()*23)+1);
		t = new Timer(100, new ClockListener(this));   // t is a timer.  This object will call the ClockListener's
		heads = new ArrayList<>();
		turningPoints = new ArrayList<>();
		over = false;
		score = 0;
		count = 0;
		addCount = 0;
		paused = false;
		first = false;
		reset = false;

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
		g2.drawString(".", 0, 0);
		g2.fillRect(0, 0, 500, 500);
		g2.setColor(Color.red);
		g2.fillRect(0, 0, 20, 500);
		g2.fillRect(0, 0, 500, 20);
		g2.fillRect(0, 500-20, 500, 20);
		g2.fillRect(500-20, 0, 20, 500);
		g2.setColor(Color.BLUE);
		g2.fillRect(head.getLocal().x*20, head.getLocal().y*20, 20, 20);
		g2.setColor(Color.BLACK);
		g2.drawRect(head.getLocal().x*20, head.getLocal().y*20, 20, 20);
		score = heads.size()*100;
		g2.setColor(Color.BLUE);

		if(paused)
			g2.drawString("PAUSED", 300, 300);
		g2.drawString("" + score, 460, 495);
		g2.setColor(Color.YELLOW);
		g2.fillRect(dot.getLocal().x*20, dot.getLocal().y*20, 20, 20);
		g2.setColor(Color.BLACK);
		g2.drawRect(dot.getLocal().x*20, dot.getLocal().y*20, 20, 20);
		g2.setColor(Color.GREEN);
		if(head.getAccel() != direction)
			turningPoints.add(new Point(head.getLocal().x,head.getLocal().y));
		direction = head.getAccel();


		if(head.getLocal().equals(dot.getLocal()) && heads.size() == 0) {
			turningPoints.add(new Point(head.getLocal().x, (head.getLocal().y)));
			heads.add(new snakeBody(turningPoints.get(turningPoints.size()-1).x, (turningPoints.get(turningPoints.size()-1).y), turningPoints.size()));
			first = true;
		}
		else if(head.getLocal().equals(dot.getLocal()) || addCount>0 || first ) {
			for(int i = 0; i<heads.size(); i++) {
				if(dot.getLocal().equals(heads.get(i).getLocal()) && reset)
					dot.reset();
			}
			System.out.print(1);
			if(addCount == 1) {
				reset = true;
				dot.reset();
			}
			else
				reset = false;

			//			for(int i = 0; i<heads.size(); i++)
			//				if(dot.getLocal().equals(heads.get(i).getLocal()))
			//					dot.reset();
			addCount++;
			if(addCount == 5)
				addCount = 0;

			if(heads.get(heads.size()-1).getDirection() == 2 || count%5 == 1) {
				heads.add(new snakeBody(heads.get(heads.size()-1).getLocal().x, heads.get(heads.size()-1).getLocal().y+1, heads.get(heads.size()-1).getCount()));
				//				heads.add(new snakeBody(heads.get(heads.size()-1).getLocal().x, heads.get(heads.size()-1).getLocal().y+1, heads.get(heads.size()-1).getCount()));
				//				heads.add(new snakeBody(heads.get(heads.size()-1).getLocal().x, heads.get(heads.size()-1).getLocal().y+1, heads.get(heads.size()-1).getCount()));
				//				heads.add(new snakeBody(heads.get(heads.size()-1).getLocal().x, heads.get(heads.size()-1).getLocal().y+1, heads.get(heads.size()-1).getCount()));
				//				heads.add(new snakeBody(heads.get(heads.size()-1).getLocal().x, heads.get(heads.size()-1).getLocal().y+1, heads.get(heads.size()-1).getCount()));
				if(count == 0)
					count = -4;
				if(count == 16)
					count = -5;
				count+=5;
			}
			else if(heads.get(heads.size()-1).getDirection() == 1 || count%5 == 2) {
				heads.add(new snakeBody(heads.get(heads.size()-1).getLocal().x-1, heads.get(heads.size()-1).getLocal().y, heads.get(heads.size()-1).getCount()));
				//				heads.add(new snakeBody(heads.get(heads.size()-1).getLocal().x-1, heads.get(heads.size()-1).getLocal().y, heads.get(heads.size()-1).getCount()));
				//				heads.add(new snakeBody(heads.get(heads.size()-1).getLocal().x-1, heads.get(heads.size()-1).getLocal().y, heads.get(heads.size()-1).getCount()));
				//				heads.add(new snakeBody(heads.get(heads.size()-1).getLocal().x-1, heads.get(heads.size()-1).getLocal().y, heads.get(heads.size()-1).getCount()));
				//				heads.add(new snakeBody(heads.get(heads.size()-1).getLocal().x-1, heads.get(heads.size()-1).getLocal().y, heads.get(heads.size()-1).getCount()));
				if(count == 0)
					count = -3;
				if(count == 17)
					count = -5;
				count+=5;
			}
			else if(heads.get(heads.size()-1).getDirection() == -1 || count%5 == 3) {
				heads.add(new snakeBody(heads.get(heads.size()-1).getLocal().x+1, heads.get(heads.size()-1).getLocal().y, heads.get(heads.size()-1).getCount()));
				//				heads.add(new snakeBody(heads.get(heads.size()-1).getLocal().x+1, heads.get(heads.size()-1).getLocal().y, heads.get(heads.size()-1).getCount()));
				//				heads.add(new snakeBody(heads.get(heads.size()-1).getLocal().x+1, heads.get(heads.size()-1).getLocal().y, heads.get(heads.size()-1).getCount()));
				//				heads.add(new snakeBody(heads.get(heads.size()-1).getLocal().x+1, heads.get(heads.size()-1).getLocal().y, heads.get(heads.size()-1).getCount()));
				//				heads.add(new snakeBody(heads.get(heads.size()-1).getLocal().x+1, heads.get(heads.size()-1).getLocal().y, heads.get(heads.size()-1).getCount()));
				if(count == 0)
					count = -2;
				if(count == 18)
					count = -5;
				count+=5;
			}
			else if(heads.get(heads.size()-1).getDirection() == -2 || count%5 == 4) {
				heads.add(new snakeBody(heads.get(heads.size()-1).getLocal().x, heads.get(heads.size()-1).getLocal().y-1, heads.get(heads.size()-1).getCount()));
				//				heads.add(new snakeBody(heads.get(heads.size()-1).getLocal().x, heads.get(heads.size()-1).getLocal().y-1, heads.get(heads.size()-1).getCount()));
				//				heads.add(new snakeBody(heads.get(heads.size()-1).getLocal().x, heads.get(heads.size()-1).getLocal().y-1, heads.get(heads.size()-1).getCount()));
				//				heads.add(new snakeBody(heads.get(heads.size()-1).getLocal().x, heads.get(heads.size()-1).getLocal().y-1, heads.get(heads.size()-1).getCount()));
				//				heads.add(new snakeBody(heads.get(heads.size()-1).getLocal().x, heads.get(heads.size()-1).getLocal().y-1, heads.get(heads.size()-1).getCount()));
				if(count == 0)
					count = -1;
				if(count == 19)
					count = -5;
				count+=5;

			}
			first = false;
		}

		//		else if(turningPoints.size()-1>0 &&(
		//				head.getLocal().x-1 == turningPoints.get(turningPoints.size()-1).x ||
		//				head.getLocal().x+1 == turningPoints.get(turningPoints.size()-1).x ||
		//				head.getLocal().y+1 == turningPoints.get(turningPoints.size()-1).y ||
		//				head.getLocal().y-1 == turningPoints.get(turningPoints.size()-1).y))
		over = head.move();
		for(snakeBody s: heads) {
			g2.setColor(Color.GREEN);
			g2.fillRect(s.getLocal().x*20, s.getLocal().y*20, 20, 20);
			g2.setColor(Color.BLACK);
			g2.drawRect(s.getLocal().x*20, s.getLocal().y*20, 20, 20);
			if(head.getLocal().x == s.getLocal().x && head.getLocal().y == s.getLocal().y)
				over = true;
		}



		//		if(head.getLocal().equals(dot.getLocal()) && heads.get(heads.size()-1).getAccel() == -1) {
		//			dot.reset();
		//			heads.add(new snakeHead(heads.get(heads.size()-1).getLocal().x+20,heads.get(heads.size()-1).getLocal().y));
		//		}
		//		if(head.getLocal().equals(dot.getLocal()) && heads.get(heads.size()-1).getAccel() == -2) {
		//			dot.reset();
		//			heads.add(new snakeHead(heads.get(heads.size()-1).getLocal().x,heads.get(heads.size()-1).getLocal().y-20));
		//		}
		//		if(head.getLocal().equals(dot.getLocal()) && heads.get(heads.size()-1).getAccel() == 1) {
		//			dot.reset();
		//			heads.add(new snakeHead(heads.get(heads.size()-1).getLocal().x-20,heads.get(heads.size()-1).getLocal().y));
		//		}
		//		if(head.getLocal().equals(dot.getLocal()) && heads.get(heads.size()-1).getAccel() == 2) {
		//			dot.reset();
		//			heads.add(new snakeHead(heads.get(heads.size()-1).getLocal().x,heads.get(heads.size()-1).getLocal().y+20));
		//		}
		for(int i = 0; i<heads.size(); i++)
			heads.get(i).move(turningPoints, head, i);
		if(over) {
			g2.setFont(new Font("TimesRoman", Font.BOLD, 64));
			g2.setColor(Color.MAGENTA);
			g2.drawString("Game over", 60, 200);
			g2.drawString("Score: " + score, 60, 270);
			g2.setFont(new Font("TimesRoman", Font.BOLD, 32));
			g2.drawString("Space to restart", 220, 310);
			g2.drawString("esc To exit", 220, 350);
			t.stop();
		}

	}



	//ClassLoader cldr = this.getClass().getClassLoader();	// These five lines of code load the background picture.
	//String imagePath = "images/background.png";				// Change this line if you want to use a different 
	//URL imageURL = cldr.getResource(imagePath);				// background image.  The image should be saved in the
	//ImageIcon background = new ImageIcon(imageURL);
	//background.paintIcon(this, g2, 0, 0);






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
		switch(e.getKeyCode()) {
		case KeyEvent.VK_UP:
			head.setUpAccel(true);
			break;
		case KeyEvent.VK_DOWN:
			head.setDownAccel(true);
			break;
		case KeyEvent.VK_RIGHT:
			head.setRightAccel(true);
			break;
		case KeyEvent.VK_LEFT:
			head.setLeftAccel(true);
			break;
		case KeyEvent.VK_SPACE:
			over = false;
			t.start();
			heads.clear();
			turningPoints.clear();
			dot.reset();
			head.reset();
			score = 0;
			addCount = 0;
			count = 0;
			direction = 0;
			break;
		case KeyEvent.VK_ESCAPE:
			System.exit(0);
		case KeyEvent.VK_P:
			if(t.isRunning()) {
				t.stop();
				paused = true;
				this.repaint();
			}
			else {
				t.start();
				paused = false;
			}
			break;
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}



	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

}
