package com.biencloud.smarthome.service.push.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.biencloud.smarthome.service.base.service.impl.BaseService;
import com.biencloud.smarthome.service.common.constants.Constants;
import com.biencloud.smarthome.service.common.model.Paging;
import com.biencloud.smarthome.service.device.dao.IDeviceDao;
import com.biencloud.smarthome.service.device.model.Device;
import com.biencloud.smarthome.service.push.model.Push;
import com.biencloud.smarthome.service.push.service.IPushService;
import com.biencloud.smarthome.service.sysparam.service.ISysParamService;


/**
 * 
 * 类名称：PushServiceImpl 类描述： 推送业务接口实现类
 * 
 * @author: kouy
 * @version: 0.1
 * @date: 2012-5-9 上午11:25:53
 */
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class PushServiceImpl extends BaseService<Push, Long> implements IPushService {
	private IDeviceDao deviceDao;
	private ISysParamService sysParamService;
	private int size = 100;

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public boolean insertPush(Push push) {
		boolean flag = false;

		Push backObj = super.save_update(push);// 保存或更新
		if (backObj != null) {
			flag = true;
		}

		return flag;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public boolean inserPushCollection(List<Push> pushList) {
		boolean flag = false;
		super.saveCollection(pushList);
		flag = true;
		return flag;
	}

	@Override
	public Push findById(Long id) {
		Push push = super.get(id);
		return push;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public boolean deleteById(Long id) {
		boolean flag = false;
		Push push = this.findById(id);// 查询
		if (push != null) {
			flag = this.deleteByEntity(push);// 删除
		}
		return flag;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public boolean deleteByEntity(Push push) {
		boolean flag = false;
		if (push != null) {
			super.remove(push);// 删除
		}
		flag = true;
		return flag;
	}

	@Override
	public Paging<Push> queryPushForPaging(int pageNum, int pageSize, String condition, String orderString) {

		return super.queryForPagingByEntityNameAndCondition(pageNum, pageSize, "Push", condition, orderString);
	}

	@Override
	public Map<String, List<Push>> listPushByClientIDForMap(String ids, String pushKinds) {
		Map<String, List<Push>> map = new HashMap<String, List<Push>>();
		// mac地址字符串，以逗号分割
		if (ids != null && !"".equals(ids)) {
			// 拆分成数组
			String[] idArray = ids.split(",");
			if (idArray != null && idArray.length > 0) {
				
				String orderCondition = "";
				String pushKindCondition = "";
				String kinds="";
				if (pushKinds != null) {
					//System.out.println("query push data for :"+pushKinds);
					kinds = sysParamService.queryParamValueByParamCode(pushKinds.trim());
					if (kinds != null && !"".equals(kinds)) {
						// mysql的find_in_set函数，按参数降序排列
						orderCondition = "find_in_set(pushKind,'" + kinds + "') desc limit "+size+",";
						pushKindCondition = " and pushKind in(" + converToCondition(kinds) + ") ";
						if(pushKindCondition==null||"".equals(pushKindCondition))
						{
							return map;
						}
					}
				} else {
					
		/*			// 立即推送类型
					String instancy_kinds = sysParamService.queryParamValueByParamCode(Constants.PUSH_KINDS_INSTANCY);
					// 次要推送类型
					String secondary_kinds = sysParamService.queryParamValueByParamCode(Constants.PUSH_KINDS_SECONDARY);
					if (instancy_kinds != null&&!"".equals(instancy_kinds.trim())) {
						kinds=instancy_kinds;
					}
					if (secondary_kinds != null&&!"".equals(secondary_kinds.trim())) {
						if(!"".equals(kinds)&&kinds!=null)
						{
							kinds+=","+secondary_kinds;
						}else
						{
							kinds=secondary_kinds;
						}
					}
					//System.out.println("query push data for normal,pushKind not in  :"+kinds);
					//查询出非立即和次要推送的数据
					pushKindCondition = " and pushKind not in(" + converToCondition(kinds) + ") ";
		*/
				}
				for (String id : idArray) {
					if (id != null && !"".equals(id)) {

						int num = 10;// 默认一次查找10条
						// 从参数表取
						String pushDataNum = sysParamService.queryParamValueByParamCode("push_record_num");
						if (pushDataNum != null) {
							num = Integer.parseInt(pushDataNum);
						}
						// String hql =
						// "from Push where pushClientId =?1  order by addTime desc limit "+num;
						// List<Push> list = super.find(hql, id);
						String condition = "pushClientId ='" + id + "'" + pushKindCondition;
						String orderString = "order by " + orderCondition + "  addTime desc";
						//System.out.println("query condition  :"+condition+",order:"+orderString);
						Paging<Push> page = this.queryPushForPaging(1, num, condition, orderString);
						if (page != null) {
							List<Push> list = page.getResults();
							if (list != null && !list.isEmpty()) {
								List<Push> pushList = new ArrayList<Push>();
								// 按设置的条数，添加到集合中
								// if (list.size() < num) {
								pushList.addAll(list);
								// } else {
								// pushList.addAll(list.subList(0, num));

								// }
								if (pushList != null && !pushList.isEmpty()) {
									map.put(id, pushList);
								}
							}
						}
						//System.out.println("query "+(pushKinds==null?"normal":pushKinds)+" data ,and data size:"+map.size()+", query condition  :"+condition+",order:"+orderString);
					}
				}
			}

		}
        
		return map;
	}

	@Override
	public List<Push> listPushByClientID(String ids) {
		List<Push> pushList = null;
		if (ids != null && !"".equals(ids)) {
			String[] idArray = ids.split(",");
			if (idArray != null && idArray.length > 0) {
				pushList = new ArrayList<Push>();
				// String pushKinds =
				// sysParamService.queryParamValueByParamCode(Constants.PUSH_KIND_PRIORITY);
				// String orderCondition = "";
				// if (pushKinds != null && !"".equals(pushKinds)) {
				// // mysql的find_in_set函数，按参数降序排列
				// orderCondition = "find_in_set(pushKind,'" + pushKinds +
				// "') desc,";
				// }
				for (String id : idArray) {
					if (id != null && !"".equals(id)) {
						// 以pushKind优先降序，然后按时间降序
						// String hql =
						// "from Push where pushClientId =?1 order by " +
						// condition + " addTime desc";
						// List<Push> list = super.find(hql, id);
						int num = 10;// 默认一次查找10条
						String pushDataNum = sysParamService.queryParamValueByParamCode("push_record_num");
						if (pushDataNum != null) {
							num = Integer.parseInt(pushDataNum);
						}
						String condition = "pushClientId ='" + id + "'";
						String orderString = "order by addTime desc";
						Paging<Push> page = this.queryPushForPaging(1, num, condition, orderString);
						if (page != null) {
							List<Push> list = page.getResults();
							if (list != null && !list.isEmpty()) {
								// if (list.size() < num) {
								pushList.addAll(list);
								// } else {
								// pushList.addAll(list.subList(0, num));

								// }
							}
						}
					}
				}
			}

		}

		return pushList;
	}

	@Override
	public List<Push> listPush(String condition) {
		String hql = "from Push ";
		if (condition != null) {
			if (condition.contains("where")) {
				hql += " " + condition;
			} else {
				hql += " where " + condition;// 添加条件
			}
		}
		List<Push> list = super.find(hql);
		return list;
	}

	@Override
	public Paging<Push> queryPushForPaging(int pageNum, int pageSize, String condition, String orderString, Object... values) {
		return super.queryForPagingByEntityNameAndCondition(pageNum, pageSize, "Push", condition, orderString, values);
	}

	@Override
	public List<Push> listPush(String condition, Object... values) {
		String hql = "from Push ";
		if (condition != null) {
			if (condition.contains("where")) {
				hql += " " + condition;
			} else {
				hql += " where " + condition;
			}
		}
		List<Push> list = super.find(hql, values);
		return list;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void pushToAllClients(String pushName, String pushContent, String pushKind, String pushDescription, String pushVersion,
			Long size, String areaNo) {
		if (pushKind != null) {
			List<Device> list = deviceDao.findAll(areaNo);
			if (list != null && !list.isEmpty()) {
				List<Push> pushList = new ArrayList<Push>();
				for (Device d : list) {
					if (d != null) {
						String deviceNo = d.getDeviceCode();
						if (deviceNo != null) {
							Push p = new Push(pushName, pushKind, pushContent, pushVersion, new Date(), deviceNo, size);
							pushList.add(p);
						}
					}
				}
				if (pushList != null && !pushList.isEmpty()) {
					this.inserPushCollection(pushList);
				}

			}
		}

	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void pushToClients(String pushName, String pushContent, String pushKind, String pushDescription, String pushVersion, Long size,
			List<Device> list) {
		if (list != null && !list.isEmpty()) {
			List<Push> pushList = new ArrayList<Push>();
			for (Device d : list) {
				if (d != null) {
					String deviceNo = d.getDeviceCode();
					if (deviceNo != null) {
						Push p = new Push(pushName, pushKind, pushContent, pushVersion, new Date(), deviceNo, size);
						pushList.add(p);
					}
				}
			}
			if (pushList != null && !pushList.isEmpty()) {
				this.inserPushCollection(pushList);
			}

		}

	}

	/**
	 * 方法的描述: 将逗号分隔的字符串转换为加单引号再逗号分隔
	 * 
	 * @author: ykou
	 * @version: 0.1
	 * @date: 2012-12-11 下午12:04:57
	 * @param kinds
	 * @return
	 */
	private String converToCondition(String kinds) {
		StringBuffer sb = new StringBuffer();
		String[] kindArray = kinds.split(",");
		if (kindArray != null && kindArray.length > 0) {
			for (int i = 0; i < kindArray.length; i++) {
				sb.append("'" + kindArray[i] + "'");
				if (i < kindArray.length - 1) {
					sb.append(",");
				}
			}
		}
		return sb.toString();
	}

	public IDeviceDao getDeviceDao() {
		return deviceDao;
	}

	public void setDeviceDao(IDeviceDao deviceDao) {
		this.deviceDao = deviceDao;
	}

	public ISysParamService getSysParamService() {
		return sysParamService;
	}

	public void setSysParamService(ISysParamService sysParamService) {
		this.sysParamService = sysParamService;
	}

}
