$(
    function(){
    	$("#txt_gotopage").keyup(function(){
    		this.value=this.value.replace(/\D/g,'');
    	});
    	
    	$("#btn_gotopage").click(function(){
    		$("#currentPage").val($("#txt_gotopage").val());
//    		window.location.href = $("#txt_gotopage").val();
    		
    		var page = $("#txt_gotopage").val();
    		var currentURL = window.location.toString();
    		var desURL = "";
    		//判断路径是否包含当前页信息(/top250或/top250/currentPage=1/)
    		if(currentURL.indexOf("currentPage=")==-1){
    			desURL = currentURL+"/currentPage="+page+"/";
    		}else{
    			var str = "currentPage="+page;
    			desURL = currentURL.replace(/currentPage=\d/,str);
    		}
    		window.location = desURL;
    	});
    }
);

/**
 * 分页组件进行跳转操作
 */
function gotopage(page){
//	var localObj = window.location;
//	var contextPath = localObj.pathname.split("/")[1];
//	var url = localObj.protocol+"//"+localObj.host+"/"+contextPath+"/movie/top250/currentPage="+page+"/";
//	alert(url);
	
	var currentURL = window.location.toString();
	var desURL = "";
	//判断路径是否包含当前页信息(/top250或/top250/currentPage=1/)
	if(currentURL.indexOf("currentPage=")==-1){
		desURL = currentURL+"/currentPage="+page+"/";
	}else{
		var str = "currentPage="+page;
		desURL = currentURL.replace(/currentPage=\d/,str);
	}
	window.location = desURL;
}