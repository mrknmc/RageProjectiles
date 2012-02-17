import java.awt.Point;

public class Obstruction {

	// Attributes for the object. I think these are all we decided on,
	// if there are more just let me know.
	private int width;
	private int height;
	private Point position; // The Point and Color data types will need 
	private Color colour;   // to be declared seperately.


	// Methods for getting the value of attributes.
	public int getWidth(){
		return this.width;
	}

	public int getHeight(){
		return this.height;
	}

	public Point getPosition(){
			return this.position;
	}

	public void setPosition(Point newPoint){
			this.position = newPoint;
	}

	public void destroyed(){
	// Not really sure what should go here.

	}


	// Constructor
	public Obstruction(Point p, int h, int w){
		this.width = w;
		this.height = h;
		this.position = p;
		this.colour = c;

	}

}