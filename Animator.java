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
	private ArrayList<Projectile> projectiles = new ArrayList<Projectile>();
	private ArrayList<Target> targets = new ArrayList<Target>();
	private ArrayList<Obstruction> obstructions = new ArrayList<Obstruction>();
	private BufferedImage bgImage;	
	private Point initialPoint;
	private Point endPoint;
	private boolean havePoints = false;
	private int xdiff;
	private int ydiff;
	private boolean finishedGame = false;
	private boolean failedGame = false;
	private boolean nextLevel = false;

	public void setFinishedGame(boolean b) {
		finishedGame = b;
	}
	
	public void setFailedGame(boolean b) {
		failedGame = b;
	}
	
	public void setNextLevel(boolean b) {
		nextLevel = b;
	}

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
		if (length > 420) {
			length = 420;
		}
		return (int)  (2 * length);		
	}

	// Setters
	public void setHavePoints(boolean b) {
		this.havePoints = b;
	}
	
	// Set Level
	public void setLevel(Level l) {
		projectiles = l.getProjectiles();
		targets = l.getTargets();
		obstructions = l.getObstructions();
	}
	
	// Class Methods
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		// Draws background
		g.drawImage(bgImage, 0, 0, null);
		
		/*// Draws the launching line
		g.setColor(Color.WHITE);
		g.drawLine(initialPoint.x, initialPoint.y, endPoint.x,endPoint.y);
		DecimalFormat df = new DecimalFormat("#.##");
		g.setFont(new Font("Helvetica", Font.BOLD, 14));
		g.drawString(this.getAngle() + "\u00b0 , " + df.format((((double) this.getSpeed()/840)* 100)) + "%", endPoint.x + 10, endPoint.y + 10);*/
		
		// Draws obstructions
		for(Obstruction o : obstructions) {
			g.drawImage(o.getImage(), (int) o.getPosition().x, (int) o.getPosition().y, null);
		}
		
		
		
		// Draws projectile
		for(Projectile p : projectiles) {
			Graphics2D g2d = (Graphics2D) g;
			AffineTransform org = g2d.getTransform();
			// If current projectile is the active one, rotate it
			if (p.isReady()){
				g2d.rotate(p.getRotate(), p.getCenter().x,p.getCenter().y);
			}
			if (p.isAlive()) {
				g2d.drawImage(p.getImage(), (int) p.getPosition().x, (int) p.getPosition().y, null);
			}
			g2d.setTransform(org);
		}
		
		/*
		g2d.setTransform(org);
		*/
		
		// Draws targets
		for(Target t : targets) {
			if (t.isAlive()) {
				g.drawImage(t.getImage(), (int) t.getPosition().x, (int) t.getPosition().y, null);
			}
		}
		
		// Draws the launching line
		g.setColor(Color.WHITE);
		g.drawLine(initialPoint.x, initialPoint.y, endPoint.x,endPoint.y);
		DecimalFormat df = new DecimalFormat("#.##");
		g.setFont(new Font("Helvetica", Font.BOLD, 14));
		g.drawString(this.getAngle() + "\u00b0 , " + df.format((((double) this.getSpeed()/840)* 100)) + "%", endPoint.x + 10, endPoint.y + 10);
		
		// Display finished game image
		if (finishedGame) {
			BufferedImage end = null;
			try {
				end = ImageIO.read(new File("img/end.jpg"));
			} catch (IOException e) {
				e.printStackTrace();
			}
			g.drawImage(end, 0, 0, null);
		}		
		// Display failed game image
		else if (failedGame) {
			BufferedImage fail = null;
			try {
				fail = ImageIO.read(new File("img/fail.jpg"));
			} catch (IOException e) {
				e.printStackTrace();
			}
			g.drawImage(fail, 0, 0, null);
		}
		// Display next level image
		else if (nextLevel) {
			BufferedImage next = null;
			try {
				next = ImageIO.read(new File("img/next.jpg"));
			} catch (IOException e) {
				e.printStackTrace();
			}
			initialPoint = new Point(1000,700);
			endPoint = new Point(1000,700);
			g.drawImage(next, 0, 0, null);
		}
	}
	
	// Events
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
		if (!havePoints) {
			initialPoint = new Point(arg0.getX(),arg0.getY());
		}
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		if (!havePoints) {
			this.havePoints = true;
		}
	}

	@Override
	public void mouseDragged(MouseEvent arg0) {
		if (!havePoints) {
			endPoint = new Point(arg0.getX(),arg0.getY());
			ydiff = this.endPoint.y - this.initialPoint.y;
			xdiff = this.initialPoint.x - this.endPoint.x;
			//System.out.println(endPoint);
			this.repaint();
		}
		
	}

	@Override
	public void mouseMoved(MouseEvent arg0) {
		
	}
	
	// Constructor
	public Animator(Level level) {
		projectiles = level.getProjectiles();
		obstructions = level.getObstructions();
		targets = level.getTargets();
		try {                
			bgImage = ImageIO.read(new File("img/bg2.jpg"));
		} catch (IOException ex) {
			// handle exception...
		}
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
		this.initialPoint = new Point(-1,-1);
		this.endPoint = new Point(-15,-15);		// endPoint starts off the jPanel
	}
	
}