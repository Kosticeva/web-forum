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
import org.app.data.database.comments.DatabaseCommentService;
import org.app.data.database.forums.DatabaseForumService;
import org.app.data.database.users.DatabaseUserService;
import org.app.data.entities.Comment;
import org.app.data.entities.Subforum;
import org.app.data.user_data.Complaint;
import org.app.data.user_data.User;
import org.json.JSONException;
import org.json.JSONObject;

@Path("/complaints")
public class CommentComplaintServlet {

	//nova zalba na komentar
	@POST
	@Path("/comments/{parent}/{title}/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String addCommentComplaint(@PathParam("parent") String parent, @PathParam("title") String title,
			@PathParam("id") long id, Complaint c){
		
		Comment cc = null;
		try {
			cc = DatabaseCommentService.getComment(URLDecoder.decode(parent, "UTF-8"), URLDecoder.decode(title, "UTF-8"), id);
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		User u = DatabaseUserService.getUser(c.getAuthor());
		
		if(cc!=null && u!=null){
			
			c.setTarget(cc);
			c.setAuthor(u.getUsername());
			
			for(User uu: Database.users.values()){
				if(uu.getType().equals("Administrator")){
					c.getAuthorities().add(uu);
				}else{
					Subforum f = DatabaseForumService.getForum(cc.getGrandparent());

					if(f.getResponsibleMod().equals(uu.getUsername())){
						c.getAuthorities().add(uu);
					}
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
