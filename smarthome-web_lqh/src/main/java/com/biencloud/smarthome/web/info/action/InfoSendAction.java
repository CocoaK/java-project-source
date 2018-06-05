package com.biencloud.smarthome.web.info.action;


import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.StringUtils;

import com.biencloud.smarthome.web.base.action.BaseAction;
import com.biencloud.smarthome.web.common.action.ActionUtils;
import com.biencloud.smarthome.web.common.util.Constants;
import com.biencloud.smarthome.web.common.vo.PagingVO;
import com.biencloud.smarthome.web.devicetype.service.IDeviceTypeService;
import com.biencloud.smarthome.web.devicetype.vo.DeviceTypeVO;
import com.biencloud.smarthome.web.info.service.IInfoReceiverService;
import com.biencloud.smarthome.web.info.service.IInfoSendService;
import com.biencloud.smarthome.web.info.vo.InfoReceiverVO;
import com.biencloud.smarthome.web.info.vo.InfoSendVO;
import com.biencloud.smarthome.web.systemgroup.service.ISystemGroupService;
import com.biencloud.smarthome.web.systemgroup.vo.SystemGroupVO;

/**
 * 
 * 项目名称：smarthome-web-new 
 * 类名称：InfoSendAction 
 * 类描述：  信息发布管理类
 * @author: kouy 
 * @version: 0.1
 * @date: 2012-6-12 下午2:14:14
 */
public class InfoSendAction extends BaseAction<InfoSendVO> {

	private static final long serialVersionUID = 1L;
	
	private static final String LIST_PAGE="list";
	
	private static final String EDIT_PAGE="editPage";

	private IInfoSendService infoSendService;
	
	private ISystemGroupService systemGroupService;
	
	private IInfoReceiverService infoReceiverService;

	public InfoSendVO infoSend;
	
	private IDeviceTypeService deviceTypeService;
	
	private List<DeviceTypeVO> deviceTypes;
	
	/**
	 * 
	 * 方法的描述: 信息发布列表
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-6-12 下午2:14:54
	 * @return
	 */
	public String queryList() throws Exception {
		PagingVO<InfoSendVO> page = getPage();
		if (page == null)
			page = new PagingVO<InfoSendVO>();
		if (infoSend == null)
			infoSend = new InfoSendVO();
		if (Constants.LOGIN_USER_TYPE_SYSTEM.equals(getUserType())) {
			infoSend.setType(InfoReceiverVO.INFO_TYPE_SYSTEM);
		} else if (Constants.LOGIN_USER_TYPE_PAUSER.equals(getUserType())) {
			infoSend.setType(InfoReceiverVO.INFO_TYPE_COMMUNITY);
			infoSend.setAreaId(new Long(getDistrictId()));
		} else if (Constants.LOGIN_USER_TYPE_OWNER.equals(getUserType())) {
			infoSend.setType(InfoReceiverVO.INFO_TYPE_PERSON);
			infoSend.setAreaId(new Long(getDistrictId()));
			infoSend.setSendUserId(new Long(getUserId()));
		}
		PagingVO<InfoSendVO> pagingVO = infoSendService.queryInfoSendVOForPaging(infoSend, page.getPageNum(), page.getPageSize());
		setPage(pagingVO);
		setRequestAttribute("LoginUserType", getUserType());
		return LIST_PAGE;
	}
	
	/**
	 * 
	 * 方法的描述: 保存信息发布
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-6-12 下午2:15:03
	 * @return
	 */
	public String save() throws Exception {
		String comStr = getRequest().getParameter("comStrSel");
		setInfoSend(comStr);
		boolean result = infoSendService.saveInfoSendVO(infoSend, comStr, getUserType(), deviceTypes);
		this.getRequest().setAttribute("editResult", result);
		infoSend = null;
		return LIST_PAGE;
	}
	/**
	 * 
	 * 方法的描述: 树异步加载房间
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-11-21 上午11:49:21
	 * @return
	 */
	public String queryHouseByCellId() throws Exception {
		String cellId = getRequest().getParameter("cellId");
		cellId = cellId.replace(IInfoSendService.TREE_CELL_FLAG, "");
		String result = infoSendService.queryHouseByCellId(cellId);
		ActionUtils.printMsg(getResponse(), result);
		return null;
	}
	
