package org.app.users.saved.subjects;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Collection;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.app.data.database.subjects.DatabaseSubjectService;
import org.app.data.database.users.DatabaseUserService;
import org.app.data.entities.Subforum;
import org.app.data.entities.Subject;
import org.app.data.user_data.User;
import org.app.users.saved.forums.SavedForumsService;

@Path("/users")
public class SavedSubjectsService {

	public SavedForumsService serviser = new SavedForumsService();
	
	//dobavljanje konkretne snimljene teme
	@GET
	@Path("/{username}/forums/{parent}/subjects/{title}")
	@Produces(MediaType.APPLICATION_JSON)
	public Subject getConcreteSubject(@PathParam("username") String username, 
			@PathParam("parent") String parent, @PathParam("title") String title){
		
		Subforum f = null;
		try {
			f = serviser.getUserForum(URLDecoder.decode(username, "UTF-8"), URLDecoder.decode(parent, "UTF-8"));
			
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(f!=null){
			
			Subject goal = DatabaseSubjectService.getSubject(parent, title);
			return goal;
		}
		
		return null;
	}
	
	//dobavljanje sacuvanih tema sa datim imenom
	@GET
	@Path("/{username}/subjects/{title}")
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<Subject> getNotConcreteSubjects(@PathParam("username") String username, 
			@PathParam("title") String title){
		
		User u = null;
		try {
			u = DatabaseUserService.getUser(URLDecoder.decode(username, "UTF-8"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		ArrayList<Subject> allSubj = null;
		if(u!=null)
		{
			allSubj =  u.getSavedSubjects();
		}
		
		if(allSubj!=null){
			
			ArrayList<Subject> toReturn = new ArrayList<Subject>();
			
			if(allSubj!=null){
				for(int i=0; i<allSubj.size(); i++){
					try {
						if(allSubj.get(i).getTitle().equals(URLDecoder.decode(title, "UTF-8"))){
							toReturn.add(allSubj.get(i));
						}
					} catch (UnsupportedEncodingException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			
			return toReturn;
		}
		
		return null;
		
	}
	
	//dobavljanje svih sacuvanih tema
	@GET
	@Path("/{username}/subjects")
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<Subject> getSavedSubjects(@PathParam("username") String username){
		
		User u = null;
		try {
			u = DatabaseUserService.getUser(URLDecoder.decode(username, "UTF-8"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(u!=null)
		{
			return u.getSavedSubjects();
		}
		
		return null;
	}
}
