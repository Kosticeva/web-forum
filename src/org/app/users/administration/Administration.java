package org.app.users.administration;

import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.app.data.database.Database;
import org.app.data.database.users.DatabaseUserService;
import org.app.data.user_data.User;
import org.json.JSONException;
import org.json.JSONObject;

@Path("/administration")
public class Administration {

	//izmena tipa korisnika
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String setTypeForUser(User u){
		
		User uu = DatabaseUserService.getUser(u.getUsername());
		
		if(uu!=null){
			uu.setType(u.getType());
			
			Database.saveData();
			JSONObject obj = new JSONObject();
			try {
				obj.put("success", true);
				obj.put("url", "/WebApp/user_page.html");
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
				obj.put("url", "/WebApp/change_type.html");
				obj.put("message", "Unable to set type");
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
			return obj.toString();
			
		}
		
	}
	
}
