package com.biencloud.smarthome.service.common.constants;

/**
 * 常量定义类。
 * 
 * @author kouy
 * @since 1.0 2012-4-19
 */
public class Constants {

	private Constants() {
		// Never instance
	}

	/** 分页默认每页记录数 */
	public static final int DEFAULT_PAGE_SIZE = 10;

	/** 用户在线标志：下线 */
	public static final String LOGIN_OFFLINE = "0";

	/** 用户在线标志：在线 */
	public static final String LOGIN_ONLINE = "1";

	/** 调度服务的Bean名称 */
	public static final String SCHEDULER_BEAN_NAME = "scheduler";

	/** 参数名：登录初始密码 */
	public static final String PARAM_LOGIN_INIT_PASS = "login_init_pass";

	/** 参数名：web下载相对路径 */
	public static final String PARAM_WEB_DOWNLOAD_REL_URL = "web_download_rel_url";

	/** 参数名：app下载相对路径 */
	public static final String PARAM_APP_DOWNLOAD_REL_URL = "app_download_rel_url";

	/** 登录初始密码参数值 */
	public static final String VALUE_LOGIN_INIT_PASS = "888888";
	/** 系统管理员 */
	public static final String LOGIN_USER_TYPE_SYSTEM = "03";

	/** 物业管理员 */
	public static final String LOGIN_USER_TYPE_PAUSER = "02";

	/** 业主 */
	public static final String LOGIN_USER_TYPE_OWNER = "01";

	/** 首页查询记录数 */
	public static final int SELECT_COUNT_FOR_INDEX = 5;

	/** 设备状态：在线 */
	public static final String DEVICE_ONLINE = "1";

	/** 设备状态：离线 */
	public static final String DEVICE_OFFLINE = "0";

	/** 文件服务器地址参数代码 */
	public static final String FILE_SERVER_URL = "file_server_url";

	/** 从外网访问文件服务器地址参数代码 */
	public static final String EXTERNAL_FILE_SERVER_URL = "fileServerIP";

	/** web下载相对地址 */
	public static final String WEB_DOWNLOAD_RELATIVE_URL = "web_download_rel_url";

	/** app下载相对地址 */
	public static final String APP_DOWNLOAD_RELATIVE_URL = "app_download_rel_url";

	/** 室内机 */
	public static final String INDOOR_DEVICE = "01";

	/** 单元门口机 */
	public static final String UNIT_DOOR_DEVICE = "02";

	/** 子门口机 */
	public static final String CHILD_DOOR_DEVICE = "03";

	/** 管理机 */
	public static final String MANAGEMENT_DEVICE = "04";

	/** 门卫机 */
	public static final String GATE_GUARD_DEVICE = "05";

	/** 围墙机 */
	public static final String FENCE_DEVICE = "06";

	/** 设备修改接口updateDevice的参数updateType的值: 修改密码 */
	public final static String DEVICE_PASSWORD = "password";

	/** 设备修改接口updateDevice的参数updateType的值: 修改设备名称 */
	public final static String DEVICE_NAME = "deviceName";

	/** 设备修改接口updateDevice的参数updateType的值: 修改设备别名 */
	public final static String DEVICE_ALIAS = "deviceAlias";

	/** 推送操作类型：新增 */
	public static final String PUSH_OP_TYPE_ADD = "01";

	/** 推送操作类型：修改 */
	public static final String PUSH_OP_TYPE_UPDATE = "02";

	/** 推送操作类型：删除 */
	public static final String PUSH_OP_TYPE_REMOVE = "03";

	/** 推送操作类型：停止（禁用） */
	public static final String PUSH_OP_TYPE_STOP = "04";

	/** 推送操作类型：恢复（启用） */
	public static final String PUSH_OP_TYPE_RESUME = "05";

	/** 推送操作类型：升级 */
	public static final String PUSH_OP_TYPE_UPGRADE = "06";

	/** 权限状态：启用 */
	public static final String PERMISSIONS_ENABLED = "0";

	/** 权限状态：禁用 */
	public static final String PERMISSIONS_DISABLED = "1";

	/** 天气状态：禁用 */
	public static final String WEATHER_DISABLED = "0";

	/** 天气状态：启用 */
	public static final String WEATHER_ENABLED = "1";

	/** 登录领域服务Bean名称 */
	public static final String BEAN_NAME_LOGIN_SERVICE = "loginService";

	/** IP是已被分配 */
	public static final String IP_USED = "1";

	/** IP是未被分配 */
	public static final String IP_NOT_USED = "0";

	/** 系统管理用户领域服务Bean名称 */
	public static final String BEAN_NAME_SA_USER_SERVICE = "saUserService";

	/** 物业管理用户领域服务Bean名称 */
	public static final String BEAN_NAME_PA_USER_SERVICE = "paUserService";

	/** 住户用户管理领域服务Bean名称 */
	public static final String BEAN_NAME_OWNER_USER_SERVICE = "ownerUserService";

	/** 楼宇管理领域服务Bean名称 */
	public static final String BEAN_NAME_BUILDING_SERVICE = "regionBuildingInfoService";

	/** 单元管理领域服务Bean名称 */
	public static final String BEAN_NAME_CELL_SERVICE = "buildingCellInfoService";

	/** 房号管理领域服务Bean名称 */
	public static final String BEAN_NAME_HOUSE_SERVICE = "cellHouseholdInfoService";

