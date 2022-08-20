
import java.util.Scanner;

/**
 *
 * @author Demis Mota
 */
public class TrainingManualCostCalculator {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
      // required constants   
      final char US_CUR = '$';
      final double PER_PAGE_COLOR = 0.06;
      final double PER_PAGE_BW = 0.04;
      final double COST_PER_OUNCE = 0.21;
      final double COST_FIRST_OUNCE = 0.98;
      
      // FIXME: Define additional required constants here      
      
      int colorPageCount;
      int bwPageCount;
      int totalPages;
      double coverCost;
      double budget;
      double colorPrintingCost;
      double bwPrintingCost;
      double costToPrint;
      double totalWeight;
      double costToShip;
      double costPerManual;
      double totalManuals;
      
      // FIXME: Define additional needed variables here 
      Scanner scnr = new Scanner(System.in);
      
      System.out.println("Program to calculate training manual costs");
      System.out.println();
      
      // FIXME (1a): Prompt for and read input page counts 
      System.out.println("Enter the number of black and white pages:");
      bwPageCount = scnr.nextInt();
      System.out.println("Enter the number of color pages:");
      colorPageCount = scnr.nextInt();
       
      System.out.println();
      // FIXME (1b): Calculate and output total pages
      totalPages = colorPageCount + bwPageCount;
      
      System.out.println("There are " + totalPages + " pages in the training manual");     // FIXME (1b): Finish the output statement
      System.out.println();
      
      // FIXME (2a): Prompt for and read input cover cost and budget amount
      System.out.println("Enter the cover cost:");
      coverCost = scnr.nextDouble();
      System.out.println("Enter the budget for training manuals:");
      budget = scnr.nextDouble();
      System.out.println();
      

      // FIXME (2b): Calculate and output the printing costs
      colorPrintingCost = (PER_PAGE_COLOR * colorPageCount);
      bwPrintingCost = (PER_PAGE_BW * bwPageCount);
      costToPrint = coverCost + colorPrintingCost + bwPrintingCost;
      System.out.println("For one manual:");
      System.out.println("    Color printing cost is " +  colorPrintingCost);
      System.out.println("    Black & white printing cost is " + bwPrintingCost);
      System.out.println("Total printing cost with cover is " + costToPrint + " per manual");
      System.out.println();
      // FIXME (3): Calculate and output the weight and mailing cost
      totalWeight = totalPages / 6.1 + 2.0;
      totalWeight = Math.ceil(totalWeight);
      costToShip = (totalWeight - 1.0) * COST_PER_OUNCE + COST_FIRST_OUNCE;
      
      System.out.println("Each manual weighs " + totalWeight + " ounces and will cost " + costToShip + " to mail");
      System.out.println();
      // FIXME (4): Calculate and output cost per manual, using constant character in output
      costPerManual = costToPrint + costToShip;
      System.out.println("Cost per manual (printing and mailing) is " + US_CUR + costPerManual);
      // FIXME (5): Calculate and output number of manuals within budget 
      totalManuals = Math.floor(budget / costPerManual);
      System.out.println("Your budget will cover the costs of printing and mailing " + totalManuals + " manuals");
      return;
   }
    
}
