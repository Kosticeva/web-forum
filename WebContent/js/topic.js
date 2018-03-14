$(document).ready(function(event){
	
	var currForum = getCookie("forum");
	var currSubject = getCookie("subject");
	
	if(currSubject==="" || currForum===""){
		window.location.href="/WebApp/forum.html";
	}
	
	startRender();
	$.ajax({
		url: "/WebApp/app/forums/"+encodeURI(currForum)+"/subjects/"+encodeURI(currSubject),
		type: "GET",
		dataType: "json",
		async: false,
		
		success: function(data){
			
			document.title = data["title"];
			
			$("#ttitle").text(data["title"]);
			$("#ptitle").text(data["parent"]);
			//$(".parentt")[1].text=data["parent"];
			$("#author").text(data["author"]);
			$("#date").text(data["creationDate"]);
			$("#type").text(data["type"]);
			$("#likes").text(data["likesCount"]);
			$("#hates").text(data["hatesCount"]);
			
			if(data["type"]=="Text"){
				$("#cont").text(data["content"]);
			}else if(data["type"]=="Link"){
				$("#cont").append("<a href=\""+data["content"]+"\">"+data["content"]+"</a>");
			}else{
				$("#cont").append("<img src=\""+data["content"]+"\"/>");
				$("#cont img").css("width", "80%");
				$("#cont img").css("height", "80%");
			}
			
			//provera da li moze da odgovori - check Cookie
			//treba da se proveri da li moze da lajkuje i hejtuje - check liked
			//da li moze da obrise - check permission
			//da li moze da menja - check permission
			//da li moze da snimi - check saved
			if(checkCookie()){
				
				$(".rep .ovel").html("<br /><br /><a id=\"report\" href=\"report\">Report</a>");
				$(".rep .onsed").html("<br /><br /><a id=\"replyy\" href=\"reply\">Reply</a>");
				$.ajax({
					url: "/WebApp/app/users/"+encodeURI(getCookie("username"))+"/forums/"+encodeURI(currForum)+"/subjects/"+encodeURI(currSubject)+"/like",
					type: "GET",
					dataType: "json",
					async: false,
					
					success: function(data1){
						
						liked=false;
						LL = false;
						
						if(data1["success"]==true){
							if(data1["message"]==="YES"){
								LL=true;
							}else{
								//
							}
						}
						
						$.ajax({
							url: "/WebApp/app/users/"+encodeURI(getCookie("username"))+"/forums/"+encodeURI(currForum)+"/subjects/"+encodeURI(currSubject)+"/hate",
							type: "GET",
							dataType: "json",
							async: false,
							
							success: function(data2){
								if(data2["success"]==true){
									if(data2["message"]==="YES"){
										return;
									}
								}
								
								if(LL==true){
									//
								}else{
									$(".like").html("<a id=\"like\" href=\"like\"><img src=\"images/like.png\" /></a>");
									$(".hate").html("<a id=\"hate\" href=\"hate\"><img src=\"images/dislike.png\" /></a>");
								}
							}
						});
					}
				});
				
				$.ajax({
					url: "/WebApp/app/users/"+encodeURI(getCookie("username"))+"/forums/"+encodeURI(currForum)+"/subjects/"+encodeURI(currSubject)+"/save",
					type: "GET",
					dataType: "json",
					async: false,
					
					success: function(data1){
						if(data1["success"]==true){
							if(data1["message"]==="YES"){
								return;
							}
						}
						
						$(".savee").html("<a id=\"save\" href=\"save\"><img src=\"images/save.jpeg\" /></a>");
					}
				});
				
				if(getType()==="A"){
					$(".edit").html("<br /><br /><a id=\"edit\" href=\"edit\">Edit</a>");
					$(".delete").html("<a id=\"del\" href=\"delete\"><img src=\"images/remove-icon-png-24.png\" /></a>");
				}else{
					$.ajax({
						url: "/WebApp/app/forums/"+encodeURI(currForum)+"/subjects/"+encodeURI(currSubject)+"/check/"+encodeURI(getCookie("username")),
						type: "GET",
						dataType: "json",
						async: false,
						
						success: function(data1){
							if(data1["success"]==true){
								if(data1["message"]==="OK"){
									$(".edit").html("<br /><br /><a id=\"edit\" href=\"edit\">Edit</a>");
									$(".delete").html("<a id=\"del\" href=\"delete\"><img src=\"images/remove-icon-png-24.png\" /></a>");
								}
							}	
						}
					});
				}
			}
		}
	});
	
	$("a#save").click(function(event){
		
		event.preventDefault();
		var cont = $(this);
		$.ajax({
			url: "/WebApp/app/users/"+encodeURI(getCookie("username"))+"/forums/"+encodeURI(getCookie("forum"))+"/subjects/"+encodeURI(getCookie("subject"))+"/save",
			type: "POST",
			dataType: "json",
			async: false,
			
			success: function(data){
				if(data['success']===true){
					cont.remove();
				}else{
					alert(data['message']);
				}
			}
		});
	});
	
	$("a#edit").click(function(event){
	
		event.preventDefault();
		deleteCookie("change", "/", "");
		document.cookie="change="+encodeURI(getCookie("subject"))+"; path=/";
		window.location.href="new-topic.html";
		
	});
	
	$("a#like").click(function(event){
		
		event.preventDefault();
		var cont = $(this);
		
		$.ajax({
			url: "/WebApp/app/users/"+encodeURI(getCookie("username"))+"/forums/"+encodeURI(getCookie("forum"))+"/subjects/"+encodeURI(getCookie("subject"))+"/like",
			type: "POST",
			dataType: "json",
			async: false,
			
			success: function(data){
				if(data['success']===true){
					$("#likes").text(data['ammount']);
					var brother = cont.parent().parent().children(".hate").children("a#hate");
					cont.remove();
					brother.remove();
					
				}else{
					alert(data['message']);
				}
			}
		});
		
	});
	
	$("a#hate").click(function(event){
		
		event.preventDefault();
		var cont = $(this);
		
		$.ajax({
			url: "/WebApp/app/users/"+encodeURI(getCookie("username"))+"/forums/"+encodeURI(getCookie("forum"))+"/subjects/"+encodeURI(getCookie("subject"))+"/hate",
			type: "POST",
			dataType: "json",
			async: false,
			
			success: function(data){
				if(data['success']===true){
					$("#hates").text(data['ammount']);
					var brother = cont.parent().parent().children(".like").children("a#like");
					cont.remove();
					brother.remove();
				}else{
					alert(data['message']);
				}
			}
		});
		
	});
	
	$("a#del").click(function(event){
		
		event.preventDefault();
		var cont = $(this);
		
		$.ajax({
			url: "/WebApp/app/forums/"+encodeURI(getCookie("forum"))+"/subjects/"+encodeURI(getCookie("subject"))+"/delete",
			type: "DELETE",
			dataType: "json",
			async: false,
			
			success: function(data){
				if(data!=null){
					window.location.href="forum.html";
				}else{
					alert("Something went wrong.");
				}
			}
		});
	});
	
	$("a#report").click(function(event){
		
		event.preventDefault();
		
		//ovde napravi novu formu za report
		if($(".report-cont").html()===""){
			var form = "<div class=\"content\"><div id=\"rep_cont\"><div class=\"text\">" +
					"Enter complaint here:<br /><textarea></textarea></div><div class=\"but1\">" +
					"<button id=\"complain\">Send report</button></div></div></div>";
			
			$(".report-cont").css("height", "33%");
			$(".report-cont").append(form);
		
			$("#complain").click(function(event){
				
				if($(".report-cont .text textarea").val()===""){
					event.preventDefault();
					alert("Please enter your complaint.");
					return;
				}
				
				var jsonobj1 = 
				{
					"author" : getCookie("username"),
					"content" : $("#complaint").val()
				}
				
				$.ajax({
					url: "/WebApp/app/complaints/forums/"+encodeURI($("#ptitle").text())+"/subjects/"+encodeURI($("#ttitle")),
					type: "POST",
					data: JSON.stringify(jsonobj1),
					dataType: "json",
					contentType: "application/json; charset=utf-8",
					async: false,
					
					success: function(data1){
						
						if(data1["success"]==true){
							$(".report-cont").html("");
							$(".report-cont").css("height", "0%");
							alert("Complaint succesfully made.");
						}else
							alert(data1["message"]);
					}
				});
			});
		}else{
			$(".report-cont").html("");
			$(".report-cont").css("height", "0%");
		}
	});
	
	$("a#replyy").click(function(event){
		
		event.preventDefault();
		
		//ovde napravi novu formu za report
		if($(".report-cont").html()===""){
			var form = "<div class=\"content\"><div id=\"com_cont\"><div class=\"text\">" +
					"Enter comment here:<br /><textarea></textarea></div><div class=\"but2\">" +
					"<button id=\"ccommentt\">Comment</button></div></div></div>";
			
			$(".report-cont").css("height", "33%");
			$(".report-cont").append(form);
			
			$("#ccommentt").click(function(event){
				
				if($(".report-cont .text textarea").val()===""){
					event.preventDefault();
					alert("Please enter your comment.");
					return;
				}
				
				var parentId = "-1";
				
				var jsonobj = 
				{
					"parent" : currSubject,
					"grandparent" : currForum,
					"author" : getCookie("username"),
					"parentComment" : parentId,
					"content" : $(".report-cont .text textarea").val()
				}
				
				$.ajax({
					url: "/WebApp/app/forums/"+encodeURI(currForum)+"/subjects/"+encodeURI(currSubject)+"/comments",
					type: "POST",
					data: JSON.stringify(jsonobj),
					dataType: "json",
					contentType: "application/json; charset=utf-8",
					async: false,
					
					success: function(data1){
						
						if(data1["success"]==true){
							$(".report-cont").html("");
							$(".report-cont").css("height", "0%");
							startRender();
						}else
							alert(data1["message"]);
					}
				});
			});
		}else{
			$(".report-cont").html("");
			$(".report-cont").css("height", "0%");
		}
	});
	
});