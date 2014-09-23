package org.rage.loansales.manager;

import java.util.List;

import org.rage.loan.exception.RageDataException;
import org.rage.loan.model.Loan;
import org.rage.loan.model.ServiceResponse;
import org.rage.loan.model.interfaces.DataProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

/**
 * @see org.rage.loansales.manager.LoanSalesManager
 * */
public class LoanSalesManagerImpl implements LoanSalesManager {

	private transient DataProvider<Loan> dataProvider;
	
	public ServiceResponse saveLoanSales(final Loan loanSales) throws RageDataException{
		return this.dataProvider.save(loanSales);
	}
	
	public ServiceResponse updateLoanSales(final Loan loanSales) throws RageDataException{
		return this.dataProvider.update(loanSales);
	}
	
	public Loan getLoanSales(final String id){
		return this.dataProvider.get(id);
	}
	
	public ServiceResponse deleteLoanSales(final String id){
		return this.dataProvider.delete(id);
	}
	
	public List<Loan> getAllLoanSales() {
		return this.dataProvider.getAll();
	}
	
	@Autowired
	//@Qualifier("mongoLoanDataProvider")
	@Qualifier("hazelcastDataProvider")
	public void setDataProvider(final DataProvider<Loan> dataProvider){
		this.dataProvider = dataProvider;
	}

	/* (non-Javadoc)
	 * @see org.rage.loansales.manager.LoanSalesManager#getAllByPerson(java.lang.String)
	 */
	public List<Loan> getAllByPerson(String personId) {
		return this.dataProvider.getAllByPerson(personId);
	}
}
