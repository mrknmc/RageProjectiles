import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.swing.*;
import java.io.File;
import java.util.Scanner;
import java.util.ArrayList;

public class World extends JFrame{

	private static final long serialVersionUID = 1L;
	private double gravity = 540;	 													// Gravity constant, acceleration in px/s^2; 
	private int obstructionCount;														// Number of obstructions
	private static int targetCount;														// Number of targets
	private ArrayList<Target> targets = new ArrayList<Target>();				// Target array containing targets
	private ArrayList<Obstruction> obstructions = new ArrayList<Obstruction>();	// Obstruction array containing obstructions
	private Projectile projectile;														// Projectile
	private Timer timer;
	private Animator animator;															// Animator that will animate the GUI
	private int animSpeed = 17;															// Speed of the animation in ms. 25 FPS - 40ms 60FPS - 16.67ms
	private int pause = 10;																// Delay of the start of animation
	private double dt = 0.017;															// Time elapsed (initialised to zero)
	private boolean finished = false;                                           // Keeps track of whether the current go has ended

	
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
	    		int x = (int) (projectile.getVelocity().getXComponent() * dt);		// Calculates the x coordinate
	    		projectile.getVelocity().updateY(-gravity*dt);						// Updates the y coordinate of the velocity
	    		int y = -(int) (projectile.getVelocity().getYComponent() * dt);		// Calculates the y coordinate
    			projectile.move(x,y);
    			
    			// Bouncing
	    		if (projectile.getPosition().y >= 556 && projectile.getVelocity().getAngle() < 0) { 
	    			projectile.bounce();
	    			projectile.getVelocity().updateX(0.8);
	    		}

	    		// End current go
	    		 if(projectile.getVelocity().getYComponent() == 0 && projectile.getBounceCount() > 8){
	    			GameHandler.wait(1000);
	    			projectile.reset(new Point(50,550));
	    			System.out.println("Finished");
	    			finished = true;
	    			timer.stop();
	    		}
	    		 
	    		 
	    		// Redraw screen
	    		animator.repaint();
	    	}
	    });
	    
		timer.setInitialDelay(pause);
		timer.start();
		
		do {
			GameHandler.wait(300);                                                              // Wait for current go to end
		}
		while (finished == false);
		
		this.startWorld();

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
