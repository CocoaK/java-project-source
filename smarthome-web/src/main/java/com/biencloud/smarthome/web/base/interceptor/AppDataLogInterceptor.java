package com.biencloud.smarthome.web.base.interceptor;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import com.biencloud.smarthome.web.appdata.action.AppDataAction;
import com.biencloud.smarthome.web.base.action.BaseAction;
import com.biencloud.smarthome.web.common.util.Constants;
import com.biencloud.smarthome.web.common.util.JsonUtil;
import com.biencloud.smarthome.web.device.service.IDeviceService;
import com.biencloud.smarthome.web.device.vo.DeviceVO;
import com.biencloud.smarthome.web.log.service.IAppDataLogService;
import com.biencloud.smarthome.web.log.vo.AppDataLogVO;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;

/**
 * 
 * 类名称：AppDataLogInterceptor 
 * 类描述： APP请求日志记录拦截器。
 * @author: ykou  
 * @version: 0.1
 * @date: 2012-10-19 下午3:31:54
 */
@SuppressWarnings({"serial", "rawtypes"})
public class AppDataLogInterceptor extends MethodFilterInterceptor {

	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	private Set<String> errorResults;
	
	public Set<String> getErrorResults() {
		return errorResults;
	}

	public void setErrorResults(String errorResults) {
		this.errorResults = new HashSet<String>();
		if(StringUtils.isNotBlank(errorResults)){
			String[] ers = StringUtils.split(errorResults, ',');
			for (String er : ers) {
				this.errorResults.add(er);
			}
		}
	}
		
	@Override
	protected String doIntercept(ActionInvocation actionInvocation) throws Exception {
		Object action = actionInvocation.getAction();
		HttpServletRequest request = ServletActionContext.getRequest();
		String result = null;
		Exception ex = null;
		String resData = null;
		int operationResult = Constants.LOG_SUCCESS;
		//如果是APP请求的动作才做处理。
		if(actionInvocation.getInvocationContext()!=null 
				&& actionInvocation.getInvocationContext().getName() !=null 
				&& actionInvocation.getInvocationContext().getName().startsWith(Constants.APP_ACTION_PREFIX)){
			AppDataAction ba = (AppDataAction)action;
			try {
				result = actionInvocation.invoke();
			} catch (Exception e) {
				ex = e;
				operationResult = Constants.LOG_EXCEPTION;
				//记录系统异常日志
				logger.error("********************调用{}发生异常:{}", new Object[]{action, e});			
			}
			
			if(!(action instanceof BaseAction)){//忽略记录操作日志
				if(ex != null)
					throw ex;
				return result;
			}
			//获取resData返回数据
			try {
				Class clazz = AppDataAction.class;//获得Class对象
				Field[] fields = clazz.getDeclaredFields();//根据Class对象获得属性 私有的也可以获得
				for(Field f : fields) {
					Object obj = getFieldValueByName(f.getName(),ba);
					//如果属性不是接口,且属性不是整数和字符串，且属性不为空，则返回数据为此属性
					if(!f.getType().isInterface() && obj!=null && 
							!obj.getClass().isAssignableFrom(Integer.class) && !obj.getClass().isAssignableFrom(String.class)){
						//System.out.println("***********f.getName():"+f.getName());
						resData = JsonUtil.objectToJsonString(obj);
						//System.out.println("***********resData:"+resData);
					}
				}
			} catch(Exception e) {
				e.printStackTrace();
			}
			
			if(!ba.isIgnoreLog()){
				recordAppDataLog(actionInvocation, ba,request,resData, 
						operationResult, result, ex);
			}
				
			if(ex != null)
				throw ex;
		}
		return result;
	}

	//记录App请求数据日志
	private void recordAppDataLog(ActionInvocation actionInvocation,
			AppDataAction ba,HttpServletRequest request,String resData, int operationResult, String result,
			Exception ex) {
		if(ba.getErrorResults() != null 
				&& !ba.getErrorResults().isEmpty()){
			//Action定义错误结果，则覆盖拦截器默认的错误结果
			
			if(ba.getErrorResults().contains(result))
				operationResult = Constants.LOG_FAILURE_RESULT;
		}else{//Action未定义错误结果，则使用拦截器默认的错误结果
			if(errorResults!=null && errorResults.contains(result))
				operationResult = Constants.LOG_FAILURE_RESULT;
		}
		//设备编号
		String deviceNo = getDeviceNoFromParams(request);
		//设备名称
		String deviceName = null;
		//mac地址
		String mac = null;
		//完整房号
		StringBuffer fullRoomNo = new StringBuffer();
		//位置
		StringBuffer position = new StringBuffer();
		DeviceVO deviceVO = new DeviceVO();
		IDeviceService deviceService = getDeviceService(actionInvocation.getInvocationContext());
		if(deviceService != null){
			deviceVO = deviceService.queryDeviceByCode(deviceNo);
		}
		if(deviceVO!=null){
			deviceName = deviceVO.getDeviceName();
			mac = deviceVO.getDeviceMac();
			fullRoomNo.append(deviceVO.getHousingDistrictInfo().getCode());
			position.append(deviceVO.getHousingDistrictInfo().getName());
			if(deviceVO.getHousingDistrictRegionInfo()!=null){
				fullRoomNo.append("-"+deviceVO.getHousingDistrictRegionInfo().getCode());
				position.append("-"+deviceVO.getHousingDistrictRegionInfo().getName());
			}
			if(deviceVO.getRegionBuildingInfo()!=null){
				fullRoomNo.append("-"+deviceVO.getRegionBuildingInfo().getCode());
				position.append("-"+deviceVO.getRegionBuildingInfo().getName());
			}
			if(deviceVO.getBuildingCellInfo()!=null){
				fullRoomNo.append("-"+deviceVO.getBuildingCellInfo().getCode());
				position.append("-"+deviceVO.getBuildingCellInfo().getName());
			}
			if(deviceVO.getCellHouseholdInfo()!=null){
				fullRoomNo.append("-"+deviceVO.getCellHouseholdInfo().getCode());
				position.append("-"+deviceVO.getCellHouseholdInfo().getName());
			}
		}
		//创建APP日志实体，给属性赋值
		AppDataLogVO appDataLogVO = new AppDataLogVO();
		appDataLogVO.setAction(result);
		appDataLogVO.setDeviceName(deviceName);
		appDataLogVO.setDeviceNo(getDeviceNoFromParams(request));
		appDataLogVO.setFullRoomNo(fullRoomNo.toString());
		appDataLogVO.setIp(ba.getIp());
		appDataLogVO.setMac(mac);
		appDataLogVO.setPosition(position.toString());
		appDataLogVO.setRequestData(getRequestParams(request));
		appDataLogVO.setResponseData(resData);
		appDataLogVO.setResult(((Integer)operationResult).toString());
		appDataLogVO.setResultDesc(getMsg(operationResult, ex));
			saveAppDataLog(actionInvocation, appDataLogVO);
	}


