package org.app.data.database;


import javax.servlet.ServletContext;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

@Path("/load")
public class Loader {
	
	@Context 
	ServletContext servletContext;
	private static boolean loader = false;
	
	//prvi servlet koji se izvrsava
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public void loadData(){
		
		if(!loader){
			Database.loadData(servletContext.getRealPath("/"));
			loader=true;
		}
	}
	
}
