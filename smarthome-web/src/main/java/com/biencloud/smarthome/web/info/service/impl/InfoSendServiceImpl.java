package com.biencloud.smarthome.web.info.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;

import com.biencloud.smarthome.web.base.service.BaseService;
import com.biencloud.smarthome.web.common.util.Constants;
import com.biencloud.smarthome.web.common.vo.PagingVO;
import com.biencloud.smarthome.web.devicetype.vo.DeviceTypeVO;
import com.biencloud.smarthome.web.housemgr.service.ICellHouseholdInfoService;
import com.biencloud.smarthome.web.housemgr.service.IHousingDistrictInfoService;
import com.biencloud.smarthome.web.housemgr.vo.CellHouseholdInfoVo;
import com.biencloud.smarthome.web.info.service.IInfoReceiverService;
import com.biencloud.smarthome.web.info.service.IInfoSendService;
import com.biencloud.smarthome.web.info.vo.InfoReceiverVO;
import com.biencloud.smarthome.web.info.vo.InfoSendVO;
import com.biencloud.smarthome.web.user.service.IOwnerUserService;
import com.biencloud.smarthome.web.user.vo.OwnerUserVO;
import com.biencloud.smarthome.web.wsclient.stub.DistrictData;
import com.biencloud.smarthome.web.wsclient.stub.InfoReceiver;
import com.biencloud.smarthome.web.wsclient.stub.InfoReceiverDevice;
import com.biencloud.smarthome.web.wsclient.stub.InfoSend;
import com.biencloud.smarthome.web.wsclient.stub.Paging;

/**
 * 
 * 项目名称：smarthome-web-new 类名称：InfoSendServiceImpl 类描述：信息发布管理领域服务接口
 * 
 * @author: kouy
 * @version: 0.1
 * @date: 2012-6-12 下午2:06:51
 */
public class InfoSendServiceImpl extends BaseService<InfoSendVO> implements IInfoSendService {

	private IInfoReceiverService infoReceiverService;
	private IHousingDistrictInfoService housingDistrictInfoService;
	private ICellHouseholdInfoService cellHouseholdInfoService;
	private IOwnerUserService ownerUserService;

	public static final String[] TIMES = { "sendTime", "timimgTime", "sendStartTime", "sendEndTime", "timimgTimeStartTime", "timimgTimeEndTime", "createTime", "updateTime" };
	public static final String[] IGNOREPRO_PERTIES = { "infoReceivers" };
	public static final String splitFlag = "A";

	@Override
	public PagingVO<InfoSendVO> queryInfoSendVOForPaging(InfoSendVO paramsOb, int pageNum, int pageSize) {
		InfoSend ob = new InfoSend();
		if (paramsOb != null) {
			ob = covertVotoOb(paramsOb);
		}
		Paging paging = getSmartHomeService().queryInfoSendForPaging(ob, pageNum, pageSize);
		PagingVO<InfoSendVO> pagingVO = convertToVO(paging, IGNOREPRO_PERTIES, TIMES);
		List<Object> list = paging.getResults();
		if (list != null && list.size() > 0) {
			List<InfoSendVO> results = new ArrayList<InfoSendVO>();
			for (int index = 0, size = list.size(); index < size; index++) {
				results.add(covertObtoVo((InfoSend) list.get(index)));
			}
			pagingVO.setResults(results);
		}
		return pagingVO;
	}

	@Override
	public boolean updateInfoSendVO(InfoSendVO entity, String comStr, String loginUserType, List<DeviceTypeVO> deviceTypes) {
		try {
			Integer receiverStatus = setInfoSendVO(entity, loginUserType, true);
			InfoSend ob = covertVotoOb(entity);
			setInfoSend(ob, comStr, receiverStatus, loginUserType);
			String deviceTypeIds = "";
			if (deviceTypes != null && !deviceTypes.isEmpty()) {
				for (int i = 0; i < deviceTypes.size(); i++) {
					deviceTypeIds += deviceTypes.get(i).getDeviceType() + ",";
				}
			}
			getSmartHomeService().updateInfoSend(ob, deviceTypeIds);
			return true;
		} catch (Exception e) {
			logger.error("********************发生异常:{}", e);
		}
		return false;
	}

