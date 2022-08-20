import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;
import java.util.Scanner;

/**
 * Implementation of the Chutes and Ladders board game. Uses a separate
 * configuration file for the board size.
 * @author Demis Mota
 * @version 1.0
 */
public class ChutesAndLadders {
    static final int MAX_DIE_ROLL = 6;
    static final int MAX_TURNS = 100;
    /**
     * main() collects input from user to determine which file to use for their
     * game as well as a possible seed to create unique game experience.
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int[] gameboard = null;
        String filename;
        String logfile;
        Scanner input = new Scanner(System.in);
        Player player1 = null;
        Player player2 = null;
        int seedValue = 0;
        int turn = 1;
        Random randGen = new Random();
        boolean playerWon = false;
        
        System.out.println("Enter gameboard data input filename:");
        filename = input.nextLine();
        
        try {
            gameboard = createGameboard(filename);
            logfile = writeLogFile(filename, gameboard);
            if ( logfile != null )
                System.out.println("Log file " + logfile + " successfully created.");
            else
                System.out.println("Program will continue without logfile");
            System.out.println();
            player1 = new Player();
            player2 = new Player();
            player1.setName(input);
            player2.setName(input);
            
            System.out.println();
            System.out.println("Chutes and Ladders game for players " + player1.getName() + " and " + player2.getName());
            
            System.out.println();
            System.out.println("Enter integer seed (or 0 for none):");
            seedValue = input.nextInt();
            
            if ( seedValue == 0) {
                randGen = new Random();
                System.out.println("Random play!");
            }
            else {
                randGen = new Random(seedValue);
                System.out.println("Controlled play for testing.");
            }

            while ( !playerWon ){
                System.out.println();
                if ( turn > MAX_TURNS )
                {
                    System.out.println("NOBODY CAN WIN.  Program exiting.");
                    playerWon = true;
                }
                else 
                {
                    System.out.println("TURN " + turn + ":");
                    if (!playerWon){
                        player1.oneTurnMove(randGen.nextInt(MAX_DIE_ROLL)+1, gameboard);
                        if ( player1.getLocation() == (gameboard.length - 1) ) {
                            System.out.println();
                            System.out.println(player1.getName().toUpperCase() + " WON!");
                            playerWon = true;
                        }
                    }
                    if (!playerWon) {
                        player2.oneTurnMove(randGen.nextInt(MAX_DIE_ROLL)+1, gameboard);
                        if ( player2.getLocation() == (gameboard.length - 1) ) {
                            System.out.println();
                            System.out.println(player2.getName().toUpperCase() + " WON!");
                            playerWon = true;
                        }
                    }
                    turn++;
                }
            }
        }
        catch (IOException e) {
            System.out.println(e.getMessage());
            System.out.println("Cannot play without gameboard, so program exiting");
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
        
    }
    /**
     * Creates an integer array representation of the game board each index
     * has a value if positive its considered a ladder if negative a chute. 
     * Each index corresponds to a place on the game board as well.
     * @throws java.lang.Exception
     * @param filename
     * @return gameBoard
     */
    public static int[] createGameboard(String filename) throws Exception{
        int[] gameBoard = null;
        int boardSize = 0;
        int gbIndex = 0;
        int gbVal = 0;
        int numChutes = 0;
        int numLadders = 0;
        FileInputStream fbs = null;
        
        fbs = new FileInputStream(filename);
        Scanner inFS = new Scanner(fbs);
        
        boardSize = inFS.nextInt();
        gameBoard = new int[boardSize];
        while ( inFS.hasNext())
        {
            gbIndex = inFS.nextInt();
            gbVal = inFS.nextInt();
            if (gbVal > 0)
                numLadders++;
            else if (gbVal < 0)
                numChutes++;
            gameBoard[gbIndex] = gbVal;
        }
        fbs.close();
        System.out.println("Gameboard setup with " + (boardSize - 1) + " squares");
        System.out.println("  " + numChutes + " squares have a chute");
        System.out.println("  " + numLadders + " squares have a ladder");
        System.out.println();
        return gameBoard;
    }
    
    /**
     * Creates a separate file (logfile) that describes the board setup.
     * @param filename
     * @param gameboard
     * @return logfile
     */
    public static String writeLogFile(String filename, int[] gameboard){
        File myFile = null;
        PrintWriter pw = null;
        String logfile = null;
        try {
            logfile = filename.substring(0,filename.indexOf(".")) + "_log.txt";
            myFile = new File(logfile);
            pw = new PrintWriter(myFile);
            
            for (int i = 0; i < gameboard.length; i++){
                if (gameboard[i] < 0)
                    pw.println("Chute at square " + i);
                else if (gameboard[i] > 0)
                    pw.println("Ladder at square " + i);
            }
            pw.close();
        }
        catch (IOException e)
        {
            System.out.println("ERROR! Cannot create log file.");
            logfile = null;
        }
        return logfile;
    }
}