	//获取所有设备类型
	private List<DeviceTypeVO> queryAllDeviceTypes() {
		List<DeviceTypeVO> deviceTypes = getDeviceTypeService().queryAllDeviceTypes();
		for (DeviceTypeVO dt : deviceTypes) {
			dt.setDeviceName(getText(dt.getDeviceName()));
		}
		return deviceTypes;
	}
	
	/**
	 * 
	 * 方法的描述: 到更新页面
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-6-12 上午11:04:26
	 * @return
	 */
	public String goToUpdate() throws Exception {
		if(!Constants.LOGIN_USER_TYPE_OWNER.equals(getUserType())) setSendOb(false,false);
		deviceTypes=queryAllDeviceTypes();
		setRequestAttribute("LoginUserType",getUserType());
		return EDIT_PAGE;
	}
	
	/**
	 * 
	 * 方法的描述: 更新信息发布
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-6-12 下午2:15:11
	 * @return
	 */
	public String update() throws Exception {
		String comStr = getRequest().getParameter("comStrSel");
		setInfoSend(comStr);
		boolean result = infoSendService.updateInfoSendVO(infoSend, comStr, getUserType(), deviceTypes);
		this.getRequest().setAttribute("editResult", result);
		// infoSend=null;
		return LIST_PAGE;
	}
	
	/**
	 * 
	 * 方法的描述: 设置信息发布属性
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-6-12 下午2:17:32
	 * @param comStr
	 */
	private void setInfoSend(String comStr) {
		if (StringUtils.isNotBlank(comStr)) {
			if (Constants.LOGIN_USER_TYPE_SYSTEM.equals(getUserType())) {
				infoSend.setType(InfoReceiverVO.INFO_TYPE_SYSTEM);
			} else if (Constants.LOGIN_USER_TYPE_PAUSER.equals(getUserType())) {
				infoSend.setType(InfoReceiverVO.INFO_TYPE_COMMUNITY);
				infoSend.setAreaId(new Long(getDistrictId()));
			}
		} else {
			infoSend.setType(InfoReceiverVO.INFO_TYPE_PERSON);
			infoSend.setAreaId(new Long(getDistrictId()));
			infoSend.setSendMode(InfoSendVO.SENDMODE_GOING);
		}
		infoSend.setSendUserId(new Long(getUserId()));
		infoSend.setSendUserName(getLoginVO().getUserName());
	}
	
	private void setSendOb(boolean isUpdate,boolean isShowDetail){
		Object[] ob=null;
		Object treeList=null;
		Object comStr=null;
		String id=getRequest().getParameter("requestId");
		//if(StringUtils.isBlank(id)) id=infoSend.getId().toString();//更新操作返回当前更新页面
		if(Constants.LOGIN_USER_TYPE_SYSTEM.equals(getUserType())){ 
			SystemGroupVO vo=new SystemGroupVO();
			if(StringUtils.isNotBlank(id)) vo.setGroupNo(new Long(id));
			InfoReceiverVO ifrVo=new InfoReceiverVO();
			ifrVo.setReceiverType(InfoReceiverVO.INFO_TYPE_SYSTEM);
			ifrVo.setId(vo.getGroupNo());//设置所属发布信息ID
			Set<Long> receiverIds=null;
			if(isUpdate) receiverIds=infoReceiverService.queryInfoReceiverIdSet(ifrVo,true);
			ob=systemGroupService.querySystemGroupForCheck(receiverIds,true,isShowDetail);
			treeList=ob[0];
			comStr=ob[1];
		}
		else if(Constants.LOGIN_USER_TYPE_PAUSER.equals(getUserType())){
			InfoSendVO vo=new InfoSendVO();
			vo.setAreaId(new Long(getDistrictId()));
			if(StringUtils.isNotBlank(id)) vo.setId(new Long(id));
			ob=infoSendService.queryAreaData(vo,isUpdate,isShowDetail);
			treeList=ob[0];
			comStr=ob[1];
		}
		setRequestAttribute("treeList",treeList);
		setRequestAttribute("comStr",comStr);
	}
	
	
	/**
	 * 
	 * 方法的描述: 查找单个信息发布 
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-6-12 下午2:17:46
	 * @return
	 */
	public String findById() throws Exception {
		boolean isShowDetail = false;
		if (ActionUtils.judgShowDetail(getRequest())) {
			getRequest().setAttribute("isCheckBox", "0");
			isShowDetail = true;
		}
		String id = getRequest().getParameter("requestId");
		infoSend = infoSendService.getInfoSendVO(id);
		if (!Constants.LOGIN_USER_TYPE_OWNER.equals(getUserType()))
			setSendOb(true, isShowDetail);
		setRequestAttribute("LoginUserType", getUserType());
		deviceTypes = queryAllDeviceTypes();
		setRequestAttribute("dts", extractDeviceTypes(infoSendService.getReceiverDeviceType(id)));
		if (ActionUtils.judgShowDetail(getRequest()))
			return "detailPage";
		else
			return EDIT_PAGE;
	}
	
