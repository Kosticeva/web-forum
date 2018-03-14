package org.app.subject.single.votes;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.app.data.database.Database;
import org.app.data.database.subjects.DatabaseSubjectService;
import org.app.data.database.users.DatabaseUserService;
import org.app.data.entities.Subject;
import org.app.data.user_data.User;
import org.json.JSONException;
import org.json.JSONObject;

@Path("/users")
public class LikeSubjectServlet {

	//lajkovanje teme
	@POST
	@Path("/{username}/forums/{parent}/subjects/{title}/like")
	@Produces(MediaType.APPLICATION_JSON)
	public String likeSubject(@PathParam("username") String username,
			@PathParam("parent") String parent, @PathParam("title") String title){
		
		Subject s = null;
		try {
			s = DatabaseSubjectService.getSubject(URLDecoder.decode(parent, "UTF-8"), URLDecoder.decode(title, "UTF-8"));
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
		
		if(!u.getLikeSubj().contains(s) && !u.getHateSubj().contains(s)){
			
			s.setLikesCount(s.getLikesCount()+1);
			u.getLikeSubj().add(s);
			
			Database.saveData();
			JSONObject obj = new JSONObject();
			try {
				obj.put("success", true);
				obj.put("url", "/WebApp/topic.html");
				obj.put("message", "OK");
				obj.put("ammount", s.getLikesCount());
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return obj.toString();
		}
		
		JSONObject obj = new JSONObject();
		try {
			obj.put("success", false);
			obj.put("url", "no");
			obj.put("message", "Content already liked");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return obj.toString();
	}
	
	//provera da li je tema lajkovana
	@GET
	@Path("/{username}/forums/{parent}/subjects/{title}/like")
	@Produces(MediaType.APPLICATION_JSON)
	public String getLikeSubject(@PathParam("username") String username,
			@PathParam("parent") String parent, @PathParam("title") String title){
		

		Subject s = null;
		try {
			s = DatabaseSubjectService.getSubject(URLDecoder.decode(parent, "UTF-8"), URLDecoder.decode(title, "UTF-8"));
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
		
		if(u.getLikeSubj().contains(s)){
			
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
		}
		
		JSONObject obj = new JSONObject();
		try {
			obj.put("success", true);
			obj.put("url", "no");
			obj.put("message", "Not liked");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return obj.toString();
	}
	
}
