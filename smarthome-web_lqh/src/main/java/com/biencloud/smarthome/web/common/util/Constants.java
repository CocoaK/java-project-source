package com.biencloud.smarthome.web.common.util;

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

	/** 全局异常统一显示视图名称 */
	public static final String GLOBAL_EXCEPTION_RESULT = "exception";
	
	/** 登录信息Session名称 */
	public static final String KEY_LOGIN_SESSION = "login";
	
	/** Key名称 ：登录帐号初始密码*/
	public static final String KEY_LOGIN_INIT_PASS = "loginInitPass";

	/** 用户在线标志：下线 */
	public static final String LOGIN_OFFLINE = "0";

	/** 用户在线标志：在线 */
	public static final String LOGIN_ONLINE = "1";

	/** 登录成功 */
	public static final String LOGIN_SUCCESS = "00";

	/** 帐号不存在 */
	public static final String ACC_ERROR = "01";

	/** 密码错误 */
	public static final String PASS_ERROR = "02";

	/** 帐号已禁用 */
	public static final String ACC_DISABLED = "03";

	/** 帐号已锁定 */
	public static final String ACC_LOCKED = "04";

	/** 帐号已删除 */
	public static final String ACC_REMOVED = "05";

	/** 帐号已启用 */
	public static final String ACC_ENABLED = "06";
	
	/** 会话超时 */
	public static final String SESSION_TIMEOUT = "07";
	
	/** 同一账号不允许在不同IP同时登录 */
	public static final String LOGIN_SYNC = "08";
	
	/** 用户未登录 */
	public static final String ACC_OFFLINE = "09";
	
	/** Web移动版只支持业主登录 */
	public static final String ONLY_OWNER = "10";

	/** 终端系统管理员 */
	public static final String LOGIN_USER_TYPE_CLIENT_SYSTEM = "04";
	/** 系统管理员 */
	public static final String LOGIN_USER_TYPE_SYSTEM = "03";

	/** 物业管理员 */
	public static final String LOGIN_USER_TYPE_PAUSER = "02";

	/** 业主 */
	public static final String LOGIN_USER_TYPE_OWNER = "01";

	/** 广告投放到App系统 */
	public static final String AD_APP_SYS = "01";

	/** 广告投放到Web系统 */
	public static final String AD_WEB_SYS = "02";

	/**
	 * 1.单元门口机 02 2.子门口机 03 3.室内机 01 4.管理机 04 5.门卫机 05 6.围墙机 06
	 */
	public static final String DEVICE_TYPE_MANAGEMENT = "04";
	public static final String DEVICE_TYPE_INDOOR = "01";

	/** 操作结果：成功 */
	public static final int LOG_SUCCESS = 1;

	/** 操作结果：返回失败结果 */
	public static final int LOG_FAILURE_RESULT = 2;

	/** 操作结果：发生异常 */
	public static final int LOG_EXCEPTION = 3;

	/** 操作结果：未知 */
	public static final int LOG_UNKNOW = 4;
	/** 首页查询记录数 */
	public static final int SELECT_COUNT_FOR_INDEX = 5;

	/** 广告投放状态：已申请 */
	public static final String AD_APPLIED = "0";

	/** 广告投放状态：等待发布 */
	public static final String AD_PUBLISHING = "1";

	/** 广告投放状态：已发布 */
	public static final String AD_PUBLISHED = "2";

	/** 广告投放状态：已投放 */
	public static final String AD_RUNNING = "3";

	/** 广告投放状态：已停止 */
	public static final String AD_STOPPED = "4";
	
	/** 广告位置代码：主页面右边栏 */
	public static final String AD_LOCATION_CODE_MAIN_RIGHT = "WEB00001";

	/** 软件升级状态：已申请未审核 */
	public static final String SU_APPLIED = "0";

	/** 软件升级状态：已审核未发布 */
	public static final String SU_APPROVED = "1";

	/** 软件升级状态：审核未通过 */
	public static final String SU_REFUSED = "2";

	/** 软件升级状态：等待发布 */
	public static final String SU_PUBLISHING = "3";

	/** 软件升级状态：已发布 */
	public static final String SU_PUBLISHED = "4";

	/** 软件升级状态：已禁用 */
	public static final String SU_DISABLED = "5";

	/** 设备状态：在线 */
	public static final String DEVICE_ONLINE = "1";

	/** 设备状态：离线 */
	public static final String DEVICE_OFFLINE = "0";

	/** 操作日志代码的国际化前缀 */
	public static final String OPERATION_CODE = "operation.code.";

	/** 上传文件相对路径 */
	public static final String UPLOAD_FILE_RELATIVE_PATH = "/upload/fileAction_webUpload.action";

	/** 下载文件相对路径 */
	public static final String DOWNLOAD_FILE_RELATIVE_PATH = "/upload/fileAction_downloadWebUploadFile.action";

	/** 通话类型:打出 */
	public static final String CALL_TYPE_CALL_OUT = "0";

	/** 通话类型:打进（未接） */
	public static final String CALL_TYPE_CALL_IN = "1";

	/** 通话类型:打进已接 */
	public static final String CALL_TYPE_CALL_IN_PICKUP = "2";

	/** 通话类型:留言 */
	public static final String CALL_TYPE_MESSAGE = "3";
	
	/**设备修改接口updateDevice的参数updateType的值: 修改密码*/
	public final static String DEVICE_PASSWORD = "password";
	
	/**设备修改接口updateDevice的参数updateType的值: 修改设备名称*/
	public final static String DEVICE_NAME = "deviceName";

	/**设备修改接口updateDevice的参数updateType的值: 修改设备别名*/
	public final static String DEVICE_ALIAS = "deviceAlias";

	/** 参数代码：从外网访问文件服务器地址 */
	public static final String EXTERNAL_FILE_SERVER_URL = "fileServerIP";

	/** 参数代码：登录初始密码 */
	public static final String PARAM_CODE_LOGIN_INIT_PASS = "login_init_pass";
	
	/** 参数代码：app下载相对路径 */
	public static final String PARAM_CODE_APP_DOWNLOAD_REL_URL = "app_download_rel_url";
	
	/** 参数代码：web下载相对路径 */
	public static final String PARAM_CODE_WEB_DOWNLOAD_REL_URL = "web_download_rel_url";
	
	/** 参数代码：App软件格式 */
	public static final String PARAM_CODE_APP_SOFTWARE_FORMAT = "app_software_format";
	
	/** 参数代码：web图片格式 */
	public static final String PARAM_CODE_WEB_IMAGE_FORMAT = "web_image_format";
	
	/** 参数名：小区平面图最大分辨率 */
	public static final String PARAM_PLAN_MAX_RESOLUTION = "plan_max_resolution";
	
	/** Bean名称：系统参数服务 */
	public static final String BEAN_NAME_SYS_PARAM_SERVICE = "sysParamService";
	
	/** Bean名称：登录管理服务 */
	public static final String BEAN_NAME_LOGIN_SERVICE = "loginService";
	
	/** 组织编号分隔符 */
	public static final char SEPARATOR_GROUP_ID = ',';
	
	/**操作结果：成功 */
	public static final int RESULT_SUCCESS = 0;
	
	/**操作结果：失败，存在关系数据不允许删除*/
	public static final int RESULT_FAIL_REMOVE = 1;
	
	/**操作结果：失败，系统异常 */
	public static final int RESULT_SYS_EX = -1;
	
	/** 室内机*/
	public static final String INDOOR_DEVICE = "01";
	
	/** 单元门口机*/
	public static final String UNIT_DOOR_DEVICE = "02";
	
	/** 子门口机*/
	public static final String CHILD_DOOR_DEVICE = "03";
	
	/** 管理机*/
	public static final String MANAGEMENT_DEVICE = "04";
	
	/** 门卫机*/
	public static final String GATE_GUARD_DEVICE = "05";
	
	/** 围墙机*/
	public static final String FENCE_DEVICE = "06";
	
	/** 安防*/
	public static final String SECURITY = "0";
	
	/** 家庭控制*/
	public static final String FAMILY_CONTROL = "1";
	
	/** 文件上传结果：成功*/
	public static final String FILE_UPLOAD_SUCCESS = "1";
	
	/** 文件上传结果：文件名重复*/
	public static final String FILE_UPLOAD_NAME_REPEATED = "0";
	
	/** 文件上传结果：失败*/
	public static final String FILE_UPLOAD_FAILURE = "2";
	
	/** 文件上传结果：文件格式错误*/
	public static final String FILE_UPLOAD_FORMAT_ERROR = "3";
	
	/** 投诉类型 :0: 系统投诉(物业公司 投诉 系统xx问题)**/
	public static final String COMPLAINT_SYSTEM = "0";
	
	/** 投诉类型 :1: 业主投诉(业主 投诉 物业xx问题)**/
	public static final String COMPLAINT_OWNER = "1";
	
	/**app接口请求action前缀**/
	public static final String APP_ACTION_PREFIX = "appDataAction_";
	
	/**版本编号**/
	public static final String VERSION_CODE = "version";
	
	/** 设备修改：设备坐标*/
	public final static String DEVICE_LOCATION_COORDINATE = "deviceLocation";
	
	/** 设备修改：设备修改失败*/
	public final static int DEVICE_UPDATE_FLAG_FAIL = 0;
	
	/** 设备修改：设备修改成功*/
	public final static int DEVICE_UPDATE_FLAG_SUCCESS = 1;
	
	/** 设备修改：设备重名，修改失败*/
	public final static int DEVICE_UPDATE_FLAG_NAMEREPEAT = 2;
	
	/** 保存ip段成功标志*/
	public final static int DEVICE_IP_SAVE_SUCCESS = 1;
	
	
	/** 模板页元素行数*/
	public final static int TEMPLATE_ROW_NUMBER = 2;
	
	/** 模板页元素列数*/
	public final static int TEMPLATE_COLUMN_NUMBER = 3;
	
	/** 模板页元素类型：内容*/
	public final static String TEMPLATE_MODULE_TYPE_CONTENT = "content";
	
	/** 模板页元素类型：按钮*/
	public final static String TEMPLATE_MODULE_TYPE_BUTTON = "button";
	
	/** 二维码状态：无效*/
	public final static Integer QRCODE_STATUS_INVALID=0;
	
	/** 二维码状态：有效*/
	public final static Integer QRCODE_STATUS_VALID=1;
	
	/** 二维码状态：申请*/
	public final static Integer QRCODE_STATUS_REQUEST=2;
	
	/** 二维码类型：系统生成*/
	public final static String QRCODE_TYPE_SYSTEM="0";
	
	/** 二维码类型：通话*/
	public final static String QRCODE_TYPE_TALK="1";
	
	/** 二维码类型：开锁*/
	public final static String QRCODE_TYPE_LOCK="2";
	
	/**	敏感词禁止 */
	public final static String WORDS_FORBIDDEN = "forbidden";
	
	/** rest参数：分页*/
	public final static String REST_PARAM_PAGE="page";
	
	/** rest参数：VO对象*/
	public final static String REST_PARAM_VO="vo";
}
