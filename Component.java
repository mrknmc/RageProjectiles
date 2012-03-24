import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public abstract class Component {

	// Object Attributes
	private int height;
	private int width;	
	private Point position;
	private BufferedImage image;

	//Getters
	public Point getPosition() {
			return position;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}
	
	public BufferedImage getImage() {
		return image;
	}
	
	public void setImage(BufferedImage i) {
		image = i;
		System.out.println("IMAGE SET");
	}
	
	// Setters
	public void setPosition(Point p) {
		this.position = p;
	}
	
	public Point getCenter() {
		int x = (int) getPosition().x + (getWidth()/2);
		int y = (int) getPosition().y + (getHeight()/2);
		
		return (new Point(x,y));
	}
	
	// Class Methods
	public Point bRCorner() {
		return new Point(position.x+width, position.y+height);
	}
	
	// Constructor
	public Component(Point aPoint, int aWidth, int aHeight, String i) {
		position = aPoint;
		height = aHeight;
		width = aWidth;
		try {                
			image = ImageIO.read(new File(i));
		} catch (IOException ex) {
			// handle exception...
		}
	}
}
