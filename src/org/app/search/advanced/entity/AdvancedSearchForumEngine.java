package org.app.search.advanced.entity;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;

import org.app.data.database.Database;
import org.app.data.entities.Subforum;
import org.app.search.advanced.AdvancedSearchQuery;

public class AdvancedSearchForumEngine {

	//napredna pretraga foruma
	public void searchForForums(AdvancedSearchQuery query, ArrayList<String> results){
		
		if(query.getForumQuery()!=null){
			
			for(Subforum f: Database.forums.values()){
				
				String url = null;
				try {
					url = "<a class=\"forumR\" href=\"/WebApp/app/forums/"+URLEncoder.encode(f.getTitle(), "UTF-8")+"\">"+f.getTitle()+"</a>";
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				if(url!=null){
					
					if(query.isForumTitle()){
					
						if(f.getTitle().toLowerCase().contains(query.getForumQuery().toLowerCase()) ||
								query.getForumQuery().toLowerCase().contains(f.getTitle().toLowerCase())){
							
							if(!results.contains(url))
								results.add(url);
						}
					}
					
					if(query.isForumDescription()){
						
						if(f.getDescription().toLowerCase().contains(query.getForumQuery().toLowerCase()) ||
								query.getForumQuery().toLowerCase().contains(f.getDescription().toLowerCase())){
						
							if(!results.contains(url))
								results.add(url);
						}
					}
							
					if(query.isForumMod()){
						
						if(f.getResponsibleMod().toLowerCase().equals(query.getForumQuery().toLowerCase())){
							
							if(!results.contains(url))
								results.add(url);
						}
					}
				}
			}
		}
		
	}
}