	private Integer setInfoSendVO(InfoSendVO entity, String loginUserType, boolean isUpdate) {
		java.sql.Date now = new java.sql.Date(System.currentTimeMillis());
		Integer receiverStatus = InfoReceiverVO.STATUSNO;
		if (entity.getStatus() == InfoSendVO.STATUSNOSEND) {// 用户选择保存操作，暂不发布
			receiverStatus = InfoReceiverVO.STATUSNOREAD;
		} else {// 用户选择提交，马上发布
			if (Constants.LOGIN_USER_TYPE_OWNER.equals(loginUserType)) {// 用户登录
				entity.setStatus(InfoSendVO.STATUSNOAUDIT);
				receiverStatus = InfoReceiverVO.STATUSNOREAD;
			} else
				entity.setSendTime(now);
		}
		if (!isUpdate)
			entity.setCreateTime(now);
		return receiverStatus;
	}

	@Override
	public boolean saveInfoSendVO(InfoSendVO entity, String comStr, String loginUserType, List<DeviceTypeVO> deviceTypes) {
		try {
			Integer receiverStatus = setInfoSendVO(entity, loginUserType, false);
			InfoSend ob = covertVotoOb(entity);
			setInfoSend(ob, comStr, receiverStatus, loginUserType);
			String deviceTypeIds = "";
			if (deviceTypes != null && !deviceTypes.isEmpty()) {
				for (int i = 0; i < deviceTypes.size(); i++) {
					deviceTypeIds += deviceTypes.get(i).getDeviceType() + ",";
				}
			}
			getSmartHomeService().saveInfoSend(ob, deviceTypeIds);
			return true;
		} catch (Exception e) {
			logger.error("********************发生异常:{}", e);
		}
		return false;
	}

	private void setInfoSend(InfoSend entity, String comStr, Integer receiverStatus, String loginUserType) {
		// List<InfoReceiver> resultList=new ArrayList<InfoReceiver>();
		List<InfoReceiver> resultList = entity.getInfoReceivers();
		if (Constants.LOGIN_USER_TYPE_OWNER.equals(loginUserType)) {// 用户登录
			InfoReceiver vo = new InfoReceiver();
			vo.setAreaId(entity.getAreaId());
			vo.setReceiverType(entity.getType());
			vo.setStatus(receiverStatus);
			resultList.add(vo);
		} else {// 物业管理员或系统用户登录
			String[] comIds = comStr.split(",");
			for (int i = 0; i < comIds.length; i++) {
				if (Constants.LOGIN_USER_TYPE_PAUSER.equals(loginUserType)) {// 物业管理员登录
					if(comIds[i].indexOf(IInfoSendService.TREE_CELL_FLAG)!=-1){//该ID是单元ID，用户是在没有加载单元下面的房间直接选中单元,房间ID格式：IInfoSendService.TREE_CELL_FLAG+单元ID
						String cellId=comIds[i].replace(IInfoSendService.TREE_CELL_FLAG, "");
						List<CellHouseholdInfoVo> list = cellHouseholdInfoService.queryListByCellId(cellId);
						if (list != null && list.size() > 0) {
							for (int j = 0; j < list.size(); j++) {
								CellHouseholdInfoVo ob = list.get(j);
								setInfoReceiverByCell(resultList, ob.getId(), receiverStatus, entity);
							}
						}
					}else{//该ID是单元房间ID,房间ID格式：房间ID+A+用户ID
						setInfoReceiverByCell(resultList, (comIds[i].split(splitFlag))[0], receiverStatus, entity);
					}
				}else{//系统管理员
					InfoReceiver vo = new InfoReceiver();
					vo.setAreaId(new Long(housingDistrictInfoService.getDistrictIdByGroupNo(new Long(comIds[i]))));
					vo.setStatus(receiverStatus);
					vo.setReceiverType(entity.getType());
					// 设置接收者名称，待补全
					resultList.add(vo);
				}
				
			}
		}
		// copyProperty(entity, "infoReceivers", resultList);
		// entity.setInfoReceivers(resultList);
	}
	
