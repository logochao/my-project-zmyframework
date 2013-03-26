
package org.wendellup.core.support;

/**
 * 分页对象
 *
 */
public class Page {

	public static final int FIRST_PAGE = 1;

	public static final int DEFAULT_PAGE_SIZE = 10;
	
    public static final int FIVE_PAGE_SIZE = 5;

    public static final int EIGHT_PAGE_SIZE = 8;
    public static final int TWENTY_PAGE_SIZE = 20;
    public static final int FIFTEEN_PAGE_SIZE = 15;
	
	/**
	 * 每页有多少条记录
	 */
	private int pageSize = DEFAULT_PAGE_SIZE;
	
	/**
	 * 总页数
	 */
	private int pages;
	
	/**
	 * 当前页
	 */
	private int currentPage = 1;
	
	private int records;
	
	/**
	 * added by ixr_wang
	 */
	public Page(){
		this(FIRST_PAGE,DEFAULT_PAGE_SIZE);
	}
	public Page(int pageSize){
		this(FIRST_PAGE,pageSize);
	}
	
	public Page(int currentPage,int pageSize){
		this.currentPage = currentPage;
		this.pageSize = pageSize;
	}
	
	public Page(int currentPage,int pageSize,int records){
		this.currentPage = currentPage;
		this.pageSize = pageSize;
		this.records = records;
	}
	/**
	 * @return the pageSize
	 */
	public int getPageSize() {
		return pageSize;
	}

	/**
	 * @param pageSize the pageSize to set
	 */
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	/**
	 * @return the pages
	 */
	public int getPages() {
		return pages;
	}

	/**
	 * @param pages the pages to set
	 */
	public void setPages(int pages) {
		this.pages = pages;
	}
	
	/**
	 * @return the currentPage
	 */
	public int getCurrentPage() {
		return currentPage;
	}

	/**
	 * @param currentPage the currentPage to set
	 */
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public boolean isFirstPage() {
		return currentPage == 1;
	}
	
	public boolean isLastPage() {
		return currentPage == pages;
	}
	
	public int getStartRecord() {
		return (currentPage - 1) * pageSize;
	}
	
	public int getEndRecord(){
	    return getStartRecord() + getPageSize();
	}
	
	/**
	 * @return the records
	 */
	public int getRecords() {
		return records;
	}
	
	public void setRecords(int records) {
		this.records = records;
		int n = records % pageSize;
		if(n > 0)
			pages =  records / pageSize + 1;
		else
			pages =  records / pageSize;
		
		/*
		 * 如果当前页码小于第一页，强制将其设置成第一页
		 */
		if (currentPage < FIRST_PAGE)
			currentPage = FIRST_PAGE;

		/*
		 * 如果当前页码大于总页码，强制设置成总页码
		 */
		if (pages > 0 && currentPage > pages)
			currentPage = pages;
		
	}
	
	public int getNextPage() {
		int next = isLastPage() ? pages : currentPage + 1;
		currentPage = next;
		return next;
	}
	
	public int getPreviousPage() {
		int previous = isFirstPage() ? 1 : currentPage - 1;
		currentPage = previous;
		return previous;
	}	
	
}
