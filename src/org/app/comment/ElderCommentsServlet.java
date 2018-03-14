package org.app.comment;

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
import org.app.data.database.forums.DatabaseForumService;
import org.app.data.database.subjects.DatabaseSubjectService;
import org.app.data.entities.Comment;
import org.app.data.entities.Subforum;
import org.app.data.entities.Subject;

@Path("/forums")
public class ElderCommentsServlet {

	//Metoda za dobavljanje svih comm jednog foruma
	@GET
	@Path("/{parent}/comments")
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<Comment> getForumComments(@PathParam("parent") String parent){
		
		ArrayList<Comment> comments = new ArrayList<Comment>();
		Subforum f = null;
		try {
			f = DatabaseForumService.getForum(URLDecoder.decode(parent, "UTF-8"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(f!=null){
			ArrayList<Subject> subjects = new ArrayList<Subject>();
			for(Subject s: Database.subjects.keySet()){
				if(s.getParent().equals(f)){
					subjects.add(s);
				}
			}
			
			for(int i=0; i<subjects.size(); i++){
				for(int j=0; j<subjects.get(i).getComments().size(); j++){
					comments.add(subjects.get(i).getComments().get(j));
				}
			}
		
			return comments;
		}
		
		return null;
	}
	
	//Metoda za dobavljanje svih comm odgovarajuceg idja jednog foruma
	@GET
	@Path("/{parent}/comments/{Id}")
	public Collection<Comment> getForumCommentss(@PathParam("parent") String parent, @PathParam("Id") long id){
		
		ArrayList<Comment> comments = new ArrayList<Comment>();
		Subforum f = null;
		try {
			f = DatabaseForumService.getForum(URLDecoder.decode(parent, "UTF-8"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(f!=null){
			ArrayList<Subject> subjects = new ArrayList<Subject>();
			for(Subject s: Database.subjects.keySet()){
				if(s.getParent().equals(f)){
					subjects.add(s);
				}
			}
			
			for(int i=0; i<subjects.size(); i++){
				for(int j=0; j<subjects.get(i).getComments().size(); j++){
					if(subjects.get(i).getComments().get(j).getCommentId()==id)
						comments.add(subjects.get(i).getComments().get(j));
				}
			}
			
			return comments;
		}
		
		return null;
	}
	
	//Metoda za dobavljanje svih comm jedne teme
	@GET
	@Path("/{parent}/subjects/{title}/comments")
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<Comment> getSubjectComments(@PathParam("parent") String parent, @PathParam("title") String title){
		
		Subject s = null;
		try {
			s = DatabaseSubjectService.getSubject(URLDecoder.decode(parent, "UTF-8"),URLDecoder.decode(title, "UTF-8"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(s!=null){
			ArrayList<Comment> commentss = s.getComments();
			return commentss;
		}
		
		return null;
	}
}
