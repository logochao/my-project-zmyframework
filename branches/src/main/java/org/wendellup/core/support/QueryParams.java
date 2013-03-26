
package org.wendellup.core.support;



/**
 * 查询参数基类
 *
 */
public class QueryParams<T> {
	
	/**
	 * added by ixr_wang
	 */
	private T entity;
	
	/**
	 * 排序列
	 * added by andy_luo
	 */
	private String orderColumn;
	
	/**
	 * 排序方式。只允许两个值。desc和asc
	 */
	private String orderMode = "desc";
	
	/**
	 * 可以通过该参数直接指定查询多少条。
	 */
	private int limit;
	
	private Page paging = new Page();
	
	public QueryParams(){}
	public QueryParams(T entity){
		setEntity(entity);
	}
	
	public T getEntity(){
		return entity;
	}
	/**
	 * @return the limit
	 */
	public int getLimit() {
		return limit;
	}

	public String getOrderColumn() {
		return orderColumn;
	}

	/**
	 * @return the orderMode
	 */
	public String getOrderMode() {
		return orderMode;
	}
	/**
	 * @return the paging
	 */
	public Page getPaging() {
		return paging;
	}
	public void setEntity(T entity){
		this.entity = entity;
	}
	
	/**
	 * @param limit the limit to set
	 */
	public void setLimit(int limit) {
		this.limit = limit;
	}
	
	public void setOrderColumn(String orderColumn) {
		this.orderColumn = orderColumn;
	}
	/**
	 * @param orderMode the orderMode to set
	 */
	public void setOrderMode(String orderMode) {
		this.orderMode = orderMode;
	}
	
	/**
	 * @param paging the paging to set
	 */
	public void setPaging(Page paging) {
		this.paging = paging;
	}
	
}
