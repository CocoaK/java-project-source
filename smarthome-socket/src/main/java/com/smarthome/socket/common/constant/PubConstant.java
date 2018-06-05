package com.smarthome.socket.common.constant;

/**
 * 
 * 类名称：PubConstant 类描述：公共常量类
 * 
 * @author: kouy
 * @version: 0.1
 * @date: 2012-5-4 下午9:56:49
 */
public class PubConstant {
	// 注册失败
	public final static String CLIENT_REGEIST_FAILURE = "0";
	// 注册成功
	public final static String CLIENT_REGEIST_SUCCESS = "1";
	// 数据由App发起
	public final static String DATA_FROM_APPCLIENT = "appClient";

	// 数据由Server发起
	public final static String DATA_FROM_SERVER = "server";

	// 在线
	public static final String ONLINE = "1";
	// 离线
	public static final String OUTLINE = "0";
	// telnet service状态
	public static final String TELNET_SERVICE_STATUS = "service_start_up_is_ok";

	// telnet service状态:成功
	public static final String TELNET_SERVICE_STATUS_SUCCESS = "success";
	// telnet service状态：失败
	public static final String TELNET_SERVICE_STATUS_FAIL = "fail";

	// 初始化所有设备状态
	public static final String INITAL_ALL_DEVICE = "inital_all_device";

	// 初始化所有设备状态:成功
	public static final String INITAL_ALL_DEVICE_STATUS = "success";

	// 推送类型:紧急推送类型（优先级最高）
	public static final String PUSH_KINDS_INSTANCY = "push_kind_instancy";

	// 推送类型：次要推送类型
	public static final String PUSH_KINDS_SECONDARY = "push_kind_secondary";

	// 次要推送类型定时任务cron参数名称
	public static final String CRON_TIME_SECONDARY = "secondary_cron_time";

	// 紧要推送类型定时任务cron参数名称
	public static final String CRON_TIME_INSTANCY = "instancy_cron_time";

	// 普通推送类型定时任务cron参数名称
	public static final String CRON_TIME_NORMAL = "normal_cron_time";

	// 心跳定时任务cron参数名称
	public static final String CRON_TIME_HEARTBEAT = "heartBeat_cron_time";
	
	//JSON类型 透传
	public static final String JSON_TYPE_TRANS = "trans";
	//JSON类型 刷新
	public static final String JSON_TYPE_REFRESH = "refresh";

}
