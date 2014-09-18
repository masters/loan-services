package org.rage.loansales.manager;

import java.util.List;

import org.rage.loadsales.model.LoanSales;
import org.rage.loadsales.model.ServiceResponse;
import org.rage.loadsales.model.interfaces.DataProvider;
import org.rage.loan.exception.RageDataException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

/**
 * @see org.rage.loansales.manager.LoanSalesManager
 * */
public class LoanSalesManagerImpl implements LoanSalesManager {

	private transient DataProvider<LoanSales> dataProvider;
	
	public ServiceResponse saveLoanSales(final LoanSales loanSales) throws RageDataException{
		return this.dataProvider.save(loanSales);
	}
	
	public ServiceResponse updateLoanSales(final LoanSales loanSales) throws RageDataException{
		return this.dataProvider.update(loanSales);
	}
	
	public LoanSales getLoanSales(final String id){
		return this.dataProvider.get(id);
	}
	
	public ServiceResponse deleteLoanSales(final String id){
		return this.dataProvider.delete(id);
	}
	
	public List<LoanSales> getAllLoanSales() {
		return this.dataProvider.getAll();
	}
	
	@Autowired
	@Qualifier("mongoLoanDataProvider")
	public void setDataProvider(final DataProvider<LoanSales> dataProvider){
		this.dataProvider = dataProvider;
	}

	/* (non-Javadoc)
	 * @see org.rage.loansales.manager.LoanSalesManager#getAllByPerson(java.lang.String)
	 */
	public List<LoanSales> getAllByPerson(String personId) {
		return this.dataProvider.getAllByPerson(personId);
	}
}
