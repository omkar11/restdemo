package com.crunchify.restjersey;

import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import org.json.JSONException;
import org.json.JSONObject;

import com.crunchify.restdatabase.Database;
import com.crunchify.restdatabase.User;

@Path("/user")
public class UserService {
	@GET
	@Path("/createuser1")
	@Produces("application/json")
	// public Response createUser(@QueryParam("firstname") String firstName,
	// @QueryParam("lastname") String lastName,
	// @QueryParam("address") String address, @QueryParam("emailId") String
	// emailId, @QueryParam("zip") int zip)
	// throws JSONException {

	public Response createUser(@QueryParam("emailId") String emailId) throws JSONException {

		JSONObject jsonObject = new JSONObject();

		// System.out.println("Firstname " + firstName);
		// System.out.println("LastName " + lastName);
		// System.out.println("address " + address);
		// System.out.println("zip " + zip);
		System.out.println("emailId " + emailId);

		User user = new User();
		// user.setFirstName(firstName);
		// user.setLastName(lastName);
		user.setEmailId(emailId);
		// user.setAddress(address);
		// user.setZip(zip);

		Map<String, User> dataabase = Database.getDatabase();
		dataabase.put(emailId, user);
		jsonObject.put("User", emailId);

		String result = "@Produces(\"application/json\") Output: User creared with id: \n\n" + jsonObject;
		return Response.status(200).entity(result).build();
	}

	@GET
	@Path("/createuser")
	@Produces("application/json")
	public Response createUsers(@QueryParam("emailId") String emailId, @QueryParam("firstname") String firstname,
			@QueryParam("lastname") String lastname, @QueryParam("address") String address) {

		JSONObject jsonObject = new JSONObject();
		User user = new User();
		user.setFirstName(firstname);
		user.setLastName(lastname);
		user.setAddress(address);
		user.setEmailId(emailId);

		Map<String, User> database = Database.getDatabase();

		if (database.containsKey(emailId)) {
			return Response.status(200).entity("User already exists with username emailId:" + emailId).build();
		}
		database.put(emailId, user);
		jsonObject.put("User", emailId);

		String result = "@Produces(\"application/json\") Output: User creared with id: \n\n" + jsonObject;
		return Response.status(200).entity(result).build();
	}

	@GET
	@Path("/updateuser")
	@Produces("application/json")
	public Response updateUsers(@QueryParam("emailId") String emailId, @QueryParam("firstname") String firstname,
			@QueryParam("lastname") String lastname, @QueryParam("address") String address) {

		Map<String, User> database = Database.getDatabase();

		if (!database.containsKey(emailId)) {
			return Response.status(200).entity("Username :" + emailId
					+ " does not exists in our database please have another look before updating").build();
		}

		JSONObject jsonObject = new JSONObject();
		User user = new User();
		user.setFirstName(firstname);
		user.setLastName(lastname);
		user.setAddress(address);
		user.setEmailId(emailId);

		database.put(emailId, user);
		jsonObject.put("User", emailId);

		String result = "@Produces(\"application/json\") Output: User has been updated:" + jsonObject;
		return Response.status(200).entity(result).build();

	}

	@GET
	@Path("/deleteeuser")
	@Produces("application/json")
	public Response deleteUsers(@QueryParam("emailId") String emailId) {
		Map<String, User> database = Database.getDatabase();
		if (!database.containsKey(emailId)) {
			return Response.status(200).entity("Username :" + emailId
					+ " does not exists in our database please have another look before deleting").build();
		}

		database.remove(emailId);
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("User", emailId);
		// return Response.status(200).entity("Delete User is called, emailId:"
		// + emailId).build();
		String result = "@Produces(\"application/json\") Output: User has been deleted:" + jsonObject;
		return Response.status(200).entity(result).build();

	}

	@GET
	@Path("/getuser")
	@Produces("application/json")
	public Response getUsers(@QueryParam("emailId") String emailId) {

		Map<String, User> database = Database.getDatabase();
		if (!database.containsKey(emailId)) {
			return Response.status(200).entity("Username :" + emailId
					+ " does not exists in our database please have another look before retrieving").build();
		}
		User user = database.get(emailId);
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("firstname", user.getFirstName());
		jsonObject.put("lastname", user.getLastName());
		jsonObject.put("emailId", user.getEmailId());
		jsonObject.put("address", user.getAddress());

		String result = "@Produces(\"application/json\") Output: User has been retrieved:" + jsonObject;
		return Response.status(200).entity(result).build();
		// return Response.status(200).entity("Get User is called emailId:" +
		// emailId + ", Firstname :"
		// + user.getFirstName() + ", Lastname :" + user.getLastName()).build();

	}
}
