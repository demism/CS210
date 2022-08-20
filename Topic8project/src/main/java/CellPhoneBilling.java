/**
 *
 * @author demis
 */
import java.util.Scanner;

/**
 * Program Description: Cell Phone billing program that generates bills based
 *  on customers service plan.
 * @author Demis Mota
 * @version 1.0
 * 
 */
public class CellPhoneBilling {

    enum PlanType { SINGLE, COUPLE, FAMILY };
    
    public static void main(String[] args) {
        // Variables
    	String whichPlan;
    	char choice;
    	PlanType thePlan;
    	int numLines = 0;
    	double dataUsage;
        double billPrice;
        double overageCharge;
        Scanner userInput = new Scanner(System.in);
        
        
        System.out.println("Cell Phone Bill Generator\n");
        System.out.println("Available plans:");
        System.out.println("    S - single plan (1 line)");
        System.out.println("    C - couple plan (2 lines)");
        System.out.println("    F - family plan (2 or more lines)");
        System.out.println("Enter plan type:");
        whichPlan = userInput.next();
        choice = whichPlan.charAt(0);
        choice = Character.toUpperCase(choice);
        
        switch(choice) {
        case 'S':
        	thePlan = PlanType.SINGLE;
        	numLines = 1;
        	break;
        case 'C':
        	thePlan = PlanType.COUPLE;
        	numLines = 2;
        	break;
        case 'F':
        	thePlan = PlanType.FAMILY;
        	System.out.println("Enter number of lines on family plan:");
        	numLines = userInput.nextInt();
        	if (numLines < 2)
        	{
        		System.out.println("ERROR! Family plans require a minimum of 2 lines.");
        		System.out.println("       Therefore, your plan will include 2 lines.");
        		numLines = 2;
        	}
        	break;
        default:
        	thePlan = PlanType.SINGLE;
        	System.out.println("ERROR! Invalid choice. SINGLE plan will be used.");
                numLines = 1;
        	break;	
        }
        System.out.println("Enter data used this month (in GB):");
        dataUsage = userInput.nextDouble();
        System.out.println("\n");
        planSummary(thePlan, numLines, dataUsage);
        billPrice = determinePlanCharge(thePlan, numLines);
        dataUsage = Math.ceil(dataUsage);
        overageCharge = calcDataCharges(numLines, (int)dataUsage);
        displayBill(billPrice, overageCharge);
    }
    /* Method Description: displays which plan, number of lines and amount of data used.
        @params PlanType,int,double
        @return void
    */
    public static void planSummary(PlanType plan, int numLines, double dataUsage){
        System.out.println("PLAN/USAGE SUMMARY");
        System.out.printf("%-15s%18s\n", "Plan:", plan );
        System.out.printf("%-15s%17d\n", "Number of lines:", numLines);
        System.out.printf("%-15s%15.1f GB\n", "Data usage:", dataUsage);
    }
    
    /* Method Description: determines cost of having said plan
        @params PlanType,int
        @return double
    */
    public static double determinePlanCharge(PlanType plan, int numLines) {
        double planPrice;
        if ( plan == PlanType.SINGLE )
            planPrice = 34.75;
        else if ( plan == PlanType.COUPLE )
            planPrice = 57.75;
        else
        {
            planPrice = 59.75;
            if ( numLines >= 3)
            {
                planPrice += 24.00;
                numLines -= 3;
            }
            else
                return planPrice;
            planPrice += (numLines * 19.00);
            
        }
        return planPrice;
    }
    
    /* Method Description: returns dataUsage cost if overage occurs or $0.00 if
        no overages were incurred.
        @params int,int
        @return double
    */
    public static double calcDataCharges(int numLines, int dataUsage ) {
        
        final double MAX_DATA_PER_LINE = 2.0;
        final double OVERAGE_COST_PER_GIG = 8.25;
        
        double cost = 0.0;
        
        if (dataUsage > MAX_DATA_PER_LINE * numLines)
        {
            cost = ( dataUsage - (MAX_DATA_PER_LINE * numLines) ) * OVERAGE_COST_PER_GIG;
        }
        return cost; 
    }
    
    /* Method Description: displays users bill
        @params double, double
    */
    public static void displayBill(double planRate, double overageCharge) {
        System.out.println("\nCHARGES");
        System.out.printf("%-15s%18.2f\n","Plan rate",planRate);
        if (overageCharge > 0.0)
        {
            System.out.printf("%-23s%10.2f\n","Data overage charges",overageCharge);
            System.out.printf("%33s\n", "-------");
            System.out.printf("%-15s%18.2f\n","Total bill",planRate + overageCharge);
        }
        else
            System.out.printf("\n%33s\n","No data overage charges");
    }
}

