import java.util.ArrayList;
import java.awt.*;
import java.awt.image.*;
import javax.swing.*;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Animator extends JPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Projectile projectile;
	/*
	private int projX;
	private int projY;
	private int projW;
	private int projH;	
	*/
	private BufferedImage bgImage;
	private BufferedImage projImage;
	
	 private ArrayList<Target> targets = new ArrayList<Target>();
	 private ArrayList<Obstruction> obstructions = new ArrayList<Obstruction>();
	
	
	
	public static void printProgress(int x, int y) {
		System.out.printf("Projectile is at %d, %d.\n", x, y);
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		g.drawImage(bgImage, 0, 0, null);
		g.setColor(new Color (0,0,0));
		
		g.drawImage(projImage, projectile.getPosition().x, projectile.getPosition().y, null);
		g.setColor(new Color(255,0,0));
		for(Obstruction o : obstructions){
			g.fillRect((int) o.getPosition().getX(), (int) o.getPosition().getY(), o.getWidth(), o.getHeight());
		}
		
		g.setColor(new Color(0,255,0));
				
		for(Target t : targets){
			g.fillRect((int) t.getPosition().getX(), (int) t.getPosition().getY(), t.getWidth(), t.getHeight());
		}
	}
	
	/*
	@Override
	public void paintComponent(Graphics g){
		
		super.paintComponent(g);
				
		g.drawImage(bgImage, 0, 0, null);
		g.setColor(new Color (0,0,0));
		
		g.drawImage(projImage, this.projX, this.projY, this.projW, this.projH, null);
		//g.fillOval(this.projX, this.projY, this.projW, this.projH);
		
		g.setColor(new Color(255,0,0));
		for(Obstruction o : obstructions){
			g.fillRect((int) o.getPosition().getX(), (int) o.getPosition().getY(), o.getWidth(), o.getHeight());
		}
		
		g.setColor(new Color(0,255,0));
				
		for(Target t : targets){
			g.fillRect((int) t.getPosition().getX(), (int) t.getPosition().getY(), t.getWidth(), t.getHeight());
		}
	}
	
	public void setProjSize(int W, int H){
		this.projW = W;
		this.projH = H;
	}
	
	public void addTarget(Target t){
		this.targets.add(t);
	}
	
	public void addObstruction(Obstruction o){
		this.obstructions.add(o);
	}
	
	public void updateProjPos(int X, int Y){
		this.projX = X;
		this.projY = Y;
		this.repaint();
	}
	*/
	
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
		
		try {                
			projImage = ImageIO.read(new File("img/LolGuy.png"));
		} catch (IOException ex) {
	        // handle exception...
		}
	}
	
	/*
	public Animator(int pX, int pY, int pW, int pH){
		this.projX = pX;
		this.projY = pY;
		this.projW = pW;
		this.projH = pH;
		
		try {                
			bgImage = ImageIO.read(new File("img/bg.jpg"));
		} catch (IOException ex) {
	        // handle exception...
		}
		
		try {                
			projImage = ImageIO.read(new File("img/LolGuy.png"));
		} catch (IOException ex) {
	        // handle exception...
		}
	}
	*/
}