package com.wendellup.web.base.tag;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import org.wendellup.core.support.QueryParams;

public class BootstrapPageTag extends SimpleTagSupport {

	public BootstrapPageTag() {
	}

	@Override
	public void doTag() throws JspException, IOException {
		PageContext ctx = (PageContext) getJspContext();
		JspWriter out = ctx.getOut();
		QueryParams queryParams = (QueryParams) ctx.getSession().getAttribute(
				"queryParams");
		int records = queryParams.getPaging().getRecords();
		int pageSize = queryParams.getPaging().getPageSize();
		int currentPage = queryParams.getPaging().getCurrentPage();
		int pages = queryParams.getPaging().getPages();

		out.print("总记录数:"+records+", 每页条数:"+pageSize+"<br />");
		out.print("当前第几页:"+currentPage+", 总页数:"+pages+"<br />");
		
		out.print("<div class='input-append'>" +
				  "<input id='txt_gotopage' type='text' class='input-mini' value='"+currentPage+"'>" +
				  "<button id='btn_gotopage' class='btn' type='button'>去该页!</button></div>");
		
		out.print("<div class='pagination'> <ul>");

		if (currentPage != 1) {
			out.print("<li><a href='javascript:gotopage(1)'>首页</a></li>");
			out.print("<li><a href='javascript:gotopage("+(currentPage - 1)+")'>上一页</a></li>");
		}else{
			out.print("<li class='active'><a href='javascript:gotopage(1)'>首页</a></li>");
			out.print("<li class='active'><a href='javascript:gotopage("+(currentPage - 1)+")'>上一页</a></li>");
		}

		if (currentPage <= 3) {
			for (int i = 1; i <= (pages <= 5 ? pages : 5); i++) {
				if (i == currentPage) {
					out.print("<li class='active'><a href='#'>"+currentPage+"</a></li>");
				} else {
					out.print("<li><a href='javascript:gotopage("+i+")'>"+i+"</a></li>");
				}
			}
		} else if (currentPage > 3 && pages <= (currentPage + 2)) {
			for (int i = pages - 4 < 1 ? 1 : pages - 4; i <= pages; i++) {
				if (i == currentPage) {
					out.print("<li class='active'><a href='#'>"+currentPage+"</a></li>");
				} else {
					out.print("<li><a href='javascript:gotopage("+i+")'>"+i+"</a></li>");
				}
			}
		} else if (currentPage > 3 && pages > (currentPage + 2)) {
			for (int i = currentPage - 2; i <= currentPage + 2; i++) {
				if (i == currentPage) {
					out.print("<li class='active'><a href='#'>"+currentPage+"</a></li>");
				} else {
					out.print("<li><a href='javascript:gotopage("+i+")'>"+i+"</a></li>");
				}
			}
		}

		if (currentPage == pages) {
			out.print("<li class='active'><a href='javascript:gotopage("
					+ (currentPage + 1) + ")'>下一页</a></li>");
			out.print("<li class='active'><a href='javascript:gotopage(" + pages
					+ ")'>尾页</a></li>");
		} else {
			out.print("<li><a href='javascript:gotopage("
					+ (currentPage + 1) + ")'>下一页</a></li>");
			out.print("<li><a href='javascript:gotopage(" + pages
					+ ")'>尾页</a></li>");
		}
		
		out.print("</ul> </div>");
	}
}
