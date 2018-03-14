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
public class LikeCommentServlet {

	//lajkovanje komentara
	@POST
	@Path("/{username}/forums/{parent}/subjects/{title}/comments/{id}/like")
	@Produces(MediaType.APPLICATION_JSON)
	public String likeComment(@PathParam("username") String username,
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
			
			c.setLikesCount(c.getLikesCount()+1);
			u.getLikeComm().add(c);
			
			Database.saveData();
			
			JSONObject obj = new JSONObject();
			try {
				obj.put("success", true);
				obj.put("url", "/WebApp/topic.html");
				obj.put("message", "OK");
				obj.put("ammount", c.getLikesCount());
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
	
	//da li je lajkovan komentar od strane odr korisnika
	@GET
	@Path("/{username}/forums/{parent}/subjects/{title}/comments/{id}/like")
	@Produces(MediaType.APPLICATION_JSON)
	public String getLikeComment(@PathParam("username") String username,
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
		
		if(getLCommentt(u, c)){
			
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
	
	//provera da li je lajkovan komentar od str korisnika
	public boolean getLCommentt(User u, Comment c){
		
		for(int i=0; i<u.getLikeComm().size(); i++){
			if(u.getLikeComm().get(i).getGrandparent().equals(c.getGrandparent())
					&& u.getLikeComm().get(i).getParent().equals(c.getParent())
					&& u.getLikeComm().get(i).getCommentId()==c.getCommentId())
				return true;
		}
		
		return false;
	}
	
}
