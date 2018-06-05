package com.biencloud.smarthome.web.page.action;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.StringUtils;

import com.biencloud.smarthome.web.ad.vo.AdTargetVO;
import com.biencloud.smarthome.web.base.action.BaseAction;
import com.biencloud.smarthome.web.common.util.Constants;
import com.biencloud.smarthome.web.common.vo.PagingVO;
import com.biencloud.smarthome.web.device.vo.DeviceVO;
import com.biencloud.smarthome.web.devicetype.vo.DeviceTypeVO;
import com.biencloud.smarthome.web.housemgr.service.IHousingDistrictInfoService;
import com.biencloud.smarthome.web.housemgr.vo.HousingDistrictInfoVo;
import com.biencloud.smarthome.web.page.service.IPageService;
import com.biencloud.smarthome.web.page.vo.PageDistrictVO;
import com.biencloud.smarthome.web.page.vo.PageVO;
import com.biencloud.smarthome.web.systemgroup.service.ISystemGroupService;

/**
 * 
 * 类名称：DeviceCallAction 
 * 类描述：页面动作类
 * @author: ykou  
 * @version: 0.1
 */
public class PageAction extends BaseAction<PageVO>{
		
	private static final long serialVersionUID = -1095744104756427075L;
	//页面service
	private IPageService pageService;
	
	private PageVO pageVO;
	//成功标志
	private String successFlag;
	
	private String id;
	
	private String[] updateId;
	
	private String groupIds;
	
	private ISystemGroupService systemGroupService;
	
	private IHousingDistrictInfoService housingDistrictInfoService;
	/**
	 * 
	 * 方法的描述: 页面列表
	 * @author: ykou  
	 * @version: 0.1
	 * @return
	 * @throws Exception
	 */
	public String queryList() throws Exception{
		Long time = new Long(System.currentTimeMillis());
		if(updateId!=null){
			for(int i=0;i<updateId.length;i++){
				PageVO pVO = pageService.getPageById(updateId[i]);
				pVO.setUpdateId(time.toString());
				pageService.updatePage(pVO);
			}
		}
		
		PagingVO<PageVO> page = getPage();
		if(page == null)
			page = new PagingVO<PageVO>();
		PagingVO<PageVO> pageList = pageService.queryPageForPaging(pageVO, page.getPageNum(), page.getPageSize());
		super.setPage(pageList);
		return SUCCESS;
	}

	/**
	 * 
	 * 方法的描述: 输入页面
	 * @author: ykou  
	 * @version: 0.1
	 * @return
	 * @throws Exception
	 */
	public String addInput() throws Exception{
		setSystemGroups(false,null);
		return SUCCESS;
	}
	
	/**
	 * 
	 * 方法的描述: 新增页面
	 * @author: ykou  
	 * @version: 0.1
	 * @return
	 * @throws Exception
	 */
	public String add() throws Exception{
		//如果名称重复
		if(pageVO!=null){
			List<PageVO> list =  pageService.queryPage(pageVO);
			if(list!=null && list.size()>0){
				successFlag = "repeat_name";
				return SUCCESS;
			}
		}
		buildDistrcitIds();
		pageService.savePage(pageVO);
		successFlag = "success";
		setSuccessFlag(successFlag);
		pageVO = null;
		setSystemGroups(false,null);
		return queryList();
	}
	
	/**
	 * 
	 * 方法的描述: 删除页面
	 * @author: ykou  
	 * @version: 0.1
	 * @return
	 * @throws Exception
	 */
	public String delete() throws Exception{
		pageService.deletePage(id);
		return queryList();
	}
	
	
	public String updateInput() throws Exception{
		pageVO = pageService.getPageById(id);
		return SUCCESS;
	}
	
	/**
	 * 
	 * 方法的描述: 修改页面
	 * @author: ykou  
	 * @version: 0.1
	 * @return
	 * @throws Exception
	 */
	public String update() throws Exception{
		PageVO p = pageService.getPageById(id);
		//如果名称重复
		if(pageVO!=null){
			p.setPageDesc(pageVO.getPageDesc());
			if(StringUtils.isNotBlank(p.getName()) && !p.getName().equals(pageVO.getName())){
				p.setName(pageVO.getName());
				PageVO pg = new PageVO();
				pg.setName(pageVO.getName());
				List<PageVO> list =  pageService.queryPage(pg);
				if(list!=null && list.size()>0){
					successFlag = "repeat_name";
					return SUCCESS;
				}
			}
			
		}
		
		pageService.updatePage(p);
		successFlag = "success";
		return SUCCESS;
	}
	
	private void setSystemGroups(boolean isUpdated, List<AdTargetVO> adTargets){
		Set<Long> districtIds = null;
		if(isUpdated){						
			districtIds = new HashSet<Long>();
			for (AdTargetVO at : adTargets) {
				if(at.getDevice() != null 
						&& at.getDevice().getHousingDistrictInfo() != null){
					String districtId = at.getDevice().getHousingDistrictInfo().getId();
					if(StringUtils.isNotBlank(districtId))
						districtIds.add(Long.valueOf(districtId));
				}
			}
		}
		logger.info("********************当前小区编号：{}", districtIds);
		Object[] objects = getSystemGroupService().querySystemGroupForCheck(districtIds, true, false);
		setRequestAttribute("treeList", objects[0]);
		setRequestAttribute("comStr", objects[1]);
	}
	
	//构造广告投放目标
		private void buildDistrcitIds() {
			if(StringUtils.isNotBlank(groupIds)){
				List<String> districtIds = new ArrayList<String>();
				for (String id : StringUtils.split(groupIds, Constants.SEPARATOR_GROUP_ID)) {
					String districtId = housingDistrictInfoService.getDistrictIdByGroupNo(Long.valueOf(id));
					districtIds.add(districtId);
				}
				pageVO.setDistricts(districtIds);
			}
		}
	
	public IPageService getPageService() {
		return pageService;
	}

	public void setPageService(IPageService pageService) {
		this.pageService = pageService;
	}

	public PageVO getPageVO() {
		return pageVO;
	}

	public void setPageVO(PageVO pageVO) {
		this.pageVO = pageVO;
	}


	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSuccessFlag() {
		return successFlag;
	}

	public void setSuccessFlag(String successFlag) {
		this.successFlag = successFlag;
	}

	public String[] getUpdateId() {
		return updateId;
	}

	public void setUpdateId(String[] updateId) {
		this.updateId = updateId;
	}

	public ISystemGroupService getSystemGroupService() {
		return systemGroupService;
	}

	public void setSystemGroupService(ISystemGroupService systemGroupService) {
		this.systemGroupService = systemGroupService;
	}

	public String getGroupIds() {
		return groupIds;
	}

	public void setGroupIds(String groupIds) {
		this.groupIds = groupIds;
	}

	public IHousingDistrictInfoService getHousingDistrictInfoService() {
		return housingDistrictInfoService;
	}

	public void setHousingDistrictInfoService(
			IHousingDistrictInfoService housingDistrictInfoService) {
		this.housingDistrictInfoService = housingDistrictInfoService;
	}

}
