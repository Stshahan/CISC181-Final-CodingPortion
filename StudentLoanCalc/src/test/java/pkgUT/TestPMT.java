package pkgUT;

import static org.junit.Assert.*;
import org.apache.poi.ss.formula.functions.*;
import org.junit.Test;

import Helper.LoanCalc;

public class TestPMT {

	@Test
	public void test() {
		double PMT;
		double r = 0.07 / 12;
		double n = 20 * 12;
		double p = 150000;
		double f = 0;
		boolean t = false;
		PMT = Math.abs(FinanceLib.pmt(r, n, p, f, t));
		
		double PMTExpected = 1162.95;
		
		assertEquals(PMTExpected, PMT, 0.01);
		
		
		
	}
	@Test
	public void testPayment() {
		double PMT;
		double r = 0.07 / 12;
		double n = 20 * 12;
		double p = 150000;
		

		LoanCalc L = new LoanCalc();
		L.setInterestRate(0.07 / 12);
		L.setMonthlyRate(0.07 / 12);
		L.setLoanAmt(p);
		L.setNumPayments(n);
		L.setLoanRemaining(p);
		
		double PMTExpected = 1162.95;
		
		L.paymentCalc(L.getMonthlyRate(), L.getNumPayments(), L.getLoanAmt());
		PMT = -L.getPayment();
		assertEquals(PMTExpected, PMT, 0.01);
		
		
		
	}
	
	
	
	@Test
	public void testTotalInterestandTotalPayment() {
		double PMT;
		double r = 0.07 / 12;
		double n = 20 * 12;
		double p = 100000;
	

		LoanCalc L = new LoanCalc();
		L.setInterestRate(0.07 / 12);
		L.setMonthlyRate(0.07 / 12);
		L.setLoanAmt(p);
		L.setNumPayments(n);
		L.setLoanRemaining(p);
		
		double interestExpected = 86071.47;
		double totalPaymentsExpected = 186071.47;		
		
		do {
			L.makePayment();
		}while (L.getLoanRemaining()>0);
	
		assertEquals(interestExpected, L.getTotalInterest(), 0.01);
		assertEquals(totalPaymentsExpected, L.getTotalPayments(), 0.01);
	}
}

 

