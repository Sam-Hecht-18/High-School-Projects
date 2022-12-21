import java.awt.Point;

public class dot {
	private Point local;
	public dot(int xValue, int yValue) { 
		local = new Point();
		local.move(xValue,yValue);
		
	}
	public Point getLocal() {
		return local;
	}
	public void setLocal(Point local) {
		this.local = local;
	}
	public void reset() {
		local.move((int)(Math.random()*23)+1,(int)(Math.random()*23)+1);
		if(local.getY()<1)
			local.move((int)local.getX(), (int)local.getY()+1);
		if(local.getY()>23)
			local.move((int)local.getX(), (int)local.getY()-1);
		if(local.getX()>23)
			local.move((int)local.getX()-1, (int)local.getY());
		if(local.getX()<1)
			local.move((int)local.getX()+1, (int)local.getY());
	}
}