	private void setInfoReceiverByCell(List<InfoReceiver> resultList,String houseId,Integer receiverStatus,InfoSend entity){
		/*
		 * if(StringUtils.isBlank(userId)||"null".equals(userId)){//
		 * 过滤掉房号没有业主的记录 isAdd=false; }
		 */
		InfoReceiver vo = new InfoReceiver();
		vo.setHouseId(new Long(houseId));
		vo.setAreaId(entity.getAreaId());
		vo.setStatus(receiverStatus);
		vo.setReceiverType(entity.getType());
		// 设置接收者名称，待补全
		resultList.add(vo);
	}

	@Override
	public boolean delInfoSendVO(String id) {
		try {
			getSmartHomeService().delInfoSend(getSmartHomeService().getInfoSend(id));
			return true;
		} catch (Exception e) {
			logger.error("********************发生异常:{}", e);
		}
		return false;
	}

	@Override
	public InfoSendVO getInfoSendVO(String entityId) {
		InfoSend ob = getSmartHomeService().getInfoSend(entityId);
		return covertObtoVo(ob);
	}

	@Override
	public String getReceiverDeviceType(String infoId) {
		String result = "";
		InfoReceiverDevice ob = new InfoReceiverDevice();
		InfoSend info = new InfoSend();
		info.setId(new Long(infoId));
		ob.setInfoSend(info);
		List<InfoReceiverDevice> list = getSmartHomeService().queryInfoReceiverDeviceForList(ob);
		if (list!=null&&!list.isEmpty())
			result = list.get(0).getDeviceTypeId();
		return result;
	}

	@Override
	public boolean updateStatus(String entityId, Integer status, String reply, boolean isSend) {
		try {
			InfoSend ob = getSmartHomeService().getInfoSend(entityId);
			ob.setStatus(status);
			if (StringUtils.isNotBlank(reply))
				ob.setReply(reply);
			if (isSend) {
				java.sql.Date now = new java.sql.Date(System.currentTimeMillis());
				ob.setSendTime(this.convertToXMLGregorianCalendar(now));
				List<InfoReceiver> list = ob.getInfoReceivers();
				if (list != null && list.size() > 0) {
					for (int i = 0; i < list.size(); i++) {
						InfoReceiver irOb = list.get(i);
						irOb.setStatus(InfoReceiverVO.STATUSNO);
					}
				}
			}
			getSmartHomeService().updateInfoSend(ob, null);
			return true;
		} catch (Exception e) {
			logger.error("********************发生异常:{}", e);
		}
		return false;
	}

	@Override
	public List<InfoSendVO> queryInfoSendForList(InfoSendVO paramsOb) {
		// TODO Auto-generated method stub
		return null;
	}

	public void copyPropertiesObToVo(InfoSend ob, InfoSendVO vo) {
		this.copyProperties(ob, vo, IGNOREPRO_PERTIES, true, TIMES);
	}

	public void copyPropertiesVoToOb(InfoSendVO vo, InfoSend ob) {
		this.copyProperties(vo, ob, IGNOREPRO_PERTIES, false, TIMES);
	}

	private InfoSendVO covertObtoVo(InfoSend dnoOb) {
		InfoSendVO result = new InfoSendVO();
		copyPropertiesObToVo(dnoOb, result);
		// 转换列表
		List<InfoReceiver> ctrObList = dnoOb.getInfoReceivers();
		List<InfoReceiverVO> ctrVoList = new ArrayList<InfoReceiverVO>();
		if (ctrObList != null && ctrObList.size() > 0) {
			for (int i = 0; i < ctrObList.size(); i++) {
				InfoReceiverVO ctrVo = new InfoReceiverVO();
				getInfoReceiverService().copyPropertiesObToVo(ctrObList.get(i), ctrVo);
				ctrVoList.add(ctrVo);
			}
			result.setInfoReceivers(ctrVoList);
		}
		return result;
	}

	private InfoSend covertVotoOb(InfoSendVO entity) {
		InfoSend result = new InfoSend();
		copyPropertiesVoToOb(entity, result);
		return result;
	}

	public IInfoReceiverService getInfoReceiverService() {
		return infoReceiverService;
	}

