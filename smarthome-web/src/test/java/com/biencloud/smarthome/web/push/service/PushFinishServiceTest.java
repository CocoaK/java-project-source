package com.biencloud.smarthome.web.push.service;

import java.util.List;

import org.junit.Test;

import com.biencloud.smarthome.web.base.BaseTest;
import com.biencloud.smarthome.web.common.vo.PagingVO;
import com.biencloud.smarthome.web.push.vo.PushFinishVO;
import com.biencloud.smarthome.web.push.vo.PushVO;
import com.biencloud.smarthome.web.push.service.IPushService;
public class PushFinishServiceTest extends BaseTest{
	public IPushFinishService getPushFinishService()
	{
		return (IPushFinishService)this.getBean("pushFinishService");
	}
	@Test
	public void queryPushFinishForPaging()
	{
		/*PagingVO<PushFinishVO> pv=getPushFinishService().queryPushFinishForPaging(1, 10, null,null,null);
		List<PushFinishVO> list=pv.getResults();
		if(list!=null&&!list.isEmpty())
		{
			for(PushFinishVO pv1:list)
			{
				if(pv1!=null)
				{
				//System.out.println(pv1.getPublishName());
				}
			}
		}*/
	}
}
