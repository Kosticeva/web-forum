$.ajax({
	url: "/WebApp/app/load",
	type: "GET",
	dataType: "json",
	
	success: function(data){
		window.location.href="/WebApp/home_page.html";
	}

});