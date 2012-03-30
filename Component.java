import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public abstract class Component {

	// Object Attributes
	private int height;
	private int width;	
	private Point2D.Double position;
	private BufferedImage image;

	//Getters
	public Point2D.Double getPosition() {
			return position;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}
	
	// Getters
	public int getRadius() {
		return getHeight() / 2;
	}
	
	public BufferedImage getImage() {
		return image;
	}
	
	public void setImage(BufferedImage i) {
		image = i;
	}
	
	// Setters
	public void setPosition(Point2D.Double p) {
		position = p;
	}
	
	public Point2D.Double getCenter() {
		double x = getPosition().x + (getWidth()/2);
		double y = getPosition().y + (getHeight()/2);
		
		return (new Point2D.Double(x,y));
	}
	
	// Constructor
	public Component(Point2D.Double aPoint, int aWidth, int aHeight, String i) {
		position = aPoint;
		height = aHeight;
		width = aWidth;
		try {                
			image = ImageIO.read(new File(i));
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public Component() {
		
	}
}
