$(document).ready(function(event){
	
	if(checkCookie()){
		window.location.href = "home_page.html";
	}
	
	$('#submit').click(function(event){
		
		if($('#username').val()===""){
			alert("You need to fill-in all fields before creating your account!");
			event.preventDefault();
		}else if($('#email').val()===""){
			alert("You need to fill-in all fields before creating your account!");
			event.preventDefault();
		}else if($('#password').val()===""){
			alert("You need to fill-in all fields before creating your account!");
			event.preventDefault();
		}else if($('#fname').val()===""){
			alert("You need to fill-in all fields before creating your account!");
			event.preventDefault();
		}else if($('#lname').val()===""){
			alert("You need to fill-in all fields before creating your account!");
			event.preventDefault();
		}else if($('#number').val()===""){
			alert("You need to fill-in all fields before creating your account!");
			event.preventDefault();
		}else if(isNaN(Number($("#number").val()))){
			alert("You need to enter a number for your phone number!");
			event.preventDefault();
		}else{
			var jsonobj = 
			{ "username" : $("#username").val(), 
			  "password" : $("#password").val(),
			  "name" : $("#fname").val(), 
			  "surname" : $("#lname").val(),
			  "email" : $("#email").val(), 
			  "phoneNum" : $("#number").val()
			}
			
			$.ajax({
				url: "/WebApp/app/register",
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