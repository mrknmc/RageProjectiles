import java.awt.geom.Point2D;


public class ActiveComponent extends Component {
	// Object Attributes
	private boolean alive = true;
	
	// Class Methods
	public boolean isAlive() {
		return alive;
	}
	
	public void destroy() {
		alive = false;
	}
	
	public ActiveComponent(Point2D.Double aPoint, int aWidth, int aHeight, String i) {
		super(aPoint, aWidth, aHeight, i);
	}
}
