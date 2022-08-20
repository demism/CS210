
import java.util.Scanner;

/**
 * Player Class creates an instance of a player and manages players 
 * game turn as well as location on game board.
 * @author Demis Mota
 * @version 1.0
 */
public class Player {
    private static int nextPlayerNum = 1;
    private int playerNum;
    private String name;
    private int location;
    
    /**
     * Default Constructor
     * initializes private members
     */
    public Player() {
        playerNum = nextPlayerNum;
        nextPlayerNum++;
        name = null;
        location = 0;                
    }
    /**
     * Returns private member variable playerNum
     * @return playerNum:int
     */
    public int getPlayerNum() {
        return playerNum;
    }
    /**
     * Returns private member variable name
     * @return name:string
     */
    public String getName() {
        return name;
    }
    /**
     * Returns private member variable location
     * @return location:int
     */
    public int getLocation() {
        return location;
    }
    /**
     * Sets private member variable name
     * @param name:String
     */
    public void setName(String name) {
        this.name = name;
    }
    /**
     * Prompts user for name of player and sets name private member
     * @param input:Scanner 
     */
    public void setName(Scanner input) {
        System.out.println("First name of player " + playerNum + "?");
        this.name = input.nextLine();
    }
    
    /**
     * Simulates one turn in game of Chutes and Ladders
     * @param dieRoll
     * @param gameBoard 
     */
    public void oneTurnMove(int dieRoll, int[] gameBoard) {
       int newLocation = 0;
       int finalLocation = 0;
       
       newLocation = location + dieRoll;
       if (newLocation > gameBoard.length - 1)
           newLocation = gameBoard.length - 1;
       
       finalLocation = gameBoard[newLocation] + newLocation;
       
       System.out.printf("Player %d, %s, rolled %d, and moved from square %d to square %d\n",
               playerNum, name, dieRoll, location, newLocation);
       
       if ( gameBoard[newLocation] < 0 )
       {
           System.out.printf("  At square %d, %s hit a chute and slid back %d to square %d\n",
                   newLocation, name, -gameBoard[newLocation], finalLocation);
       } else if ( gameBoard[newLocation] > 0 )
       {
           System.out.printf("  At square %d, %s hit a ladder and climbed forward %d to square %d\n",
                   newLocation, name, gameBoard[newLocation], finalLocation);
       }
       
       location = finalLocation;
    }   
}
