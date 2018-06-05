package com.biencloud.smarthome.web.customercomplaint.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import net.sf.json.JSONObject;

import com.biencloud.smarthome.web.appdata.constant.AppDataConstant;
import com.biencloud.smarthome.web.appdata.json.Json;
import com.biencloud.smarthome.web.base.service.BaseService;
import com.biencloud.smarthome.web.common.util.DateTimeUtil;
import com.biencloud.smarthome.web.common.util.JsonUtil;
import com.biencloud.smarthome.web.common.vo.PagingVO;
import com.biencloud.smarthome.web.customercomplaint.service.IComplaintService;
import com.biencloud.smarthome.web.customercomplaint.vo.ComplaintVo;
import com.biencloud.smarthome.web.housemgr.service.ICellHouseholdInfoService;
import com.biencloud.smarthome.web.housemgr.vo.CellHouseholdInfoVo;
import com.biencloud.smarthome.web.login.vo.LoginVO;
import com.biencloud.smarthome.web.wsclient.stub.Complaint;
import com.biencloud.smarthome.web.wsclient.stub.Device;
import com.biencloud.smarthome.web.wsclient.stub.Login;
import com.biencloud.smarthome.web.wsclient.stub.OwnerUser;
import com.biencloud.smarthome.web.wsclient.stub.Paging;

/**
 * 客服投诉Service实现类
 * 
 * @author jsun  
 * @since 1.0 2012-5-30
 */
public class ComplaintServiceImpl extends BaseService<ComplaintVo> implements IComplaintService {
	private String complaintDate = "complaintDate";
	private String processingDate = "processingDate";
	private String complaintLogin = "complaintLogin";
	private String processingLogin = "processingLogin";

	private String createdTime = "createdTime";
	private String lastLoginTime = "lastLoginTime";
	
	private ICellHouseholdInfoService cellHouseholdInfoService;
	
	@Override
	public PagingVO<ComplaintVo> queryPropertyComplaintForPaging(ComplaintVo condition,
			Date endComplaintDate, Date endProcessingDate, int pageNum, int pageSize,
			boolean excludeUncommitted) {
		Complaint model = complaint2ModelFull(condition);

		Paging paging = getSmartHomeService().queryPropertyComplaintForPaging(model,
				convertToXMLGregorianCalendar(endComplaintDate),
				convertToXMLGregorianCalendar(endProcessingDate), pageNum, pageSize,
				excludeUncommitted);

		return convert2PagingVo(paging);
	}

	private Complaint complaint2ModelFull(ComplaintVo vo) {
		Complaint model = new Complaint();
		if (vo == null) {
			return model;
		}

		copyProperties(vo, model, new String[] {complaintLogin, processingLogin},
				false, complaintDate, processingDate);

		// 转换LoginVo -> Login
		if (vo.getComplaintLogin() != null) {
			Login login = new Login();
			login.setUserName(vo.getComplaintLogin().getUserName());
			model.setComplaintLogin(login);
		}
		if (vo.getProcessingLogin() != null) {
			Login login = new Login();
			login.setUserName(vo.getProcessingLogin().getUserName());
			model.setProcessingLogin(login);
		}

		return model;
	}

	private PagingVO<ComplaintVo> convert2PagingVo(Paging paging) {
		List<Object> complaints = paging.getResults();

		PagingVO<ComplaintVo> pagingVo = convertToVO(paging,
				new String[] {complaintLogin, processingLogin}, complaintDate, processingDate);
		List<ComplaintVo> complaintVos = pagingVo.getResults();
		if(complaintVos!=null&&complaintVos.size()>0){
			for (int i = 0, length = complaintVos.size(); i < length; i++) {
				Complaint complaint = (Complaint) complaints.get(i);
				ComplaintVo complaintVo = complaintVos.get(i);
				convert2LoginVo(complaint, complaintVo);
			}
		}

		return pagingVo;
	}
	
	@Override
	public PagingVO<ComplaintVo> queryOwnerComplaintForPaging(ComplaintVo condition,
			Date endComplaintDate, Date endProcessingDate, int pageNum, int pageSize,
			boolean excludeUncommitted) {
		Complaint model = complaint2ModelFull(condition);

		Paging paging = getSmartHomeService().queryOwnerComplaintForPaging(model,
				convertToXMLGregorianCalendar(endComplaintDate),
				convertToXMLGregorianCalendar(endProcessingDate), pageNum, pageSize,
				excludeUncommitted);

		return convert2PagingVo(paging);
	}
	
	private void convert2LoginVo(Complaint complaint, ComplaintVo complaintVo) {
		Login complaintLogin = complaint.getComplaintLogin();
		if (complaintLogin != null) {
			LoginVO complaintLoginVo = new LoginVO();
			copyProperties(complaintLogin, complaintLoginVo, true, createdTime, lastLoginTime);
			complaintVo.setComplaintLogin(complaintLoginVo);
		}

		Login processingLogin = complaint.getProcessingLogin();
		if (processingLogin != null) {
			LoginVO processingLoginVo = new LoginVO();
			copyProperties(processingLogin, processingLoginVo, true, createdTime, lastLoginTime);
			complaintVo.setProcessingLogin(processingLoginVo);
		}
	}

	@Override
	public ComplaintVo getComplaint(String id) {
		Complaint complaint = getSmartHomeService().getComplaint(id);
		ComplaintVo complaintVo = new ComplaintVo();

		copyProperties(complaint, complaintVo, new String[] {complaintLogin, processingLogin},
				true, complaintDate, processingDate);
		convert2LoginVo(complaint, complaintVo);
		return complaintVo;
	}
	
