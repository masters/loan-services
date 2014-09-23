package org.rage.loansales.manager;

import java.util.List;

import org.rage.loan.exception.RageDataException;
import org.rage.loan.model.Loan;
import org.rage.loan.model.ServiceResponse;

/**
 * Manage all the operations directly from the DataProvider
 * */
public interface LoanSalesManager {
	/**
	 * Save a new loan
	 * 
	 * @param loanSales
	 * */
	ServiceResponse saveLoanSales(Loan loanSales) throws RageDataException;
	
	/**
	 * Get a loan by id
	 * 
	 * @param id
	 * */
	Loan getLoanSales(String id);
	
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
	ServiceResponse updateLoanSales(Loan loanSales) throws RageDataException;
	
	/**
	 * Returns a list of all existing loans
	 * <br>Just for testing purposes</br>
	 * */
	List<Loan> getAllLoanSales();
	
	/**
	 * Get all loans by a person Id
	 * 
	 * @param personId
	 * @return list
	 * */
	List<Loan> getAllByPerson(String personId);
}
