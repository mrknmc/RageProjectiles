public class Target extends Component {
	int size;

	public int getSize(){
		return this.size;
	}

	public Target(Point aPoint, int aSize) {
		this.position = aPoint;
		this.size = aSize;
		this.leftBottomCorner = new Point(this.position.x, this.position.y+this.size);
		this.rightTopCorner = new Point(this.position.x+this.size, this.position.y);
		this.rightBottomCorner = new Point(this.position.x+this.size, this.position.y+this.size);
	}
}