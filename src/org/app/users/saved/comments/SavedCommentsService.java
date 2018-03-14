package org.app.users.saved.comments;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Collection;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.app.data.database.users.DatabaseUserService;
import org.app.data.entities.Comment;

@Path("/users")
public class SavedCommentsService {

	//dobavljanje sacuvanih komentara
	@GET
	@Path("/{username}/comments")
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<Comment> getSavedComments(@PathParam("username") String username){
		
		try {
			return DatabaseUserService.getUser(URLDecoder.decode(username, "UTF-8")).getSavedComments();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
}
