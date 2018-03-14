package org.app.complaints.newComplaint;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.app.data.database.Database;
import org.app.data.database.forums.DatabaseForumService;
import org.app.data.database.users.DatabaseUserService;
import org.app.data.entities.Subforum;
import org.app.data.user_data.Complaint;
import org.app.data.user_data.User;
import org.json.JSONException;
import org.json.JSONObject;

@Path("/complaints")
public class ForumComplaintServlet {

	//nova zalba na forum
	@POST
	@Path("/forums/{title}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String addForumComplaint(@PathParam("title") String title, Complaint c){
		
		Subforum f = null;
		try {
			f = DatabaseForumService.getForum(URLDecoder.decode(title, "UTF-8"));
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		User u = DatabaseUserService.getUser(c.getAuthor());
		
		if(f!=null && u!=null){
			
			c.setTarget(f);
			c.setAuthor(u.getUsername());
			
			for(User uu: Database.users.values()){
				if(uu.getType().equals("Administrator")){
					c.getAuthorities().add(uu);
				}
			}
			
			Database.complaints.add(c);
			
			Database.saveData();
			JSONObject obj = new JSONObject();
			try {
				obj.put("success", true);
				obj.put("url", "/WebApp/complaints.html");
				obj.put("message", "Report succesfully made.");
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return obj.toString();
		}
		
		JSONObject obj = new JSONObject();
		try {
			obj.put("success", false);
			obj.put("url", "/WebApp/complaints.html");
			obj.put("message", "Something went wrong.");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return obj.toString();
	}
	
}
