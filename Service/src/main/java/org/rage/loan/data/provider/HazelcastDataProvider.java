package org.rage.loan.data.provider;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentMap;

import org.rage.loadsales.model.LoanSales;
import org.rage.loadsales.model.ServiceResponse;
import org.rage.loadsales.model.interfaces.DataProvider;
import org.rage.loan.exception.RageDataException;
import org.rage.loan.helper.LoanHelper;
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
public class HazelcastDataProvider implements DataProvider<LoanSales> {

	private transient HazelcastInstance instance;
	private transient String loanSalesMapName;
	
	public ServiceResponse save(LoanSales loanSales) throws RageDataException{
		String id  = null;
		try{
			ConcurrentMap<String, LoanSales> map = instance.getMap(loanSalesMapName);
			id = LoanHelper.addNewData(loanSales, Boolean.TRUE);
			map.put(id, loanSales);
		}catch(Exception ex){
			throw new RageDataException(ex);
		}
		return new ServiceResponse(Boolean.TRUE, id);
	}
	
	public ServiceResponse update(LoanSales loanSales) throws RageDataException{
		ConcurrentMap<String, LoanSales> map = instance.getMap(loanSalesMapName);
		LoanSales old = map.get(loanSales.getId());
		BeanUtils.copyProperties(loanSales, old, new String[]{"id","date"});
		map.replace(loanSales.getId(), old);
		return new ServiceResponse(Boolean.TRUE, null);
	}
	
	public LoanSales get(String id){
		ConcurrentMap<String, LoanSales> map = instance.getMap(loanSalesMapName);
		return map.get(id);
	}
	
	public ServiceResponse delete(String id){
		ConcurrentMap<String, LoanSales> map = instance.getMap(loanSalesMapName);
		map.remove(id);
		return new ServiceResponse(Boolean.TRUE, null);
	}
	
	/**
	 * Get all the loans registered
	 * 
	 * @return ArrayList<LoanSales>
	 * */
	public List<LoanSales> getAll() {
		ConcurrentMap<String, LoanSales> map = instance.getMap(loanSalesMapName);
		return new ArrayList<LoanSales>(map.values());
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
	public List<LoanSales> getAllByPerson(String personId) {
		// TODO Auto-generated method stub
		return null;
	}

}
