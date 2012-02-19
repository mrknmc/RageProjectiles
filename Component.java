import java.awt.Point;

public class Component {
	Point position;
	Point leftBottomCorner;
	Point rightTopCorner;
	Point rightBottomCorner;

	public Point getPosition() {
			return this.position;
	}

	public void setPosition(Point newPoint) {
			this.position = newPoint;
	}

	public void destroyed() {
	// Not really sure what should go here.

	}
	
	public Component(Point aPoint, int aSize) {
		this.position = aPoint;
		this.leftBottomCorner = new Point(this.position.x, this.position.y+this.size);
		this.rightTopCorner = new Point(this.position.x+this.size, this.position.y);
		this.rightBottomCorner = new Point(this.position.x+this.size, this.position.y+this.size);
	}

}