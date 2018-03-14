$(document).ready(function(event){		
	
	if(checkCookie()){
		if(getType()==="R"){
			/**/
		}else{
			$(".new-forum").append("<br /><br /><a id=\"new-forum\" href=\"new-forum.html\">Create your own forum</a>");
		}
	}
});