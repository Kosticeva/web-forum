package org.app.subject;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Collection;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.app.data.database.Database;
import org.app.data.entities.Subject;

@Path("/subjects")
public class SubjectsServlet {

	//dobavljanje svih tema
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<Subject> getSubjects(){
		
		ArrayList<Subject> subjects = new ArrayList<Subject>();
		for(Subject s: Database.subjects.keySet()){
			subjects.add(s);
		}
		
		return subjects;
	}
	
	//dobavljanje svih tema sa xx naslovom
	@GET
	@Path("/{title}")
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<Subject> getSubjects(@PathParam("title") String title){
		
		ArrayList<Subject> subjects = new ArrayList<Subject>();
		for(Subject s: Database.subjects.keySet()){
			try {
				if(s.getTitle().equals(URLDecoder.decode(title, "UTF-8"))){
					subjects.add(s);
				}
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return subjects;
	}
}
