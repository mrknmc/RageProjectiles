import java.awt.Point;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Projectile extends Component {
	
	// Object Attributes
	private final Point initialPosition;
	private Velocity velocity;
	private boolean hit = false;
	private int bounceCount = 0;
	
	// Getters
	public Velocity getVelocity() {
		return velocity;
	}
	
	public int getRadius() {
		return getHeight() / 2;
	}
	
	public boolean getHit() {
		return hit;		
	}
	
	public int getBounceCount(){
		return bounceCount;
	}
	
	// Setters	
	public void setVelocity(Velocity v) {
		velocity = v;
	}
	
	public void setHit(boolean b) {
		hit = b;
	}
	
	// Class Methods
	public void reset() {
		this.setPosition(initialPosition);
		this.setVelocity(new Velocity(0,0));
		bounceCount = 0;
		hit = false;
		try {
			this.setImage(ImageIO.read(new File("img/LolGuy.png")));
		} catch (IOException e1) {
			
		}
	}
		
	public void move(int x, int y) {
		x = getPosition().x + x;
		y = getPosition().y + y;
		
		Point pos = new Point(x,y);		
		setPosition(pos);
	}
	
	public void bounce() {
		if (bounceCount < 7) {
			velocity.setYComponent(velocity.getYComponent() * -0.5);			// Should make a boolean
			velocity.setYComponent(velocity.getYComponent() * 0.8);
		} else {
			velocity.setYComponent(0);
			if (hit == false) {
				try {                
					setImage(ImageIO.read(new File("img/okayGuy.png")));
				} catch (IOException ex) {
					// handle exception...
				}
			}
		}
		bounceCount++;
	}
	
	public void bounceLeft() {
			velocity.setXComponent(velocity.getXComponent() * -0.5);
	}
	
	public boolean gonnaHitTarget(Target t) {
		double d = Math.sqrt(Math.pow(getCenter().getX() - t.getCenter().getX(), 2) + Math.pow(getCenter().getY() - t.getCenter().getY(), 2));
		if (d <= (getRadius() + t.getRadius())) {
			hit = true;
			t.destroy();
			try {                
				setImage(ImageIO.read(new File("img/Troll.png")));
			} catch (IOException ex) {
				// handle exception...
			}
			return true;
		}
		return false;
	}
	
	public boolean gonnaHitObstruction(Obstruction o) {
		int leftBorder = o.getPosition().x;
		int rightBorder = o.getPosition().x + o.getWidth();
		int topBorder = o.getPosition().y;
		
		if (getPosition().x >= (leftBorder - getWidth())) {
			if (getPosition().y >= (topBorder - getRadius())) {
				bounceLeft();
				return true;
			}
			if (getPosition().x <= (rightBorder - getRadius())) {
				if (getPosition().y >= (topBorder - getHeight())) {
					bounce();
					return true;
				}
			}
		}
		return false;
	}
	
	// Constructor
	public Projectile(Point p, int w, int h) {
		super(p, w, h, "img/LolGuy.png");
		initialPosition = p;
		
	}
}