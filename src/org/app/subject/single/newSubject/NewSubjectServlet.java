package org.app.subject.single.newSubject;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.app.data.database.forums.DatabaseForumService;
import org.app.data.database.subjects.SubjectManipulationService;
import org.app.data.entities.Subject;
import org.json.JSONException;
import org.json.JSONObject;

@Path("/forums")
public class NewSubjectServlet {

	//dodavanje teme
	@POST
	@Path("/{parent}/subjects")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String addSubject(@PathParam("parent") String parent, Subject s){
		
		s.setAuthor(s.getAuthor());
		
		try {
			if(DatabaseForumService.getForum(URLDecoder.decode(parent, "UTF-8"))!=null)
				s.setParent(URLDecoder.decode(parent, "UTF-8"));
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		if(SubjectManipulationService.addSubject(s)!=null){	
			JSONObject obj = new JSONObject();
			try {
				obj.put("success", true);
				obj.put("url", "/WebApp/app/forums/"+parent+"/subjects/"+s.getUrl());
				obj.put("message", "OK");
				obj.put("cookie-subject", "subject="+s.getTitle()+"; path=/");
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
				obj.put("message", "Subject with title: \""+s.getTitle()+"\" already exists"
						+ "within the forum: \""+s.getParent()+"\".");
			} catch (JSONException e) {
				e.printStackTrace();
			}
			
			return obj.toString();
			
		}
	}
	
}
