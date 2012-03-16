import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Projectile extends Component {
	// Attributes for the object
	private Velocity velocity;
	
	// Methods for getting the value of attributes	
	public Velocity getVelocity() {
		return velocity;
	}
	
	public int getRadius() {
		return getHeight() / 2;
	}
	
	public void setVelocity(Velocity v) {
		velocity = v;
	}
	
	public void move(int x, int y) {
		x = getPosition().x + x;
		y = getPosition().y + y;
		
		Point pos = new Point(x,y);		
		setPosition(pos);
	}
	
	public boolean gonnaHit(Target t) {
		double d = Math.pow(getPosition().getX() - t.getPosition().getX(), 2) + Math.pow(getPosition().getY() - t.getPosition().getY(), 2);
		if (d <= Math.pow((getRadius() + t.getRadius()),2)) {
			return true;
		}
		return false;
	}
	
	// Constructor
	public Projectile(Point p, int w, int h) {
		super(p, w, h);
	}
}