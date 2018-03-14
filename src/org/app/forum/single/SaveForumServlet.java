package org.app.forum.single;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.app.data.database.Database;
import org.app.data.database.forums.DatabaseForumService;
import org.app.data.database.users.DatabaseUserService;
import org.app.data.entities.Subforum;
import org.app.data.user_data.User;
import org.json.JSONException;
import org.json.JSONObject;

@Path("/users")
public class SaveForumServlet {

	//cuvanje foruma
	@Path("{username}/forums/{title}/save")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public String saveForum(@PathParam("username") String username, @PathParam("title") String title){
		
		Subforum f = null;
		try {
			f = DatabaseForumService.getForum(URLDecoder.decode(title, "UTF-8"));
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		User u = null;
		try {
			u = DatabaseUserService.getUser(URLDecoder.decode(username, "UTF-8"));
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		if(f!=null && u!=null){
			
			if(!u.getFollowedForums().contains(f)){
			
				u.getFollowedForums().add(f);
				
				Database.saveData();
				JSONObject obj = new JSONObject();
				try {
					obj.put("success", true);
					obj.put("url", "/WebApp/forum.html");
					obj.put("message", "OK");
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				return obj.toString();
			}else{
				
				JSONObject obj = new JSONObject();
				try {
					obj.put("success", true);
					obj.put("url", "/WebApp/forum.html");
					obj.put("message", "Already saved");
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				return obj.toString();
				
			}
		}
		
		JSONObject obj = new JSONObject();
		try {
			obj.put("success", false);
			obj.put("url", "no");
			obj.put("message", "Unable to save forum");
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		return obj.toString();
		
	}
	
	//provera da li je forum vec snimljen
	@Path("{username}/forums/{title}/save")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String checkSaveForum(@PathParam("username") String username, @PathParam("title") String title){
		
		Subforum f = null;
		try {
			f = DatabaseForumService.getForum(URLDecoder.decode(title, "UTF-8"));
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		User u = null;
		try {
			u = DatabaseUserService.getUser(URLDecoder.decode(username, "UTF-8"));
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		if(f!=null && u!=null){
			
			if(u.getFollowedForums().contains(f)){
			
				JSONObject obj = new JSONObject();
				try {
					obj.put("success", true);
					obj.put("url", "/WebApp/forum.html");
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
					obj.put("url", "/WebApp/forum.html");
					obj.put("message", "NO");
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				return obj.toString();
				
			}
		}
		
		JSONObject obj = new JSONObject();
		try {
			obj.put("success", false);
			obj.put("url", "no");
			obj.put("message", "Error");
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		return obj.toString();
		
	}
}
