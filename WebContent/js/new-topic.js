$(document).ready(function(event){
	
	if(!checkCookie()){
		window.location.href = "home_page.html";
	}else{
		if(getCookie("forum")===""){
			window.location.href="home_page.html";
		}
	}
	
	var url = "/WebApp/app/forums/"+encodeURI(getCookie("forum"))+"/subjects";
	var type= "POST";
	
	$("#forum_title").text(getCookie("forum"));
	
	if(getCookie("change")!=""){
		
		$.ajax({
			url: "/WebApp/app/forums/"+encodeURI(getCookie("forum"))+"/subjects/"+encodeURI(getCookie("subject")),
			type: "GET",
			dataType: "json",
			
			success: function(data){
				url = "/WebApp/app/forums/"+encodeURI(getCookie("forum"))+"/subjects/"+encodeURI(getCookie("subject"))+"/change";
				type="PUT";
				
				$("#title").val(data["title"]);
				$("#content").val(data["content"]);
				$("#typee").val(data["type"]);
			}
		});
	}
	
	$("#typee").change(function(event){
		
		if($(this).val()==="Image"){
			$("#containter").html("<div class=\"imageChoosee\">"+
								 "<input class=\"imCC\" type=\"file\" id=\"image\" " +
								 "accept=\"image/*\"  onchange=\"readURL(this);\"/><br />" +
								 "<img class=\"imCC\" id=\"blah\" src=\"#\" alt=\"Choose a image\" />"+
							     "</div>");
		}else{
			$("#containter").html("<textarea id=\"content\" name=\"content\"></textarea>");
		}
	});
	
	$('#submit').click(function(event){
	
		if($('#title').val()===""){
			event.preventDefault();
			alert("Please enter all data needed.");
			return;
		}else if($('#content').val()==="" && $("#type").val()!="Image"){
			event.preventDefault();
			alert("Please enter all data needed.");
			return;
		}else{
			event.preventDefault();
			
			var jsonobj = 
			{  
			  "parent" : getCookie("forum"),
			  "author" : getCookie("username"),
			  "title" : $("#title").val(),
			  "type" : $("#typee").val(),
			  "content" : $("#content").val()
			}
			
			$.ajax({
				url: url,
				type: type,
				data: JSON.stringify(jsonobj),
				dataType: "json",
				contentType: "application/json; charset=utf-8",
				
				success: function(data){
					if(data['success']===true){

						deleteCookie("change", "/", "");
						deleteCookie("subject", "/", "");
						document.cookie=data['cookie-subject'];
						
						window.location.href="/WebApp/topic.html";
					}else{
						alert(data['message']);
					}
				}
			});
		}
		
	});
	
});

function readURL(input) {
    if (input.files && input.files[0]) {
        var reader = new FileReader();

        reader.onload = function (e) {
            $('#blah')
                .attr('src', e.target.result)
                .width("30%")
                .height("60%");
        };

        reader.readAsDataURL(input.files[0]);
    }
}