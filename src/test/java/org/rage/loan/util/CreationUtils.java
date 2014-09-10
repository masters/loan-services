package org.rage.loan.util;

import java.util.ArrayList;
import java.util.List;

import org.rage.loadsales.model.LoanSales;
import org.rage.loadsales.model.ServiceResponse;

public final class CreationUtils {

	public static ServiceResponse createServiceResponse(){
		return ServiceResponse.newInstance();
	}
	
	public static LoanSales createLoanObject(){
		LoanSales loan = new LoanSales();
		loan.setAmmount(new Double(1500.00));
		loan.setConcept("concept 1");
		loan.setPersonId("132456");
		loan.setId("123");
		return loan;
	}
	
	public static List<LoanSales> createLoanSalesList(){
		List<LoanSales> list = new ArrayList<LoanSales>();
		list.add(createLoanObject());
		list.add(createLoanObject());
		return list;
	}
}
