// GraphicsMain.java
// Written by: Mr. Swope
// Date: February 10th, 2015
// This is the main class for a simple java program that uses graphics.  You do not need to edit this class.

import javax.swing.JFrame;
import javax.swing.JPanel;
public class CheckersMain extends JFrame{

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		CheckersMain window = new CheckersMain();
        JPanel p = new JPanel();
        p.add(new CheckersPanel());  //  add a class that extends JPanel
        window.setTitle("Checkers");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        window.setContentPane(p);
        
        window.pack();
        window.setLocationRelativeTo(null);
        window.setVisible(true);
	}

}
