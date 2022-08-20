/**
 * Savings Plan class defines a SavingsPlan type
 * used for calculating savings
 * @author Demis Mota
 */
public class SavingsPlanV2 {
    public final static int MIN_ACCT_ID = 100000;
    public final static int MAX_ACCT_ID = 999999;
    public final static double MIN_MONTH_CONTRIB = 10.00;
    public final static double MIN_INTEREST_RATE = 1.00;
    public final static double MAX_INTEREST_RATE = 30.00;
    public final static int MIN_MONTHS_SAVING = 2;
    
    private int accountNum;
    private double monthlyContrib;
    private double annualInterestRate;
    private int numMonthsSaving;
    /**
     * Default Constructor with min values as parameters
     */
    public SavingsPlanV2() {
        accountNum = MIN_ACCT_ID;
        monthlyContrib = MIN_MONTH_CONTRIB;
        annualInterestRate = MIN_INTEREST_RATE;
        numMonthsSaving = MIN_MONTHS_SAVING;
    }
    /**
     * Constructor to set parameter values;
     * @param accountNum
     * @param monthlyContrib
     * @param annualInterestRate
     * @param numMonthsSaving 
     */
    public SavingsPlanV2(int accountNum, double monthlyContrib, 
            double annualInterestRate, int numMonthsSaving ) {
        setAccountNum(accountNum);
        setMonthlyContrib(monthlyContrib);
        setAnnualInterestRate(annualInterestRate);
        setNumMonthsSaving(numMonthsSaving);
    }
    
    // Setter Methods
    /**
     * Sets the account number
     * @param accountNum 
     */
    public void setAccountNum(int accountNum) {
        if ( accountNum < MIN_ACCT_ID ) {
            System.out.println("Too few digits in account number " + accountNum + ".");
            while (accountNum < MIN_ACCT_ID) {
                if (accountNum == 0)
                    accountNum = MIN_ACCT_ID;
                else
                    accountNum = (accountNum * 10) + (accountNum % 10); 
            }
            System.out.println("    Set to the 6-digit value of " + accountNum + ".");
            this.accountNum = accountNum;
        }
        else if ( accountNum > MAX_ACCT_ID)
        {
            System.out.println("Too many digits in account number " + accountNum + ".");
            while (accountNum > MAX_ACCT_ID)
                accountNum /= 10;
            System.out.println("    Set to the 6-digit value of " + accountNum + ".");
            this.accountNum = accountNum;
        }
        else
            this.accountNum = accountNum;
    }
    /**
     * Sets the amount of monthly contribution
     * @param monthlyContrib 
     */
    public void setMonthlyContrib(double monthlyContrib) {
        if ( monthlyContrib < MIN_MONTH_CONTRIB) {
            this.monthlyContrib = MIN_MONTH_CONTRIB;
            System.out.println("Monthly contribution value is too low.");
            System.out.printf("    Set to minimum allowable value of %.2f.\n", MIN_MONTH_CONTRIB);
        }
        else
            this.monthlyContrib = monthlyContrib;
    }
    /**
     * Sets the annual interest rate of account object
     * @param annualInterestRate 
     */
    public void setAnnualInterestRate(double annualInterestRate) {
        if ( annualInterestRate < MIN_INTEREST_RATE) {
            this.annualInterestRate = MIN_INTEREST_RATE;
            System.out.println("Annual interest rate value is too low.");
            System.out.printf("    Set to minimum allowable value of %.2f%%.\n", MIN_INTEREST_RATE);
        }
        else if ( annualInterestRate > MAX_INTEREST_RATE){
            this.annualInterestRate = MAX_INTEREST_RATE;
            System.out.println("Annual interest rate value is too high.");
            System.out.printf("    Set to maximum allowable value of %.2f%%.\n", MAX_INTEREST_RATE);
        }
        else
            this.annualInterestRate = annualInterestRate;
    }
    /**
     * Sets the number of months one would have savings account for
     * @param numMonthsSaving 
     */
    public void setNumMonthsSaving(int numMonthsSaving) {
        if ( numMonthsSaving < MIN_MONTHS_SAVING){
            System.out.println("Number of months to save value is too low.");
            System.out.printf("    Set to minimum allowable value of %d months.\n",MIN_MONTHS_SAVING);
            this.numMonthsSaving = MIN_MONTHS_SAVING;
        }
        else
            this.numMonthsSaving = numMonthsSaving;
    }
    // Getter Methods
    
    /**
     * 
     * @return accountNum 
     */
    public int getAccountNum() {
        return accountNum;
    }
    /**
     * 
     * @return monthlyContrib;
     */
    public double getMonthlyContrib () {
        return monthlyContrib;
    }
    /**
     * 
     * @return annualInterestRate
     */
    public double getAnnualInterestRate () {
        return annualInterestRate;
    }
    /**
     * 
     * @return numMonthSaving
     */
    public int getNumMonthsSaving() {
        return numMonthsSaving;
    }
    /**
     * Method displays all values in an organized manner.
     */
    public void displayPlanTerms() {
        System.out.println();
        System.out.printf("Plan for account number %d has an annual interest rate of %.2f%%.\n", accountNum, annualInterestRate);
        System.out.printf("The customer will contribute $%.2f per month for %d month(s).\n",monthlyContrib, numMonthsSaving);
    }
    /**
     * Method to compute the total amount that will be accumulated in a savings plan
     * including contributions and interest earned.
     * 
     * @return totalAccumulated - amount yielded from savings plan
     */
    public double calcTotalAccumulation() {
        double factor, totalAccumulated;
        double monthlyInterestRate = annualInterestRate / 100 / 12;
        factor = Math.exp(numMonthsSaving * Math.log(1 + monthlyInterestRate));
        totalAccumulated = ( (factor - 1) * monthlyContrib) / monthlyInterestRate;
        return totalAccumulated;
    }
    /**
     * Displays total accumulation of interest and contributions
     * @return total Interest accumulated during the month
     */
    public double displaySavingsResults() {
        double totalAccumulated;
        double totalContribution = numMonthsSaving * monthlyContrib;
        System.out.println();
        totalAccumulated = calcTotalAccumulation();
        
        System.out.printf("%-23s$ %12.2f\n","Total contributions", totalContribution);
        System.out.printf("%-23s$ %12.2f\n","Total interest", totalAccumulated - totalContribution);
        System.out.printf("%-23s%s\n"," ","--------------");
        System.out.printf("%-23s$ %12.2f\n","Total accumulation",totalAccumulated);
        
        return ( totalAccumulated - totalContribution );
    }
    /**
     * Compares two SavingsPlanV2 object data fields for equality
     * @param compare
     * @return true if each data field is equal on both objects; false otherwise.
     */
    public boolean isEqualTo(SavingsPlanV2 compare){
        return ( (this.accountNum == compare.accountNum) && (this.annualInterestRate == compare.annualInterestRate) && (this.monthlyContrib == compare.monthlyContrib) && (this.numMonthsSaving == compare.numMonthsSaving) );
    }
}
