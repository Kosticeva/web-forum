package org.app.complaints.resolve;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.app.data.database.Database;
import org.app.data.database.users.DatabaseUserService;
import org.app.data.entities.Comment;
import org.app.data.entities.Entity;
import org.app.data.entities.Subforum;
import org.app.data.entities.Subject;
import org.app.data.user_data.Complaint;
import org.app.data.user_data.Message;
import org.app.data.user_data.User;
import org.json.JSONException;
import org.json.JSONObject;

@Path("/complaints/resolve")
public class RefuseComplaintServlet {

	//odbijanje zalbe
	@POST
	@Path("/{id}/refuse")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public String refuseEntity(@PathParam("id") int id, User sender){
		
		Complaint c = Database.complaints.get(id);
		sender = DatabaseUserService.getUser(sender.getUsername());
		
		if(c!=null && sender!=null){
			Entity e = c.getTarget();
			
			String msg = null;
			
			if(e instanceof Subforum){
				//ForumManipulationService.removeForum(((Subforum)e).getTitle());
				msg = "Your complaint about forum \""+((Subforum)e).getTitle()+"\" has been refused";
			}else if(e instanceof Subject){
				//SubjectManipulationService.removeSubject((Subject)e);
				msg = "Your complaint about topic \""+((Subject)e).getTitle()+"\" under forum \""+((Subject)e).getParent()
						+"\" has been refused";
			}else{
				//((Comment)e).setDeleted(true);
				msg = "Your complaint about comment\""+((Comment)e).getCommentId()+"\" at topic \""+((Comment)e).getParent()+"\"under forum \""+
						((Comment)e).getGrandparent()+"\" has been refused";
			}
			
			Message m1 = new Message();
			m1.setSender(sender.getUsername());
			m1.setRecipient(c.getAuthor());
			m1.setContent(msg);
			
			DatabaseUserService.getUser(c.getAuthor()).getMessages().add(m1);
			Database.complaints.remove(id);
			
			Database.saveData();
			JSONObject obj = new JSONObject();
			try {
				obj.put("success", true);
				obj.put("url", "/WebApp/complaints.html");
				obj.put("message", "OK");
			} catch (JSONException ee) {
				// TODO Auto-generated catch block
				ee.printStackTrace();
			}
			
			return obj.toString();
		}
		
		JSONObject obj = new JSONObject();
		try {
			obj.put("success", false);
			obj.put("url", "/WebApp/complaints.html");
			obj.put("message", "Complaint not found.");
		} catch (JSONException ee) {
			// TODO Auto-generated catch block
			ee.printStackTrace();
		}
		
		return obj.toString();
	}
}
