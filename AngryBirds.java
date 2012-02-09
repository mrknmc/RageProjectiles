public class AngryBirds {	
	
    /*	public static void main (String[] args) {
		
		double angle = Integer.parseInt(args[0]),
			speed = Double.parseDouble(args[1]),
			xDiff = Double.parseDouble(args[2]),
			yDiff = Double.parseDouble(args[3]);
	
		AngryBirds.calculate (angle, speed, xDiff, yDiff);
		} */

	public static int calculate (double angle, double speed, double xDifference, double yDifference) {
		
		double rad = Math.toRadians(angle);
		double timeX = xDifference / ( speed * Math.cos(rad) );
		String timeYs = solveQuadratic (4.905, -speed * Math.sin(rad), yDifference);
		if (timeYs == "Im"){ return 0;}
		double timeY = Double.parseDouble(timeYs);
		if ( Math.abs(timeX - timeY) <= 1 ) {
			System.out.println("It will hit the target.");
			return (1);
		} else {
			System.out.println("It won't hit the target.");
			System.out.println("timeX = " + timeX);
			System.out.println("timeY = " + timeY);
			return (0);
		}
	}
	
    public static String solveQuadratic (double a, double b, double c) {
		
		double d, x1, x2;

		if (b*b - 4*a*c < 0)
		{
		    System.out.println("Imaginary result!");
		    return "Im"; // System.exit(1);
		}
	
		d = Math.sqrt(b*b - 4*a*c);
		x1 = (-b + d) / 2*a;
		x2 = (-b - d) / 2*a;
		
		return Double.toString( Math.max(x1, x2));
    }

}