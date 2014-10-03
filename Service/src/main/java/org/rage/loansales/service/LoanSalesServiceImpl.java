package org.rage.loansales.service;

import java.util.List;

import org.rage.loan.exception.RageDataException;
import org.rage.loan.helper.LoanHelper;
import org.rage.loan.model.Loan;
import org.rage.loan.model.ServiceResponse;
import org.rage.loansales.manager.LoanSalesManager;
import org.springframework.beans.factory.annotation.Autowired;

public class LoanSalesServiceImpl implements LoanSalesService {

	private transient LoanSalesManager loanSalesManager;

	@Autowired
	public void setLoanSalesManager(LoanSalesManager loanSalesManager) {
		this.loanSalesManager = loanSalesManager;
	}

	public ServiceResponse saveLoanSales(Loan loanSales) {
		try {
			return loanSalesManager.saveLoanSales(loanSales);
		} catch (RageDataException e) {
			e.printStackTrace();
			return LoanHelper.buildErrorResponse(e);
		}
	}

	public ServiceResponse updateLoanSales(Loan loanSales) {
		try {
			return loanSalesManager.updateLoanSales(loanSales);
		} catch (RageDataException e) {
			return LoanHelper.buildErrorResponse(e);
		}
	}

	public Loan getLoanSales(String id) {
		return loanSalesManager.getLoanSales(id);
	}

	public ServiceResponse deleteLoanSales(String id) {
		return loanSalesManager.deleteLoanSales(id);
	}

	public List<Loan> getAllLoanSales() {
		return loanSalesManager.getAllLoanSales();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.rage.loansales.service.LoanSalesService#getAllByPersonId(java.lang
	 * .String)
	 */
	public List<Loan> getAllByPersonId(String personId) {
		return this.loanSalesManager.getAllByPerson(personId);
	}
}
