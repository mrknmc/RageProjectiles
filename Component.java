import java.awt.Point;

public abstract class Component {

	// Object Attributes
	private int height;
	private int width;	
	private Point position;

	//Getters
	public Point getPosition() {
			return position;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}
	
	// Setters
	public void setPosition(Point p) {
		this.position = p;
	}
	
	public Point getCenter() {
		int x = (int) getPosition().x + (getWidth()/2);
		int y = (int) getPosition().y + (getHeight()/2);
		
		return (new Point(x,y));
	}
	
	// Class Methods
	public Point bRCorner() {
		return new Point(position.x+width, position.y+height);
	}
	
	// Constructor
	public Component(Point aPoint, int aWidth, int aHeight) {
		position = aPoint;
		height = aHeight;
		width = aWidth;
	}

}
