package Helper;

import java.text.DecimalFormat;
import java.util.Currency;

import org.apache.poi.ss.formula.functions.FinanceLib;

public class LoanCalc {

	private double loanAmt;
	private double interestRate;
	private double loanYears;
	private double extraPayments;
	private double numPayments;
	private double payment;
	private double totalInterest;
	private double totalPayments;
	private double monthlyRate;
	private double loanRemaining;
	private int paymentNumber;
	
	
	// not the prettiest code, but complete. 
		
	public int getPaymentNumber() {
		return paymentNumber;
	}


	public void setPaymentNumber(int paymentNumber) {
		this.paymentNumber = paymentNumber;
	}


	public double getLoanRemaining() {
		return loanRemaining;
	}


	public void setLoanRemaining(double loanRemaining) {
		this.loanRemaining = loanRemaining;
	}


	public double getTotalInterest() {
		return roundTwoDecimals(totalInterest);
	}


	public void setTotalInterest(double totalInterest) {
		this.totalInterest = totalInterest;
	}


	public double getTotalPayments() {
		return roundTwoDecimals(totalPayments);
	}


	public void setTotalPayments(double totalPayments) {
		this.totalPayments = totalPayments;
	}


	public double getMonthlyRate() {
		return monthlyRate;
	}


	public void setMonthlyRate(double monthlyRate) {
		this.monthlyRate = monthlyRate;
	}


	public LoanCalc() {
	}
	
	
	public double getLoanAmt() {
		return loanAmt;
	}


	public void setLoanAmt(double loanAmt) {
		this.loanAmt = loanAmt;
	}


	public double getInterestRate() {
		return interestRate;
	}


	public void setInterestRate(double interestRate) {
		this.interestRate = interestRate;
	}


	public double getLoanYears() {
		return loanYears;
	}


	public void setLoanYears(double loanYears) {
		this.loanYears = loanYears;
	}


	public double getExtraPayments() {
		return extraPayments;
	}


	public void setExtraPayments(double extraPayments) {
		this.extraPayments = extraPayments;
	}


	public double getNumPayments() {
		return numPayments;
	}


	public void setNumPayments(double numPayments) {
		this.numPayments = numPayments;
	}


	public double getPayment() {
		return payment;
	}


	public void setPayment(double payment) {
		this.payment = payment;
	}

	public double roundTwoDecimals(double d) {
	    DecimalFormat twoDForm = new DecimalFormat("#.00");
	    return Double.valueOf(twoDForm.format(d));
	}

	public double paymentCalc(double interestRate, double numPayments, double loanAmt) {
	payment = roundTwoDecimals(FinanceLib.pmt(monthlyRate, numPayments, loanAmt,0.0,false));
	
	
	return payment;
	}
	
	public void interestPaidCalc() {
		totalInterest = roundTwoDecimals(totalInterest)+roundTwoDecimals(loanRemaining*(this.getMonthlyRate()));
		
		}
	public void paymentsPaidCalc() {
		
		
		if(loanRemaining>= -this.paymentCalc(monthlyRate, numPayments, loanAmt) - this.getExtraPayments() + loanRemaining*this.getMonthlyRate() )
		totalPayments = roundTwoDecimals(totalPayments)- roundTwoDecimals(this.paymentCalc(monthlyRate, numPayments, loanAmt)) + roundTwoDecimals(this.getExtraPayments());
		if(roundTwoDecimals(loanRemaining) < -roundTwoDecimals(this.paymentCalc(monthlyRate, numPayments, loanAmt)) - roundTwoDecimals(this.getExtraPayments()) + roundTwoDecimals(loanRemaining*this.getMonthlyRate()) )
		totalPayments = roundTwoDecimals(totalPayments) + roundTwoDecimals(loanRemaining)+ roundTwoDecimals(loanRemaining*this.getMonthlyRate());
		}
	

	public void makePayment() {
		this.setPaymentNumber(this.getPaymentNumber()+1);
		if(loanRemaining>= -this.paymentCalc(monthlyRate, numPayments, loanAmt) - this.getExtraPayments() + loanRemaining*this.getMonthlyRate() )
		{this.interestPaidCalc();
		this.paymentsPaidCalc();
		loanRemaining = roundTwoDecimals(loanRemaining) + roundTwoDecimals(this.paymentCalc(monthlyRate, numPayments, loanAmt)) - roundTwoDecimals(this.getExtraPayments()) + roundTwoDecimals(loanRemaining*this.getMonthlyRate());  
		}
		if(loanRemaining< -this.paymentCalc(monthlyRate, numPayments, loanAmt) - this.getExtraPayments() + loanRemaining*this.getMonthlyRate() )
		{this.interestPaidCalc();
		this.paymentsPaidCalc();
		loanRemaining=0;}
		
		}
	

	
	
	
}
