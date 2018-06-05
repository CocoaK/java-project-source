package com.biencloud.smarthome.web.appdata.json;

import java.util.List;

import com.biencloud.smarthome.web.page.vo.PageVO;

/**
 * 
 * 类名称：PageJson 
 * 类描述： 终端页面Json
 * @author: ykou  
 * @version: 0.1
 */
@SuppressWarnings("serial")
public class PageJson extends Json{
	private String type="page";
	private List<PageVO> pageList;
	private Integer pageCount;
	private String updateId;

	public List<PageVO> getPageList() {
		return pageList;
	}

	public void setPageList(List<PageVO> pageList) {
		this.pageList = pageList;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Integer getPageCount() {
		if(pageList==null){
			return 0;
		}
		return pageList.size();
	}

	public void setPageCount(Integer pageCount) {
		this.pageCount = pageCount;
	}

	public String getUpdateId() {
		return updateId;
	}

	public void setUpdateId(String updateId) {
		this.updateId = updateId;
	}

}
