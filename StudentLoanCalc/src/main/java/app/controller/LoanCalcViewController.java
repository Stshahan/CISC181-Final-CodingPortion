package app.controller;

import app.StudentCalc;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import java.net.URL;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.ResourceBundle;

import Helper.LoanCalc;
import javafx.fxml.Initializable;
import javafx.event.ActionEvent;
import javafx.scene.control.DatePicker;

public class LoanCalcViewController implements Initializable   {

	private StudentCalc SC = null;
	
	@FXML
	private TextField LoanAmount;

	
	@FXML
	private Label lblTotalPayemnts;
	
	@FXML
	private DatePicker PaymentStartDate;
	
	@FXML
	private TextField TotalPayments;
	
	@FXML
	private TextField TotalInterest;
	
	@FXML
	private TextField AdditionalPayments;
	
	@FXML
	private TextField InterestRate;
	
	@FXML
	private TextField NbrOfYears;
	
	@FXML
	private TextField NbrOfPayments;
	
	@FXML
	private TextField MonthlyPayment;
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
	}

	public void setMainApp(StudentCalc sc) {
		this.SC = sc;
	}
	
	/**
	 * btnCalcLoan - Fire this event when the button clicks
	 * 
	 * @version 1.0
	 * @param event
	 */

	
	
	@FXML
	private void btnCalcLoan(ActionEvent event) {

		
		double dLoanAmount = Double.parseDouble(LoanAmount.getText());
		double dAdditionalPayments =  Double.parseDouble(AdditionalPayments.getText());
		double  dInterestRate =  Double.parseDouble(InterestRate.getText());
		double  dNbrOfYears =  Double.parseDouble(NbrOfYears.getText());
		LocalDate localDate = PaymentStartDate.getValue();
		
		LoanCalc L = new LoanCalc();
		L.setExtraPayments(dAdditionalPayments);
		L.setInterestRate(dInterestRate/100.0);
		L.setNumPayments(dNbrOfYears*12.0);
		L.setLoanAmt(dLoanAmount);
		L.setLoanRemaining(dLoanAmount);
		L.setMonthlyRate((dInterestRate/12.0)/100.0);

do {
		L.makePayment();
	}while (L.getLoanRemaining()>0);

		lblTotalPayemnts.setText("123");
		TotalPayments.setText((Double.toString(L.getTotalPayments())));
		TotalInterest.setText((Double.toString(L.getTotalInterest())));
		MonthlyPayment.setText((Double.toString(-L.getPayment())));
		NbrOfPayments.setText((Double.toString(L.getNumPayments())));		
		
		System.out.println(localDate);
	}

}
