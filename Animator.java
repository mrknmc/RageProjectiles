import java.text.DecimalFormat;
import java.util.ArrayList;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.AffineTransform;
import java.awt.image.*;
import javax.swing.*;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Animator extends JPanel implements MouseListener, MouseMotionListener{

	private static final long serialVersionUID = 1L;
	
	// Object Attributes
	private Projectile projectile;
	private ArrayList<Target> targets = new ArrayList<Target>();
	private ArrayList<Obstruction> obstructions = new ArrayList<Obstruction>();
	private BufferedImage bgImage;	
	private Point initialPoint;
	private Point endPoint;
	private boolean havePoints = false;
	private int xdiff;
	private int ydiff;

	// Getters
	public boolean getHavePoints(){
		return havePoints;
	}
		
	public int getAngle(){
		int angle = (int) (Math.toDegrees(Math.atan((double) ydiff/xdiff)));
		if (angle < 0){
			angle = -angle;
		}
		return angle;
	}
	
	public int getSpeed(){
		double length = Math.sqrt(xdiff * xdiff + ydiff * ydiff);
		if (length > 450) {
			length = 450;
		}
		return (int)  (1.8 * length);		
	}

	// Setters
	public void setHavePoints(boolean b) {
		this.havePoints = b;
	}
	
	// Class Methods
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		
		
		// Draws background
		g.drawImage(bgImage, 0, 0, null);
		
		// Draws obstructions
		for(Obstruction o : obstructions){
			g.drawImage(o.getImage(), o.getPosition().x, o.getPosition().y, null);
		}
		
		Graphics2D g2d = (Graphics2D) g;
		AffineTransform org = g2d.getTransform();
		g2d.rotate(projectile.getRotate(),projectile.getCenter().x,projectile.getCenter().y);
		
		// Draws projectile
		g.drawImage(projectile.getImage(), projectile.getPosition().x, projectile.getPosition().y, null);
		
		g2d.setTransform(org);
		
		// Draws targets
		for(Target t : targets){
			if (t.isAlive()) {
				g.drawImage(t.getImage(), t.getPosition().x, t.getPosition().y, null);
			}
		}
		
		g.setColor(new Color(0,0,0));
		g.drawLine(initialPoint.x, initialPoint.y, endPoint.x,endPoint.y);
		DecimalFormat df = new DecimalFormat("#.##");
		g.drawString(this.getAngle() + "\u00b0 , " + df.format((((double) this.getSpeed()/900)* 100)) + "%", endPoint.x + 10, endPoint.y + 10);
		
	}
	
	// Events
	
	public boolean getAllTargetsDead(){
		for(Target t : targets){
			if (t.isAlive()) {
				return false;
			}
		}
		return true;
	}
	
	@Override
	public void mouseClicked(MouseEvent arg0) {
		//  Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		//  Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		//  Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		if(!havePoints){
		initialPoint = new Point(arg0.getX(),arg0.getY());
		}
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		if(!havePoints){
		this.havePoints = true;
		}
	}

	@Override
	public void mouseDragged(MouseEvent arg0) {
		if(!havePoints){
		endPoint = new Point(arg0.getX(),arg0.getY());
		ydiff = this.endPoint.y - this.initialPoint.y;
		xdiff = this.initialPoint.x - this.endPoint.x;
		System.out.println(endPoint);
		this.repaint();
		}
		
	}

	@Override
	public void mouseMoved(MouseEvent arg0) {
		
		
	}

	// Constructor
	public Animator(Level level) {
		projectile = level.getProjectile();
		obstructions = level.getObstructions();
		targets = level.getTargets();
		try {                
			bgImage = ImageIO.read(new File("img/bg2.jpg"));
		} catch (IOException ex) {
			// handle exception...
		}
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
		this.initialPoint = new Point(0,0);
		this.endPoint = new Point(0,0);
	}
	
}