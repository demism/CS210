
import java.util.Scanner;

/**
 * Program Description: Sets up a savings plan utilizing
 * the SavingsPlan class.
 * 
 * @author Demis Mota
 */
public class PlanSetup {
    /**
     * Sets SavingsPlan member variables from users input
     * @param keyboard, gets user input
     * @param onePlan, SavingsPlan object  
     */
    public static void readSetupValues(Scanner keyboard, SavingsPlan onePlan)
    {
        System.out.println("Enter account number:");
        onePlan.setAccountNum(keyboard.nextInt());
        System.out.println("Enter amount to be saved each month:");
        onePlan.setMonthlyContrib(keyboard.nextDouble());
        System.out.println("Enter annual interest rate (e.g. 5.5):");
        onePlan.setAnnualInterestRate(keyboard.nextDouble());
        System.out.println("Enter number of months to save:");
        onePlan.setNumMonthsSaving(keyboard.nextInt());
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        SavingsPlan myPlan = new SavingsPlan();
        readSetupValues(input, myPlan);
        
        System.out.println();
        System.out.printf("Plan for account number %d has an annual interest rate of %.2f%%.\n", myPlan.getAccountNum(), myPlan.getAnnualInterestRate());
        System.out.printf("The customer will contribute $%.2f per month for %d month(s).\n",myPlan.getMonthlyContrib(),myPlan.getNumMonthsSaving());
        
        
    }
    
}
