
import java.util.Scanner;
/**
 * Program Description: Analyzes the run times for a track team's 400 meter run.
 * maximum number of runners is set by NUMBER_OF_RUNNERS. Each runner has a 
 * Unique ID entered by the user and a run time. Fastest run times are shown and 
 * user may search for a specific runner's ID to get runner's time.
 * @author Demis Mota
 */
public class TrackTeamAnalyzer {
    static final int INVALID_INDEX = -1;
    /**
     * Main function: 
     * Requests ID and run time from user
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        final int NUMBER_OF_RUNNERS = 12;
        final int STOPPING_ID = 9999;
        
        Scanner input = new Scanner(System.in);
        int idNum;
        double runTime;
        int count = 0;
        int getID = INVALID_INDEX;
        boolean loop = true;
        double averageRunTime = 0.0;
        
        int[] runnerID = new int[NUMBER_OF_RUNNERS];
        double[] runTimes = new double[NUMBER_OF_RUNNERS];
        
        do {
            if (count == 12) {
                System.out.println("Maximum of " + NUMBER_OF_RUNNERS + " runners can be stored. You have reached the limit.");
                loop = false;
            }
            else
            {
                System.out.println("Enter id number of runner (or " + STOPPING_ID + " to stop):");
                idNum = input.nextInt();
                if (idNum != 9999) {
                    System.out.println("Enter 400 meter run time for runner with id number " + idNum + ":");
                    runTime = input.nextDouble();
                    runnerID[count] = idNum;
                    runTimes[count++] = runTime;
                }
                else
                    loop = false;
            }
        } while ( loop && count < 13);
        
        System.out.println();
        
        System.out.println("You entered the following " + count + " runner id numbers and run times:");
        for (int i = 0; i < count; i++)
        {
            System.out.printf("%d  %.2f\n", runnerID[i], runTimes[i]);
        }
        
        System.out.println();
        
        for(int i=0;i < count; i++)
            averageRunTime += runTimes[i];
        averageRunTime /= count;
        System.out.printf("Average 400 meter run time is %.2f seconds\n", averageRunTime);
        
        getFastestRunnerIDs(runnerID, runTimes, count);
        System.out.println();
        
        loop = true;
        while( loop )
        {
            System.out.println("Enter id number of one runner to find (or " + STOPPING_ID + " to stop):");
            idNum = input.nextInt();
            if ( idNum != STOPPING_ID )
            {
                getID = findRunner(idNum, runnerID, count);
            
                if ( getID == INVALID_INDEX )
                    System.out.println("No runner with id number " + idNum);
                else
                    System.out.printf("Runner %d has a 400 meter run time of %.2f seconds\n", idNum, runTimes[getID]);
                System.out.println();
            }
            else
                loop = false;
        }
    }
    
    /*
        Method Description: Finds the fastest runner's ID and display's them.
        @param idArray, runArray, numIDs
               idArray - the Array of IDs
               runArray - the Array of run times 1-to-1 with idArray
               numIDs - the total number of IDS
    */
    public static void getFastestRunnerIDs(int[] idArray, double[] runArray, int numIDs )
    {
        double min = runArray[0]; // minimum time is fastest time
        for (int i=0; i < numIDs; i++)
            if( min > runArray[i])
                min = runArray[i];
        System.out.printf("Fastest 400 meter run time is %.2f seconds\n", min);
        System.out.println("   Id(s) of runners with fastest run time:");
        for (int i=0; i < numIDs; i++)
            if ( runArray[i] == min)
                System.out.println("   " + idArray[i]);
        
    }
    /*
        Method Description: Returns the index of the runner who's ID matches idNumer
        @param idNumber, idArray, numIDs
        @return array index of runner
        idNumber - ID to search for
        idArray - the array containing all the runner IDs
        numIDs - total number of IDs
        
    */
    public static int findRunner(int idNumber, int[] idArray, int numIDs)
    {
        int runnerIndex = INVALID_INDEX;
        
        for (int i=0; i < numIDs; i++)
        {
            if ( idNumber == idArray[i] )
                return i;
        }
        return runnerIndex;
    }
    
}
