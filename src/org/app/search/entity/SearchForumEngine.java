package org.app.search.entity;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;

import org.app.data.database.Database;
import org.app.data.entities.Subforum;

public class SearchForumEngine {

	//pretraga foruma
	public void searchForForums(ArrayList<String> urls, String query){
		
		for(Subforum f: Database.forums.values()){
			
			if(f.getTitle().toLowerCase().contains(query.toLowerCase()) ||
					query.toLowerCase().contains(f.getTitle().toLowerCase())){
				
				try {
					urls.add("<a class=\"forumR\" href=\"/WebApp/app/forums/"+URLEncoder.encode(f.getTitle(), "UTF-8")+"\">"+f.getTitle()+"</a>");
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}else if(f.getDescription().toLowerCase().contains(query.toLowerCase()) ||
					query.toLowerCase().contains(f.getDescription().toLowerCase())){
				
				try {
					urls.add("<a class=\"forumR\" href=\"/WebApp/app/forums/"+URLEncoder.encode(f.getTitle(), "UTF-8")+"\">"+f.getTitle()+"</a>");
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			/*}else if(f.getRuleList().toLowerCase().contains(query.toLowerCase()) ||
					query.toLowerCase().contains(f.getRuleList().toLowerCase())){
				
				try {
					urls.add("<li><a class=\"forumR\" href=\"/WebApp/app/forums/"+URLEncoder.encode(f.getTitle(), "UTF-8")+"\">"+f.getTitle()+"</a></li>");
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}*/
			}else if(f.getResponsibleMod().toLowerCase().equals(query.toLowerCase())){
				
				try {
					urls.add("<a class=\"forumR\" href=\"/WebApp/app/forums/"+URLEncoder.encode(f.getTitle(), "UTF-8")+"\">"+f.getTitle()+"</a>");
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		}
		
	}
	
}
