package org.app.data.database.forums;

import org.app.data.database.Database;
import org.app.data.entities.Subforum;

public class DatabaseForumService {

	//dobavljanje foruma preko naslova iz baze
	public static Subforum getForum(String title){
		return Database.forums.get(title);
	}
	
	//provera podataka - da li je sve popunjeno
	public static boolean checkData(Subforum f){
		if(f==null){
			return false;
		}
		
		if(f.getTitle()!=null && f.getDescription()!=null && f.getImage()!=null 
				&& f.getRuleList()!=null && f.getResponsibleMod()!=null){
			
			if(!f.getTitle().equals("") && !f.getDescription().equals("") && !f.getImage().equals("")
					&& !f.getRuleList().equals("")){
				return true;
			}
		}
			
		return false;
	}
}
