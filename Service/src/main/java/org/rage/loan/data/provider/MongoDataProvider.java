package org.rage.loan.data.provider;

import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;
import org.rage.loadsales.model.LoanSales;
import org.rage.loadsales.model.ServiceResponse;
import org.rage.loadsales.model.interfaces.DataFactory;
import org.rage.loadsales.model.interfaces.DataProvider;
import org.rage.loan.exception.RageDataException;
import org.rage.loan.helper.LoanHelper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

/**
 * Data provider, do basic operations against MongoDB.
 * 
 * @author <roar109@gmail.com> Hector Mendoza
 *
 */
@Component("mongoLoanDataProvider")
public class MongoDataProvider implements DataProvider<LoanSales> {
	private DataFactory<DB, DBCollection> mongoDataFactory;
	private final static String COLLECTION_NAME = "loan";
	private final static String PROPERTY_NAME_DATA = "data";
	private final static String PROPERTY_NAME_ID = "_id"; 
	private final static String PERSON_ID= "personId";
	
	@Autowired
	@Qualifier("mDataFactory")
	public void setMongoDataFactory(DataFactory<DB, DBCollection> mongoDataFactory){
		this.mongoDataFactory = mongoDataFactory;	
	}

	/**
	 * Save a LoanSales on MongoDB
	 * 
	 * @param instance
	 * @return ServiceResponse
	 * @see org.rage.loadsales.model.interfaces.DataProvider#save(java.lang.Object)
	 * 
	 * @TODO improve
	 */
	public ServiceResponse save(LoanSales instance) throws RageDataException {
		BasicDBObject dbObject = new BasicDBObject();
		LoanHelper.addNewData(instance, Boolean.FALSE);
		dbObject.put(PROPERTY_NAME_DATA, LoanHelper.toJSON(instance));
		dbObject.put(PERSON_ID, instance.getPersonId());
		
		this.mongoDataFactory.getCollection(COLLECTION_NAME).save(dbObject);
		return new ServiceResponse(Boolean.TRUE,null,String.valueOf(dbObject.get(PROPERTY_NAME_ID)));
	}

	/**
	 * Get a loan by id
	 * 
	 * @param id
	 * @return LoanSales
	 * @see org.rage.loadsales.model.interfaces.DataProvider#get(java.lang.String)
	 */
	public LoanSales get(String id) {
		LoanSales result = null;
		DBObject results = this.mongoDataFactory.getCollection(COLLECTION_NAME).findOne(new BasicDBObject(PROPERTY_NAME_ID, new ObjectId(id)));
		if(results != null){
			String json  = String.valueOf(results.get(PROPERTY_NAME_DATA));
			result = (LoanSales) LoanHelper.toObjectFromJSON(json, LoanSales.class);
			if(result != null){
				result.setId(id);
			}
		}
		return result;
	}

	/**
	 * Delete a loan
	 * 
	 * @param id
	 * @return ServiceResponse
	 * @see org.rage.loadsales.model.interfaces.DataProvider#delete(java.lang.String)
	 */
	public ServiceResponse delete(String id) {
		BasicDBObject deletionCriteria = new BasicDBObject(PROPERTY_NAME_ID, new ObjectId(id));
		this.mongoDataFactory.getCollection(COLLECTION_NAME).remove(deletionCriteria);
		return new ServiceResponse(Boolean.TRUE,null);
	}

	/**
	 * Update a loan
	 * 
	 * @param instance
	 * @return ServiceResponse
	 * @see org.rage.loadsales.model.interfaces.DataProvider#update(java.lang.Object)
	 * 
	 * @TODO improve
	 */
	public ServiceResponse update(LoanSales instance) throws RageDataException {
		ServiceResponse resultOperation = LoanHelper.buildUpdateResponse(Boolean.TRUE, null);
		DBObject results = this.mongoDataFactory.getCollection(COLLECTION_NAME)
					.findOne(new BasicDBObject(PROPERTY_NAME_ID, new ObjectId(instance.getId())));
		if(results != null){
			String json  = String.valueOf(results.get(PROPERTY_NAME_DATA));
			LoanSales result = (LoanSales) LoanHelper.toObjectFromJSON(json, LoanSales.class);
			BeanUtils.copyProperties(instance, result, new String[]{"id","date"});
			results.put(PROPERTY_NAME_DATA, LoanHelper.toJSON(result));
			
			this.mongoDataFactory.getCollection(COLLECTION_NAME).update(new BasicDBObject(PROPERTY_NAME_ID, 
					new ObjectId(instance.getId())), results);
		}else{
			resultOperation = LoanHelper.buildUpdateResponse(Boolean.FALSE, "Criteria not match");
		}
		return resultOperation;
	}

	/* (non-Javadoc)
	 * @see org.rage.loadsales.model.interfaces.DataProvider#getAll()
	 */
	@Deprecated
	public List<LoanSales> getAll() {
		return null;
	}
	
	/**
	 * Search for loans by personId
	 * 
	 * @param personId
	 * @return loans
	 * */
	public List<LoanSales> getAllByPerson(String personId){
		List<LoanSales> loans = new ArrayList<LoanSales>();
		DBObject result = null;
		DBCursor results = this.mongoDataFactory.getCollection(COLLECTION_NAME).find(new BasicDBObject(PERSON_ID, personId));
		while(results.hasNext()){
			result = results.next();
			String json = String.valueOf(result.get(PROPERTY_NAME_DATA));
			LoanSales loan = (LoanSales)LoanHelper.toObjectFromJSON(json, LoanSales.class);
			loan.setId(String.valueOf(result.get(PROPERTY_NAME_ID)));
			loans.add(loan);
		}
		results.close();
		return loans;
	}
}
