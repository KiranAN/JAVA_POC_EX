package com.resource.User;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import com.dao.*;
import com.model.*;
import java.util.List;

@Path("/Users")
public class UserController {
	@GET
	@Produces(MediaType.APPLICATION_JSON)	
	public List<UserMsg> getUsers() {
		UserDAOImpl userInstance = UserDAOImpl.getInstance();
		return userInstance.getAllUsers();		
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)	
	@Path("{id}")
	public UserMsg getUser(@PathParam("id") int id) {
		UserDAOImpl userInstance = UserDAOImpl.getInstance();
		return userInstance.getUser(id);		
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)	
	@Path("/create")
	public int createUser(UserMsg msg) {
		UserDAOImpl userInstance = UserDAOImpl.getInstance();
		return userInstance.createUser(msg);		
	}
	
	@PUT	
	@Path("/update/{id}")
	public void updateUser(@PathParam("id") int id) {
		UserDAOImpl userInstance = UserDAOImpl.getInstance();
		return userInstance.updateUser(id);		
	}
	
	@POST	
	@Path("/delete/{id}")
	public boolean deleteUser(@PathParam("id") int id) {
		UserDAOImpl userInstance = UserDAOImpl.getInstance();
		return userInstance.deleteUser(id);		
	}
}

