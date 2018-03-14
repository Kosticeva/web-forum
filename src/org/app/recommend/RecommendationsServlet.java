package org.app.recommend;

import java.util.ArrayList;
import java.util.Set;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.app.data.database.Database;
import org.app.data.database.subjects.DatabaseSubjectService;
import org.app.data.database.users.DatabaseUserService;
import org.app.data.entities.Comment;
import org.app.data.entities.Subforum;
import org.app.data.entities.Subject;
import org.app.data.user_data.User;

@Path("/recommendations")
public class RecommendationsServlet {

	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<Subject> getRecommendations(User uu){
		
		User u = DatabaseUserService.getUser(uu.getUsername());
		
		if(u!=null){
			ArrayList<Subject> goal = new ArrayList<Subject>();
			Set<Subject> allSubjects = Database.subjects.keySet();
			
			ArrayList<Subforum> savedForums = u.getFollowedForums();
			
			//dodavanje svih tema snimljenih foruma
			for(int i=0; i<savedForums.size(); i++){
				for(Subject s: allSubjects){
					
					//teme istog foruma
					if(s.getParent().equals(savedForums.get(i).getTitle())){
						
						if(!goal.contains(s))
							goal.add(s);
					}
					
					//dela istog autora
					if(s.getAuthor().equals(savedForums.get(i).getResponsibleMod())){
						
						if(!goal.contains(s))
							goal.add(s);
					}
					
				}
			}
			
			ArrayList<Subject> savedSubjects = u.getSavedSubjects();
			
			//dodavanje svih tema prema temama
			for(int i=0; i<savedSubjects.size(); i++){
				for(Subject s: allSubjects){
					
					//bratske teme(teme koje pripadaju istom forumu)
					if(s.getParent().equals(savedSubjects.get(i).getParent())){
					
						if(!goal.contains(s))
							goal.add(s);
					}
					
					//dela istog autora
					if(s.getAuthor().equals(savedSubjects.get(i).getAuthor())){
						
						if(!goal.contains(s))
							goal.add(s);
					}
				}
			}
			
			ArrayList<Comment> savedComments = u.getSavedComments();
			
			for(int i=0; i<savedComments.size(); i++){
				for(Subject s: allSubjects){
					
					//bratske teme(teme koje pripadaju istom forumu)
					if(s.getParent().equals(savedComments.get(i).getGrandparent())){
					
						if(!goal.contains(s))
							goal.add(s);
					}
					
					//dela istog autora
					if(s.getAuthor().equals(savedComments.get(i).getAuthor())){
						
						if(!goal.contains(s))
							goal.add(s);
					}
				}
			}
			
			ArrayList<Subject> likedSubjects = u.getLikeSubj();
			
			//dodavanje svih tema prema lajkovima
			for(int i=0; i<likedSubjects.size(); i++){
				for(Subject s: allSubjects){
					
					//bratske teme
					if(s.getParent().equals(likedSubjects.get(i).getParent())){
						
						if(!goal.contains(s))
							goal.add(s);
					}
					
					//dela istog autora
					if(s.getAuthor().equals(likedSubjects.get(i).getAuthor())){
						
						if(!goal.contains(s))
							goal.add(s);
					}
				}
			}
			
			ArrayList<Comment> likedComments = u.getLikeComm();
			
			for(int i=0; i<likedComments.size(); i++){
				for(Subject s: allSubjects){
					
					//bratske teme(teme koje pripadaju istom forumu)
					if(s.getParent().equals(savedComments.get(i).getGrandparent())){
					
						if(!goal.contains(s))
							goal.add(s);
					}
					
					//dela istog autora
					if(s.getAuthor().equals(savedComments.get(i).getAuthor())){
						
						if(!goal.contains(s))
							goal.add(s);
					}
				}
			}
			
			ArrayList<Subject> hatedSubjects = u.getHateSubj();
			
			for(int i=0; i<hatedSubjects.size(); i++){
				
				if(goal.contains(hatedSubjects.get(i))){
					goal.remove(hatedSubjects.get(i));
				}
				
			}
			
			ArrayList<Comment> hatedComments = u.getHateComm();
			
			for(int i=0; i<hatedComments.size(); i++){
				
				Subject currSubj = DatabaseSubjectService.getSubject(hatedComments.get(i).getGrandparent(), hatedComments.get(i).getParent());
				
				if(goal.contains(currSubj)){
					goal.remove(currSubj);
				}
			}
			
			return goal;
		}
		
		return null;
	}
	
	
}
