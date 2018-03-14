package org.app.search;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.app.search.entity.SearchForumEngine;
import org.app.search.entity.SearchSubjectEngine;
import org.app.search.entity.SearchUserEngine;

@Path("/search")
public class SearchEngine {

	public SearchForumEngine forums = new SearchForumEngine();
	public SearchSubjectEngine subjects = new SearchSubjectEngine();
	public SearchUserEngine users = new SearchUserEngine();
	
	//obicna pretraga
	@GET
	@Path("/{query}")
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<String> getResults(@PathParam("query") String query){
		
		ArrayList<String> listOfUrls = new ArrayList<String>();
		
		try {
			forums.searchForForums(listOfUrls, URLDecoder.decode(query, "UTF-8"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			subjects.searchForSubjects(listOfUrls, URLDecoder.decode(query, "UTF-8"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			users.searchForUsers(listOfUrls, URLDecoder.decode(query, "UTF-8"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return listOfUrls;
	}
	
}
