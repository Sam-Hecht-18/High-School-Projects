import java.awt.Point;
import java.util.ArrayList;

public class snakeBody {
	private Point local;
	private int count;
	private Point oldLocal;
	
	public snakeBody() {
		this(new Point(), 0);
	}
	public snakeBody(Point local) {
		this.setLocal(local);
	}
	public snakeBody(int x, int y) {
		local = new Point();
		local.move(x,y);
	}
	public snakeBody(int x, int y, int count) {
		local = new Point();
		local.move(x, y);
		this.count = count;
		oldLocal = new Point();
	}
	public snakeBody(Point local, int count) {
		this.setLocal(local);
		this.setCount(count);
	}
	
	public void move(ArrayList<Point> turningPoints,snakeHead head, int i) {
		oldLocal.move(local.x, local.y);
		if(count<turningPoints.size() && turningPoints.get(count).equals(local)) {
			count++;
			
		}
		if(count<turningPoints.size() && turningPoints.get(count).x>local.x) {
			local.move(local.x+1,local.y);
			
		}
		else if(count<turningPoints.size() && turningPoints.get(count).x<local.x) {
			local.move(local.x-1, local.y);
			
		}
		else if(count<turningPoints.size() && turningPoints.get(count).y<local.y) {
			local.move(local.x, local.y-1);
		
		}
		else if(count<turningPoints.size() && turningPoints.get(count).y>local.y) {
			local.move(local.x, local.y+1);
			
		}
		
		
		//Write code that makes it follow the one in front even if there isn't a turning point
		else if(head.getAccel() == 1)
			local.move(head.getLocal().x-(i+1),head.getLocal().y);
		else if(head.getAccel() == 2)
			local.move(head.getLocal().x,head.getLocal().y+(i+1));
		else if(head.getAccel() == -1)
			local.move(head.getLocal().x+(i+1),head.getLocal().y);
		else if(head.getAccel() == -2)
			local.move(head.getLocal().x,head.getLocal().y-(i+1));
		
			
	}
	public int getDirection() {
		//Going right
		if(oldLocal.x<local.x)
			return 1;
		//Going down
		else if(oldLocal.y<local.y)
			return -2;
		//Going left
		else if(oldLocal.x>local.x)
			return -1;
		//Going up
		else if(oldLocal.y>local.y)
			return 2;
		return 0;
		
	}
	public Point getLocal() {
		return local;
	}
	public void setLocal(Point local) {
		this.local = local;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
}
