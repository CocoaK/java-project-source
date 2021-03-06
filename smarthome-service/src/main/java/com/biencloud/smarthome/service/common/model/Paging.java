package com.biencloud.smarthome.service.common.model;

import java.util.ArrayList;
import java.util.List;

import com.biencloud.smarthome.service.base.model.BaseEntity;
import com.biencloud.smarthome.service.common.constants.Constants;

/**
 * 分页实体对象。
 * @author kouy
 * @since 1.0 2012-4-18
 */
public class Paging<T> extends BaseEntity {

	private static final long serialVersionUID = -4681151678305621844L;

	private int pageNum = 1;
	private int pageSize = Constants.DEFAULT_PAGE_SIZE; 
	
	private long totalCount;	
	private List<T> results = new ArrayList<T>();
	
	public Paging(){
		
	}
	
	public Paging(int pageSize, int pageNum){
		this.pageSize = pageSize;
		this.pageNum = pageNum;
	}
	
	/**
	 * 获得页码。
	 */
	public int getPageNum() {
		return pageNum;
	}
	/**
	 * 设置页码。
	 * @param pageNum 页码
	 */
	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	/**
	 * 获得每页记录数。
	 */
	public int getPageSize() {
		return pageSize;
	}
	/**
	 * 设置每页记录数。
	 * @param pageSize 每页记录数
	 */
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	/**
	 * 获得总页数。
	 * @return
	 */
	public int getTotalPages(){
		int totalPages = (int)getTotalCount()/pageSize;
		if(getTotalCount()%pageSize != 0)
			totalPages += 1;	
		return totalPages;
	}
	
	/**
	 * 获得结果记录数。
	 * @return
	 */
	public int getResultsSize(){	
		return (results == null) ? 0 : results.size();
	}
	
	/**
	 * 是否有上一页。
	 * @return
	 */
	public boolean hasPrevious(){
		return (pageNum > 1);
	}
	
	/**
	 * 是否有下一页。
	 * @return
	 */
	public boolean hasNext(){
		return (pageNum < getTotalPages());
	}
	
	/**
	 * 获得总记录数。
	 * @return
	 */
	public long getTotalCount() {
		return totalCount;
	}
	/**
	 * 设置每页记录数。
	 * @param totalCount 每页记录数
	 */
	public void setTotalCount(long totalCount) {
		this.totalCount = totalCount;
	}
	
	/**
	 * 获得查询结果。
	 * @return
	 */
	public List<T> getResults() {
		return results;
	}
	/**
	 * 设置查询结果。
	 * @param results 查询结果
	 */
	public void setResults(List<T> results) {
		this.results = results;
	}
	
	
	@Override
	public String toString() {
		return "Paging [pageNum=" + pageNum + ", pageSize=" + pageSize
				+ ", totalCount=" + totalCount + ", totalPages="
				+ getTotalPages() + ", resultsSize=" + getResultsSize()
				+ ", hasPrevious=" + hasPrevious() + ", hasNext="
				+ hasNext() + ", results="
				+ getResults() + "]";
	}
}
