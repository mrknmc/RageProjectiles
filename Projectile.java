<<<<<<< HEAD

public class Projectile {
  

			// Attributes for the object
			private int width;
			private int height;
			private weight; 
			private colour;
			Point origin;
			Point leftBottomCorner;
			Point rightTopCorner;
			Point rightBottomCorner;


			// Methods for getting the value of attributes
			public int getWidth(){
			return this.width;
			}
			
			public int getHeight(){
			return this.height; 
			}

			public int getWeight(){
			return this.weight;
			}

			//public void 
			//this.
			//}
			
			// Not too sure what I should be putting here


			// Constructor
			public ProjectileBirds(int w, int h, color c){
			this.width = w;
			this.height = h;
			this.colour = c;
			this.leftBottomCorner = new Point(origin.x, origin.y+size);
			this.rightTopCorner = new Point(origin.x+size, origin.y);
			this.rightBottomCorner = new Point(origin.x+size, origin.y+size);

			}

			}
=======
import java.awt.Point;

public class Projectile {
	int size;
	Point origin;
	Point leftBottomCorner;
	Point rightTopCorner;
	Point rightBottomCorner;
	
	public Projectile(Point aPoint, int aSize) {
		origin = aPoint;
		size = aSize;
		leftBottomCorner = new Point(origin.x, origin.y+size);
		rightTopCorner = new Point(origin.x+size, origin.y);
		rightBottomCorner = new Point(origin.x+size, origin.y+size);
	}
}
>>>>>>> world
