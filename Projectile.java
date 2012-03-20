import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Projectile extends Component {
	// Attributes for the object
	private Velocity velocity;
	private boolean hit = false;
	private int bounceCount = 0;
	private BufferedImage image;
	
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
	
	public void reset(Point p){
		this.setPosition(p);
		this.setVelocity(new Velocity(0,0));
		this.resetBounceCount();
		try {
			this.setImage(ImageIO.read(new File("img/LolGuy.png")));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			//e1.printStackTrace();
		}
	}
	
	public void setHit(boolean b) {
		hit = b;
	}
	
	public BufferedImage getImage() {
		return image;
	}

	public void setImage(BufferedImage i) {
		image = i;
	}
	
	public void move(int x, int y) {
		x = getPosition().x + x;
		y = getPosition().y + y;
		
		Point pos = new Point(x,y);		
		setPosition(pos);
	}
	
	public void resetBounceCount(){
		bounceCount = 0;
	}
	
	public void bounce() {
		if (bounceCount < 7) {
			velocity.setYComponent(velocity.getYComponent() * -0.5);
			bounceCount++;
		} else {
			velocity.setYComponent(0);
			if (!hit) {
				try {                
					image = ImageIO.read(new File("img/OkayGuy.png"));
				} catch (IOException ex) {
					// handle exception...
				}
			}
		}
	}
	
	public boolean gonnaHit(Target t) {
		double d = Math.pow(getPosition().getX() - t.getPosition().getX(), 2) + Math.pow(getPosition().getY() - t.getPosition().getY(), 2);
		if ((d <= Math.pow((getRadius() + t.getRadius()),2)) && t.isAlive() == true) {
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