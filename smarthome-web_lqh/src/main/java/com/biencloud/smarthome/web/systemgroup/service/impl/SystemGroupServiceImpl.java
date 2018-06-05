package com.biencloud.smarthome.web.systemgroup.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;

import com.biencloud.smarthome.web.base.service.BaseService;
import com.biencloud.smarthome.web.housemgr.service.IHousingDistrictInfoService;
import com.biencloud.smarthome.web.info.service.IInfoReceiverService;
import com.biencloud.smarthome.web.systemgroup.service.ISystemGroupService;
import com.biencloud.smarthome.web.systemgroup.vo.GroupFlatVO;
import com.biencloud.smarthome.web.systemgroup.vo.SystemGroupVO;
import com.biencloud.smarthome.web.wsclient.stub.SystemGroup;

/**
 * 
 * 项目名称：smarthome-web-new 类名称：SystemGroupServiceImpl 类描述：组织模块领域服务实现类
 * 
 * @author: kouy
 * @version: 0.1
 * @date: 2012-6-12 下午2:37:47
 */
public class SystemGroupServiceImpl extends BaseService<SystemGroupVO> implements ISystemGroupService {

	private IInfoReceiverService infoReceiverService;
	private IHousingDistrictInfoService housingDistrictInfoService;

	@Override
	public List<Map<String, String>> querySystemGroup(SystemGroupVO vo) {
		List<SystemGroup> list = getSmartHomeService().querySystemGroup(0, 0, new SystemGroup());
		List<Map<String, String>> treeList = new ArrayList<Map<String, String>>();
		for (Object listOb : list) {
			Map<String, String> treeMap = new HashMap<String, String>();
			SystemGroup ob = (SystemGroup) listOb;
			if (new Integer(ob.getDeep()).intValue() == SystemGroupVO.Deep_Root)
				treeMap.put("NodeNo", ob.getGroupNo() + ",-1");
			else
				treeMap.put("NodeNo", ob.getGroupNo() + "," + ob.getGroupParentNo());
			treeMap.put("Nodename", ob.getGroupName());
			treeMap.put("NodeUrl", "javascript:detailMsg(" + ob.getGroupNo() + ",'" + ob.getGroupName() + "','" + ob.getDesc() + "'," + ob.getHasChild() + "," + ob.getDeep() + ");");
			treeList.add(treeMap);
		}
		return treeList;
	}

	@Override
	public Object[] querySystemGroupForCheck(Set<Long> receiverIds, boolean isCheckBox, boolean isShowDetail) {
		Object result[] = new Object[2];
		List<SystemGroup> list = getSmartHomeService().querySystemGroup(0, 0, new SystemGroup());
		List<Map<String, String>> treeList = new ArrayList<Map<String, String>>();
		StringBuilder sp = new StringBuilder();
		List<String> commNodeNoList = new ArrayList<String>();
		Map<String, String> current_parent_node = new HashMap<String, String>();
		Map<String, Map<String, String>> exclude_community_map = new HashMap<String, Map<String, String>>();
		for (Object listOb : list) {
			Map<String, String> treeMap = new HashMap<String, String>();
			SystemGroup ob = (SystemGroup) listOb;
			if (new Integer(ob.getDeep()).intValue() == SystemGroupVO.Deep_Root)
				treeMap.put("ParentNodeNo", "0");
			else
				treeMap.put("ParentNodeNo", ob.getGroupParentNo());
			treeMap.put("CurrentNodeNo", ob.getGroupNo().toString());
			treeMap.put("Nodename", ob.getGroupName());
			if (isCheckBox) {
				String checked = "0";
				boolean isCheck = false;
				if (receiverIds != null && SystemGroupVO.DEEP_COMMUNITY == ob.getDeep()) {
					String districtId = housingDistrictInfoService.getDistrictIdByGroupNo(ob.getGroupNo());
					if (districtId != null && receiverIds.contains(new Long(districtId))) {
						checked = "1";
						isCheck = true;
					}
				}
				treeMap.put("checked", checked);
				boolean isAdd = false;
				if (SystemGroupVO.DEEP_COMMUNITY == ob.getDeep() && housingDistrictInfoService.getDistrictIdByGroupNo(ob.getGroupNo()) != null)
					isAdd = true;// 还没有在小区表生成记录过滤掉
				if (isAdd) {
					if (isShowDetail && SystemGroupVO.DEEP_COMMUNITY == ob.getDeep()) {
						isAdd = false;
						if (isCheck)
							isAdd = true;
					}
				}
				if (isAdd) {
					treeList.add(treeMap);
					if (new Integer(ob.getDeep()).intValue() == SystemGroupVO.DEEP_COMMUNITY) {
						sp.append("-" + ob.getGroupNo().toString() + "-");
						commNodeNoList.add(ob.getGroupNo().toString());
						current_parent_node.put(ob.getGroupNo().toString(), ob.getGroupParentNo());
					}
				}
				if (new Integer(ob.getDeep()).intValue() != SystemGroupVO.DEEP_COMMUNITY)
					exclude_community_map.put(ob.getGroupNo().toString(), treeMap);
			}
			if (!isCheckBox) {
				treeList.add(treeMap);
				treeMap.put("onClickParams", ob.getGroupNo() + "," + ob.getGroupParentNo() + "," + ob.getGroupName() + "," + ob.getDesc() + "," + ob.getHasChild() + "," + ob.getDeep());
			}
		}
		if (isCheckBox) {
			for (int i = 0; i < commNodeNoList.size(); i++) {
				Map<String, String> streetMap = exclude_community_map.get(current_parent_node.get(commNodeNoList.get(i)));
				if (!treeList.contains(streetMap))
					treeList.add(streetMap);
				Map<String, String> areaMap = exclude_community_map.get(streetMap.get("ParentNodeNo"));
				if (!treeList.contains(areaMap))
					treeList.add(areaMap);
				Map<String, String> cityMap = exclude_community_map.get(areaMap.get("ParentNodeNo"));
				if (!treeList.contains(cityMap))
					treeList.add(cityMap);
				Map<String, String> provinceMap = exclude_community_map.get(cityMap.get("ParentNodeNo"));
				if (!treeList.contains(provinceMap))
					treeList.add(provinceMap);
				Map<String, String> contunryMap = exclude_community_map.get(provinceMap.get("ParentNodeNo"));
				if (!treeList.contains(contunryMap))
					treeList.add(contunryMap);
			}
		}
		result[0] = treeList;
		result[1] = sp.toString();
		return result;
	}

