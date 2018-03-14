package org.app.comment;

import java.util.ArrayList;
import java.util.Collection;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.app.data.database.Database;
import org.app.data.entities.Comment;
import org.app.data.entities.Subject;

@Path("/comments")
public class CommentsServlet {

	//Metoda za dobavljanje svih komentara
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<Comment> getAllComments(){
		ArrayList<Comment> comments = new ArrayList<Comment>();
		
		ArrayList<Subject> subjects = new ArrayList<Subject>();
		for(Subject s : Database.subjects.keySet()){
			subjects.add(s);
		}
		
		for(int i=0; i<subjects.size(); i++){
			ArrayList<Comment> scomm = subjects.get(i).getComments();
			for(int j=0; j<scomm.size(); i++){
				comments.add(scomm.get(i));
			}
		}
		
		return comments;
	}
	
	//Metoda za dobavljanje svih komentara sa odg ID-jem
	@GET
	@Path("/{Id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<Comment> getIdComments(@PathParam("Id") long id){
		ArrayList<Comment> comments = new ArrayList<Comment>();
		
		for(Subject s : Database.subjects.keySet()){
		
			for(int i=0; i<s.getComments().size(); i++){
				if(s.getComments().get(i).getCommentId()==id){
					comments.add(s.getComments().get(i));
				}
			}
		}
		
		return comments;
	}
	
}
