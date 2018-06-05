package com.quhwa.scalehouse.service.scale.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.quhwa.scalehouse.common.model.ResultEntity;
import com.quhwa.scalehouse.service.scale.mapper.BaseMapper;
import com.quhwa.scalehouse.service.scale.mapper.DeviceMapper;
import com.quhwa.scalehouse.service.scale.model.Device;
import com.quhwa.scalehouse.service.scale.model.Person;
import com.quhwa.scalehouse.service.scale.model.Product;
import com.quhwa.scalehouse.service.scale.service.IDeviceService;
import com.quhwa.scalehouse.util.GetPastDateUtils;

@Service
@Transactional
public class DeviceService extends BaseService<Device> implements IDeviceService{

	@Autowired
	private DeviceMapper deviceMapper;
	
	@Override
	public BaseMapper<Device> getBaseMapper() {
		return deviceMapper;
	}

	@Override
	public ResultEntity<String> insertActive(Device record) {
		ResultEntity<String> re=new ResultEntity<String>();
		if(record.getName()==null || record.getType()==null || record.getMac()==null){
			return re;
		}
		List<Device> list=deviceMapper.queryByMac(record.getMac());
		if(list==null || list.size()==0){
			record.setStatus("1");
			int insert=deviceMapper.insertActive(record);
			re.setCode(ResultEntity.SUCCESS);
			re.setMessage(ResultEntity.MESSAGE_SUCCESS);
			re.setData("");
		}else{
			re.setCode(ResultEntity.ALREADY_EXIST);
			re.setMessage("已存在，无法插入");
			re.setData("");
		}
		return re;
	}

	@Override
	public ResultEntity<List<Device>> getAll(String countryCode,String type) {
		ResultEntity<List<Device>> re=new ResultEntity<List<Device>>();
		List<Device> list=deviceMapper.getAll(countryCode,type);
		Long total=deviceMapper.getNewTotal(countryCode,type);
		re.setCode(ResultEntity.SUCCESS);
		re.setMessage(ResultEntity.MESSAGE_SUCCESS);
		re.setData(list);
		re.setTotal(total);
		re.setRows(list);
		return re;
	}

	@Override
	public ResultEntity<List<Product>> getProduct() {
		ResultEntity<List<Product>> re=new ResultEntity<List<Product>>();
		List<Product> list=deviceMapper.getProduct();
		re.setCode(ResultEntity.SUCCESS);
		re.setMessage(ResultEntity.MESSAGE_SUCCESS);
		re.setData(list);
		return re;
	}

	@Override
	public ResultEntity<String> deviceNum(String countryCode,String type) {
		ResultEntity<String> re=new ResultEntity<String>();
		Long deviceNum=deviceMapper.getNewTotal(countryCode,type);
		re.setCode(ResultEntity.SUCCESS);
		re.setMessage(ResultEntity.MESSAGE_SUCCESS);
		re.setTotal(deviceNum);
		return re;
	}

	@Override
	public ResultEntity<String> todayActiveDevN(String countryCode, String type) {
		ResultEntity<String> re=new ResultEntity<String>();
		Long active=deviceMapper.todayActiveDevN(countryCode, type);
		re.setCode(ResultEntity.SUCCESS);
		re.setMessage(ResultEntity.MESSAGE_SUCCESS);
		re.setTotal(active);
		return re;
	}
	
	@Override
	public ResultEntity<List<Long>> getChartsData(int intervals,String countryCode,String type) {
		ResultEntity<List<Long>> re=new ResultEntity<List<Long>>();
		ArrayList<Long> arr=new ArrayList<Long>();
		ArrayList<String> arrayList=GetPastDateUtils.dateArray(intervals);//得到过去intervals天日期数组
		//System.out.println(arrayList);
		for(int i=0;i<arrayList.size();i++){
			arr.add(deviceMapper.getSum(arrayList.get(i),countryCode,type));//获取每天使用的总设备数
		}
		re.setCode(ResultEntity.SUCCESS);
		re.setMessage(ResultEntity.MESSAGE_SUCCESS);
		re.setData(arr);
		return re;
	}
	
	@Override
	public ResultEntity<List<Long>> getDevices(int intervals,String countryCode,String type) {
		ResultEntity<List<Long>> re=new ResultEntity<List<Long>>();
		ArrayList<Long> arr=new ArrayList<Long>();
		ArrayList<String> arrayList=GetPastDateUtils.dateArray(intervals);//得到过去intervals天日期数组
		//System.out.println(arrayList);
		for(int i=0;i<arrayList.size();i++){
			arr.add(deviceMapper.getDevices(arrayList.get(i),countryCode,type));//获取某天之前的总设备数
		}
		re.setCode(ResultEntity.SUCCESS);
		re.setMessage(ResultEntity.MESSAGE_SUCCESS);
		re.setData(arr);
		return re;
	}

}
