package org.app.search.entity;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;

import org.app.data.database.Database;
import org.app.data.user_data.User;

public class SearchUserEngine {

	//pretraga korisnika
	public void searchForUsers(ArrayList<String> urls, String query){
		
		for(User u : Database.users.values()){
			
			if(u.getUsername().toLowerCase().contains(query.toLowerCase()) ||
					query.toLowerCase().contains(u.getUsername().toLowerCase())){
				
				try {
					urls.add("<a class=\"userR\" href=\"/WebApp/app/users/"+URLEncoder.encode(u.getUsername(), "UTF-8")+"\">"+u.getUsername()+"</a>");
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}/*else if(u.getEmail().toLowerCase().contains(query.toLowerCase()) ||
					query.toLowerCase().contains(u.getEmail().toLowerCase())){
				
				try {
					urls.add("<li><a class=\"userR\" href=\"/WebApp/app/users/"+URLEncoder.encode(u.getUsername(), "UTF-8")+"\">"+u.getUsername()+"</a></li>");
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}else if(u.getName().toLowerCase().contains(query.toLowerCase()) ||
					query.toLowerCase().contains(u.getName().toLowerCase())){
				
				try {
					urls.add("<li><a class=\"userR\" href=\"/WebApp/app/users/"+URLEncoder.encode(u.getUsername(), "UTF-8")+"\">"+u.getUsername()+"</a></li>");
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}else if(u.getSurname().toLowerCase().contains(query.toLowerCase()) ||
					query.toLowerCase().contains(u.getSurname().toLowerCase())){
				
				try {
					urls.add("<li><a class=\"userR\" href=\"/WebApp/app/users/"+URLEncoder.encode(u.getUsername(), "UTF-8")+"\">"+u.getUsername()+"</a></li>");
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}else if(u.getType().toLowerCase().equals(query.toLowerCase())){
				
				try {
					urls.add("<li><a class=\"userR\" href=\"/WebApp/app/users/"+URLEncoder.encode(u.getUsername(), "UTF-8")+"\">"+u.getUsername()+"</a></li>");
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}else if(u.getRegistrationDate().toLowerCase().equals(query.toLowerCase())){
				
				try {
					urls.add("<li><a class=\"userR\" href=\"/WebApp/app/users/"+URLEncoder.encode(u.getUsername(), "UTF-8")+"\">"+u.getUsername()+"</a></li>");
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}*/
		}
	}
	
}
