import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Projectile extends Component {
	
	// Object Attributes
	private Velocity velocity;
	private boolean hit = false;
	private int bounceCount = 0;
	private BufferedImage image;
	
	// Getters
	public Velocity getVelocity() {
		return velocity;
	}
	
	public int getRadius() {
		return getHeight() / 2;
	}
	
	public BufferedImage getImage() {
		return image;
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
	
	public void setImage(BufferedImage i) {
		image = i;
		System.out.println("IMAGE SET");
	}
	
	public void setHit(boolean b) {
		hit = b;
	}
	
	// Class Methods
	public void reset(Point p){
		this.setPosition(p);
		this.setVelocity(new Velocity(0,0));
		bounceCount = 0;
		hit = false;
		try {
			this.setImage(ImageIO.read(new File("img/LolGuy.png")));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
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
			velocity.setYComponent(velocity.getYComponent() * -0.5);
			//bounceCount++;
		} else {
			velocity.setYComponent(0);
			if (hit == false) {
				try {                
					image = ImageIO.read(new File("img/okayGuy.png"));
				} catch (IOException ex) {
					// handle exception...
				}
			}
		}
		bounceCount++;
	}
	
	public boolean gonnaHit(Target t) {
		double d = Math.pow(getPosition().getX() - t.getPosition().getX(), 2) + Math.pow(getPosition().getY() - t.getPosition().getY(), 2);
		if ((d <= Math.pow((getRadius() + t.getRadius()),2)) && t.isAlive() == true) {
			hit = true;
			try {                
				image = ImageIO.read(new File("img/Troll.png"));
			} catch (IOException ex) {
				// handle exception...
			}
			return true;
		}
		return false;
	}
	
	// Constructor
	public Projectile(Point p, int w, int h) {
		super(p, w, h);
		try {                
			image = ImageIO.read(new File("img/LolGuy.png"));
		} catch (IOException ex) {
			// handle exception...
		}
	}
}