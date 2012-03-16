public class Velocity {
	private double magnitude;
	private double angle;
	private double xcomponent;
	private double ycomponent;
	
	public Velocity(double a, double m) {
		angle = a;
		magnitude = m;
		xcomponent = this.calcXComponent();
		ycomponent = this.calcYComponent();
	}
	
	private double calcXComponent(){
		double rad = Math.toRadians(angle);
		return Math.cos(rad) * magnitude;	
	}
	
	private double calcYComponent(){
		double rad = Math.toRadians(angle);
		return Math.sin(rad) * magnitude;
	}
	
	public double getXComponent() {
		//double rad = Math.toRadians(angle);
		//return Math.cos(rad) * magnitude;
		return xcomponent;
	}
	
	public double getYComponent() {
		//double rad = Math.toRadians(angle);
		//return Math.sin(rad) * magnitude;
		return ycomponent;
	}
	
	public double getMagnitude() {
		return magnitude;
	}
	
	public double getAngle() {
		return angle;
	}
	
	public void setMagnitude(double m) {
		magnitude = m;
		xcomponent = this.calcXComponent();
		ycomponent = this.calcYComponent();
	}
	
	public void setAngle(double a) {
		angle = a;
		xcomponent = this.calcXComponent();
		ycomponent = this.calcYComponent();
	}
	
	public void bounce(){
		ycomponent = -1 * ycomponent;
	}
	
}