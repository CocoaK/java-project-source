package com.smarthome.socket.service.job;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelFuture;
import org.jboss.netty.channel.ChannelFutureListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.smarthome.socket.common.constant.PubConstant;
import com.smarthome.socket.common.util.CacheUtil;
import com.smarthome.socket.common.util.DateTimeUtil;
import com.smarthome.socket.common.util.JsonUtil;
import com.smarthome.socket.common.util.SessionUtil;
import com.smarthome.socket.service.business.adapater.PushRsp;
import com.smarthome.socket.service.netty.service.NettyService;
import com.smarthome.socket.service.vo.PushVO;

/**
 * 
 * 类名称：PushJob 类描述：推送工作
 * 
 * @author: kouy
 * @version: 0.1
 * @date: 2012-5-9 下午3:28:54
 */
public class PushJob extends BaseJob {
	Logger log = LoggerFactory.getLogger(PushJob.class.getName());
	private NettyService nettyService;
	private String pushKinds;
	private Map<String, Date> mac_time_heart_map;
	private Map<Integer, String> channelId_mac_map;
	private Map<String, String> mac_deviceNoMac_map;
	private Map<String, Channel> deviceNoMac_channel_map;
	private Map<String, Long> deviceNoMac_sentDataClient_map;

	public PushJob(Map<String, Date> mac_time_heart_map, Map<Integer, String> channelId_mac_map, Map<String, String> mac_deviceNoMac_map,
			Map<String, Channel> deviceNoMac_channel_map, Map<String, Long> deviceNoMac_sentDataClient_map, NettyService nettyService) {
		super();
		this.deviceNoMac_channel_map = deviceNoMac_channel_map;
		this.nettyService = nettyService;
		this.mac_time_heart_map = mac_time_heart_map;
		this.channelId_mac_map = channelId_mac_map;
		this.mac_deviceNoMac_map = mac_deviceNoMac_map;
		this.deviceNoMac_channel_map = deviceNoMac_channel_map;
		this.deviceNoMac_sentDataClient_map = deviceNoMac_sentDataClient_map;

	}

	public void execute()

	{
		//printSplitLine();
		//print("to query push data for kind:" + (pushKinds == null ? "normal" : pushKinds));
		// CacheUtil.getInstance().getAllMapsSize();
		boolean service_is_ok = CacheUtil.getInstance().serviceIsOK(PubConstant.TELNET_SERVICE_STATUS);

		if (service_is_ok) {
			// 初始化设备状态
//			boolean initalAllDeviceStatus = CacheUtil.getInstance().initalAllDevicesIsOK(PubConstant.INITAL_ALL_DEVICE);
			boolean initalAllDeviceStatus = true;

			if (!initalAllDeviceStatus)// 如果没有完成初始所有设备状态
			{
				print("inital all device outline status:" + initalAllDeviceStatus);
				// 初始化所有设备状态
				nettyService.initialDeviceStatus(PubConstant.OUTLINE);
				// 设置为初始化完成标志位
				CacheUtil.getInstance().addCache(PubConstant.INITAL_ALL_DEVICE, PubConstant.INITAL_ALL_DEVICE_STATUS);
			} else {
				// 当前时间
				Date currentDate = new Date();
					Object is_sending_data_time = CacheUtil.getInstance().getValue("data_sending");
					if (is_sending_data_time != null) {
						// 上次发送时间与当前时间间隔
						long diffSeconds = DateTimeUtil.getDiffSecond(currentDate, (Date) is_sending_data_time);
						if (diffSeconds > 10) {
							CacheUtil.getInstance().removeKey("data_sending");
							print("send data is over time 10s, cancel send and to query new data");
						} else {
							print("query data is sending,cancel query new data");
							return;
						}
					}
				
/*				if ("smartHomeDataPushSecondary".equals(pushKinds)) {
					Object is_sending_secondary_data_time = CacheUtil.getInstance().getValue("secondary_data_sending");
					if (is_sending_secondary_data_time != null) {
						// 上次发送时间与当前时间间隔
						long diffSeconds = DateTimeUtil.getDiffSecond(currentDate, (Date) is_sending_secondary_data_time);
						if (diffSeconds > 20) {// 超过20s
							CacheUtil.getInstance().removeKey("secondary_data_sending");
							print("send " + (pushKinds == null ? "normal" : pushKinds) + " data is over time 20s,cancel send and to query new data");
						} else {
							print("query " + (pushKinds == null ? "normal" : pushKinds) + " data is sending,undo query new data");
							return;
						}
					}
				}
				if (pushKinds == null) {
					Object is_sending_normal_data_time = CacheUtil.getInstance().getValue("normal_data_sending");
					if (is_sending_normal_data_time != null) {
						// 上次发送时间与当前时间间隔
						long diffSeconds = DateTimeUtil.getDiffSecond(currentDate, (Date) is_sending_normal_data_time);
						if (diffSeconds > 30) {//超过30s
							CacheUtil.getInstance().removeKey("normal_data_sending");
							print("send " + (pushKinds == null ? "normal" : pushKinds) + " data is over time 30s,undo send and to query new data");
						} else {
							print("query " + (pushKinds == null ? "normal" : pushKinds) + " data is sending,undo query data");
							return;
						}
					}
				}
*/
				// 如果完成初始所有设备状态
				String allMac = getAllMacFromMap();
				// queueDealData(allMac);
				currentDealData(allMac);
			}

		} else {
			print("service_is_ok:" + service_is_ok);
		}
	}