	public void setInfoReceiverService(IInfoReceiverService infoReceiverService) {
		this.infoReceiverService = infoReceiverService;
	}

	@Override
	public Object[] queryAreaData(InfoSendVO vo, boolean isUpdate, boolean isShowDetail) {
		Object result[] = new Object[2];
		InfoSend isOb = new InfoSend();
		isOb.setAreaId(vo.getAreaId());
		List<DistrictData> list = getSmartHomeService().queryAreaData(isOb);
		List<Map<String, String>> treeList = new ArrayList<Map<String, String>>();
		StringBuilder sp = new StringBuilder();
		Set<Long> receiverIds = null;
		if (isUpdate) {
			InfoReceiverVO ifrVo = new InfoReceiverVO();
			ifrVo.setReceiverType(InfoReceiverVO.INFO_TYPE_COMMUNITY);
			ifrVo.setId(vo.getId());// 设置所属发布信息ID
			receiverIds = infoReceiverService.queryInfoReceiverIdSet(ifrVo, false);
		}
		Set<String> set = new HashSet<String>();
		if (list != null && list.size() > 0) {
			for (DistrictData ob : list) {
				// if(!ob.getUserId().equals("null")){
				Map<String, String> treeMap = new HashMap<String, String>();
				String districtId =  "0052460"+ob.getDistrictId();
				String regionId = "0052461"+ob.getRegionId();
				String getBuildingId = "0052462"+ob.getBuildingId();
				String cellId = TREE_CELL_FLAG+ob.getCellId();
				String userId = ob.getUserId().equals("null") ? "" : ob.getUserId();
				if (!set.contains(districtId)) {
					treeMap.put("CurrentNodeNo", districtId);
					treeMap.put("ParentNodeNo", "0");
					treeMap.put("Nodename", ob.getDistrictName());
				}
				Map<String, String> treeMap2 = new HashMap<String, String>();
				if (!set.contains(regionId)) {
					treeMap2.put("CurrentNodeNo", regionId);
					treeMap2.put("ParentNodeNo", districtId);
					treeMap2.put("Nodename", ob.getRegionName());
				}
				Map<String, String> treeMap3 = new HashMap<String, String>();
				if (!set.contains(getBuildingId)) {
					treeMap3.put("CurrentNodeNo", getBuildingId);
					treeMap3.put("ParentNodeNo", regionId);
					treeMap3.put("Nodename", ob.getBuildingName());
				}
				Map<String, String> treeMap4 = new HashMap<String, String>();
				if (!set.contains(cellId)) {
					treeMap4.put("CurrentNodeNo", cellId);
					treeMap4.put("ParentNodeNo", getBuildingId);
					treeMap4.put("Nodename", ob.getCellName());
				}
				Map<String, String> treeMap5 = new HashMap<String, String>();
				treeMap5.put("CurrentNodeNo", ob.getHouseId() + splitFlag + userId);
				// treeMap5.put("CurrentNodeNo",ob.getHouseId());
				treeMap5.put("ParentNodeNo", cellId);
				treeMap5.put("Nodename", ob.getHouseName());
				treeMap5.put("userId", userId);
				sp.append("-" + ob.getHouseId().toString() + splitFlag + userId + "-");
				// sp.append("-"+ob.getHouseId().toString()+"-");
				String checked = "0";
				if (isUpdate) {
					if (receiverIds != null && receiverIds.contains(new Long(ob.getHouseId()))) {
						checked = "1";
					}
				}
				if (!set.contains(districtId)) {
					treeMap.put("checked", "0");
					treeList.add(treeMap);
					set.add(districtId);
				}
				if (!set.contains(regionId)) {
					treeMap2.put("checked", "0");
					treeList.add(treeMap2);
					set.add(regionId);
				}
				if (!set.contains(getBuildingId)) {
					treeMap3.put("checked", "0");
					treeList.add(treeMap3);
					set.add(getBuildingId);
				}
				if (!set.contains(cellId)) {
					treeMap4.put("checked", "0");
					treeList.add(treeMap4);
					set.add(cellId);
				}
				treeMap5.put("checked", checked);
				if ((isShowDetail || isUpdate) && "1".equals(checked)) {
					treeList.add(treeMap5);
				}
				/*
				 * boolean isAdd=false; if(isShowDetail&&"1".equals(checked)){
				 * isAdd=true; } if(!isShowDetail||isAdd) treeList.add(treeMap5);
				 */
				// }
			}
		}
		result[0] = treeList;
		result[1] = sp.toString();
		return result;
	}

