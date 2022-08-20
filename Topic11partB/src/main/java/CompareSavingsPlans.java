
import java.util.Scanner;

/**
 * Program Description: Compares savings plans set up 
 * from the SavingsPlan class.
 * 
 * @author Demis Mota
 */
public class CompareSavingsPlans {
    /**
     * Sets SavingsPlan member variables from users input
     * @param keyboard, gets user input
     * @param onePlan, SavingsPlan object  
     */
    public static void readSetupValues(Scanner keyboard, SavingsPlanV2 onePlan)
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
     * Creates a SavingsPlanV2 object after getting inputs from user
     * @param keyboard gets user inputs
     * @return savingPlan - returns newly create SavingsPlanV2 object
     */
    public static SavingsPlanV2 createSavingsPlanObject(Scanner keyboard) {
        SavingsPlanV2 savingPlan;
        int accountNum, numMonthsSaving;
        double monthlyContrib, annualInterestRate;
        System.out.println("Enter account number:");
        accountNum = keyboard.nextInt();
        System.out.println("Enter amount to be saved each month:");
        monthlyContrib = keyboard.nextDouble();
        System.out.println("Enter annual interest rate (e.g. 5.5):");
        annualInterestRate = keyboard.nextDouble();
        System.out.println("Enter number of months to save:");
        numMonthsSaving = keyboard.nextInt();
        savingPlan = new SavingsPlanV2(accountNum, monthlyContrib, annualInterestRate, numMonthsSaving);
        return savingPlan;
    }
    /**
     * main function: create two savings plans and compare both and show best plan
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        SavingsPlanV2 plan1 = new SavingsPlanV2();
        SavingsPlanV2 plan2; 
        double savingsResult, savingsResult2;
        
        System.out.println("Values for first savings plan");
        readSetupValues(input, plan1);
        System.out.println();
        System.out.println("Values for second savings plan");
        
        plan2 = createSavingsPlanObject(input);
        plan2.displayPlanTerms();
        savingsResult2 = plan2.displaySavingsResults();
        
        plan1.displayPlanTerms();
        savingsResult = plan1.displaySavingsResults();
        
        System.out.println();
        if ( plan1.isEqualTo(plan2) )
            System.out.println("Same plans for same account, so cannot compare");
        else if ( savingsResult > savingsResult2 )
        {
            System.out.println("Savings plan for account " + plan1.getAccountNum() + " will pay more total interest");
            System.out.println("    than savings plan for account " + plan2.getAccountNum());
        }
        else if ( savingsResult < savingsResult2 )
        {
            System.out.println("Savings plan for account " + plan2.getAccountNum() + " will pay more total interest");
            System.out.println("    than savings plan for account " + plan1.getAccountNum());
        }
        else
            System.out.println("Both savings plans will pay the same amount of total interest");
    }
    
}
