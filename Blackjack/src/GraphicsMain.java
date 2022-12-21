// Class: GraphicsMain
// Written by: Sam Hecht/Mr. Swope
// Date: 6/11/2019
// Description: This class is the class which runs the project. It contains the main method
//

import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JPanel;

//public class GraphicsMain extends JApplet{
//	public void init() {
//		GraphicsMain window = new GraphicsMain();
//		window.add(new GraphicsPanel());
//		import javax.swing.JApplet;
//	}
public class GraphicsMain extends JFrame {



	public static void main(String[] args) {
		GraphicsMain window = new GraphicsMain();
		
	    JPanel p = new JPanel();
	    p.add(new GraphicsPanel()); 
	    window.setTitle("Blackjack");
	    window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    window.setContentPane(p);
	    window.pack();
	    window.setSize(new Dimension(1000,600));
	    window.setLocationRelativeTo(null);
	    window.setVisible(true);
	    
	}


}