	/**
	 * 
	 * 方法的描述: 串行发送数据
	 * 
	 * @author: kouy
	 * @version: 0.1
	 * @date: 2012-12-7 下午5:55:41
	 * @param allMac
	 */
	private void queueDealData(String allMac) {
		if (allMac != null) {
			//print("current client ids:" + (allMac == null ? "no" : allMac));
			List<PushVO> dataList = nettyService.listPushDataByClientID(allMac);
			if (dataList != null && !dataList.isEmpty()) {
				//print("query " + (pushKinds == null ? "normal" : pushKinds) + " data size:" + dataList.size());
				for (PushVO pv : dataList) {

					if (pv != null) {
						String deviceNo = pv.getPushClientId();

						if (deviceNo != null) {

							if (deviceNoMac_channel_map != null && !deviceNoMac_channel_map.isEmpty()) {

								Set<String> keySet = deviceNoMac_channel_map.keySet();
								if (keySet != null && !keySet.isEmpty()) {
									for (String key : keySet) {
										String _deviceNo = key.split("_")[0];
										String mac = key.split("_")[1];
										if (_deviceNo.equals(deviceNo)) {

											// if
											// (deviceNoMac_sentDataClient_map
											// != null &&
											// !deviceNoMac_sentDataClient_map.isEmpty())
											// {
											// if
											// (!deviceNoMac_sentDataClient_map.containsKey(key))
											// {

											// sendData(key, pv);
											// }
											// } else {

											// sendData(key, pv);
											// }
										}
									}
								}
							}
						}
					}
				}
			}
		}
	}

