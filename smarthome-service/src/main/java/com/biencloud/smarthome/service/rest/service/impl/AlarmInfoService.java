package com.biencloud.smarthome.service.rest.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.jpush.api.JPushClient;

import com.biencloud.smarthome.service.base.mapper.BaseMapper;
import com.biencloud.smarthome.service.base.service.impl.BaseResService;
import com.biencloud.smarthome.service.common.constants.Constants;
import com.biencloud.smarthome.service.common.model.ResultEntity;
import com.biencloud.smarthome.service.rest.mapper.AlarmInfoMapper;
import com.biencloud.smarthome.service.rest.model.AlarmInfo;
import com.biencloud.smarthome.service.rest.model.DeviceAccessories;
import com.biencloud.smarthome.service.rest.model.DeviceConfig;
import com.biencloud.smarthome.service.rest.model.MsgToken;
import com.biencloud.smarthome.service.rest.service.IAlarmInfoService;
import com.biencloud.smarthome.service.rest.service.IDeviceAccessoriesService;
import com.biencloud.smarthome.service.rest.service.IDeviceConfigService;
import com.biencloud.smarthome.service.rest.service.IMsgTokenService;
import com.biencloud.smarthome.service.rest.service.IPushClientService;

@Service
public class AlarmInfoService extends BaseResService<AlarmInfo> implements
		IAlarmInfoService {

	@Autowired
	private AlarmInfoMapper alarmInfoMapper;
	
	@Autowired
	private IDeviceAccessoriesService deviceAccessoriesService;
	
	@Autowired
	private IMsgTokenService msgTokenService;
	
	@Autowired
	private IPushClientService pushClientService;

	@Autowired
	private IDeviceConfigService deviceConfigService;
	
	@Override
	public BaseMapper<AlarmInfo> getBaseMapper() {
		return alarmInfoMapper;
	}

	@Override
	public ResultEntity<String> updateForResultEntity(AlarmInfo alarmInfo){
		if(alarmInfo!=null){
			alarmInfo.setHanlderTime(new Date());
		}
		return super.updateForResultEntity(alarmInfo);
		
	}
	
	@Override
	public ResultEntity<String> addForResultEntity(AlarmInfo alarmInfo){
		if(alarmInfo!=null){
			alarmInfo.setCreatedTime(new Date());
		}
		//推送给客户端
		pushAlarmToClient(alarmInfo);
		return super.addForResultEntity(alarmInfo);
	}

	@Override
	public void pushAlarmToClient(AlarmInfo alarmInfo){
		if(alarmInfo != null){
			DeviceAccessories da = deviceAccessoriesService.getForOne(Integer.parseInt(alarmInfo.getDeviceCode()));
			//主设备的ID
			if(da!=null){
				int pushSoundType = checkAccessoriesType(da.getType());
				Long deviceId = da.getDeviceId();
				List<MsgToken> list = msgTokenService.getListByDeviceId(deviceId);
				if(list!=null && list.size()>0){
					List<String> tokensAndroidJpush = new ArrayList<String>();
					List<String> tokensAndroidJpush1byone = new ArrayList<String>();
					List<String> tokensAndroidGcm = new ArrayList<String>();
					List<String> tokensIosApns = new ArrayList<String>();
					List<String> tokensIosApns1byone = new ArrayList<String>();
					for(MsgToken token : list){
						if(MsgToken.TYPE_ANDROID_JPUSH.equals(token.getType())){
							tokensAndroidJpush.add(token.getToken());
						}
						if(MsgToken.TYPE_ANDROID_JPUSH_1BYONE.equals(token.getType())){
							tokensAndroidJpush1byone.add(token.getToken());
						}
						if(MsgToken.TYPE_ANDROID_GCM.equals(token.getType())){
							tokensAndroidGcm.add(token.getToken());
						}
						if(MsgToken.TYPE_IOS_APNS.equals(token.getType())){
							tokensIosApns.add(token.getToken());
						}
						if(MsgToken.TYPE_IOS_APNS_1BYONE.equals(token.getType())){
							tokensIosApns1byone.add(token.getToken());
						}
					}
					
					//String title = Constants.PUSH_TITLE;

					StringBuilder sb = new StringBuilder();
					sb.append("[");
					sb.append(da.getName());
					sb.append("] received an alarm!");
					String sound = null;
					//判断推送铃声是不是自定义
					ResultEntity<DeviceConfig> dcResult= deviceConfigService.getByDeviceId(deviceId);
					if(dcResult.getCode()==ResultEntity.SUCCESS){
						DeviceConfig deviceConfig = dcResult.getData();
						//若推送铃声是自定义，则将推送铃声换为默认自定义
						if(deviceConfig!=null && Constants.PUSH_SOUND_CUSTOM.equals(deviceConfig.getPushSound())){
							if(pushSoundType == 1){
								sound = Constants.PUSH_SOUND_DOORBELL;
							}
							if(pushSoundType == 2){
								sound = Constants.PUSH_SOUND_ALARM;
							}
						}
					}
					if(tokensAndroidJpush.size()>0){
						JPushClient jpushClient = new JPushClient(Constants.PUSH_MASTER_SECRET_JPUSH,Constants.PUSH_APP_KEY_JPUSH);
						pushClientService.sendAndroidJpush(sb.toString(),Constants.PUSH_TITLE,sound,tokensAndroidJpush,jpushClient);
					}
					if(tokensAndroidJpush1byone.size()>0){
						JPushClient jpushClient = new JPushClient(Constants.PUSH_MASTER_SECRET_JPUSH_1BYONE,Constants.PUSH_APP_KEY_JPUSH_1BYONE);
						pushClientService.sendAndroidJpush(sb.toString(),Constants.PUSH_TITLE_1BYONE,sound,tokensAndroidJpush1byone,jpushClient);
					}
					if(tokensIosApns.size()>0){
						AlarmInfo ai = new AlarmInfo();
						ai.setDeviceCode(alarmInfo.getDeviceCode());
						ai.setAlarmType(alarmInfo.getAlarmType());
						ai.setStatus(AlarmInfo.HANLDER_STATUS_NO+"");
						//未处理记录数
						int badge = alarmInfoMapper.getCount(ai)+1;
						pushClientService.sendIosPush(sb.toString(), sound, tokensIosApns, badge);
					}
					if(tokensIosApns1byone.size()>0){
						AlarmInfo ai = new AlarmInfo();
						ai.setDeviceCode(alarmInfo.getDeviceCode());
						ai.setAlarmType(alarmInfo.getAlarmType());
						ai.setStatus(AlarmInfo.HANLDER_STATUS_NO+"");
						//未处理记录数
						int badge = alarmInfoMapper.getCount(ai)+1;
						pushClientService.sendIosPush1byone(sb.toString(), sound, tokensIosApns1byone, badge);
					}
				}
			}
		}
		
	}

	@Override
	public ResultEntity<String> deleteAlarms(String ids) {
		ResultEntity<String> re = new ResultEntity<String>();
		if(ids==null || "".equals(ids)){
			return re;
		}
		String[] alarmIdArrays = ids.split(",");
		if(alarmIdArrays!=null && alarmIdArrays.length>0){
			for(String id : alarmIdArrays){
				Integer alarmId = Integer.parseInt(id);
				alarmInfoMapper.delete(alarmId);
			}
		}
		re.setCode(ResultEntity.SUCCESS);
		return re;
	}
	
	/***
	 * 检查配件类型
	 * @param type
	 * @return 0系统默认声音，1门铃声，2报警声
	 */
	private int checkAccessoriesType(String type){
		int result = 0;
		if(!StringUtils.isBlank(type)){
			//doorbell
			if("1".equals(type)){
				result = 1;
			}
			//PIR
			if("2".equals(type) || "3".equals(type) || "8".equals(type)){
				result = 2;
			}
		}
		return result;
	}
	
}  
