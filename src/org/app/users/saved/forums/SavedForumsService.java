package org.app.users.saved.forums;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Collection;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.app.data.database.users.DatabaseUserService;
import org.app.data.entities.Subforum;
import org.app.data.user_data.User;

@Path("/users")
public class SavedForumsService {

	//dobavljanje snimljenog foruma
	@GET
	@Path("/{username}/forums/{forumTitle}")
	@Produces(MediaType.APPLICATION_JSON)
	public Subforum getUserForum(@PathParam("username") String username, @PathParam("forumTitle") String title){
		
		User u = null;
		try {
			u = DatabaseUserService.getUser(URLDecoder.decode(username, "UTF-8"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ArrayList<Subforum> forums = null;
		
		if(u!=null)
		{
			forums = u.getFollowedForums();
		}
		
		if(forums!=null){
			
			for(int i=0; i<forums.size(); i++){
				try {
					if(forums.get(i).getTitle().equals(URLDecoder.decode(title, "UTF-8"))){
						return forums.get(i);
					}
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		}
		
		return null;
	}
	
	//dobavljanje snimljenih foruma
	@GET
	@Path("/{username}/forums")
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<Subforum> getSavedForums(@PathParam("username") String username){
		
		User u = null;
		try {
			u = DatabaseUserService.getUser(URLDecoder.decode(username, "UTF-8"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(u!=null)
		{
			return u.getFollowedForums();
		}
		
		return null;
	}
	
}
