import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.util.Scanner;

public class World extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	double gravity = -196;	 							// Gravity constant, acceleration in px/s^2; 
	int obstructionCount;								// Number of obstructions
	static int targetCount;								// Number of targets
	static Target[] targets;							// Target array containing targets
	Obstruction[] obstructions;							// Obstruction array containing obstructions
	Projectile projectile;								// Projectile
	Timer timer;
	Animator animator = new Animator(10,10,10,10);		// Animator that will animate the GUI
	int animSpeed = 40;									// Speed of the animation in ms. 25 FPS - 40ms, temporarily set to 1s for testing
	int pause = 100;									// Delay of the start of animation
	double dt = 0;										// Time elapsed (initialised to zero)
		
	
	// Constructs a new world with the given parameters	
	public World(int anObstructionCount, int aTargetCount) {
		obstructionCount = anObstructionCount;
		targetCount = aTargetCount;
		
		// Initialises new Projectile, Obstructions and Targets
		targets = new Target[targetCount];
		obstructions = new Obstruction[obstructionCount];
		Point projectileOrigin = new Point(50, 560);
		projectile = new Projectile(projectileOrigin, 30, 30, 440);
		animator.updateProjPos(50,560);
		animator.setProjSize(30,30);
		
		// Fills the obstructions array with obstructions
		for (int i = 0; i < obstructionCount; i++) {
			Point obstructOrigin = new Point(600+(i*120), 560 );
			obstructions[i] = new Obstruction(obstructOrigin, 80, 20);
			animator.addObstruction(obstructions[i]);
		}

		// Fills the targets array with targets
		for (int i = 0; i < targetCount; i++) {
			Point targetOrigin = new Point(565+(i*120), 560);
			Target t = new Target(targetOrigin, 30, 30);
			targets[i] = t;
			animator.addTarget(t);
		} 
		
		// GUI
		add(animator);
		setTitle("Angry Birds");
	    setSize(960, 640);
	    setLocationRelativeTo(null);
	    setDefaultCloseOperation(EXIT_ON_CLOSE);

	}
	
	// Starts the world
	public void startWorld() {
		Scanner input = new Scanner(System.in);
		
		System.out.print("\n\n");
	    System.out.print("Angle (°): ");
	    int angle = input.nextInt();
	    projectile.setAngle((-1) * angle); // Angle of 90 is straight down, so invert angle to make user friendly
	    System.out.print("Speed (px/s): ");
	    int projSpeed = input.nextInt();
	    projectile.setSpeed(projSpeed);
	    
	    // The thing that gets called when the timer updates
	    timer = new Timer(animSpeed, new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		dt += ((double) animSpeed/1000);							// Time passes another constant, must be converted to seconds
	    		double projSpeed = projectile.getSpeed();					// Speed of the projectile
	    		double projRad = Math.toRadians(projectile.getAngle());		// Angle of the projectile in radians
	    		int x = (int) (projSpeed * Math.cos(projRad) * dt);			// Calculates the x coordinate
	    		int y = (int) ((projSpeed * Math.sin(projRad) * dt) -
	    				(0.5 * gravity * dt * dt));							// Calculates the y coordinate
	    		
	    		projectile.move(x,y);										// Updates the position of the projectile
	    		animator.updateProjPos((int) projectile.getPosition().getX(), (int) projectile.getPosition().getY());
	    		printProjectilePosition();
	    		// Calls the animator to repaint with new coordinates
	    		if (projectile.getPosition().x > 800) {
	    			timer.stop();
	    		}
	    		
	    	}
	    });
	    
		timer.setInitialDelay(pause);
		timer.start();
		while (true) {}
	}
	
	
	// Calculates whether a projectile hits the target
	/*
	public void calculate(double anAngle, double aSpeed) {
		
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
		
	}
	*/
	
	// Returns a time it takes for projectile to reach the Y distance
	/*
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
    */
	
	// Prints the position of the target
	public void printTargetPositions() {
		// Prints out the position of the Targets
		for (int i = 0; i < targetCount; i++) {
			int x = targets[i].getPosition().x;
			int y = targets[i].getPosition().y;
			System.out.printf("Target #%d is at %d, %d\t", i+1, x, y);
		}
	}
	
	// Prints the position of the projectile
	public void printProjectilePosition() {
		// Prints out the position of the Projectile
		int x = projectile.getPosition().x;
		int y = projectile.getPosition().y;
		System.out.printf("Your projectile is at %d, %d \n\n", x, y);
	}
	

}
	
	/*
	public World(int anObstructCount, int[] obstructionLocations) {
		gravity = 9.81;
		obstructionCount = anObstructionCount;
		Projectile projectile = new Projectile();
		Obstruction[] obstructions = new Obstruction[obstructionCount];
	}
	*/