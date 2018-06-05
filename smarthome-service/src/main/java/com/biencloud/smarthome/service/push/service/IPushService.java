package com.biencloud.smarthome.service.push.service;

import java.util.List;
import java.util.Map;

import com.biencloud.smarthome.service.base.service.IService;
import com.biencloud.smarthome.service.common.model.Paging;
import com.biencloud.smarthome.service.device.model.Device;
import com.biencloud.smarthome.service.push.model.Push;


/**
 * 
 * 类名称：IPushService 
 * 类描述： 推送业务接口
 * @author: kouy  
 * @version: 0.1
 * @date: 2012-5-9 上午11:26:31
 */
public interface IPushService extends IService<Push, Long>{
   /**
    * 
    * 方法的描述:插入推送内容 
    * @author: kouy  
    * @version: 0.1
    * @date: 2012-5-9 上午11:31:22
    * @param push 推送对象
    * @return 成功为true,失败为false
    */
	public boolean insertPush(Push push);
	/**
	 * 
	 * 方法的描述:插入推送内容集合 
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-5-9 上午11:34:14
	 * @param pushList内容集合 
	 * @return 成功为true,失败为false
	 */
	public boolean inserPushCollection(List<Push> pushList);
	/**
	 * 
	 * 方法的描述: 通过ID查找
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-5-9 上午11:36:10
	 * @param id
	 * @return
	 */
	public Push findById(Long id);
	/**
	 * 
	 * 方法的描述: 根据ID删除
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-5-9 上午11:37:05
	 * @param id
	 * @return
	 */
	public boolean deleteById(Long id);
	/**
	 * 
	 * 方法的描述: 通过实体类进行删除
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-5-9 上午11:37:58
	 * @param push
	 * @return
	 */
	public boolean deleteByEntity(Push push);
	/**
	 * 
	 * 方法的描述: 分页处理
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-5-9 下午12:03:27
	 * @param pageNum
	 * @param pageSize
	 * @param condition 条件
	 * @return
	 */
	public Paging<Push> queryPushForPaging(
			 int pageNum, int pageSize,String condition,String orderString);
	/**
	 * 
	 * 方法的描述: 分页处理
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-6-13 上午9:04:16
	 * @param pageNum 页码
	 * @param pageSize 每页大小
	 * @param condition 条件
	 * @param orderString 顺序
	 * @param values 条件值
	 * @return
	 */
	public Paging<Push> queryPushForPaging(
			 int pageNum, int pageSize,String condition,String orderString,final Object... values);
	/**
	 * 
	 * 方法的描述: 按设备ID查询
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-5-11 上午10:52:15
	 * @param ids,id以逗号分割,如"123","222"
	 * @return
	 */
	public List<Push> listPushByClientID(String ids);
	/**
	 * 
	 * 方法的描述: 按设备ID查询
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-9-4 下午8:03:31
	 * @param ids
	 * @return
	 */
	public Map<String,List<Push>> listPushByClientIDForMap(String ids,String pushKinds);
	/**
	 * 
	 * 方法的描述: 根据条件查询
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-5-11 上午10:59:36
	 * @param condition 条件
	 * @return
	 */
	public List<Push> listPush(String condition);
	/**
	 * 
	 * 方法的描述:根据条件查询 
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-6-13 上午9:06:03
	 * @param condition条件
	 * @param values 条件值
	 * @return list集合
	 */
	public List<Push> listPush(String condition,final Object... values);
	/**
	 * 
	 * 方法的描述: 推送到所有终端
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-8-1 下午4:06:09
	 * @param pushName
	 * @param pushContent
	 * @param pushKind
	 * @param pushDescription
	 * @param pushVersion
	 * @param size
	 * @param areaNo 小区编号
	 */
	public void pushToAllClients(String pushName,String pushContent,String pushKind,String pushDescription,String pushVersion,Long size,String areaNo);
	/**
	 * 
	 * 方法的描述: 推送内容到终端
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-8-2 下午6:29:58
	 * @param pushName
	 * @param pushContent
	 * @param pushKind
	 * @param pushDescription
	 * @param pushVersion
	 * @param size
	 * @param list
	 */
	public void pushToClients(String pushName,String pushContent,String pushKind,String pushDescription,String pushVersion,Long size,List<Device> list);
	
	
}
