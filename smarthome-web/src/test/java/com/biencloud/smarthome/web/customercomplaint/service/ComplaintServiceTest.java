package com.biencloud.smarthome.web.customercomplaint.service;

import org.junit.Test;

import com.biencloud.smarthome.web.base.BaseTest;
import com.biencloud.smarthome.web.customercomplaint.vo.ComplaintVo;

/**
 * 客服投诉Service单元测试
 * 
 * @author jsun  
 * @since 1.0 2012-6-8
 */
public class ComplaintServiceTest extends BaseTest {
	public IComplaintService getService() {
		return (IComplaintService) this.getBean("complaintService");
	}

	@Test
	public void testQueryPropertyComplaintCount() {
		ComplaintVo complaint = new ComplaintVo();
		// 物业投诉
		complaint.setType("0");
		logger.info("查询今日物业投诉总数: {}", getService().queryComplaintCount(complaint, true));
	}

	@Test
	public void testQueryOwnerRecentComplaint() {
		ComplaintVo complaint = new ComplaintVo();
		// 业主登陆名
		complaint.setComplaintLoginName("test3");
		logger.info("查询业主本人最近投诉: {}", getService().queryRecentComplaint(complaint, 5, false));
	}
	
	@Test
	public void test() {
		ComplaintVo complaint = new ComplaintVo();
		// 业主登陆名
		complaint.setComplaintLoginName("test3");
		logger.info("查询业主本人最近投诉: {}", getService().queryComplaintCount(complaint, true));
	}
}
