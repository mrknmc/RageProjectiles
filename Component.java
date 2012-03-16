import java.awt.Point;

public class Component {
	private int height;
	private int width;	
	private Point position;

	public Point getPosition() {
			return position;
	}

	public void setPosition(Point p) {
		this.position = p;
	}
	
	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}
	
	public Point bRCorner() {
		return new Point(position.x+width, position.y+height);
	}
	
	public Component(Point aPoint, int aWidth, int aHeight) {
		position = aPoint;
		height = aHeight;
		width = aWidth;
	}

}