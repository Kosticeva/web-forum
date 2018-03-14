package org.app.users;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Collection;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.app.data.database.Database;
import org.app.data.database.users.DatabaseUserService;
import org.app.data.user_data.User;

@Path("/users")
public class UsersServlet {

	//dobavljanje svih korisnika
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<User> getUsers(){
		return Database.users.values();
	}
	
	//dobavljanje odg korisnika
	@GET
	@Path("/{username}")
	@Produces(MediaType.APPLICATION_JSON)
	public User getUser(@PathParam("username") String username){
		
		User u = null;
		try {
			u = DatabaseUserService.getUser(URLDecoder.decode(username, "UTF-8"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return u;
	}
	
	
	
	
	
}
