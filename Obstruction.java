public class Obstruction extends Component {

	private int width;
	private int height;

	// Methods for getting the value of attributes.
	public int getWidth() {
		return this.width;
	}

	public int getHeight() {
		return this.height;
	}

	// Constructor
	public Obstruction(Point p, int h, int w) {
		this.width = w;
		this.height = h;
		this.position = p;
	}

}