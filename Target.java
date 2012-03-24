import java.awt.Point;

public class Target extends Component {
	
	// Object Attributes
	private boolean alive = true;
	
	// Getters
	public int getRadius() {
		return getHeight() / 2;
	}
	
	// Class Methods
	public boolean isAlive() {
		return alive;
	}
	
	public void destroy() {
		alive = false;
	}
	
	// Constructor
	public Target(Point p, int w, int h) {
		super(p, w, h, "img/MeGusta.png");
	}
}