	/**
	 * 
	 * 方法的描述: 并行处理数据
	 * 
	 * @author: kouy
	 * @version: 0.1
	 * @date: 2012-12-7 下午5:53:47
	 * @param allMac
	 */
	private void currentDealData(String allMac) {
		if (allMac != null) {
			CacheUtil.getInstance().addCache("data_sending", new Date());
			print("send insert send data flag");
			/*if ("smartHomeDataPushSecondary".equals(pushKinds)) {
				CacheUtil.getInstance().addCache("secondary_data_sending", new Date());
				print("send " + (pushKinds == null ? "normal" : pushKinds) + " insert send data flag");
			}
			if (pushKinds == null) {
				CacheUtil.getInstance().addCache("normal_data_sending", new Date());
				print("send " + (pushKinds == null ? "normal" : pushKinds) + " insert send data flag");

			}*/
			// 查询出每个在线设备待推送数据
			Map<String, List<PushVO>> dataMap = null;
			try {
				dataMap = nettyService.listPushDataByClientIDForMap(allMac,null);
			} catch (Exception e1) {
				CacheUtil.getInstance().removeKey("data_sending");
				print("data remove send flag");
				
				/*if ("smartHomeDataPushSecondary".equals(pushKinds)) {
					CacheUtil.getInstance().removeKey("secondary_data_sending");
					print((pushKinds == null ? "normal" : pushKinds) + "  data remove send flag");
				}
				if (pushKinds == null) {
					CacheUtil.getInstance().removeKey("normal_data_sending");
					print((pushKinds == null ? "normal" : pushKinds) + "  data remove send flag");
				}*/
				e1.printStackTrace();
			}
			if (dataMap != null) {
				printNoLn("query data size:" + dataMap.size());
				Set<String> keySet = dataMap.keySet();
				if (keySet != null && !keySet.isEmpty()) {
					ExecutorService exec = Executors.newFixedThreadPool(keySet.size() + 1);
					printNoLn("exec num:" + (keySet.size() + 1));
					for (String key : keySet) {
						if (key != null) {
							final List<PushVO> dataList = dataMap.get(key);
							final String deviceNoMac = this.getDeviceNoMacFromMap(key);

							if (dataList != null && !dataList.isEmpty()) {
								print("send data to client[:" + deviceNoMac
										+ "],total data num is:" + dataList.size());
								currentSend(exec, dataList, deviceNoMac);
							} else {
								print("send data to client[:" + deviceNoMac
										+ "],total data num is:0 or null");
							}
						}
					}
					exec.shutdown();
					if (exec.isTerminated()) {
						printNoLn(" exec is terminated");
					} else {
						printNoLn(" exec not terminated");
					}
					if (exec.isShutdown()) {
						printNoLn(" exec shutdown");
					} else {
						printNoLn(" exec not shutdown");
					}
					try {
						boolean isTerminate = exec.awaitTermination(Long.MAX_VALUE, TimeUnit.DAYS);
						if (isTerminate) {
							CacheUtil.getInstance().removeKey("data_sending");
							print("data send finished");
						/*	
							if ("smartHomeDataPushSecondary".equals(pushKinds)) {
								CacheUtil.getInstance().removeKey("secondary_data_sending");
								print((pushKinds == null ? "normal" : pushKinds) + "  data send finished");
							}
							if (pushKinds == null) {
								CacheUtil.getInstance().removeKey("normal_data_sending");
								print((pushKinds == null ? "normal" : pushKinds) + "  data send finished");
							}
						*/
							print("all thread complete");
						} else {
							print("all thread not complete");
						}
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						print("all thread not complete,and throw exception");
						e.printStackTrace();
					}
					printSplitLine();

				}

			} else {
				print("query data size is 0");
			}
		} else {
			//print("current client ids:" + (allMac == null ? "no" : allMac));
		}
	}

	/**
	 * 
	 * 方法的描述: 并行发送数据
	 * 
	 * @author: kouy
	 * @version: 0.1
	 * @date: 2012-12-7 下午5:44:45
	 * @param exec
	 * @param dataList
	 * @param deviceNoMac
	 */
	private void currentSend(ExecutorService exec, final List<PushVO> dataList, final String deviceNoMac) {
		if (exec != null && !exec.isShutdown()) {
			exec.execute(new Runnable() {
				public void run() {
					printSplitLine();
					printNoLn("" + (pushKinds == null ? "normal" : pushKinds) + "  exec  runnable...");

					for (int i = 0; i < dataList.size();) {
						PushVO pv = dataList.get(i);
						if (pv != null) {

							// if (deviceNoMac_sentDataClient_map != null &&
							// !deviceNoMac_sentDataClient_map.isEmpty()) {
							// if
							// (!deviceNoMac_sentDataClient_map.containsKey(deviceNoMac))
							// {
							// print("" + (pushKinds == null ? "normal" :
							// pushKinds) +
							// " exec  runnable to send data1 pushId is:"
							// + pv.getId());
							// sendData(deviceNoMac, pv);
							// } else {

							// print((pushKinds == null ? "normal" : pushKinds)
							// + "  client [:" + deviceNoMac
							// + "] sending old data pushId is:" +
							// deviceNoMac_sentDataClient_map.get(deviceNoMac)
							// + ",and cancel new data pushId:" + pv.getId() +
							// " to send");
							// }

							// } else {

							// print("" + (pushKinds == null ? "normal" :
							// pushKinds) +
							// " exec  runnable to send data pushId is:"
							// + pv.getId());
							// print((pushKinds == null ? "normal" : pushKinds)
							// + "  data :total num:"+
							// dataList.size()+",and now send is:"+i);
							sendData(deviceNoMac, pv, i);
							/*
							 * Object
							 * sent=CacheUtil.getInstance().getValue(pv.getId
							 * ()+""); if(sent!=null) {
							 * 
							 * i++;
							 * CacheUtil.getInstance().removeKey(pv.getId()+"");
							 * }
							 */
							// }
							try {
								Thread.sleep(500);
								i++;
								// print(" send " + (pushKinds == null ?
								// "normal" : pushKinds) + "   data pushId :"
								// + pv.getId()+",and wait for 100ms");
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}

						}
					}

				}
			});
		} else {
			print("" + (pushKinds == null ? "normal" : pushKinds) + "   exec  is null or shutdown");
		}
	}

