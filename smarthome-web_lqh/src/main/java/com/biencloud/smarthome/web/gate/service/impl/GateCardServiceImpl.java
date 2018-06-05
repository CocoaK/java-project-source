package com.biencloud.smarthome.web.gate.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.ArrayUtils;

import com.biencloud.smarthome.web.base.service.BaseService;
import com.biencloud.smarthome.web.common.vo.PagingVO;
import com.biencloud.smarthome.web.device.service.IDeviceService;
import com.biencloud.smarthome.web.gate.service.IGateCardService;
import com.biencloud.smarthome.web.gate.vo.GateCardVO;
import com.biencloud.smarthome.web.gate.vo.GatePermissionsVO;
import com.biencloud.smarthome.web.wsclient.stub.GateCard;
import com.biencloud.smarthome.web.wsclient.stub.GatePermissions;
import com.biencloud.smarthome.web.wsclient.stub.Paging;

/**
 * 门卡管理调用服务实现。
 * @author kouy
 * @since 1.0 2012-5-7
 * @see BaseService
 * @see IGateCardService
 */
public class GateCardServiceImpl extends BaseService<GateCardVO> implements
		IGateCardService {

	private static final String GATE_PERMISSIONS = "gatePermissions";
	private static final String CREATED_TIME = "createdTime";
	private static final String UPDATED_TIME = "updatedTime";
	private static final String RESULTS = "results";
	private static final String BEGIN_TIME = "beginTime";
	private static final String END_TIME = "endTime";
	private static final String DEVICE = "device";
	
	private IDeviceService deviceService;
	
	public IDeviceService getDeviceService() {
		return deviceService;
	}

	public void setDeviceService(IDeviceService deviceService) {
		this.deviceService = deviceService;
	}

	
	@Override
	public PagingVO<GateCardVO> queryGateCardsForPaging(GateCardVO gateCard,
			int pageNum, int pageSize) {
		GateCard gc = new GateCard();
		
		copyProperties(gateCard, gc, new String[]{GATE_PERMISSIONS}, 
				false, CREATED_TIME, UPDATED_TIME);
		
		Paging paging = getSmartHomeService().queryGateCardsForPaging(
				gc, pageNum, pageSize);
		
		PagingVO<GateCardVO> pagingVO = new PagingVO<GateCardVO>();
		copyProperties(paging, pagingVO, new String[]{RESULTS}, true);
		
		List<Object> list = paging.getResults();
		if(list != null && list.size() > 0){
			List<GateCardVO> results = new ArrayList<GateCardVO>();
			for (int index = 0, size = list.size(); index < size; index++) {
				results.add(convertToVO((GateCard)list.get(index)));
			}
			pagingVO.setResults(results);
		}
		
		return pagingVO;
	}

	@Override
	public GateCardVO getGateCardDetail(String gateCardId) {
		GateCard gateCard = getSmartHomeService().getGateCardDetail(gateCardId);
		return convertToVO(gateCard);
	}
	
	@Override
	public void addGateCard(GateCardVO gateCard) {
		getSmartHomeService().addGateCard(
				convertToEntity(gateCard));
	}

	@Override
	public void updateGateCard(GateCardVO gateCard) {
		getSmartHomeService().updateGateCard(
				convertToEntity(gateCard));
	}

	@Override
	public void removeGateCard(String gateCardId) {
		getSmartHomeService().removeGateCard(gateCardId);
	}

	@Override
	public void updateGateCardStatus(String gateCardId, String status,
			String updatedUser) {
		getSmartHomeService().updateGateCardStatus(
				gateCardId, status, updatedUser);
	}
	
	@Override
	public boolean existCardNo(GateCardVO gateCard) {
		return getSmartHomeService().existCardNo(
				convertToEntity(gateCard));
	}

	@Override
	public List<GateCardVO> queryByDeviceCode(String deviceCode) {
		List<GateCard> list = getSmartHomeService().queryGateCardByDeviceCode(deviceCode);
		List<GateCardVO> listVO = new ArrayList<GateCardVO>();
		if(list == null || list.size() == 0)
			return listVO;
		
		for (GateCard gc : list)
			listVO.add(convertToVO(gc));
		
		return listVO;
	}
	
		
	//转换为门卡值对象
	private GateCardVO convertToVO(GateCard gateCard,
			String... ignoreProperties) {
		GateCardVO gateCardVO = new GateCardVO();		
		copyObject(gateCard, gateCardVO, true, ignoreProperties);		
		return gateCardVO;
	}
	
	//转换为门卡实体对象
	private GateCard convertToEntity(GateCardVO gateCard, 
			String... ignoreProperties){
		GateCard gc = new GateCard();	
		copyObject(gc, gateCard, false, ignoreProperties);	
		return gc;
	}
	
	//门卡值对象和实体对象相互拷贝
	private void copyObject(GateCard gc, GateCardVO gcVO, 
			boolean convertToVO, String[] ignoreProperties){
		boolean contains = ArrayUtils.contains(
				ignoreProperties, GATE_PERMISSIONS);
		if(!contains)
			ignoreProperties = (String[])ArrayUtils.add(
					ignoreProperties, GATE_PERMISSIONS);
		
		if(convertToVO){
			copyProperties(gc, gcVO, ignoreProperties, 
					convertToVO, CREATED_TIME, UPDATED_TIME);
		}else{
			copyProperties(gcVO, gc, ignoreProperties, 
					convertToVO, CREATED_TIME, UPDATED_TIME);
		}
				
		if(!contains){
			if(convertToVO){
				gcVO.setGatePermissions(
						convertToVOList(gc.getGatePermissions()));
			}else{
				copyToEntityList(gcVO, gc);
			}			
		}
	}
	
	//转换为门禁权限值对象列表
	private List<GatePermissionsVO> convertToVOList(List<GatePermissions> gatePermissions){
		List<GatePermissionsVO> list = new ArrayList<GatePermissionsVO>();
		if(gatePermissions != null && gatePermissions.size() > 0){
			GatePermissions gp = null;
			for (int index = 0, size = gatePermissions.size(); index < size; index++) {
				GatePermissionsVO gpVO = new GatePermissionsVO();
				gp = gatePermissions.get(index);
				copyProperties(gp, gpVO, new String[]{DEVICE}, 
						true, BEGIN_TIME, END_TIME);
				gpVO.setDevice(getDeviceService().deviceToVO(gp.getDevice()));
				list.add(gpVO);
			}
		}
		return list;
	}
	
	//转换为门禁权限实体对象列表
	private void copyToEntityList(GateCardVO gcVO, GateCard gc){
		List<GatePermissions> list = gc.getGatePermissions();
		List<GatePermissionsVO> voList = gcVO.getGatePermissions();
		if(voList != null && voList.size() > 0){
			GatePermissionsVO gpVO = null;
			for (int index = 0, size = voList.size(); index < size; index++) {
				GatePermissions gp = new GatePermissions();
				gpVO = voList.get(index);
				copyProperties(gpVO, gp, new String[]{DEVICE}, 
						false, BEGIN_TIME, END_TIME);
				gp.setDevice(getDeviceService().voToDevice(gpVO.getDevice()));
				gp.setGateCardId(gcVO.getGateCardId());
				list.add(gp);
			}
		}
	}
}
