/**
 * 
 */
package com.sait.amigo.user;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.bson.Document;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.util.JSON;

/**
 * @author home
 *
 */
@Path("/amigoo-services")
public class AmigooServiceImpl {

	/**
	 * 
	 */
	public AmigooServiceImpl() {
		// TODO Auto-generated constructor stub
	}

	@PUT
	@Path("setMyCarInfo")
	@Consumes(MediaType.APPLICATION_JSON)
	// @Produces(MediaType.APPLICATION_JSON)
	public void setMyCarInfo(JsonNode inputCarData) {
		/*
		 * MyCar myCar = new MyCar(); return myCar;
		 */
		// return Object;
		AmigooDBImpl amigoDbImpl = new AmigooDBImpl();
		MongoClient mongoClient = amigoDbImpl.getMongoClient();
		MongoDatabase database = mongoClient.getDatabase("amigoo");
		MongoCollection<Document> myCarDetailColl = database.getCollection("mycar-details");
		Document myCarDoc = Document.parse(inputCarData.toString());
		myCarDetailColl.insertOne(myCarDoc);

	}

	@GET
	@Path("getMyCarInfo")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public MyCar getMyCarInfo() {
		MyCar myCar = new MyCar();
		return myCar;
	}

	@PUT
	@Path("setMyCarBrkDwnInfo")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public MyCar setMyCarBrkDwnInfo() {
		MyCar myCar = new MyCar();
		return myCar;
	}

	@GET
	@Path("getMyCarBrkDwnInfo")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public MyCar getMyCarBrkDwnInfo() {
		MyCar myCar = new MyCar();
		return myCar;
	}
}
