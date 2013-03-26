<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="json" uri="http://www.atg.com/taglibs/json" %>
<% Exception ex = (Exception)request.getAttribute("ex"); %>
{"errormsg":"<%= ex.getMessage()%>","errorstatus":"true"}


