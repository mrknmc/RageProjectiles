public class Velocity {
	private double magnitude;
	private double angle;
	
	public Velocity(double a, double m) {
		angle = a;
		magnitude = m;
	}
	
	public double getXComponent() {
		double rad = Math.toRadians(angle);
		return Math.cos(rad) * magnitude;
	}
	
	public double getYComponent() {
		double rad = Math.toRadians(angle);
		return Math.sin(rad) * magnitude;
	}
	
	public double getMagnitude() {
		return magnitude;
	}
	
	public double getAngle() {
		return angle;
	}
	
	public void setMagnitude(double m) {
		magnitude = m;
	}
	
	public void setAngle(double a) {
		angle = a;
	}
	
}