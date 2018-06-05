package com.biencloud.smarthome.service.monitor.service;

import java.util.List;
import java.util.Map;

import com.biencloud.smarthome.service.base.service.IService;
import com.biencloud.smarthome.service.monitor.model.Scene;
import com.biencloud.smarthome.service.monitor.model.SceneDevice;
/**
 * 
 * 类名称：ISceneMonitorService 
 * 类描述：场景监控业务接口 
 * @author kouy  
 * @version 0.1
 * @date 2012-6-5 下午3:02:31
 */
public interface ISceneMonitorService extends IService<Scene, Long>{  
	
	
    /**
     * 
     * 方法的描述: 批量保存场景 与家具设备
     * @author: kouy  
     * @version: 0.1
     * @date: 2012-6-7 下午3:36:35
     * @param sceneList 场景集合
     * @param sceneDeviceList 家具设备集合
     * @return
     */
    public boolean saveSceneAndDevice(List<Scene> sceneList,List<SceneDevice> sceneDeviceList);
    /**
     * 
     * 方法的描述: 保存或更新场景
     * @author: kouy  
     * @version: 0.1
     * @date: 2012-6-7 下午3:31:56
     * @param scene
     * @return
     */
    public boolean saveScene(Scene scene);
    /**
     * 
     * 方法的描述: 批量 保存或更新场景设备
     * @author: kouy  
     * @version: 0.1
     * @date: 2012-6-7 下午3:38:16
     * @param sceneDeviceList 场景设备集合
     * @return
     */
    public boolean saveOrUpdateSceneDevice(List<SceneDevice> sceneDeviceList);
    /**
     * 
     * 方法的描述: 保存场景设备
     * @author: kouy  
     * @version: 0.1
     * @date: 2012-6-7 下午4:03:09
     * @param sceneDevice
     * @return
     */
    public boolean saveSceneDevice(SceneDevice sceneDevice);
    /**
     * 
     * 方法的描述: 批量保存或更新场景
     * @author: kouy  
     * @version: 0.1
     * @date: 2012-6-7 下午3:34:22
     * @param sceneList
     * @return
     */
    public boolean saveOrUpdateScene(List<Scene> sceneList);
    /**
	 * 
	 * 方法的描述: 保存场景
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-6-5 下午3:06:25
	 * @param scene 场景对象
	 * @return 成功为true,失败为false
	 */
    public Scene saveSceneForBackOjb(Scene scene);
   /**
    * 
    * 方法的描述:  根据设备编号和场景id删除场景
    * @author: kouy  
    * @version: 0.1
    * @date: 2012-7-5 下午5:47:46
    * @param deviceNo
    * @param sceneId
    * @return
    */
    public boolean deleteSceneByDeviceNoAndSceneId(String deviceNo,String sceneId);
    /**
     * 
     * 方法的描述: 根据设备编号和设备Id进行家具设备删除 
     * @author: kouy  
     * @version: 0.1
     * @date: 2012-7-5 下午5:59:06
     * @param deviceNo
     * @param deviceId
     * @return
     */
    public boolean deleteSceneDeviceByDeviceNoAndDeviceId(String deviceNo,String deviceId);
    /**
     * 
     * 方法的描述: 根据房号编码删除
     * @author: kouy  
     * @version: 0.1
     * @date: 2012-6-7 下午3:39:28
     * @param roomNo 房号编码
     * @return
     */
    public boolean deleteSceneByRoomNo(String roomNo);
    /**
     * 
     * 方法的描述: 根据房号编码删除家具设备
     * @author: kouy  
     * @version: 0.1
     * @date: 2012-6-7 下午3:42:07
     * @param roomNo 房号编码
     * @return
     */
    public boolean deleteSceneDeviceByRoomNo(String roomNo);
    /**
     * 
     * 方法的描述: 根据设备编号和场景模式删除场景
     * @author: kouy  
     * @version: 0.1
     * @date: 2012-6-5 下午3:29:42
     * @param deviceNo 设备编号
     * @param mode 场景模式
     * @return 成功为true,失败为false
     */
    public boolean deleteSceneByDeviceNoAndMode(String deviceNo,String mode);
    
