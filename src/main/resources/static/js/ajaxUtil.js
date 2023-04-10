

function getXmlHttpRequest(){
		//브라우저 들어가면서 값 받음
	if(window.ActiveXObject){
		
		try{
			//IE5.0이후
			return new ActiveXObject("Msxml2.XMLHTTP");				
			
		}catch(e){
			//IE5.0이전
			return new ActiveXObject("Microsoft.XMLHTTP");
		}
		
	}else{
		
		return new XMLHttpRequest();
	}		
}

var httpRequest = null;


function sendRequest(url,params,callback,method){
	
	httpRequest = getXmlHttpRequest();
	
	//method 처리
	var httpMethod = method ? method : "GET";
	
	if(httpMethod != "GET" && httpMethod != "POST"){
		httpMethod = "GET";
	}
	
	//data처리
	var httpParams = (params==null || params=="") ? null : params;
		
	//url 처리
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















