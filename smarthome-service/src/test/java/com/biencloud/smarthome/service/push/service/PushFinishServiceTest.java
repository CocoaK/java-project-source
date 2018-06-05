package com.biencloud.smarthome.service.push.service;

import java.util.Date;

import org.junit.Test;

import com.biencloud.smarthome.service.base.BaseTransactionalTest;
import com.biencloud.smarthome.service.common.model.Paging;
import com.biencloud.smarthome.service.push.model.PushFinish;

public class PushFinishServiceTest extends BaseTransactionalTest{
	    public IPushFinishService getPushFinishService()
	    {
	    	return (IPushFinishService) this.getBean("pushFinishService");
	    }
	    @Test
		public void insertPushFinish()
		{
	    	PushFinish pf=new PushFinish("大师傅上", "第三十三", "message", new Date(), new Date(),
	    			"d0000000000000001");
	    	getPushFinishService().insertPushFinish(pf);
		}
		
		//@Test
		public void findById()
		{
			PushFinish pf=getPushFinishService().findById(new Long(1));
			assert(pf!=null);
		}
		//@Test
		public void deleteById()
		{
			getPushFinishService().deleteById(new Long(1));
		}
		//@Test
		public void deleteByEntity()
		{
			PushFinish pf=getPushFinishService().findById(new Long(2));
			getPushFinishService().deleteByEntity(pf);
		}
		//@Test
		public void queryPushFinishForPaging()
		{
			Paging p=getPushFinishService().queryPushFinishForPaging(1, 10, null,null);
			System.out.println(p.getTotalCount());
			
		}
}
