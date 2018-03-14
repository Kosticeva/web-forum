package org.app.forum.single.newForum;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.app.data.database.Database;
import org.app.data.database.forums.ForumManipulationService;
import org.app.data.entities.Subforum;
import org.json.JSONException;
import org.json.JSONObject;

@Path("/forums")
public class NewForumServlet {

	//kreiranje novog foruma
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String addForum(Subforum f){
		
		f.setResponsibleMod(f.getResponsibleMod());
		
		if(ForumManipulationService.addForum(f)!=null){
			
			Database.saveData();
			JSONObject obj = new JSONObject();
			try {
				obj.put("success", true);
				obj.put("url", "/WebApp/app/forums/"+f.getUrl());
				obj.put("message", "OK");
				obj.put("cookie-forum", "forum="+f.getTitle()+"; path=/");
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return obj.toString();
			
		}else{
			
			JSONObject obj = new JSONObject();
			try {
				obj.put("success", false);
				obj.put("url", "no");
				obj.put("message", "Forum with title: \""+f.getTitle()+"\" already exists.");
			} catch (JSONException e) {
				e.printStackTrace();
			}
			
			return obj.toString();
			
			
		}
	}
	
}
