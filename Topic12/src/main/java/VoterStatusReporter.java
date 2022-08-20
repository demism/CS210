
import java.util.Scanner;
import java.util.InputMismatchException;

/**
 * Program to get the amount voter registrations in particular states
 * @author Demis Mota
 * @version 1.0
 */
public class VoterStatusReporter {
    public enum RatingType { POOR, AVERAGE, ABOVE_AVERAGE, HIGH };
    /**
     * Returns status of registered voters in state
     * @param percentRegistered
     * @return RatingType
     */
    public static RatingType determineRegistrationStatus(int percentRegistered){
        
        if ( percentRegistered < 55)
            return RatingType.POOR;
        else if ( percentRegistered >= 55 && percentRegistered <= 63)
            return RatingType.AVERAGE;
        else if ( percentRegistered >= 64 && percentRegistered < 70)
            return RatingType.ABOVE_AVERAGE;
        else
            return RatingType.HIGH;
    }
    /**
     * Prompts user to enter state, population, and voter numbers for each party
     * then displays results
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        VoterStatistics voterStats = new VoterStatistics();
        String CHECK = "unknown";
        boolean loop = false;
        Scanner input = new Scanner(System.in);
        
        do {
            System.out.println("Enter the state name:");
            try {
            voterStats.setStateName(input.nextLine());
            }
            catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
                System.out.println("State's name not set");
                System.out.println();
            }
            
        } while ( voterStats.getStateName().equals(CHECK) );
        
        do {
            System.out.println("Enter " + voterStats.getStateName() + " state population:");
            try {
                voterStats.setPopulation(input.nextInt());
            }
            catch (InputMismatchException e) {
                System.out.println("ERROR: Invalid non-integer value entered");
                input.nextLine();
                loop = true;
            }
        }while (loop);
        
        voterStats.updateRegistrations(input);
        
        System.out.println();
        try {
            int percentRegistered = voterStats.calcPercentRegistered();
            System.out.printf("In %s, %d%% of the eligible voters are registered to vote\n",voterStats.getStateName(),percentRegistered);
            System.out.println(voterStats.getStateName() + " voter registration is " + determineRegistrationStatus(percentRegistered) + " compared to other states");
        }
        catch (ArithmeticException e){
            System.out.println("ERROR: " + e.getMessage());
            System.out.println("Program cannot continue -- Exiting");
        }
        catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
        
    }
    
}