	private Set<String> extractDeviceTypes(String ids) {
		Set<String> deviceTypes = new HashSet<String>();
		if (StringUtils.isNotBlank(ids)) {
			String typeIds[] = ids.split(",");
			for (String id : typeIds) {
				deviceTypes.add(id);
			}
		}
		return deviceTypes;
	}
	
	/**
	 * 
	 * 方法的描述: 发布
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-6-12 下午2:17:53
	 * @return
	 */
	public String send() throws Exception {
		String id = getRequest().getParameter("requestId");
		boolean result = infoSendService.updateStatus(id, InfoSendVO.STATUSYESSEND, null, true);
		this.getRequest().setAttribute("editResult", result);
		return LIST_PAGE;
	}
	
	/**
	 * 
	 * 方法的描述: 审核
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-6-12 下午2:18:00
	 * @return
	 */
	public String audit() throws Exception {
		String id = getRequest().getParameter("requestId");
		boolean result = infoSendService.updateStatus(id, infoSend.getStatus(), infoSend.getReply(), false);
		this.getRequest().setAttribute("editResult", result);
		return LIST_PAGE;
	}
	
	/**
	 * 
	 * 方法的描述:  删除信息发布
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-6-12 下午2:18:11
	 * @return
	 */
	public String delById() throws Exception {
		String id = getRequest().getParameter("requestId");
		boolean result = infoSendService.delInfoSendVO(id);
		this.getRequest().setAttribute("editResult", result);
		return LIST_PAGE;
	}

	public IInfoSendService getInfoSendService() {
		return infoSendService;
	}

	public void setInfoSendService(IInfoSendService infoSendService) {
		this.infoSendService = infoSendService;
	}
	
	public InfoSendVO getInfoSend() {
		return infoSend;
	}

	public void setInfoSend(InfoSendVO infoSend) {
		this.infoSend = infoSend;
	}

	public ISystemGroupService getSystemGroupService() {
		return systemGroupService;
	}

	public void setSystemGroupService(ISystemGroupService systemGroupService) {
		this.systemGroupService = systemGroupService;
	}

	public IInfoReceiverService getInfoReceiverService() {
		return infoReceiverService;
	}

	public void setInfoReceiverService(IInfoReceiverService infoReceiverService) {
		this.infoReceiverService = infoReceiverService;
	}

	public IDeviceTypeService getDeviceTypeService() {
		return deviceTypeService;
	}

	public void setDeviceTypeService(IDeviceTypeService deviceTypeService) {
		this.deviceTypeService = deviceTypeService;
	}

	public List<DeviceTypeVO> getDeviceTypes() {
		return deviceTypes;
	}

	public void setDeviceTypes(List<DeviceTypeVO> deviceTypes) {
		this.deviceTypes = deviceTypes;
	}
	
	

	

}
