package com.smarthome.socket.service.business.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.client.RestTemplate;
import com.smarthome.socket.common.constant.Constant;
import com.smarthome.socket.common.model.ResultEntity;
import com.smarthome.socket.service.business.service.BaseRestService;
import com.smarthome.socket.service.business.service.IDeviceAccessoriesService;
import com.smarthome.socket.service.vo.DeviceAccessories;

public class DeviceAccessoriesService extends BaseRestService<DeviceAccessories> implements IDeviceAccessoriesService {

	@Autowired
	private RestTemplate restTemplate;
	
	@Override
	public RestTemplate getRestTemplate() {
		return restTemplate;
	}
	
	@Override
	public ResultEntity<String> save(String data, String mac) {
		ResultEntity<String> re = new ResultEntity<String>();
		try{
			DeviceAccessories da = new DeviceAccessories();
			if(StringUtils.isNotBlank(data) && data.startsWith(Constant.CMD_ACCESSORIES_ADD_PREFIX) && data.endsWith(Constant.CMD_ACCESSORIES_ADD_END)){
				String code = data.substring(data.length()-10,data.length()-4);
				da = this.buildDeviceAccessories(code);
				ResultEntity<Long> result = getDeviceIdByDeviceNo(mac.replace(":",""));
				if(result == null || result.getData()==null){
					return re;
				}
				Long deviceId = result.getData();
				da.setDeviceId(deviceId);
			}
			super.setCreateUrl("/device/accessories/create");
			re = super.postForObject(restServiceUrl+createUrl, da, new ParameterizedTypeReference<ResultEntity<String>>(){});
		}catch(Exception e){
			System.out.println("save accessorise exception:"+e.getMessage());
			return new ResultEntity<String>();
		}
		return re;
	}

	@Override
	public ResultEntity<Long> getDeviceIdByDeviceNo(String deviceNo) throws Exception {
		super.setQueryUrl("/device/getId");
		Map<String,String> map = new HashMap<String,String>();
		map.put("deviceNo", deviceNo);
		ResultEntity<Long> re = super.postForObject(restServiceUrl+queryUrl, map, new ParameterizedTypeReference<ResultEntity<Long>>(){});
		return re;
	}

	@Override
	public ResultEntity<String> refresh(String data, String mac) {
		ResultEntity<String> re = new ResultEntity<String>();
		List<DeviceAccessories> das = new ArrayList<DeviceAccessories>();
		try{
			ResultEntity<Long> result = getDeviceIdByDeviceNo(mac.replace(":",""));
			if(result == null || result.getData()==null){
				return re;
			}
			Long deviceId = result.getData();
			List<String> list = this.refreshAccessories(data);
			if(list == null){
				return re;
			}
			if(list.size()==0){
				//清除数据库中此设备关联的配件
				DeviceAccessories d = new DeviceAccessories();
				d.setDeviceId(deviceId);
				super.setDeleteUrl("/device/accessories/clear");
				re = super.postForObject(restServiceUrl+deleteUrl, d, new ParameterizedTypeReference<ResultEntity<String>>(){});
				return re;
			}
			for(String str : list){
				String code = str.substring(2,str.length());
				DeviceAccessories da = this.buildDeviceAccessories(code);
				if(da!=null){
					da.setDeviceId(deviceId);
					das.add(da);	
				}
			}
			super.setCreateUrl("/device/accessories/addList");
			re = super.postForObject(restServiceUrl+createUrl, das, new ParameterizedTypeReference<ResultEntity<String>>(){});
		}catch(Exception e){
			System.out.println("save accessorise exception:"+e.getMessage());
			return new ResultEntity<String>();
		}
		return re;
	}
	
