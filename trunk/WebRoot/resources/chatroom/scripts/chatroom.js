$(
    function(){
    	var localObj = window.location;
    	var contextPath = localObj.pathname.split("/")[1];
    	var basePath = localObj.protocol+"//"+localObj.host+"/"+contextPath;
    	
    	//第一次进入的时候显示到最下面信息
    	$("#div_msg_content").load(basePath+"/chatroom/messageContent",function(){
    		var obj = $("#div_msg_content").get(0);
			obj.scrollTop = obj.scrollHeight;;
			$("#text_msg_send").val("");
    	});
    	
    	//每隔1s刷新下
//    	var timer = setInterval(function(){
//    		$("#div_msg_content").load(basePath+"/chatroom/messageContent");
//    	}, 1000);
    	
    	$("#btn_msg_send").click(function(){
    		var str = $("#text_msg_send").val();
    		url = basePath+"/chatroom/sendMessage";
    		jQuery.ajax({type:"POST",
    			data:{"str":str},
    			async: false,
    			url:url
//    			success:function(){
//    			}
    		});
    		$("#div_msg_content").load(basePath+"/chatroom/messageContent",function(){
    			var obj = $("#div_msg_content").get(0);
    			obj.scrollTop = obj.scrollHeight;;
    			$("#text_msg_send").val("");
    		});
    		
    	});
    }
);
