function getAgain(){
	
}

function checkName(event){
	//清空错误信息
	$("#accountName_info").text("");
	
	var accountName = $("#accountName").val().trim();
	var errorInfo = "";
	/** ajax判断数据库中对应的用户名是否存在 */
	var isExist = true;
	var url = "register/accountNameVerify?accountName="+accountName;
	jQuery.ajax({type:"POST", 
		contentType:"application/json", 
		async: false,
		url:url, 
		dataType:"json", 
		success:function (data) {
			var num = data.num;
			if(num>0){
				isExist = true;
				errorInfo = data.errorInfo;
			}else{
				isExist = false;
			}
		}, 
		error:function (data) {
			alert(data.error);
		}
	});
	if(isExist){
		//用户名已存在
		$("#accountName_info").text(errorInfo);
		$("#accountName_info").css("color","red");
		return false;
	}
	
	return true;
}