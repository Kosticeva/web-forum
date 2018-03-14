function addLinks(){
	$('a.sv').click(function(event){
		
		event.preventDefault();
		var contt = $(this);
		$.ajax({
			url: "/WebApp/app/users/"+encodeURI(getCookie("username"))+"/forums/"+encodeURI(getCookie("forum"))+"/subjects/"+encodeURI(getCookie("subject"))+"/comments/"+$(this).parent().parent()[0].id+"/save",
			type: "POST",
			dataType: "json",
			
			success: function(data){
				if(data['success']===true){
					contt.remove();
				}else{
					alert(data['message']);
				}
			}
		});
	});
	
	$('a.dt').click(function(event){
	
		event.preventDefault();
		if(isCommentOpen==true){
			placer=$(this);
			putHTML($(this));
		}
		
		$.ajax({
			url: "/WebApp/app/forums/"+encodeURI(getCookie("forum"))+"/subjects/"+encodeURI(getCookie("subject"))+"/comments/"+$(this).parent().parent()[0].id,
			type: "GET",
			dataType: "json",
		
			success: function(data){
				deleteCookie("change", "/", "");
				document.cookie="change="+data["commentId"]+"; path=/";
				placer.parent().parent().parent().parent().parent().children(".placeholder").children(".com_cont").children(".text").children("textarea").val(data["content"]);
			}
		});
	});
	
	$('a.rpl').click(function(event){
		
		placer=$(this);
		event.preventDefault();
		putHTML(placer);
	});
	
	$('.likee a').click(function(event){
		
		event.preventDefault();
		var cont = $(this);
		
		$.ajax({
			url: "/WebApp/app/users/"+encodeURI(getCookie("username"))+"/forums/"+encodeURI(getCookie("forum"))+"/subjects/"+encodeURI(getCookie("subject"))+"/comments/"+cont.parent().parent().parent().parent().children(".big").children(".keypad")[0].id+"/like",
			type: "POST",
			dataType: "json",
			
			success: function(data){
				if(data['success']===true){
					cont.parent().parent().children(".ww").children("span").text(data['ammount']);
					cont.parent().parent().parent().children(".haten").children(".hatee").children("a").remove();
					cont.parent().children("a").remove();
				}else{
					alert(data['message']);
				}
			}
		});
		
	});
	
	$('.hatee a').click(function(event){
		
		event.preventDefault();
		var cont = $(this);
		
		$.ajax({
			url: "/WebApp/app/users/"+encodeURI(getCookie("username"))+"/forums/"+encodeURI(getCookie("forum"))+"/subjects/"+encodeURI(getCookie("subject"))+"/comments/"+cont.parent().parent().parent().parent().children(".big").children(".keypad")[0].id+"/hate",
			type: "POST",
			dataType: "json",
			
			success: function(data){
				if(data['success']===true){
					cont.parent().parent().children(".ww").children("span").text(data['ammount']);
					cont.parent().parent().parent().children(".liken").children(".likee").children("a").remove();
					cont.parent().children("a").remove();
					
				}else{
					alert(data['message']);
				}
			}
		});
	});
	
	$('a.dlt').click(function(event){
		
		event.preventDefault();
		var cont = $(this);
		
		$.ajax({
			url: "/WebApp/app/forums/"+encodeURI(getCookie("forum"))+"/subjects/"+encodeURI(getCookie("subject"))+"/comments/"+cont.parent().parent()[0].id+"/delete",
			type: "DELETE",
			dataType: "json",
			
			success: function(data){
				if(data!=null){
					startRender();
				}else{
					alert("Something went wrong.");
				}
			}
		});
	});
	
	$("a.rprt").click(function(event){
		
		event.preventDefault();
		var ccc = $(this);
		
		var form = "<div class=\"rep_cont\">";
		form += "<div class=\"text\">Enter complaint here:<br />";
		form += "<textarea></textarea>";
		form += "</div>";
		form += "<div class=\"but1\">";
		form += "<span id=\"id\"><button>Send report</button></span>";
		form += "</div>";
		form += "</div>"
		
		$(this).parent().parent().parent().parent().parent().children(".placeholder")[0].innerHTML = form;
		var ide = $(this).parent().parent().parent().parent().parent().children(".placeholder")[0].id;
		$("#"+ide+".placeholder").css("height", "50%");
		
		$("#id button").click(function(event){
			
			ccc = $(this);
			
			if($(this).parent().parent().parent().children(".text").children("textarea").val()===""){
				event.preventDefault();
				alert("Please enter your complaint.");
				return;
			}
			
			$.ajax({
				url: "/WebApp/app/forums/"+encodeURI(currForum)+"/subjects/"+encodeURI(currSubject)+"/comments/"+$(this).parent().parent().parent().parent()[0].id,
				type: "GET",
				dataType: "json",
				async: false,
				
				success: function(data){
					
					var jsonobj1 = 
					{
						"author" : getCookie("username"),
						"content" : $("#"+data["commentId"]+".placeholder").children().children().children("textarea").val()
					}
					
					var grdpt = data["grandparent"];
					var pt = data["parent"];
					var idd = data["commentId"];
					
					$.ajax({
						url: "/WebApp/app/complaints/comments/"+encodeURI(grdpt)+"/"+encodeURI(pt)+"/"+encodeURI(idd),
						type: "POST",
						data: JSON.stringify(jsonobj1),
						dataType: "json",
						contentType: "application/json; charset=utf-8",
						async: false,
						
						success: function(data1){
							
							if(data1["success"]==true){
								var ide = ccc.parent().parent().parent().parent().parent().children(".placeholder")[0].id;
								$("#"+ide+".placeholder").css("height", "0%");
								ccc.parent().parent().parent().parent().parent().children(".placeholder").html("");
							}else
								alert(data1["message"]);
						}
					});
				}
			});
		});
	});
}