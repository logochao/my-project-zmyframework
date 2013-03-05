<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core"%>  
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style type="text/css">
div {
	border: 1px solid black;
}
</style>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/common/scripts/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/test/scripts/test.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/common/scripts/ajaxfileupload.js"></script>
<script type="text/javascript">
    function ajaxFileUpload() {
    	/**
        $("#loading").ajaxStart(function() {
            $(this).show();
            $("#showData").html();
        }).ajaxComplete(function() {
            $(this).hide();
        });
    	**/
        $.ajaxFileUpload({
            url : $("#uploadform").attr("action"),
            secureuri : false,
            fileElementId : 'myfiles',
            dataType : 'json',
            data : {
                'entity.indentId' : 'id'
            },
            success : function(data, status) {
                if(data.success){
                	/**
                    htmlStr = "";
                    sheet = data.msg.myfiles.excelSheetList[0];
                    headRowIndex = sheet.headRowIndex;
                    rowList = sheet.excelRowList;
                    rowSize = rowList.length;
                    if( rowSize >= headRowIndex + 1 ){
                        headRow = rowList[headRowIndex].cellValueList;
                        headCellLen = headRow.length;
                        htmlStr += "<table border=\"1\">";
                        htmlStr += "<thead>";
                        htmlStr += "<tr>";
                        for(headCellIndex = 0; headCellIndex < headCellLen; headCellIndex ++){
                            htmlStr += "<th>";
                            htmlStr += headRow[headCellIndex];
                            htmlStr += "</th>";
                        }
                        htmlStr += "</tr>";
                        htmlStr += "</thead>";
                        htmlStr += "<tbody>";
                        if( rowSize == headRowIndex + 1 ){
                            htmlStr += "<tr>";
                            htmlStr += "<td colSpan=\""+headCellLen+"\">";
                            htmlStr += "data not fond";
                            htmlStr += "</td>";
                            htmlStr += "</tr>";
                        }else{
                            for(rowIndex = headRowIndex + 1; rowIndex < rowSize; rowIndex ++){
                                row = rowList[rowIndex].cellValueList;
                                htmlStr += "<tr>";
                                for(cellIndex = 0; cellIndex < headCellLen; cellIndex ++){
                                    htmlStr += "<td>";
                                    rowContent = row[cellIndex];
                                    if('object' == typeof(rowContent)){
                                        htmlStr += rowContent.dateStr;
                                    }else{
                                        htmlStr += rowContent;
                                    }
                                    htmlStr += "</td>";
                                }
                                htmlStr += "</tr>";
                            }
                        }
                        htmlStr += "</tbody>";
                        htmlStr += "</table>";
                    }
                	$("#showData").html(htmlStr);
                	**/
                }
            },
            error : function(data, status, e) {
                alert('上传错误：' + data);
            }
        });
        return false;
    }
</script>
</head>
<body>
<!-- 
	<div style="margin:0px auto; height: 80px;width: 1106px;">
		<div style="height: 80px;width: 1106px;">
			网站历史访问次数:${visitTimes}
			<br /> <a href="${pageContext.request.contextPath}/test/logout">退出系统</a>
		</div>
		<div style="float: left;height: 500px;width: 150px;">navigator</div>
		<div style="float: left;height: 500px;width: 800px;">
			${content}
		</div>
		<div id="div_onlinePerson"
			style="float: left;height: 500px;width: 150px;">
			<%-- <jsp:include page="../../module/global/onlinePersonList.jsp" flush="true"></jsp:include> --%>
		</div>
	</div>
 -->
	<div>
        <form name="uploadform" id="uploadform"
            action="${pageContext.request.contextPath}/filexload/upload"
            enctype="multipart/form-data">
            <input id="myfiles" type="file" size="20" name="myfiles"
                class="input" /> <br /> <input type="submit"
                id="buttonUpload" onclick="ajaxFileUpload();" value="上传"/>
        </form>
    </div>
</body>
</html>