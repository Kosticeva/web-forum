$(document).ready(function(event){

	if(checkCookie()){
		window.location.href = "home_page.html";
	}
	
	$('#submit').click(function(event){
		
		if($('#username').val()===""){
			alert("You need to enter your username first!");
		}else if($('#password').val()===""){
			alert("You need to enter some sort of password to log in!");
		}else{
			var jsonobj = 
			{ "username" : $("#username").val(), "password" : $("#password").val() }
			
			$.ajax({
				url: "/WebApp/app/login",
				type: "POST",
				data: JSON.stringify(jsonobj),
				dataType: "json",
				contentType: "application/json; charset=utf-8",
				
				success: function(data){
					if(data['success']===true){
						document.cookie = data['cookie-username'];
						document.cookie = data['cookie-type'];
						window.location.href=data['url'];
					}else{
						alert(data['message']);
					}
				}
			});
		}
		
	});
	
});