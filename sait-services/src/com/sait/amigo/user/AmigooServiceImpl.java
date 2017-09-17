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
	@Produces(MediaType.APPLICATION_JSON)
	public MyCar setMyCarInfo() {
		MyCar myCar = new MyCar();
		return myCar;
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
