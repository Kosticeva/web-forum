package org.app.comment.single;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.app.data.database.comments.DatabaseCommentService;
import org.app.data.database.forums.DatabaseForumService;
import org.app.data.database.users.DatabaseUserService;
import org.app.data.entities.Comment;
import org.app.data.entities.Subforum;
import org.app.data.user_data.User;
import org.json.JSONException;
import org.json.JSONObject;

@Path("/forums/{parent}/subjects/{title}/comments/{Id}/check/{username}")
public class CredentialCheckCommentServlet {

	//provera odgovornosti za komentar
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public String checkCredentials(@PathParam("parent") String parent, @PathParam("title") String title,
			@PathParam("Id") long id, @PathParam("username") String username){
	
	
		Comment c = DatabaseCommentService.getComment(parent, title, id);
		if(c!=null){
			
			User u = DatabaseUserService.getUser(username);
			if(c.getAuthor().equals(u.getUsername())){
				
				JSONObject obj = new JSONObject();
				try {
					obj.put("success", true);
					obj.put("message", "OK");
				} catch (JSONException e) {
					e.printStackTrace();
				}
				
				return obj.toString();
				
			}else{
				
				Subforum f = DatabaseForumService.getForum(c.getGrandparent());
				if(f.getResponsibleMod().equals(u.getUsername())){
					
					JSONObject obj = new JSONObject();
					try {
						obj.put("success", true);
						obj.put("message", "OK");
					} catch (JSONException e) {
						e.printStackTrace();
					}
					
					return obj.toString();
				}
				
			}
			
			JSONObject obj = new JSONObject();
			try {
				obj.put("success", true);
				obj.put("message", "NO");
			} catch (JSONException e) {
				e.printStackTrace();
			}
			
			return obj.toString();
			
		}
		
		JSONObject obj = new JSONObject();
		try {
			obj.put("success", false);
			obj.put("message", "NO");
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		return obj.toString();
		
	}
	
}
