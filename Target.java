import java.awt.geom.Point2D;

public class Target extends Component {
	
	// Object Attributes
	private boolean alive = true;
	
	// Class Methods
	public boolean isAlive() {
		return alive;
	}
	
	public void destroy() {
		alive = false;
	}
	
	// Constructor
	public Target(Point2D.Double p, int w, int h) {
		super(p, w, h, "img/MeGusta.png");
	}
}