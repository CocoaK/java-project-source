package com.biencloud.smarthome.web.monitor.service.impl;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.biencloud.smarthome.web.appdata.constant.AppDataConstant;
import com.biencloud.smarthome.web.appdata.json.Json;
import com.biencloud.smarthome.web.base.service.BaseService;
import com.biencloud.smarthome.web.common.util.JsonUtil;
import com.biencloud.smarthome.web.monitor.service.ISceneMonitorService;
import com.biencloud.smarthome.web.monitor.vo.SceneDeviceVo;
import com.biencloud.smarthome.web.monitor.vo.SceneVo;
import com.biencloud.smarthome.web.wsclient.stub.Device;
import com.biencloud.smarthome.web.wsclient.stub.Scene;
import com.biencloud.smarthome.web.wsclient.stub.SceneDevice;

/**
 * 场景监控Service实现类
 * 
 * @author jsun
 * @since 1.0 2012-6-7
 */
public class SceneMonitorServiceImpl extends BaseService<SceneDeviceVo> implements ISceneMonitorService {
	private static final String ADD_TIME = "addTime";

	@Override
	public boolean sendSceneMonitorCommand(String deviceNo) {
		return getSmartHomeService().sendSceneMonitorCommand(deviceNo);
	}

	@Override
	public boolean sendSceneMonitorCommand(String deviceNo, List<SceneDeviceVo> list, Long sceneId) {
		List<SceneDevice> sceneDeviceList = new ArrayList<SceneDevice>();
		if (list != null) {
			for (SceneDeviceVo sceneDeviceVo : list) {
				if(sceneDeviceVo!=null){
					SceneDevice sceneDevice = new SceneDevice();
					sceneDevice.setDeviceId(sceneDeviceVo.getDeviceId());
					sceneDevice.setDeviceNo(deviceNo);
					sceneDevice.setStatus(sceneDeviceVo.getStatus());
					sceneDeviceList.add(sceneDevice);
				}
			}
		}
		return getSmartHomeService().sendSceneDeviceMonitorCommand(deviceNo, sceneDeviceList, sceneId);
	}

	@Override
	public List<SceneDeviceVo> querySceneDeviceByDeviceNo(String deviceNo) {
		List<SceneDevice> sceneDeviceList = getSmartHomeService().querySceneDeviceByDeviceNo(deviceNo);

		List<SceneDeviceVo> sceneDeviceVoList = new ArrayList<SceneDeviceVo>();
		if (sceneDeviceList != null) {
			for (SceneDevice sceneDevice : sceneDeviceList) {
				sceneDeviceVoList.add(model2Vo(sceneDevice));
			}
		}
		return sceneDeviceVoList;
	}

	@Override
	public List<SceneVo> getSceneByDeviceNo(String deviceNo) {
		List<Scene> sceneList = getSmartHomeService().getSceneByDeviceNo(deviceNo);

		List<SceneVo> sceneVoList = new ArrayList<SceneVo>();
		if (sceneList != null) {
			for (Scene scene : sceneList) {
				sceneVoList.add(model2Vo(scene));
			}
		}
		return sceneVoList;
	}

	@Override
	public boolean sendProtectionAndRemovalMonitorCommand(String deviceNo, Long sceneId, String actionWay,String sceneType) {
		return getSmartHomeService().sendProtectionAndRemovalMonitorCommand(deviceNo, sceneId, actionWay,sceneType);
	}

	private SceneVo model2Vo(Scene model) {
		SceneVo vo = new SceneVo();
		copyProperties(model, vo, ADD_TIME);
		return vo;
	}

	private SceneDeviceVo model2Vo(SceneDevice model) {
		SceneDeviceVo vo = new SceneDeviceVo();
		copyProperties(model, vo, ADD_TIME);
		return vo;
	}
	
	private SceneDevice vo2Model(SceneDeviceVo vo) {
		SceneDevice model = new SceneDevice();
		copyProperties(vo, model, ADD_TIME);
		return model;
	}

