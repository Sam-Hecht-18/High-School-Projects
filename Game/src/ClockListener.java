// Class: GraphicsPanel
// Written by: Mr. Swope
// Date: 6/11/2019
// Description: Does the clock listening
//
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ClockListener implements ActionListener {

	GraphicsPanel f;
	
	ClockListener(GraphicsPanel c)
	{
		f = c;
	}
	
	public void actionPerformed(ActionEvent e) {
            f.clock();
	}

}
