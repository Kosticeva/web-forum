package org.app.comment.single;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.app.data.database.comments.DatabaseCommentService;
import org.app.data.entities.Comment;

@Path("/forums")
public class CommentGetterServlet {

	//dobavljanje komentara sa odg idjem
	@GET
	@Path("/{parent}/subjects/{title}/comments/{Id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Comment getConcreteComment(@PathParam("parent") String parent, 
			@PathParam("title") String title, @PathParam("Id") long id){
		
		try {
			return DatabaseCommentService.getComment(URLDecoder.decode(parent, "UTF-8"), URLDecoder.decode(title, "UTF-8"), id);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	//dobavljanje svih komentara odg komentara
	@GET
	@Path("/{parent}/subjects/{title}/comments/{Id}/comments")
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<Comment> getComments(@PathParam("parent") String parent, @PathParam("title") String title,
			@PathParam("Id") long id){
		
		Comment c = null;
		
		try {
			c = DatabaseCommentService.getComment(URLDecoder.decode(parent, "UTF-8"),URLDecoder.decode(title, "UTF-8"),id);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(c!=null){
			return c.getChildren();
		}
		
		return null;
	}
	
}
