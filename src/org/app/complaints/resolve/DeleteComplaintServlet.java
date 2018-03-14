package org.app.complaints.resolve;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.app.data.database.Database;
import org.app.data.database.forums.ForumManipulationService;
import org.app.data.database.subjects.SubjectManipulationService;
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
public class DeleteComplaintServlet {

	//uvazavanje zable
	@POST
	@Path("/{id}/delete")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public String deleteEntity(@PathParam("id") int id, User sender){
		
		Complaint c = Database.complaints.get(id);
		
		if(c!=null){
			Entity e = c.getTarget();
			User entityAuthor = null;
			
			String msg = null;
			
			if(e instanceof Subforum){
				ForumManipulationService.removeForum(((Subforum)e).getTitle());
				entityAuthor = DatabaseUserService.getUser(((Subforum)e).getResponsibleMod());
				msg = "Forum \""+((Subforum)e).getTitle()+"\" has been deleted because of a complaint by \""+sender.getUsername()+"\"";
			}else if(e instanceof Subject){
				SubjectManipulationService.removeSubject((Subject)e);
				entityAuthor = DatabaseUserService.getUser(((Subject)e).getAuthor());
				msg = "Topic\""+((Subject)e).getTitle()+"\" under forum \""+((Subject)e).getParent()
						+"\" has been deleted because of a complaint by \""+sender.getUsername()+"\"";
			}else{
				((Comment)e).setDeleted(true);
				entityAuthor = DatabaseUserService.getUser(((Comment)e).getAuthor());
				msg = "Comment\""+((Comment)e).getCommentId()+"\" at topic \""+((Comment)e).getParent()+"\"under forum \""+
						((Comment)e).getGrandparent()+"\" has been deleted because of a complaint by \""+sender.getUsername()+"\"";
			}
			
			Message m1 = new Message();
			Message m2 = new Message();
			m1.setSender(sender.getUsername());
			m2.setSender(sender.getUsername());
			
			m1.setRecipient(c.getAuthor());
			m2.setRecipient(entityAuthor.getUsername());
			
			m1.setContent(msg);
			m2.setContent(msg);
			
			DatabaseUserService.getUser(c.getAuthor()).getMessages().add(m1);
			entityAuthor.getMessages().add(m2);
			
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
