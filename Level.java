import java.util.ArrayList;

public class Level {
	Projectile projectile;
	ArrayList<Obstruction> obstructions = new ArrayList<Obstruction>();
	ArrayList<Target> targets = new ArrayList<Target>();
	boolean finished = false;
	
	public Projectile getProjectile() {
		return projectile;
	}
	
	public ArrayList<Target> getTargets() {
		return targets;
	}
	
	public ArrayList<Obstruction> getObstructions() {
		return obstructions;
	}
	
	public Level(Projectile p, ArrayList<Obstruction> obs, ArrayList<Target> ts) {
		projectile = p;
		obstructions = obs;
		targets = ts;
	}
}