	@Override
	public String getFullLocation(String houseId) {
		CellHouseholdInfoVo dVo=cellHouseholdInfoService.get(houseId);
		return dVo.getTHmBuildingCellInfo().getTHmRegionBuildingInfo().getTHmHousingDistrictRegionInfo().getName()+"-"+dVo.getTHmBuildingCellInfo().getTHmRegionBuildingInfo().getName()+"-"+dVo.getTHmBuildingCellInfo().getName()+"-"+dVo.getName();
	}

	@Override
	public void replySuggestion(String id, String processingLoginName, String suggestion) {
		getSmartHomeService().replySuggestion(id, processingLoginName, suggestion);
	}

	@Override
	public void addPropertyComplaint(ComplaintVo complaint) {
		getSmartHomeService().addPropertyComplaint(complaint2Model(complaint));
	}

	@Override
	public void addOwnerComplaint(ComplaintVo complaint) throws Exception {
		getSmartHomeService().addOwnerComplaint(complaint2Model(complaint));
	}

	@Override
	public void updateComplaint(String id, String title, String content, boolean submit) {
		getSmartHomeService().updateComplaint(id, title, content, submit);
	}

	@Override
	public void remove(String id) {
		getSmartHomeService().removeComplaint(id);
	}

	private Complaint complaint2Model(ComplaintVo complaint) {
		Complaint model = new Complaint();
		copyProperties(complaint, model,
				new String[] { complaintLogin, processingLogin, complaintDate, processingDate });

		return model;
	}

	@Override
	public long queryComplaintCount(ComplaintVo condition, boolean today) {
		return getSmartHomeService().queryComplaintCount(complaint2Model(condition), today);
	}

	@Override
	public List<ComplaintVo> queryRecentComplaint(ComplaintVo condition, int quantity, boolean today) {
		List<Complaint> list = getSmartHomeService().queryRecentComplaint(complaint2Model(condition), quantity, today);

		List<ComplaintVo> complaintVos = new ArrayList<ComplaintVo>();
		if(list!=null&&list.size()>0){
			for (int i = 0, length = list.size(); i < length; i++) {
				Complaint complaint = (Complaint) list.get(i);
				ComplaintVo complaintVo = new ComplaintVo();

				copyProperties(complaint, complaintVo, new String[] {complaintLogin, processingLogin},
						true, complaintDate, processingDate);
				convert2LoginVo(complaint, complaintVo);
				complaintVos.add(complaintVo);
			}
		}

		return complaintVos;
	}

	@Override
	public Json saveAppComplaint(String json) {
		Json js = new Json();
		Complaint complaint = new Complaint();
		JSONObject jsonObj = JsonUtil.jsonStringToJsonObject(json);
		String deviceCode = jsonObj.getString("deviceNo");
		int appId = jsonObj.getInt("id");
		String content = jsonObj.getString("content");
		Long complaintDate = jsonObj.getLong("creatTime");
		String title = jsonObj.getString("title");
		String onlyValue = jsonObj.getString("onlyValue");
		
		Device device = getSmartHomeService().queryDeviceByCode(deviceCode);
		OwnerUser ownerUser = new OwnerUser();
		String districtId=null;
		if(device.getCellHouseholdInfo() != null && device.getCellHouseholdInfo().getOwner() != null){
			ownerUser = getSmartHomeService().getOwnerUserDetail(device.getCellHouseholdInfo().getOwner().getUserId());
		}
		if(device.getHousingDistrictInfo()!= null ){
			districtId = device.getHousingDistrictInfo().getId();
		}
		complaint.setComplaintDate(DateTimeUtil.convertDateToXMLGregorianCalendar(new Date(complaintDate)));
		complaint.setComplaintLogin(ownerUser.getLogin());
		if(ownerUser.getLogin() != null){
			complaint.setComplaintLoginName(ownerUser.getLogin().getLoginName());
		}
		complaint.setHouseId(device.getCellHouseholdInfo().getId());
		complaint.setLocation(getFullLocation(device.getCellHouseholdInfo().getId()));
		complaint.setContent(content);
		complaint.setAppId(appId);
		complaint.setDistrictId(districtId);
		//complaint.setLocation(value);
		complaint.setProcessingStatus("0");
		complaint.setTitle(title);
		complaint.setOnlyValue(onlyValue);
		try{
			getSmartHomeService().addOwnerComplaint(complaint);
			js.setCode(AppDataConstant.SUCCESS);
		}catch(Exception e){
			js.setCode(AppDataConstant.FAILTRUE);
		}
		js.setDeviceNo(deviceCode);
		return js;
	}

	@Override
	public void removeOwnerComplaint(String onlyValue) {
		getSmartHomeService().removeOwnerComplaint(onlyValue);
	}

	@Override
	public List<ComplaintVo> queryComplaintByDeviceCode(String deviceCode) {
		List<Complaint> list = getSmartHomeService().queryComplaintByDeviceCode(deviceCode);
		List<ComplaintVo> complaintVos = new ArrayList<ComplaintVo>();

		if (list != null) {
			for (int i = 0, length = list.size(); i < length; i++) {
				Complaint complaint = (Complaint) list.get(i);
				ComplaintVo complaintVo = new ComplaintVo();

				copyProperties(complaint, complaintVo, new String[] {complaintLogin, processingLogin},
						true, complaintDate, processingDate);
				convert2LoginVo(complaint, complaintVo);
				complaintVos.add(complaintVo);
			}
		}

		return complaintVos;
	}

	public ICellHouseholdInfoService getCellHouseholdInfoService() {
		return cellHouseholdInfoService;
	}

	public void setCellHouseholdInfoService(ICellHouseholdInfoService cellHouseholdInfoService) {
		this.cellHouseholdInfoService = cellHouseholdInfoService;
	}
}
