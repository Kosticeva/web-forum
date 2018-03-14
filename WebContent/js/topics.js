$(document).ready(function(event){
	
	//dodavanje inf
	$.ajax({
		url: "/WebApp/app/count/subjects",
		type: "GET",
		dataType: "json",
		async: false,
		
		success: function(data){
			$("#subjects-title").text(data);
		}
	});
	
	$.ajax({
		url: "/WebApp/app/subjects",
		type: "GET",
		dataType: "json",
		async: false,
		
		success: function(data){
			
			//DIZAJN<a class="tpic" href="topic.html"><div class="odd-subject">Tema bla bla (<span class="parenter">XXX</span>)</div></a>
			
			
			//dodavanje foruma, ovo ces da ispravis kako treba kada smislis dizajn foruma
			for(i=0; i<data.length; i++){
				var a = "<a class=\"tpic\" href=\"/WebApp/app/subjects/"+data[i]['url']+"\">";
				
				if((i+1)%3===1)
					a += "<div class=\"odd-subject\"><span class=\"titler\">"+data[i]['title']+"</span>";
				else if((i+1)%3===2)
					a += "<div class=\"even-subject\"><span class=\"titler\">"+data[i]['title']+"</span>";
				else
					a += "<div class=\"three-subject\"><span class=\"titler\">"+data[i]['title']+"</span>";
				
				a += " ( <span class=\"parenter\">"+data[i]['parent']+"</span> )</div></a>";
				
				$(".subjects").append(a);
			}
			
			$('a.tpic').click(function(event){
				event.preventDefault();
				deleteCookie("forum", "/", "");
				deleteCookie("subject", "/", "");
				document.cookie="forum="+$(this).children().children(".parenter").text()+"; path=/";
				document.cookie="subject="+$(this).children().children(".titler").text()+"; path=/";
				
				window.location.href="/WebApp/topic.html";
			});
		}
	});
});