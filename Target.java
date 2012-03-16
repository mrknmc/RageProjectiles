import java.awt.Point;

public class Target extends Component {
	private boolean alive = true;

	public void destroy() {
		alive = false;
	}
	
	public int getRadius() {
		return getHeight() / 2;
	}
	
	public boolean isAlive() {
		return alive;
	}
	
	public Target(Point p, int w, int h) {
		super(p,w,h);
	}
}