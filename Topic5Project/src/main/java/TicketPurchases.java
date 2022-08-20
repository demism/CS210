
import java.util.Scanner;

/*
 * Program Description: Ticket Purchases
 * This is a simple ticket ordering system that helps a local concert
 * venue calculate the cost of their customer's ticket purchases.
 */

/**
 *
 * @author Demis Mota
 * @date 2/6/2022
 */
public class TicketPurchases {
    public static void main(String[] args) {
        // Constants
        final double CONCERT_TIX_PRICE = 44.0;
        final double RESERVED_SEATING_PRICE = 9.9;
        final double TSHIRT_COST = 22.95; // MAX 2
        final double GROUP_DISCOUNT = .08; // DISCOUNT FOR 8 or more tickets 8%
        final int MAX_SHIRTS_PER_TIX = 2;
        final int MIN_NUM_TIX_FOR_DISCOUNT = 8;
        
        // variables
        Scanner userInput = new Scanner(System.in);
        int numTix;
        int floorOrReserved;
        char wantShirt;
        int numShirts;
        double ticketCost;
        double seatCost = 0.0;
        double tshirtCost;
        double totalCost;
        double groupDiscount;
        
        // Inputs
        System.out.println("Ticket Purchase Ordering System");
        System.out.println();
        
        System.out.println("Enter number of tickets:");
        numTix = userInput.nextInt();
        System.out.println("Enter 1 for the floor or 2 for reserved seats:");
        floorOrReserved = userInput.nextInt();
        System.out.println("Do you want t-shirts (Y or N)?");
        wantShirt = userInput.next().charAt(0);
        //userInput.nextLine();
        if ( (wantShirt == 'n') || (wantShirt == 'N') )
            numShirts = 0;
        else{
            System.out.println("How many t-shirts?");
            numShirts = userInput.nextInt();
        }
        if ( numShirts  > (numTix * MAX_SHIRTS_PER_TIX) )
        {
            System.out.println("ERROR! Limit of " + MAX_SHIRTS_PER_TIX+ " t-shirts per ticket.");
            System.out.println("Program will lower number of t-shirts to " + (numTix * MAX_SHIRTS_PER_TIX) );
            numShirts = numTix * MAX_SHIRTS_PER_TIX;
        }
        
        System.out.println("\n");
        
        // Order Summary
        System.out.println("TICKET ORDER");
        System.out.printf("%-20s %9s\n", "Number of tickets: ", numTix);
        if ( floorOrReserved == 1)
            System.out.printf("%-20s %9s\n", "Ticket type:", "floor");
        else if ( floorOrReserved == 2)
            System.out.printf("%-20s %9s\n", "Ticket type:", "reserved");
        
        System.out.printf("%-20s %9s\n", "Number of t-shirts:", numShirts);
        System.out.println();
        
        // TICKET BILL
        System.out.println("TICKET BILL");

        ticketCost = numTix * CONCERT_TIX_PRICE;
        System.out.printf("%-20s %9.2f\n", "Ticket cost:", ticketCost);
        if ( floorOrReserved == 2)
        {
            seatCost = RESERVED_SEATING_PRICE * numTix;
            System.out.printf("%-20s %9.2f\n", "Seating cost:", seatCost);
        }
        tshirtCost = TSHIRT_COST * numShirts;
        System.out.printf("%-19s %-1s %8.2f\n", "T-shirt cost:", '+', tshirtCost);
        System.out.printf("%30s\n","----------");
        
        totalCost = ticketCost + tshirtCost + seatCost;
        System.out.printf("%30.2f\n",totalCost);
        
        if ( numTix >= MIN_NUM_TIX_FOR_DISCOUNT )
        {
            groupDiscount = totalCost * GROUP_DISCOUNT;
            System.out.printf("%-19s %-1s %8.2f\n", "Group discount:", '-', groupDiscount);
            System.out.printf("%30s\n","----------");
            totalCost = totalCost - groupDiscount;
            System.out.printf("%30.2f\n",totalCost);
        }
    }
}
