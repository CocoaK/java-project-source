package com.smarthome.socket.service.netty.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelFuture;
import org.jboss.netty.channel.ChannelFutureListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import net.sf.json.JSONObject;
import com.smarthome.socket.common.constant.PubConstant;
import com.smarthome.socket.common.model.ResultEntity;
import com.smarthome.socket.common.util.CacheUtil;
import com.smarthome.socket.common.util.JsonUtil;
import com.smarthome.socket.common.util.SessionUtil;
import com.smarthome.socket.common.util.StringUtil;
import com.smarthome.socket.service.business.adapater.RegistReq;
import com.smarthome.socket.service.business.adapater.RegistRsp;
import com.smarthome.socket.service.business.service.IAlarmService;
import com.smarthome.socket.service.business.service.IAuthSessionService;
import com.smarthome.socket.service.business.service.IDeviceAccessoriesService;
import com.smarthome.socket.service.business.service.IDevicesService;
import com.smarthome.socket.service.business.service.IPushClientService;
import com.smarthome.socket.service.business.service.clientlog.IClientLogService;
import com.smarthome.socket.service.business.service.device.IDeviceService;
import com.smarthome.socket.service.business.service.monitor.ISceneMonitorService;
import com.smarthome.socket.service.business.service.push.IPushFinishService;
import com.smarthome.socket.service.business.service.push.IPushService;
import com.smarthome.socket.service.netty.service.NettyService;
import com.smarthome.socket.service.vo.AuthSession;
import com.smarthome.socket.service.vo.LoginVO;
import com.smarthome.socket.service.vo.PushVO;
import com.smarthome.socket.service.vo.TransVO;
import com.smarthome.socket.wsservice.stub.ClientLog;

/**
 * 
 * 类名称：NettyServiceImpl 类描述：netty业务接口实现类
 * 
 * @author: kouy
 * @version: 0.1
 * @date: 2012-5-9 下午7:07:07
 */
public class NettyServiceImpl implements NettyService {

	private IDeviceService deviceService;
	private IPushService pushService;
	private IPushFinishService pushFinishService;
	private ISceneMonitorService sceneMonitorService;
	private IClientLogService clientLogService;
	Logger log = LoggerFactory.getLogger(NettyServiceImpl.class.getName());
	private Map<String, Date> mac_time_heart_map = CacheUtil.getInstance()
			.getMac_time_heart_map();
	private Map<Integer, String> channelId_mac_map = CacheUtil.getInstance()
			.getChannelId_mac_map();
	private Map<String, String> mac_deviceNoMac_map = CacheUtil.getInstance()
			.getMac_deviceNoMac_map();
	private Map<String, Channel> deviceNoMac_channel_map = CacheUtil
			.getInstance().getDeviceNoMac_channel_map();
	private Map<String, Long> deviceNoMac_sentDataClient_map = CacheUtil
			.getInstance().getDeviceNoMac_sentDataClient_map();
//	public ResultEntity<String> re = new ResultEntity<String>();
	
	private IAlarmService alarmService;
	
	private IDeviceAccessoriesService deviceAccessoriesService;
	
	private IPushClientService pushClientService;
	
	private IAuthSessionService authSessionService;
	
	private IDevicesService devicesService;
	//默认超时时间
	private int defaultTimeout = 3000;
	
	@Override
	public List<PushVO> listPushData() {
		return pushService.listPush(null);
	}

	@Override
	public List<PushVO> listPushDataByClientID(String ids) {

		return pushService.listPushByClientID(ids);
	}

	@Override
	public Map<String, List<PushVO>> listPushDataByClientIDForMap(String ids,
			String pushKinds) throws Exception {
		// TODO Auto-generated method stub
		return pushService.listPushByClientIDForMap(ids, pushKinds);
	}

	@Override
	public boolean acceptData(JSONObject jb, String jsonType, String mac,
			String pushId) {
		boolean delSuccess = false;

		return delSuccess;
	}

