$(document).ready(function(event){
	
	$("button.search").click(function(event){
		search($(this));
	});
});

function search(cont){
	
	var query = cont.parent().children("input").val();
	$(".search-results").html("");
	
	$.ajax({
		url: "/WebApp/app/search/"+encodeURI(query),
		type: "GET",
		dataType: "json",
		contentType: "application/json; charset=utf-8",
		
		success: function(data){
			for(i=0; i<data.length; i++){
				if((i+1)%2==0){
					$(".search-results").append("<div class=\"even-result\">" +
							"<br />"+data[i]+"</div>");
				}else{
					$(".search-results").append("<div class=\"odd-result\">" +
							"<br />"+data[i]+"</div>");
				}
			}
			
			addResHandler();
		}
	});
}

function addResHandler(){
	
	$("a.forumR").click(function(event){
		event.preventDefault();
		deleteCookie("forum", "/", "");
		document.cookie="forum="+$(this).text()+"; path=/";
		window.location.href="/WebApp/forum.html";
	});
	
	$("a.subjectR").click(function(event){
		event.preventDefault();
		deleteCookie("forum", "/", "");
		deleteCookie("subject", "/", "");
		
		var thiss = $(this)[0].href;
		var forums = thiss.split("/");
		
		document.cookie="forum="+forums[forums.length-3]+"; path=/";
		document.cookie="subject="+$(this).text()+"; path=/";
		window.location.href="/WebApp/topic.html";
	});
	
	$("a.userR").click(function(event){
		event.preventDefault();
		deleteCookie("browse", "/", "");
		document.cookie="browse="+$(this).text()+"; path=/";
		window.location.href="/WebApp/user_page.html";
	});
}