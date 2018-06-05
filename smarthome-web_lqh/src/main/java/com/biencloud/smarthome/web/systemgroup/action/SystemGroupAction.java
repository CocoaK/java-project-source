package com.biencloud.smarthome.web.systemgroup.action;

import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.biencloud.smarthome.web.base.action.BaseAction;
import com.biencloud.smarthome.web.common.action.ActionUtils;
import com.biencloud.smarthome.web.housemgr.service.IHousingDistrictInfoService;
import com.biencloud.smarthome.web.systemgroup.service.ISystemGroupService;
import com.biencloud.smarthome.web.systemgroup.vo.SystemGroupVO;

/**
 * 
 * 项目名称：smarthome-web-new 
 * 类名称：SystemGroupAction 
 * 类描述：  组织管理类 
 * @author: kouy 
 * @version: 0.1
 * @date: 2012-6-12 下午2:41:21
 */
public class SystemGroupAction extends BaseAction<SystemGroupVO> {
	private static final long serialVersionUID = 1L;

	private ISystemGroupService systemGroupService;

	private SystemGroupVO systemGroup;

	public String errMessage;
	
	private List<SystemGroupVO> sysgroups;
	
	private IHousingDistrictInfoService housingDistrictInfoService;

	/**
	 * 
	 * 方法的描述: 查询组织结构列表(列表管理页面)
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-6-12 下午2:41:32
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String queryList() throws Exception {
		String groupName = getParameter("groupName");
		String areaName = (String) this.getRequest().getAttribute("groupName");
		if (!StringUtils.isBlank(groupName)) {
			SystemGroupVO vo = new SystemGroupVO();
			vo.setGroupName(groupName);
			if (!StringUtils.isBlank(areaName))
				vo.setDeep(SystemGroupVO.DEEP_COMMUNITY);
			String result = systemGroupService.querySystemGroupByParams(vo);
			this.getResponse().setContentType("text/html; charset=UTF-8");
			PrintWriter out = this.getResponse().getWriter();
			out.write(result);
			out.close();
			return null;
		} else {
			Object[] ob = systemGroupService.querySystemGroupForCheck(null, false, false);
			List<Map<String, String>> treeList = (List<Map<String, String>>) ob[0];
			this.getRequest().setAttribute("treeList", treeList);
			String groupNo = getRequest().getParameter("groupNo");
			this.getRequest().setAttribute("groupNoForList", groupNo);
			return "list";
		}
	}
	
	/**
	 * 
	 * 方法的描述:查询小区列表 
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-6-12 下午2:41:41
	 * @return
	 */
	public String queryComList() throws Exception {
		String groupName = getParameter("groupName");
		getRequest().setAttribute("groupName", groupName);
		return queryList();
	}
	
	/**
	 * 
	 * 方法的描述:判断小区是否已生成区域
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-6-12 下午2:41:41
	 * @return
	 */
	public String validateIsGenratorRegion() throws Exception {
		String backMsg = "false";
		String groupId = getRequest().getParameter("groupId");
		String districtId = housingDistrictInfoService.getDistrictIdByGroupNo(new Long(groupId));
		if (StringUtils.isBlank(districtId))
			backMsg = "true";
		else {
			boolean result = housingDistrictInfoService.hasRegion(districtId);
			if (!result)
				backMsg = "true";
		}
		ActionUtils.printMsg(getResponse(), backMsg);
		return null;
	}
	
	/**
	 * 
	 * 方法的描述: 查询小区列表，返回Json格式
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-6-12 下午2:41:48
	 * @return
	 */
	public String queryListForJson() throws Exception {
		String groupName = getParameter("groupName");
		getRequest().setAttribute("groupName", groupName);
		SystemGroupVO vo = new SystemGroupVO();
		if (!StringUtils.isBlank(groupName)) {
			vo.setGroupName(groupName);
		}
		vo.setDeep(SystemGroupVO.DEEP_COMMUNITY);
		List<SystemGroupVO> result = systemGroupService.queryListByParams(vo);
		setSysgroups(result);
		return SUCCESS;
	}
	

	/**
	 * 
	 * 方法的描述: 保存或更新组织结构
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-6-12 下午2:41:56
	 * @return
	 */
	public String editData() throws Exception {
		this.getRequest().setAttribute("hanlerGroupNo", systemGroup.getGroupNo() == null ? systemGroup.getGroupParentNo() == "" ? null : systemGroup.getGroupParentNo() : systemGroup.getGroupNo());
		SystemGroupVO ob = null;
		if (systemGroup.getGroupNo() == null) {
			if (StringUtils.isBlank(systemGroup.getGroupParentNo()))
				ob = systemGroupService.getSystemGroupVO(null, null, SystemGroupVO.Deep_Root.shortValue(), systemGroup.getGroupName());// 添加国家
			else {
				ob = systemGroupService.getSystemGroupVO(systemGroup.getGroupParentNo(), null, null, null);
				Short deep = ob.getDeep();
				ob = systemGroupService.getSystemGroupVO(null, null, ++deep, systemGroup.getGroupName());
			}
		} else {
			ob = systemGroupService.getSystemGroupVO(systemGroup.getGroupNo().toString(), null, null, null);
			if (!ob.getGroupName().equals(systemGroup.getGroupName())) {
				ob = systemGroupService.getSystemGroupVO(null, null, ob.getDeep(), systemGroup.getGroupName());
			} else
				ob = null;
		}
		if (ob != null) {
			this.getRequest().setAttribute("groupNameExist", true);
			return "list";
		}
		boolean result = systemGroupService.saveOrUpdateSystemGroup(systemGroup);
		this.getRequest().setAttribute("editResult", result);
		// return this.queryList();
		// this.getServletContext().getRequestDispatcher("/systemGroupAction!queryList.action?editResult="+result).forward(getRequest(),
		// getResponse());
		// this.getResponse().sendRedirect(this.getRequest().getContextPath()+"/systemGroupAction!queryList.action?editResult="+result);
		return "list";
	}

	/**
	 * 
	 * 方法的描述: 删除组织
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-6-12 下午2:42:07
	 * @return
	 */
	public String DelData() throws Exception {
		boolean result = systemGroupService.deleteSystemGroupById(Long.parseLong(getParameter("optId")));
		this.getRequest().setAttribute("delResult", result);
		this.getRequest().setAttribute("hanlerGroupNo", getRequest().getParameter("gropuParentNoselected"));
		return "list";
	}

	public String getErrMessage() {
		return errMessage;
	}

	public void setErrMessage(String errMessage) {
		this.errMessage = errMessage;
	}

	public ISystemGroupService getSystemGroupService() {
		return systemGroupService;
	}

	public void setSystemGroupService(ISystemGroupService systemGroupService) {
		this.systemGroupService = systemGroupService;
	}

	public SystemGroupVO getSystemGroup() {
		return systemGroup;
	}

	public void setSystemGroup(SystemGroupVO systemGroup) {
		this.systemGroup = systemGroup;
	}

	public List<SystemGroupVO> getSysgroups() {
		return sysgroups;
	}

	public void setSysgroups(List<SystemGroupVO> sysgroups) {
		this.sysgroups = sysgroups;
	}

	public IHousingDistrictInfoService getHousingDistrictInfoService() {
		return housingDistrictInfoService;
	}

	public void setHousingDistrictInfoService(IHousingDistrictInfoService housingDistrictInfoService) {
		this.housingDistrictInfoService = housingDistrictInfoService;
	}
	
	

}
