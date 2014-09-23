package org.rage.loan.helper;

import java.util.Date;

import org.rage.loan.model.Loan;
import org.rage.loan.model.ServiceResponse;

import com.google.gson.Gson;

/**
 * Utilities for data manipulation and error responses.
 * 
 * @author <roar109@gmail.com> Hector Mendoza
 * */
public final class LoanHelper {

	/**
	 * Add default additional fields to a new object to be persisted
	 * 
	 * @param loan the object with the loan data
	 * @param addId if the auto-generated id must be set
	 * */
	public static String addNewData(Loan loan, boolean addId){
		Date now = new Date();
		loan.setDate(now);
		loan.setActive(1);
		String id = String.valueOf(now.getTime());
		if(addId)
			loan.setId(id);
		return id;
	}
	
	/**
	 * Build a response from a exception
	 * 
	 * @exception exception
	 * */
	public static ServiceResponse buildErrorResponse(Exception exception){
		return new ServiceResponse(Boolean.FALSE, exception.getMessage());
	}
	
	/**
	 * Converts a object into a json.
	 * 
	 * @return string a json representation of the passed instance
	 * */
	public static String toJSON(Object instance){
		Gson gson = new Gson();
		return gson.toJson(instance);
	}
	
	/**
	 * Converts a json into a given type class instance
	 * 
	 * @param instance json in a string to be converted
	 * @param type class type
	 * @return Object
	 * */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static Object toObjectFromJSON(String instance, Class type){
		Gson gson = new Gson();
		return gson.fromJson(instance, type);
	}
	
	/**
	 * Build a update response message
	 * 
	 * @param valid
	 * @param message
	 * */
	public static ServiceResponse buildUpdateResponse(boolean valid, String message){
		return new ServiceResponse(valid, message);
	} 
}
