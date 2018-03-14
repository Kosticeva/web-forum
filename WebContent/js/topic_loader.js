$(document).ready(function(event){		
	$.ajax({
		url: "/WebApp/app/forums/"+encodeURI(getCookie("forum"))+"/subjects",
		type: "GET",
		dataType: "json",
		async: false,
		
		success: function(data){
			
			//dodavanje tema
			for(i=0; i<data.length; i++){
				var a = "<a class=\"tpic\" href=\"/WebApp/app/subjects/"+data[i]['url']+"\">";
				
				if((i+1)%3===1)
					a += "<div class=\"odd-subject\"><span class=\"titler\">"+data[i]['title']+"</span>";
				else if((i+1)%3===2)
					a += "<div class=\"even-subject\"><span class=\"titler\">"+data[i]['title']+"</span>";
				else
					a += "<div class=\"three-subject\"><span class=\"titler\">"+data[i]['title']+"</span>";
				
				a += "</div></a>";
				
				$(".forum-subjects").append(a);
			}
			
			$('a.tpic').click(function(event){
				event.preventDefault();
				deleteCookie("subject", "/", "");
				document.cookie="subject="+$(this).children().children(".titler").text()+"; path=/";
				
				window.location.href="/WebApp/topic.html";
			});
		}
	});
});