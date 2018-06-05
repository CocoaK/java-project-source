package com.biencloud.smarthome.service.common.constants;

/**
 * 
 * 类名称：AppConstants 类描述： app常量类
 * 
 * @author: kouy
 * @version: 0.1
 * @date: 2012-7-10 下午9:40:20
 */
public class AppConstants {

	// 小区不存在
	public static final String AREA_NOT_EXIST = "2";
	// 小区区域不存在
	public static final String AREA_REGION_NOT_EXIST = "3";
	// 楼宇不存在
	public static final String AREA_BUILDING_NOT_EXIST = "4";
	// 单元不存在
	public static final String AREA_UNIT_NOT_EXIST = "5";
	// 房号不存在
	public static final String AREA_ROOM_NOT_EXIST = "6";
	// 设备类型不存在
	public static final String DEVICE_TYPE_NOT_EXIST = "7";
	// 设备名称重复
	public static final String DEVICE_NAME_REPEAT = "8";
	// 设备IP重复
	public static final String DEVICE_IP_REPEAT = "9";
	// 设备位置重复
	public static final String DEVICE_POSITION_REPEAT = "10";
	// 设备位置没有输入
	public static final String DEVICE_POSITION_NOT_EXIST = "11";
	// 设备房号没有输入
	public static final String DEVICE_HOUSE_NUM_NOT_EXIST = "12";
	// 设备归属发生变化，比如被换到别的小区或别的家庭
	// public static final String DEVICE_BELONG_CGANGE = "13";

	// 设备类型与输入的房号信息不符
	// public static final String DEVICE_TYPE_ERROR = "14";
	// 设备IP不存在
	public static final String DEVICE_IP_NOT_EXIST = "13";
	// 房号已存在设备,不允许再注册设备
	public static final String HOUSE_BE_TAKED = "14";
	// 房号和ip同时冲突
	public static final String HOUSE_AND_IP_BE_TAKED = "15";
	// 室内机
	public static final String DEVICE_TYPE_INDOOR = "01";
	// 单元机
	public static final String DEVICE_TYPE_UNIT = "02";
	// 子门口机
	public static final String DEVICE_TYPE_CHILD = "03";
	// 管理机
	public static final String DEVICE_TYPE_MANAGE = "04";
	// 门卫机
	public static final String DEVICE_TYPE_GUARD = "05";
	// 围墙机
	public static final String DEVICE_TYPE_FENCE = "06";
	
	// WIFI模块
	public static final String DEVICE_TYPE_WIFI = "20";
		
	// 成功
	public static final String SUCCESSFULL = "1";
	// 失败
	public static final String FAILURE = "0";
	// 在线
	public static final String ONLINE = "1";
	// 离线
	public static final String OUTLINE = "0";
	// 区域
	public static final String REGION = "region";
	// 栋
	public static final String BUILDING = "building";
	// 单元
	public static final String UNIT = "unit";
	// 房号
	public static final String HOUSE = "house";
	// 数据服务器参数名称
	public static final String DATA_SERVER_IP = "dataServerIP";
	// 文件服务器参数名称
	public static final String FILE_SERVER_IP = "fileServerIP";
	// tcp服务器参数名称
	public static final String SOCKET_SERVER_IP = "socketServerIP";

	// 第一次注册
	public static final String APP_FIRST_REGIST = "add";

	// 非第一次注册
	public static final String APP_NOT_FIRST_REGIST = "update";

	// ip冲突，强制占用该ip
	public static final String FORCE_TAKE_IP = "1";

	// 房号冲突，强制占用该房号
	public static final String FORCE_TAKE_HOUSENO = "1";

	// ip被使用
	public static final String IP_USED = "1";

	// 同一个房号下是否允许存在多个设备,y表示允许,n表示不允许
	public static final String allowManyDevicesInHouse = "y";

	// 同一个房号下是否允许存在多个设备,y表示允许,n表示不允许
	public static final String notAllowManyDevicesInHouse = "n";

}
