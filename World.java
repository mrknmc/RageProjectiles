import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import java.util.ArrayList;
import java.awt.geom.Point2D;

public class World extends JFrame {

	private static final long serialVersionUID = 1L;
	private final double gravity = 600;	 												// Gravity constant, acceleration in px/s^2; 
	private ArrayList<Obstruction> obstructions = new ArrayList<Obstruction>();			// Obstruction array containing obstructions
	private ArrayList<Target> targets = new ArrayList<Target>();						// Target array
	private ArrayList<Projectile> projectiles = new ArrayList<Projectile>();			// Projectile array
	private Timer timer;																// Timer that repeats
	private Animator animator;															// Animator that will animate the GUI
	private final int animSpeed = 17;													// Speed of the animation in ms. 25 FPS - 40ms 60FPS - 16.67ms
	private final int pause = 10;														// Delay of the start of animation
	private final double dt = 0.017;													// Time elapsed (initialised to zero)
	//private boolean finished = false;                                           		// Keeps track of whether the current go has ended
	boolean allTargetsDead;
	private int go = 0;
	private Projectile curProjectile;
	
	// Sets a new level
	public void setLevel(Level l) {
		animator.setLevel(l);
		projectiles = l.getProjectiles();
		targets = l.getTargets();
		obstructions = l.getObstructions();
	}
	
	
	// Starts the world
	public void startWorld() {
		// Wait until the user has provided input
		animator.setHavePoints(false);
	    boolean a = animator.getHavePoints(); 
	    
		while(!a) {
			GameHandler.wait(300);
			a = animator.getHavePoints();
		}
		
		curProjectile.setLaunched(true);
		int angle = animator.getAngle();
		int projSpeed = animator.getSpeed();
		
	    double rad = Math.toRadians(angle);
	    double xc = Math.cos(rad)*projSpeed;
	    double yc = Math.sin(rad)*projSpeed;
	    Velocity v = new Velocity(xc, yc);
	    curProjectile.setVelocity(v);
	   
	    
	    // The thing that gets called when the timer updates
	    timer = new Timer(animSpeed, new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		
	    		// Projectile is launched and ready to hit targets
	    		if (curProjectile.isLaunched()) {
	    			// Movement of the projectile
	    			double x = curProjectile.getVelocity().getXComponent() * dt;		// Calculates the x coordinate
	    			curProjectile.getVelocity().updateY(-gravity*dt);					// Updates the y coordinate of the velocity
	    			double y = - curProjectile.getVelocity().getYComponent() * dt;		// Calculates the y coordinate
	    			curProjectile.move(x,y);

	    			// Bouncing, negative YComponent should take care of left bouncing
	    			if (curProjectile.getPosition().y >= 557 && curProjectile.getVelocity().getYComponent() < 0) { 
	    				curProjectile.bounce();
	    				//projectile.setRotate(-0.025);
	    			}

	    			// Determining obstruction hit
	    			for (Obstruction o : obstructions) {
	    				curProjectile.gonnaHitObstruction(o);
	    			}

	    			// Determining target hit
	    			for (Target t : targets) {
	    				curProjectile.gonnaHitTarget(t);
	    			}

	    			// End current go (SHOULD INCORPORATE XCOMPONENT HERE)
	    			if (curProjectile.getVelocity().getYComponent() == 0 && curProjectile.getBounceCount() > 11) {
	    				go++;
	    				GameHandler.wait(1000);			// This should call animator.repaint() until 1000ms passed
	    				nextProjectile();
	    				//curProjectile.reset();
	    				System.out.println("Finished");
	    				//finished = true;
	    			}

	    		}
	    		// Projectile is getting ready for launch
	    		else {
	    			// Projectile is set
	    			if (curProjectile.getBounceCount() > 0 && curProjectile.getPosition().y < 520) {
	    				curProjectile.resetVelocity();
	    				curProjectile.setBounceCount(0);
	    				System.out.println(curProjectile.getVelocity().getYComponent());
	    			}
	    			
	    			// Projectile is falling
	    			else {
	    				curProjectile.getVelocity().updateY(-gravity*dt);
	    			}
	    			
	    			double y = - curProjectile.getVelocity().getYComponent() * dt;
	    			curProjectile.move(0,y);
	    			
	    			// Projectile is bouncing to be set
	    			if (curProjectile.getPosition().y >= 557 && curProjectile.getVelocity().getYComponent() < 0) { 
	    				curProjectile.bounce(0.6);
	    			}
	    		}
    			System.out.println("dt:" + dt);
    			System.out.println("Velocity x: " + curProjectile.getVelocity().getXComponent() + "y: " + + curProjectile.getVelocity().getYComponent());
 				
	    		// Redraw screen
	    		animator.repaint();
	    	}
	    });
	    
		timer.setInitialDelay(pause);
		timer.start();
		
		
		/* Not sure what this does
		 * 
		while (finished == false) {
			GameHandler.wait(300);                                                              // Wait for current go to end
		}
		*/
		
		if (!allTargetsDead) {
			this.startWorld();
		} else {
			GameHandler.nextLevel();
		}

		
		//this.startWorld();

		/*
		 * boolean allTargetsDead = true;                                              // Conditions for ending game
		for (Target t : targets){
			allTargetsDead = allTargetsDead && !t.isAlive();
			System.out.println(allTargetsDead + " && " + !t.isAlive());
		}
		
		if (!allTargetsDead){
			this.startWorld();
		}
		*/
		
		 
	}
	
	public void nextProjectile() {
		curProjectile.destroy();
		curProjectile = projectiles.get(go);
	}
	
	public boolean allTargetsDead(){
		for (Target t : targets) {
			if (t.isAlive()) {
				return false;
			}
		}
		return true;
	}
		
	// Constructor	
	public World(Level level) {
		
		animator = new Animator(level);	
		projectiles = level.getProjectiles();
		curProjectile = level.getProjectiles().get(0);
		curProjectile.setPosition(new Point2D.Double(50, 520));
		obstructions = level.getObstructions();
		targets = level.getTargets();
		
		// GUI
		add(animator);
		setTitle("Angry Birds");
	    setSize(960, 662);
	    setResizable(false);
	    setLocationRelativeTo(null);
	    setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
}
