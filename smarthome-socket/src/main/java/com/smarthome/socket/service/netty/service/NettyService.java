package com.smarthome.socket.service.netty.service;

import java.util.List;
import java.util.Map;

import org.jboss.netty.channel.Channel;

import net.sf.json.JSONObject;

import com.smarthome.socket.common.model.ResultEntity;
import com.smarthome.socket.service.vo.LoginVO;
import com.smarthome.socket.service.vo.PushVO;
/**
 * 
 * 类名称：NettyService 
 * 类描述： netty业务接口
 * @author: kouy  
 * @version: 0.1
 * @date: 2012-5-9 下午6:16:49
 */
public interface NettyService {
	/**
	 * 
	 * 方法的描述: 设备登陆
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-5-9 下午8:03:57
	 * @param req
	 * @return
	 */
	public LoginVO login(JSONObject jb,String jsonType,String mac);
	/**
	 * 
	 * 方法的描述:推送数据 
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-5-9 下午6:17:13
	 * @return
	 */
   public List<PushVO> listPushData();
   /**
    * 
    * 方法的描述: 根据ID查找
    * @author: kouy  
    * @version: 0.1
    * @date: 2012-5-11 上午11:20:58
    * @param ids，id以逗号分隔
    * @return
    */
   public List<PushVO> listPushDataByClientID(String ids);
   /**
    * 
    * 方法的描述: 
    * @author: kouy  
    * @version: 0.1
    * @date: 2012-9-4 下午9:29:22
    * @param ids
    * @return
    */
   public Map<String,List<PushVO>> listPushDataByClientIDForMap(String ids,String pushKinds)throws Exception;
   /**
    * 
    * 方法的描述: 接收数据
    * @author: kouy  
    * @version: 0.1
    * @date: 2012-5-9 下午7:05:33
    */
   public boolean acceptData(JSONObject jb,String jsonType,String mac,String pushId);
   /**
    * 
    * 方法的描述: 保存推送完成数据
    * @author: kouy  
    * @version: 0.1
    * @date: 2012-5-15 上午11:34:27
    * @param id
    * @return
    */
   public boolean savePushFinishData(Long id);
   /**
    * 
    * 方法的描述:终端断开 
    * @author: kouy  
    * @version: 0.1
    * @date: 2012-5-15 下午9:46:42
    * @param deviceNo
    */
   public void loginOut(String mac);
   
   /**
    * 
    * 方法的描述: 写日志
    * @author: kouy  
    * @version: 0.1
    * @date: 2012-7-24 下午3:03:18
    * @param deviceNo
    * @param mac
    * @param deviceName
    * @param dataContent
    */
   public void saveClientLog(String deviceNo, String mac,String dataType, String deviceName, String dataContent,String areaNo,String ip,String from);
   /**
    * 
    * 方法的描述: 初始化设备状态
    * @author: kouy  
    * @version: 0.1
    * @date: 2012-8-2 下午5:10:09
    * @param status
    */
   public void initialDeviceStatus(String status);
   
   /**
    * 
    * 方法的描述: 更新设备状态
    * @author: kouy  
    * @version: 0.1
    * @date: 2012-9-5 上午10:06:20
    * @param status
    * @param mac
    */
   public void updateDeviceStatusByMac(String status,String mac);
   /**
	 * 
	 * 方法的描述: 查询定时器时间设置更新
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-12-11 下午3:36:52
	 */	
	public String queryCronTimeUpdate();
	
	 /**
		 * 
		 * 方法的描述: 根据mac地址直接发送数据
		 * @author: kouy  
		 * @version: 0.1
		 * @date: 2012-12-11 下午3:36:52
		 */	
	public ResultEntity<String> sendData(String jsonType,String data,String mac,Integer timeout) throws Exception;
	
	   /**
		 * 
		 * 方法的描述: 无放好信息关联的设备登录注册
		 * @author: kouy  
		 * @version: 0.1
		 * @date: 2012-12-11 下午3:36:52
		 */		
	public LoginVO register(JSONObject jb, String jsonType, String mac);
	
	/**
	 * 
	 * 方法的描述: 保存报警信息
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-12-11 下午3:36:52
	 */	
	public ResultEntity<String> saveAlarm(String data,String mac) throws Exception;
	
	/**
	 * 
	 * 方法的描述: 保存配件信息
	 * @author: kouy  
	 * @version: 0.1
	 * @throws Exception 
	 * @date: 2012-12-11 下午3:36:52
	 */	
	public ResultEntity<String> saveDeviceAccessories(String data,String mac) throws Exception;
	
	/**
	 * 推送设防状态
	 * @param mac
	 * @return
	 * @throws Exception
	 */
	public ResultEntity<String> pushDefenceStatusChanged(String data,String mac) throws Exception;
	
	/**
	 * 刷新配件
	 * @param data
	 * @param mac
	 * @return
	 * @throws Exception
	 */
	public ResultEntity<String> refreshAccessories(String data, String mac);
	
	/**
	 * 发送升级数据
	 */
	public ResultEntity<String> sendUpdateData(String data, String mac);
	
	/**
	 * 清除配件
	 */
	public ResultEntity<String> clearDeviceAccessories(String mac);
	/**
	 * @desc   @param data
	 * @desc   @param mac
	 * @desc   @throws Exception
	 * @return void
	 * @param data
	 * @param mac
	 * @throws Exception
	 */
	void pushDefenceStatusChangedThread(String data, String mac);
	/**
	 * @desc   @param status
	 * @desc   @param mac
	 * @return void
	 * @param status
	 * @param mac
	 */
	void updateDeviceStatusByMacThread(final String status, final String mac);
	/**
	 * @desc   @param status
	 * @return void
	 * @param status
	 */
	void initAllDeviceStatus(String status);
	/**
	 * @desc   @param data
	 * @desc   @param deviceNo
	 * @desc   @param mac
	 * @desc   @return
	 * @return ResultEntity<String>
	 * @param data
	 * @param deviceNo
	 * @param mac
	 * @return
	 */
	ResultEntity<String> sendData(String data, String deviceNo, String mac);
	/**
	 * @desc   @param jsonType json类型
	 * @desc   @param data
	 * @desc   @param deviceNo
	 * @desc   @param mac
	 * @desc   @return
	 * @return ResultEntity<String>
	 * @param jsonType
	 * @param data
	 * @param deviceNo
	 * @param mac
	 * @return
	 */
	ResultEntity<String> sendData(String jsonType, String data,
			String deviceNo, String mac);
	
//	/**
//	 * 
//	 * 方法的描述: 根据mac地址发送数据,传递key参数
//	 * @author: kouy  
//	 * @version: 0.1
//	 * @date: 2012-12-11 下午3:36:52
//	 */	
//	ResultEntity<String> sendData(String data, String mac, String key);
}
