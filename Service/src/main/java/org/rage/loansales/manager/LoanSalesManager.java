package org.rage.loansales.manager;

import java.util.List;

import org.rage.loadsales.model.LoanSales;
import org.rage.loadsales.model.ServiceResponse;
import org.rage.loan.exception.RageDataException;

/**
 * Manage all the operations directly from the DataProvider
 * */
public interface LoanSalesManager {
	/**
	 * Save a new loan
	 * 
	 * @param loanSales
	 * */
	ServiceResponse saveLoanSales(LoanSales loanSales) throws RageDataException;
	
	/**
	 * Get a loan by id
	 * 
	 * @param id
	 * */
	LoanSales getLoanSales(String id);
	
	/**
	 * Delete a Loan
	 * 
	 * @param id
	 * @return ServiceResponse
	 * */
	ServiceResponse deleteLoanSales(String id);
	
	/**
	 * Update a existing loan
	 * 
	 * @param loanSales
	 * @return ServiceResponse
	 * */
	ServiceResponse updateLoanSales(LoanSales loanSales) throws RageDataException;
	
	/**
	 * Returns a list of all existing loans
	 * <br>Just for testing purposes</br>
	 * */
	List<LoanSales> getAllLoanSales();
	
	/**
	 * Get all loans by a person Id
	 * 
	 * @param personId
	 * @return list
	 * */
	List<LoanSales> getAllByPerson(String personId);
}
