$(document).ready(function(event){
	$(".search1").click(function(event){
		search();
	});
	
});

function search(){
	
	var forum, subj, user;
	
	if($("#forum").val()==""){
		forum=null;
	}else{
		forum=$("#forum").val();
	}
	
	if($("#subject").val()==""){
		subj=null;
	}else{
		subj=$("#subject").val();
	}
	
	if($("#user").val()==""){
		user=null;
	}else{
		user=$("#user").val();
	}
	
	var fT = false;
	var fD = false;
	var fA = false;
	var sT = false;
	var sC = false;
	var sA = false;
	var sP = false;
	
	if($("#fTitle")[0].checked==true){
		fT=true;
	}
	
	if($("#fDesc")[0].checked==true){
		fD=true;
	}
	
	if($("#fMod")[0].checked==true){
		fA=true;
	}
	
	if($("#sTitle")[0].checked==true){
		sT=true;
	}
	
	if($("#sCont")[0].checked==true){
		sC=true;
	}
	
	if($("#sAuthor")[0].checked==true){
		sA=true;
	}
	
	if($("#sParent")[0].checked==true){
		sP=true;
	}
	
	if(fT==false && fD==false && fA==false && sT==false && sC==false && sA==false 
			&& sP==false && forum==null && subj==null && user==null)
		return;
	
	var jsonobj = 
		{
			"forumQuery" : forum,
			"forumTitle" : fT,
			"forumDescription" : fD,
			"forumMod" : fA,
			"subjQuery" : subj,
			"subjTitle" : sT,
			"subjContent" : sC,
			"subjAuthor" : sA,
			"subjParent" : sP,
			"userQuery" : user
		}
	
	$(".search-results").text("");
	
	$.ajax({
		url: "/WebApp/app/search/advanced",
		type: "POST",
		data: JSON.stringify(jsonobj),
		dataType: "json",
		contentType: "application/json; charset=utf-8",
		
		success: function(data){
			for(i=0; i<data.length; i++){
				if((i+1)%2==0){
					$(".search-results").append("<div class=\"even-result\"><br />"+data[i]+"</div>");
				}else{
					$(".search-results").append("<div class=\"odd-result\"><br />"+data[i]+"</div>");
				}
			}
			
			addResHandler();
		}
	});
}

function addResHandler(){
	
	$("a.forumR").click(function(event){
		event.preventDefault();
		deleteCookie("forum", "/", "");
		document.cookie="forum="+$(this).text()+"; path=/";
		window.location.href="/WebApp/forum.html";
	});
	
	$("a.subjectR").click(function(event){
		event.preventDefault();
		
		var thiss = $(this)[0].href;
		var forums = thiss.split("/");
		
		deleteCookie("forum", "/", "");
		deleteCookie("subject", "/", "");
		
		document.cookie="forum="+forums[forums.length-3]+"; path=/";
		document.cookie="subject="+$(this).text()+"; path=/";
		window.location.href="/WebApp/topic.html";
	});
	
	$("a.userR").click(function(event){
		event.preventDefault();
		deleteCookie("browse", "/", "");
		document.cookie="browse="+$(this).text()+"; path=/";
		window.location.href="/WebApp/user_page.html";
	});
}