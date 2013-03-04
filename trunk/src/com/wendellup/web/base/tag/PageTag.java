package com.wendellup.web.base.tag;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import org.wendellup.core.support.QueryParams;

public class PageTag extends SimpleTagSupport {

	public PageTag() {
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
		out.print("去第<input type='text' id='txt_gotopage' size='3' value='"+currentPage+"'>页&nbsp;");
		out.print("<input id='btn_gotopage' type='button' value='走你' style='border: 1px solid black;'><br />");
		
		

		if (currentPage != 1) {
			out.print("<a href='javascript:gotopage(1)'>首页</a>|");
			out.print("<a href='javascript:gotopage("
					+ (currentPage - 1) + ")'>上一页 </a> | ");
		}

		if (currentPage <= 3) {
			for (int i = 1; i <= (pages <= 5 ? pages : 5); i++) {
				if (i == currentPage) {
					out.print(i + " | ");
				} else {
					out.print("<a href='javascript:gotopage("
							+ i + ")'>" + i + "</a> | ");
				}
			}
		} else if (currentPage > 3 && pages <= (currentPage + 2)) {
			for (int i = pages - 4 < 1 ? 1 : pages - 4; i <= pages; i++) {
				if (i == currentPage) {
					out.print(i + " | ");
				} else {
					out.print("<a href='javascript:gotopage("
							+ i + ")'>" + i + "</a> | ");
				}
			}
		} else if (currentPage > 3 && pages > (currentPage + 2)) {
			for (int i = currentPage - 2; i <= currentPage + 2; i++) {
				if (i == currentPage) {
					out.print(i + " | ");
				} else {
					out.print("<a href='javascript:gotopage("
							+ i + ")'>" + i + "</a> | ");
				}
			}
		}

		if (currentPage == pages) {
		} else {
			out.print("<a href='javascript:gotopage("
					+ (currentPage + 1) + ")'>下一页 </a> | ");
			out.print("<a href='javascript:gotopage(" + pages
					+ ")'>尾页</a>|");
		}
	}
}
