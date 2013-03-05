$(
    function(){
    	$("#txt_gotopage").keyup(function(){
    		this.value=this.value.replace(/\D/g,'');
    	});
    	
    	$("#btn_gotopage").click(function(){
    		$("#currentPage").val($("#txt_gotopage").val());
    		$("#form1").submit();
    		
//    		gotopage($("#txt_gotopage").val());
//    		$("#currentPage").val(gotopage);
//    		$("#btn_search").submit();
    		//window.location.href = "showAccountsByPage?currentPage=" + $("#txt_gotopage").val();
    	});
    	
    	$("#btn_search").click(function(){
    		$("#currentPage").val(1);
    		$("#form1").submit();
    	});
    }
);

/**
 * 分页组件进行跳转操作
 */
function gotopage(page){
	$("#currentPage").val(page);
	$("#form1").submit();
}