package org.app.messages;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.app.data.database.Database;
import org.app.data.database.users.DatabaseUserService;
import org.app.data.user_data.Message;
import org.json.JSONException;
import org.json.JSONObject;

@Path("/users")
public class SaveMessagesServlet {

	//cuvanje poruke
	@POST
	@Path("/{username}/messages/{id}/save")
	@Produces(MediaType.APPLICATION_JSON)
	public String saveMessage(@PathParam("username") String username, @PathParam("id") int id){
	
		Message m = null;
		try {
			m = DatabaseUserService.getUser(URLDecoder.decode(username, "UTF-8")).getMessages().get(id);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(m!=null){
			try {
				if(!DatabaseUserService.getUser(URLDecoder.decode(username, "UTF-8")).getSavedMessages().contains(m)){
					DatabaseUserService.getUser(URLDecoder.decode(username, "UTF-8")).getSavedMessages().add(m);
				
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
						obj.put("message", "Already saved");
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					return obj.toString();
				}
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		JSONObject obj = new JSONObject();
		try {
			obj.put("success", false);
			obj.put("url", "/WebApp/messages.html");
			obj.put("message", "Something went wrong.");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return obj.toString();
	}
		
	//provera da li je poruka sacuvana
	@GET
	@Path("/{username}/messages/{id}/save")
	@Produces(MediaType.APPLICATION_JSON)
	public String checkSavedMessage(@PathParam("username") String username, @PathParam("id") int id){
	
		Message m = null;
		try {
			m = DatabaseUserService.getUser(URLDecoder.decode(username, "UTF-8")).getMessages().get(id);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(m!=null){
			try {
				if(DatabaseUserService.getUser(URLDecoder.decode(username, "UTF-8")).getSavedMessages().contains(m)){
					
					JSONObject obj = new JSONObject();
					try {
						obj.put("success", true);
						obj.put("url", "/WebApp/messages.html");
						obj.put("message", "YES");
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					return obj.toString();
					
				}else{
					
					JSONObject obj = new JSONObject();
					try {
						obj.put("success", true);
						obj.put("url", "/WebApp/messages.html");
						obj.put("message", "NO");
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					return obj.toString();
				}
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		JSONObject obj = new JSONObject();
		try {
			obj.put("success", false);
			obj.put("url", "/WebApp/messages.html");
			obj.put("message", "Something went wrong.");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return obj.toString();
	}
	
}
