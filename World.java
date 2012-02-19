import java.awt.Point;

public class World {
	double gravity; //constant
	int obstructionCount;
	static int targetCount;
	static Target[] targets;
	Obstruction[] obstructions;
	Projectile projectile;
	
	// Constructs a new world with the given parameters	
	public World(int anObstructionCount, int aTargetCount) {
		gravity = 196; // acceleration inpx/s^2
		obstructionCount = anObstructionCount;
		targetCount = aTargetCount;
		
		// Initialises new Projectile, Obstructions and Targets
		targets = new Target[targetCount];
		obstructions = new Obstruction[obstructionCount];
		Point projectileOrigin = new Point(50, 560);
		projectile = new Projectile(projectileOrigin, 30);
		
		// Fills the obstructions array with obstructions
		for (int i = 0; i < obstructionCount; i++) {
			Point obstructOrigin = new Point(800+(i*60), 560 );
			obstructions[i] = new Obstruction(obstructOrigin, 80, 20);
		}

		// Fills the targets array with targets
		for (int i = 0; i < targetCount; i++) {
			Point targetOrigin = new Point(795+(i*60), 560);
			targets[i] = new Target(targetOrigin, 30);
		} 
	}
	
	public void calculate(double anAngle, double aSpeed) {
		// Calculates whether a projectile hits the target
		
		double time;
		double xDifference;
		double yDifference;
		double angle = anAngle;
		double speed = aSpeed;
		double rad = Math.toRadians(angle);
		Point landing;
		
		for (int i = 0; i < targetCount; i++) {
			yDifference = projectile.rightBottomCorner.y - targets[i].position.y; // Calculates the difference in Y direction
			time = solveQuadratic(gravity/2, -speed * Math.sin(rad), yDifference);
			xDifference = speed * Math.cos(rad) * time;                           // Returns the difference in X direction
			landing = new Point(projectile.rightBottomCorner.x + (int) xDifference, targets[i].position.y); // projectile landing point
			
			if ( time == 0 ) {
				System.out.println("The target is too damn high!"); // If target is higher than projectile can reach
			} else if ( ( (landing.x >= targets[i].leftBottomCorner.x)
						&& (landing.x <= targets[i].rightBottomCorner.x) )
						|| ( (landing.x - projectile.size >= targets[i].leftBottomCorner.x)
						&& (landing.x - projectile.size <= targets[i].rightBottomCorner.x) ) ) {
				System.out.println("Target " + i+1 + " hit!");
			} else {
				System.out.println("Missed target #" + i+1 + "! Projectile landed at " + landing.x + ", " + landing.y);
			}
		}
		/*
		if (timeYs == "Im") {
			return 0;
		}
		double timeY = Double.parseDouble(timeYs);
		if ( Math.abs(timeX - timeY) <= 1 ) {
			System.out.println("It will hit the target.");
			return (1);
		} else {
			System.out.println("It won't hit the target.");
			System.out.println("timeX = " + timeX);
			System.out.println("timeY = " + timeY);
			return (0);
		}
		*/
	}
	
	// Returns a time it takes for projectile to reach the Y distance
    public static double solveQuadratic (double a, double b, double c) {
	
		double d, x1, x2;
		
		if (b*b - 4*a*c < 0) {
		    return 0;
		}
		d = Math.sqrt(b*b - 4*a*c);
		x1 = (-b + d) / 2*a;
		x2 = (-b - d) / 2*a;
		
		return Math.max(x1, x2);
    }
	
	public void printTargetPositions() {
		// Prints out the position of the Targets
		for (int i = 0; i < targetCount; i++) {
			System.out.printf("Target #%d is at %d, %d\t", i+1, targets[i].position.x, targets[i].position.y);
		}
	}
	
	public void printProjectilePosition() {
		// Prints out the position of the Projectile
		System.out.printf("Your projectile is at %d, %d \n\n", projectile.position.x, projectile.position.y);
	}
	
	public static void drawWorld() {
		
	}
	
	/*
	public World(int anObstructCount, int[] obstructionLocations) {
		gravity = 9.81;
		obstructionCount = anObstructionCount;
		Projectile projectile = new Projectile();
		Obstruction[] obstructions = new Obstruction[obstructionCount];
	}
	*/
	
}