$(document).ready(function(event){
	
	if(!(checkCookie() && getType()=="A")){
		window.location.href="home_page.html";
	}
	
	$.ajax({
		url: "/WebApp/app/users",
		type: "GET",
		dataType: "json",
		async: false,
		
		success: function(data){
			
			
			for(i=0; i<data.length; i++){
				
				var form = "<div class=\"";
				if((i+1)%3==1){
					form += "one-user\">";
				}else if((i+1)%3==2){
					form += "two-user\">";
				}else{
					form += "three-user\">";
				}
				
				form += "<span class=\"ime\"><span>" + data[i]["username"];
				form += "</span>, <div>"+data[i]["type"]+"</div> </span>";
				form += "<button class=\"typen\">Change type</button></div>"
					
				$(".middle").append(form);
			}
			
			$("button.typen").click(function(event){
				var cont = $(this).parent();
				cont.children("button.typen").remove();
				cont.append("<select class=\"typen\">" + 
						"<option value=\"Administrator\">Administrator</option>" +
						"<option value=\"Moderator\">Moderator</option>" +
						"<option value=\"Regular\">Regular</option>" +
						"</select>");
				
				cont.children("select").val(cont.children("span.ime").children("div").text());
				
				$("select.typen").change(function(event){
					
					var username = $(this).parent().children(".ime").children("span").text();
					var jsonobj = 
					{ "username" : username, "type" : $(this).val() }
					
					$.ajax({
						url: "/WebApp/app/administration",
						type: "PUT",
						data: JSON.stringify(jsonobj),
						dataType: "json",
						contentType: "application/json; charset=utf-8",
						
						success: function(data){
							if(data['success']===true){
								window.location.href="administration.html";
							}else{
								alert(data['message']);
							}
						}
					});
				});
				
			});
		}
	});
	
});