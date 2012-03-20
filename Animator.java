import java.util.ArrayList;
import java.awt.*;
import java.awt.image.*;
import javax.swing.*;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Animator extends JPanel{

	private static final long serialVersionUID = 1L;
	private Projectile projectile;
	private BufferedImage bgImage;
	private ArrayList<Target> targets = new ArrayList<Target>();
	private ArrayList<Obstruction> obstructions = new ArrayList<Obstruction>();

	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		g.drawImage(bgImage, 0, 0, null);
		g.setColor(new Color (0,0,0));
		
		g.drawImage(projectile.getImage(), projectile.getPosition().x, projectile.getPosition().y, null);
		g.setColor(new Color(255,0,0));
		/*
		for(Obstruction o : obstructions){
			g.fillRect((int) o.getPosition().getX(), (int) o.getPosition().getY(), o.getWidth(), o.getHeight());
		}
		*/
		g.setColor(new Color(0,255,0));

		for(Target t : targets){
			if (projectile.gonnaHit(t) && t.isAlive()) {
				t.destroy();
				projectile.setHit(true);
			}
			if (t.isAlive()) {
				g.drawImage(t.getImage(), t.getPosition().x, t.getPosition().y, null);
			}
		}
	}

	// Constructor
	public Animator(Projectile p, ArrayList<Obstruction> o, ArrayList<Target> t) {
		projectile = p;
		obstructions = o;
		targets = t;
		try {                
			bgImage = ImageIO.read(new File("img/bg.jpg"));
		} catch (IOException ex) {
			// handle exception...
		}
	}
}