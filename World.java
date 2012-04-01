import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import java.util.ArrayList;
import java.awt.geom.Point2D;
import java.util.concurrent.CountDownLatch;

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
	private boolean finished = false;                                           		// Keeps track of whether the current go has ended
	boolean allTargetsDead;
	private int go = 0;
	private Projectile curProjectile;
	CountDownLatch latch;
	
	// Sets a new level
	public void setLevel(Level l) {
		go = 0;
		projectiles = l.getProjectiles();
		targets = l.getTargets();
		obstructions = l.getObstructions();
		animator.setLevel(l);
	}
	
	// Starts the world
	public void startWorld() {
		// Get the current Projectile ready
		curProjectile = projectiles.get(0);
		curProjectile.setPosition(new Point2D.Double(50, 520));
		curProjectile.setReady(true);
		int projectileCount = projectiles.size();
		
		while (!allTargetsDead()) {
			System.out.println("ready1:" + curProjectile.isReady());
			process();
			if (go >= projectileCount) {
				break;
			}
			if (!allTargetsDead()) {
				process();
			}
			try {
				latch.await();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			animator.setHavePoints(false);
		}
		if (go >= projectileCount) {
			RageProjectiles.resetLevel();
		} else {
			RageProjectiles.nextLevel();
		}
		/*
		if (!allTargetsDead()) {
			System.out.println("ready2:" + curProjectile.isReady());
			process();
		} else {

		}
		*/
	}
	
	public void process() {
		if (curProjectile.isReady()) {
			launch();
		}
	    
		timer.setInitialDelay(pause);
		timer.start();
	}
	
	public void launch() {
		// Wait until the user has provided input
	    boolean a = animator.getHavePoints(); 
	    
		while(!a) {
			RageProjectiles.wait(300);
			a = animator.getHavePoints();
			System.out.println("have points:" + a);
		}
		
		int angle = animator.getAngle();
		int projSpeed = animator.getSpeed();
		
	    double rad = Math.toRadians(angle);
	    double xc = Math.cos(rad)*projSpeed;
	    double yc = Math.sin(rad)*projSpeed;
	    Velocity v = new Velocity(xc, yc);
	    curProjectile.setVelocity(v);
		curProjectile.setLaunched(true);
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
		obstructions = level.getObstructions();
		targets = level.getTargets();
		
		latch = new CountDownLatch(1);
		
	    timer = new Timer(animSpeed, new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		System.out.println("In da timer");
	    		// Projectile is launched and ready to hit targets
	    		if (curProjectile.isReady()) {
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
	    				RageProjectiles.wait(1000);
	    				go++;
	    				curProjectile.destroy();
	    				if (go < projectiles.size()) {
	    					curProjectile = projectiles.get(go);
	    					//curProjectile.reset();
	    					System.out.println("Finished");
	    					finished = true;
	    				} else {
	    					timer.stop();
	    					latch.countDown();
	    				}
	    			}
	    		}
	    		// Projectile is getting ready for launch
	    		else {
	    			System.out.println(curProjectile.getBounceCount());
	    			// Projectile is set
	    			if (curProjectile.getBounceCount() > 0 && curProjectile.getPosition().y < 520) {
	    				curProjectile.resetVelocity();
	    				curProjectile.setReady(true);
	    				System.out.println(curProjectile.getVelocity().getYComponent());
	    				timer.stop();
	    				latch.countDown();
	    			}
	    			// Projectile is falling
	    			else {
	    				curProjectile.getVelocity().updateY(-gravity*dt);
		    			double y = - curProjectile.getVelocity().getYComponent() * dt;
		    			curProjectile.move(0,y);
	    			}
	    			
	    			// Projectile is bouncing to be set
	    			if (curProjectile.getPosition().y >= 557 && curProjectile.getVelocity().getYComponent() < 0) { 
	    				curProjectile.bounce(0.6);
	    			}
	    		}
    			System.out.println("Velocity x: " + curProjectile.getVelocity().getXComponent() + ", y: " + curProjectile.getVelocity().getYComponent());
 				System.out.println("Position x: " + curProjectile.getPosition().x + ", y: " + curProjectile.getPosition().y);
	    		// Redraw screen
	    		animator.repaint();
	    	}
	    });
		
		// GUI
		add(animator);
		setTitle("Rage Projectiles");
	    setSize(960, 662);
	    setResizable(false);
	    setLocationRelativeTo(null);
	    setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
}
