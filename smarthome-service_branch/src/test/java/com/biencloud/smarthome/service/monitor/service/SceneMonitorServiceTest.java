package com.biencloud.smarthome.service.monitor.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Test;

import com.biencloud.smarthome.service.base.BaseTransactionalTest;
import com.biencloud.smarthome.service.monitor.model.Scene;
import com.biencloud.smarthome.service.monitor.model.SceneDevice;
import com.biencloud.smarthome.service.monitor.service.ISceneMonitorService;

/**
 * 
 * 类名称：SceneMonitorServiceTest 类描述： 场景监控测试类
 * 
 * @author: kouy
 * @version: 0.1
 * @date: 2012-6-5 下午5:30:16
 */
public class SceneMonitorServiceTest extends BaseTransactionalTest {
	public ISceneMonitorService getSceneMonitorService() {
		return (ISceneMonitorService) this.getBean("sceneMonitorService");
	}

	// @Test
	public void sendCommand() {
		getSceneMonitorService().sendSceneMonitorCommand("x01");
	}

	// @Test
	public void saveScene() {
		List<SceneDevice> sceneDeviceList = new ArrayList<SceneDevice>();
		SceneDevice sceneDevice1 = new SceneDevice("灯1", "12,12", "0", "1", new Date(), "lamp", "客厅", "x01", "2101");
		SceneDevice sceneDevice2 = new SceneDevice("灯2", "12,22", "0", "2", new Date(), "lamp", "客厅", "x01", "2101");
		SceneDevice sceneDevice3 = new SceneDevice("窗帘1", "32,22", "1", "3", new Date(), "curtain", "客厅", "x01", "2101");
		SceneDevice sceneDevice4 = new SceneDevice("窗帘2", "22,22", "1", "4", new Date(), "curtain", "客厅", "x01", "2101");
		SceneDevice sceneDevice5 = new SceneDevice("灯3", "12,12", "0", "5", new Date(), "lamp", "卧室", "x01", "2101");
		SceneDevice sceneDevice6 = new SceneDevice("灯4", "112,22", "0", "6", new Date(), "lamp", "卧室", "x01", "2101");
		SceneDevice sceneDevice7 = new SceneDevice("窗帘5", "132,22", "1", "7", new Date(), "curtain", "卧室", "x01", "2101");
		SceneDevice sceneDevice8 = new SceneDevice("窗帘6", "122,22", "1", "8", new Date(), "curtain", "卧室", "x01", "2101");
		sceneDeviceList.add(sceneDevice1);
		sceneDeviceList.add(sceneDevice2);
		sceneDeviceList.add(sceneDevice3);
		sceneDeviceList.add(sceneDevice4);
		sceneDeviceList.add(sceneDevice5);
		sceneDeviceList.add(sceneDevice6);
		sceneDeviceList.add(sceneDevice5);
		sceneDeviceList.add(sceneDevice8);
		Scene scene1 = new Scene("1", "test1", "a.jpg", new Date(), "x01", "1", 0, "2101");
		Scene scene2 = new Scene("1", "test1", "a.jpg", new Date(), "x01", "1", 0, "2101");
		Scene scene3 = new Scene("1", "test1", "a.jpg", new Date(), "x01", "1", 0, "2101");
		List<Scene> sceneList = new ArrayList<Scene>();
		sceneList.add(scene1);
		sceneList.add(scene2);
		sceneList.add(scene3);
		getSceneMonitorService().saveSceneAndDevice(sceneList, sceneDeviceList);
	}

	@Test
	public void getMap() {
		Map<String, List<SceneDevice>> map = getSceneMonitorService().getSceneDeviceByDeviceNo("x01");
		if (map != null && !map.isEmpty()) {
			for (String s : map.keySet())
				System.out.println(s);
		}
	}

	// @Test
	public void getList() {
		List<SceneDevice> list = getSceneMonitorService().querySceneDeviceByDeviceNo("x01");
		if (list != null && !list.isEmpty()) {

			System.out.println(list.size());
		}
	}

	// @Test
	public void delete() {
		boolean flag = getSceneMonitorService().deleteSceneByDeviceNoAndMode("x01", "1");

	}

	@Test
	public void getUsedScene() {
//		Scene scene = getSceneMonitorService().getIsUsedSceneByDeviceNo("x01");
//		System.out.println(scene.getSceneName());

	}

	// @Test
	public void pushCommand() {
		this.sendCommand();
		Scene scene = new Scene("1", "test", "a.jpg", new Date(), "x01", "1", 1, "2101");
		scene = getSceneMonitorService().saveSceneForBackOjb(scene);
		List<SceneDevice> list = new ArrayList<SceneDevice>();
		SceneDevice sd1 = new SceneDevice();

		sd1.setDeviceId("1");
		sd1.setStatus("0");
		SceneDevice sd2 = new SceneDevice();

		sd2.setDeviceId("2");
		sd2.setStatus("0");
		SceneDevice sd3 = new SceneDevice();

		sd3.setDeviceId("3");
		sd3.setStatus("1");
		list.add(sd1);
		list.add(sd2);
		list.add(sd3);
		boolean flag = getSceneMonitorService().sendSceneMonitorCommand("x01", list, scene.getId());

	}

	// 布防撤防
	@Test
	public void sendPRemovalMonitorCommand() {
		Scene scene = new Scene("11", "test1", "a1.jpg", new Date(), "x01", "1", 1, "2101");
		scene = getSceneMonitorService().saveSceneForBackOjb(scene);
//		getSceneMonitorService().sendProtectionAndRemovalMonitorCommand("x01", scene.getId(), "0");
	}
	
	@Test
	public void querySceneDevice(){
		List<SceneDevice> list = null;
		SceneDevice sd = new SceneDevice();
		sd.setRoomNo("2");
		sd.setDeviceNo(null);
		sd.setKind("11");
		list = getSceneMonitorService().querySceneDevices(sd);
		System.out.println("------------:"+list);
	}
}
