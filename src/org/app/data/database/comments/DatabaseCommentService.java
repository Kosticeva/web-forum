package org.app.data.database.comments;

import org.app.data.database.Database;
import org.app.data.database.subjects.DatabaseSubjectService;
import org.app.data.entities.Comment;
import org.app.data.entities.Subject;

public class DatabaseCommentService {

	//dobavljanje idja za novi komentar
	public static long getCommentId(String parent, String title){
		
		Subject s = DatabaseSubjectService.getSubject(parent, title);
		long retVal = getCommentFromSubject(s);
		Database.commentIds.put(s, (retVal+1));
		Database.saveData();
		return retVal;
	}
	
	//dobavljanje sledeceg idja za komentar
	public static Long getCommentFromSubject(Subject s){
		
		for(Subject ss : Database.commentIds.keySet()){
			if(ss.getTitle().equals(s.getTitle()) && ss.getParent().equals(s.getParent())){
				return Database.commentIds.get(ss);
			}
		}
		
		return null;
		
	}
	
	//nalazenje komentara u bazi
	public static Comment getComment(String ftitle, String stitle, long commentId){
		
		if(Database.forums.containsKey(ftitle)){
			
			Subject s = DatabaseSubjectService.getSubject(ftitle, stitle);
				
			Long commId = getCommentFromSubject(s);
			
			if(s!=null && commentId<commId){
				for(int i=0; i<s.getComments().size(); i++){
					if(s.getComments().get(i).getCommentId()==commentId){
						return s.getComments().get(i);
					}
				}
				
				Comment goal = null;
				for(int i=0; i<s.getComments().size(); i++){
					goal = getCommentComment(s.getComments().get(i), commentId);
					if(goal!=null)
						break;
				}
				
				return goal;
			}
			
		}
		
		return null;
	}
	
	//nalazenje komentara pod komentarom
	public static Comment getCommentComment(Comment c, long id){
		
		Comment goal = null;
		for(int j=0; j<c.getChildren().size(); j++){
			if(c.getChildren().get(j).getCommentId()==id){
				return c.getChildren().get(j);
			}else{
				goal = getCommentComment(c.getChildren().get(j), id);
				if(goal!=null)
					return goal;
			}
		}
		
		return null;
	}
	
}
