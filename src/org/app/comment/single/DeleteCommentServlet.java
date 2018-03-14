package org.app.comment.single;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import javax.ws.rs.DELETE;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.app.data.database.Database;
import org.app.data.database.comments.DatabaseCommentService;
import org.app.data.entities.Comment;

@Path("/forums")
public class DeleteCommentServlet {

	//brisanje komentara
	@DELETE
	@Path("/{parent}/subjects/{title}/comments/{Id}/delete")
	@Produces(MediaType.APPLICATION_JSON)
	public Comment deleteComment(@PathParam("parent") String parent, @PathParam("title") String title,
			@PathParam("Id") long id){
		
		Comment c = null;
		try {
			c = DatabaseCommentService.getComment(URLDecoder.decode(parent, "UTF-8"), URLDecoder.decode(title, "UTF-8"), id);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(c!=null){
			
			c.setDeleted(true);
			Database.saveData();
			return c;
		}
		
		return null;
	}
}
