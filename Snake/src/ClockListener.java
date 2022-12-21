import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ClockListener implements ActionListener {

	snakePanel f;
	
	ClockListener(snakePanel c)
	{
		f = c;
	}
	
	public void actionPerformed(ActionEvent e) {
            f.clock();
	}

}