	@Override
	public String queryHouseByCellId(String cellId) {
		StringBuilder result = new StringBuilder();
		List<CellHouseholdInfoVo> list = cellHouseholdInfoService.queryListByCellId(cellId);
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				CellHouseholdInfoVo ob = list.get(i);
				String userId = "";
				OwnerUserVO ou = ownerUserService.getUserByHouseId(ob.getId());
				if (ou != null)
					userId = ou.getUserId();
				String houseId = ob.getId() + splitFlag + userId;
				result.append(houseId + "," + ob.getName() + "#");
			}
		}
		return result.toString();
	}

	/**
	 * 登录成功首页查询相关信息发布
	 * 
	 * @param device
	 *            信息发布对象
	 * @return
	 */
	@Override
	public List<InfoSendVO> queryInfoSendForIndex(String loginUserType, String userId, String districtId) {
		List<InfoSendVO> results = new ArrayList<InfoSendVO>();
		InfoSend paramsOb = new InfoSend();
		if (Constants.LOGIN_USER_TYPE_OWNER.equals(loginUserType)) {// 业主登录
			paramsOb.setType(InfoReceiverVO.INFO_TYPE_PERSON);
			paramsOb.setSendUserId(new Long(userId));
		} else if (Constants.LOGIN_USER_TYPE_PAUSER.equals(loginUserType)) {// 物业登录
			paramsOb.setType(InfoReceiverVO.INFO_TYPE_COMMUNITY);
			paramsOb.setAreaId(new Long(districtId));
		} else if (Constants.LOGIN_USER_TYPE_SYSTEM.equals(loginUserType)) {// 系统管理员登录
			paramsOb.setType(InfoReceiverVO.INFO_TYPE_SYSTEM);
		}
		List<InfoSend> list = getSmartHomeService().queryInfoSendForIndex(paramsOb);
		if (list != null && list.size() > 0) {
			for (int index = 0, size = list.size(); index < size; index++) {
				results.add(covertObtoVo((InfoSend) list.get(index)));
			}
		}
		return results;
	}

	/**
	 * 登录成功首页查询相关信息发布数量统计
	 * 
	 * @param device
	 *            信息发布对象
	 * @return
	 */
	@Override
	public Long getInfoCount(String loginUserType, String userId, String districtId) {
		InfoSend paramsOb = new InfoSend();
		if (Constants.LOGIN_USER_TYPE_OWNER.equals(loginUserType)) {// 业主登录
			paramsOb.setType(InfoReceiverVO.INFO_TYPE_PERSON);
			paramsOb.setSendUserId(new Long(userId));
		} else if (Constants.LOGIN_USER_TYPE_PAUSER.equals(loginUserType)) {// 物业登录

		} else if (Constants.LOGIN_USER_TYPE_SYSTEM.equals(loginUserType)) {// 系统管理员登录
			paramsOb.setType(InfoReceiverVO.INFO_TYPE_SYSTEM);
		}
		return getSmartHomeService().getInfoCount(paramsOb);
	}

	public IHousingDistrictInfoService getHousingDistrictInfoService() {
		return housingDistrictInfoService;
	}

	public void setHousingDistrictInfoService(IHousingDistrictInfoService housingDistrictInfoService) {
		this.housingDistrictInfoService = housingDistrictInfoService;
	}

	public ICellHouseholdInfoService getCellHouseholdInfoService() {
		return cellHouseholdInfoService;
	}

	public void setCellHouseholdInfoService(ICellHouseholdInfoService cellHouseholdInfoService) {
		this.cellHouseholdInfoService = cellHouseholdInfoService;
	}

	public IOwnerUserService getOwnerUserService() {
		return ownerUserService;
	}

	public void setOwnerUserService(IOwnerUserService ownerUserService) {
		this.ownerUserService = ownerUserService;
	}

}
