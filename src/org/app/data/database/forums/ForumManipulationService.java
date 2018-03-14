package org.app.data.database.forums;

import org.app.data.database.Database;
import org.app.data.database.users.DatabaseUserService;
import org.app.data.entities.Subforum;
import org.app.data.entities.Subject;
import org.app.data.user_data.User;

public class ForumManipulationService {

	//dodavanje foruma u bazu
	public static Subforum addForum(Subforum f){
		if(DatabaseForumService.checkData(f)){
			if(DatabaseUserService.getUser(f.getResponsibleMod()).getType().equals("Regular")){
				return null;
			}
			if(Database.forums.containsKey(f.getTitle())){
				return null;
			}
			Database.forums.put(f.getTitle(), f);
			return f;
		}
		
		return null;
	}
	
	//brisanje foruma iz baze
	public static Subforum removeForum(String title){
		
		Subforum f = null;
		if(Database.forums.containsKey(title)){
			f = Database.forums.remove(title);
			
			for(User uss: Database.users.values()){
				uss.getFollowedForums().remove(f);
			}
			
			for(Subject s: Database.subjects.keySet()){
				if(s.getParent().equals(f)){
					Database.subjects.remove(s);
					Database.commentIds.remove(s);
				}
			}
		}
		
		Database.saveData();
		return f;
	}
	
}
