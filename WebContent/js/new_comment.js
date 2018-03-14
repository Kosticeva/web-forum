var isCommentOpen = true;

function addHandler(){
	
	$('#cid button').click(function(event){
		
		var comma = $(this).parent().parent().parent().children(".text").children("textarea");
		
		if(comma.val()===""){
			alert("Enter comment first.");
		}else{
			var currForum = getCookie("forum");
			var currSubject = getCookie("subject");
			var author = getCookie("username");
			var parentId = $(this).parent().parent().parent().parent()[0].id;
	
			var jsonobj = 
			{
				"parent" : currSubject,
				"grandparent" : currForum,
				"author" : author,
				"parentComment" : parentId,
				"content" : comma.val()
			}
			
			var check = getCookie("change");
			var urll = "";
			var type = "POST";
			if(check==""){
				urll="/WebApp/app/forums/"+encodeURI(currForum)+"/subjects/"+encodeURI(currSubject)+"/comments";
			}else{
				urll="/WebApp/app/forums/"+encodeURI(currForum)+"/subjects/"+encodeURI(currSubject)+"/comments/"+encodeURI(getCookie("change"))+"/change";
				type="PUT";
			}
			
			$.ajax({
				url: urll,
				type: type,
				data: JSON.stringify(jsonobj),
				dataType: "json",
				contentType: "application/json; charset=utf-8",
				async: false,
				
				success: function(data){
					if(data['success']===true){
						deleteCookie("change", "/", "");
						isCommentOpen = true;
						var ide = placer.parent().parent().parent().parent().parent().children(".placeholder")[0].id;
						placer.parent().parent().parent().parent().parent().children(".placeholder")[0].innerHTML="";
						$("#"+ide+".placeholder").css("height", "0%");
						placer=null;
						$(".comments").text("");
						startRender();
					}else{
						alert(data["message"]);
					}
				}
			});
		}
	});
}


placer = null;
function newComment(){
	
	$(".rpl").click(function(event){
		
		if(isCommentOpen==true){
			placer = $(this);
			event.preventDefault();
			putHTML(placer);
		}
		
	});
}

function putHTML(place){
	$.ajax({
		url: "/WebApp/app/comment",
		type: "GET",
		dataType: "html",
		
		success: function(data){
			place.parent().parent().parent().parent().parent().children(".placeholder")[0].innerHTML=data;
			var ide = place.parent().parent().parent().parent().parent().children(".placeholder")[0].id;
			$("#"+ide+".placeholder").css("height", "50%");
			addHandler();
			isCommentOpen=false;
		}
	});
}