    /**
     * 
     * 方法的描述: 根据房号编号删除场景和设备
     * @author: kouy  
     * @version: 0.1
     * @date: 2012-6-7 下午3:47:02
     * @param roomNo 房号编号
     * @return
     */
    public boolean deleteSceneAndDeviceByRoomNo(String roomNo);
    /**
     * 
     * 方法的描述: 根据设备编号获取场景
     * @author: kouy  
     * @version: 0.1
     * @date: 2012-6-5 下午3:10:43
     * @param deviceNo
     * @return 场景集合
     */
    public List<Scene> getSceneByDeviceNo(String deviceNo);
    /**
     * 
     * 方法的描述: 根据房号编号获取场景
     * @author: kouy  
     * @version: 0.1
     * @date: 2012-6-5 下午3:10:43
     * @param roomNo房号编号
     * @return 场景集合
     */
    public List<Scene> getSceneByRoomNo(String roomNo);
    /**
     * 
     * 方法的描述: 根据设备编号获取场景设备
     * @author: kouy  
     * @version: 0.1
     * @date: 2012-6-5 下午3:10:43
     * @param deviceNo 设备编号
     * @return 场景集合
     */
    public Map<String,List<SceneDevice>> getSceneDeviceByDeviceNo(String deviceNo);
    /**
     * 
     * 方法的描述: 根据房号编号获取场景设备
     * @author: kouy  
     * @version: 0.1
     * @date: 2012-6-7 下午3:53:23
     * @param roomNo 房号编号
     * @return
     */
    public Map<String,List<SceneDevice>> getSceneDeviceByRoomNo(String roomNo);
    /**
     * 
     * 方法的描述: 根据设备编号获取场景设备
     * @author: kouy  
     * @version: 0.1
     * @date: 2012-6-5 下午3:10:43
     * @param deviceNo
     * @return 场景集合
     */
    public List<SceneDevice> querySceneDeviceByDeviceNo(String deviceNo);
    /**
     * 
     * 方法的描述: 根据场景模式获得场景
     * @author: kouy  
     * @version: 0.1
     * @date: 2012-6-5 下午3:18:10
     * @param deviceNo 设备编号
     * @param mode 场景模式
     * @return 场景
     */
    public Scene getSceneByDeviceNo(String deviceNo,String mode);
    /**
     * 
     * 方法的描述: 推送送上传家电设备的命令
     * @author: kouy  
     * @version: 0.1
     * @date: 2012-6-5 下午5:08:37
     * @param deviceNo 设备编号
     * @return
     */
    public boolean sendSceneMonitorCommand(String deviceNo);
   /**
    * 
    * 方法的描述: 发送场景设备控制命令 
    * @author: kouy  
    * @version: 0.1
    * @date: 2012-6-7 下午4:50:48
    * @param deviceNo设备编号
    * @param list 选择的场景设备列表
    * @param sceneId 设置的场景Id
    * @return
    */
    public boolean sendSceneMonitorCommand(String deviceNo,List<SceneDevice> list,Long sceneId);
    /**
     * 
     * 方法的描述: 根据设备编号获得当前使用场景
     * @author: kouy  
     * @version: 0.1
     * @date: 2012-6-7 下午3:51:08
     * @param deviceNo 设备编号
     * @return
     */
    public Scene getIsUsedSceneByDeviceNo(String deviceNo,String sceneKind);
    /**
     * 
     * 方法的描述: 根据房号编号获得当前使用场景
     * @author: kouy  
     * @version: 0.1
     * @date: 2012-6-7 下午3:51:14
     * @param roomNo 房号编号
     * @return
     */
    public Scene getIsUsedSceneByRoomNo(String roomNo);
    /**
     * 
     * 方法的描述: 发送布防或撤防命令
     * @author: kouy  
     * @version: 0.1
     * @date: 2012-6-7 下午9:10:19
     * @param deviceNo 设备编号
     * @param sceneId 场景id
     * @param actionWay 方式：0表示撤防，1表示布防
     * @return 成功为true,失败为false
     */
    public boolean sendProtectionAndRemovalMonitorCommand(String deviceNo,Long sceneId,String actionWay,String sceneType);
    
    /**
     * 方法的描述: 根据设备编号删除家电设备
     * @author: ykou  
     * @version: 0.1
     * @date: 2012-8-24 下午6:07:26
     * @param deviceNo
     * @return
     */
    public boolean delSceneDeviceByDeviceNo(String deviceNo);
    
    /**
     * 方法的描述: 根据参数查询场景
     * @author: ykou  
     * @version: 0.1
     * @date: 2012-10-11 上午11:03:22
     * @param deviceNo
     * @param sceneKind
     * @return
     */
    public List<Scene> getScenes(String deviceNo,String sceneKind);
    
    /**
     * 
     * 方法的描述: 根据设备编号获取场景设备
     * @author: kouy  
     * @version: 0.1
     * @date: 2012-6-5 下午3:10:43
     * @param deviceNo,sceneId
     * @return 场景集合
     */
    public List<SceneDevice> querySceneDeviceByDeviceNo(String deviceNo,Long sceneId);
    
    /**
     * 方法的描述: 发送获取场景的命令
     * @author: ykou  
     * @version: 0.1
     * @date: 2012-11-16 下午3:07:18
     * @param deviceNo,sceneKind
     * @return
     */
    public boolean sendGetSceneCommand(String deviceNo,String sceneKind);
    
    /**
     * 方法的描述: 根据参数获取场景设备
     * @param deviceNo  设备编号
     * @param sceneId	场景编号
     * @param roomId	房间编号
     * @return
     */
    public List<SceneDevice> querySceneDevices(SceneDevice sceneDevice);

}
