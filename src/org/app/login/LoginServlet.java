package org.app.login;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.app.data.database.users.DatabaseUserService;
import org.app.data.user_data.User;
import org.json.JSONException;
import org.json.JSONObject;

@Path("/login")
public class LoginServlet{
	
    //proces logovanja
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
	public String logIn(User u){
		
    	User uu = DatabaseUserService.checkPassword(u.getUsername(), u.getPassword());
    	
		if(uu!=null){
			
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
				obj.put("message", "No such username/password combination.");
			} catch (JSONException e) {
				e.printStackTrace();
			}
			
			return obj.toString();
			
		}
	}

}
