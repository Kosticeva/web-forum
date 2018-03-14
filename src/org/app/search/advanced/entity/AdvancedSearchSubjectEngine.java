package org.app.search.advanced.entity;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;

import org.app.data.database.Database;
import org.app.data.entities.Subject;
import org.app.search.advanced.AdvancedSearchQuery;

public class AdvancedSearchSubjectEngine {

	//napredna pretraga po temama
	public void searchForSubjects(AdvancedSearchQuery query, ArrayList<String> results){
		
		if(query.getSubjQuery()!=null){
			
			for(Subject s : Database.subjects.keySet()){
				
				String url = null;
				try {
					url = "<a class=\"subjectR\" href=\"/WebApp/app/forums/"+URLEncoder.encode(s.getParent(), "UTF-8")+"/subjects/"+URLEncoder.encode(s.getTitle(), "UTF-8")+"\">"+s.getTitle()+"</a>";
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				if(url!=null){
					
					if(query.isSubjTitle()){
					
						if(s.getTitle().toLowerCase().contains(query.getSubjQuery().toLowerCase()) ||
								query.getSubjQuery().toLowerCase().contains(s.getTitle().toLowerCase())){
							
							if(!results.contains(url))
								results.add(url);
						}
					}
					
					if(query.isSubjAuthor()){
						
						if(s.getAuthor().toLowerCase().equals(query.getSubjQuery().toLowerCase())){
							
							if(!results.contains(url))
								results.add(url);
						}
					}
							
					if(query.isSubjContent()){
						
						if(s.getContent().toLowerCase().contains(query.getSubjQuery().toLowerCase()) ||
								query.getSubjQuery().toLowerCase().contains(s.getContent().toLowerCase())){
							
							if(!results.contains(url))
								results.add(url);
						}
					}
					
					if(query.isSubjParent()){
						
						if(s.getParent().toLowerCase().contains(query.getSubjQuery().toLowerCase())
								|| query.getSubjQuery().toLowerCase().contains(s.getParent().toLowerCase())){
							
							if(!results.contains(url))
								results.add(url);
						}
					}
				}
			}
		}
	}
	
}
