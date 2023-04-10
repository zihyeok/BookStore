

function getXmlHttpRequest(){
		
	if(window.ActiveXObject){
		
		try{
			
			return new ActiveXObject("Msxml2.XMLHTTP");				
			
		}catch(e){
			
			return new ActiveXObject("Microsoft.XMLHTTP");
		}
		
	}else{
		
		return new XMLHttpRequest();
	}		
}

var httpRequest = null;


function sendRequest(url,params,callback,method){

	httpRequest = getXmlHttpRequest();
	
	
	var httpMethod = method ? method : "GET";
	
	if(httpMethod != "GET" && httpMethod != "POST"){
		httpMethod = "GET";
	}
	
	
	var httpParams = (params==null || params=="") ? null : params;
		
	
	var httpUrl = url;
	
	if(httpMethod=="GET" && httpParams!=null){		
		httpUrl = httpUrl + "?" + httpParams;		
	}
	
	httpRequest.open(httpMethod,httpUrl,true);
	
	httpRequest.setRequestHeader("Content-type",
			"application/x-www-form-urlencoded");
	httpRequest.onreadystatechange = callback;
	
	httpRequest.send(httpMethod=="POST" ? httpParams : null);	
	
}















