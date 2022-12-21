// Class: Screens
// Written by: Sam Hecht
// Date: 6/11/2019
// Description: This class has an arraylist of screens and an index. It contains methods
//which move through the array and this class is the background of the game
//
import java.awt.Component;
import java.awt.Graphics;
import java.util.ArrayList;

public class Screens {
	private ArrayList<Screen> screens;
	private int index;
	public Screens() {
		screens = new ArrayList<>();
		screens.add(new Screen(2));
		screens.add(new Screen(1));
		screens.add(new Screen(1));
		screens.add(new Screen(2));
		screens.add(new Screen(2));
		screens.add(new Screen(2));
		screens.add(new Screen(3));
		screens.add(new Screen(1));
		screens.add(new Screen(2));
		screens.add(new Screen(1));
		index = 0;
	}
	public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
	}
	//Goes to the next index, which goes to the next screen
	public void nextIndex() {
		if(index<screens.size())
			index++;
	}
	//Goes to the previous index, which goes to the previous screen
	public void prevIndex() {
		if(index>0)
			index--;
	}
	public void drawScreen(Graphics g, Component c) {
		screens.get(index).drawScreen(g, c);
	}
	public void nextScreen(String imagePath) {
		screens.get(index).nextImage(imagePath);
	}
	
	
}
