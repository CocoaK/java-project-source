package com.biencloud.smarthome.web.push.service;

import java.util.List;

import org.junit.Test;

import com.biencloud.smarthome.web.base.BaseTest;
import com.biencloud.smarthome.web.common.vo.PagingVO;
import com.biencloud.smarthome.web.push.vo.PushVO;
import com.biencloud.smarthome.web.push.service.IPushService;
public class PushServiceTest extends BaseTest{
	public IPushService getPushService()
	{
		return (IPushService)this.getBean("pushService");
	}
	@Test
	public void queryPushForPaging()
	{
		/*PagingVO<PushVO> pv=getPushService().queryPushForPaging(1, 10, null,null,null);
		List<PushVO> list=pv.getResults();
		if(list!=null&&!list.isEmpty())
		{
			for(PushVO pv1:list)
			{
				if(pv1!=null)
				{
					System.out.println(pv1.getPushName()+","+pv1.getPushClientId());
				}
			}
		}*/
		
	}
	
	@Test
	public void query(){
		PushVO pushVO = new PushVO();
		pushVO.setPushKind("weather");
//		PagingVO<PushVO> paging= getPushService().query(1, 5, pushVO);
//		logger.info("查询业最近推送: {}",paging);
	}
}
