package com.biencloud.smarthome.web.housemgr.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.biencloud.smarthome.web.base.service.BaseService;
import com.biencloud.smarthome.web.housemgr.service.ICellSizeInfoService;
import com.biencloud.smarthome.web.housemgr.util.HouseManagementModelVoConvert;
import com.biencloud.smarthome.web.housemgr.vo.CellSizeInfoVo;
import com.biencloud.smarthome.web.wsclient.stub.CellSizeInfo;

/**
 * 户型 Service 实现类
 * 
 * @author jsun  
 * @since 1.0 2012-5-24
 */
public class CellSizeInfoServiceImpl extends BaseService<CellSizeInfoVo>
		implements ICellSizeInfoService {
	@Override
	public void saveOrUpdate(CellSizeInfoVo size) {
		getSmartHomeService().saveOrUpdateCellSizeInfo(
				HouseManagementModelVoConvert.size2Model(size));
	}

	@Override
	public List<CellSizeInfoVo> find(CellSizeInfoVo condition) {
		List<CellSizeInfo> sizeList = getSmartHomeService().findCellSizeInfo(
				HouseManagementModelVoConvert.size2Model(condition));

		List<CellSizeInfoVo> sizeVoList = new ArrayList<CellSizeInfoVo>();
		for (int i = 0, length = sizeList.size(); i < length; i++) {
			sizeVoList.add(HouseManagementModelVoConvert.size2Vo(sizeList.get(i)));
		}

		return sizeVoList;
	}

	@Override
	public int remove(String id) {
		return getSmartHomeService().removeCellSizeById(id);
	}

	@Override
	public String saveCustomSize(CellSizeInfoVo size, String houseId) {
		getSmartHomeService().saveCustomSize(
				HouseManagementModelVoConvert.size2Model(size), houseId);
		return null;
	}
}
