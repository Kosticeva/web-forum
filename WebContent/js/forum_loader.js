$(document).ready(function(event){		
	$.ajax({
		url: "/WebApp/app/forums",
		type: "GET",
		dataType: "json",
		async: false,
		
		success: function(data){
			
			//dodavanje foruma, ovo ces da ispravis kako treba kada smislis dizajn foruma
			for(i=0; i<data.length; i++){
				var a = "<a class=\"forumX\" href=\"/WebApp/app/forums/"+data[i]['url']+"\">";
				a += "<div class=\"forum\"><div class=\"up\">";
				a += "<div class=\"jpg\"><img src=\""+data[i]['image']+"\" /></div>";
				a += "<div class=\"name\">"+data[i]['title']+"</div></div>";
				a += "<div class=\"other\">"+data[i]['description']+"</div></div></a>";
				
				if((i+1)%2==0){
					$(".even-forums").append(a);
				}else{
					$(".odd-forums").append(a);
				}
			}
			
			$('a.forumX').click(function(event){
				event.preventDefault();
				deleteCookie("forum", "/", "");
				document.cookie="forum="+$(this).children().children().children(".name").text()+"; path=/";
				
				window.location.href="/WebApp/forum.html";
			});
		}
	});
});