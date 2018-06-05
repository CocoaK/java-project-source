package com.biencloud.smarthome.web.device.service;

import java.util.List;

import com.biencloud.smarthome.web.appdata.json.Json;
import com.biencloud.smarthome.web.common.vo.PagingVO;
import com.biencloud.smarthome.web.device.vo.CallRecordVO;

/** 
 * 类名称：ICallRecordService 
 * 类描述： 设备留言服务接口
 * @author: ykou  
 * @version: 0.1
 * @date: 2012-11-24 上午11:16:23
 */
public interface ICallRecordService {

	/**
	 * 方法的描述: 根据ID查询通话记录
	 * @author: ykou  
	 * @version: 0.1
	 * @date: 2012-11-24 上午11:16:34
	 * @param id
	 * @return
	 */
	public CallRecordVO getCallRecordById(String id);
	
	/**
	 * 方法的描述: 根据条件查询留言
	 * @author: ykou  
	 * @version: 0.1
	 * @date: 2012-11-24 上午11:16:45
	 * @param callRecord
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	public PagingVO<CallRecordVO> queryCallRecordForPaging(CallRecordVO callRecord, int pageNum,int pageSize);
	
	/**
	 * 方法的描述: 保存留言或录像
	 * @author: ykou  
	 * @version: 0.1
	 * @date: 2012-11-24 上午11:16:56
	 * @param callRecord
	 */
	public void saveCallRecord(CallRecordVO callRecord);
	
	/**
	 * 方法的描述: 删除通话记录
	 * @author: ykou  
	 * @version: 0.1
	 * @date: 2012-5-21 下午4:59:31
	 * @param callRecord
	 */
	public void removeCallRecord(CallRecordVO callRecord);
	
	/**
	 * 
	 * 方法的描述: 查询所有通话记录列表
	 * @author: ykou  
	 * @version: 0.1
	 * @date: 2012-5-21 下午5:10:57
	 * @param callRecord
	 * @return List
	 */
	public List<CallRecordVO> queryCallRecords(CallRecordVO callRecord);
	
	/**
	 * 
	 * 方法的描述: 查询留言记录数
	 * @author: ykou  
	 * @version: 0.1
	 * @date: 2012-6-13 上午11:46:07
	 * @param callRecord
	 * @return Long
	 */
	public Long recordCount(CallRecordVO callRecord);
	
	/**
	 * 
	 * 方法的描述: APP发送通话记录Json数据保存为通话记录
	 * @author: ykou  
	 * @version: 0.1
	 * @date: 2012-6-21 下午2:19:26
	 * @param json
	 */
	public Json saveCallRecordByJson(String json);
	
	/**
	 * 方法的描述:保存通话记录 
	 * @author: ykou  
	 * @version: 0.1
	 * @date: 2012-9-13 下午3:30:11
	 * @param json
	 */
	public Json saveCallRecordMessageByJson(String json);
}