	private DeviceAccessories buildDeviceAccessories(String code){
		DeviceAccessories da = new DeviceAccessories();
		if("000000".equals(code)){
			return null;
		}
		String temp = code.substring(code.length()-1,code.length());
		int s = Integer.parseInt(temp, 16);
		String type = null;
		String name = null;
		String defenceArea = null;
		String f = Integer.toBinaryString(s);
		//门铃
		if("1111".equals(f)){
			type="1";
			name="Doorbell";
			//name = "门铃";
		}else
		//门铃
		if("10".equals(f)){
			type="1";
			name="Doorbell";
			//name = "门铃";
		}else
		//PIR
		if("1011".equals(f)){
			type="2";
			name="PIR";
			//name = "PIR";
		}else
		//泳池
		if("111".equals(f)){
			type="8";
			name="Door sensor";
			//name = "门磁";
		}else
		//车库
		if("1101".equals(f)){
			type="3";
			name="Door sensor";
			//name = "有线门磁";
		}else
		//气体
		if("1001".equals(f)){
			type="7";
			name="CO";
			//name="气体";
		}else
		//烟雾
		if("101".equals(f)){
			type="6";
			name="Smoke detector";
			//name="烟感";
		}else
		//遥控器
		if("11".equals(f)){
			type="9";
			name="SOS";
			defenceArea = "2";
			
		    //name="遥控器";
		}else{
			type="0";
			name="noname";	//未命名
		}
//		ResultEntity<Long> result = getDeviceIdByDeviceNo(mac.replace(":",""));
//		if(result == null || result.getData()==null){
//			return re;
//		}
//		Long deviceId = result.getData();
//		da.setDeviceId(deviceId);
		da.setStatus(Constant.ACCESSORIES_STATUS_NEW);
		da.setType(type);
		da.setCode(code);
		da.setName(name);
		if(defenceArea==null){
			//默认普通防区
			da.setDefenceArea("0");
		}
		return da;
	}
	
	private List<String> refreshAccessories(String data){
//		data格式示例：
//		String data = "5c0455ff0003249f0001081700033d5f00014b020000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000002c88"
//				 +"5c0455ff0003249f0001081700033d5f00014b020000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000002c88"
//				 +"5c0400ff0003249f000108170003ed88";
		List<String> li = new ArrayList<String>();
		if(data==null || "".equals(data)){
			return null;
		}
		if(data.length()<332){
			return null;
		}
		//每条返回数据前缀
		String prefix = "5c0455ff";
		//结束指令
		String flag = "5c0400ff";
		//后缀
//		if(data.startsWith(prefix)){
//			data = data.substring(prefix.length(),data.indexOf(flag));
//			String[] str = data.split(prefix);
//			for(int i=0;i<str.length;i++){
//				str[i] = str[i].substring(0,str[i].length()-4);
//				//字符串按照长度8来分段
//				if(str[i].length()%8==0){
//					for(int j=0;j<str[i].length()/8;j++){
//						String temp = str[i].substring(j*8,j*8+8);
//						if(!"00000000".equals(temp)){
//							li.add(temp);
//						}
//					}
//				}
//			}
//		}
		if(data.startsWith(prefix)){
			data = data.substring(prefix.length(),data.indexOf(flag)-4);
			//字符串按照长度8来分段
			if(data.length()%8==0){
				for(int j=0;j<data.length()/8;j++){
					String temp = data.substring(j*8,j*8+8);
					if(!"00000000".equals(temp)){
						li.add(temp);
					}
				}
			}
		}
		List<String> list = new ArrayList<String>(new HashSet<String>(li));
		return list;
	}

	@Override
	public ResultEntity<String> clear(String mac) {
		ResultEntity<String> re = new ResultEntity<String>();
		try{
			ResultEntity<Long> result = getDeviceIdByDeviceNo(mac.replace(":",""));
			if(result == null || result.getData()==null){
				return re;
			}
			Long deviceId = result.getData();
			DeviceAccessories d = new DeviceAccessories();
			d.setDeviceId(deviceId);
			super.setDeleteUrl("/device/accessories/clear");
			re = super.postForObject(restServiceUrl+deleteUrl, d, new ParameterizedTypeReference<ResultEntity<String>>(){});
		}catch(Exception e){
			System.out.println("clear accessorise exception:"+e.getMessage());
			return new ResultEntity<String>();
		}
		return re;
	}
}
