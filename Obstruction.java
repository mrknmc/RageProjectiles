import java.awt.Point;

public class Obstruction extends Component {
	
	// Need to move the corner inside a bit
	public Point getRightCorner() {
		return new Point(getPosition().x + getWidth() - 2, getPosition().y + getHeight() + 2);
	}
	
	// Need to move the corner inside a bit
	public Point getLeftCorner() {
		return new Point(getPosition().x + 2, getPosition().y + 2);
	}
	
	// Constructor
	public Obstruction(Point p, int w, int h, String i) {
		super(p, w, h, i);
	}

}