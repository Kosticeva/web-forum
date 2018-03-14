package org.app.users.saved.messages;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.app.data.database.users.DatabaseUserService;
import org.app.data.user_data.Message;

@Path("/users")
public class SavedMessagesService {

	//dobavljanje svih sacuvanih poruka
	@GET
	@Path("/{username}/messages/save")
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<Message> getSavedMessages(@PathParam("username") String username){
		
		try {
			return DatabaseUserService.getUser(URLDecoder.decode(username, "UTF-8")).getSavedMessages();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
}
