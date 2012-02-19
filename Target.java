import java.awt.Point;

public class Target {
	int size;
	Point position;
	Point leftBottomCorner;
	Point rightTopCorner;
	Point rightBottomCorner;
	
	public int getSize(){
		return this.size;
	}

	public Point getPosition(){
			return this.position;
	}

	public void setPosition(Point newPoint){
			this.position = newPoint;
	}

	public void destroyed(){
	// Not really sure what should go here.

	}
	
	public Target(Point aPoint, int aSize) {
		this.position = aPoint;
		this.size = aSize;
		this.leftBottomCorner = new Point(this.position.x, this.position.y+this.size);
		this.rightTopCorner = new Point(this.position.x+this.size, this.position.y);
		this.rightBottomCorner = new Point(this.position.x+this.size, this.position.y+this.size);
	}
}