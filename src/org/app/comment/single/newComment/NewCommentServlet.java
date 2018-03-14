package org.app.comment.single.newComment;

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
import org.app.data.database.subjects.DatabaseSubjectService;
import org.app.data.database.users.DatabaseUserService;
import org.app.data.entities.Comment;
import org.app.data.entities.Subject;
import org.json.JSONException;
import org.json.JSONObject;

@Path("/forums")
public class NewCommentServlet {
	
	//Metoda za postavljanje novog komentara na temu
	@POST
	@Path("/{parent}/subjects/{title}/comments")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String addComment(@PathParam("parent") String parent, @PathParam("title") String title, Comment c){
		
		if(DatabaseUserService.getUser(c.getAuthor())!=null)
			c.setAuthor(c.getAuthor());
		
		if(DatabaseSubjectService.getSubject(parent, title)!=null)
			c.setParent(title);
		
		if(DatabaseForumService.getForum(parent)!=null)
			c.setGrandparent(parent);
		
		Subject s = null;
		try {
			s = DatabaseSubjectService.getSubject(URLDecoder.decode(parent,"UTF-8"), URLDecoder.decode(title, "UTF-8"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(s!=null){
			c.setCommentId();
			if(c.getParentComment()>-1){
				DatabaseCommentService.getComment(c.getGrandparent(), c.getParent(), c.getParentComment()).getChildren().add(c);
			}else{
				s.getComments().add(c);
			}
				
			Database.saveData();
			JSONObject obj = new JSONObject();
			try {
				obj.put("success", true);
				obj.put("url", "/WebApp/app/forums/"+DatabaseForumService.getForum(s.getParent()).getUrl()+"/subjects/"+s.getUrl());
				obj.put("message", "OK");
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return obj.toString();
			
		}else{
			
			JSONObject obj = new JSONObject();
			try {
				obj.put("success", false);
				obj.put("url", "no");
				obj.put("message", "Unable to add comment");
			} catch (JSONException e) {
				e.printStackTrace();
			}
			
			return obj.toString();
			
		}
		
	}
}
