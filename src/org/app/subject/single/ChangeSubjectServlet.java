package org.app.subject.single;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.app.data.database.Database;
import org.app.data.database.subjects.DatabaseSubjectService;
import org.app.data.entities.Subject;
import org.json.JSONException;
import org.json.JSONObject;

@Path("/forums")
public class ChangeSubjectServlet {

	//izmena teme
	@PUT
	@Path("/{parent}/subjects/{title}/change")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String changeComment(@PathParam("parent") String parent, 
			@PathParam("title") String title, Subject s){
		
		Subject real = null;
		try {
			real = DatabaseSubjectService.getSubject(URLDecoder.decode(parent, "UTF-8"), URLDecoder.decode(title, "UTF-8"));
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		if(real!=null){
			
			real.setContent(s.getContent());
			real.setType(s.getType());
			real.setTitle(s.getTitle());
			
			Database.saveData();
			JSONObject obj = new JSONObject();
			try {
				obj.put("success", true);
				obj.put("url", "/WebApp/topic.html");
				obj.put("message", "OK");
				obj.put("cookie-subject", "subject="+real.getTitle()+"; path=/");
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return obj.toString();
			
		}else{
			
			JSONObject obj = new JSONObject();
			try {
				obj.put("success", false);
				obj.put("url", "/WebApp/topic.html");
				obj.put("message", "Unable to edit subject");
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return obj.toString();
			
		}
		
	}
}