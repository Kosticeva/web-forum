package org.app.data.database.subjects;

import org.app.data.database.Database;
import org.app.data.database.forums.DatabaseForumService;
import org.app.data.entities.Subforum;
import org.app.data.entities.Subject;
import org.app.data.user_data.User;

public class SubjectManipulationService {

	//dodavanje teme u bazu
	public static Subject addSubject(Subject s){
		
		Subforum f = DatabaseForumService.getForum(s.getParent());
		if(f!=null){
			Subject ss = DatabaseSubjectService.getSubject(s.getParent(), s.getTitle());
			if(ss==null){
				DatabaseSubjectService.putSubject(s);
				return s;
			}
			
			return null;
		}
		
		return null;
	}

	//brisanje teme iz baze
	public static Subject removeSubject(Subject s){
		
		if(Database.subjects.containsKey(s)){
			Database.subjects.remove(s);
		}
		
		for(User uss: Database.users.values()){
			uss.getSavedSubjects().remove(s);
		}
		
		if(Database.subjects.containsKey(s)){
			Database.subjects.remove(s);
			Database.commentIds.remove(s);
		}
		
		Database.saveData();
		return s;
	}
}