	/**
	 * 
	 * 方法的描述: 获得设备编号mac组合
	 * 
	 * @author: kouy
	 * @version: 0.1
	 * @date: 2012-12-7 下午5:37:52
	 * @param deviceNo
	 * @return
	 */
	private String getDeviceNoMacFromMap(String deviceNo) {
		String deviceNoMac = null;
		if (deviceNoMac_channel_map != null && !deviceNoMac_channel_map.isEmpty()) {

			Set<String> keySet = deviceNoMac_channel_map.keySet();
			if (keySet != null && !keySet.isEmpty()) {
				for (String key : keySet) {
					String _deviceNo = key.split("_")[0];
					if (_deviceNo.equals(deviceNo)) {
						deviceNoMac = key;
					}
				}
			}
		}
		return deviceNoMac;
	}

	/**
	 * 
	 * 方法的描述: 获得所有在线设备MAC
	 * 
	 * @author: kouy
	 * @version: 0.1
	 * @date: 2012-5-11 上午11:32:39
	 */
	private String getAllMacFromMap() {
		StringBuffer buf = new StringBuffer();
		if (deviceNoMac_channel_map != null && !deviceNoMac_channel_map.isEmpty()) {
			Set<String> keySet = deviceNoMac_channel_map.keySet();
			print((pushKinds == null ? "normal" : pushKinds) + " data client num:" + (keySet == null ? 0 : keySet.size()));
			if (keySet != null && !keySet.isEmpty()) {
				for (String key : keySet) {
					if (key != null) {
						String deviceNo = key.split("_")[0];
						if (deviceNo != null) {
							// buf.append("'");
							buf.append(deviceNo);
							// buf.append("'");
							buf.append(",");
						}
					}
				}
			}
		}
		if (buf.length() > 0) {
			String s = buf.toString();
			if (s.endsWith(",")) {
				int lastIndex = s.lastIndexOf(",");
				s = s.substring(0, lastIndex);

			}
			return s;
		} else {
			return null;
		}
	}

	public static void main(String[] arg) {
		String s = "'o20120806182139R','m20120806182327O',";
		int lastIndex = s.lastIndexOf(",");
		s = s.substring(0, lastIndex);
		System.out.println(s);
	}

