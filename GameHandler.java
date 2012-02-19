import java.util.Scanner;

public class GameHandler {
    
    public static void main(String[] args) {
		int counter;
		World world = new World(2,2);
		Scanner input = new Scanner(System.in);
		
		for (counter = 1; counter < 4; counter++) {
			world.printProjectilePosition();
			world.printTargetPositions();
			System.out.print("\n\n");
		    System.out.println("Guess: " + counter);
		    System.out.print("Angle (°): ");
		    double angle = input.nextDouble();
		    System.out.print("Speed (px/s): ");
		    double speed = input.nextDouble();    
		    world.calculate(angle,speed);    
		}
    }
		/*
		Scanner input = new Scanner(System.in);
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
			System.out.printf("X Distance: %f \n", xDiff);
//		    double xDiff = input.nextDouble();
		    System.out.printf("Y Distance: %f \n", yDiff);
//		    double yDiff = input.nextDouble();
		    y = AngryBirds.calculate(angle,speed,xDiff,yDiff);
		    x++;    
			}
    	}
		*/
}
