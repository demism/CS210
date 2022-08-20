
import java.util.Scanner;
/*
 * Program Description: Ticket Purchases V2
 * This is a simple ticket ordering system that helps a local concert
 * venue calculate the cost of their customer's ticket purchases.
 *
 * Update V2: Added DJ Dancing events and created new costs 
 *            for events and tickets
 *
 * @author Demis Mota
 * @date 2/13/2022
 */
public class TicketPurchasesV2 {
    /* 
        Main Function 
        @param args
    */
    public static void main(String[] args) {
        /******************************
         *  Constants
         ******************************/
        // Concert Tickets
        final double CONCERT_TIX_LT10 = 44.00; // For fewer than 10
        final double CONCERT_TIX_GE10 = 40.00; // For greater than or equal to 10
        final int MAX_CONCERT_TIX = 25;
        // DJ Dancing Tickets
        final double DANCING_TIX_LT10 = 25.00; // For less than 10
        final double DANCING_TIX_GE10 = 20.00; // For greater than or equal to 10
        // Seating Costs
        final double FLOOR_SEAT = 0.00;
        final double BLEACHER_SEAT = 4.99;
        final double RESERVED_ROW_SEAT = 9.99;
        final double RESERVED_TABLE_SEAT = 15.99;
        // T-Shirt Costs
        final double TSHIRT_COST_1to5 = 22.95; // 1-5 shirts
        final double TSHIRT_COST_6to12 = 21.25; // 6-12 shirts
        final double TSHIRT_COST_GT12 = 19.00; // 12 or more shirts
        // DISCOUNTS and LIMITS        
//        final double GROUP_DISCOUNT = .08; // DISCOUNT FOR 8 or more tickets 8%
        final int MAX_SHIRTS_PER_TIX = 2;
//        final int MIN_NUM_TIX_FOR_DISCOUNT = 8;
        
        // variables
        Scanner userInput = new Scanner(System.in);
        int tixType; // 1 for concert or 2 for DJ Dancing event
        String strTixType = "";
        String strSeatType;
        
        int numTix;
        char seatType;
        int numShirts;
        double ticketCost = 0.00;
        double seatCost = FLOOR_SEAT;
        double tshirtCost = 0.00;
        double totalCost;
    //    double groupDiscount;
        
        // Inputs
        System.out.println("Ticket Purchase Ordering System");
        System.out.println();
        
        System.out.println("Enter 1 for the concert or 2 for DJ dancing:");
        tixType = userInput.nextInt();
        if (tixType == 1)
            strTixType = "concert";
        else if ( tixType == 2)
            strTixType = "DJ dancing";
        
        System.out.println("How many " + strTixType + " tickets?");
        numTix = userInput.nextInt();
        
        if ( tixType == 1 && numTix > MAX_CONCERT_TIX )
        {
            System.out.println("ERROR! A maximum of 25 tickets may be purchased at once."); 
            System.out.println("Order will be adjusted to 25 tickets.\n");
            numTix = MAX_CONCERT_TIX;
        }
        
        System.out.println("Seating options are");
        System.out.println("  F - floor (no seats)");
        System.out.println("  B - bleachers (open seating)");
        System.out.println("  R - row seats (reserved)");
        System.out.println("  T - table seats (reserved)");
        System.out.println("Enter choice:");
        seatType = userInput.next().charAt(0);
        switch (seatType){
            case 'F':
            case 'f':
                strSeatType = "floor";
                break;
            case 'B':
            case 'b':
                strSeatType = "bleachers";
                break;
            case 'R':
            case 'r':
                strSeatType = "row seat";
                break;
            case 'T':
            case 't':
                strSeatType = "table";
                break;
            default:
                System.out.println("Invalid choice - floor will be used");
                System.out.println();
                strSeatType = "floor";
                break;
        }
        
        System.out.println("How many t-shirts?");
        numShirts = userInput.nextInt();
        if ( numShirts  > (numTix * MAX_SHIRTS_PER_TIX) )
        {
            System.out.println("ERROR! Limit of " + MAX_SHIRTS_PER_TIX + " t-shirts per ticket.");
            System.out.println("Program will lower number of t-shirts to " + (numTix * MAX_SHIRTS_PER_TIX) );
            numShirts = numTix * MAX_SHIRTS_PER_TIX;
        }
        
        System.out.println("\n");
        
        // Order Summary
        System.out.println("TICKET ORDER");
        System.out.printf("%-23s%12s\n", "Event type:", strTixType);
        System.out.printf("%-23s%12s\n", "Number of tickets:", numTix);
        System.out.printf("%-23s%12s\n", "Seating type:", strSeatType);
        if ( numShirts > 0)
            System.out.printf("%-23s%12s\n", "Number of t-shirts:", numShirts);
        System.out.println();
        
        // TICKET BILL
        System.out.println("TICKET BILL");

        if ( strTixType == "concert")
        {
            if ( numTix < 10)
                ticketCost = numTix * CONCERT_TIX_LT10;
            else 
                ticketCost = numTix * CONCERT_TIX_GE10;
        }
        else if ( strTixType == "DJ dancing")
        {
            if ( numTix < 10)
                ticketCost = numTix * DANCING_TIX_LT10;
            else 
                ticketCost = numTix * DANCING_TIX_GE10;            
        }
        System.out.printf("%-25s%10.2f\n", "Ticket cost:", ticketCost);
        if ( strSeatType != "floor" )
        {
            if (strSeatType == "bleachers")
                seatCost = BLEACHER_SEAT * numTix;
            else if (strSeatType == "row seat")
                seatCost = RESERVED_ROW_SEAT * numTix;
            else if (strSeatType == "table") 
                seatCost = RESERVED_TABLE_SEAT * numTix;
        }
        if ( strSeatType != "floor")
            System.out.printf("%-25s%10.2f\n", "Seating cost:", seatCost);
        // TSHIRT_COST_1to5 1-5 shirts
        // TSHIRT_COST_6to12 6-12 shirts
        // TSHIRT_COST_GT12 12 or more shirts
        if ( numShirts > 0 && numShirts < 6)
            tshirtCost = TSHIRT_COST_1to5 * numShirts;
        else if ( numShirts >=6 && numShirts <= 12 )
            tshirtCost = TSHIRT_COST_6to12 * numShirts;
        else if ( numShirts > 12)
            tshirtCost = TSHIRT_COST_GT12 * numShirts;
        if ( numShirts > 0 )
            System.out.printf("%-25s%10.2f\n", "T-shirt cost:", tshirtCost);
        
        if ( (strSeatType != "floor") || (numShirts > 0) )
        {
            System.out.printf("%35s\n","----------");
            totalCost = ticketCost + tshirtCost + seatCost;
            System.out.printf("%-10s %24.2f\n","Total",totalCost);
        }
        
        /*if ( numTix >= MIN_NUM_TIX_FOR_DISCOUNT )
        {
            groupDiscount = totalCost * GROUP_DISCOUNT;
            System.out.printf("%-19s %-1s %8.2f\n", "Group discount:", '-', groupDiscount);
            System.out.printf("%30s\n","----------");
            totalCost -= groupDiscount;
            System.out.printf("%30.2f\n",totalCost);
        }*/
    }
}
