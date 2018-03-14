package org.app.data.database.users;

import org.app.data.database.Database;
import org.app.data.user_data.User;

public class DatabaseUserService {

	//dobavljanje usera iz baze
	public static User getUser(String username){
		return Database.users.get(username);
	}
	
	//dodavanje usera u bazu
	public static User addUser(User u){
		
		if(u.getUsername()!=null && u.getName()!=null &&
				u.getSurname()!=null && u.getEmail()!=null &&
				u.getPassword()!=null && u.getPhoneNum()!=null){
		
			if(!Database.users.containsKey(u.getUsername())){
				Database.users.put(u.getUsername(), u);
				return u;
			}
		}
		
		return null;
	}
	
	//provera poklapanja sifre i korisnickog imena
	public static User checkPassword(String username, String password){
		if(Database.users.containsKey(username)){
			User u = Database.users.get(username);
			if(u.getPassword().equals(password)){
				return u;
			}
		}
		
		return null;
	}
}
