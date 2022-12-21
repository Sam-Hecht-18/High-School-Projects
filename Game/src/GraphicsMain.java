// Class: GraphicsMain
// Written by: Sam Hecht/Mr. Swope
// Date: 6/11/2019
// Description: This class is the class which runs the project. It contains the main method
//
//import javax.swing.JApplet;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.applet.*;
import java.awt.Dimension;
// import javax.swing.JApplet;
//import javax.swing.SwingUtilities;

// public class GraphicsMain extends JApplet{
// 	public void init() {
// 		GraphicsMain window = new GraphicsMain();
// 		window.add(new GraphicsPanel());
// 	}
// }	

// public class GraphicsMain extends JApplet {
// 	/**
// 	 * 
// 	 */
// 	private static final long serialVersionUID = 1916527107977839975L;

// 	//Called when this applet is loaded into the browser.
// 	public void init() {
// 		resize(1024,576);
// 		try {
// 			SwingUtilities.invokeAndWait(new Runnable() {
// 				public void run() {
// 					createGUI();
// 				}
// 			});
// 		} catch (Exception e) { 
// 			System.err.println("createGUI didn't complete successfully");
// 		}
// 	}

// 	private void createGUI() {
// 		//Create and set up the content pane.
// 		GraphicsPanel newContentPane = new GraphicsPanel();
// 		newContentPane.setOpaque(true);
// 		setContentPane(newContentPane);        
// 	}        
// }



public class GraphicsMain extends JFrame {
	public static void main(String[] args) {
		GraphicsMain window = new GraphicsMain();
		
	    JPanel p = new JPanel();
	    p.add(new GraphicsPanel()); 
	    window.setTitle("Game");
	    window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    window.setContentPane(p);
	    window.pack();
	    //window.setPreferredSize(new Dimension(1024,576));
	    window.setLocationRelativeTo(null);
	    window.setVisible(true);
	    
	}


}
