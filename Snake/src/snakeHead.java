import java.awt.Point;

public class snakeHead {
	
	private boolean upAccel;
	private boolean downAccel;
	private boolean rightAccel;
	private boolean leftAccel;
	private Point local;
	
	
	public snakeHead() {
		this(false,false,false,false, new Point());
	}
	
	public snakeHead(int xValue, int yValue) {
		this(false,false,false,false, new Point());
		local.move(xValue,yValue);
		
	}
	public snakeHead(boolean upAccel, boolean downAccel, boolean rightAccel, boolean leftAccel, Point local) {
		
		this.upAccel = upAccel;
		this.downAccel = downAccel;
		this.rightAccel = rightAccel;
		this.leftAccel = leftAccel;
		this.local = local;
	}
	
	public void setUpAccel(boolean truey) {
		if(!downAccel) {
			this.upAccel = truey;
			this.rightAccel = false;
			this.leftAccel = false;
		}
	}
	public void setDownAccel(boolean truey) {
		if(!upAccel) {
			this.downAccel = truey;
			this.rightAccel = false;
			this.leftAccel = false;
		}
	}
	public void setRightAccel(boolean truey) {
		if(!leftAccel) {
			this.rightAccel = truey;
			this.downAccel = false;
			this.upAccel = false;
			
		}
	}
	public void setLeftAccel(boolean truey) {
		if(!rightAccel) {
			this.leftAccel = truey;
			this.downAccel = false;
			this.upAccel = false;
		}
	}
	public int getAccel() {
		if(rightAccel)
			return 1;
		else if(leftAccel)
			return -1;
		else if(upAccel)
			return 2;
		else 
			return -2;
	}
	public boolean move() {
		if(upAccel)
			local.move((int)local.getX(), (int)local.getY()-1);
		else if(downAccel)
			local.move((int)local.getX(), (int)local.getY()+1);
		else if(rightAccel)
			local.move((int)local.getX()+1, (int)local.getY());
		else if(leftAccel)
			local.move((int)local.getX()-1, (int)local.getY());
		if(local.getY()<1 || local.getY()>23 || local.getX()>23 || local.getX()<1)
			return true;
		return false;
			
	}
	public void reset() {
		local.move((int)(Math.random()*23)+1,(int)(Math.random()*23)+1);
		leftAccel = false;
		upAccel = false;
		downAccel = false;
		rightAccel = false;
	}
	public Point getLocal() {
		return local;
	}
	public void setLocal(Point local) {
		this.local = local;
	}
	
	
	
}
