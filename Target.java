import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

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
		super(p,w,h);
		try {                
			image = ImageIO.read(new File("img/MeGusta.png"));
		} catch (IOException ex) {
			// handle exception...
		}
	}
}