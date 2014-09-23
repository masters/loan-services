package org.rage.loan.util;

import java.util.ArrayList;
import java.util.List;

import org.rage.loan.model.Loan;
import org.rage.loan.model.ServiceResponse;


public final class CreationUtils {

	public static ServiceResponse createServiceResponse(){
		return ServiceResponse.newInstance();
	}
	
	public static Loan createLoanObject(){
		Loan loan = new Loan();
		loan.setAmmount(new Double(1500.00));
		loan.setConcept("concept 1");
		loan.setPersonId("132456");
		loan.setId("123");
		return loan;
	}
	
	public static List<Loan> createLoanSalesList(){
		List<Loan> list = new ArrayList<Loan>();
		list.add(createLoanObject());
		list.add(createLoanObject());
		return list;
	}
}