	@Override
	public boolean savePushFinishData(Long id) {
		boolean delSuccess = false;
		/*
		 * PushVO pv = pushService.findById(id); if (pv != null) { PushFinishVO
		 * pfv = new PushFinishVO(pv.getPushName(), pv.getPushContent(),
		 * pv.getPushKind(), pv.getAddTime(), new Date(), pv.getPushClientId(),
		 * pv.getPushDescription()); boolean flag =
		 * pushFinishService.insertPushFinish(pfv); if (flag) { delSuccess =
		 * pushService.deleteById(id); } }
		 */
		if (id != null)
			delSuccess = pushFinishService.insertPushFinish(id);
		return delSuccess;
	}

	@Override
	public LoginVO login(JSONObject jb, String jsonType, String mac) {
		LoginVO lv = new LoginVO();
		RegistRsp rr = new RegistRsp();
		try {

			rr.setJsonType(jsonType);
			// 小区号
			String areaNo = JsonUtil.getDataFromJsonObject(jb, "area");
			// 区域号
			String regionNo = JsonUtil.getDataFromJsonObject(jb, "region");
			// 栋号
			String buildingNo = JsonUtil.getDataFromJsonObject(jb, "building");
			// 单元号
			String unitNo = JsonUtil.getDataFromJsonObject(jb, "unit");
			// 房号
			String houseNo = JsonUtil.getDataFromJsonObject(jb, "room");

			String deviceNo = JsonUtil.getDataFromJsonObject(jb, "deviceNo");
			// 子类型
			String childDeviceType = JsonUtil.getDataFromJsonObject(jb,
					"childDeviceType");
			// 类型
			String deviceType = JsonUtil
					.getDataFromJsonObject(jb, "deviceType");
			// 如果是门口机则登录时房号为空
			if (deviceType != null && "02".equals(deviceType.trim())) {
				houseNo = null;
			}
			// 如果是围墙机则登录时区域，楼栋，单元，房号都为空
			if(deviceType != null && "06".equals(deviceType.trim())){
				regionNo = null;
				buildingNo = null;
				unitNo = null;
				houseNo = null;
			}

			String deviceName = JsonUtil.getDataFromJsonObject(jb, "deviceName");
			String devicePassword = JsonUtil.getDataFromJsonObject(jb,"password");
			String deviceMac = JsonUtil.getDataFromJsonObject(jb, "mac");
			String deviceIp = JsonUtil.getDataFromJsonObject(jb, "ip");
			String ipState = JsonUtil.getDataFromJsonObject(jb, "ipState");
			String roomState = JsonUtil.getDataFromJsonObject(jb, "roomState");
			String position = JsonUtil.getDataFromJsonObject(jb, "position");
			String version = JsonUtil.getDataFromJsonObject(jb, "version");
			String sipid = JsonUtil.getDataFromJsonObject(jb, "sipid");
			
			if (areaNo == null || deviceMac == null || deviceType == null) {
				rr.setCode(PubConstant.CLIENT_REGEIST_FAILURE);
			} else {
				RegistReq req = new RegistReq();
				req.setAreaNo(areaNo);
				req.setEstateNo(regionNo);
				req.setRidgepole(buildingNo);
				req.setUnitNo(unitNo);
				req.setHouseNo(houseNo);
				req.setDeviceNo(deviceNo);
				req.setDeviceType(deviceType);
				req.setDeviceIp(deviceIp);
				req.setDeviceMac(deviceMac);
				req.setDevicePassword(devicePassword);
				req.setIpState(ipState);
				req.setDeviceName(deviceName);
				req.setHouseState(roomState);
				req.setPosition(position);
				req.setVersion(version);
				req.setSipid(sipid);
				doLogin(lv, rr, deviceNo, req);

			}

		} catch (Exception ee) {
			log.info("login exception:" + ee.getMessage());
			rr.setCode(PubConstant.CLIENT_REGEIST_FAILURE);
		}
		lv.setRegistRsp(rr);
		return lv;
	}

