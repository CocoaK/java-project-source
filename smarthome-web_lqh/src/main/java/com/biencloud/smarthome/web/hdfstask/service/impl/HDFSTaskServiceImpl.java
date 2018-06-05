package com.biencloud.smarthome.web.hdfstask.service.impl;

import java.util.List;
import com.biencloud.smarthome.web.base.service.BaseService;
import com.biencloud.smarthome.web.common.util.DateTimeUtil;
import com.biencloud.smarthome.web.common.util.MapUtil;
import com.biencloud.smarthome.web.common.vo.PagingVO;
import com.biencloud.smarthome.web.hdfstask.service.IHDFSTaskService;
import com.biencloud.smarthome.web.hdfstask.vo.HdfsTaskVO;
import com.biencloud.smarthome.web.wsclient.stub.HdfsTask;
import com.biencloud.smarthome.web.wsclient.stub.Paging;

/**
 * 
 * 类名称：HDFSTaskServiceImpl 类描述：
 * 
 * @author: kouy
 * @version: 0.1
 * @date: 2012-5-12 上午9:30:20
 */
public class HDFSTaskServiceImpl extends BaseService<HdfsTaskVO> implements IHDFSTaskService {

	@Override
	public PagingVO<HdfsTaskVO> queryHDFSTaskForPaging(int pageNum, int pageSize,HdfsTaskVO hdfsTaskVO) {
		
		PagingVO<HdfsTaskVO> pv = new PagingVO<HdfsTaskVO>();
		MapUtil.clearMap();
		String hql=null;
		List<Object> list=null;
		//封装查询条件
		if (hdfsTaskVO != null) {

			if (!"all".equals(hdfsTaskVO.getUploaderKind())) {
				MapUtil.addKeyValueToMap("uploaderKind", hdfsTaskVO.getUploaderKind());
			}
			if (!"all".equals(hdfsTaskVO.getKind())) {
				MapUtil.addKeyValueToMap("kind", hdfsTaskVO.getKind());
			}
			if (!"".equals(hdfsTaskVO.getFileName())&&hdfsTaskVO.getFileName()!=null) {
				MapUtil.addKeyValueToMap("fileName like", "%" + hdfsTaskVO.getFileName().replace("%", "\\%").replace("_", "\\_")+ "%");

			}
            //转成hql语句
			hql = MapUtil.covertMapKeyToCondition();
			//转成list集合
			list = MapUtil.covertMapValueToObjectList();
			
		}
		//查询
		pv = this.queryHDFSTaskForPaging(pageNum, pageSize, hql, "taskAddTime", "desc", list);
		return pv;
	}

	@Override
	public PagingVO<HdfsTaskVO> query(int pageNum, int pageSize, HdfsTaskVO hdfsTaskVO) {
		PagingVO<HdfsTaskVO> pv = new PagingVO<HdfsTaskVO>();
		MapUtil.clearMap();
		//封装查询条件
		if (hdfsTaskVO != null) {

			if (!"all".equals(hdfsTaskVO.getUploaderKind())) {
				MapUtil.addKeyValueToMap("uploaderKind", hdfsTaskVO.getUploaderKind());
			}
			if (!"all".equals(hdfsTaskVO.getKind())) {
				MapUtil.addKeyValueToMap("kind", hdfsTaskVO.getKind());
			}
			if (!"".equals(hdfsTaskVO.getFileName())&&hdfsTaskVO.getFileName()!=null) {
				MapUtil.addKeyValueToMap("fileName like", "%" + hdfsTaskVO.getFileName().replace("%", "\\%").replace("_", "\\_")+ "%");

			}
			//转成hql语句
			String hql = MapUtil.covertMapKeyToCondition();
			//转成list集合
			List<Object> list = MapUtil.covertMapValueToObjectList();
			//查询
			pv = this.queryHDFSTaskForPaging(pageNum, pageSize, hql, "taskAddTime", "desc", list);
		}
		return pv;
	}

	@Override
	public PagingVO<HdfsTaskVO> queryHDFSTaskForPaging(int pageNum, int pageSize, String condition, String orderString, String orderBy,
			List<Object> conditionValue) {
		String order = null;
		if (orderString != null && orderBy != null) {
			order = " order by " + orderString + " " + orderBy;
		}
		//查询
		Paging paging = getSmartHomeService().queryHdfsTaskForPageByKeyValue(pageNum, pageSize, condition, order, conditionValue);
		//转换
		PagingVO<HdfsTaskVO> pv = super.convertToPagingVO(paging, "taskAddTime", "taskAddTime");
		return pv;
	}

	@Override
	public HdfsTaskVO getHdfsTaskById(Long id) {
		HdfsTaskVO hdfsTaskVO=null;
		if(id!=null)
		{
			//查询
			HdfsTask hdfsTask=getSmartHomeService().queryHdfsTaskById(id);
			if(hdfsTask!=null)
			{
				//封装成新对象
				hdfsTaskVO=new HdfsTaskVO(hdfsTask.getId(), hdfsTask.getUploadPath(), hdfsTask.getHdfsPath(), hdfsTask.getTaskStatus(), DateTimeUtil.convertXMLGregorianCalendarToDate(hdfsTask.getTaskAddTime(), "yyyy-MM-dd HH:mm:ss"), null,
						hdfsTask.getFileName(), hdfsTask.getFileFormat(),hdfsTask.getFileSize(), hdfsTask.getBasepath(), hdfsTask.getKind(), hdfsTask.getUploaderKind());
			}
		}
		return hdfsTaskVO;
	}

}
