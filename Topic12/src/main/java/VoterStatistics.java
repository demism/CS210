
import java.util.Scanner;
import java.util.InputMismatchException;
/**
 *
 * @author Demis Mota
 */
public class VoterStatistics {
    private static final int STATE_MAX_CHARS = 14;
    private static final int PERCENT_POP_ELIGIBLE = 76;
    
    private String stateName;
    private int population;
    private int numRepublicans;
    private int numDemocrats;
    private int numOther;
    
    // Default Constructor
    public VoterStatistics() {
        this.stateName = "unknown";
        this.population = 0;
        this.numRepublicans = 0;
        this.numDemocrats = 0;
        this.numOther = 0;
    }
    // Getter Methods
    public String getStateName() { 
        return stateName; 
    }

    public int getPopulation() {
        return population;
    }

    public int getNumRepublicans() {
        return numRepublicans;
    }

    public int getNumDemocrats() {
        return numDemocrats;
    }

    public int getNumOther() {
        return numOther;
    }
    
    // Setter Methods
    public void setStateName(String stateName) {
        int spaces = 0;
        int indexOfSpace = -1;
        if (stateName.length() > STATE_MAX_CHARS)
            throw new IllegalArgumentException("ERROR: State name cannot have more than " + STATE_MAX_CHARS + " characters in it");
        try {
            for (int i=0; i<stateName.length(); i++) {
                if ( !Character.isLetter(stateName.charAt(i)) && !Character.isSpaceChar(stateName.charAt(i)) )
                    throw new Exception("ERROR: Invalid character \'" + stateName.charAt(i) + "\' is not allowed!" );
                else if ( Character.isSpaceChar(stateName.charAt(i))) {
                    spaces++;
                    indexOfSpace = i;
                if (spaces > 1)
                    throw new Exception("ERROR: No more than one space is allowed!");
                }
            }
            if ( spaces == 1)
            {
                this.stateName = stateName.substring(0,1).toUpperCase() 
                        + stateName.substring(1,indexOfSpace+1).toLowerCase()
                        + stateName.substring(indexOfSpace+1, indexOfSpace+2).toUpperCase()
                        + stateName.substring(indexOfSpace+2).toLowerCase();
            }
            else
                this.stateName = stateName.substring(0,1).toUpperCase() + stateName.substring(1).toLowerCase();
            
        }catch (Exception exception) {
           System.out.println(exception.getMessage());
           System.out.println("State's name cannot be set to \"" + stateName  + "\"");
           System.out.println();
        }
        
    }

    public void setPopulation(int population) {
        this.population = population;
        System.out.println("Integer correctly entered for population");
    }

    public void setNumRepublicans(int numRepublicans) {
        this.numRepublicans = numRepublicans;
    }

    public void setNumDemocrats(int numDemocrats) {
        this.numDemocrats = numDemocrats;
    }

    public void setNumOther(int numOther) {
        this.numOther = numOther;
    }
    /**
     * Reads in number of voters for a particular party
     * @param keyboard
     * @param regType
     * @return numVoters
     */
    public int readRegistrations(Scanner keyboard, String regType) {
        int numVoters = -1;
        
        do {
            try {
            System.out.println("Enter number of " + regType + " registrations:");
            numVoters = keyboard.nextInt();
            if ( numVoters < 0 )
                System.out.println("ERROR: Cannot be negative");
            if ( numVoters > this.population )
                System.out.println("ERROR: Cannot be more than " + this.stateName + " population of " + this.population);
            }
            catch (InputMismatchException e) {
                System.out.println("ERROR: Must be an integer value");
                keyboard.nextLine();
            }
        }while(( numVoters < 0) || (numVoters > this.population ));
        
        return numVoters;
    }
    /**
     * Calls readRegistrations and sets number of voters per party
     * @param keyboard 
     */
    public void updateRegistrations(Scanner keyboard) {
       this.numRepublicans = readRegistrations(keyboard,"Republican");
       this.numDemocrats = readRegistrations(keyboard,"Democrat");
       this.numOther = readRegistrations(keyboard,"other");
    }
    /**
     * Returns the percentage of registered voters to eligible population
     * @return percentRegistered
     */
    public int calcPercentRegistered() {
        int numEligibleVoters = this.population * PERCENT_POP_ELIGIBLE / 100;
        int numRegisteredVoters = this.numDemocrats + this.numOther + this.numRepublicans;
        
        if ( numRegisteredVoters > numEligibleVoters )
            throw new RuntimeException("DATA ERROR: " + this.stateName + " registrations exceed number of eligible voters");
        else
            return (100 * numRegisteredVoters / numEligibleVoters);
    }
}
