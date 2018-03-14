package org.app.users.votes.subjects;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Collection;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.app.data.database.users.DatabaseUserService;
import org.app.data.entities.Subject;

@Path("/users")
public class SubjectVotesService {

	//dobavljanje svih lajkovanih tema
	@GET
	@Path("/{username}/likes/subjects")
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<Subject> getLikesS(@PathParam("username") String username){
		
		try {
			return DatabaseUserService.getUser(URLDecoder.decode(username, "UTF-8")).getLikeSubj();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	//dobavljanje svih hejtovanih tema
	@GET
	@Path("/{username}/hates/subjects")
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<Subject> getHatesS(@PathParam("username") String username){
		
		try {
			return DatabaseUserService.getUser(URLDecoder.decode(username, "UTF-8")).getHateSubj();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
}