	/**
	 * 
	 * 方法的描述: 正常登陆
	 * 
	 * @author: kouy
	 * @version: 0.1
	 * @date: 2012-5-11 上午11:24:40
	 * @param lv
	 * @param rr
	 * @param deviceNo
	 * @param req
	 * @throws Exception
	 */
	private void doLogin(LoginVO lv, RegistRsp rr, String deviceNo,
			RegistReq req) throws Exception {
		String[] obj = deviceService.login(req);
		if (PubConstant.CLIENT_REGEIST_SUCCESS.equals(obj[1])) {
			rr.setCode(PubConstant.CLIENT_REGEIST_SUCCESS);
			// lv.setDeviceNo(obj[0]);
			rr.setFileServerIP(obj[3]);
			rr.setDataServerIP(obj[2]);
			rr.setSocketServerIP(obj[4]);
			lv.setConflictMac(obj[5]);

		} else {
			rr.setCode(obj[1]);
		}
		rr.setDeviceNo(obj[0]);
		this.printLog(obj[1], req.getDeviceMac(), req.getDeviceIp(), obj[0],
				req.getDeviceName(), req.getAreaNo());
	}

	private void printLog(String str, String mac, String ip, String deviceNo,
			String deviceName, String areaNo) {
		String pintInfo = "client[mac:" + mac + ",ip:" + ip + ",deviceNo:"
				+ (deviceNo == null ? "" : deviceNo) + "] login result:"+str;
		if (str != null && !"".equals(str)) {
			String status = "";
			int info = Integer.parseInt(str);
			switch (info) {
			case 2:
				status = "area not exist!";
				break;
			case 3:
				status = "area region not exist!";
				break;
			case 4:
				status = "area building not exist!";
				break;
			case 5:
				status = "area unit not exist!";
				break;
			case 6:
				status = "area house not exist!";
				break;
			case 7:
				status = "area device type not exist!";
				break;
			case 8:
				status = "device name repeat!";
				break;
			case 9:
				status = "device ip repeat!";
				break;
			case 10:
				status = "device position repeat!";
				break;
			case 11:
				status = "device position not exist!";
				break;
			case 12:
				status = "device house num not exist!";
				break;

			case 13:
				status = "device ip not exist!";
				break;
			case 14:
				status = "house  exist device!";
				break;
			case 15:
				status = "house and ip  confilct!";
				break;
			default:
				break;

			}
			log.info(pintInfo + status);
//			saveClientLog(deviceNo, mac, "login_deal", deviceName, pintInfo
//					+ status, areaNo, ip, PubConstant.DATA_FROM_SERVER);

		}

	}

