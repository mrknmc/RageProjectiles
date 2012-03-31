import java.awt.geom.Point2D;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Projectile extends ActiveComponent {
	
	// Object Attributes
	//private final Point2D.Double initialPosition;
	private Velocity velocity;
	private boolean hit = false;
	private int bounceCount = 0;
	private double rotate = 0;
	private boolean launched = false;
	
	public void setLaunched(boolean l) {
		launched = l;
	}

	public boolean isLaunched() {
		return launched;
	}

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
	
	public void setBounceCount(int b) {
		bounceCount = 0;
	}
	
	public void resetVelocity() {
		velocity = new Velocity(0,0);
	}
	
	/*
	// Class Methods
	public void reset() {
		System.out.println(initialPosition.x + " " + initialPosition.y);
		this.setPosition(initialPosition);
		this.setVelocity(new Velocity(0,0));
		bounceCount = 0;
		hit = false;
		this.resetRotate();
		try {
			this.setImage(ImageIO.read(new File("img/LolGuy.png")));
		} catch (IOException e) {
			
		}
	}
	*/
		
	public void move(double dx, double dy) {
		double x = getPosition().getX();
		double y = getPosition().getY();
		setPosition(new Point2D.Double(x+dx, y+dy));
	}
	
	public void bounce(double a) {
		double yc = getVelocity().getYComponent();
		if (yc < 0) {
			if (bounceCount < 10) {
				velocity.setYComponent(yc * -a);
				velocity.setXComponent(velocity.getXComponent() * a);
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
	
	public void bounce() {
		bounce(0.8);
	}
	
	public void bounceLeft() {
		double xc = getVelocity().getXComponent();
		if (xc >= 0) {
			velocity.setXComponent(xc * -0.6);
			rotate = -rotate;
		}
	}
	
	public void bounceRight() {
		double xc = getVelocity().getXComponent();
		if (xc <= 0) {
			velocity.setXComponent(xc * -0.6);
		}
	}
	
	public void rotate() {
		if (rotate < 0){
			rotate -= 0.01;
		}
		else {
			rotate += 0.01;
		}
	}
	
	public void gonnaHitTarget(Target t) {
		double d = getCenter().distance(t.getCenter());
		if (d <= (getRadius() + t.getRadius())) {
			hit = true;
			t.destroy();
			try {                
				setImage(ImageIO.read(new File("img/Troll.png")));
			} catch (IOException ex) {
				// handle exception...
			}
		}
	}
	
	public void gonnaHitObstruction(Obstruction o) {
		// Obstruction variables
		double leftBorder = o.getPosition().x;
		double rightBorder = o.getPosition().x + o.getWidth();
		double topBorder = o.getPosition().y;
		
		// Projectile variables
		Point2D.Double center = getCenter();
		int radius = getRadius();
		
		if ((center.x + radius) >= leftBorder) {
			// Left corner bounce
			if (center.x <= leftBorder && center.distance(o.getLeftCorner()) <= radius) {
				bounce();
				// If moving right, bounce left
				if (velocity.getXComponent() > 0) {
					bounceLeft();
				}
			}
			// Right corner bounce
			else if (center.distance(o.getRightCorner()) <= radius) {
				bounce();
				// If moving left, bounce right
				if (velocity.getXComponent() < 0) {
					bounceRight();
				}
			}
			// Left & right side bounce
			else if (center.y >= topBorder) {
				// Left bounce if left from the obstruction center
				if (center.x < o.getCenter().x) {
					bounceLeft();
				}
				// Right bounce otherwise
				else if (center.x - radius <= rightBorder) {
					bounceRight();
				}
			}
			// Top bounce
			else if (center.x > leftBorder && center.x <= rightBorder) {
				if ((center.y + radius) >= topBorder) {
					bounce();
				}
			}
		}
	}
	
	// Constructor
	public Projectile(Point2D.Double p, int w, int h) {
		super(p, w, h, "img/LolGuy.png");
		velocity = new Velocity(0,0);
		//initialPosition = p;
	}
}