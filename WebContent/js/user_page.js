$(document).ready(function(event){
	
	if(!checkCookie() && getCookie("browse")===""){
		window.location.href="home_page.html";
	}
	
	//ukoliko neko ulogovan proverava
	if(checkCookie()){
		
		if(getType()==="A"){
			//admin njegova
			if(getCookie("browse")===getCookie("username") || getCookie("browse")==""){
				$(".administration").html("<br /><span class=\"title\">Administration</span><br /><br /><a id=\"administration\" href=\"administration.html\">Administrate</a>");
				$(".complaints").html("<a id=\"complaints\" href=\"complaints.html\"><img src=\"images/complaints.png\" /></a>");
				$(".messages").html("<a id=\"messages\" href=\"messages.html\"><img src=\"images/message1600.png\" /></a>");
			}else if(getCookie("browse")!="" && getCookie("username")!=""){
				
				$(".administration").html("<br /><span class=\"title\">Administration</span>"+
						"<div class=\"expl\">Change this users' type</div>"+
						"<div class=\"sel\"><select id=\"type\" name=\"type\">"+
						"<option value=\"Administrator\">Administrator</option>"+
						"<option value=\"Moderator\">Moderator</option>"+
						"<option value=\"Regular\">Regular</option>"+
						"</select></div><br /><a id=\"changer\" href=\"/WebApp/app/administration\">Save changes</a>");
			
				$("#changer").click(function(event){
					
					event.preventDefault();
					var jsonobj = 
					{ "username" : $("#username span").text(), "type" : $("select#type").val() }
					
					$.ajax({
						url: "/WebApp/app/administration",
						type: "PUT",
						data: JSON.stringify(jsonobj),
						dataType: "json",
						contentType: "application/json; charset=utf-8",
						
						success: function(data){
							if(data['success']===true){
								window.location.href=data['url'];//user pejdz
							}else{
								alert(data['message']);
							}
						}
					});
				});
				
			}
		}else if(getType()==="M"){
			//moderator njegova
			if(getCookie("browse")===getCookie("username") || getCookie("browse")==""){
				//poruke i zalbe
				$(".complaints").html("<a id=\"complaints\" href=\"complaints.html\"><img src=\"images/complaints.png\" /></a>");
				$(".messages").html("<a id=\"messages\" href=\"messages.html\"><img src=\"images/message1600.png\" /></a>");
			}else{
				//moderator trazi -> nista
			}
		}else{
			if(getCookie("browse")===getCookie("username") || getCookie("browse")==""){
				//obican njegova -> poruke
				$(".messages").html("<a id=\"messages\" href=\"messages.html\"><img src=\"images/message1600.png\" /></a>");
			}else{
				//obican trazi -> nista
			}
		}
		
		if(getCookie("browse")==""){
			uriUser = getCookie("username");
		}else{
			uriUser = getCookie("browse");
		}
		
		$.ajax({
			url: "/WebApp/app/users/"+encodeURI(uriUser),
			type: "GET",
			dataType: "json",
			
			success: function(data1){
				$("#username").html("Username:<br /><span>"+data1["username"]+"</span>");
				$("#namee").html("Name:<br />"+data1["name"]);
				$("#surname").html("Surname:<br />"+data1["surname"]);
				$("#role").html("Role:<br />"+data1["type"]);
				$("#phone").html("Phone:<br />"+data1["phoneNum"]);
				$("#email").html("E-mail:<br />"+data1["email"]);
				$("#datee").html("Registration date:<br />"+data1["registrationDate"]);
				$("select").val(data1["type"]);
				
				$(".OL a").click(function(event){
					
					if((getCookie("browse")==getCookie("username") && getCookie("browse")!="") || (getCookie("username")!="" && getCookie("browse")=="")){
						
						if($(".forumi").html()===""){
							
							$(".forumi").append("Saved forums<br />");
							$.ajax({
								url: "/WebApp/app/users/"+encodeURI(getCookie("username"))+"/forums",
								type: "GET",
								dataType: "json",
								
								success: function(data){
									
									for(i=0; i<data.length; i++){
										$(".forumi").append("<a class=\"for\" href=\"/WebApp/app/forums/\">" +
												data[i]["title"]+"</a><br />");
									}
									
									$("a.for").click(function(event){
										event.preventDefault();
										forL($(this));
									});
								}
							});
						}else{
							$(".forumi").html("");
						}
					}
				});
				
				$(".OT a").click(function(event){
					
					if((getCookie("browse")==getCookie("username") && getCookie("browse")!="") || (getCookie("username")!="" && getCookie("browse")=="")){
						
						if($(".subjecti").html()===""){
							
							$(".subjecti").append("Saved topics<br />");
							$.ajax({
								url: "/WebApp/app/users/"+encodeURI(getCookie("username"))+"/subjects",
								type: "GET",
								dataType: "json",
								
								success: function(data){
									
									for(i=0; i<data.length; i++){
										$(".subjecti").append("<a class=\"subj\" name=\""
												+data[i]["parent"]+"\" href=\"/WebApp/app/forums/\">"
												+data[i]["title"]+"</a><br />");
									}
									
									$("a.subj").click(function(event){
										event.preventDefault();
										subL($(this));
									});
								}
							});
						}else{
							$(".subjecti").html("");
						}
					}
				});
				
				$(".OB .LL a").click(function(event){
					
					if((getCookie("browse")==getCookie("username") && getCookie("browse")!="") || (getCookie("username")!="" && getCookie("browse")=="")){
						
						if($(".commenti").html()===""){
							
							$(".commenti").append("Saved comments<br />");
							$.ajax({
								url: "/WebApp/app/users/"+encodeURI(getCookie("username"))+"/comments",
								type: "GET",
								dataType: "json",
								
								success: function(data){
									
									for(i=0; i<data.length; i++){
										$(".commenti").append("<a class=\"comm\" name=\""
												+data[i]["parent"]+"\" id=\""+data[i]["grandparent"]
												+"\" href=\"/WebApp/app/forums/\">"+data[i]["content"]
												+"</a><br />");
									}
									
									$("a.comm").click(function(event){
										event.preventDefault();
										comL($(this));
									});
								}
							});
						}else{
							$(".commenti").html("");
						}
					}
				});
				
				$(".OB .RR a").click(function(event){
					
					if((getCookie("browse")==getCookie("username") && getCookie("browse")!="") || (getCookie("username")!="" && getCookie("browse")=="")){
					
						if($(".messge").html()===""){
							
							$(".messge").append("Saved messages<br />");
							$.ajax({
								url: "/WebApp/app/users/"+encodeURI(getCookie("username"))+"/messages/save",
								type: "GET",
								dataType: "json",
								
								success: function(data){
									
									for(i=0; i<data.length; i++){
										$(".messge").append("<p>Message("+data[i]["sender"]+" 2 "
												+data[i]["recipient"]+")</p><p>"+data[i]['content']
												+"</p><br />");
									}
								}
							});
						}else{
							$(".messge").html("");
						}
					}
				});
				
				$(".obj2 .LL a").click(function(event){
					
					if((getCookie("browse")==getCookie("username") && getCookie("browse")!="") || (getCookie("username")!="" && getCookie("browse")=="")){
						
						if($(".likevi").html()===""){
							
							$(".likevi").append("Liked content<br />");
							$.ajax({
								url: "/WebApp/app/users/"+encodeURI(getCookie("username"))+"/likes/subjects",
								type: "GET",
								dataType: "json",
								
								success: function(data){
									
									for(i=0; i<data.length; i++){
										$(".likevi").append("<a class=\"subj\" name=\""+data[i]["parent"]
										+"\" href=\"/WebApp/app/forums/\">"+data[i]["title"]+"</a><br />");
									}
									
									$("a.subj").click(function(event){
										event.preventDefault();
										subL($(this));
									});
								}
							});
							
							$.ajax({
								url: "/WebApp/app/users/"+encodeURI(getCookie("username"))+"/likes/comments",
								type: "GET",
								dataType: "json",
								
								success: function(data){
									
									for(i=0; i<data.length; i++){
										$(".likevi").append("<a class=\"comm\" name=\""+data[i]["parent"]
										+"\" id=\""+data[i]["grandparent"]+"\" href=\"/WebApp/app/forums/" +
												"\">"+data[i]["content"]+"</a><br />");
									}
				
									$("a.comm").click(function(event){
										event.preventDefault();
										comL($(this));
									});
								}
							});
						}else{
							$(".likevi").html("");
						}
					}
				});
				
				$(".obj2 .RR a").click(function(event){
					
					if((getCookie("browse")==getCookie("username") && getCookie("browse")!="") || (getCookie("username")!="" && getCookie("browse")=="")){
						
						if($(".hatevi").html()===""){
							
							$(".hatevi").append("Hated content<br />");
							$.ajax({
								url: "/WebApp/app/users/"+encodeURI(getCookie("username"))+"/hates/subjects",
								type: "GET",
								dataType: "json",
								
								success: function(data){
									
									for(i=0; i<data.length; i++){
										$(".hatevi").append("<a class=\"subj\" name=\""+data[i]["parent"]
										+"\" href=\"/WebApp/app/forums/\">"+data[i]["title"]+"</a><br />");
									}
									
									$("a.subj").click(function(event){
										event.preventDefault();
										subL($(this));
									});
								}
							});
							
							$.ajax({
								url: "/WebApp/app/users/"+encodeURI(getCookie("username"))+"/hates/comments",
								type: "GET",
								dataType: "json",
								
								success: function(data){
									
									for(i=0; i<data.length; i++){
										$(".hatevi").append("<a class=\"comm\" name=\""+data[i]["parent"]
										+"\" class=\""+data[i]["grandparent"]+"\" href=\"/WebApp/app/forums/\">"
										+data[i]["content"]+"</a><br />");
									}
				
									$("a.comm").click(function(event){
										event.preventDefault();
										comL($(this));
									});
								}
							});
						}else{
							$(".hatevi").html("");
						}
					}
				});
			}
		});
		
		$(".user a").click(function(event){
			deleteCookie("browse", "/", "");
			window.reload();
		});
	}
});

function forL(thiss){
	document.cookie="forum="+thiss.text()+"; path=/;";
	window.location.href="/WebApp/forum.html";
}

function subL(thiss){
	document.cookie="forum="+thiss[0].name+"; path=/;";
	document.cookie="subject="+thiss.text()+"; path=/;";
	window.location.href="/WebApp/topic.html";
}

function comL(thiss){
	document.cookie="forum="+thiss[0].className+"; path=/;";
	document.cookie="subject="+thiss[0].name+"; path=/;";
	window.location.href="/WebApp/topic.html";
}