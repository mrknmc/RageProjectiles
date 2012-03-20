import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.util.Scanner;
import java.util.ArrayList;

public class World extends JFrame{

	private static final long serialVersionUID = 1L;
	double gravity = 540;	 													// Gravity constant, acceleration in px/s^2; 
	int obstructionCount;														// Number of obstructions
	static int targetCount;														// Number of targets
	private ArrayList<Target> targets = new ArrayList<Target>();				// Target array containing targets
	private ArrayList<Obstruction> obstructions = new ArrayList<Obstruction>();	// Obstruction array containing obstructions
	Projectile projectile;														// Projectile
	Timer timer;
	Animator animator;															// Animator that will animate the GUI
	int animSpeed = 17;															// Speed of the animation in ms. 25 FPS - 40ms 60FPS - 16.67ms
	int pause = 10;																// Delay of the start of animation
	double dt = 0.017;																// Time elapsed (initialised to zero)

	// Constructs a new world with the given parameters	
	public World(int anObstructionCount, int aTargetCount) {
		obstructionCount = anObstructionCount;
		targetCount = aTargetCount;
		Point projectileOrigin = new Point(50, 550);
		projectile = new Projectile(projectileOrigin, 54, 54);
		
		// Fills the obstructions array with obstructions
		for (int i = 0; i < obstructionCount; i++) {
			Point obstructOrigin = new Point(600+(i*120), 557);
			Obstruction o = new Obstruction(obstructOrigin, 80, 30);
			obstructions.add(o);
		}

		// Fills the targets array with targets
		for (int i = 0; i < targetCount; i++) {
			Point targetOrigin = new Point(565+(i*120), 557);
			Target t = new Target(targetOrigin, 30, 30);
			targets.add(t);
		} 
		animator = new Animator(projectile, obstructions, targets);
		
		// GUI
		add(animator);
		setTitle("Angry Birds");
	    setSize(960, 662);
	    setLocationRelativeTo(null);
	    setDefaultCloseOperation(EXIT_ON_CLOSE);

	}
	
	// Starts the world
	public void startWorld() {
		//Scanner input = new Scanner(System.in);
		
		/*
		System.out.print("\n\n");
	    System.out.print("Angle (°): ");
	    int angle = input.nextInt();
	    System.out.print("Speed (px/s): ");
	    int projSpeed = input.nextInt();
	    */
	    boolean a = animator.havePoints(); 

		while(a == false){
			long t0 = System.currentTimeMillis();
			long t1;
			do { 
				t1 = System.currentTimeMillis();
			}
			while (t1 - t0 < 300);
			a = animator.havePoints();
		}
		


		int angle = animator.getAngle();
		int projSpeed = animator.getSpeed();
		
		System.out.print("Angle: " + angle + "; Length: " + projSpeed);
		
	    double rad = Math.toRadians(angle);
	    double xc = Math.cos(rad)*projSpeed;
	    double yc = Math.sin(rad)*projSpeed;
	    Velocity v = new Velocity(xc, yc);
	    projectile.setVelocity(v);
	    
	    // The thing that gets called when the timer updates
	    timer = new Timer(animSpeed, new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		int x = (int) (projectile.getVelocity().getXComponent() * dt);		// Calculates the x coordinate
	    		projectile.getVelocity().updateY(-gravity*dt);						// Updates the y coordinate of the velocity
	    		int y = -(int) (projectile.getVelocity().getYComponent() * dt);		// Calculates the y coordinate
    			projectile.move(x,y);
	    		if (projectile.getPosition().y >= 556 && projectile.getVelocity().getAngle() < 0) {
	    			projectile.bounce();
	    			projectile.getVelocity().updateX(0.8);
	    		}
	    		animator.repaint();
	    		 if(projectile.getPosition().x > 960){
	    			/*projectile.setPosition(new Point(50,550));
	    			projectile.setVelocity(new Velocity(0,0));
	    			//this.startWorld(); */
	    		} 
	    	}
	    });
	    
		timer.setInitialDelay(pause);
		timer.start();
		while (true) {}
	}
}