	@Override
	public void loginOut(String mac) {
		try {
			deviceService.exit(mac);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void saveClientLog(String deviceNo, String mac, String dataType,
			String deviceName, String dataContent, String areaNo, String ip,
			String from) {
		if (mac != null) {
			ClientLog cl = new ClientLog();
			cl.setDeviceNo(deviceNo);
			cl.setDeviceMac(mac);
			cl.setAreaNo(areaNo);
			cl.setDataContent(dataContent);
			cl.setDeviceName(deviceName);
			cl.setIp(ip);
			cl.setDataType(dataType);
			cl.setLaunch(from);
			clientLogService.saveClientLog(cl);
		}

	}

	@Override
	public void initialDeviceStatus(String status) {
		deviceService.updateDeviceStatusForAll(status);

	}
	
	@Override
	public void initAllDeviceStatus(String status) {
		devicesService.updateAllDeviceStatus(status);
	}

	@Override
	public void updateDeviceStatusByMac(String status, String mac) {
		deviceService.updateDeviceStatusByMac(status, mac);

	}
	
	@Override
	public void updateDeviceStatusByMacThread(final String status, final String mac) {
		Thread t = new Thread(  
				new Thread(){  
                    @Override  
                    public void run() {  
                    	try {
                    		deviceService.updateDeviceStatusByMac(status, mac);
						} catch (Exception e) {
							e.printStackTrace();
						}
                    }  
                }
        );
		t.start();
	}

	@Override
	public String queryCronTimeUpdate() {
		// TODO Auto-generated method stub
		return pushService.queryCronTimeUpdate();
	}

	public IDeviceService getDeviceService() {
		return deviceService;
	}

	public void setDeviceService(IDeviceService deviceService) {
		this.deviceService = deviceService;
	}

	public IPushService getPushService() {
		return pushService;
	}

	public void setPushService(IPushService pushService) {
		this.pushService = pushService;
	}

	public IPushFinishService getPushFinishService() {
		return pushFinishService;
	}

	public void setPushFinishService(IPushFinishService pushFinishService) {
		this.pushFinishService = pushFinishService;
	}

	public ISceneMonitorService getSceneMonitorService() {
		return sceneMonitorService;
	}

	public void setSceneMonitorService(ISceneMonitorService sceneMonitorService) {
		this.sceneMonitorService = sceneMonitorService;
	}

	public IClientLogService getClientLogService() {
		return clientLogService;
	}

	public void setClientLogService(IClientLogService clientLogService) {
		this.clientLogService = clientLogService;
	}

	@Override
	public ResultEntity<String> sendData(String jsonType,String data, String mac,Integer timeout) {
		TransVO trans = new TransVO();
		trans.setData(data);
		//如果jsonType为空，则默认是trans类型
		if(StringUtils.isBlank(jsonType)){
			trans.setJsonType(PubConstant.JSON_TYPE_TRANS);
		}else{
			trans.setJsonType(jsonType);
		}
		trans.setMac(mac);
		String jsonString = JsonUtil.objectToJsonString(trans);
		//如果超时时间为空或者0，则默认超时时间
		if(timeout==null || 0 == timeout){
			timeout = defaultTimeout;
		}
		return send(jsonString, mac,timeout);
	}
	
	@Override
	public ResultEntity<String> sendData(String data, String deviceNo, String mac) {
		TransVO trans = new TransVO();
		trans.setData(data);
		trans.setJsonType(PubConstant.JSON_TYPE_TRANS);
		trans.setMac(mac);
		String jsonString = JsonUtil.objectToJsonString(trans);
		return send(jsonString, deviceNo, mac);
	}
	
	@Override
	public ResultEntity<String> sendData(String jsonType,String data, String deviceNo, String mac) {
		TransVO trans = new TransVO();
		trans.setData(data);
		trans.setJsonType(jsonType);
		trans.setMac(mac);
		String jsonString = JsonUtil.objectToJsonString(trans);
		return send(jsonString, deviceNo, mac);
	}
	
	@Override
	public ResultEntity<String> sendUpdateData(final String data, final String mac) {
		return send(data, mac,defaultTimeout);
	}

	/**
	 * 发送数据
	 * @param jsonString
	 * @param mac
	 * @return
	 */
	private ResultEntity<String> send(final String jsonString, final String mac,final int timeout) {
		//间隔时间
		int perTime = 50;
		//超时时间如果很长的话，增加间隔时间
		if(timeout>=10000){
			perTime=100;
		}
		ResultEntity<String> re = new ResultEntity<String>(ResultEntity.FAILD,"", "");
		if (jsonString == null || "".equals(jsonString)) {
			re.setMessage("data is null");
			return re;
		}
		String deviceNoMac = StringUtil.coverMacToString(mac) + "_" + mac;
		Channel c = CacheUtil.getInstance()
				.getValueFromDeviceNoMac_channel_map(deviceNoMac);
//		Boolean flag = false;
		if (c != null && c.isConnected()) {
			log.info("Channel:" + c.getId() + ", send data:"+jsonString+" --> "+mac);
			CacheUtil.getInstance().removeKey(c.getId().toString());
			try {
				ChannelFuture _future = c.write(jsonString + "\r\n");
				Map<String, Object> map;
				int i = 0;
				while (true) {
					// str字符串数组[1,1],str[0]为返回成功标志,str[1]为撤防设防状态
					// Map<String,Object> map =
					// (Map<String,Object>)CacheUtil.getInstance().getValue(_future.getChannel().getId().toString());
					map = (Map<String, Object>) CacheUtil.getInstance().getValue(_future.getChannel().getId().toString());
					if (map != null && map.size() > 0) {
						Boolean code = (Boolean) map.get("code");
						if (code) {
							String temp = (String) map.get("data");
							re.setData(temp != null ? temp : "");
							re.setCode(ResultEntity.SUCCESS);
							break;
						}
					} else {
						Thread.sleep(perTime);
						i++;
						if (i >= timeout/perTime) {
							re.setCode(ResultEntity.FAILD);
							re.setMessage("failed");
							break;
						}
					}
				}
				final Map<String, Object> map1 = map;
				_future.addListener(new ChannelFutureListener() {
					public void operationComplete(ChannelFuture future)
							throws Exception {
						// ResultEntity<String> re = new ResultEntity<String>();
						if (future.isSuccess()) {
							// //从缓存中获取返回值
							// Map<String,Object> map1 =
							// (Map<String,Object>)CacheUtil.getInstance().getValue(future.getChannel().getId().toString());
							// 如果缓存有数据且是true则返回成功
							if (map1 != null && map1.size() > 0) {
								Boolean code = (Boolean) map1.get("code");
								if (code) {
									log.info("exec Data[" + jsonString + "] to Client[mac:" + mac + "] ok");
									mac_time_heart_map.put(mac, new Date());
								}
							} else {
								log.info(
										"===============exec Data failed, channelId:"
												+ future.getChannel().getId().toString(), " result:" + map1);
								// re.setCode(ResultEntity.FAILD);
								// re.setData("false");
								// deviceNoMac_channel_map.remove(mac);
								// removeDataFromMap(future.getChannel().getId());
								// future.getChannel().disconnect();
								// future.getChannel().close();
							}
							// 删除此缓存
							CacheUtil.getInstance().removeKey(future.getChannel().getId().toString());
							updateDeviceStatusByMacThread(PubConstant.ONLINE, mac);
						} else {
							log.info("exec Data[" + jsonString + "] to Client[mac:" + mac + "] failure!");
							deviceNoMac_channel_map.remove(mac);
							removeDataFromMap(future.getChannel().getId());
							future.getChannel().disconnect();
							future.getChannel().close();
						}
						// 删除此缓存
						CacheUtil.getInstance().removeKey(future.getChannel().getId() + "");
					}
				});
			} catch (Exception e1) {
				CacheUtil.getInstance().getDeviceNoMac_channel_map().remove(mac);
				removeDataFromMap(c.getId());
				c.disconnect();
				c.close();
				log.error("send Data to Client[mac:" + mac + "] exception:"
						+ e1.getMessage() + " " + e1);
				e1.printStackTrace();
			}
		} else {
			return new ResultEntity<String>(ResultEntity.FAILD, "", "false");
		}
		// }
//		try {
//			Thread.sleep(20);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
		return re;
	}
	
	/**
	 * 发送数据
	 * @param jsonString
	 * @param mac
	 * @return
	 */
	private ResultEntity<String> send(final String jsonString, final String deviceNo, final String mac) {
		ResultEntity<String> re = new ResultEntity<String>(ResultEntity.FAILD,
				"", "");
		if (jsonString == null || "".equals(jsonString)) {
			re.setMessage("data is null");
			return re;
		}
		String deviceNoMac = deviceNo + "_" + mac;
		Channel c = CacheUtil.getInstance()
				.getValueFromDeviceNoMac_channel_map(deviceNoMac);
//		Boolean flag = false;
		if (c != null && c.isConnected()) {
			log.info("Channel:" + c.getId() + ", send data:"+jsonString+" --> "+mac);
			CacheUtil.getInstance().removeKey(c.getId().toString());
			try {
				ChannelFuture _future = c.write(jsonString + "\r\n");
				Map<String, Object> map;
				int i = 0;
				while (true) {
					map = (Map<String, Object>) CacheUtil.getInstance().getValue(_future.getChannel().getId().toString());
					if (map != null && map.size() > 0) {
						Boolean code = (Boolean) map.get("code");
						if (code) {
							String temp = (String) map.get("data");
							re.setData(temp != null ? temp : "");
							re.setCode(ResultEntity.SUCCESS);
							break;
						}
					} else {
						Thread.sleep(30);
						i++;
						if (i >= 100) {
							re.setCode(ResultEntity.FAILD);
							re.setMessage("failed");
							break;
						}
					}
				}
				final Map<String, Object> map1 = map;
				_future.addListener(new ChannelFutureListener() {
					public void operationComplete(ChannelFuture future)
							throws Exception {
						// ResultEntity<String> re = new ResultEntity<String>();
						if (future.isSuccess()) {
							// //从缓存中获取返回值
							// 如果缓存有数据且是true则返回成功
							if (map1 != null && map1.size() > 0) {
								Boolean code = (Boolean) map1.get("code");
								if (code) {
									log.info("send data[" + jsonString
											+ "] to Client[mac:" + mac
											+ "] ok, result:" + map1);
									mac_time_heart_map.put(mac, new Date());
								}
							} else {
								log.info("=============send data failed, channelId:"
												+ future.getChannel().getId()
														.toString(), " result:"
												+ map1);
							}
							// 删除此缓存
							CacheUtil.getInstance().removeKey(
									future.getChannel().getId().toString());
							updateDeviceStatusByMacThread(PubConstant.ONLINE, mac);
						} else {
							log.info("send [" + jsonString + "] to Client[mac:"
									+ mac + "] failure!");
							deviceNoMac_channel_map.remove(mac);
							removeDataFromMap(future.getChannel().getId());
							future.getChannel().disconnect();
							future.getChannel().close();
						}
						// 删除此缓存
						CacheUtil.getInstance().removeKey(future.getChannel().getId() + "");
					}
				});
			} catch (Exception e1) {
				CacheUtil.getInstance().getDeviceNoMac_channel_map()
						.remove(mac);
				removeDataFromMap(c.getId());
				c.disconnect();
				c.close();
				log.error("send to Client[mac:" + mac + "] exception:"
						+ e1.getMessage() + " " + e1);
				e1.printStackTrace();
			}
		} else {
			return new ResultEntity<String>(ResultEntity.FAILD, "", "false");
		}
		// }
//		try {
//			Thread.sleep(20);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
		return re;
	}
	
	/**
	 * 
	 * 方法的描述: 清除session数据
	 * 
	 * @author: kk
	 * @version: 0.1
	 * @date: 2016-5-9 下午10:57:23
	 * @param channelId
	 */
	private void removeDataFromMap(Integer channelId) {
		SessionUtil.removeDataFromMap(channelId_mac_map, mac_deviceNoMac_map,
				deviceNoMac_channel_map, deviceNoMac_sentDataClient_map,
				mac_time_heart_map, this, log, channelId);
	}
	
	@Override
	public LoginVO register(JSONObject jb, String jsonType, String mac) {
		LoginVO lv = new LoginVO();
		RegistRsp rr = new RegistRsp();
		try {

			rr.setJsonType(jsonType);
			// 小区号
//			String areaNo = JsonUtil.getDataFromJsonObject(jb, "estateno");
//			// 区域号
//			String estateNo = JsonUtil.getDataFromJsonObject(jb, "estate");
//			// 栋号
//			String ridgepole = JsonUtil.getDataFromJsonObject(jb, "storey");
//			// 单元号
//			String unitNo = JsonUtil.getDataFromJsonObject(jb, "unit");
//			// 房号
//			String houseNo = JsonUtil.getDataFromJsonObject(jb, "roomNum");
//
//			String deviceNo = JsonUtil.getDataFromJsonObject(jb, "deviceNo");
//			// 子类型
//			String childDeviceType = JsonUtil.getDataFromJsonObject(jb,
//					"childDeviceType");
//			// 类型
			String deviceType = JsonUtil
					.getDataFromJsonObject(jb, "deviceType");
			// 如果是门口机，且子类型为1，该设备类型为围墙机
//			if (childDeviceType != null && "1".equals(childDeviceType.trim())
//					&& !"".equals(childDeviceType) && deviceType != null
//					&& "02".equals(deviceType.trim())) {
//				deviceType = "06";// 围墙机
//			}
			String deviceName = JsonUtil
					.getDataFromJsonObject(jb, "deviceName");
			//生成随机4位数密码
//			String devicePassword = StringUtil.randomNum(4);
			String deviceMac = JsonUtil.getDataFromJsonObject(jb, "mac");
			String deviceIp = JsonUtil.getDataFromJsonObject(jb, "ip");
//			String ipState = JsonUtil.getDataFromJsonObject(jb, "ipState");
//			String roomState = JsonUtil.getDataFromJsonObject(jb, "roomState");
			String version = JsonUtil.getDataFromJsonObject(jb,	"ver");
			if (deviceMac == null || deviceType == null) {
				rr.setCode(PubConstant.CLIENT_REGEIST_FAILURE);
			} else {
				RegistReq req = new RegistReq();
//				req.setAreaNo(areaNo);
//				req.setEstateNo(estateNo);
//				req.setRidgepole(ridgepole);
//				req.setUnitNo(unitNo);
//				req.setHouseNo(houseNo);
//				req.setDeviceNo(deviceNo);
				req.setDeviceType(deviceType);
				req.setDeviceIp(deviceIp);
				req.setDeviceMac(deviceMac);
//				req.setDevicePassword(devicePassword);
//				req.setIpState(ipState);
				req.setDeviceName(deviceName);
//				req.setHouseState(roomState);
				req.setVersion(version);

				String[] obj = deviceService.easyLogin(req);
				if (PubConstant.CLIENT_REGEIST_SUCCESS.equals(obj[1])) {
					updateDeviceStatusByMac(PubConstant.ONLINE, deviceMac);
					rr.setCode(PubConstant.CLIENT_REGEIST_SUCCESS);
					// lv.setDeviceNo(obj[0]);
					rr.setFileServerIP(obj[3]);
					rr.setDataServerIP(obj[2]);
					rr.setSocketServerIP(obj[4]);
					lv.setConflictMac(obj[5]);

				} else {
					rr.setCode(obj[1]);
				}
				rr.setDeviceNo(obj[0]);
				this.printLog(obj[1], req.getDeviceMac(), req.getDeviceIp(), obj[0],
						req.getDeviceName(), req.getAreaNo());

			}

		} catch (Exception ee) {
			log.info("login exception:" + ee.getMessage());
			rr.setCode(PubConstant.CLIENT_REGEIST_FAILURE);
		}
		lv.setRegistRsp(rr);
		return lv;
	}

	@Override
	public ResultEntity<String> saveAlarm(String data, String mac) throws Exception {
		// TODO Auto-generated method stub
		return alarmService.save(data,mac);
	}

	@Override
	public ResultEntity<String> saveDeviceAccessories(String data, String mac) throws Exception {
		// TODO Auto-generated method stub
		return deviceAccessoriesService.save(data, mac);
	}

	@Override
	public ResultEntity<String> pushDefenceStatusChanged(final String data,final String mac)
			throws Exception {
		Thread t = new Thread(  
                new Thread(){  
                    @Override  
                    public void run() {  
                    	try {
							pushClientService.pushDefenceStatusChanged(data,mac);
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
                    }  
                }  
        );
		t.start();
		return pushClientService.pushDefenceStatusChanged(data,mac);
	}
	
	@Override
	public void pushDefenceStatusChangedThread(final String data,final String mac){
		Thread t = new Thread(  
                new Thread(){  
                    @Override  
                    public void run() {  
                    	try {
							pushClientService.pushDefenceStatusChanged(data,mac);
						} catch (Exception e) {
							e.printStackTrace();
						}
                    }  
                }  
        );
		t.start();
	}
	
	public IAlarmService getAlarmService() {
		return alarmService;
	}

	public void setAlarmService(IAlarmService alarmService) {
		this.alarmService = alarmService;
	}

	public IDeviceAccessoriesService getDeviceAccessoriesService() {
		return deviceAccessoriesService;
	}

	public void setDeviceAccessoriesService(
			IDeviceAccessoriesService deviceAccessoriesService) {
		this.deviceAccessoriesService = deviceAccessoriesService;
	}

	public IPushClientService getPushClientService() {
		return pushClientService;
	}

	public void setPushClientService(IPushClientService pushClientService) {
		this.pushClientService = pushClientService;
	}

	public IAuthSessionService getAuthSessionService() {
		return authSessionService;
	}

	public void setAuthSessionService(IAuthSessionService authSessionService) {
		this.authSessionService = authSessionService;
	}

	@Override
	public ResultEntity<String> refreshAccessories(String data, String mac) {
		return deviceAccessoriesService.refresh(data, mac);
	}

	@Override
	public ResultEntity<String> clearDeviceAccessories(String mac) {
		return deviceAccessoriesService.clear(mac);
	}

	public IDevicesService getDevicesService() {
		return devicesService;
	}

	public void setDevicesService(IDevicesService devicesService) {
		this.devicesService = devicesService;
	}

}
