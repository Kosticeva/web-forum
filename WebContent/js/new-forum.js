$(document).ready(function(event){
	
	if(!checkCookie()){
		window.location.href = "home_page.html";
	}else if(getType()==="R"){
		window.location.href="home_page.html";
	}
	
	$('#submit').click(function(event){
		
		if($('#title').val()===""){
			event.preventDefault();
			alert("Please enter all data needed.");
			return;
		}else if($('#image').val()===""){
			event.preventDefault();
			alert("Please enter all data needed.");
			return;
		}else if($("#description").val()===""){
			event.preventDefault();
			alert("Please enter all data needed.");
			return;
		}else if($("#ruless").val()===""){
			event.preventDefault();
			alert("Please enter all data needed.");
			return;
		}else{
			
			var jsonobj = 
			{ 
			  "title" : $("#title").val(), 
			  "responsibleMod" : getCookie("username"),
			  "description" : $("#description").val(),
			  "image" : $("#image").val(),
			  "ruleList" : $("#ruless").val()
			}
			
			event.preventDefault();
			$.ajax({
				url: "/WebApp/app/forums",
				type: "POST",
				data: JSON.stringify(jsonobj),
				dataType: "json",
				contentType: "application/json; charset=utf-8",
				
				success: function(data){
					if(data['success']===true){
						event.preventDefault();
						deleteCookie("forum", "/", "");
						
						document.cookie=data['cookie-forum'];
						window.location.href="forum.html";
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
                .width("50%")
                .height("70%");
        };

        reader.readAsDataURL(input.files[0]);
    }
}