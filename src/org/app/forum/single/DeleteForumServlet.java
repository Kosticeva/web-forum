package org.app.forum.single;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import javax.ws.rs.DELETE;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.app.data.database.forums.ForumManipulationService;
import org.app.data.entities.Subforum;
import org.json.JSONException;
import org.json.JSONObject;

@Path("/forums")
public class DeleteForumServlet {

	//brisanje foruma
	@DELETE
	@Path("/{title}/delete")
	@Produces(MediaType.APPLICATION_JSON)
	public String deleteForum(@PathParam("title") String title){
		
		Subforum f = null;
		
		try {
			f = ForumManipulationService.removeForum(URLDecoder.decode(title, "UTF-8"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(f!=null){
			
			JSONObject obj = new JSONObject();
			try {
				obj.put("success", true);
				obj.put("url", "/WebApp/home_page.html");
				obj.put("message", "OK");
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return obj.toString();
		
		}else{
			
			JSONObject obj = new JSONObject();
			try {
				obj.put("success", false);
				obj.put("url", "/WebApp/forum.html");
				obj.put("message", "Error deleting forum");
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return obj.toString();
		}
	}
	
}
