package org.app.comment.single.votes;

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
public class HateCommentServlet {
	
	//hejtovanje komentara
	@POST
	@Path("/{username}/forums/{parent}/subjects/{title}/comments/{id}/hate")
	@Produces(MediaType.APPLICATION_JSON)
	public String hateComment(@PathParam("username") String username,
			@PathParam("parent") String parent, @PathParam("title") String title, @PathParam("id") long id){
		
		Comment c = null;
		try {
			c = DatabaseCommentService.getComment(URLDecoder.decode(parent, "UTF-8"), URLDecoder.decode(title, "UTF-8"), id);
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
		
		if(!u.getLikeComm().contains(c) && !u.getHateComm().contains(c)){
			
			c.setHatesCount(c.getHatesCount()+1);
			u.getHateComm().add(c);
			
			Database.saveData();
			JSONObject obj = new JSONObject();
			try {
				obj.put("success", true);
				obj.put("url", "/WebApp/topic.html");
				obj.put("message", "OK");
				obj.put("ammount", c.getHatesCount());
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
			obj.put("message", "Content already disliked");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return obj.toString();
	}
	
	//provera je l vec ishejtovan komentar
	@GET
	@Path("/{username}/forums/{parent}/subjects/{title}/comments/{id}/hate")
	@Produces(MediaType.APPLICATION_JSON)
	public String getHateComment(@PathParam("username") String username,
			@PathParam("parent") String parent, @PathParam("title") String title, @PathParam("id") long id){
		
		Comment c = null;
		try {
			c = DatabaseCommentService.getComment(URLDecoder.decode(parent, "UTF-8"), URLDecoder.decode(title, "UTF-8"), id);
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
		
		if(getHCommentt(u,c)){
			
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
			obj.put("message", "Not disliked");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return obj.toString();
	}
	
	//da li je korisnik u bacio hejt na komentar
	public boolean getHCommentt(User u, Comment c){
		
		for(int i=0; i<u.getHateComm().size(); i++){
			if(u.getHateComm().get(i).getGrandparent().equals(c.getGrandparent())
					&& u.getHateComm().get(i).getParent().equals(c.getParent())
					&& u.getHateComm().get(i).getCommentId()==c.getCommentId())
				return true;
		}
		
		return false;
	}
}
