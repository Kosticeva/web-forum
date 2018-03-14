package org.app.register;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.app.data.database.Database;
import org.app.data.database.users.DatabaseUserService;
import org.app.data.user_data.User;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Servlet implementation class RegistrationServlet
 */
@Path("/register")
public class RegistrationServlet{
    //dodavanje novog usera
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
	public String logIn(User u){
		
    	User uu = DatabaseUserService.addUser(u);
    	
		if(uu!=null){
			
			Database.saveData();
			JSONObject obj = new JSONObject();
			try {
				obj.put("success", true);
				obj.put("url", "/WebApp/home_page.html");
				obj.put("message", "OK");
				obj.put("cookie-username", "username="+uu.getUsername()+"; path=/");
				obj.put("cookie-type", "type="+uu.getType()+"; path=/");
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return obj.toString();
			
		}else{
			JSONObject obj = new JSONObject();
			try {
				obj.put("success", false);
				obj.put("url", "no");
				obj.put("message", "User with username: \""+u.getUsername()+"\" already exists.");
			} catch (JSONException e) {
				e.printStackTrace();
			}
			
			return obj.toString();
		}
	
    }
}
