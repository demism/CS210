/**
 * Savings Plan class defines a SavingsPlan type
 * used for calculating savings
 * @author Demis Mota
 */
public class SavingsPlan {
    public final static int MIN_LOAN_ID = 100000;
    public final static double MIN_MONTH_CONTRIB = 10.00;
    public final static double MIN_INTEREST_RATE = 1.0;
    public final static int MIN_MONTHS_SAVING = 2;
    
    private int accountNum;
    private double monthlyContrib;
    private double annualInterestRate;
    private int numMonthsSaving;
    
    public SavingsPlan() {
        accountNum = MIN_LOAN_ID;
        monthlyContrib = MIN_MONTH_CONTRIB;
        annualInterestRate = MIN_INTEREST_RATE;
        numMonthsSaving = MIN_MONTHS_SAVING;
    }
    
    // Set Methods
    public void setAccountNum(int accountNum) {
        this.accountNum = accountNum;
    }
    public void setMonthlyContrib(double monthlyContrib) {
        this.monthlyContrib = monthlyContrib;
    }
    public void setAnnualInterestRate(double annualInterestRate) {
        this.annualInterestRate = annualInterestRate;
    }
    public void setNumMonthsSaving(int numMonthsSaving) {
        this.numMonthsSaving = numMonthsSaving;
    }
    // Get Methods
    public int getAccountNum() {
        return accountNum;
    }
    public double getMonthlyContrib () {
        return monthlyContrib;
    }
    public double getAnnualInterestRate () {
        return annualInterestRate;
    }
    public int getNumMonthsSaving() {
        return numMonthsSaving;
    }
    
    
    
}
