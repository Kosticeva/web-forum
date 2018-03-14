package org.app.comment;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/comment")
public class GetCommentPage {

	private String pageContent1 = "<div class=\"com_cont\"><div class=\"text\">Enter comment here:<br />"
								+ "<textarea></textarea></div><div class=\"but2\"><span id=\"cid\"><button>"
								+ "Comment</button></span></div></div>";
	
	//dobavlja html kod za novi komentar
	@GET
	@Produces(MediaType.TEXT_HTML)
	public String getHTML1(){
		return pageContent1;
	}
	
}
