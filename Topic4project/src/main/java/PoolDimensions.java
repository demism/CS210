
import java.util.Scanner;

/*
    Program Description: a program that calculates pool dimensions for various hotels
    in various locations for a particular hotel chain.
    Calculates Area in ft_squared and meters_squared
    Calculate Perimeter in ft and meters
    Calculates total amount of gallons needed to fill the pool
 */

/**
 *
 * @author Demis Mota
 * @date 30JAN2022
 */
public class PoolDimensions {
    public static void main(String[] args) {
        // Constants
        final double RECT_WIDTH_MULTIPLIER = 2.2;
        final double METERS_IN_FT = 0.3048;
        final double SQ_FEET_IN_SQ_METER = 10.7639;
        final double POOL_DEPTH = 6.0;
        final double GALS_IN_CUBIC_FT = 7.48;
        
        // Variables
        char hotelDistrict;
        String hotelCity = null;
        String hotelState = null;       
        double hexagonSide = 0.0;
        double hexagonArea1 = 0.0;
        double hexagonArea2 = 0.0;
        double rectArea = 0.0;
        double totalPoolArea = 0.0;
        double totalPoolAreaSqM = 0.0;
        double totalPoolPeriFeet = 0.0;
        double totalPoolPeriMeters = 0.0;
        double totalPoolVolume = 0.0;
        
        // Get user inputs
        Scanner input = new Scanner(System.in);
        System.out.println("Program to calculate pool dimensions");
        System.out.println();
        System.out.println("Enter hotel district:");
        hotelDistrict = input.next().charAt(0);
        input.nextLine();
        System.out.println("Enter hotel city:");
        hotelCity = input.nextLine();
        System.out.println("Enter hotel state:");
        hotelState = input.nextLine();
        System.out.println("Enter hexagon side length in feet:");
        hexagonSide = input.nextDouble();
        
        System.out.println("\n");
        System.out.println("Calculations for hotel pool in");
        System.out.printf("%28s %s\n", "District",hotelDistrict);
        System.out.printf("%30s\n",hotelCity);
        System.out.printf("%30s\n",hotelState);
         
        rectArea = (RECT_WIDTH_MULTIPLIER * hexagonSide) * hexagonSide;
        hexagonArea1 = (6 * Math.pow(hexagonSide, 2)) / (4 * Math.tan(Math.PI/6));
        hexagonArea2 = (6 * Math.pow(hexagonSide, 2)) / (4 * Math.tan(Math.PI/6));
        
        System.out.println();
        System.out.printf("%-15s %14.3f\n","Rectangle area",rectArea);
        System.out.printf("%-15s %14.3f\n","Hexagon 1 area",hexagonArea1);
        System.out.printf("%-15s %14.3f\n","Hexagon 2 area",hexagonArea2);
        
        totalPoolArea = rectArea + hexagonArea1 + hexagonArea2;
        totalPoolAreaSqM = totalPoolArea / SQ_FEET_IN_SQ_METER;
        
        System.out.printf("%30s\n","--------");
        System.out.printf("%-15s %14.3f sq ft\n","Total pool area",totalPoolArea);
        System.out.printf("%-15s %14.3f sq meters\n","and in metric",totalPoolAreaSqM);
        
        totalPoolPeriFeet = (hexagonSide * 5) + (hexagonSide * 5) + (2 * hexagonSide * RECT_WIDTH_MULTIPLIER);
        totalPoolPeriMeters = totalPoolPeriFeet * METERS_IN_FT;
        System.out.println();
        System.out.printf("%-21s %8.1f ft\n","Pool perimeter length",totalPoolPeriFeet);
        System.out.printf("%-21s %8.1f meters\n","and in metric",totalPoolPeriMeters);
        
        System.out.println();
        totalPoolVolume = totalPoolArea * POOL_DEPTH * GALS_IN_CUBIC_FT;
        System.out.printf("%.0f gallons of water will be needed to fill the pool\n", totalPoolVolume);
    }   
}
