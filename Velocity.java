public class Velocity {
	
	// Object Attributes
	private double xcomponent;
	private double ycomponent;
	private final int terminalVelocity = -400;
	
	// Getters
	public double getXComponent() {
		return xcomponent;
	}
	
	public double getYComponent() {
		return ycomponent;
	}
	
	public double getMagnitude() {
		return Math.sqrt(Math.pow(xcomponent, 2) + Math.pow(ycomponent, 2));
	}
	
	public double getAngle() {
		return Math.toDegrees(Math.atan(ycomponent/xcomponent));
	}
	
	// Setters
	public void setXComponent(double x) {
		xcomponent = x;
	}
	
	public void setYComponent(double y) {
		ycomponent = y;
	}
	
	// Class Methods
	public void updateY(double a) {
		if (ycomponent > terminalVelocity) {
			ycomponent += a;
		}
	}
	
	// Constructor
	public Velocity(double xc, double yc) {
		xcomponent = xc;
		ycomponent = yc;
	}
	
}