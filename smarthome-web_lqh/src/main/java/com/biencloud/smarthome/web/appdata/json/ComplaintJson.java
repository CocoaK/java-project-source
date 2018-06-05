package com.biencloud.smarthome.web.appdata.json;

import java.util.List;
import com.biencloud.smarthome.web.customercomplaint.vo.ComplaintVo;
/**
 * 
 * 类名称：ComplaintJson 
 * 类描述： 投诉json
 * @author: ykou  
 * @version: 0.1
 * @date: 2012-5-31 下午5:42:39
 */
public class ComplaintJson extends Json{
	
	private List<ComplaintVo> complaintList;

	public List<ComplaintVo> getComplaintList() {
		return complaintList;
	}

	public void setComplaintList(List<ComplaintVo> complaintList) {
		this.complaintList = complaintList;
	}
}
