$(document).ready(function(event){
	
	deleteCookie("forum", "/", "");
	deleteCookie("subject", "/", "");
	deleteCookie("change", "/", "");
	deleteCookie("browse", "/", "");
	
	getInformations();	
});

function getInformations(){
	
	$.ajax({
		url: "/WebApp/app/count/forums",
		type: "GET",
		dataType: "json",
		async: false,
		
		success: function(data){
			$("#forum_num").text(data);
		}
	});
	
	$.ajax({
		url: "/WebApp/app/count/subjects",
		type: "GET",
		dataType: "json",
		async: false,
		
		success: function(data){
			$("#subj_num").text(data);
		}
	});
	
}