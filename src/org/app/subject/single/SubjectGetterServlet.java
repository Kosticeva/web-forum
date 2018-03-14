package org.app.subject.single;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Collection;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.app.data.database.subjects.DatabaseSubjectService;
import org.app.data.entities.Subject;

@Path("/forums")
public class SubjectGetterServlet {

	//dobavljanje svih tema jednog foruma
	@GET
	@Path("/{title}/subjects")
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<Subject> getSubjects(@PathParam("title") String title){
		
		try {
			return DatabaseSubjectService.getSubjects(URLDecoder.decode(title, "UTF-8"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	//dobavljanje odg teme odg foruma
	@GET
	@Path("/{parent}/subjects/{title}")
	@Produces(MediaType.APPLICATION_JSON)
	public Subject getSubject(@PathParam("parent") String parent, @PathParam("title") String title){
		
		try {
			return DatabaseSubjectService.getSubject(URLDecoder.decode(parent, "UTF-8"), URLDecoder.decode(title, "UTF-8"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
}
