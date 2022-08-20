
import java.util.Scanner;

/**
 * Program Description: Approximates the Value of Pi
 * @author Demis Mota
 * @version 1.0
 */
public class SlowPiApproximator {
    public static void main(String[] args) {
        Scanner getInput = new Scanner(System.in);
        int numTerms;
        int totalCalculations = 0;
        double piApproximation;
        char runMe = 'n';
        
        System.out.println("Pi Approximation Program");
        do {
            System.out.println();
            numTerms = getUserInput(getInput,"How many terms should be calculated?");
            System.out.println();
            do
            {
                totalCalculations = getUserInput(getInput, "Display Pi approximation after every how many calculations?");
                if (totalCalculations > numTerms)
                    System.out.println("ERROR! Display value cannot be larger than number of terms to calculate. Please try again.");
            }while (totalCalculations > numTerms);
            System.out.println();
            if (numTerms > 1 && totalCalculations > 1)
                System.out.println(numTerms + " terms will be calculated, displayed every " + totalCalculations + " calculations");
            else if ( numTerms > 1 )
                System.out.println(numTerms + " terms will be calculated, displayed every " + totalCalculations + " calculation");
            else
                System.out.println(numTerms + " term will be calculated, displayed every " + totalCalculations + " calculation");

            System.out.println();
            System.out.println("PI APPROXIMATION RESULTS");
            piApproximation = calcApproxPi(numTerms,totalCalculations);
            System.out.println();
            System.out.printf("Final Pi at term %d = %.9f\n", numTerms, piApproximation);
            System.out.println();
            System.out.println("Run again (y/n)?");
            runMe = getInput.next().charAt(0);
            
        }while ( runMe == 'y' || runMe == 'Y');
    }
    
    public static int getUserInput(Scanner userInput, String prompt)
    {
        /**
         * getUserInput returns a valid user Input to a prompt
         * @param Scanner, String
         * @return int
         */
        int value;
        do {
            System.out.println(prompt);
            value = userInput.nextInt();
            if ( value <= 0 )
                System.out.println("ERROR! Input must be more than 0. Please try again.");
        } while (value <= 0 );
        
        return value;
    }
    
    public static double calcApproxPi(int numTerms, int numCalcs)
    {
        /**
         * calcApproxPi calculates pi multiple times dependent on number of terms and calculations
         * @param int, int
         * @return double
         */
        double piApprox = 0.0;
        int count = numCalcs;
        do{
            piApprox = 0.0;
            for (int i=0; i < count; i++)
            {
                piApprox += Math.pow(-1, i)*4/(i*2+1);
            }
            System.out.printf("At term %d: Pi = %.9f\n", count, piApprox);   
            count += numCalcs;
        }while (count <= numTerms);
        if (count > numTerms)
            count = numTerms;
        piApprox = 0;
        for (int i=0; i < count; i++)
        {
            piApprox += Math.pow(-1, i)*4/(i*2+1);
        }
        return piApprox;
    }
}
