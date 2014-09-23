/**
 * 
 */
package org.rage.loan.factory;


import java.net.UnknownHostException;

import org.rage.loan.model.interfaces.DataFactory;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;

/**
 * Represents a connection to the MongoDB
 * 
 * @author <roar109@gmail.com> Hector Mendoza
 *
 */
public class MongoDataFactory implements DataFactory<DB, DBCollection>{
	private transient String url;
	private transient Integer port;
	private transient String db;
	private transient MongoClient mongoClient;

	/**
	 * Do pre configuration tasks
	 * @see org.rage.loan.model.interfaces.DataFactory#preConfiguration()
	 */
	public void preConfiguration() throws UnknownHostException {
		mongoClient = new MongoClient( url , port );
	}

	/**
	 * Connect to the Mongo DB
	 * @see org.rage.loan.model.interfaces.DataFactory#connect()
	 */
	public void connect() {
		try {
			preConfiguration();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Get default database or the one requested
	 * @param dbName
	 * @return DB
	 * */
	public DB getDB(String dbName){
		if(dbName == null)
			dbName = this.db;
		return mongoClient.getDB(dbName);
	}
	
	public DBCollection getCollection(String collectionName){
		return getDB(null).getCollection(collectionName);
	}
	
	/**
	 * Close the connection
	 * @see org.rage.loan.model.interfaces.DataFactory#shutdown()
	 */
	public void shutdown() {
		mongoClient.close();
	}
	
	public void setUrl(String url) {
		this.url = url;
	}

	public void setPort(Integer port) {
		this.port = port;
	}

	public void setDb(String db) {
		this.db = db;
	}
}
