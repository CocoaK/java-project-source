package com.biencloud.smarthome.web.info.service;

import java.util.List;
import java.util.Set;

import com.biencloud.smarthome.web.common.vo.PagingVO;
import com.biencloud.smarthome.web.info.vo.InfoReceiverVO;
import com.biencloud.smarthome.web.wsclient.stub.InfoReceiver;

/**
 * 信息接收管理领域服务接口。
 * @author dehua ye
 * @since 1.0 2012-5-11
 * @see IService
 * @throws RuntimeException 如果操作执行失败
 */
public interface IInfoReceiverService{

	/**
	 * 查询信息接收列表
	 * 方法的描述: 
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-6-12 下午1:57:30
	 * @param paramsOb：信息接收对象
	 * @param pageNum：页码
	 * @param pageSize：显示条数
	 * @return
	 */
	public PagingVO<InfoReceiverVO> queryInfoReceiverVOForPaging(InfoReceiverVO paramsOb, int pageNum, int pageSize,String userid,String userType);
	/**
	 * 更新信息接收
	 * 方法的描述: 
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-6-12 下午1:57:53
	 * @param entity:信息接收对象
	 */
	public boolean updateInfoReceiverVO(InfoReceiverVO entity) ;
	/**
	 * 保存信息接收
	 * 方法的描述: 
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-6-12 下午1:58:04
	 * @param entity:信息接收对象
	 */
	public boolean saveInfoReceiverVO(InfoReceiverVO entity);
	/**
	 * 
	 * 方法的描述: 获取单个信息接收对象
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-6-12 下午1:58:18
	 * @param entityId：信息接收对象主键
	 * @return
	 */
	public InfoReceiverVO getInfoReceiverVO(String entityId,String loginUserType);
	/**
	 * 
	 * 方法的描述: 删除信息接收对象
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-6-12 下午1:58:38
	 * @param entity：信息接收对象
	 */
	public boolean delInfoReceiverVO(String  id);
	/**
	 * 查询信息接收列表
	 * 方法的描述: 
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-6-12 下午1:57:03
	 * @param paramsOb：信息接收对象
	 * @return
	 */
	public List<InfoReceiverVO> queryInfoReceiverForList(InfoReceiverVO paramsOb);
	/**
	 * 
	 * 方法的描述: 实体类转换为VO类
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-6-12 下午2:09:48
	 * @param ob：实体类
	 * @param vo：VO类
	 */
	public void copyPropertiesObToVo(InfoReceiver ob ,InfoReceiverVO vo);
	/**
	 * 
	 * 方法的描述: VO类转换为实体类
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-6-12 下午2:10:19
	 * @param vo：VO类
	 * @param ob：实体类
	 */
	public void copyPropertiesVoToOb(InfoReceiverVO vo ,InfoReceiver ob);
	/**
	 * 
	 * 方法的描述: 查询接收用户或者接收小区ID集合
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-6-12 下午2:13:17
	 * @param paramsOb:信息接收对象
	 * @param isDistrict:根节点是否是小区
	 * @return
	 */
	public Set<Long> queryInfoReceiverIdSet(InfoReceiverVO paramsOb,boolean isDistrict);
	/**
	 * 
	 * 方法的描述: 查询5条业主未读信息量
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-6-12 下午1:58:48
	 * @param paramsOb：信息接收对象
	 * @return
	 */
	public List<InfoReceiverVO> queryInfoReceiverVOForIndex(String loginUserType,String userId,String districtId);
	/**
	 * 
	 * 方法的描述: 查询未读消息数量
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-6-25 下午2:32:26
	 * @param paramsOb
	 * @return
	 */
	public Long getNoReadReceiverCount(String loginUserType,String userId,String districtId);
}
