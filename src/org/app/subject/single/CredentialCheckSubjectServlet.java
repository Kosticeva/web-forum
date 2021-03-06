package org.app.subject.single;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.app.data.database.forums.DatabaseForumService;
import org.app.data.database.subjects.DatabaseSubjectService;
import org.app.data.database.users.DatabaseUserService;
import org.app.data.entities.Subject;
import org.app.data.user_data.User;
import org.json.JSONException;
import org.json.JSONObject;

@Path("/forums")
public class CredentialCheckSubjectServlet {
	
	//provera odgovornosti za temu
	@GET
	@Path("/{parent}/subjects/{title}/check/{username}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public String checkCredentials(@PathParam("parent") String parent, @PathParam("title") String title, @PathParam("username") String username){
	
		Subject s = null;
		try {
			s = DatabaseSubjectService.getSubject(URLDecoder.decode(parent, "UTF-8"), URLDecoder.decode(title, "UTF-8"));
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		if(s!=null){
			
			User u = DatabaseUserService.getUser(username);
			if(s.getAuthor().equals(u.getUsername())){
				
				JSONObject obj = new JSONObject();
				try {
					obj.put("success", true);
					obj.put("message", "OK");
				} catch (JSONException e) {
					e.printStackTrace();
				}
				
				return obj.toString();
				
			}else{
				
				if(DatabaseForumService.getForum(s.getParent()).getResponsibleMod().equals(u.getUsername())){
					
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
