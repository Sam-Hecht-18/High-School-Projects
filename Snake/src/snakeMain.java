import javax.swing.JFrame;
import javax.swing.JPanel;


public class snakeMain extends JFrame{

	public static void main(String[] args) {
		snakeMain window = new snakeMain();
	    JPanel p = new JPanel();
	    p.add(new snakePanel());  //  add a class that extends JPanel
	    window.setTitle("Snake!");
	    window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    window.setContentPane(p);
	    window.pack();
	    window.setLocationRelativeTo(null);
	    window.setVisible(true);

	}

}
