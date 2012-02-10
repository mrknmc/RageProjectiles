import java.util.Scanner;
import java.util.Random;

public class GameHandler {
    
    public static void main(String[] args) {
	Scanner input = new Scanner( System.in);
	int x = 1;
	int y = 0;
	Random generator = new Random();
	double xDiff = (generator.nextDouble()) * 50 + 100;
	double yDiff = (generator.nextDouble()) * 50 + 100;
	while (x <= 3 && y == 0) {
	    System.out.println ("Guess: " + x);
	    System.out.print ("Angle: ");
	    double angle = input.nextDouble();
	    System.out.print ("Speed: ");
	    double speed = input.nextDouble();
	    /*System.out.print ("X Distance: ");
	    double xDiff = input.nextDouble();
	    System.out.print ("Y Distance: ");
	    double yDiff = input.nextDouble();*/
	     y = AngryBirds.calculate(angle,speed,xDiff,yDiff);
	    x++;
	    
	}
    }
}
