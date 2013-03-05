function uploadImage(){
	//alert("xx");
	$.ajaxFileUpload({  
	    url:'imageUploadAjax?id='+$("#hidden_id").val(),  
	    secureuri:false,  
	    fileElementId:'imageFileAjax',  
	    dataType: 'json',  
	    success: function (data, status){
	    	$("#div_upload_result").text(data.msg);
	    	//alert($("#t1 img").get(0).src);
	    	
	    	var localObj = window.location;
	    	var contextPath = localObj.pathname.split("/")[1];
	    	var basePath = localObj.protocol+"//"+localObj.host+"/"+contextPath;
	    	
	    	$("#t1 img").get(0).src = basePath+"/fileStore/"+data.imgSrc;
	    	//alert(data.imgSrc);
	    	$("#input_accountPicture").get(0).value = data.imgSrc;
			//alert(JSON.stringify(data));
	    },
	    error: function (data, status, e){
            alert(e);
        }
	});
}
  

$(
	function(){
		//alert("xx");
	}
);