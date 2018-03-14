package org.app.forum.single;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.app.data.database.forums.DatabaseForumService;
import org.app.data.database.users.DatabaseUserService;
import org.app.data.entities.Subforum;
import org.app.data.user_data.User;
import org.json.JSONException;
import org.json.JSONObject;

@Path("/forums")
public class CredentialCheckForumServlet {

	//provera odgovornosti za forum
	@GET
	@Path("/{title}/check/{username}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String checkForum(@PathParam("title") String title, @PathParam("username") String username){
	
		Subforum f = null;
		try {
			f = DatabaseForumService.getForum(URLDecoder.decode(title, "UTF-8"));
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
			
		if(f!=null){
			
			User u = DatabaseUserService.getUser(username);
			if(f.getResponsibleMod().equals(u.getUsername())){
				
				JSONObject obj = new JSONObject();
				try {
					obj.put("success", true);
					obj.put("message", "OK");
				} catch (JSONException e) {
					e.printStackTrace();
				}
				
				return obj.toString();
				
			}else{
			
				JSONObject obj = new JSONObject();
				try {
					obj.put("success", true);
					obj.put("message", "NO");
				} catch (JSONException e) {
					e.printStackTrace();
				}
				
				return obj.toString();
			}
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
