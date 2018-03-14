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
public class Comment2CommentServlet {
	
	//Metoda za postavljanje novog komentara na komentar
	@POST
	@Path("/{parent}/subjects/{title}/comments/{Id}/comments")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String addCommentToComment(@PathParam("parent") String parent, @PathParam("title") String title,
			@PathParam("Id") long id, Comment c){
		
		if(DatabaseUserService.getUser(c.getAuthor())!=null)
			c.setAuthor(c.getAuthor());
		
		try {
			if(DatabaseSubjectService.getSubject(URLDecoder.decode(parent, "UTF-8"),URLDecoder.decode(title, "UTF-8"))!=null)
				c.setParent(title);
		} catch (UnsupportedEncodingException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		
		try {
			if(DatabaseForumService.getForum(URLDecoder.decode(parent, "UTF-8"))!=null)
				c.setGrandparent(parent);
		} catch (UnsupportedEncodingException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		
		Subject s = null;
		
		try {
			s = DatabaseSubjectService.getSubject(URLDecoder.decode(parent,"UTF-8"), URLDecoder.decode(title, "UTF-8"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(s!=null){
			
			Comment cc = null;
			try {
				cc = DatabaseCommentService.getComment(URLDecoder.decode(parent,"UTF-8"), URLDecoder.decode(title, "UTF-8"), id);
			} catch (UnsupportedEncodingException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			if(cc!=null){

				c.setCommentId();
				cc.getChildren().add(c);
				
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
