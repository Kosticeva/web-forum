package org.app.search.advanced.entity;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;

import org.app.data.database.Database;
import org.app.data.user_data.User;
import org.app.search.advanced.AdvancedSearchQuery;

public class AdvancedSearchUserEngine {

	//napredna pretraga po korisnicima
	public void searchForUsers(AdvancedSearchQuery query, ArrayList<String> results){
		
		if(query.getUserQuery()!=null){
			
			for(User u : Database.users.values()){
					
				String url = null;
				try {
					url = "<a class=\"userR\" href=\"/WebApp/app/users/"+URLEncoder.encode(u.getUsername(), "UTF-8")+"\">"+u.getUsername()+"</a>";
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				if(url!=null){
					
					if(u.getUsername().toLowerCase().contains(query.getUserQuery().toLowerCase()) ||
							query.getUserQuery().toLowerCase().contains(u.getUsername().toLowerCase())){
						
						if(!results.contains(url)){
							results.add(url);
						}
					}
				}
			}
		}
		
	}
}
