package org.app.subject.single;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import javax.ws.rs.DELETE;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.app.data.database.subjects.DatabaseSubjectService;
import org.app.data.database.subjects.SubjectManipulationService;
import org.app.data.entities.Subject;

@Path("/forums")
public class DeleteSubjectServlet {

	//brisanje teme
	@DELETE
	@Path("/{parent}/subjects/{title}/delete")
	@Produces(MediaType.APPLICATION_JSON)
	public Subject deleteSubject(@PathParam("parent") String parent, @PathParam("title") String title){
		
		Subject s = null;
		try {
			s = DatabaseSubjectService.getSubject(URLDecoder.decode(parent, "UTF-8"), URLDecoder.decode(title, "UTF-8"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(s!=null){
			s = SubjectManipulationService.removeSubject(s);
			return s;
		}
		
		return null;
	}
}
