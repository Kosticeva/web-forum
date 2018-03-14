package org.app.messages;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.app.data.database.Database;
import org.app.data.database.users.DatabaseUserService;
import org.app.data.user_data.Message;
import org.app.data.user_data.User;
import org.json.JSONException;
import org.json.JSONObject;

@Path("/users")
public class MessagesServlet {

	//nova poruka
	@POST
	@Path("/{username}/messages")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public String sendMessage(@PathParam("username") String username, Message m){
		
		if(DatabaseUserService.getUser(m.getSender())!=null && DatabaseUserService.getUser(m.getRecipient())!=null){
			
			User u = DatabaseUserService.getUser(m.getRecipient());
			u.getMessages().add(m);
			
			Database.saveData();
			JSONObject obj = new JSONObject();
			try {
				obj.put("success", true);
				obj.put("url", "/WebApp/messages.html");
				obj.put("message", "OK");
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return obj.toString();
			
		}else{
			
			JSONObject obj = new JSONObject();
			try {
				obj.put("success", false);
				obj.put("url", "/WebApp/messages.html");
				obj.put("message", "Sender and/or recipient not available");
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return obj.toString();
			
		}
		
	}
	
}
