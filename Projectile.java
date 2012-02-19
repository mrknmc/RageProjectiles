public class Projectile extends Component {
	// Attributes for the object
	int size;
	
	// Methods for getting the value of attributes
	public int getSize(){
		return this.size;
	}
	
	// Constructor
	public Projectile(Point p, int s) {
		this.size = s;
		this.position = p;
		this.leftBottomCorner = new Point(position.x, position.y+size);
		this.rightTopCorner = new Point(position.x+size, position.y);
		this.rightBottomCorner = new Point(position.x+size, position.y+size);

	}

	}