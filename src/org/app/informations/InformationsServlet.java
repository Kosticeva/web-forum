package org.app.informations;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.app.data.database.Database;

@Path("/count")
public class InformationsServlet {

	@Path("/forums")
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String getForumCount(){
		return ""+Database.forums.size();
	}
	
	@Path("/subjects")
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String getSubjectCount(){
		return ""+Database.subjects.size();
	}
	
	@Path("/users")
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String getUserCount(){
		return ""+Database.users.size();
	}
	
}
