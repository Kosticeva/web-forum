package org.app.comment.single;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.app.data.database.Database;
import org.app.data.database.comments.DatabaseCommentService;
import org.app.data.database.users.DatabaseUserService;
import org.app.data.entities.Comment;
import org.app.data.user_data.User;
import org.json.JSONException;
import org.json.JSONObject;

@Path("/users")
public class SaveCommentServlet {

	//cuvanje komentara
	@POST
	@Path("/{username}/forums/{title}/subjects/{parent}/comments/{id}/save")
	@Produces(MediaType.APPLICATION_JSON)
	public String saveComment(@PathParam("username") String username, 
			@PathParam("title") String title, @PathParam("parent") String parent, @PathParam("id") long id){
		
		Comment c = DatabaseCommentService.getComment(title, parent, id);
		User u = DatabaseUserService.getUser(username);
		
		if(c!=null && u!=null){
			
			if(!u.getSavedComments().contains(c)){
			
				u.getSavedComments().add(c);
				Database.saveData();
				JSONObject obj = new JSONObject();
				try {
					obj.put("success", true);
					obj.put("url", "/WebApp/topic.html");
					obj.put("message", "OK");
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
			obj.put("message", "Unable to save comment");
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		return obj.toString();
		
	}
	
	//provera da li je komentar sacuvan
	@GET
	@Path("/{username}/forums/{title}/subjects/{parent}/comments/{id}/save")
	@Produces(MediaType.APPLICATION_JSON)
	public String checkSavedComment(@PathParam("username") String username, 
			@PathParam("title") String title, @PathParam("parent") String parent, @PathParam("id") long id){
		
		Comment c = null;
		try {
			c = DatabaseCommentService.getComment(URLDecoder.decode(title, "UTF-8"), URLDecoder.decode(parent, "UTF-8"), id);
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
		
		if(c!=null && u!=null){
			
			if(u.getSavedComments().contains(c)){
			
				JSONObject obj = new JSONObject();
				try {
					obj.put("success", true);
					obj.put("url", "/WebApp/topic.html");
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
					obj.put("url", "/WebApp/topic.html");
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
