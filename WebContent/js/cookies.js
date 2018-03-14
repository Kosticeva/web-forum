function checkCookie() {
    var username = getCookie("username");
    if (username != "") {
        return true;
    } else {
    	return false;
    }
}

function getType(){
	if(getCookie("type")==="Administrator"){
		return "A";
	}else{
		if(getCookie("type")==="Moderator"){
			return "M";
		}else{
			return "R";
		}
	}
}

function getCookie(cname) {
    var name = cname + "=";
    var decodedCookie = decodeURIComponent(document.cookie);
    var ca = decodedCookie.split(';');
    for(var i = 0; i <ca.length; i++) {
        var c = ca[i];
        while (c.charAt(0) == ' ') {
            c = c.substring(1);
        }
        if (c.indexOf(name) == 0) {
            return c.substring(name.length, c.length);
        }
    }
    return "";
}

function deleteCookie(name, path, domain){
    document.cookie = name + "=" +
      ((path) ? ";path="+path:"")+
      ((domain)?";domain="+domain:"") +
      ";expires=Thu, 01 Jan 1970 00:00:01 GMT";
}