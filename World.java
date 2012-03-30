import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.swing.*;
import java.io.File;
import java.util.ArrayList;

public class World extends JFrame {

	private static final long serialVersionUID = 1L;
	private final double gravity = 600;	 												// Gravity constant, acceleration in px/s^2; 
	private ArrayList<Obstruction> obstructions = new ArrayList<Obstruction>();			// Obstruction array containing obstructions
	private ArrayList<Target> targets = new ArrayList<Target>();
	private Projectile projectile;														// Projectile
	private Timer timer;
	private Animator animator;															// Animator that will animate the GUI
	private final int animSpeed = 17;													// Speed of the animation in ms. 25 FPS - 40ms 60FPS - 16.67ms
	private final int pause = 10;														// Delay of the start of animation
	private final double dt = 0.017;													// Time elapsed (initialised to zero)
	private boolean finished = false;                                           		// Keeps track of whether the current go has ended
	boolean allTargetsDead = true;

	
	// Starts the world
	public void startWorld() {
		// Wait until the user has provided input
		animator.setHavePoints(false);
	    boolean a = animator.getHavePoints(); 
		while(a == false){
			GameHandler.wait(300);
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
	   
	    
	    // The thing that gets called when the timer updates
	    timer = new Timer(animSpeed, new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		
	    		// Movement
	    		double x = projectile.getVelocity().getXComponent() * dt;		// Calculates the x coordinate
	    		projectile.getVelocity().updateY(-gravity*dt);					// Updates the y coordinate of the velocity
	    		double y = - projectile.getVelocity().getYComponent() * dt;		// Calculates the y coordinate
    			projectile.move(x,y);
    			
    			
    			// Bouncing, negative YComponent should take care of left bouncing
	    		if (projectile.getPosition().y >= 557 && projectile.getVelocity().getYComponent() < 0) { 
	    			projectile.bounce();
	    			//projectile.setRotate(-0.025);
	    		}
	    		
	    		// Determining obstruction hit
	    		for (Obstruction o : obstructions) {
	    			projectile.gonnaHitObstruction(o);
	    		}
	    		
	    		// Determining target hit
	    		for (Target t : targets) {
	    			projectile.gonnaHitTarget(t);
	    		}
	    		
	    		// End current go (SHOULD INCORPORATE XCOMPONENT HERE)
	    		 if (projectile.getVelocity().getYComponent() == 0 && projectile.getBounceCount() > 11) {
	    			GameHandler.wait(1000);
	    			projectile.reset();
	    			System.out.println("Finished");
	    			finished = true;
	    			                                              // Conditions for ending game	 
	    			timer.stop();
	    		}
	    		 
	    		 allTargetsDead = animator.getAllTargetsDead();
 				//System.out.println(allTargetsDead );
 				projectile.rotate();
	    		// Redraw screen
	    		animator.repaint();
	    	}
	    });
	    
		timer.setInitialDelay(pause);
		timer.start();
		
		while (finished == false){
			GameHandler.wait(300);                                                              // Wait for current go to end
		}
		
		if (!allTargetsDead) {
			this.startWorld();
		} else {
			GameHandler.nextLevel();
		}

		
		//this.startWorld();

		/*boolean allTargetsDead = true;                                              // Conditions for ending game
		for (Target t : targets){
			allTargetsDead = allTargetsDead && !t.isAlive();
			System.out.println(allTargetsDead + " && " + !t.isAlive());
		}
		
		if (!allTargetsDead){
			this.startWorld();
		}*/
		
		 
	}
	
		
	// Constructor	
	public World(Level level) {
		
		animator = new Animator(level);	
		projectile = level.getProjectile();
		obstructions = level.getObstructions();
		targets = level.getTargets();
		
		// GUI
		add(animator);
		setTitle("Angry Birds");
	    setSize(960, 662);
	    setLocationRelativeTo(null);
	    setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Music
	    try {
		playAudio();
	    } catch (Exception e) {
	    	
	    }
	}
	
	public static void playAudio() throws Exception {
		AudioInputStream stream = AudioSystem.getAudioInputStream(new File("trololo.wav"));
		
		AudioFormat format = stream.getFormat();
	    if (format.getEncoding() != AudioFormat.Encoding.PCM_SIGNED) {
	      format = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED, format
	          .getSampleRate(), format.getSampleSizeInBits() * 2, format
	          .getChannels(), format.getFrameSize() * 2, format.getFrameRate(),
	          true); // big endian
	      stream = AudioSystem.getAudioInputStream(format, stream);
	    }

	    DataLine.Info info = new DataLine.Info(Clip.class, stream.getFormat(),
	        ((int) stream.getFrameLength() * format.getFrameSize()));
	    Clip clip = (Clip) AudioSystem.getLine(info);

	    clip.open(stream);

	    clip.loop(Clip.LOOP_CONTINUOUSLY);
	}

}
