package com.biencloud.smarthome.web.housemgr.service.impl;

import com.biencloud.smarthome.web.base.service.BaseService;
import com.biencloud.smarthome.web.common.vo.PagingVO;
import com.biencloud.smarthome.web.housemgr.service.IBuildingCellInfoService;
import com.biencloud.smarthome.web.housemgr.util.HouseManagementModelVoConvert;
import com.biencloud.smarthome.web.housemgr.vo.BuildingCellInfoVo;
import com.biencloud.smarthome.web.wsclient.stub.BuildingCellInfo;
import com.biencloud.smarthome.web.wsclient.stub.Paging;

/**
 * 单元 Service 实现类
 * 
 * @author jsun  
 * @since 1.0 2012-5-17 上午9:21:49
 */
public class BuildingCellInfoServiceImpl extends
		BaseService<BuildingCellInfoVo> implements IBuildingCellInfoService {
	private static final String CREATE_TIME = "createTime";

	@Override
	public PagingVO<BuildingCellInfoVo> queryBuildingCellInfosForPaging(
			BuildingCellInfoVo condition, int pageNum, int pageSize) {
		Paging paging = getSmartHomeService().queryBuildingCellInfosForPaging(
				HouseManagementModelVoConvert.cell2Model(condition), pageNum, pageSize);

		return HouseManagementModelVoConvert.pagingCell2Vo(paging,
				convertToVO(paging, new String[] { CREATE_TIME, HouseManagementModelVoConvert.REGION_BUILDING_INFO }));
	}

	@Override
	public void saveOrUpdate(BuildingCellInfoVo cell) {
		getSmartHomeService().saveOrUpdateBuildingCellInfo(
				HouseManagementModelVoConvert.cell2Model(cell));
	}

	@Override
	public BuildingCellInfoVo get(String id) {
		BuildingCellInfo cell = getSmartHomeService().getBuildingCellInfo(id);
		return HouseManagementModelVoConvert.cell2Vo(cell);
	}

	@Override
	public int remove(String id) {
		return getSmartHomeService().removeCellById(id);
	}

	@Override
	public boolean newCodeNotRepeat(String cellCode, String buildingId) {
		return getSmartHomeService().newCellCodeNotRepeat(cellCode, buildingId);
	}

	@Override
	public boolean updateCodeNotRepeat(String cellId, String cellCode, String buildingId) {
		return getSmartHomeService().updateCellCodeNotRepeat(cellId, cellCode, buildingId);
	}

	@Override
	public boolean hasHouse(String cellId) {
		return getSmartHomeService().hasHouse(cellId);
	}

	@Override
	public boolean existCellName(String buildingId, String cellId,
			String cellName) {
		return getSmartHomeService().existCellName(
				buildingId, cellId, cellName);
	}

	@Override
	public BuildingCellInfoVo saveOrUpdateVo(BuildingCellInfoVo cell) {
		BuildingCellInfo buildCell = getSmartHomeService().saveOrUpdateBuildingCell(
				HouseManagementModelVoConvert.cell2Model(cell));
		return HouseManagementModelVoConvert.cell2Vo(buildCell);
	}
}
