import java.awt.Point;

public class Projectile extends Component {
	// Attributes for the object
	private int speed;
	private int angle;
	
	// Methods for getting the value of attributes	
	public int getSpeed() {
		return this.speed;
	}
	
	public void setSpeed(int s) {
		this.speed = s;
	}
	
	public int getAngle() {
		return this.angle;
	}
	
	public void setAngle(int a) {
		this.angle = a;
	}
	
	public void move(int x, int y) {
		x = getPosition().x + x;
		y = getPosition().y + y;
		
		Point pos = new Point(x,y);		
		setPosition(pos);
	}
	
	// Constructor
	public Projectile(Point p, int w, int h, int s) {
		super(p, w, h);
		this.speed = s;
	}

	}