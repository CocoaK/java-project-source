package com.biencloud.smarthome.web.requestrepair.service;

import java.util.List;

import com.biencloud.smarthome.web.common.vo.PagingVO;
import com.biencloud.smarthome.web.requestrepair.vo.RequestRepairVO;
import com.biencloud.smarthome.web.wsclient.stub.RequestRepair;

/**
 * 
 * 项目名称：smarthome-web-new 
 * 类名称：IRequestRepairService 
 * 类描述： 报修管理领域服务接口
 * @author: kouy 
 * @version: 0.1
 * @date: 2012-6-12 下午2:24:16
 */
public interface IRequestRepairService{

	/**
	 * 
	 * 方法的描述: 查询报修列表
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-6-12 下午2:20:25
	 * @param paramsOb: 报修对象
	 * @param pageNum:页码
	 * @param pageSize：显示条数
	 * @return
	 */
	public PagingVO<RequestRepairVO> queryRequestRepairVOForPaging(RequestRepairVO paramsOb, int pageNum, int pageSize);
	/**
	 * 
	 * 方法的描述: 更新报修
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-6-12 下午2:20:46
	 * @param entity：报修对象
	 */
	public boolean updateRequestRepairVO(RequestRepairVO entity) ;
	/**
	 * 
	 * 方法的描述: 保存报修
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-6-12 下午2:20:57
	 * @param entity：报修对象
	 */
	public boolean saveRequestRepairVO(RequestRepairVO entity);
	/**
	 * 
	 * 方法的描述: 获取单个报修对象
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-6-12 下午2:21:08
	 * @param entityId：报修对象主键
	 * @return
	 */
	public RequestRepairVO getRequestRepairVO(String entityId);
	/**
	 * 
	 * 方法的描述: 删除报修对象
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-6-12 下午2:21:22
	 * @param entity：报修对象
	 */
	public boolean delRequestRepairVO(String  id);
	/**
	 * 
	 * 方法的描述:  查询报修列表
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-6-12 下午2:20:18
	 * @param paramsOb
	 * @return
	 */
	public List<RequestRepairVO> queryRequestRepairForList(RequestRepairVO paramsOb);
	/**
	 * 
	 * 方法的描述: 实体类转换为VO类
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-6-12 下午2:09:48
	 * @param ob：实体类
	 * @param vo：VO类
	 */
	public void copyPropertiesObToVo(RequestRepair ob ,RequestRepairVO vo);
	/**
	 * 
	 * 方法的描述: VO类转换为实体类
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-6-12 下午2:10:19
	 * @param vo：VO类
	 * @param ob：实体类
	 */
	public void copyPropertiesVoToOb(RequestRepairVO vo ,RequestRepair ob);
	/**
	 * 
	 * 方法的描述:  物业查询今日报修/业主查询最近报修
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-6-12 下午2:26:50
	 * @param loginUserType:登录用户类型
	 * @param userId：登录用户ID
	 * @param districtId：小区ID
	 * @return
	 */
	public List<RequestRepairVO> queryRequestRepairVOForIndex(String loginUserType,String userId,String districtId);
	
	/**
	 * 
	 * 方法的描述: 业主查询报修数
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-6-12 下午2:26:27
	 * @param loginUserType:登录用户类型
	 * @param userId：登录用户ID
	 * @param districtId：小区ID
	 * @return
	 */
	public Long getRequestRepairCount(String loginUserType,String userId,String districtId);
}
