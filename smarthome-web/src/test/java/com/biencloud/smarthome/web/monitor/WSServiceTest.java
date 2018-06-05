package com.biencloud.smarthome.web.monitor;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.biencloud.smarthome.web.base.BaseTest;
import com.biencloud.smarthome.web.wsclient.stub.MapConvertor;
import com.biencloud.smarthome.web.wsclient.stub.MapEntry;
import com.biencloud.smarthome.web.wsclient.stub.SceneDevice;
import com.biencloud.smarthome.web.wsclient.stub.SmartHomePubService;
import com.biencloud.smarthome.web.wsclient.stub.SmartHomePubServiceService;

public class WSServiceTest extends BaseTest {
	public SmartHomePubService getSmartHomeService() {
		return new SmartHomePubServiceService().getSmartHomePubServicePort();
	}

	@Test
	public void getMap() {
		MapConvertor mapc = getSmartHomeService().getSceneDeviceByDeviceNo("x01");
		List<MapEntry> entries = mapc.getEntries();
		if (entries != null && !entries.isEmpty()) {
			for (MapEntry me : entries) {

				System.out.println(me.getKey());
			}

		}
	}
}
