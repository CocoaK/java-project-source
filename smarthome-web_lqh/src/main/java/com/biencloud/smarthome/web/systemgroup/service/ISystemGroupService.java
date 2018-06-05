package com.biencloud.smarthome.web.systemgroup.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.biencloud.smarthome.web.systemgroup.vo.GroupFlatVO;
import com.biencloud.smarthome.web.systemgroup.vo.SystemGroupVO;

/**
 * 组织模块领域服务接口。
 * @author kouy
 * @since 1.0 2012-4-14
 * @throws RuntimeException 当方法执行发生异常时
 */
public interface ISystemGroupService{

	/**
	 * 
	 * 方法的描述: 树状组装数据(组织管理中的树结构组装)，暂时弃用
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-6-12 下午2:38:17
	 * @param systemGroup：组织对象
	 * @return
	 */
	public List<Map<String, String>> querySystemGroup(SystemGroupVO systemGroup);
	
	/**
	 * 
	 * 方法的描述: 查询组织完整路径
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-6-12 下午2:42:52
	 * @param vo
	 * @return
	 */
	public String querySystemGroupByParams(SystemGroupVO vo);
	/**
	 * 
	 * 方法的描述: 根据小区ID获取完整路径信息
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-6-12 下午2:35:46
	 * @param comId:小区ID
	 * @return
	 */
	public String getCompletePosition(String comId);
	/**
	 * 保存或更新组织
	 * 方法的描述: 
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-6-12 下午2:36:01
	 * @param systemGroup:组织对象
	 * @return
	 */
	public boolean saveOrUpdateSystemGroup(SystemGroupVO systemGroup);
	/**
	 * 
	 * 方法的描述:  删除组织
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-6-12 下午2:36:15
	 * @param id:组织主键ID
	 * @return
	 */
	public boolean deleteSystemGroupById(Long id);
	
	/**
	 * 
	 * 方法的描述: 查询组织列表
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-6-12 下午2:43:46
	 * @param vo
	 * @return
	 */
	public List<SystemGroupVO> queryListByParams(SystemGroupVO vo);
	/**
	 * 
	 * 方法的描述: 树状数据组装
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-6-12 下午2:38:55
	 * @param receiverIds 在更新操作时，已选择的小区Set
	 * @param isCheckBox 是否使用复选框,true表示使用
	 * @return
	 */
	public Object[] querySystemGroupForCheck(Set<Long> receiverIds,boolean isCheckBox,boolean isShowDetail);
	/**
	 * 
	 * 方法的描述: 根据组织机构的名称查询，返回小区的组织层次关系(暂未使用)
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-6-12 下午2:39:23
	 * @param vo:未知
	 * @return
	 */
	public List<GroupFlatVO> queryGroupFlatListByParams(GroupFlatVO vo);
	/**
	 * 
	 * 方法的描述: 根据小区ID查询城市名称
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-6-14 上午10:26:08
	 * @param comId:小区ID
	 * @return
	 */
	public String getCityNameByDistrictId(String comId);
	/**
	 * 
	 * 方法的描述: 根据条件参数查询组织对象
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-11-26 上午11:02:51
	 * @param groupNo：组织编号
	 * @param parantGroupNo：父编号
	 * @param deep：深度
	 * @param name：名称
	 * @return
	 */
	public SystemGroupVO getSystemGroupVO(String groupNo,String parantGroupNo,Short deep,String name) ;
	
}