	/** 户型管理领域服务Bean名称 */
	public static final String BEAN_NAME_CELL_SIZE_SERVICE = "cellSizeInfoService";

	/** 设备管理领域服务Bean名称 */
	public static final String BEAN_NAME_DEVICE_SERVICE = "deviceService";

	/** 收费数据管理领域服务Bean名称 */
	public static final String BEAN_NAME_CHARGE_DATA_SERVICE = "chargeDataService";

	/** 报修管理领域服务Bean名称 */
	public static final String BEAN_NAME_REQUEST_REPAIR_SERVICE = "requestRepairService";

	/** 菜单状态：显示 */
	public static final String MENU_STATUS_VISIBLE = "0";

	/** 菜单状态：隐藏 */
	public static final String MENU_STATUS_HIDDEN = "1";

	/** 菜单父代码：系统管理 */
	public static final String MENU_PARENT_CODE_SA = "M001";

	/** 菜单父代码：物业管理 */
	public static final String MENU_PARENT_CODE_PA = "M002";

	/** 菜单父代码：业主 */
	public static final String MENU_PARENT_CODE_OWNER = "M003";

	/** 房号状态：入住 */
	public static final String HOUSE_STATUS_CHECK = "1";

	/** 房号状态：未入住 */
	public static final String HOUSE_STATUS_UNCHECK = "0";

	// 同一个房号下是否允许存在多个设备
	public static final String IsAllowManyDeviceInHouse = "isAllowManyDeviceInHouse";

	/** 删除几天前的数据库表中的操作日志 */
	public static final String OPERATE_LOG_DELETE_DAY_COUNT = "operateLogDeleteDayCount";

	/** 删除几天前的应用和服务器日志 */
	public static final String SERVER_LOG_DELETE_DAY_COUNT = "serverLogDeleteDayCount";

	/** 删除几天前的数据库中的App请求日志 */
	public static final String APP_DATA_LOG_DELETE_DAY_COUNT = "appDataLogDeleteDayCount";

	/** 是否自定义户型：否 */
	public static final String SIZE_CUSTOM_FLAG_NO = "0";

	/** 是否自定义户型：是 */
	public static final String SIZE_CUSTOM_FLAG_YES = "1";

	/** 门卡状态：启用 */
	public static final String GATE_CARD_ENABLED = "0";

	/** 门卡状态：禁用 */
	public static final String GATE_CARD_DISABLED = "1";
	
	/** 门卡状态：门口机授权 */
	public static final String GATE_CARD_DEVICE_AUTH = "2";

	/** 场景类型：远程家电控制 */
	public static final String SCENE_KIND_DEVICE = "1";

	/** 场景类型：布防撤防 */
	public static final String SCENE_KIND_PROTECTION_AND_REMOVAL = "0";

	/**
	 * 参数名：推送数据类型优先级：优先级由低到高依次排列。以","分隔。 例如：setSceneDeviceStatus,openDoorCommand
	 * 则openDoorCommand优先级较高，优先推送
	 */
	public static final String PUSH_KIND_PRIORITY = "push_kind_priority";

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
	
	/** 随机密码长度 */
	public static final Integer PASSWD_LENGTH = 6;
	
	/** 默认账号类型 */
	public static final Integer SIP_REGISTER_TYPE_DEFAULT = 0;
	
	/** 管理机账号类型 */
	public static final Integer SIP_REGISTER_TYPE_MANAGE = 1;
	
	/** 极光推送appKey*/
	public static final String PUSH_APP_KEY_JPUSH = "e6095bbd178e75ad6f83226e";
	
	/** 极光推送Master Secret*/
	public static final String PUSH_MASTER_SECRET_JPUSH = "5ddfd535348bb62900847b3c";
	
	/** 1byone极光推送appKey*/
	public static final String PUSH_APP_KEY_JPUSH_1BYONE = "199fc354d5ad181ef760b4b1";
	
	/** 1byone极光推送Master Secret*/
	public static final String PUSH_MASTER_SECRET_JPUSH_1BYONE = "21293204b2fa40a1c6fddf35";
	
	/** 推送重试次数 */
	public static final Integer PUSH_RETRY_COUNT = 3;
	
	/** IOS推送证书名称*/
	public static final String IOS_PUSH_CERT_NAME = "quhwaPushCert.p12";
	
	/** IOS推送证书名称*/
	public static final String IOS_PUSH_CERT_NAME_1BYONE = "1byonePushCert.p12";
	
	/** IOS推送证书密码 */
    public static final String IOS_PUSH_CERT_PASSWD = "quhwasmarthouse123456";
    
    /** 密码找回功能的发送邮箱账号 */
    public static final String EMAIL_SEND_ACCT = "software.rnd@quhwa.com";
    
    /** 密码找回功能的发送邮箱密码 */
    public static final String EMAIL_SEND_PASSWORD = "11qq22ww";
    
    /** 安卓skeeper推送 title */
    public static final String PUSH_TITLE = "Skeeper";
    
    /** 安卓1byone推送 title */
    public static final String PUSH_TITLE_1BYONE = "1byone keeper";
    
    /** 推送门铃声 */
    public static final String PUSH_SOUND_DOORBELL = "doorbell.wav";
    
    /** 推送报警声 */
    public static final String PUSH_SOUND_ALARM = "alarm.wav";
    
    /** 推送铃声定制 */
    public static final String PUSH_SOUND_CUSTOM = "1";
    
    /** 是否为正式生产版本推送*/
    public static final boolean IS_PUSH_PRODUCT = true;
}
