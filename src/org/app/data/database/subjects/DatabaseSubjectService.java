package org.app.data.database.subjects;

import java.util.ArrayList;

import org.app.data.database.Database;
import org.app.data.database.forums.DatabaseForumService;
import org.app.data.entities.Subforum;
import org.app.data.entities.Subject;

public class DatabaseSubjectService {

	//inicijalizovanje idjeva za komentare teme
	public static void putSubject(Subject s){
		Database.subjects.put(s, DatabaseForumService.getForum(s.getParent()));
		Database.commentIds.put(s, 0L);
		Database.saveData();
	}
	
	//dobavljanje odg teme iz baze
	public static Subject getSubject(String ftitle, String stitle){
		if(Database.forums.containsKey(ftitle)){
			
			Subforum f = Database.forums.get(ftitle);
			
			for(Subject s: Database.subjects.keySet()){
				
				if(s.getTitle().equals(stitle)){
					Subforum parent = Database.subjects.get(s);
					if(parent.getTitle().equals(f.getTitle())){
						return s;
					}
				}
			}
		}
		
		return null;
	}
	
	//dobavljanje svih tema jednog foruma iz baze
	public static ArrayList<Subject> getSubjects(String title){
		
		Subforum f = DatabaseForumService.getForum(title);
		
		if(f!=null){
			ArrayList<Subject> goal = new ArrayList<Subject>();
			
			for(Subject s : Database.subjects.keySet()){
			
				if(s.getParent().equals(f.getTitle())){
					goal.add(s);
				}
			}
			
			return goal;
		}
		
		return null;
	}
	
}
