import java.awt.Point;

public class Target extends Component {
	private boolean alive = true;

	public void destroy() {
		alive = false;
	}
	
	public boolean isAlive() {
		return alive;
	}
	
	public Target(Point p, int w, int h) {
		super(p,w,h);
	}
}