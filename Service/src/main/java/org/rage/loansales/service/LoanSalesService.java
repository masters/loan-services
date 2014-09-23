package org.rage.loansales.service;

import java.util.List;

import org.rage.loan.model.Loan;
import org.rage.loan.model.ServiceResponse;

/**
 * Service that handle all
 * */
public interface LoanSalesService {

	ServiceResponse updateLoanSales(Loan loanSales);
	ServiceResponse saveLoanSales(Loan loanSales);
	Loan getLoanSales(String id);
	ServiceResponse deleteLoanSales(String id);
	List<Loan> getAllLoanSales();
	List<Loan> getAllByPersonId(String personId);
}
