package org.app.search.entity;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;

import org.app.data.database.Database;
import org.app.data.entities.Subject;

public class SearchSubjectEngine {

	//pretraga tema
	public void searchForSubjects(ArrayList<String> urls, String query){
		
		for(Subject s : Database.subjects.keySet()){
			
			if(s.getTitle().toLowerCase().contains(query.toLowerCase()) ||
					query.toLowerCase().contains(s.getTitle().toLowerCase())){
				
				try {
					urls.add("<a class=\"subjectR\" href=\"/WebApp/app/forums/"+URLEncoder.encode(s.getParent(), "UTF-8")+"/subjects/"+URLEncoder.encode(s.getTitle(), "UTF-8")+"\">"+s.getTitle()+"</a>");
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}else if(s.getAuthor().toLowerCase().equals(query.toLowerCase())){
				
				try {
					urls.add("<a class=\"subjectR\" href=\"/WebApp/app/forums/"+URLEncoder.encode(s.getParent(), "UTF-8")+"/subjects/"+URLEncoder.encode(s.getTitle(), "UTF-8")+"\">"+s.getTitle()+"</a>");
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}else if(s.getContent().toLowerCase().contains(query.toLowerCase()) ||
					query.toLowerCase().contains(s.getContent().toLowerCase())){
				
				try {
					urls.add("<a class=\"subjectR\" href=\"/WebApp/app/forums/"+URLEncoder.encode(s.getParent(), "UTF-8")+"/subjects/"+URLEncoder.encode(s.getTitle(), "UTF-8")+"\">"+s.getTitle()+"</a>");
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			/*}else if(s.getType().toLowerCase().equals(query.toLowerCase())){
				
				try {
					urls.add("<li><a class=\"subjectR\" href=\"/WebApp/app/forums/"+URLEncoder.encode(s.getParent(), "UTF-8")+"/subjects/"+URLEncoder.encode(s.getTitle(), "UTF-8")+"\">"+s.getTitle()+"</a></li>");
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			*/}else if(s.getParent().toLowerCase().contains(query.toLowerCase())
					|| query.toLowerCase().contains(s.getParent().toLowerCase())){
				
				try {
					urls.add("<a class=\"subjectR\" href=\"/WebApp/app/forums/"+URLEncoder.encode(s.getParent(), "UTF-8")+"/subjects/"+URLEncoder.encode(s.getTitle(), "UTF-8")+"\">"+s.getTitle()+"</a>");
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			/*}else if(s.getCreationDate().toLowerCase().equals(query.toLowerCase())){
				
				try {
					urls.add("<li><a class=\"subjectR\" href=\"/WebApp/app/forums/"+URLEncoder.encode(s.getParent(), "UTF-8")+"/subjects/"+URLEncoder.encode(s.getTitle(), "UTF-8")+"\">"+s.getTitle()+"</a></li>");
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}*/
			}
		}
	}
	
}
