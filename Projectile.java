import java.awt.Point;

public class Projectile extends Component {
	// Attributes for the object
	private Velocity velocity;
	
	// Methods for getting the value of attributes	
	public Velocity getVelocity() {
		return velocity;
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
	
	// Constructor
	public Projectile(Point p, int w, int h) {
		super(p, w, h);
	}
}