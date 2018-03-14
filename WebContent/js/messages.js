$(document).ready(function(event){
	
	if(checkCookie()!=true){
		window.location.href="home_page.html";
	}
	
	//$("#msgTable").css("height", "0%");
	
	$.ajax({
		url: "/WebApp/app/users/"+encodeURI(getCookie("username"))+"/messages",
		type: "GET",
		dataType: "json",
		async: false,
		
		success: function(data){
			
			for(i=0; i<data.length; i++){
				
				var msg = "<div class=\"message\"><div class=\"frame\">";
				
				$.ajax({
					url: "/WebApp/app/users/"+encodeURI(getCookie("username"))+"/messages/"+i+"/save",
					type: "GET",
					dataType: "json",
					async: false,
					
					success: function(data1){
						if(data1["success"]==true){
							if(data1["message"]==="YES")
								msg+="<br />";
							else{
								msg+="<div class=\"ssave\"><br />"+
								"<a href=\"/WebApp/app/users/"+encodeURI(getCookie("username"))+"/messages/"+i+"\">Save</a></div>";
							}
						}else{
							msg+="<br />";
						}
					}
				});
				
				if(data[i]["read"]==false){
					msg += "<a class=\"msg\" href=\"#\"><b>"+data[i]["sender"]+"</b></a>";
				}else{
					msg += "<a class=\"msg\" href=\"#\">"+data[i]["sender"]+"</a>";
				}
				
				msg += "</div><div class=\"important\"></div></div>";
				
				
				if((i+1)%2==0){
					$(".even-messages").append(msg);
				}else{
					$(".odd-messages").append(msg);
				}
			}
			
			$("a.msg").click(function(event){
				event.preventDefault();
				
				var thiss = $(this);
				var cont = $(this).parent().parent().children(".important");
				if(cont.text()===""){
					$.ajax({
						url: $(this).parent().children(".ssave").children("a")[0].href,
						type: "GET",
						dataType: "json",
						
						success: function(dataa){
							cont.text(dataa["content"]);
							thiss.html("<a class=\"msg\" href=\"#\">"+dataa["sender"]+"</a>");
						}
					});
				}else{
					cont.text("");
				}
			});
			
			$(".ssave a").click(function(event){
				event.preventDefault();
				
				var thiss = $(this);
				var hreff = $(this)[0].href+"/save";
				
				$.ajax({
					url: hreff,
					type: "POST",
					dataType: "json",
					
					success: function(dataa){
						thiss.remove();
					}
				});
			});
			
		}
	});
	
	
	$("#new-message").click(function(event){
	
	    var form = "<div class=\"hat\"><div class=\"whom\">&nbsp;&nbsp;Receiver: <br />"
	    	+ "<input type=\"text\" id=\"receiver\" name=\"receiver\" /><br />"
	    	+ "&nbsp;&nbsp;Message: </div><div class=\"send\"><br /><br /><br /><br /><br />"
			+ "<a href=\"send\">Send</a></div></div><div class=\"shoe\"><textarea id=\"content\">"
			+ "</textarea></div>"
		
		if($("#msgTable").text()===""){
			$("#msgTable").append(form);
			$("#msgTable").css("height", "50%");
		}else{
			$("#msgTable").html("");
			$("#msgTable").css("height", "0%");
		}
		
		event.preventDefault();
		
		$("#msgTable .hat .send a").click(function(event){
			
			event.preventDefault();
			var jsonobj = {
				"sender" : getCookie("username"),
				"recipient" : $("#receiver").val(),
				"content" : $("#content").val()
			}
			
			$.ajax({
				url: "/WebApp/app/users/"+encodeURI(getCookie("username"))+"/messages",
				type: "POST",
				data: JSON.stringify(jsonobj),
				dataType: "json",
				contentType: "application/json; charset=utf-8",
				
				success: function(dataa){
					if(dataa["success"]==true){
						$("#msgTable").text("");
						$("#msgTable").css("height", "0%");
						window.reload();
					}else{
						alert(dataa["message"]);
					}
				}
			});
		});
		
	});
	
});