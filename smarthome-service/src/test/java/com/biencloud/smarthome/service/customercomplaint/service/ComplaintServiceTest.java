package com.biencloud.smarthome.service.customercomplaint.service;

import junit.framework.Assert;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.biencloud.smarthome.service.base.BaseTest;
import com.biencloud.smarthome.service.common.model.Paging;
import com.biencloud.smarthome.service.customercomplaint.model.Complaint;
import com.biencloud.smarthome.service.login.model.Login;

/**
 * 客服投诉 Service 单元测试
 * 
 * @author jsun  
 * @since 1.0 2012-5-30
 */
public class ComplaintServiceTest extends BaseTest {
	@Autowired
	private IComplaintService service;

	@Test
	public void testAddPropertyComplaint() {
		Complaint complaint = new Complaint();
		complaint.setTitle("物业投诉标题");
		complaint.setContent("投诉内容, 很长很长....");
		// 物业投诉系统 投诉人为物业管理用户
		complaint.setComplaintLoginName("test3");
		complaint.setLocation("投诉地址");
		service.addPropertyComplaint(complaint);

		Assert.assertNotNull("新增物业投诉信息到数据库后, 投诉ID应该有值", complaint.getId());
		Assert.assertEquals("物业投诉标示为 0", "0", complaint.getType());
	}

	@Test
	public void testAddOwnerComplaint() throws Exception {
		Complaint complaint = new Complaint();
		complaint.setTitle("业主投诉标题");
		complaint.setContent("投诉内容, 很长很长....");
		// 业主投诉物业 投诉人为业主
		complaint.setComplaintLoginName("test4");
		complaint.setLocation("投诉地址");
		service.addOwnerComplaint(complaint);

		Assert.assertNotNull("新增业主投诉信息到数据库后, 投诉ID应该有值", complaint.getId());
		Assert.assertEquals("业主投诉标示为 1", "1", complaint.getType());
	}

	@Test
	public void testQueryPropertyComplaintForPaging() {
		Login complaintLogin = new Login();
//		complaintLogin.setUserName("海绵宝宝");
		complaintLogin.setUserName("三");
		Login processingLogin = new Login();
//		processingLogin.setUserName("派大星");
		processingLogin.setUserName("四");

		Complaint complaint = new Complaint();
		complaint.setComplaintLogin(complaintLogin);
		complaint.setProcessingLogin(processingLogin);

		Paging<Complaint> complaints = service.queryPropertyComplaintForPaging(complaint,
				null, null, 1, 10, true);

		logger.info("物业投诉分页查询: {}", complaints);
	}

	@Test
	public void testQueryOwnerComplaintForPaging() {
		Login complaintLogin = new Login();
		complaintLogin.setUserName("五");
		Login processingLogin = new Login();
		processingLogin.setUserName("三");

		Complaint complaint = new Complaint();
		complaint.setComplaintLogin(complaintLogin);
		complaint.setProcessingLogin(processingLogin);

		Paging<Complaint> complaints = service.queryOwnerComplaintForPaging(complaint,
				null, null, 1, 10, false);

		logger.info("业主投诉分页查询: {}", complaints);
	}
	
	@Test
	public void testReplySuggestion() {
		service.replySuggestion("7", "test1", "suggestion");
		Complaint complaint = service.get("7");
		logger.info("回复投诉: {}", complaint);
	}

	@Test
	public void testSaveComplaint() {
		service.updateComplaint("1", "new title", "new content", false);
	}

	@Test
	public void testSubmitComplaint() {
		service.updateComplaint("1", "new title", "new content", true);
	}

	@Test
	public void testQueryTodayPropertyComplaintCount() {
		Complaint complaint = new Complaint();
		// 物业投诉
		complaint.setType("0");
		logger.info("查询今日物业投诉总数: {}", service.queryComplaintCount(complaint, true));
	}

	@Test
	public void testQueryTodayPropertyComplaint() {
		Complaint complaint = new Complaint();
		// 物业投诉
		complaint.setType("0");
		// 未处理的状态
		complaint.setProcessingStatus(IComplaintService.PROCESSING_STATUS_PENDING);
		logger.info("查询今日未处理的物业投诉: {}", service.queryRecentComplaint(complaint, 5, true));
	}

	@Test
	public void testQueryTodayOwnerComplaintCount() {
		Complaint complaint = new Complaint();
		// 业主投诉
		complaint.setType("1");
		logger.info("查询今日业主投诉总数: {}", service.queryComplaintCount(complaint, true));
	}

	@Test
	public void testQueryTodayOwnerComplaint() {
		Complaint complaint = new Complaint();
		// 业主投诉
		complaint.setType("1");
		logger.info("查询今日业主投诉: {}", service.queryRecentComplaint(complaint, 5, true));
	}

	@Test
	public void testQueryOwnerComplaintCount() {
		Complaint complaint = new Complaint();
		// 业主登陆名
		complaint.setComplaintLoginName("test4");
		logger.info("查询业主本人投诉总数: {}", service.queryComplaintCount(complaint, false));
	}

	@Test
	public void testQueryOwnerRecentComplaint() {
		Complaint complaint = new Complaint();
		// 业主登陆名
		complaint.setComplaintLoginName("test4");
		logger.info("查询业主本人最近投诉: {}", service.queryRecentComplaint(complaint, 5, false));
	}

	@Test
	public void testRemoveOwnerComplaint() {
		service.removeOwnerComplaint("a1");
	}
	
	@Test
	public void testQueryComplaintByDeviceCode() {
		logger.info("根据设备编号查询业主投诉信息: {}", service.queryComplaintByDeviceCode("MC00021"));
	}
}
