package org.rage.loansales.service;

import java.util.List;

import org.rage.loadsales.model.LoanSales;
import org.rage.loadsales.model.ServiceResponse;

/**
 * Service that handle all
 * */
public interface LoanSalesService {

	ServiceResponse updateLoanSales(LoanSales loanSales);
	ServiceResponse saveLoanSales(LoanSales loanSales);
	LoanSales getLoanSales(String id);
	ServiceResponse deleteLoanSales(String id);
	List<LoanSales> getAllLoanSales();
	List<LoanSales> getAllByPersonId(String personId);
}
