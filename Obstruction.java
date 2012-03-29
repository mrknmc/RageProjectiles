import java.awt.geom.Point2D;

public class Obstruction extends Component {
	
	// Need to move the corner inside a bit
	public Point2D.Double getRightCorner() {
		return new Point2D.Double(getPosition().x + getWidth() - 2, getPosition().y + 2);
	}
	
	// Need to move the corner inside a bit
	public Point2D.Double getLeftCorner() {
		return new Point2D.Double(getPosition().x + 2, getPosition().y + 2);
	}
	
	// Constructor
	
	public Obstruction(Point2D.Double p, int w, int h, String i) {
		super(p, w, h, i);
	}
}