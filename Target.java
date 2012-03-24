import java.awt.Point;
import java.awt.image.BufferedImage;

public class Target extends Component {
	
	// Object Attributes
	private boolean alive = true;
	private BufferedImage image;
	
	// Getters
	public int getRadius() {
		return getHeight() / 2;
	}
	
	public BufferedImage getImage() {
		return image;
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