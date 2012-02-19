import java.awt.Point;
//import java.awt.Color;

public class Projectile {
  

			// Attributes for the object
			int size;
		//	private Color colour;
			Point position;
			Point leftBottomCorner;
			Point rightTopCorner;
			Point rightBottomCorner;


			// Methods for getting the value of attributes
			public int getSize(){
			return this.size;
			}

			
			// Not too sure what I should be putting here


			// Constructor
			public Projectile(Point p, int s) {
				this.size = s;
				this.position = p;
				this.leftBottomCorner = new Point(position.x, position.y+size);
				this.rightTopCorner = new Point(position.x+size, position.y);
				this.rightBottomCorner = new Point(position.x+size, position.y+size);

			}

}