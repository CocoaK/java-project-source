package com.biencloud.smarthome.web.hdfs.service.impl;

import java.util.List;
import com.biencloud.smarthome.web.base.service.BaseService;
import com.biencloud.smarthome.web.common.util.DateTimeUtil;
import com.biencloud.smarthome.web.common.util.MapUtil;
import com.biencloud.smarthome.web.common.vo.PagingVO;
import com.biencloud.smarthome.web.hdfs.service.IHDFSFileService;
import com.biencloud.smarthome.web.hdfs.vo.LocalHdfsVO;
import com.biencloud.smarthome.web.wsclient.stub.LocalHdfs;
import com.biencloud.smarthome.web.wsclient.stub.Paging;

/**
 * 
 * 类名称：HDFSFileServiceImpl 类描述：
 * 
 * @author: kouy
 * @version: 0.1
 * @date: 2012-5-12 上午9:27:23
 */
public class HDFSFileServiceImpl extends BaseService<LocalHdfsVO> implements IHDFSFileService {

	@Override
	public PagingVO<LocalHdfsVO> queryHDFSFileForPaging(int pageNum, int pageSize, LocalHdfsVO localHdfsVO) {

		PagingVO<LocalHdfsVO> pv = new PagingVO<LocalHdfsVO>();
		MapUtil.clearMap();

		String hql = null;
		List<Object> list = null;
		//封装查询条件
		if (localHdfsVO != null) {
			if (!"all".equals(localHdfsVO.getUploaderKind())) {
				MapUtil.addKeyValueToMap("uploaderKind", localHdfsVO.getUploaderKind());
			}
			if (!"all".equals(localHdfsVO.getKind())) {
				MapUtil.addKeyValueToMap("kind", localHdfsVO.getKind());
			}
			if (!"".equals(localHdfsVO.getFileName())&&localHdfsVO.getFileName()!=null) {
				MapUtil.addKeyValueToMap("fileName like", "%" + localHdfsVO.getFileName().replace("%", "\\%").replace("_", "\\_") + "%");
			}
			//转成hql语句
			hql = MapUtil.covertMapKeyToCondition();
			//转成list集合
			list = MapUtil.covertMapValueToObjectList();

		}
		//查询
		pv = this.queryHDFSFileForPaging(pageNum, pageSize, hql, "addTime", "desc", list);
		return pv;
	}

	@Override
	public PagingVO<LocalHdfsVO> query(int pageNum, int pageSize, LocalHdfsVO localHdfsVO) {
		PagingVO<LocalHdfsVO> pv = new PagingVO<LocalHdfsVO>();

		MapUtil.clearMap();
		//封装查询条件
		if (localHdfsVO != null) {
			if (!"all".equals(localHdfsVO.getUploaderKind())) {
				MapUtil.addKeyValueToMap("uploaderKind", localHdfsVO.getUploaderKind());
			}
			if (!"all".equals(localHdfsVO.getKind())) {
				MapUtil.addKeyValueToMap("kind", localHdfsVO.getKind());
			}
			if (!"".equals(localHdfsVO.getFileName())&&localHdfsVO.getFileName()!=null) {
				MapUtil.addKeyValueToMap("fileName like", "%" + localHdfsVO.getFileName().replace("%", "\\%").replace("_", "\\_") + "%");
			}
			//转成hql语句
			String hql = MapUtil.covertMapKeyToCondition();
			//转成list集合
			List<Object> list = MapUtil.covertMapValueToObjectList();
			//查询
			pv = this.queryHDFSFileForPaging(pageNum, pageSize, hql, "addTime", "desc", list);
		}
		return pv;
	}

	@Override
	public PagingVO<LocalHdfsVO> queryHDFSFileForPaging(int pageNum, int pageSize, String condition, String orderString, String orderBy,
			List<Object> conditionValue) {
		String order = null;
		if (orderString != null && orderBy != null) {
			order = " order by " + orderString + " " + orderBy;
		}
		//查询
		Paging paging = getSmartHomeService().queryLocalHdfsForPageByKeyValue(pageNum, pageSize, condition, order, conditionValue);
		//转化
		PagingVO<LocalHdfsVO> pv = super.convertToPagingVO(paging, "addTime");
		return pv;
	}

	@Override
	public LocalHdfsVO getLocalHdfsById(Long id) {
		LocalHdfsVO localHdfsVO=null;
		if(id!=null)
		{
			//查询
			LocalHdfs localHdfs=getSmartHomeService().queryLocalHdfsById(id);
			if(localHdfs!=null)
			{
				//封装到新对象
				localHdfsVO=new LocalHdfsVO(localHdfs.getId(), localHdfs.getLocalPath(), localHdfs.getHdfsPath(),localHdfs.getFileName(), localHdfs.getFileSize(), localHdfs.getTransferWay(), DateTimeUtil.convertXMLGregorianCalendarToDate(localHdfs.getAddTime(), "yyyy-MM-dd HH:mm:ss"),
						localHdfs.getFileFormat(), localHdfs.getKind(), localHdfs.getUploaderKind());
			}
		}
		return localHdfsVO;
	}

}