	//保存APP请求日志，失败不影响当前操作
	private void saveAppDataLog(ActionInvocation actionInvocation,
			AppDataLogVO appDataLogVO) {
		IAppDataLogService appDataLogService = getAppDataLogService(
				actionInvocation.getInvocationContext());
		
		if(appDataLogService != null){
			try {
				appDataLogService.saveOrUpdateAppDataLog(appDataLogVO);
			} catch (Exception e) {
				logger.error("********************记录操作日志{}发生异常:{}", new Object[]{appDataLogVO, e});
			}
		}
	}

	//获取app请求日志服务
	private IAppDataLogService getAppDataLogService(ActionContext ac){
		IAppDataLogService appDataLogService = null;
		try {
			ServletContext sc = (ServletContext) ac.get(ServletActionContext.SERVLET_CONTEXT);
			WebApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(sc);
			appDataLogService = ctx.getBean("appDataLogService", IAppDataLogService.class);
		} catch (Exception e) {
			logger.error("********************获取{}发生异常:{}", new Object[]{IAppDataLogService.class, e});
		}
		return appDataLogService;
	}
	
	//获取设备服务
		private IDeviceService getDeviceService(ActionContext ac){
			IDeviceService deviceService = null;
			try {
				ServletContext sc = (ServletContext) ac.get(ServletActionContext.SERVLET_CONTEXT);
				WebApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(sc);
				deviceService = ctx.getBean("deviceService", IDeviceService.class);
			} catch (Exception e) {
				logger.error("********************获取{}发生异常:{}", new Object[]{IDeviceService.class, e});
			}
			return deviceService;
		}
	
	//获取日志消息
	private String getMsg(int result, Exception e){
		String msg = null;
		if(result == Constants.LOG_EXCEPTION){
			msg = "请求结果->发生异常";
			if(e != null)
				msg = e.getMessage();
		}
		return msg;
	}
	
	//获取请求参数
	public String getRequestParams(HttpServletRequest request){
		Map map = new HashMap();
		Set set = new HashSet();
		StringBuffer sb = new StringBuffer();
		Object ob = null;
		set = request.getParameterMap().keySet();
		if(set.iterator().hasNext()){
			ob = set.iterator().next();
			map = request.getParameterMap();
			String[] params = (String[]) map.get(ob);
			if(params != null && params.length != 0){
				sb.append(ob+"="+params[0]+";");
			}
		}
		return sb.toString();	
	}
	
	//获取某个类中的某个属性的值
	private Object getFieldValueByName(String fieldName, Object o) {
		try {    
			String firstLetter = fieldName.substring(0, 1).toUpperCase();    
	        String getter = "get" + firstLetter + fieldName.substring(1);    
	        Method method = o.getClass().getMethod(getter, new Class[] {});    
	        Object value = method.invoke(o, new Object[] {});    
	        return value;    
		} catch (Exception e) {    
			e.printStackTrace();   
			return null;    
		} 
	}
	//获取设备编号参数
	public String getDeviceNoFromParams(HttpServletRequest request){
		String deviceNo = "";
		Map map = request.getParameterMap();
		Set set = request.getParameterMap().keySet();
		Object obj = null;
		for(Object o : set){
			if(o.toString().contains("deviceCode")){
				obj = o;
			}else if(o.toString().contains("deviceNo")){
				obj = o;
			}
		}
		String[] paramDeviceNo = (String[]) map.get(obj);
		String[] paramJsonString = (String[]) map.get("jsonString");
		if(paramDeviceNo != null && paramDeviceNo.length != 0){
			deviceNo = paramDeviceNo[0];
		}else if(paramJsonString != null && paramJsonString.length != 0){
			JSONObject jsonOb = JsonUtil.jsonStringToJsonObject(paramJsonString[0]);
			//不确定设备编号在json中的键是deviceNo还是deviceCode，或者没有，做如下处理
			try{
				deviceNo = jsonOb.getString("deviceNo");
			}catch(Exception e){
				//如果json中没有deviceNo键，捕获异常,获取deviceCode
				try{
					deviceNo = jsonOb.getString("deviceCode");
				}catch(Exception ex){
					//如果json中没有deviceCode键，捕获异常
					System.out.println(e.getMessage());
				}
			}
		}
		return deviceNo;
	}
}
