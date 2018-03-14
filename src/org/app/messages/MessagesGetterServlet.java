package org.app.messages;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.app.data.database.Database;
import org.app.data.database.users.DatabaseUserService;
import org.app.data.user_data.Message;

@Path("/users")
public class MessagesGetterServlet {

	//dobavljanje svih poruka
	@GET
	@Path("/{username}/messages")
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<Message> getMessages(@PathParam("username") String username){
		
		try {
			return DatabaseUserService.getUser(URLDecoder.decode(username, "UTF-8")).getMessages();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	//dobavljanje poruke sa idjem
	@GET
	@Path("/{username}/messages/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Message getMessage(@PathParam("username") String username, @PathParam("id") int id){
		
		Message m = null;
		try {
			m = DatabaseUserService.getUser(URLDecoder.decode(username, "UTF-8")).getMessages().get(id);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(m!=null){
			m.setRead(true);
			Database.saveData();
		}
		
		return m;
	}
}
