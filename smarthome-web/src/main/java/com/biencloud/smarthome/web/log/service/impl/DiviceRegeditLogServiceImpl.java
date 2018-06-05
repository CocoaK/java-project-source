package com.biencloud.smarthome.web.log.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.biencloud.smarthome.web.base.service.BaseService;
import com.biencloud.smarthome.web.common.vo.PagingVO;
import com.biencloud.smarthome.web.log.service.IDiviceRegeditLogService;
import com.biencloud.smarthome.web.log.vo.DiviceRegeditLogVO;
import com.biencloud.smarthome.web.wsclient.stub.DiviceRegeditLog;
import com.biencloud.smarthome.web.wsclient.stub.Paging;

/**
 * 设备注册日志管理领域服务接口。
 * @author dehua ye
 * @since 1.0 2012-5-11
 * @see IService
 * @throws RuntimeException 如果操作执行失败
 */
public class DiviceRegeditLogServiceImpl extends BaseService<DiviceRegeditLogVO>  implements IDiviceRegeditLogService {
	

	public static final String[] TIMES = {"addTime","addEndTime","addStartTime"};
	@Override
	public PagingVO<DiviceRegeditLogVO> queryDiviceRegeditLogForPaging(DiviceRegeditLogVO paramsOb, int pageNum, int pageSize) {
		try {
			DiviceRegeditLog ob=new DiviceRegeditLog();
			if(paramsOb!=null){
				this.copyProperties(paramsOb, ob,null,false,TIMES);
			}
			Paging paging=getSmartHomeService().queryDiviceRegeditLogForPaging(ob, pageNum, pageSize);
			PagingVO<DiviceRegeditLogVO> pagingVO = convertToVO(paging,null,TIMES);
			return pagingVO;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	@Override
	public DiviceRegeditLogVO getDiviceRegeditLog(String entityId) {
		try {
			DiviceRegeditLogVO vo=new DiviceRegeditLogVO();
			copyProperties(getSmartHomeService().getDiviceRegeditLog(entityId), vo,null,true,TIMES);
			return vo;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	@Override
	public List<DiviceRegeditLogVO> queryDiviceRegeditLogForList(DiviceRegeditLogVO paramsOb) {
		DiviceRegeditLog ob=new DiviceRegeditLog();
		if(paramsOb!=null){
			copyProperties(paramsOb, ob,null,false,TIMES);
		}
		List<DiviceRegeditLog> result=getSmartHomeService().queryDiviceRegeditLogForList(ob);
		List<DiviceRegeditLogVO> target=new ArrayList<DiviceRegeditLogVO>();
		for (int i = 0; i < result.size(); i++) {
			DiviceRegeditLogVO vo=new DiviceRegeditLogVO();
			DiviceRegeditLog cob=result.get(i);
			copyProperties(cob, vo);
			target.add(vo);
		}
		return target;
	}

}
