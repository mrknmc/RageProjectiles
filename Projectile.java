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
	private double rotate = 0;
	
	// Getters
	public Velocity getVelocity() {
		return velocity;
	}
	
	public double getRotate() {
		return rotate;
	}

	public void setRotate(double rotate) {
		this.rotate += rotate;
	}

	public void resetRotate() {
		this.rotate = 0;
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
		this.resetRotate();
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
		if (getVelocity().getYComponent() < 0) {
			if (bounceCount < 10) {
				velocity.setYComponent(velocity.getYComponent() * -0.8);			// Should make a boolean
				velocity.setXComponent(velocity.getXComponent() * 0.8);
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
		}
		bounceCount++;
	}
	
	public void bounceLeft() {
		if (getVelocity().getXComponent() >= 0) {
			velocity.setXComponent(velocity.getXComponent() * -0.6);
			rotate = -rotate;
		}
	}
	
	public void bounceRight() {
		if (getVelocity().getXComponent() <= 0) {
			velocity.setXComponent(velocity.getXComponent() * -0.6);
		}
	}
	
	public void rotate(){
		if (rotate < 0){
			rotate -= 0.05;
		}
		else {
			rotate += 0.05;
		}
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
	
	public void gonnaHitObstruction(Obstruction o) {
		int leftBorder = o.getPosition().x;
		int rightBorder = o.getPosition().x + o.getWidth();
		int topBorder = o.getPosition().y;
		
		if ((getCenter().x + getRadius()) >= leftBorder) {
			// Left corner bounce
			if (getCenter().x <= leftBorder && getCenter().distance(o.getLeftCorner()) <= getRadius()) {
				bounce();
				// If moving right, bounce left
				if (velocity.getXComponent() > 0) {
					bounceLeft();
				}
			}
			// Left & right side bounce
			else if (getCenter().y >= topBorder) {
				// Left bounce if left from the obstruction center
				if (getCenter().x < o.getCenter().x) {
					bounceLeft();
				}
				// Right bounce otherwise
				else {
					bounceRight();
				}
			}
			// Top bounce
			else if (getCenter().x > leftBorder && getCenter().x <= rightBorder) {
				if ((getCenter().y + getRadius()) >= topBorder) {
					bounce();
				}
			}
			// Right corner bounce
			else if (getCenter().distance(o.getRightCorner()) <= getRadius()) {
				bounce();
				// If moving left, bounce right
				if (velocity.getXComponent() < 0) {
					bounceRight();
				}
			}
		}
	}
	
	// Constructor
	public Projectile(Point p, int w, int h) {
		super(p, w, h, "img/LolGuy.png");
		initialPosition = p;
		
	}
}