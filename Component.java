import java.awt.Point;

public class Component {
	int height;
	int width;	
	Point position;
	Point leftBottomCorner;
	Point rightTopCorner;
	Point rightBottomCorner;

	public Point getPosition() {
			return this.position;
	}

	public void setPosition(Point p) {
		this.position = p;
	}
	
	public int getWidth() {
		return this.width;
	}

	public int getHeight() {
		return this.height;
	}

	public void destroyed() {
	// Not really sure what should go here.

	}
	
	public Component(Point aPoint, int aWidth, int aHeight) {
		this.position = aPoint;
		this.leftBottomCorner = new Point(this.position.x, this.position.y+this.height);
		this.rightTopCorner = new Point(this.position.x+this.width, this.position.y);
		this.rightBottomCorner = new Point(this.position.x+this.width, this.position.y+this.height);
	}

}