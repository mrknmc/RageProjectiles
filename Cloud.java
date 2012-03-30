import java.awt.geom.Point2D;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

public class Cloud extends Component {
	
	public void advance() {
		Point2D.Double pos = getPosition();
		double r = new Random().nextDouble();
		setPosition(new Point2D.Double(pos.x+r, pos.y));
	}
	
	public Cloud(Point2D.Double aPoint, String i) {
		try {                
			setImage(ImageIO.read(new File(i)));
			setPosition(aPoint);
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}
}