	/**
	 * 
	 * 方法的描述: 发送数据
	 * 
	 * @author: kouy
	 * @version: 0.1
	 * @date: 2012-5-9 下午7:44:03
	 * @param deviceNo
	 * @param pv
	 */
	private void sendData(final String deviceNoMac, PushVO pv, final int num) {
		if (deviceNoMac != null) {
			String[] array = deviceNoMac.split("_");
			if (array != null && array.length > 0) {
				String deviceNo = deviceNoMac.split("_")[0];
				String mac = deviceNoMac.split("_")[1];
				Channel c = deviceNoMac_channel_map.get(deviceNoMac);
				if (c != null && c.isConnected()) {
					try {
						final Long pushDataId = pv.getId();
						// Long pushedDataId =
						// deviceNoMac_sentDataClient_map.get(deviceNoMac);

						// if (pushDataId != null &&
						// !pushDataId.equals(pushedDataId)) {
						PushRsp pr = new PushRsp(pv.getPushName(), pv.getPushContent(), pv.getPushVersion(), pv.getPushClientId(),
								pv.getSize()+"", pv.getPushDescription());
						pr.setJsonType(pv.getPushKind());
						pr.setPushId(pv.getId());
						String jsonData = JsonUtil.objectToJsonString(pr);

						ChannelFuture _future = c.write(jsonData + "\r\n");

						final String pushData = jsonData;
						final String pushKind = pv.getPushKind();
						// deviceNoMac_sentDataClient_map.put(deviceNoMac,
						// pushDataId);
						_future.addListener(new ChannelFutureListener() {
							public void operationComplete(ChannelFuture future) throws Exception {
								final String deviceNo = deviceNoMac.split("_")[0];
								final String mac = deviceNoMac.split("_")[1];

								if (future.isSuccess()) {
									print("push " + (pushKinds == null ? "normal" : pushKinds) + " Data[No:" + num + ",pushDataId:"
											+ pushDataId + "] to Client[mac:" + mac + ",deviceNo:" + deviceNo + "] successfully:"
											+ pushData);

									mac_time_heart_map.put(mac, new Date());

									// 异步处理
									/*
									 * ExecutorService exec =
									 * Executors.newFixedThreadPool(1);
									 * exec.execute(new Runnable() { public void
									 * run() { // 更新设备状态为在线状态
									 * nettyService.updateDeviceStatusByMac
									 * (PubConstant.ONLINE, mac); boolean flag =
									 * nettyService
									 * .savePushFinishData(pushDataId); if
									 * (flag) {
									 * deviceNoMac_sentDataClient_map.remove
									 * (deviceNoMac);
									 * nettyService.saveClientLog(deviceNo, mac,
									 * pushKind, null, pushData, null, null,
									 * PubConstant.DATA_FROM_SERVER);
									 * 
									 * } try { Thread.sleep(100); } catch
									 * (InterruptedException e) { // TODO
									 * Auto-generated catch block
									 * e.printStackTrace(); }
									 * 
									 * } }); exec.shutdown();
									 */

									// 更新设备状态为在线状态

									nettyService.updateDeviceStatusByMac(PubConstant.ONLINE, mac);
									boolean flag = nettyService.savePushFinishData(pushDataId);
									if (flag) {
										// deviceNoMac_sentDataClient_map.remove(deviceNoMac);
										nettyService.saveClientLog(deviceNo, mac, pushKind, null, pushData, null, null,
												PubConstant.DATA_FROM_SERVER);

									}

								} else {
									// deviceNoMac_sentDataClient_map.remove(deviceNoMac);
									log.info("push " + (pushKinds == null ? "normal" : pushKinds) + " Data to Client[mac:" + mac
											+ ",deviceNo:" + deviceNo + ",dataNo:" + num + ",pushDataId:" + pushDataId + "]:failure!");
									deviceNoMac_channel_map.remove(deviceNoMac);
									removeDataFromMap(future.getChannel().getId());
									future.getChannel().disconnect();
									future.getChannel().close();
								}

							}
						});
						// log.info("future3:" +
						// _future.getChannel().getId() +
						// "---iscancelled:" + _future.isCancelled() +
						// ",isdone:"
						// + _future.isDone() + ",issucess:" +
						// _future.isSuccess());

						// }

					} catch (Exception e1) {
						deviceNoMac_channel_map.remove(deviceNoMac);
						removeDataFromMap(c.getId());
						c.disconnect();
						c.close();
						log.error("push Data to Client[mac:" + mac + ",deviceNo:" + deviceNo + "] exception:" + e1.getMessage());
					}
				}
			}
		}
	}

	/**
	 * 
	 * 方法的描述: 清除session数据
	 * 
	 * @author: kouy
	 * @version: 0.1
	 * @date: 2012-5-9 下午10:57:23
	 * @param channelId
	 */
	private void removeDataFromMap(Integer channelId) {

		SessionUtil.removeDataFromMap(channelId_mac_map, mac_deviceNoMac_map, deviceNoMac_channel_map, deviceNoMac_sentDataClient_map,
				mac_time_heart_map, nettyService, log, channelId);
	}
}
