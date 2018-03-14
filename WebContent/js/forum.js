$(document).ready(function(event){
	
	var currForum = getCookie("forum");
	if(currForum===""){
		window.location.href = "forums.html";
		return;
	}
	
	$.ajax({
		url: "/WebApp/app/forums/"+encodeURI(currForum),
		type: "GET",
		dataType: "json",
		async: false,
		
		success: function(data){
			
			document.title = data['title'];
			$('#forum-title').text(data['title']);
			$('#description').text(data['description']);
			$("#mod").text(data['responsibleMod']);
			$('#rules').text(data['ruleList']);
			var vv = document.getElementById("image");
			vv.src = data['image'];
			
			if(checkCookie()){		//ukoliko je ulogovan
		
				$.ajax({
					url: "/WebApp/app/users/"+encodeURI(getCookie("username"))+"/forums/"+encodeURI(currForum)+"/save",
					type: "GET",
					dataType: "json",
					async: false,
					
					success: function(data1){
						if(data1["success"]==true){
							if(data1["message"]==="YES"){
								//
							}else{
								$(".save").append("<a id=\"save\" href=\"save\"><img src=\"images/save.jpeg\" /></a>");
							}
						}
					}
				});
		
				if(getType()==="A"){
					$(".del").html("<a id=\"delete\" href=\"delete\"><img src=\"images/remove-icon-png-24.png\" /></a>");
				}else{
					$.ajax({
						url: "/WebApp/app/forums/"+encodeURI(currForum)+"/check/"+encodeURI(getCookie("username")),
						type: "GET",
						dataType: "json",
						async: false,
						
						success: function(data1){
							if(data1["success"]==true){
								if(data1["message"]==="OK"){
									$(".del").append("<a id=\"delete\" href=\"delete\"><img src=\"images/remove-icon-png-24.png\" /></a>");
								}
							}
						}
					});
				}
			
				$(".new-subject").append("<br /><br /><a id=\"new-subject\" href=\"new-topic.html\">Create your own topic</a>");
				$(".report").append("<a id=\"report\" href=\"report\">Report</a>");
			}
		}
	});
	
	$('#delete').click(function(event){
		
		event.preventDefault();
		
		$.ajax({
			url: "/WebApp/app/forums/"+encodeURI(getCookie("forum"))+"/delete",
			type: "DELETE",
			dataType: "json",
			async: false,
			
			success: function(data){
				if(data['success']===true){
					window.location.href=data['url'];
				}else{
					alert(data['message']);
				}
			}
		});
	});
	
	$('#save').click(function(event){
		
		event.preventDefault();
		
		$.ajax({
			url: "/WebApp/app/users/"+encodeURI(getCookie("username"))+"/forums/"+encodeURI(getCookie("forum"))+"/save",
			type: "POST",
			dataType: "json",
			async: false,
			
			success: function(data){
				if(data['success']===true){
					$("#save").remove();
				}else{
					alert(data['message']);
				}
			}
		});
	});
	
	$("#report").click(function(event){
		
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
					url: "/WebApp/app/complaints/forums/"+encodeURI($("#forum-title").text()),
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
});