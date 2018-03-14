package org.app.users.votes.comments;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Collection;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import org.app.data.database.users.DatabaseUserService;
import org.app.data.entities.Comment;

@Path("/users")
public class CommentVotesService {

	//dobavljanje svih lajkovanih komentara
	@GET
	@Path("/{username}/likes/comments")
	public Collection<Comment> getLikesC(@PathParam("username") String username){
		
		try {
			return DatabaseUserService.getUser(URLDecoder.decode(username, "UTF-8")).getLikeComm();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	//dobavljanje svih hejtovanih komentara
	@GET
	@Path("/{username}/hates/comments")
	public Collection<Comment> getHatesC(@PathParam("username") String username){
		
		try {
			return DatabaseUserService.getUser(URLDecoder.decode(username, "UTF-8")).getHateComm();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
}
