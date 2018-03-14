package org.app.complaints;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.app.data.database.complaints.DatabaseComplaintsService;
import org.app.data.user_data.Complaint;

@Path("/complaints")
public class ComplaintsServlet {

	//sve zalbe jednog korisnika
	@GET
	@Path("/my/{username}")
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<Complaint> getMyComplaints(@PathParam("username") String username){
		
		try {
			return DatabaseComplaintsService.getUserComplaints(URLDecoder.decode(username, "UTF-8"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	//specificna zalba jednog korisnika
	@GET
	@Path("/my/{username}/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Complaint getComplaint(@PathParam("username") String username, @PathParam("id") int id){
		
		try {
			return DatabaseComplaintsService.getUserComplaints(URLDecoder.decode(username, "UTF-8")).get(id);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
}
