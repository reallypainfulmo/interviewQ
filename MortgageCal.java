import java.io.*;
import java.util.*;
import java.text.DecimalFormat;
class MortgageCal {
  public static void main(String[] args) {
  	Scanner s = new Scanner(System.in);
  	
  	System.out.println("Enter Loan Amount: ");
  	double loanAmount = s.nextLong();
  	System.out.println("Loan Amount: "+loanAmount);
  	
  	System.out.println("Enter Annual Interest Rate%: ");
  	double interest = (double)s.nextLong()/100;
  	System.out.println("Annual Interest Rate: "+interest);
  	
  	System.out.println("Enter Tenure in years: ");
  	int tenure = s.nextInt();
  	System.out.println("Tenure in years: "+tenure);
  	
    Mortgage m = new Mortgage(loanAmount,interest,tenure);
    System.out.println("Enter number of periods for breakdown of interest: ");
  	int period = s.nextInt();
    System.out.println("Monthly Installment :"+new DecimalFormat("#.##").format(m.calculatInstallment()));
    m.calculateMonthlyInterestPortion(period);
  }
}

class Mortgage {
  double loanAmount;
  double annualRate;
  int tenure;
  int annualpayment=12;
  double installment;
  double[] monthlyInterests = new double[tenure];
  public Mortgage (double loanAmount, double annualRate, int tenure){
    this.tenure=tenure;
    this.annualRate=annualRate;
    this.loanAmount=loanAmount;
  }
  public double calculatInstallment (){
    double monthlypayment=0;
    double monthlyRate=annualRate/annualpayment;
    double temp=Math.pow(1+monthlyRate, tenure*annualpayment);
    monthlypayment=loanAmount*monthlyRate*temp/(temp-1);
    this.installment=monthlypayment;
    return monthlypayment;
  }
  public void calculateMonthlyInterestPortion (int period) {
    if (period <0||period>tenure*annualpayment){
      System.out.println("ERROR: please enter between 1 to "+tenure*annualpayment);
      System.out.println("Exiting...");
      return;
    }
    double currentBalance = loanAmount;
    double currentInterest, currentPrincipal;
    for (int i=0; i< period; i++){
      currentInterest = currentBalance*annualRate/annualpayment;
      currentPrincipal = installment - currentInterest;
      currentBalance = currentBalance - currentPrincipal;
      System.out.println(String.format("No.%s Payment\t Principal: %s\tInterest: %s", i+1, new DecimalFormat("#.##").format(currentPrincipal),new DecimalFormat("#.##").format(currentInterest)));
    }
  }
}
