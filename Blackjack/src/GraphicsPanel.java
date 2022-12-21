import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

public class GraphicsPanel extends JPanel implements MouseListener{
	private Timer t;
	private Deck deck;
	private ImageIcon background;
	private Hand player;
	private Hand dealer;
	private Rectangle hitButton;
	private Rectangle doubleDownButton;
	private Rectangle standButton;
	private Rectangle reshuffleButton;
	private Rectangle splitButton;
	private int count;
	public GraphicsPanel() {
		setPreferredSize(new Dimension(1000,600));    
		deck = new Deck();
		t = new Timer(3, new ClockListener(this));   
		t.start();
		this.setFocusable(true);					 
		this.addMouseListener(this);

		ClassLoader cldr = this.getClass().getClassLoader();	
		String imagePath =  "Card Images/background.png";				
		URL imageURL = cldr.getResource(imagePath);				
		background = new ImageIcon(imageURL);
		player = new Hand(false);
		dealer = new Hand(true);
		hitButton = new Rectangle(200,425,100,50);
		doubleDownButton = new Rectangle(600,500,390,50);
		standButton = new Rectangle(800,425,180,50);
		reshuffleButton = new Rectangle(15,10,210,50);
		splitButton = new Rectangle(600,425,150,50);
		count = 0;
	}
	public void paintComponent(Graphics g) {
		//Make so reshuffle screen is nicer and it deals out cards one by one and everything is smoother in general and then add splitting and doubling and betting
		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(Color.BLUE);
		if(deck.size() == 0) {
			g2.setColor(Color.orange);
			g2.fill(reshuffleButton);
			g2.setFont(new Font("Times New Roman", Font.BOLD, 50));
			g2.setColor(Color.BLUE);
			g2.drawString("Reshuffle", 20, 50);
		}
		else {
			if(player.getScore() == 0) {
				deal(deck,dealer,player);
			}
			
			
			background.paintIcon(this, g2, 0, 0);
			deck.draw(this, g2);
			if(player.isDoubleable()) {
				g2.setColor(Color.BLUE);
				g2.fill(doubleDownButton);
				g2.setColor(Color.orange);
				g2.setFont(new Font("Times New Roman", Font.BOLD, 50));
				g2.drawString("DOUBLE DOWN", 605, 540);
			}
			
			g2.setColor(Color.BLUE);
			g2.fill(hitButton);
			g2.setColor(Color.orange);
			g2.setFont(new Font("Times New Roman", Font.BOLD, 50));
			g2.drawString("HIT", 205, 465);
			g2.setColor(Color.CYAN);
			g2.drawString("Dealer wins: " + dealer.getWins(), 20, 50);
			g2.drawString("Player wins: " + player.getWins(), 650, 50);
			if(player.isSplittable()) {
				g2.setColor(Color.BLUE);
				g2.fill(splitButton);
				g2.setColor(Color.orange);
				g2.drawString("SPLIT", 605, 465);
			}
			g2.setColor(Color.BLUE);
			g2.fill(standButton);
			g2.setColor(Color.orange);
			g2.drawString("STAND", 805, 465);
			if(count != 1) {
				for(int i = 0; i<=player.splits(); i++) {
					if(dealer.isFlipped() && !player.didBust(i) && !dealer.isStanding() && !dealer.didBust()) {
						dealer.autoHit(deck);
						break;
					}
					else if(dealer.isStanding() || dealer.didBust()) {
						dealer.didWin(player);
						player.reset();
						dealer.reset();
						t.setDelay(3);
					}
					if(player.didBust(i) && dealer.isFlipped()) {
						dealer.didWin(player);
						player.reset();
						dealer.reset();
						t.setDelay(3);
					}
					
					//When splitting if the first hand busts the dealer gets flipped, this obviously isn't supposed to happen
					else if(player.isStanding() || player.didBust(i)) {
						dealer.flip();
						t.setDelay(1000);
						count = 1;
					}
					if(dealer.isBlackJack() || player.isBlackJack()) {
						dealer.stand();
						player.stand();
						dealer.flip();
						t.setDelay(1000);
						count = 1;
					}
				}

			}
			else
				count = 0;
		}

		player.draw(this, g2);
		dealer.draw(this, g2);

	}
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		if(hitButton.contains(e.getX(),e.getY())) {
			player.hit(deck);
		}

		else if(standButton.contains(e.getX(),e.getY())) {
			player.stand();
		}

		else if(deck.size() == 0 && reshuffleButton.contains(e.getX(),e.getY())) {
			deck = new Deck();
			player.reset();
			dealer.reset();
		}
		else if(player.isSplittable() && splitButton.contains(e.getX(), e.getY())) {
			player.split();
		}
		else if(player.isDoubleable() && doubleDownButton.contains(e.getX(), e.getY())) {
			player.doubleDown(deck);
		}

	}
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}
	public void clock() {
		//if(player.stands())
		this.repaint();
	}
	public static void deal(Deck deck, Hand dealer, Hand player) {
		if(deck.size() > 0) 
			player.hit(deck);
		if(deck.size() > 0) 
			dealer.hit(deck);
		if(deck.size() > 0) 
			player.hit(deck);
		if(deck.size() > 0) 
			dealer.hit(deck);

	}
}
