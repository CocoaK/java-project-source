package com.biencloud.smarthome.cxfservice.service;

import java.util.List;
import java.util.Map;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import com.biencloud.smarthome.cxfservice.cxfmap.MapAdapter;
import com.biencloud.smarthome.cxfservice.cxfmap.PushMapAdapter;
import com.biencloud.smarthome.service.app.vo.AppLoginVO;
import com.biencloud.smarthome.service.app.vo.AppVO;
import com.biencloud.smarthome.service.log.model.ClientLog;
import com.biencloud.smarthome.service.push.model.Push;
import com.biencloud.smarthome.service.push.model.PushFinish;

/**
 * 
 * 类名称：SmartHomeTcpService 类描述：为tcp socket发布服务接口
 * 
 * @author: kouy
 * @version: 0.1
 * @date: 2012-5-9 下午2:10:48
 */
@WebService
public interface SmartHomeTcpService {
	/**
	 * 
	 * 方法的描述:按条件查询
	 * 
	 * @author: kouy
	 * @version: 0.1
	 * @date: 2012-5-9 下午2:01:27
	 * @param condition
	 * @return
	 */
	@WebMethod
	public List<Push> listPush(@WebParam String whereCondition);
	/**
	 * 
	 * 方法的描述:根据id查询 
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-5-11 上午11:03:36
	 * @param ids
	 * @return
	 */
	@WebMethod
	public List<Push> listPushByClientId(@WebParam String ids);

	/**
	 * 
	 * 方法的描述: 根据ID删除
	 * 
	 * @author: kouy
	 * @version: 0.1
	 * @date: 2012-5-9 上午11:37:05
	 * @param id
	 * @return
	 */
	@WebMethod
	public boolean deleteById(@WebParam Long id);

	/**
	 * 
	 * 方法的描述: 通过实体类进行删除
	 * 
	 * @author: kouy
	 * @version: 0.1
	 * @date: 2012-5-9 上午11:37:58
	 * @param push
	 * @return
	 */
	@WebMethod
	public boolean deleteByEntity(@WebParam Push push);

	/**
	 * 
	 * 方法的描述:插入已推送内容
	 * 
	 * @author: kouy
	 * @version: 0.1
	 * @date: 2012-5-9 下午1:55:34
	 * @param pushFinish
	 * @return
	 */
	//@WebMethod
	//public boolean insertPushFinish(@WebParam PushFinish pushFinish);
	@WebMethod
	public boolean insertPushFinish(@WebParam Long pushId);
	/**
	 * 
	 * 方法的描述: 根据ID查找
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-5-10 下午8:52:14
	 * @param id
	 * @return
	 */
	@WebMethod
	public Push findById(@WebParam Long id);
	/**
	 * 
	 * 方法的描述: 产生设备编号
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-5-15 下午8:25:45
	 * @return
	 */
	//@WebMethod
	//public String createDeviceNo();
	/**
	 * 
	 * 方法的描述: 根据mac查找
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-5-15 下午8:31:53
	 * @param mac
	 * @return
	 */
	//@WebMethod
	//public Device queryDeviceByMac(@WebParam String mac);
	
	/**
	 * 
	 * 方法的描述: 保存或更新
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-5-15 下午8:32:09
	 * @param device
	 * @return
	 */
	@WebMethod
	public AppVO saveOrUpdateDevice(@WebParam AppLoginVO loginInfo);
	
	/**
	 * 
	 * 方法的描述: 保存或更新
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-5-15 下午8:32:09
	 * @param device
	 * @return
	 */
	@WebMethod
	public AppVO saveOrUpdateDeviceByEasyLogin(@WebParam AppLoginVO loginInfo);
	
	/**
	 * 
	 * 方法的描述: 保存场景
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-6-5 下午3:06:25
	 * @param scene 场景对象
	 * @return 成功为true,失败为false
	 */
	//@WebMethod
	//public boolean saveScene(@WebParam Scene scene);
	
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
	//@WebMethod
    //public boolean saveSceneAndDevice(@WebParam List<Scene> sceneList,@WebParam List<SceneDevice> sceneDeviceList);
	
    
	/**
	 * 
	 * 方法的描述: 根据设备编号查询设备
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-6-7 下午8:03:08
	 * @param deviceNo 设备编号
	 * @return 设备
	 */
	//@WebMethod
	//public Device queryDeviceByDeviceNo(String deviceNo);
	/**
	 * 
	 * 方法的描述: 设备离线
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-7-13 下午8:15:10
	 * @param deviceNo
	 * @return
	 */
	@WebMethod
	public boolean deviceOutLine(String deviceNo);
	/**
	 * 
	 * 方法的描述: 保存日志
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-7-24 下午2:38:39
	 * @param clientLog
	 * @return
	 */
	@WebMethod
	public boolean saveClientLog(@WebParam ClientLog clientLog);
	/**
	 * 
	 * 方法的描述: 更新设备状态
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-9-4 下午8:39:06
	 * @param status
	 */
	@WebMethod
	public void updateDeviceStatusForAll(@WebParam String status);
	/**
	 * 
	 * 方法的描述: 更新设备状态
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-9-4 下午8:39:06
	 * @param status
	 */
	@WebMethod
	public void updateDeviceStatusByMac(@WebParam String status,String mac);
	
	/**
	 * 
	 * 方法的描述: 批量推送
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-9-4 下午8:40:15
	 * @param ids
	 * @return
	 */
	@WebMethod
	@XmlJavaTypeAdapter(PushMapAdapter.class)
	public Map<String,List<Push>> listPushByClientIDForMap(String ids,String pushKinds);
	/**
	 * 
	 * 方法的描述: 查询定时器时间设置更新
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-12-11 下午3:36:52
	 */
	@WebMethod
	public String queryCronTimeUpdate();

}
