package com.biencloud.smarthome.service.push.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.biencloud.smarthome.service.base.BaseTest;
import com.biencloud.smarthome.service.base.BaseTransactionalTest;
import com.biencloud.smarthome.service.common.model.Paging;
import com.biencloud.smarthome.service.push.model.Push;
import com.biencloud.smarthome.service.rss.service.IRSSService;
import com.biencloud.smarthome.service.rss.vo.RssVO;

public class PushServiceTest extends BaseTransactionalTest {
	public IPushService getPushService() {
		return (IPushService) this.getBean("pushService");
	}

	public IRSSService getRSSService() {
		return (IRSSService) this.getBean("rssService");
	}

	// @Test
	public void insertPush() {
		Push push = new Push("测试11", "message", "dddd", new Date(), "x000000000001");
		boolean flag = getPushService().insertPush(push);
	}

	//@Test
	public void insertWeather() {
		String rssUrl = "http://www.nmc.gov.cn/publish/rss/forecast.html";
		Map<String, String> map = getRSSService().getRSSInfoMapForTest(rssUrl);
		String cityName = "广东省-深圳";
		String url = map.get(cityName);
		String info = getRSSService().queryWeatherInfoByCity(cityName, "shengzhen", url);
		Push push = new Push("天气预报", "weather", info, new Date(), "x01");
		boolean flag = getPushService().insertPush(push);
	}

	// @Test
	public void inserPushCollection() {
		List<Push> list = new ArrayList<Push>();
		for (int i = 0; i < 1; i++) {
			Push push1 = new Push("消息" + i, "message", "good good" + i, new Date(), "x01");
			Push push2 = new Push("广告" + i, "ad", "http://192.168.1.1:8080/smarthome/test" + i + ".jpg", new Date(), "x01",
					new Long(123456));
			Push push3 = new Push("门禁权限" + i, "gateData", "", new Date(), "x01");
			Push push4 = new Push("软件升级" + i, "software", "http://192.168.1.1:8080/smarthome/test" + i + ".zip", new Date(), "x01",
					new Long(9089776));
			Push push5 = new Push("设备密码" + i, "updateDevicePsw", "123456", new Date(), "x01", new Long(9089776));
			Push push6 = new Push("设备名称修改" + i, "updateDeviceName", "客厅Pad", new Date(), "x01", new Long(9089776));
			Push push7 = new Push("开锁" + i, "openDoorCommand", "", new Date(), "x01", null);
			Push push8 = new Push("布防" + i, "protectionCommand", "", new Date(), "x01", null);
			Push push9 = new Push("撤防" + i, "removalCommand", "", new Date(), "x01", null);
			Push push10 = new Push("关灯" + i, "closeLampCommand", "L001", new Date(), "x01", null);
			Push push11 = new Push("开灯" + i, "openLampCommand", "L002", new Date(), "x01", null);
			Push push12 = new Push("开窗帘" + i, "openCurtainCommand", "w002", new Date(), "x01", null);
			Push push13 = new Push("关窗帘" + i, "closeCurtainCommand", "w001", new Date(), "x01", null);
			Push push14 = new Push("收费通知" + i, "chargeNotice", "xxx小区4月收费通知：尊敬的xx先生,您4月物业收费共432元，请于5月1日到管理处交费，谢谢！", new Date(), "x01", null);

			list.add(push1);
			list.add(push2);
			list.add(push3);
			list.add(push4);
			list.add(push5);
			list.add(push6);
			list.add(push7);
			list.add(push8);
			list.add(push9);
			list.add(push10);
			list.add(push11);
			list.add(push12);
			list.add(push13);
			list.add(push14);
		}
		getPushService().inserPushCollection(list);

	}

	// @Test
	public void findById() {
		Push p = getPushService().findById(new Long(1));
		assert (p != null);
	}

	// @Test
	public void deleteById() {
		getPushService().deleteById(new Long(12));
	}

	// @Test
	public void deleteByEntity() {
		Push p = getPushService().findById(new Long(13));
		getPushService().deleteByEntity(p);
	}

	// @Test
	public void queryPushForPaging() {
		Paging p = getPushService().queryPushForPaging(1, 10, null, null);
		System.out.println(p.getTotalCount());
	}

	// @Test
	public void listPushByClientID() {
		String ids = "'x01','x07'";

		List<Push> list = getPushService().listPushByClientID(ids);
		assert (list != null);
	}

	// @Test
	public void listPush() {
		List<Push> list = getPushService().listPush(null);
		assert (list != null);
	}

	//@Test
	public void listPushByKeyValue() {
		String hql = " pushKind=?";
		String values = "weather";
		List<Push> list = getPushService().listPush(hql, values);
		if(list!=null&&!list.isEmpty())
		{
			System.out.println(list.size());
		}
	}

	//@Test
	public void listPushByKeyValue1() {
		String hql = " pushKind=? and id=?";
		Object values = "weather,1";
		List<Push> list = getPushService().listPush(hql, "weather",new Long(1));
		assert (list != null);
	}
	
	@Test
	public void listPushByClientIDForMap() {
	String ids = "A,B";
	String pushKind = "test";
		Map<String,List<Push>> map = getPushService().listPushByClientIDForMap(ids,pushKind);
		//assert (map != null);
		System.out.println("----:"+map);
	}
}
