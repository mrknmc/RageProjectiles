import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Target extends Component {
	private boolean alive = true;
	private BufferedImage image;

	public void destroy() {
		alive = false;
	}
	
	public int getRadius() {
		return getHeight() / 2;
	}
	
	public BufferedImage getImage() {
		return image;
	}
	
	public boolean isAlive() {
		return alive;
	}
	
	public Target(Point p, int w, int h) {
		super(p,w,h);
		try {                
			image = ImageIO.read(new File("img/MeGusta.png"));
		} catch (IOException ex) {
			// handle exception...
		}
	}
}