	@Override
	public Json saveOrUpdateSceneData(String jsonString) {
		Json json = new Json();
		try {
			if (jsonString != null && jsonString.startsWith("{") && jsonString.endsWith("}")) {

				JSONObject jb = JsonUtil.jsonStringToJsonObject(jsonString);
				String deviceNo = JsonUtil.getDataFromJsonObject(jb, "deviceNo");
				json.setDeviceNo(deviceNo);
				String roomNo = null;
				Device device = super.getSmartHomeService().queryDeviceByCode(deviceNo);
				if (device != null) {
					roomNo = device.getCellHouseholdInfo() != null ? device.getCellHouseholdInfo().getCode() : null;
				}
				JSONArray jaScene = jb.getJSONArray("sceneData");
				List<Scene> sceneList = covertSceneList(deviceNo, roomNo, jaScene);
				boolean success = super.getSmartHomeService().saveOrUpdateScene(sceneList);
				if (success) {
					json.setCode(AppDataConstant.SUCCESS);
				} else {
					json.setCode(AppDataConstant.FAILTRUE);
				}
			}
			json.setCode(AppDataConstant.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			json.setCode(AppDataConstant.FAILTRUE);
		}

		return json;
	}

	/**
	 * 
	 * 方法的描述:封装成list集合
	 * 
	 * @author: kouy
	 * @version: 0.1
	 * @date: 2012-7-4 下午5:27:24
	 * @param deviceNo
	 * @param roomNo
	 * @param jaScene
	 * @return
	 */
	private List<Scene> covertSceneList(String deviceNo, String roomNo, JSONArray jaScene) {
		List<Scene> sceneList = new ArrayList<Scene>();

		if (jaScene != null && jaScene.size() > 0) {
			for (int i = 0; i < jaScene.size(); i++) {
				Object o = jaScene.get(i);
				if (o != null) {
					Scene scene = createScene(deviceNo, roomNo, o);
					sceneList.add(scene);
				}
			}
		}
		return sceneList;
	}

	/**
	 * 
	 * 方法的描述: 封装成list集合
	 * 
	 * @author: kouy
	 * @version: 0.1
	 * @date: 2012-7-4 下午5:41:46
	 * @param deviceNo
	 * @param ja
	 * @return
	 */
	private List<SceneDevice> covertRoomDeviceList(String deviceNo, JSONArray ja) {
		List<SceneDevice> sceneDeviceList = new ArrayList<SceneDevice>();

		if (ja != null && ja.size() > 0) {
			for (int i = 0; i < ja.size(); i++) {
				Object o = ja.get(i);
				if (o != null) {
					SceneDevice sceneDevice = createSceneDevice(deviceNo, o);
					sceneDeviceList.add(sceneDevice);
				}
			}
		}
		return sceneDeviceList;
	}

	/**
	 * 
	 * 方法的描述: 创建场景对象
	 * 
	 * @author: kouy
	 * @version: 0.1
	 * @date: 2012-7-4 下午5:25:20
	 * @param deviceNo
	 * @param roomNo
	 * @param o
	 * @return
	 */
	private Scene createScene(String deviceNo, String roomNo, Object o) {
		Scene scene = new Scene();
		scene.setDeviceNo(deviceNo);
		scene.setRoomNo(roomNo);
		if(o != null){
			String sceneId = JsonUtil.getDataFromJsonObject(JsonUtil.jsonStringToJsonObject(o.toString()), "id");
			scene.setSceneId(sceneId);
			String mode = JsonUtil.getDataFromJsonObject(JsonUtil.jsonStringToJsonObject(o.toString()), "modelType");
			scene.setMode(mode);
			String sceneName = JsonUtil.getDataFromJsonObject(JsonUtil.jsonStringToJsonObject(o.toString()), "modelName");
			scene.setSceneName(sceneName);
			String isUse = JsonUtil.getDataFromJsonObject(JsonUtil.jsonStringToJsonObject(o.toString()), "modelStatus");
			scene.setIsUse(isUse == null ? 0 : Integer.parseInt(isUse));
			String type = JsonUtil.getDataFromJsonObject(JsonUtil.jsonStringToJsonObject(o.toString()), "type");
			scene.setSceneKind(type);
		}
		return scene;
	}

	/**
	 * 
	 * 方法的描述: 构建场景设备对象
	 * 
	 * @author: kouy
	 * @version: 0.1
	 * @date: 2012-7-4 下午5:40:50
	 * @param deviceNo
	 * @param o
	 * @return
	 */
	private SceneDevice createSceneDevice(String deviceNo, Object o) {
		SceneDevice sd = new SceneDevice();
		sd.setDeviceNo(deviceNo);
		if(o != null){
			String roomNo = JsonUtil.getDataFromJsonObject(JsonUtil.jsonStringToJsonObject(o.toString()), "roomId");
			sd.setRoomNo(roomNo);
			String deviceId = JsonUtil.getDataFromJsonObject(JsonUtil.jsonStringToJsonObject(o.toString()), "deviceId");
			sd.setDeviceId(deviceId);
			String kind = JsonUtil.getDataFromJsonObject(JsonUtil.jsonStringToJsonObject(o.toString()), "deviceType");
			sd.setKind(kind);
			String deviceName = JsonUtil.getDataFromJsonObject(JsonUtil.jsonStringToJsonObject(o.toString()), "deviceName");
			sd.setDeviceName(deviceName);
			String status = JsonUtil.getDataFromJsonObject(JsonUtil.jsonStringToJsonObject(o.toString()), "deviceStatus");
			sd.setStatus(status);
			String scendId = JsonUtil.getDataFromJsonObject(JsonUtil.jsonStringToJsonObject(o.toString()), "sceneId");
			sd.setSceneId(new Long(scendId));
		}
		return sd;
	}

	@Override
	public Json saveOrUpdateRoomDeviceData(String jsonString) {
		Json json = new Json();
		try {
			if (jsonString != null && jsonString.startsWith("{")) {

				JSONObject jb = JsonUtil.jsonStringToJsonObject(jsonString);
				String deviceNo = null;
				if(jb != null){
					deviceNo = JsonUtil.getDataFromJsonObject(jb, "deviceNo");
				}
				json.setDeviceNo(deviceNo);
				JSONArray jaScene = jb.getJSONArray("roomData");
				List<SceneDevice> sceneDeviceList = covertRoomDeviceList(deviceNo, jaScene);
				boolean success = super.getSmartHomeService().saveOrUpdateSceneDevice(sceneDeviceList);
				if (success) {
					json.setCode(AppDataConstant.SUCCESS);
				} else {
					json.setCode(AppDataConstant.FAILTRUE);
				}
			} else {
				json.setCode(AppDataConstant.FAILTRUE);
			}
		} catch (Exception e) {
			e.printStackTrace();
			json.setCode(AppDataConstant.FAILTRUE);
		}

		return json;
	}

	@Override
	public Json deleteSceneData(String jsonString) {
		Json json = new Json();
		try {
			if (jsonString != null && jsonString.startsWith("{") && jsonString.endsWith("}")) {
				String deviceNo = null;
				JSONObject jb = JsonUtil.jsonStringToJsonObject(jsonString);
				if(jb != null){
					deviceNo = JsonUtil.getDataFromJsonObject(jb, "deviceNo");
				}
				json.setDeviceNo(deviceNo);
				String sceneId = JsonUtil.getDataFromJsonObject(jb, "sceneId");
				boolean flag = super.getSmartHomeService().deleteSceneByDeviceNoAndSceneId(deviceNo, sceneId);
				if (flag) {
					json.setCode(AppDataConstant.SUCCESS);
				}

			}
		} catch (Exception e) {
			json.setCode(AppDataConstant.FAILTRUE);
		}
		return json;
	}

	@Override
	public Json deleteSceneDeviceData(String jsonString) {
		Json json = new Json();
		try {
			if (jsonString != null && jsonString.startsWith("{") && jsonString.endsWith("}")) {
				String deviceNo = null;
				JSONObject jb = JsonUtil.jsonStringToJsonObject(jsonString);
				if(jb != null){
					deviceNo = JsonUtil.getDataFromJsonObject(jb, "deviceNo");
				}
				json.setDeviceNo(deviceNo);
				String deviceId = JsonUtil.getDataFromJsonObject(jb, "deviceId");
				boolean flag = super.getSmartHomeService().deleteSceneDeviceByDeviceNoAndDeviceId(deviceNo, deviceId);
				if (flag) {
					json.setCode(AppDataConstant.SUCCESS);
				}

			}
		} catch (Exception e) {
			json.setCode(AppDataConstant.FAILTRUE);
		}
		return json;
	}

	@Override
	public List<SceneVo> getScenes(String deviceNo, String sceneKind) {
		List<Scene> sceneList = getSmartHomeService().getScenes(deviceNo, sceneKind);
		List<SceneVo> sceneVoList = new ArrayList<SceneVo>();
		if (sceneList != null) {
			for (Scene scene : sceneList) {
				sceneVoList.add(model2Vo(scene));
			}
		}
		return sceneVoList;
	}
	
	/**
	 * 方法的描述: 
	 * @author: ykou  
	 * @version: 0.1
	 * @date: 2012-11-5 下午7:33:34
	 * @param deviceNo
	 * @param sceneId
	 * @return
	 */
	public List<SceneDeviceVo> querySceneDeviceByDeviceNo(String deviceNo,Long sceneId) {
		List<SceneDevice> sceneDeviceList = getSmartHomeService().querySceneDeviceByDeviceNoAndSceneId(deviceNo,sceneId);
		List<SceneDeviceVo> sceneDeviceVoList = new ArrayList<SceneDeviceVo>();
		if (sceneDeviceList != null) {
			for (SceneDevice sceneDevice : sceneDeviceList) {
				sceneDeviceVoList.add(model2Vo(sceneDevice));
			}
		}
		return sceneDeviceVoList;
	}

	@Override
	public SceneVo getIsUsedSceneByDeviceNo(String deviceNo, String sceneKind) {
		Scene scene = getSmartHomeService().getIsUsedSceneByDeviceNo(deviceNo, sceneKind);
		return model2Vo(scene);
	}

	@Override
	public boolean sendGetSceneCommand(String deviceNo, String sceneKind) {
		return getSmartHomeService().sendGetSceneCommand(deviceNo, sceneKind);
	}

	@Override
	public List<SceneDeviceVo> querySceneDevice(SceneDeviceVo sceneDeviceVo) {
		SceneDevice sd = vo2Model(sceneDeviceVo);
		List<SceneDevice> sceneDeviceList = getSmartHomeService().querySceneDevices(sd);
		List<SceneDeviceVo> sceneDeviceVoList = new ArrayList<SceneDeviceVo>();
		if (sceneDeviceList != null) {
			for (SceneDevice sceneDevice : sceneDeviceList) {
				sceneDeviceVoList.add(model2Vo(sceneDevice));
			}
		}
		return sceneDeviceVoList;
	}
}
