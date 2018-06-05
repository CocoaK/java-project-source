package com.biencloud.smarthome.service.monitor.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.biencloud.smarthome.service.base.service.impl.BaseService;
import com.biencloud.smarthome.service.common.constants.Constants;
import com.biencloud.smarthome.service.common.constants.PushKindConstants;
import com.biencloud.smarthome.service.device.dao.IDeviceDao;
import com.biencloud.smarthome.service.device.model.DeviceIp;
import com.biencloud.smarthome.service.monitor.constants.MonitorConstant;
import com.biencloud.smarthome.service.monitor.dao.ISceneDeviceDao;
import com.biencloud.smarthome.service.monitor.model.Scene;
import com.biencloud.smarthome.service.monitor.model.SceneDevice;
import com.biencloud.smarthome.service.monitor.service.ISceneMonitorService;
import com.biencloud.smarthome.service.monitor.vo.SceneDeviceCommand;
import com.biencloud.smarthome.service.push.dao.IPushDao;
import com.biencloud.smarthome.service.push.model.Push;

/**
 * 
 * 类名称：SceneMonitorServiceImpl 类描述： 场景监控业务接口实现类
 * 
 * @author kouy
 * @version 0.1
 * @date 2012-6-5 下午3:03:07
 */
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class SceneMonitorServiceImpl extends BaseService<Scene, Long> implements ISceneMonitorService {
	private IPushDao pushDao;
	private ISceneDeviceDao sceneDeviceDao;
	private IDeviceDao deviceDao;

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public boolean saveScene(Scene scene) {
		boolean isSuccess = false;
		if (scene != null) {
			super.save(scene);
			isSuccess = true;
		}
		return isSuccess;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public Scene saveSceneForBackOjb(Scene scene) {
		Scene _scene = null;
		if (scene != null) {
			_scene = super.save_update(scene);
		}
		return _scene;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public boolean deleteSceneByDeviceNoAndSceneId(String deviceNo,String sceneId) {
		boolean flag = false;
		if (deviceNo != null) {
			List<Scene> list = super.find("from Scene where deviceNo=?1 and sceneId=?2", deviceNo,sceneId);
			if (list != null && !list.isEmpty()) {
				// 删除
				super.removeAll(list);
				flag = true;
			}
		}
		return flag;
	}

	@Override
	public List<Scene> getSceneByDeviceNo(String deviceNo) {
		List<Scene> list = null;
		if (deviceNo != null) {
			list = super.find("from Scene where deviceNo=?", deviceNo);
		}
		return list;
	}

	@Override
	public Scene getSceneByDeviceNo(String deviceNo, String mode) {
		Scene scene = null;
		if (deviceNo != null) {
			List<Scene> list = super.find("from Scene where deviceNo=? and mode=?", deviceNo, mode);
			if (list != null && !list.isEmpty()) {
				scene = list.get(0);
			}
		}
		return scene;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public boolean deleteSceneByDeviceNoAndMode(String deviceNo, String mode) {
		boolean flag = false;
		if (deviceNo != null) {
			List<Scene> list = super.find("from Scene where deviceNo=? and mode=?", deviceNo, mode);
			if (list != null && !list.isEmpty()) {
				super.removeAll(list);
				flag = true;
			}
		}
		return flag;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public boolean sendSceneMonitorCommand(String deviceNo) {
		boolean flag = false;
		if (deviceNo != null) {
			//删除旧家电设备数据
			delSceneDeviceByDeviceNo(deviceNo);
			// 推送命令
			pushCommand("scene monitor",deviceNo, PushKindConstants.PUSH_LAMP_SCENE_COMMAND_KIND, "");
			// 删除旧的场景数据
			//this.deleteSceneAndDeviceByDeviceNo(deviceNo);
			
			flag = true;
		}
		return flag;
	}

	/**
	 * 
	 * 方法的描述: 推送命令
	 * 
	 * @author: kouy
	 * @version: 0.1
	 * @date: 2012-6-6 下午7:34:08
	 * @param deviceNo
	 */
	private void pushCommand(String pushName,String deviceNo, String kind, String content) {
		// 插入推送消息
		Push push = new Push(pushName, kind, content, new Date(), deviceNo);
		pushDao.save(push);
	}

	@Override
	public Map<String, List<SceneDevice>> getSceneDeviceByDeviceNo(String deviceNo) {
		Map<String, List<SceneDevice>> map = null;
		// 场景设备列表
		List<SceneDevice> list = this.querySceneDeviceByDeviceNo(deviceNo);
		if (list != null && !list.isEmpty()) {
			// 场景位置集合
			Set<String> postionNameSet = this.getAllPostionName(list);
			// 场景位置map集合
			map = covertListToMap(map, list, postionNameSet);
		}

		return map;
	}

	@Override
	public Map<String, List<SceneDevice>> getSceneDeviceByRoomNo(String roomNo) {
		Map<String, List<SceneDevice>> map = null;
		// 场景设备列表
		List<SceneDevice> list = this.querySceneDeviceByRoomNo(roomNo);
		if (list != null && !list.isEmpty()) {
			// 场景位置集合
			Set<String> postionNameSet = this.getAllPostionName(list);
			// 场景位置map集合
			map = covertListToMap(map, list, postionNameSet);
		}
		return map;
	}

	/**
	 * 
	 * 方法的描述: 将list转为map
	 * 
	 * @author: kouy
	 * @version: 0.1
	 * @date: 2012-6-6 下午12:11:25
	 * @param map
	 * @param list
	 * @param postionNameSet
	 * @return
	 */
	private Map<String, List<SceneDevice>> covertListToMap(Map<String, List<SceneDevice>> map, List<SceneDevice> list,
			Set<String> postionNameSet) {
		if (postionNameSet != null) {
			map = new HashMap<String, List<SceneDevice>>();
			for (String pn : postionNameSet) {
				if (pn != null) {
					List<SceneDevice> _list = new ArrayList<SceneDevice>();
					for (SceneDevice sd : list) {
						if (sd != null && pn.equals(sd.getPositionName())) {
							_list.add(sd);
						}
					}
					map.put(pn, _list);
				}
			}
		}
		return map;
	}

	/**
	 * 
	 * 方法的描述: 根据场景id获得设备列表
	 * 
	 * @author: kouy
	 * @version: 0.1
	 * @date: 2012-6-6 上午9:55:34
	 * @param sceneId
	 *            场景id
	 * @return 设备列表
	 */
	private List<SceneDevice> getSceneDeviceBySceneId(Long sceneId) {
		List<SceneDevice> list = null;
		if (sceneId != null && sceneId > 0) {
			list = sceneDeviceDao.find("from SceneDevice where sceneId=?", sceneId);
		}
		return list;
	}

	/**
	 * 
	 * 方法的描述: 根据设备编号查询场景
	 * 
	 * @author: kouy
	 * @version: 0.1
	 * @date: 2012-6-6 下午12:03:14
	 * @param deviceNo设备编号
	 * @return
	 */
	private Scene querySceneByDeviceNo(String deviceNo) {
		Scene scene = null;
		if (deviceNo != null) {
			List<Scene> list = super.find("from Scene where deviceNo=?", deviceNo);
			if (list != null && !list.isEmpty()) {
				scene = list.get(0);
			}
		}
		return scene;
	}

	/**
	 * 
	 * 方法的描述: 根据设备编号和场景id查询场景
	 * 
	 * @author: kouy
	 * @version: 0.1
	 * @date: 2012-6-7 下午4:53:40
	 * @param deviceNo
	 *            设备编号
	 * @param sceneId
	 *            场景id
	 * @return
	 */
	private Scene querySceneByDeviceNo(String deviceNo, Long sceneId) {
		Scene scene = null;
		if (deviceNo != null) {
			List<Scene> list = super.find("from Scene where deviceNo=? and sceneId=?", deviceNo, sceneId.toString());
			if (list != null && !list.isEmpty()) {
				scene = list.get(0);
			}
		}
		return scene;
	}

	/**
	 * 
	 * 方法的描述: 获得集合中所有的位置信息
	 * 
	 * @author: kouy
	 * @version: 0.1
	 * @date: 2012-6-6 上午11:55:19
	 * @param list
	 *            集合
	 * @return 所有的位置信息
	 */
	private Set<String> getAllPostionName(List<SceneDevice> list) {
		Set<String> set = null;
		if (list != null && !list.isEmpty()) {
			set = new HashSet<String>(0);
			for (SceneDevice sd : list) {
				if (sd != null) {
					String postionName = sd.getPositionName();
					if (postionName != null) {
						set.add(postionName);
					}
				}
			}

		}
		return set;
	}

	/**
	 * 
	 * 方法的描述: 根据场景id获得设备列表
	 * 
	 * @author: kouy
	 * @version: 0.1
	 * @date: 2012-6-6 上午9:55:34
	 * @param sceneId
	 *            场景id
	 * @return 设备列表
	 */
	private List<SceneDevice> getPositionNameBySceneId(Long sceneId) {
		List<SceneDevice> list = null;
		if (sceneId != null && sceneId > 0) {
			list = sceneDeviceDao.find("select new SceneDevice(positionName) from SceneDevice where sceneId=?", sceneId);
		}
		return list;
	}

	@Override
	public List<SceneDevice> querySceneDeviceByDeviceNo(String deviceNo) {
		List<SceneDevice> list = null;

		if (deviceNo != null) {
			// 场景设备列表
			list = sceneDeviceDao.find("from SceneDevice where deviceNo=?", deviceNo);
		}
		return list;
	}
	/**
	 * 
	 * 方法的描述:根据设备id查询场景设备 
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-7-4 下午2:53:23
	 * @param deviceId
	 * @return
	 */
	public SceneDevice querySceneDeviceByDeviceId(String deviceId,String deviceNo) {
		SceneDevice sceneDevice = null;
		if (deviceId != null) {
			// 场景设备列表
		  List<SceneDevice>	list = sceneDeviceDao.find("from SceneDevice where deviceId=?1 and deviceNo=?2", deviceId,deviceNo);
		  if(list!=null&&!list.isEmpty())
		  {
			  sceneDevice=list.get(0);
		  }
		}
		return sceneDevice;
	}
	public List<SceneDevice> querySceneDeviceByRoomNo(String roomNo) {
		List<SceneDevice> list = null;

		if (roomNo != null) {
			// 场景设备列表
			list = sceneDeviceDao.find("from SceneDevice where roomNo=?", roomNo);
		}
		return list;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public boolean sendSceneMonitorCommand(String deviceNo, List<SceneDevice> list, Long sceneId) {
		boolean flag = false;
		if (deviceNo != null && list != null && !list.isEmpty()) {
			//Scene scene = this.querySceneByDeviceNo(deviceNo, sceneId);
			if (list != null) {
				StringBuilder sb = new StringBuilder();
				// 拼成命令集
				for (SceneDevice sd : list) {
					if (sd != null && sd.getDeviceNo() != null && sd.getDeviceNo().equals(deviceNo)) {
						SceneDeviceCommand sdc = new SceneDeviceCommand(sceneId.toString(), sd.getDeviceId(), sd.getStatus());
						sb.append(sdc.toString());
						sb.append(";");
					}
				}
				if (sb.length() > 0) {
					String s = sb.toString();
					String content = s.substring(0, s.length() - 1);
					this.pushCommand("sceneDeviceStatus",deviceNo, PushKindConstants.PUSH_SET_SCENE_DEVICE_STATUS_KIND, content);
					// 将已经使用场景改为未使用状态
					//Scene usedSscene = this.getIsUsedSceneByDeviceNo(deviceNo,Constants.SCENE_KIND_DEVICE);
					//usedSscene.setIsUse(MonitorConstant.SCENE_UNUSED_STATUS);
					// 将设置的场景改为使用状态
					//scene.setIsUse(MonitorConstant.SCENE_USED_STATUS);
					//super.update(scene);
					//super.update(usedSscene);
					flag = true;
				}
			}
		}
		return flag;
	}

	

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public boolean saveSceneAndDevice(List<Scene> sceneList, List<SceneDevice> sceneDeviceList) {
		boolean flag = false;
		this.saveOrUpdateScene(sceneList);
		//this.saveSceneDevice(sceneDeviceList);
		flag = true;
		return flag;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public boolean saveOrUpdateSceneDevice(List<SceneDevice> sceneDeviceList) {
		boolean flag = false;
		if (sceneDeviceList != null && !sceneDeviceList.isEmpty()) {
			List<SceneDevice> sdList=new ArrayList<SceneDevice>();
			for(SceneDevice sd:sceneDeviceList)
			{
				if(sd!=null)
				{
					SceneDevice existSceneDevice=this.querySceneDeviceByDeviceId(sd.getDeviceId(), sd.getDeviceNo());
					if(existSceneDevice!=null)
					{
						existSceneDevice.setAddTime(new Date());
						existSceneDevice.setDeviceName(sd.getDeviceName());
						existSceneDevice.setKind(sd.getKind());
						existSceneDevice.setRoomNo(sd.getRoomNo());
						existSceneDevice.setStatus(sd.getStatus());
						sdList.add(existSceneDevice);
					}else
					{
						sd.setAddTime(new Date());
						sdList.add(sd);
					}
				}
			}
			sceneDeviceDao.saveOrUpdateCollection(sdList);
			flag = true;
		}
		return flag;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public boolean saveSceneDevice(SceneDevice sceneDevice) {
		boolean flag = false;
		if (sceneDevice != null) {
			sceneDeviceDao.save(sceneDevice);
			flag = true;
		}
		return flag;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public boolean saveOrUpdateScene(List<Scene> sceneList) {
		boolean flag = false;
		if (sceneList != null && !sceneList.isEmpty()) {
			List<Scene> sList=new ArrayList<Scene>();
			
			for(Scene se:sceneList)
			{
				if(se!=null)
				{
					Scene _secene=querySceneBySceneId(se.getSceneId());
					if(_secene!=null)
					{
						_secene.setDeviceNo(se.getDeviceNo());
						_secene.setSceneId(se.getSceneId());
						_secene.setRoomNo(se.getRoomNo());
						_secene.setIsUse(se.getIsUse());
						_secene.setSceneKind(se.getSceneKind());
						_secene.setMode(se.getMode());
						_secene.setAddTime(new Date());
						_secene.setSceneName(se.getSceneName());
						sList.add(_secene);						
					}else
					{						
						se.setAddTime(new Date());
						sList.add(se);
					}
				}
			}
			super.saveOrUpdateCollection(sList);
			flag = true;
		}
		return flag;
	}
	/**
	 * 
	 * 方法的描述: 根据场景id查询场景
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-7-3 下午3:03:10
	 * @param sceneId
	 * @return
	 */
    private Scene querySceneBySceneId(String sceneId)
    {
    	Scene scene=null;
    	if(sceneId!=null)
    	{
    		List<Scene> list=super.find("from Scene where sceneId=?", sceneId);
    		if(list!=null&&!list.isEmpty())
    		{
    			scene=list.get(0);
    		}
    	}
    	return scene;
    }
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public boolean deleteSceneDeviceByDeviceNoAndDeviceId(String deviceNo,String deviceId) {
		boolean flag = false;
		if (deviceNo != null) {
			List<SceneDevice> list = sceneDeviceDao.find("from SceneDevice where deviceNo=?1 and deviceId=?2", deviceNo,deviceId);
			if (list != null && !list.isEmpty()) {
				sceneDeviceDao.removeAll(list);
				flag = true;
			}

		}
		return flag;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public boolean deleteSceneByRoomNo(String roomNo) {
		boolean flag = false;
		if (roomNo != null) {
			List<Scene> list = super.find("from Scene where roomNo=?", roomNo);
			if (list != null && !list.isEmpty()) {
				super.removeAll(list);
				flag = true;
			}

		}
		return flag;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public boolean deleteSceneDeviceByRoomNo(String roomNo) {
		boolean flag = false;
		if (roomNo != null) {
			List<SceneDevice> list = sceneDeviceDao.find("from SceneDevice where roomNo=?", roomNo);
			if (list != null && !list.isEmpty()) {
				sceneDeviceDao.removeAll(list);
				flag = true;
			}

		}
		return flag;
	}

	
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public boolean deleteSceneAndDeviceByRoomNo(String roomNo) {
		boolean flag = false;
		this.deleteSceneByRoomNo(roomNo);
		this.deleteSceneDeviceByRoomNo(roomNo);
		flag = true;
		return flag;
	}

	@Override	
	public Scene getIsUsedSceneByDeviceNo(String deviceNo,String sceneKind) {
		Scene scene = null;
		if (deviceNo != null) {
			List<Scene> list = super.find("from Scene where deviceNo=? and isUse=? and sceneKind=? ", deviceNo, MonitorConstant.SCENE_USED_STATUS,sceneKind);
			if (list != null && !list.isEmpty()) {
				scene = list.get(0);
			}
		}
		return scene;
	}

	@Override	
	public Scene getIsUsedSceneByRoomNo(String roomNo) {
		Scene scene = null;
		if (roomNo != null) {
			List<Scene> list = super.find("from Scene where roomNo=? and isUse=?", roomNo, MonitorConstant.SCENE_USED_STATUS);
			if (list != null && !list.isEmpty()) {
				scene = list.get(0);
			}
		}
		return scene;
	}

	@Override	
	public List<Scene> getSceneByRoomNo(String roomNo) {
		List<Scene> list = null;
		if (roomNo != null) {
			list = super.find("from Scene where roomNo=? ", roomNo);
		}
		return list;
	}
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public boolean sendProtectionAndRemovalMonitorCommand(String deviceNo, Long sceneId, String actionWay,String sceneType) {
		boolean flag=false;
		String pushKind = "";
		if(Constants.SCENE_KIND_DEVICE.equals(sceneType)){
			//pushKind = PushKindConstants.PUSH_SET_SCENE_DEVICE_STATUS_KIND;
			pushKind = PushKindConstants.PUSH_PROTECTION_COMMAND_KIND;
		}
		if(Constants.SCENE_KIND_PROTECTION_AND_REMOVAL.equals(sceneType)){
			pushKind = PushKindConstants.PUSH_PROTECTION_COMMAND_KIND;
		}
		if (deviceNo != null && sceneId != null && actionWay != null) {
			Scene scene = this.querySceneByDeviceNo(deviceNo, sceneId);
			if (scene != null) {
				if (MonitorConstant.SCENE_PROTECTION_STATUS.equals(actionWay)) {
					this.pushCommand(pushKind,deviceNo, pushKind, "sceneId:"+scene.getSceneId()+",status:"+MonitorConstant.SCENE_PROTECTION_STATUS);
					// 将已经使用场景改为未使用状态
					Scene usedSscene = this.getIsUsedSceneByDeviceNo(deviceNo,sceneType);
					if(usedSscene!=null){
						usedSscene.setIsUse(MonitorConstant.SCENE_UNUSED_STATUS);
						super.update(usedSscene);
					}
					// 将设置的场景改为使用状态
					scene.setIsUse(MonitorConstant.SCENE_USED_STATUS);	//设置场景为使用中的场景
					super.update(scene);	//更新使用中的场景
					
					flag=true;
				}else
				{
					this.pushCommand("removal",deviceNo, PushKindConstants.PUSH_REMOVAL_COMMAND_KIND, "sceneId:"+scene.getSceneId()+",status:"+MonitorConstant.SCENE_REMOVAL_STATUS);
					flag=true;
				}
			}
		}
		return flag;
	}
	public IPushDao getPushDao() {
		return pushDao;
	}

	public void setPushDao(IPushDao pushDao) {
		this.pushDao = pushDao;
	}

	public ISceneDeviceDao getSceneDeviceDao() {
		return sceneDeviceDao;
	}

	public void setSceneDeviceDao(ISceneDeviceDao sceneDeviceDao) {
		this.sceneDeviceDao = sceneDeviceDao;
	}

	public IDeviceDao getDeviceDao() {
		return deviceDao;
	}

	public void setDeviceDao(IDeviceDao deviceDao) {
		this.deviceDao = deviceDao;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public boolean delSceneDeviceByDeviceNo(String deviceNo) {
		boolean flag = false;
		if (deviceNo != null) {
			List<SceneDevice> list = sceneDeviceDao.find("from SceneDevice where deviceNo=?", deviceNo);
			if (list != null && !list.isEmpty()) {
				sceneDeviceDao.removeAll(list);
				flag = true;
			}
		}
		return flag;
	}

	@Override
	public List<Scene> getScenes(String deviceNo, String sceneKind) {
		List<Scene> list = null;
		if (deviceNo != null) {
			list = super.find("from Scene where deviceNo=?1 and sceneKind=?2", deviceNo,sceneKind);
		}
		return list;
	}

	@Override
	public List<SceneDevice> querySceneDeviceByDeviceNo(String deviceNo, Long sceneId) {
		List<SceneDevice> list = null;
		if (deviceNo != null) {
			// 场景设备列表
			list = sceneDeviceDao.find("from SceneDevice where deviceNo=?1 and sceneId=?2", deviceNo,sceneId);
		}
		return list;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public boolean sendGetSceneCommand(String deviceNo,String sceneKind) {
		boolean flag = false;
		List<Scene> scenes = null;
		if (deviceNo != null) {
			//根据设备编号和场景类型查询场景，如果查询结果为空则向APP端发送上传场景的命令
			scenes = getScenes(deviceNo,sceneKind);
			if(scenes!=null && scenes.size()>0){
				super.removeAll(scenes);
			}
			//向APP端发送上传场景的命令
			pushCommand("upload scene",deviceNo, PushKindConstants.PUSH_SCENE_UPLOAD_COMMAND, "sceneType:"+sceneKind);
			flag = true;
		}
		return flag;
	}

	@Override
	public List<SceneDevice> querySceneDevices(SceneDevice sceneDevice) {
		StringBuilder jpql = new StringBuilder();
		Map<String, Object> params = createQueryParams(jpql, sceneDevice);
		jpql.insert(0, "SELECT sceneDevice FROM SceneDevice sceneDevice");
		String queryString = jpql.toString().replace(REPLACE_CHARS, "sceneDevice");
		return sceneDeviceDao.findByNamedParams(queryString, params);
	}
	
	// 创建属性变量名和属性值映射。
		private Map<String, Object> createQueryParams(StringBuilder jpql, SceneDevice sceneDevice) {
			Map<String, Object> params = new HashMap<String, Object>();
			if (sceneDevice == null)
				return params;
			if (StringUtils.isNotBlank(sceneDevice.getDeviceId()))
				appendCondition(jpql, "sceneDevice.deviceId = :deviceId", "deviceId", sceneDevice.getDeviceId(), params);
			
			if (StringUtils.isNotBlank(sceneDevice.getDeviceNo()))
				appendCondition(jpql, "sceneDevice.deviceNo = :deviceNo", "deviceNo", sceneDevice.getDeviceNo(), params);

			if (StringUtils.isNotBlank(sceneDevice.getKind()))
				appendCondition(jpql, "sceneDevice.kind = :kind", "kind", sceneDevice.getKind(), params);
			
			if (StringUtils.isNotBlank(sceneDevice.getRoomNo()))
				appendCondition(jpql, "sceneDevice.roomNo = :roomNo", "roomNo", sceneDevice.getRoomNo(), params);

			return params;
		}
}
