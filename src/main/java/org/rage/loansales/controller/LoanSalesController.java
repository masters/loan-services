package org.rage.loansales.controller;

import java.util.List;

import org.rage.loadsales.model.LoanSales;
import org.rage.loadsales.model.ServiceResponse;
import org.rage.loansales.service.LoanSalesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Controller that creates a single point of access to the loan operations.
 * 
 * @author <roar109@gmail.com> Hector Mendoza
 * */
@RequestMapping
@Controller("loanController")
public class LoanSalesController {
	private transient LoanSalesService loanSalesService;

	/**
	 * Save a new loan
	 * 
	 * @param loan
	 * @return ServiceResponse
	 * */
	@RequestMapping(value="save",method=RequestMethod.POST, consumes="application/json")
	public @ResponseBody ServiceResponse saveLoanSales(@RequestBody LoanSales loan){
		return this.loanSalesService.saveLoanSales(loan);
	}
	
	/**
	 * Update a loan
	 * 
	 * @param loan
	 * @return ServiceResponse
	 * */
	@RequestMapping(value="update",method=RequestMethod.POST)
	public @ResponseBody ServiceResponse updateLoanSales(@RequestBody LoanSales loanSales){
		return this.loanSalesService.updateLoanSales(loanSales);
	}
	
	/**
	 * Delete a given loan by id
	 * 
	 * @param id
	 * @return ServiceResponse
	 * */
	@RequestMapping(value="delete/{id}",method=RequestMethod.POST)
	public @ResponseBody ServiceResponse deleteLoanSales(@PathVariable("id") String id){
		return this.loanSalesService.deleteLoanSales(id);
	}
	
	/**
	 * Get a loan by id
	 * 
	 * @param id
	 * @return ServiceResponse
	 * */
	@RequestMapping(method=RequestMethod.GET, value="get/{id}")
	public @ResponseBody LoanSales get(@PathVariable("id") String id){
		return this.loanSalesService.getLoanSales(id);
	}

	/**
	 * Get a list of all loans
	 * 
	 * @return list
	 * */
	@RequestMapping(method=RequestMethod.GET, value="list")
	public @ResponseBody List<LoanSales> list(){
		return this.loanSalesService.getAllLoanSales();
	}

	/**
	 * Search for all loans by person id
	 * 
	 * @param personId
	 * @return list
	 * */
	@RequestMapping(method=RequestMethod.GET, value="list/{personId}")
	public @ResponseBody List<LoanSales> listByPersonId(@PathVariable("personId") String personId){
		return this.loanSalesService.getAllByPersonId(personId);
	}
	
	@Autowired
	public void setLoanSalesService(LoanSalesService loanSalesService) {
		this.loanSalesService = loanSalesService;
	}
}
