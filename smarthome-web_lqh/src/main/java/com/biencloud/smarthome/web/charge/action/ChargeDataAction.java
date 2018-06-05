package com.biencloud.smarthome.web.charge.action;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.biencloud.smarthome.web.base.action.BaseAction;
import com.biencloud.smarthome.web.charge.service.IChargeDataService;
import com.biencloud.smarthome.web.charge.service.IChargeTypeService;
import com.biencloud.smarthome.web.charge.vo.ChargeDataVO;
import com.biencloud.smarthome.web.charge.vo.ChargeStatiticsVO;
import com.biencloud.smarthome.web.charge.vo.ChargeTypeResultVO;
import com.biencloud.smarthome.web.charge.vo.ChargeTypeVO;
import com.biencloud.smarthome.web.common.action.ActionUtils;
import com.biencloud.smarthome.web.common.vo.PagingVO;
import com.biencloud.smarthome.web.housemgr.vo.HousingDistrictInfoVo;
import com.biencloud.smarthome.web.housemgr.vo.RegionBuildingInfoVo;
import com.biencloud.smarthome.web.user.vo.PaUserVO;

/**
 * 
 * 项目名称：smarthome-web-new 
 * 类名称：ChargeDataAction 
 * 类描述： 收费数据管理类
 * @author: kouy 
 * @version: 0.1
 * @date: 2012-6-12 上午11:01:56
 */
