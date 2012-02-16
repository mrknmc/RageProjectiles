import java.awt.Point;

public class Target {
	int size;
	Point origin;
	Point leftBottomCorner;
	Point rightTopCorner;
	Point rightBottomCorner;
	
	public Target(Point aPoint, int aSize) {
		origin = aPoint;
		size = aSize;
		leftBottomCorner = new Point(origin.x, origin.y+size);
		rightTopCorner = new Point(origin.x+size, origin.y);
		rightBottomCorner = new Point(origin.x+size, origin.y+size);
	}
}