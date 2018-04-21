/**
 * 
 */
package com.sait.amigo.user;

import java.util.ArrayList;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.bson.Document;
import com.fasterxml.jackson.databind.JsonNode;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.result.DeleteResult;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

/**
 * @author home
 *
 */
@Path("/amigoo-services")
public class AmigooServiceImpl {

	/**
	 * 
	 */
	private static final Logger logger = LogManager.getLogger("AmigooServiceImpl");

	public AmigooServiceImpl() {
		// TODO Auto-generated constructor stub
	}

	@POST
	@Path("setMyCarInfo")
	@Consumes(MediaType.APPLICATION_JSON)
	// @Produces(MediaType.APPLICATION_JSON)
	public ArrayList<Document> setMyCarInfo(JsonNode inputCarData) {

		/*
		 * MyCar myCar = new MyCar(); return myCar;
		 */
		// return Object;
		ArrayList<Document> addCarResult = new ArrayList<Document>();
		try {
			AmigooDBImpl amigoDbImpl = new AmigooDBImpl();
			MongoClient mongoClient = amigoDbImpl.getMongoClient();
			MongoDatabase database = mongoClient.getDatabase("amigoo");
			MongoCollection<Document> myCarDetailColl = database.getCollection("mycar-details");
			Document myCarDoc = Document.parse(inputCarData.toString());
			myCarDetailColl.insertOne(myCarDoc);
			Document doc = new Document();
			doc.append("AddCarResult", "Car Added Sucessfully");
			addCarResult.add(doc);

		} catch (Exception e) {
			Document doc = new Document();
			doc.append("Failed", e);
			addCarResult.add(doc);
			logger.error("Failed", e);
			// TODO: handle exception
		}
		return addCarResult;
	}

	// logger.warn("Firstog Message");
	@POST
	@Path("getMyCarInfo")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<Document> getMyCarInfo(JsonNode inputCarData) {
		ArrayList<Document> myCarList = new ArrayList<Document>();
		try {
			String userId = inputCarData.get("UserID").asText();
			AmigooDBImpl amigoDbImpl = new AmigooDBImpl();
			MongoClient mongoClient = amigoDbImpl.getMongoClient();
			MongoDatabase database = mongoClient.getDatabase("amigoo");
			MongoCollection<Document> myCarDetailColl = database.getCollection("mycar-details");

			// Document myDoc = myCarDetailColl.find(Filters.eq("UserID",
			// "251615")).first();
			FindIterable<Document> results = myCarDetailColl.find(Filters.eq("UserID", userId));

			for (Document doc : results) {
				System.out.println(doc);
				myCarList.add(doc);
			}

			return myCarList;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Failed", e);
			Document doc = new Document();
			doc.append("error", e);
			myCarList.add(doc);
			// TODO: handle exception
		}

		return myCarList;
	}

	@POST
	@Path("setMyCarBrkDwnInfo")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public void setMyCarBrkDwnInfo(JsonNode inputCarBrkDown) {
		ArrayList<Document> addBrkDwn = new ArrayList<Document>();
		try {
			AmigooDBImpl amigoDbImpl = new AmigooDBImpl();
			MongoClient mongoClient = amigoDbImpl.getMongoClient();
			MongoDatabase database = mongoClient.getDatabase("amigoo");
			MongoCollection<Document> myCarBrkDownColl = database.getCollection("mycar-breakdown");
			Document myCarBrkDownDoc = Document.parse(inputCarBrkDown.toString());
			myCarBrkDownColl.insertOne(myCarBrkDownDoc);
			Document doc = new Document();
			doc.append("AddBreakDownResult", "Success");
			addBrkDwn.add(doc);
		} catch (Exception e) {
			Document doc = new Document();
			doc.append("Failed", e);
			addBrkDwn.add(doc);
			logger.error("Failed", e);
			// TODO: handle exception
		}

	}

	@POST
	@Path("getMyCarBrkDwnInfo")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<Document> getMyCarBrkDwnInfo(JsonNode inputCarData) {
		ArrayList<Document> myCarBreakDownList = new ArrayList<Document>();
		try {
			String userId = inputCarData.get("UserID").asText();
			AmigooDBImpl amigoDbImpl = new AmigooDBImpl();
			MongoClient mongoClient = amigoDbImpl.getMongoClient();
			MongoDatabase database = mongoClient.getDatabase("amigoo");
			MongoCollection<Document> myCarDetailColl = database.getCollection("mycar-breakdown");

			// Document myDoc = myCarDetailColl.find(Filters.eq("UserID",
			// "251615")).first();
			FindIterable<Document> results = myCarDetailColl.find(Filters.eq("UserID", userId));

			for (Document doc : results) {
				System.out.println(doc);
				myCarBreakDownList.add(doc);
			}

		} catch (Exception e) {
			Document doc = new Document();
			doc.append("Failed", e);
			myCarBreakDownList.add(doc);
			logger.error("Failed", e);

			// TODO: handle exception
		}
		return myCarBreakDownList;
	}

	@POST
	@Path("deleteMyCarInfo")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<Document> deleteMyCarInfo(JsonNode inputCarData) {
		ArrayList<Document> deleteStatus = new ArrayList<Document>();
		try {
			AmigooDBImpl amigoDbImpl = new AmigooDBImpl();
			MongoClient mongoClient = amigoDbImpl.getMongoClient();
			MongoDatabase database = mongoClient.getDatabase("amigoo");
			MongoCollection<Document> myCarDetailColl = database.getCollection("mycar-details");

			String carId = inputCarData.get("carId").asText();

			Document doc = new Document();
			DeleteResult result = myCarDetailColl.deleteOne(Filters.eq("carId", carId));
			doc.append("DeletedStatus", result);
			doc.append("DeletedCarId", carId);
			// doc.append("DelCount", result.getDeletedCount());
			deleteStatus.add(doc);

		} catch (Exception e) {
			Document doc = new Document();
			doc.append("Failed", e);
			deleteStatus.add(doc);
			logger.error("Failed", e);

		}
		return deleteStatus;

	}

	@POST
	@Path("deleteBrkDwnHistory")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<Document> deleteBrkDwnHistory(JsonNode inputCarData) {
		ArrayList<Document> deleteStatus = new ArrayList<Document>();
		try {
			String brkDwnId = inputCarData.get("brkDwnId").asText();
			AmigooDBImpl amigoDbImpl = new AmigooDBImpl();
			MongoClient mongoClient = amigoDbImpl.getMongoClient();
			MongoDatabase database = mongoClient.getDatabase("amigoo");
			MongoCollection<Document> myCarBrkDwnColl = database.getCollection("mycar-breakdown");

			// Document myDoc = myCarDetailColl.find(Filters.eq("UserID",
			// "251615")).first();
			// String brkDwnId = inputCarData.get("brkDwnId").asText();

			Document doc = new Document();
			DeleteResult result = myCarBrkDwnColl.deleteOne(Filters.eq("brkDwnId", brkDwnId));
			doc.append("DelStatus", result);
			// doc.append("DelCount", result.getDeletedCount());
			deleteStatus.add(doc);

		} catch (Exception e) {
			logger.error("Failed", e);
			// TODO: handle exception
		}
		return deleteStatus;
	}

}
