package org.rage.loan.data.provider;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentMap;

import org.rage.loan.exception.RageDataException;
import org.rage.loan.helper.LoanHelper;
import org.rage.loan.model.Loan;
import org.rage.loan.model.ServiceResponse;
import org.rage.loan.model.interfaces.DataProvider;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.hazelcast.core.HazelcastInstance;

/**
 * Provider of data
 * @author Hector Mendoza
 * */
@Component("hazelcastDataProvider")
public class HazelcastDataProvider implements DataProvider<Loan> {

	private transient HazelcastInstance instance;
	private transient String loanSalesMapName;
	
	public ServiceResponse save(Loan loanSales) throws RageDataException{
		String id  = null;
		try{
			ConcurrentMap<String, Loan> map = instance.getMap(loanSalesMapName);
			id = LoanHelper.addNewData(loanSales, Boolean.TRUE);
			map.put(id, loanSales);
		}catch(Exception ex){
			throw new RageDataException(ex);
		}
		return new ServiceResponse(Boolean.TRUE, id);
	}
	
	public ServiceResponse update(Loan loanSales) throws RageDataException{
		ConcurrentMap<String, Loan> map = instance.getMap(loanSalesMapName);
		Loan old = map.get(loanSales.getId());
		BeanUtils.copyProperties(loanSales, old, new String[]{"id","date"});
		map.replace(loanSales.getId(), old);
		return new ServiceResponse(Boolean.TRUE, null);
	}
	
	public Loan get(String id){
		ConcurrentMap<String, Loan> map = instance.getMap(loanSalesMapName);
		return map.get(id);
	}
	
	public ServiceResponse delete(String id){
		ConcurrentMap<String, Loan> map = instance.getMap(loanSalesMapName);
		map.remove(id);
		return new ServiceResponse(Boolean.TRUE, null);
	}
	
	/**
	 * Get all the loans registered
	 * 
	 * @return ArrayList<LoanSales>
	 * */
	public List<Loan> getAll() {
		ConcurrentMap<String, Loan> map = instance.getMap(loanSalesMapName);
		return new ArrayList<Loan>(map.values());
	}
	
	@Value("#{systemProperties['hazel.map.loan.name']}")
	public void setLoanSalesMapName(String loanSalesMapName) {
		this.loanSalesMapName = loanSalesMapName;
	}

	@Autowired
	@Qualifier("instanceHazelcast")
	public void setInstance(HazelcastInstance instance){
		this.instance = instance;
	}

	/* (non-Javadoc)
	 * @see org.rage.loadsales.model.interfaces.DataProvider#getAllByPerson(java.lang.String)
	 */
	public List<Loan> getAllByPerson(String personId) {
		// TODO Auto-generated method stub
		return null;
	}

}