	@Override
	public List<SystemGroupVO> queryListByParams(SystemGroupVO vo) {
		SystemGroup sob = new SystemGroup();
		sob.setGroupName(vo.getGroupName());
		sob.setDeep(vo.getDeep());
		List<SystemGroup> list = getSmartHomeService().querySystemGroup(0, 0, sob);
		List<SystemGroupVO> result = new ArrayList<SystemGroupVO>();
		for (int i = 0; i < list.size(); i++) {
			SystemGroupVO sgvo = new SystemGroupVO();
			SystemGroup ob = (SystemGroup) list.get(i);
			sgvo.setGroupNo(ob.getGroupNo());
			sgvo.setGroupName(ob.getGroupName());
			result.add(sgvo);
		}
		return result;
	}

	@Override
	public SystemGroupVO getSystemGroupVO(String groupNo, String parantGroupNo, Short deep, String name) {
		SystemGroup sob = new SystemGroup();
		if (StringUtils.isNotBlank(groupNo))
			sob.setGroupNo(new Long(groupNo));
		if (StringUtils.isNotBlank(parantGroupNo))
			sob.setGroupParentNo(parantGroupNo);
		if (StringUtils.isNotBlank(name))
			sob.setGroupEqualName(name);
		if (deep != null)
			sob.setDeep(deep);
		List<SystemGroup> list = getSmartHomeService().querySystemGroup(0, 0, sob);
		SystemGroupVO sgvo = null;
		if (!list.isEmpty()) {
			sgvo = new SystemGroupVO();
			SystemGroup ob = (SystemGroup) list.get(0);
			sgvo.setGroupNo(ob.getGroupNo());
			sgvo.setGroupName(ob.getGroupName());
			sgvo.setDeep(ob.getDeep());
			sgvo.setGroupParentNo(ob.getGroupParentNo());
		}
		return sgvo;
	}

	@Override
	public List<GroupFlatVO> queryGroupFlatListByParams(GroupFlatVO vo) {
		try {
			return null;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public String querySystemGroupByParams(SystemGroupVO vo) {
		try {
			SystemGroup sob = new SystemGroup();
			sob.setGroupName(vo.getGroupName());
			sob.setDeep(vo.getDeep());
			StringBuilder result = new StringBuilder();
			List<SystemGroup> list = getSmartHomeService().querySystemGroup(0, 0, sob);
			for (Object listOb : list) {
				SystemGroup ob = (SystemGroup) listOb;
				result.append("<li><a href='javascript:linkFunction(" + ob.getGroupNo() + ")'>" + ob.getGroupName() + "</a></li>");
				// targetVo.setGroupNo(ob.getGroupNo());
				// result.add(targetVo);
			}
			return result.toString();
		} catch (Exception e) {
			logger.error("********************发生异常:{}", e);
		}
		return "";
	}

	@Override
	public boolean saveOrUpdateSystemGroup(SystemGroupVO vo) {
		try {
			SystemGroup ob = new SystemGroup();
			ob.setGroupName(vo.getGroupName());
			ob.setGroupParentNo(vo.getGroupParentNo());
			ob.setGroupNo(vo.getGroupNo());
			return getSmartHomeService().saveOrUpdateSystemGroup(ob);
		} catch (Exception e) {
			logger.error("********************发生异常:{}", e);
		}
		return false;

	}

	@Override
	public boolean deleteSystemGroupById(Long id) {
		return getSmartHomeService().deleteSystemGroupById(id);

	}

	public IInfoReceiverService getInfoReceiverService() {
		return infoReceiverService;
	}

	public void setInfoReceiverService(IInfoReceiverService infoReceiverService) {
		this.infoReceiverService = infoReceiverService;
	}

	@Override
	public String getCompletePosition(String comId) {
		return getSmartHomeService().getCompletePosition(comId);
	}

	@Override
	public String getCityNameByDistrictId(String comId) {
		try {
			if (StringUtils.isBlank(comId))
				return "";
			return getSmartHomeService().getCityNameByDistrictId(comId);
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}

	public IHousingDistrictInfoService getHousingDistrictInfoService() {
		return housingDistrictInfoService;
	}

	public void setHousingDistrictInfoService(IHousingDistrictInfoService housingDistrictInfoService) {
		this.housingDistrictInfoService = housingDistrictInfoService;
	}

}
