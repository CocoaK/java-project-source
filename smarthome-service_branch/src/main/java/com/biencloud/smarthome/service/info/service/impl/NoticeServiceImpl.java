package com.biencloud.smarthome.service.info.service.impl;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.biencloud.smarthome.service.base.service.impl.BaseService;
import com.biencloud.smarthome.service.charge.service.IChargeDataService;
import com.biencloud.smarthome.service.common.model.Paging;
import com.biencloud.smarthome.service.common.utils.Utils;
import com.biencloud.smarthome.service.device.model.Device;
import com.biencloud.smarthome.service.device.service.IDeviceService;
import com.biencloud.smarthome.service.housemgr.model.BuildingCellInfo;
import com.biencloud.smarthome.service.housemgr.model.CellHouseholdInfo;
import com.biencloud.smarthome.service.housemgr.model.HousingDistrictInfo;
import com.biencloud.smarthome.service.housemgr.service.ICellHouseholdInfoService;
import com.biencloud.smarthome.service.info.model.DistrictData;
import com.biencloud.smarthome.service.info.model.InfoReceiver;
import com.biencloud.smarthome.service.info.model.InfoReceiverDevice;
import com.biencloud.smarthome.service.info.model.InfoSend;
import com.biencloud.smarthome.service.info.model.NoticeData;
import com.biencloud.smarthome.service.info.service.IInfoReceiverDeviceService;
import com.biencloud.smarthome.service.info.service.IInfoSendService;
import com.biencloud.smarthome.service.info.service.INoticeService;
import com.biencloud.smarthome.service.push.model.Push;
import com.biencloud.smarthome.service.push.service.IPushService;

/**
 * 小区公告信息服务实现类。
 */
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class NoticeServiceImpl extends BaseService<NoticeData, Long> implements INoticeService {


	private ICellHouseholdInfoService cellHouseholdInfoService;

	@Override
	public Paging<NoticeData> queryNoticeForPaging(String roomNo,
			int pageNum, int pageSize) {
		String areaNo = null;
		String regionNo = null;
		String buildingNo = null;
		String unitNo = null;
		String houseCode = null;
		if(StringUtils.isNotBlank(roomNo)){
			areaNo = "1001";
			regionNo = "01";
			buildingNo = "001";
			unitNo = "01";
			houseCode = "0102";
		}
		CellHouseholdInfo house = cellHouseholdInfoService.getByCode(areaNo, regionNo, buildingNo, unitNo, houseCode);
		//System.out.println("------------house:"+house);
		//String queryString = hql.toString().replace(REPLACE_CHARS, "dn");
		//String queryStringCount = hql.toString().replace(REPLACE_CHARS, "COUNT(*)");
		String queryString = "select b.id infoId,title,content,send_time from tb_infomation_receiver a LEFT JOIN tb_information_send b ON b.id=a.info_id where b.type=?1 and a.house_id=?2 order by b.createTime desc";
		String queryString2 = "select NoticeData from tb_infomation_receiver a LEFT JOIN tb_information_send b ON b.id=a.info_id where b.type=?1 and a.house_id=?2 order by b.createTime desc";
		String queryStringCount = "select COUNT(*) from tb_infomation_receiver a LEFT JOIN tb_information_send b ON b.id=a.info_id where b.type=?1 and a.house_id=?2 order by b.createTime desc";
		//List<NoticeData> ls = super.find(queryString2,"1",house.getId());
		//super.get
		//System.out.println("**********************-----list:"+ls);
		return super.queryForPaging(pageNum, pageSize, queryString2, queryStringCount,"1",house.getId());
	}

	public ICellHouseholdInfoService getCellHouseholdInfoService() {
		return cellHouseholdInfoService;
	}

	public void setCellHouseholdInfoService(
			ICellHouseholdInfoService cellHouseholdInfoService) {
		this.cellHouseholdInfoService = cellHouseholdInfoService;
	}

}
