package org.rage.loan.controller;

import java.util.List;

import org.easymock.Capture;
import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Assert;
import org.junit.Test;
import org.rage.loadsales.model.LoanSales;
import org.rage.loadsales.model.ServiceResponse;
import org.rage.loan.util.CreationUtils;
import org.rage.loansales.controller.LoanSalesController;
import org.rage.loansales.service.LoanSalesService;

/**
 * ControllerTest
 * 
 * @author <roar109@gmail.com> Hector Mendoza
 * */
public class LoanControllerTest {

	private transient LoanSalesController loanController;
	private transient LoanSalesService loanSalesService;
	
	@Before
	public void setUp(){
		loanController = new LoanSalesController();
		loanSalesService = EasyMock.createMock(LoanSalesService.class);
		loanController.setLoanSalesService(loanSalesService);
	}
	
	@Test
	public void testSaveOperation(){
		Capture<LoanSales> capture = new Capture<LoanSales>();
		EasyMock.expect(loanSalesService.saveLoanSales(EasyMock.capture(capture))).andReturn(CreationUtils.createServiceResponse());
		
		EasyMock.replay(loanSalesService);
		ServiceResponse response = loanController.saveLoanSales(CreationUtils.createLoanObject());
		EasyMock.verify(loanSalesService);
		
		Assert.assertTrue("Response should be true", response.isValid());
	}
	
	@Test
	public void testUpdateOperation(){
		Capture<LoanSales> capture = new Capture<LoanSales>();
		EasyMock.expect(loanSalesService.updateLoanSales(EasyMock.capture(capture))).andReturn(CreationUtils.createServiceResponse());
		
		EasyMock.replay(loanSalesService);
		ServiceResponse response = loanController.updateLoanSales(CreationUtils.createLoanObject());
		EasyMock.verify(loanSalesService);
		
		Assert.assertTrue("Response should be true", response.isValid());
	}
	
	@Test
	public void testDeleteOperation(){
		Capture<String> capture = new Capture<String>();
		EasyMock.expect(loanSalesService.deleteLoanSales(EasyMock.capture(capture))).andReturn(CreationUtils.createServiceResponse());
		String id = "132465";
		
		EasyMock.replay(loanSalesService);
		ServiceResponse response = loanController.deleteLoanSales(id);
		EasyMock.verify(loanSalesService);
		
		Assert.assertTrue("Response should be true", response.isValid());
	}

	@Test
	public void testGetOperation(){
		Capture<String> capture = new Capture<String>();
		EasyMock.expect(loanSalesService.getLoanSales(EasyMock.capture(capture))).andReturn(CreationUtils.createLoanObject());
		String id = "132465";
		
		EasyMock.replay(loanSalesService);
		LoanSales response = loanController.get(id);
		EasyMock.verify(loanSalesService);
		
		Assert.assertNotNull("ID not null", response.getId());
	}
	
	@Test
	public void testGetByPersonIdOperation(){
		Capture<String> capture = new Capture<String>();
		EasyMock.expect(loanSalesService.getAllByPersonId(EasyMock.capture(capture)))
				.andReturn(CreationUtils.createLoanSalesList());
		String personId = "132465";
		
		EasyMock.replay(loanSalesService);
		List<LoanSales> response = loanController.listByPersonId(personId);
		EasyMock.verify(loanSalesService);
		
		Assert.assertNotNull("Valid list", response);
		Assert.assertTrue("List contain elements", response.size() > 0);
	}
}
