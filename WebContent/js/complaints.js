$(document).ready(function(event){
	
	if(!checkCookie()){
		window.location.href="home_page.html";
	}else{
		if(getType()==="R"){
			window.location.href="home_page.html";
		}
	}
	
	$.ajax({
		url: "/WebApp/app/complaints/my/"+encodeURI(getCookie("username")),
		type: "GET",
		dataType: "json",
		async: false,
		
		success: function(data){
			
			for(i=0; i<data.length; i++){
				
				var msg = "<div class=\"complaint\" id=\""+i+"\"><div class=\"frame\"><div class=\"rsvp\">" +
						"<a href=\"rsvp\">Respond</a></div><div class=\"sender\">"+data[i]["author"]+
						" @ "+data[i]["date"]+"</div></div><div class=\"important\">";
				
				if(data[i]["target"]["image"]!=null){
					msg += "Target (Forum) -> "+data[i]["target"]["title"]+"<br />";
				}else if(data[i]["target"]["type"]!=null){
					msg += "Target (Topic) -> "+data[i]["target"]["title"]+"<br />";
				}else{
					msg += "Target (Comment) -> "+data[i]["target"]["commentId"]+"<br />";
				}
			
				msg += data[i]["content"]+"</div><div class=\"optss\"><br /><a class=\"refuse\" href=\"refuse\">" +
						"Refuse</a><a class=\"warn\" href=\"warn\">Send warning</a><a class=\"delete\" href=\"delete\">" +
						"Remove entity</a></div></div>";
				
				if((i+1)%2==0){
					$(".even-complaints").append(msg);
				}else{
					$(".odd-complaints").append(msg);
				}
				
				$(".optss").css("display", "none");
				
				$(".rsvp a").click(function(event){
					event.preventDefault();
					if($(this).parent().parent().parent().children(".optss").css("display")=="none"){
						$(this).parent().parent().parent().children(".optss").css("display", "block");
					}else{
						$(this).parent().parent().parent().children(".optss").css("display", "none");
					}
				});
			}
			
			$(".delete").click(function(event){
				event.preventDefault();
				
				var jsonobj =
				{
					"username" : getCookie("username")
				}
				
				var id = $(this).parent().parent()[0].id;
				
				var thiss = $(this);
				$.ajax({
					url: "/WebApp/app/complaints/resolve/"+id+"/delete",
					type: "POST",
					data: JSON.stringify(jsonobj),
					dataType: "json",
					contentType: "application/json; charset=utf-8",
					
					success: function(dataaa){
						if(dataaa["success"]==true){
							thiss.parent().parent().remove();
						}else{
							alert(dataaa["message"]);
						}
					}
				});
			});
			
			$(".warn").click(function(event){
				event.preventDefault();
				
				var jsonobj =
				{
					"username" : getCookie("username")
				}
				
				var id = $(this).parent().parent()[0].id;
				
				var thiss = $(this);
				$.ajax({
					url: "/WebApp/app/complaints/resolve/"+id+"/warn",
					type: "POST",
					data: JSON.stringify(jsonobj),
					dataType: "json",
					contentType: "application/json; charset=utf-8",
					
					success: function(dataaa){
						if(dataaa["success"]==true){
							thiss.parent().parent().remove();
						}else{
							alert(dataaa["message"]);
						}
					}
				});
			});
			
			$(".refuse").click(function(event){
				event.preventDefault();
				
				var jsonobj =
				{
					"username" : getCookie("username")
				}
				
				var id = $(this).parent().parent()[0].id;
				
				var thiss = $(this);
				$.ajax({
					url: "/WebApp/app/complaints/resolve/"+id+"/refuse",
					type: "POST",
					data: JSON.stringify(jsonobj),
					dataType: "json",
					contentType: "application/json; charset=utf-8",
					
					success: function(dataaa){
						if(dataaa["success"]==true){
							thiss.parent().parent().remove();
						}else{
							alert(dataaa["message"]);
						}
					}
				});
			});
		}
	});
});