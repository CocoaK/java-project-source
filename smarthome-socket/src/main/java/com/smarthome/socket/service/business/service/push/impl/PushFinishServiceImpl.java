package com.smarthome.socket.service.business.service.push.impl;

import com.smarthome.socket.service.business.service.BaseService;
import com.smarthome.socket.service.business.service.push.IPushFinishService;



/**
 * 
 * 类名称：PushFinishServiceImpl 类描述：推送完成接口实现类
 * 
 * @author: kouy
 * @version: 0.1
 * @date: 2012-5-9 下午4:20:25
 */
public class PushFinishServiceImpl extends BaseService implements IPushFinishService {
	

	@Override
	public boolean insertPushFinish(Long pushId) {		
		boolean flag = false;
		
		if (pushId != null) {
			/*PushFinish pf = new PushFinish();
            pf.setAddTime(DateTimeUtil.convertDateToXMLGregorianCalendar(pushFinishVO.getAddTime()));
            pf.setPushClientId(pushFinishVO.getPushClientId());
            pf.setPushContent(pushFinishVO.getPushContent());
            pf.setPushFinishTime(DateTimeUtil.convertDateToXMLGregorianCalendar(pushFinishVO.getPushFinishTime()));
            pf.setPushKind(pushFinishVO.getPushKind());
            pf.setPushName(pushFinishVO.getPushName());
            pf.setPushVersion(pushFinishVO.getPushVersion());
            pf.setSize(pushFinishVO.getSize());
            pf.setPushDescription(pushFinishVO.getPushDescription());
			*/
			flag = super.getSmartHomeTcpService().insertPushFinish(pushId);

		}
	
	return flag;
	}

	

	

}
