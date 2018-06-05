package com.biencloud.smarthome.web.log.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.biencloud.smarthome.web.base.service.BaseService;
import com.biencloud.smarthome.web.common.vo.PagingVO;
import com.biencloud.smarthome.web.log.service.IFileUploadLogService;
import com.biencloud.smarthome.web.log.vo.FileUploadLogVO;
import com.biencloud.smarthome.web.wsclient.stub.FileUploadLog;
import com.biencloud.smarthome.web.wsclient.stub.Paging;

/**
 * 文件上传日志管理领域服务接口。
 * @author dehua ye
 * @since 1.0 2012-5-11
 * @see IService
 * @throws RuntimeException 如果操作执行失败
 */
public class FileUploadLogServiceImpl extends BaseService<FileUploadLogVO>  implements IFileUploadLogService {
	

	public static final String[] TIMES = {"addTime","addEndTime","addStartTime"};
	@Override
	public PagingVO<FileUploadLogVO> queryFileUploadLogForPaging(FileUploadLogVO paramsOb, int pageNum, int pageSize) {
		try {
			FileUploadLog ob=new FileUploadLog();
			if(paramsOb!=null){
				this.copyProperties(paramsOb, ob,null,false,TIMES);
			}
			Paging paging=getSmartHomeService().queryFileUploadLogForPaging(ob, pageNum, pageSize);
			PagingVO<FileUploadLogVO> pagingVO = convertToVO(paging,null,TIMES);
			return pagingVO;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	@Override
	public FileUploadLogVO getFileUploadLog(String entityId) {
		try {
			FileUploadLogVO vo=new FileUploadLogVO();
			copyProperties(getSmartHomeService().getFileUploadLog(entityId), vo,null,true,TIMES);
			return vo;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	@Override
	public List<FileUploadLogVO> queryFileUploadLogForList(FileUploadLogVO paramsOb) {
		FileUploadLog ob=new FileUploadLog();
		if(paramsOb!=null){
			copyProperties(paramsOb, ob,null,false,TIMES);
		}
		List<FileUploadLog> result=getSmartHomeService().queryFileUploadLogForList(ob);
		List<FileUploadLogVO> target=new ArrayList<FileUploadLogVO>();
		for (int i = 0; i < result.size(); i++) {
			FileUploadLogVO vo=new FileUploadLogVO();
			FileUploadLog cob=result.get(i);
			copyProperties(cob, vo);
			target.add(vo);
		}
		return target;
	}

}
