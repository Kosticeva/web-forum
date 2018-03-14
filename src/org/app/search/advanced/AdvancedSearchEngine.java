package org.app.search.advanced;

import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.app.search.advanced.entity.AdvancedSearchForumEngine;
import org.app.search.advanced.entity.AdvancedSearchSubjectEngine;
import org.app.search.advanced.entity.AdvancedSearchUserEngine;

@Path("/search/advanced")
public class AdvancedSearchEngine {

	public AdvancedSearchForumEngine forums = new AdvancedSearchForumEngine();
	public AdvancedSearchSubjectEngine subjects = new AdvancedSearchSubjectEngine();
	public AdvancedSearchUserEngine users = new AdvancedSearchUserEngine();
	
	//pretraga sa uslovima
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<String> advancedSearch(AdvancedSearchQuery query){
		
		ArrayList<String> results = new ArrayList<String>();
		
		forums.searchForForums(query, results);
		subjects.searchForSubjects(query, results);
		users.searchForUsers(query, results);
		
		return results;
	}
	
	
	
	
	

	
	
	
}
