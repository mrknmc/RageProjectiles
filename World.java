import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
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
	private boolean finished = false;
	private int numberOfGoes = 0;

	// Constructs a new world with the given parameters	
	public World(int anObstructionCount, int aTargetCount) {
		obstructionCount = anObstructionCount;
		targetCount = aTargetCount;
		Point projectileOrigin = new Point(50, 550);
		projectile = new Projectile(projectileOrigin, 54, 54);				// Sets the height and width to 30px
		
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
	    boolean a = animator.getHavePoints(); 

		while(a == false){
			wait(300);
			a = animator.getHavePoints();
		}
		


		int angle = animator.getAngle();
		int projSpeed = animator.getSpeed();
		
		System.out.println("Angle: " + angle + "; Speed: " + projSpeed);
		
	    double rad = Math.toRadians(angle);
	    double xc = Math.cos(rad)*projSpeed;
	    double yc = Math.sin(rad)*projSpeed;
	    Velocity v = new Velocity(xc, yc);
	    projectile.setVelocity(v);
	    animator.setHavePoints(false);
	    
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
	    		 if( projectile.getVelocity().getYComponent() == 0){
	    			System.out.println("Finished");
	    			long t0 = System.currentTimeMillis();
	    			long t1;
	    			do { 
	    				t1 = System.currentTimeMillis();
	    			}
	    			while (t1 - t0 < 500);
	    			projectile.reset(new Point(50,550));
	    			finished = true;
	    			timer.stop();
	    			//this.startWorld(); 
	    		} 
	    	}
	    });
	    
		timer.setInitialDelay(pause);
		timer.start();
		do {
			//System.out.println("Waiting");
			wait(300);
			
		}
		while (finished == false);
		//System.out.println("Done Waiting");
		numberOfGoes += 1;
		//if (numberOfGoes < 3){
		this.startWorld();
		//}
	}
	
	public void wait(int milliseconds){
			long t0 = System.currentTimeMillis();
			long t1;
			do { 
				t1 = System.currentTimeMillis();
			}
			while (t1 - t0 < milliseconds);
	}
}