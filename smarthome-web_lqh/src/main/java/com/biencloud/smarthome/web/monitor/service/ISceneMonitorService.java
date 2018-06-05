package com.biencloud.smarthome.web.monitor.service;

import java.util.List;
import com.biencloud.smarthome.web.appdata.json.Json;
import com.biencloud.smarthome.web.monitor.vo.SceneDeviceVo;
import com.biencloud.smarthome.web.monitor.vo.SceneVo;

/**
 * 场景监控业务接口 
 * 
 * @author jsun  
 * @since 1.0 2012-6-7
 */
public interface ISceneMonitorService {
	/**
	 * 方法的描述: 推送送上传家电设备的命令
	 * @author: ykou  
	 * @version: 0.1
	 * @date: 2012-11-28 上午10:14:35
	 * @param deviceNo 设备编号
	 * @return
	 */
	public boolean sendSceneMonitorCommand(String deviceNo);
	
	/**
	 * 方法的描述: 根据设备编号获取场景
	 * @author: ykou  
	 * @version: 0.1
	 * @date: 2012-11-28 上午10:14:46
	 * @param deviceNo 设备编号
	 * @return
	 */
	public List<SceneVo> getSceneByDeviceNo(String deviceNo);
	
	/**
	 * 方法的描述: 发送场景设备控制命令
	 * @author: ykou  
	 * @version: 0.1
	 * @date: 2012-11-28 上午10:14:59
	 * @param deviceNo 设备编号
	 * @param list	场景设备
	 * @param sceneId 场景Id
	 * @return
	 */
	public boolean sendSceneMonitorCommand(String deviceNo, List<SceneDeviceVo> list, Long sceneId);
	
	/**
	 * 方法的描述: 根据设备编号获取场景设备
	 * @author: ykou  
	 * @version: 0.1
	 * @date: 2012-11-28 上午10:15:10
	 * @param deviceNo 设备编号
	 * @return
	 */
	public List<SceneDeviceVo> querySceneDeviceByDeviceNo(String deviceNo);
	
	public List<SceneDeviceVo> querySceneDevice(SceneDeviceVo sceneDeviceVo);
	
	
	/**
	 * 方法的描述: 根据设备编号场景id获取场景设备
	 * @author: ykou  
	 * @version: 0.1
	 * @date: 2012-11-28 上午10:15:20
	 * @param deviceNo 设备编号
	 * @param sceneId 场景id
	 * @return
	 */
	public List<SceneDeviceVo> querySceneDeviceByDeviceNo(String deviceNo,Long sceneId);

	/**
	 * 方法的描述: 发送布防或撤防命令
	 * @author: ykou  
	 * @version: 0.1
	 * @date: 2012-11-28 上午10:17:29
	 * @param deviceNo
	 * @param sceneId
	 * @param actionWay 0: 撤防, 1: 布防
	 * @param sceneType
	 * @return
	 */
	public boolean sendProtectionAndRemovalMonitorCommand(String deviceNo, Long sceneId, String actionWay,String sceneType);
	/**
	 * 
	 * 方法的描述: 保存或更新场景数据
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-7-3 下午8:21:51
	 * @param jsonString
	 * @return
	 */
	public Json saveOrUpdateSceneData(String jsonString);
	/**
	 * 
	 * 方法的描述: 保存或更新房间设备
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-7-4 下午5:23:47
	 * @param jsonString
	 * @return
	 */
	public Json saveOrUpdateRoomDeviceData(String jsonString);
	/**
	 * 
	 * 方法的描述: app根据场景id进行删除
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-7-5 下午5:43:29
	 * @param jsonString
	 * @return
	 */
	public Json deleteSceneData(String jsonString);
	/**
	 * 
	 * 方法的描述: app根据场景设备id进行删除
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-7-5 下午5:44:48
	 * @param jsonString
	 * @return
	 */
	public Json deleteSceneDeviceData(String jsonString);
	
	/**
     * 方法的描述: 根据参数查询场景
     * @author: ykou  
     * @version: 0.1
     * @date: 2012-10-11 上午11:03:22
     * @param deviceNo
     * @param sceneKind
     * @return
     */
    public List<SceneVo> getScenes(String deviceNo,String sceneKind);
    
    /**
     * 
     * 方法的描述: 根据设备编号获得当前使用场景
     * @author: kouy  
     * @version: 0.1
     * @date: 2012-6-7 下午3:51:08
     * @param deviceNo 设备编号
     * @return
     */
    public SceneVo getIsUsedSceneByDeviceNo(String deviceNo,String sceneKind);
    
    /**
     * 方法的描述: 发送获取场景的命令
     * @author: ykou  
     * @version: 0.1
     * @date: 2012-11-16 下午3:07:18
     * @param deviceNo,sceneKind
     * @return
     */
    public boolean sendGetSceneCommand(String deviceNo,String sceneKind);
}
