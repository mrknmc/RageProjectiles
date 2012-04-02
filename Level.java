import java.util.ArrayList;

public class Level {
	ArrayList<Projectile> projectiles = new ArrayList<Projectile>();
	ArrayList<Obstruction> obstructions = new ArrayList<Obstruction>();
	ArrayList<Target> targets = new ArrayList<Target>();
	
	public ArrayList<Projectile> getProjectiles() {
		return projectiles;
	}
	
	public ArrayList<Target> getTargets() {
		return targets;
	}
	
	public ArrayList<Obstruction> getObstructions() {
		return obstructions;
	}
	
	public Level(ArrayList<Projectile> p, ArrayList<Obstruction> obs, ArrayList<Target> ts) {
		projectiles = p;
		obstructions = obs;
		targets = ts;
	}
}
