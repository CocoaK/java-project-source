package com.biencloud.smarthome.service.sysgroup.service.impl;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.biencloud.smarthome.service.base.service.impl.BaseService;
import com.biencloud.smarthome.service.housemgr.model.HousingDistrictInfo;
import com.biencloud.smarthome.service.housemgr.service.IHousingDistrictInfoService;
import com.biencloud.smarthome.service.sysgroup.model.SystemGroup;
import com.biencloud.smarthome.service.sysgroup.service.ISystemGroupService;
import com.biencloud.smarthome.service.user.service.IPaUserService;

/**
 * 
 * 项目名称：smarthome-service-new 类名称：SystemGroupServiceImpl 类描述： 组织模块领域服务实现类
 * 
 * @author: kouy
 * @version: 0.1
 * @date: 2012-6-12 下午2:34:22
 */
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class SystemGroupServiceImpl extends BaseService<SystemGroup, Long> implements ISystemGroupService {

	private IHousingDistrictInfoService housingDistrictInfoService;

	private IPaUserService paUserService;

	@Override
	public List<SystemGroup> querySystemGroup(int pageNo, int pageSize, SystemGroup sg) {
		StringBuilder sql = new StringBuilder();
		sql.append("from SystemGroup where 1=1 ");
		if (sg != null && !StringUtils.isBlank(sg.getGroupName()))
			sql.append(" and groupName like '%" + sg.getGroupName() + "%'");
		if (sg != null && !StringUtils.isBlank(sg.getGroupEqualName()))
			sql.append(" and groupName = '" + sg.getGroupEqualName() + "'");
		if (sg != null && sg.getGroupNo() != null)
			sql.append(" and groupNo = '" + sg.getGroupNo() + "'");
		if (sg != null && sg.getDeep() != null)
			sql.append(" and deep = " + sg.getDeep());
		if (sg != null && !StringUtils.isBlank(sg.getGroupParentNo()))
			sql.append(" and groupParentNo like '%" + sg.getGroupParentNo() + "%'");
		return this.find(sql.toString());
	}

	@SuppressWarnings("rawtypes")
	@Override
	public String getCompletePosition(String comId) {
		String resultStr = "";
		StringBuilder varname1 = new StringBuilder();
		varname1.append("SELECT Concat(country.group_name,province.name) AS name, ");
		varname1.append("       country.group_parentno ");
		varname1.append("FROM   (SELECT Concat(province.group_name,city.name) AS name, ");
		varname1.append("               province.group_parentno ");
		varname1.append("        FROM   (SELECT Concat(city.group_name,area.name) AS name, ");
		varname1.append("                       city.group_parentno ");
		varname1.append("                FROM   (SELECT Concat(area.group_name,street.name) AS name, ");
		varname1.append("                               area.group_parentno ");
		varname1.append("                        FROM   (SELECT Concat(street.group_name,com.group_name) AS name, ");
		varname1.append("                                       street.group_parentno ");
		varname1.append("                                FROM   (SELECT * ");
		varname1.append("                                        FROM   t_system_group ");
		varname1.append("                                        WHERE  group_no = " + comId + ") com, ");
		varname1.append("                                       t_system_group street ");
		varname1.append("                                WHERE  street.group_no = com.group_parentno) street, ");
		varname1.append("                               t_system_group area ");
		varname1.append("                        WHERE  area.group_no = street.group_parentno) area, ");
		varname1.append("                       t_system_group city ");
		varname1.append("                WHERE  city.group_no = area.group_parentno) city, ");
		varname1.append("               t_system_group province ");
		varname1.append("        WHERE  province.group_no = city.group_parentno) province, ");
		varname1.append("       t_system_group country ");
		varname1.append("WHERE  country.group_no = province.group_parentno");
		List result = getDao().excuteSql(varname1.toString());
		for (int i = 0; i < result.size(); i++) {
			Object[] rsOb = (Object[]) result.get(i);
			resultStr = (String) rsOb[0];
		}
		return resultStr;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public String getCityNameByDistrictId(String comId) {
		String resultStr = "";
		StringBuilder varname1 = new StringBuilder();
		varname1.append("SELECT city.group_name ");
		varname1.append("FROM   (SELECT area.group_parentno ");
		varname1.append("        FROM   (SELECT street.group_parentno ");
		varname1.append("                FROM   (SELECT * ");
		varname1.append("                        FROM   t_system_group ");
		varname1.append("                                        WHERE  group_no = " + comId + ") com, ");
		varname1.append("                       t_system_group street ");
		varname1.append("                WHERE  street.group_no = com.group_parentno) street, ");
		varname1.append("               t_system_group area ");
		varname1.append("        WHERE  area.group_no = street.group_parentno) area, ");
		varname1.append("       t_system_group city ");
		varname1.append("WHERE  city.group_no = area.group_parentno");
		List result = getDao().excuteSql(varname1.toString());
		for (int i = 0; i < result.size(); i++) {
			resultStr = (String) result.get(i);
		}
		return resultStr;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public List<String> getDistrictIdByCityName(String cityName) {
		List<String> districtIdList = new ArrayList<String>();
		StringBuilder varname1 = new StringBuilder();
		varname1.append("SELECT com.group_no ");
		varname1.append("FROM   (SELECT street.group_no ");
		varname1.append("        FROM   (SELECT area.group_no ");
		varname1.append("                FROM   (SELECT * ");
		varname1.append("                        FROM   t_system_group ");
		varname1.append("                        WHERE  group_name LIKE '%" + cityName + "%') city, ");
		varname1.append("                       t_system_group area ");
		varname1.append("                WHERE  city.group_no = area.group_parentno) area, ");
		varname1.append("               t_system_group street ");
		varname1.append("        WHERE  area.group_no = street.group_parentno) street, ");
		varname1.append("       t_system_group com ");
		varname1.append("WHERE  street.group_no = com.group_parentno");
		List result = getDao().excuteSql(varname1.toString());
		for (int i = 0; i < result.size(); i++) {
			BigInteger str = (BigInteger) result.get(i);
			districtIdList.add(str.toString());
		}
		return districtIdList;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public boolean saveOrUpdateSystemGroup(SystemGroup user) {
		try {
			if (user.getGroupNo() == null) {
				short deep = 0;
				boolean isNational = false;
				SystemGroup systemParentGroup = null;
				if (StringUtils.isBlank(user.getGroupParentNo())) {// 添加国家
					user.setGroupParentNo(SystemGroup.NOPARENT);
					user.setDeep(deep);
				} else {
					isNational = true;
					systemParentGroup = this.get(Long.parseLong(user.getGroupParentNo()));
					deep = systemParentGroup.getDeep();
					deep += 1;
					user.setDeep(deep);
				}
				user.setHasChild(SystemGroup.HashChild_NO);
				java.sql.Date now = new java.sql.Date(System.currentTimeMillis());
				user.setCreateTime(now);
				user.setUpdateTime(now);
				user.setCreateUser("");
				user.setUpdateUser("");
				this.save(user);
				if (isNational) {// 更新父节点
					systemParentGroup.setHasChild(SystemGroup.HashChild_YES);
					this.update(systemParentGroup);
				}
				if (user.getDeep() == SystemGroup.DEEP_COMMUNITY) {
					// 往小区表插入记录
					HousingDistrictInfo hdiOb = new HousingDistrictInfo();
					hdiOb.setName(user.getGroupName());
					hdiOb.setGroupId(user.getGroupNo().toString());
					housingDistrictInfoService.save(hdiOb);
				}
			} else {
				SystemGroup systemGroup = this.get(user.getGroupNo());
				systemGroup.setGroupName(user.getGroupName());
				this.update(systemGroup);
				if (systemGroup.getDeep() == SystemGroup.DEEP_COMMUNITY) {
					// 更新小区记录
					housingDistrictInfoService.upateHousingDistrictInfoName(user.getGroupNo(), user.getGroupName());
				}
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public boolean deleteSystemGroupById(Long id) {
		try {
			if (id != null) {
				SystemGroup systemGroup = findByID(id);
				if (systemGroup.getDeep() == SystemGroup.DEEP_COMMUNITY) {
					String districtId = housingDistrictInfoService.getDistrictIdByGroupNo(id);
					if (paUserService.existUsersByDistrictId(districtId))
						return false;
					HousingDistrictInfo hdiOb = housingDistrictInfoService.get(districtId);
					housingDistrictInfoService.remove(hdiOb);
				}
				if (systemGroup != null) {
					// 查询该节点的父节点是否还有其它子节点，如没有了更新hashChild的值
					Long result = getCountByNo(systemGroup.getGroupParentNo());
					if (result == 1) {
						SystemGroup systemParentGroup = this.get(Long.parseLong(systemGroup.getGroupParentNo()));
						if (systemParentGroup != null) {// 如果所删除节点是最顶级的话，systemGroup为null
							systemParentGroup.setHasChild(SystemGroup.HashChild_NO);
							this.update(systemParentGroup);
						}
					}
					this.remove(systemGroup);
				}
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public long getCountByNo(String no) {
		try {
			String hql = "select count(*) from SystemGroup where groupParentNo='" + no + "'";
			Long result = this.getTotalCount(hql);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	public SystemGroup findByID(Long id) {
		return this.get(id);
	}

	public IHousingDistrictInfoService getHousingDistrictInfoService() {
		return housingDistrictInfoService;
	}

	public void setHousingDistrictInfoService(IHousingDistrictInfoService housingDistrictInfoService) {
		this.housingDistrictInfoService = housingDistrictInfoService;
	}

	public IPaUserService getPaUserService() {
		return paUserService;
	}

	public void setPaUserService(IPaUserService paUserService) {
		this.paUserService = paUserService;
	}

}
