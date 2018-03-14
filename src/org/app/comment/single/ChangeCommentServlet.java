package org.app.comment.single;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.app.data.database.Database;
import org.app.data.database.comments.DatabaseCommentService;
import org.app.data.entities.Comment;
import org.json.JSONException;
import org.json.JSONObject;

@Path("/forums")
public class ChangeCommentServlet {

	//izmena komentara
	@PUT
	@Path("/{parent}/subjects/{title}/comments/{id}/change")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String changeComment(@PathParam("parent") String parent, 
			@PathParam("title") String title, @PathParam("id") long id, Comment c){
		
		Comment real = null;
		try {
			real = DatabaseCommentService.getComment(URLDecoder.decode(parent, "UTF-8"), URLDecoder.decode(title, "UTF-8"), id);
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		 
		if(real!=null){
			
			real.setContent(c.getContent());
			
			if(c.getAuthor().equals(real.getAuthor()))
				real.setDirty(true);
			
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
			
		}else{
			
			JSONObject obj = new JSONObject();
			try {
				obj.put("success", false);
				obj.put("url", "/WebApp/topic.html");
				obj.put("message", "Unable to edit comment");
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return obj.toString();
			
		}
		
	}
}
