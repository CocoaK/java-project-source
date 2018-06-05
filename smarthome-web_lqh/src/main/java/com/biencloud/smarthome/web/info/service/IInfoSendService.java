package com.biencloud.smarthome.web.info.service;

import java.util.List;

import com.biencloud.smarthome.web.common.vo.PagingVO;
import com.biencloud.smarthome.web.devicetype.vo.DeviceTypeVO;
import com.biencloud.smarthome.web.info.vo.InfoSendVO;
import com.biencloud.smarthome.web.wsclient.stub.InfoSend;

/**
 * 信息发布管理领域服务接口。
 * @author dehua ye
 * @since 1.0 2012-5-11
 * @see IService
 * @throws RuntimeException 如果操作执行失败
 */
public interface IInfoSendService{
	
	public static final String TREE_CELL_FLAG="0052463";

	/**
	 * 
	 * 方法的描述: 查询信息发布列表（分页）
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-6-12 下午1:59:40
	 * @param paramsOb：信息发布对象
	 * @param pageNum：页码
	 * @param pageSize：显示条数
	 * @return
	 */
	public PagingVO<InfoSendVO> queryInfoSendVOForPaging(InfoSendVO paramsOb, int pageNum, int pageSize);
	/**
	 * 
	 * 方法的描述: 更新信息发布
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-6-12 下午2:00:07
	 * @param entity：信息发布对象
	 */
	public boolean updateInfoSendVO(InfoSendVO entity,String comStr,String loginUserType,List<DeviceTypeVO> deviceTypes) ;
	/**
	 * 
	 * 方法的描述: 保存信息发布
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-6-12 下午2:00:18
	 * @param entity：信息发布对象
	 */
	public boolean saveInfoSendVO(InfoSendVO entity,String comStr,String loginUserType,List<DeviceTypeVO> deviceTypes);
	/**
	 * 
	 * 方法的描述: 获取信息发布对象
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-6-12 下午2:00:31
	 * @param entityId：信息发布对象主键
	 * @return
	 */
	public InfoSendVO getInfoSendVO(String entityId);
	/**
	 * 
	 * 方法的描述: 删除信息发布对象
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-6-12 下午2:01:08
	 * @param entity:信息发布对象
	 */
	public boolean delInfoSendVO(String  id);
	/**
	 * 
	 * 方法的描述: 查询信息发布列表（分页）
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-6-12 下午1:59:40
	 * @param paramsOb：信息发布对象
	 * @param pageNum：页码
	 * @param pageSize：显示条数
	 * @return
	 */
	public List<InfoSendVO> queryInfoSendForList(InfoSendVO paramsOb);
	/**
	 * 
	 * 方法的描述: 实体类转换为VO类
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-6-12 下午2:09:48
	 * @param ob：实体类
	 * @param vo：VO类
	 */
	public void copyPropertiesObToVo(InfoSend ob ,InfoSendVO vo);
	/**
	 * 
	 * 方法的描述: VO类转换为实体类
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-6-12 下午2:10:19
	 * @param vo：VO类
	 * @param ob：实体类
	 */
	public void copyPropertiesVoToOb(InfoSendVO vo ,InfoSend ob);
	/**
	 * 
	 * 方法的描述: 根据小区ID查询下面的区域、楼宇
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-6-12 下午2:01:21
	 * @param ob:信息发布对象
	 * @return
	 */
	public Object[] queryAreaData(InfoSendVO vo,boolean isUpdate,boolean isShowDetail);
	/**
	 * 
	 * 方法的描述:更新信息发布的状态 
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-6-12 下午2:10:43
	 * @param entityId:主键
	 * @param status：目标状态
	 * @param isSend：是否设置为发布状态
	 * @return
	 */
	public boolean updateStatus(String entityId,Integer status,String reply,boolean isSend);
	/**
	 * 
	 * 方法的描述:登录成功首页查询相关信息发布 
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-6-12 下午2:01:46
	 * @param paramsOb:信息发布对象
	 * @return
	 */
	public List<InfoSendVO> queryInfoSendForIndex(String loginUserType,String userId,String districtId);
	/**
	 * 
	 * 方法的描述: 登录成功首页查询相关信息发布数量统计
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-6-12 下午2:01:57
	 * @param paramsOb:信息发布对象
	 * @return
	 */
	public Long getInfoCount(String loginUserType,String userId,String districtId);
	/**
	 * 
	 * 方法的描述: 根据信息发布ID获取接收设备类型
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-11-27 上午11:16:58
	 * @param infoId:信息发布ID
	 * @return
	 */
	public String getReceiverDeviceType(String infoId);
	/**
	 * 
	 * 方法的描述: 树异步加载房间
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-11-21 上午11:49:21
	 * @param cellId
	 * @return
	 */
	public String queryHouseByCellId(String cellId);
}
