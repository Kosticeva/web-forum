var currForum;
var currSubject;

function startRender(){
	
	currForum = getCookie("forum");
	currSubject = getCookie("subject");
	
	$.ajax({
		url: "/WebApp/app/forums/"+encodeURI(currForum)+"/subjects/"+encodeURI(currSubject)+"/comments",
		type: "GET",
		dataType: "json",
		async: false,
		
		success: function(data){
			$(".comments").html("");
			renderComments(data, $(".comments"));
			addLinks();
		}
	});
}

function renderComments(data, container){
	
	var liked = false;
	var hated = false;
	var saved = false;
	var permission = false;
	var i;
	
	for(i=0; i<data.length; i++){
		var obj = data[i];
		var comm = "<div class=\"comment\">"
		comm += "<div class=\"tophat\">";
		comm += "<div class=\"big\">";
		comm += "<div class=\"keypad\" id=\""+obj["commentId"]+"\">";
		
		if(checkCookie()){
			comm += "<div class=\"rpl\">";
			comm += "<a class=\"rpl\" href=\"WebApp/app/forums/"+obj["grandparent"]+"/subjects/"+obj["parent"]+"/comments/"+obj["commentId"]+"/comments\">Reply</a>";
			comm += "</div>";	//rpl
		
			$.ajax({
				url: "/WebApp/app/users/"+encodeURI(getCookie("username"))+"/forums/"+encodeURI(currForum)+"/subjects/"+encodeURI(currSubject)+"/comments/"+encodeURI(obj["commentId"])+"/save",
				type: "GET",
				dataType: "json",
				async: false,
				
				success: function(data3){
					if(data3["success"]==true){
						if(data3["message"]==="YES"){
							saved=true;
						}else{
							comm += "<div class=\"sv\">";
							comm += "<a class=\"sv\" href=\"WebApp/app/forums/"+obj["grandparent"]+"/subjects/"+obj["parent"]+"/comments/"+obj["commentId"]+"/save\">Save</a>";
							comm += "</div>";	//sv
						}
					}else{
						comm += "<div class=\"sv\">";
						comm += "<a class=\"sv\" href=\"WebApp/app/forums/"+obj["grandparent"]+"/subjects/"+obj["parent"]+"/comments/"+obj["commentId"]+"/save\">Save</a>";
						comm += "</div>";	//sv
					}
				}
			});
			
			comm += "<div class=\"rprt\">";
			comm += "<a class=\"rprt\" href=\"WebApp/app/forums/"+obj["grandparent"]+"/subjects/"+obj["parent"]+"/comments/"+obj["commentId"]+"/complain\">Report</a>";
			comm += "</div>";	//rprt
		
			if(getType()==="A"){
				permission=true;
			}else{
				$.ajax({
					url: "/WebApp/app/forums/"+encodeURI(currForum)+"/subjects/"+encodeURI(currSubject)+"/comments/"+encodeURI(obj["commentId"])+"/check/"+encodeURI(getCookie("username")),
					type: "GET",
					dataType: "json",
					async: false,
					
					success: function(data4){
						
						if(data4["success"]==true){
							if(data4["message"]==="OK"){
								permission=true;
							}else{
								permission=false;
							}
						}else{
							permission=false;
						}
					}
				});
			}
			
			if(permission==true){
				comm += "<div class=\"dt\">";
				comm += "<a class=\"dt\" href=\"WebApp/app/forums/"+obj["grandparent"]+"/subjects/"+obj["parent"]+"/comments/"+obj["commentId"]+"/change\">Edit</a>";
				comm += "</div>";	//dt
				
				if(obj["deleted"]==false){
					comm += "<div class=\"dlt\">";
					comm += "<a class=\"dlt\" href=\"WebApp/app/forums/"+obj["grandparent"]+"/subjects/"+obj["parent"]+"/comments/"+obj["commentId"]+"/delete\">Delete</a>";
					comm += "</div>";	//dlt
				}
			}
		}
		
		comm += "</div>";	//keypad
		comm += "<div class=\"a-n-d\">";
		comm += "<span id=\"a-n-d_"+obj["commentId"]+"\">"+obj["author"]+" @ "+obj["commentDate"]+"</span>";
		comm +="</div>";	//a-n-d
		comm +="</div>";	//big
		comm += "<div class=\"small\">";
		comm += "<div class=\"liken\">";
		comm += "<div class=\"ww\">";
		comm += "<span id=\"like_"+obj["commentId"]+"\">"+obj["likesCount"]+"</span>";
		comm += "</div>";	//ww
		comm += "<div class=\"likee\">";
		
		if(checkCookie()){
			
			$.ajax({
				url: "/WebApp/app/users/"+encodeURI(getCookie("username"))+"/forums/"+encodeURI(currForum)+"/subjects/"+encodeURI(currSubject)+"/comments/"+encodeURI(obj["commentId"])+"/like",
				type: "GET",
				dataType: "json",
				async: false,
				
				success: function(data1){
					if(data1["success"]==true){
						if(data1["message"]==="YES"){
							liked=true;
						}else{
							liked=false;
						}
					}else{
						liked=false;
					}
					
					$.ajax({
						url: "/WebApp/app/users/"+encodeURI(getCookie("username"))+"/forums/"+encodeURI(currForum)+"/subjects/"+encodeURI(currSubject)+"/comments/"+encodeURI(obj["commentId"])+"/hate",
						type: "GET",
						dataType: "json",
						async: false,
						
						success: function(data2){
							if(data2["success"]==true){
								if(data2["message"]==="YES"){
									hated=true;
								}else{
									hated=false;
								}
							}else{
								hated=false;
							}
							
							if(liked==false && hated==false){
								comm += "<a href=\"WebApp/app/forums/"+obj["grandparent"]+"/subjects/"+obj["parent"]+"/comments/"+obj["commentId"]+"/like\"><img src=\"images/like.png\" /></a>";
							}
						}
					});
				}
			});
		}
		
		comm += "</div>";	//likee
		comm += "</div>";	//liken
		comm += "<div class=\"haten\">";
		comm += "<div class=\"ww\">";
		comm += "<span id=\"hate_"+obj["commentId"]+"\">"+obj["hatesCount"]+"</span>";
		comm += "</div>";	//ww
		comm += "<div class=\"hatee\">";
		
		if(checkCookie() && (liked==false && hated==false)){	
			comm += "<a href=\"WebApp/app/forums/"+obj["grandparent"]+"/subjects/"+obj["parent"]+"/comments/"+obj["commentId"]+"/hate\"><img src=\"images/dislike.png\" /></a>";
		}
		
		
		comm += "</div>";	//hatee
		comm += "</div>";	//haten
		comm += "</div>";	//small
		comm += "</div>";	//tophat
		comm += "<div class=\"boot\">";
		comm += "<span id=\"cont_"+obj["commentId"]+"\">";
		if(obj["deleted"]==true){
			comm += "***";
		}else{
			comm += obj["content"];
		}
		comm += "</span>";	//cont_id
		comm += "</div>";	//boot
		
		comm += "<div class=\"placeholder\" id=\""+obj["commentId"]+"\"></div>";
		comm += "<div class=\"recursion\" id=\""+obj["commentId"]+"\"></div>";
		comm += "</div>"; //comment
		

		container.append(comm);
		
		$.ajax({
			url: "/WebApp/app/forums/"+encodeURI(currForum)+"/subjects/"+encodeURI(currSubject)+"/comments/"+encodeURI(obj["commentId"])+"/comments",
			type: "GET",
			dataType: "json",
			async: false,
			
			success: function(DATA){
				renderComments(DATA, $("#"+obj["commentId"]+".recursion"));
			}
		});
	}
}