$(document).ready(function(event){
	
	//ako je korisnik ulogovan treba da se dodaju user page i logout dugmad
	if(checkCookie()==true){
		$(".log").html("<br /><br /><br /><br /><a id=\"logout\" href=\"home_page.html\">Log out</a>");
		$(".user").html("<a id=\"user\" href=\"user_page.html\"><img src=\"images/user_page.jpg\" /></a>");
	//ako je korisnik izlogovan treba da se dodaju register i login dugmad	
	}else{
		$(".log").html("<br /><br /><br /><br /><a id=\"login\" href=\"login.html\">Log in</a>");
		$(".user").html("<br /><br /><br /><br /><br /><a href=\"register.html\" id=\"register\">Register</a>");
	}
	
	$.ajax({
		url: "/WebApp/app/count/users",
		type: "GET",
		dataType: "json",
		async: false,
		
		success: function(data){
			$("#user_num").text(data);
		}
	});
	
	$('a#logout').click(function(event){
		deleteCookie("username", "/", "");
		deleteCookie("type", "/", "");
		location.reload();
	});
});