public class ChargeDataAction extends BaseAction<ChargeDataVO> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private IChargeDataService chargeDataService;
	
	private IChargeTypeService chargeTypeService;

	private ChargeDataVO chargeData;
	
	private List<ChargeTypeVO> chargeTypeList;
	
	private List<ChargeTypeResultVO> chargeTypeResultList;
	
	private List<ChargeStatiticsVO> ChargeStatiticsList;
	
	private String regionId;
	
	private String buildingId;

	/**
	 * 
	 * 方法的描述: 收费数据列表
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-6-12 上午11:02:10
	 * @return
	 */
	public String queryList() throws Exception {
		PagingVO<ChargeDataVO> page = getPage();
		if (page == null)
			page = new PagingVO<ChargeDataVO>();
		if (chargeData == null)
			chargeData = new ChargeDataVO();
		HousingDistrictInfoVo hdVo = new HousingDistrictInfoVo();
		hdVo.setId(getDistrictId());
		chargeData.setHousingDistrictInfo(hdVo);
		PagingVO<ChargeDataVO> pagingVO = chargeDataService.queryChargeDataVOForPaging(chargeData, page.getPageNum(), page.getPageSize());
		setPage(pagingVO);
		setRequestAttribute("GeneratorDetailYes", ChargeDataVO.YES);
		return "list";
	}
	
	/**
	 * 
	 * 方法的描述:  统计收费报表
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-6-12 上午11:02:18
	 * @return
	 */
	public String statisticsCharge() throws Exception {
		if (chargeData == null)
			chargeData = new ChargeDataVO();
		String buildingRequestId = getRequest().getParameter("buildingId");
		String regionRequestId = getRequest().getParameter("regionId");
		if (StringUtils.isNotBlank(buildingRequestId)) {
			RegionBuildingInfoVo rbiVo = new RegionBuildingInfoVo();
			rbiVo.setId(buildingRequestId);
			chargeData.setRegionBuildingInfo(rbiVo);
			this.regionId = regionRequestId;
			this.buildingId = buildingRequestId;
		}
		HousingDistrictInfoVo hdiv = new HousingDistrictInfoVo();
		hdiv.setId(getDistrictId());
		chargeData.setHousingDistrictInfo(hdiv);
		List<ChargeStatiticsVO> list = chargeDataService.statisticsCharge(chargeData);
		setChargeStatiticsList(list);
		return "stalist";
	}
	
	/**
	 * 
	 * 方法的描述: 保存收费数据
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-6-12 上午11:02:30
	 * @return
	 */
	public String save() throws Exception {
		if (ActionUtils.judgContainsValue(getRequest(), ChargeDataVO.REQUEST_FORGETCHARGETYPE)) {// 选择房间后请求
			String roomId = getRequest().getParameter("roomId");
			ChargeTypeVO vo = new ChargeTypeVO();
			vo.setRemark(roomId);
			chargeTypeList = chargeTypeService.queryChargeTypeForList(vo);
			if (chargeTypeList.isEmpty()) {
				setRequestAttribute(ChargeDataVO.FIRSTGOTOPAGE, true);
				setRequestAttribute("typeIsEmpty", true);
				return "editPage";
			}
			setRequestAttribute("roomText", getRequest().getParameter("roomText"));
			setRequestAttribute("ownerName", getRequest().getParameter("ownerName"));
			setRequestAttribute("roomId", roomId);
			setRequestAttribute("homeId", getRequest().getParameter("homeId"));
			return "editPage";
		}
		removeFromList();
		// 判断此月份的收费数据是否已存在
		if (chargeDataService.isExistForChargeTimeByUserId(chargeData.getCellHouseholdInfo().getId(), chargeData.getChargeTime())) {
			this.getRequest().setAttribute("isExist", true);
		} else if (chargeTypeResultList == null || chargeTypeResultList.isEmpty()) {// 只有按年收费项目，本次没有收取，故收费项目为空
			this.getRequest().setAttribute("promptError", true);
		} else {
			chargeData.setChargeTypeResultVOs(chargeTypeResultList);
			PaUserVO puVo = new PaUserVO();
			puVo.setUserId(getUserId());
			chargeData.setPaUser(puVo);
			HousingDistrictInfoVo hdVo = new HousingDistrictInfoVo();
			hdVo.setId(getDistrictId());
			chargeData.setHousingDistrictInfo(hdVo);
			boolean result = chargeDataService.saveChargeDataVO(chargeData);
			this.getRequest().setAttribute("editResult", result);
			chargeData = null;
		}
		return "list";
	}
	
	public String goToUpdate() throws Exception {
		setRequestAttribute(ChargeDataVO.FIRSTGOTOPAGE, true);
		return "editPage";
	}
	
	private void removeFromList(){
		String removeIndex=getRequest().getParameter("removeIndex");
		if(StringUtils.isNotBlank(removeIndex)){//过滤掉本次不收费的项目（如一些按年收费的项目）
			String[] indexs=removeIndex.split(",");
			List<ChargeTypeResultVO> removeList=new ArrayList<ChargeTypeResultVO>();
			for (int i = 0; i < indexs.length; i++) {
				if(StringUtils.isNotBlank(indexs[i])){
					ChargeTypeResultVO removeOb=chargeTypeResultList.get(Integer.parseInt(indexs[i]));
					removeList.add(removeOb);
				}
			}
			for (int i = 0; i < removeList.size(); i++) {
					chargeTypeResultList.remove(removeList.get(i));
			}
		}
	}
	
	/**
	 * 
	 * 方法的描述: 更新收费数据
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-6-12 上午11:02:42
	 * @return
	 */
	public String update() throws Exception {
		removeFromList();
		ChargeDataVO oldOb = chargeDataService.getChargeDataVO(chargeData.getId().toString());
		// 判断此月份的收费数据是否已存在
		if (!oldOb.getChargeTime().equals(chargeData.getChargeTime()) && chargeDataService.isExistForChargeTimeByUserId(chargeData.getCellHouseholdInfo().getId(), chargeData.getChargeTime())) {
			this.getRequest().setAttribute("isExist", true);
		} else if (chargeTypeResultList == null || chargeTypeResultList.isEmpty()) {// 只有按年收费项目，本次没有收取，故收费项目为空
			this.getRequest().setAttribute("promptError", true);
		} else {
			chargeData.setChargeTypeResultVOs(chargeTypeResultList);
			boolean result = chargeDataService.updateChargeDataVO(chargeData);
			this.getRequest().setAttribute("editResult", result);
		}
		return "editPage";
	}
	
	/**
	 * 
	 * 方法的描述: 查找单个收费数据
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-6-12 上午11:02:49
	 * @return
	 */
	public String findById() throws Exception {
		String id = getRequest().getParameter("requestId");
		chargeData = chargeDataService.getChargeDataVO(id);
		chargeTypeResultList = chargeData.getChargeTypeResultVOs();
		setRequestAttribute(ActionUtils.SHOWDETAIL, null);
		if (ActionUtils.judgShowDetail(getRequest())) {
			setRequestAttribute(ActionUtils.SHOWDETAIL, true);
		}
		return "editPage";
	}

	/**
	 * 
	 * 方法的描述: 删除收费数据
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-6-12 上午11:02:57
	 * @return
	 */
	public String delById() throws Exception {
		String id = getRequest().getParameter("requestId");
		boolean result = chargeDataService.delChargeDataVO(id);
		this.getRequest().setAttribute("editResult", result);
		return "list";
	}

	public IChargeDataService getChargeDataService() {
		return chargeDataService;
	}

	public void setChargeDataService(IChargeDataService chargeDataService) {
		this.chargeDataService = chargeDataService;
	}

	public ChargeDataVO getChargeData() {
		return chargeData;
	}

	public void setChargeData(ChargeDataVO chargeData) {
		this.chargeData = chargeData;
	}

	public IChargeTypeService getChargeTypeService() {
		return chargeTypeService;
	}

	public void setChargeTypeService(IChargeTypeService chargeTypeService) {
		this.chargeTypeService = chargeTypeService;
	}

	public List<ChargeTypeVO> getChargeTypeList() {
		return chargeTypeList;
	}

	public void setChargeTypeList(List<ChargeTypeVO> chargeTypeList) {
		this.chargeTypeList = chargeTypeList;
	}

	public List<ChargeTypeResultVO> getChargeTypeResultList() {
		return chargeTypeResultList;
	}

	public void setChargeTypeResultList(List<ChargeTypeResultVO> chargeTypeResultList) {
		this.chargeTypeResultList = chargeTypeResultList;
	}

	public List<ChargeStatiticsVO> getChargeStatiticsList() {
		return ChargeStatiticsList;
	}

	public void setChargeStatiticsList(List<ChargeStatiticsVO> chargeStatiticsList) {
		ChargeStatiticsList = chargeStatiticsList;
	}

	public String getRegionId() {
		return regionId;
	}

	public void setRegionId(String regionId) {
		this.regionId = regionId;
	}

	public String getBuildingId() {
		return buildingId;
	}

	public void setBuildingId(String buildingId) {
		this.buildingId = buildingId;
	}

	
	

}
