package org.app.forum.single;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Collection;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.app.data.database.Database;
import org.app.data.database.forums.DatabaseForumService;
import org.app.data.entities.Subforum;

@Path("/forums")
public class ForumGetterServlet {

	//dobavljanje svih foruma
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<Subforum> getForums(){
		return Database.forums.values();
	}
	
	//dobavljanje foruma sa naslovom x
	@GET
	@Path("/{forumTitle}")
	@Produces(MediaType.APPLICATION_JSON)
	public Subforum getForum(@PathParam("forumTitle") String title){
		try {
			return DatabaseForumService.getForum(URLDecoder.decode(title, "UTF-8"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
}
