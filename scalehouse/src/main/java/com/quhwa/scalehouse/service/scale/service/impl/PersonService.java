package com.quhwa.scalehouse.service.scale.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.quhwa.scalehouse.common.model.Constants;
import com.quhwa.scalehouse.common.model.ResultEntity;
import com.quhwa.scalehouse.common.utils.CryptoUtils;
import com.quhwa.scalehouse.common.utils.StringUtils;
import com.quhwa.scalehouse.service.scale.mapper.BaseMapper;
import com.quhwa.scalehouse.service.scale.mapper.PersonMapper;
import com.quhwa.scalehouse.service.scale.model.Device;
import com.quhwa.scalehouse.service.scale.model.Person;
import com.quhwa.scalehouse.service.scale.service.IEmailClientService;
import com.quhwa.scalehouse.service.scale.service.IPersonService;
import com.quhwa.scalehouse.util.GetPastDateUtils;


@Service
@Transactional
public class PersonService extends BaseService<Person> implements IPersonService{
	@Autowired
	private PersonMapper personMapper;
	
	@Autowired
	private HttpServletRequest request;  
	
	@Autowired
	private IEmailClientService emailClientService;
	
	@Override
	public BaseMapper<Person> getBaseMapper() {
		// TODO Auto-generated method stub
		return personMapper;
	}
	
	@Override
	public ResultEntity<String> delete(Integer id) {
		ResultEntity<String> re=new ResultEntity<String>();
		int per=personMapper.delete(id);
		if(per>0){
			re.setCode(ResultEntity.SUCCESS);
			re.setMessage(ResultEntity.MESSAGE_SUCCESS);
			re.setData("");
		}else{
			re.setMessage(ResultEntity.MESSAGE_FAILED);
			re.setData("");
		}
		return re;
	}
	
	@Override
	public ResultEntity<String> deleteGroup(String[] ids) {
		ResultEntity<String> re=new ResultEntity<String>();
		if (ids==null || ids.length == 0){
			return re;
        }else{
        	for (String idStr : ids){
        		int id = new Integer(idStr);
        		personMapper.delete(id);
        	}
        	re.setCode(ResultEntity.SUCCESS);
        	re.setMessage(ResultEntity.MESSAGE_SUCCESS);
        	re.setData("");
        }
		return re;
	}

	@Override
	public ResultEntity<String> insert(Person record) {
		ResultEntity<String> re = new ResultEntity<String>();
		if(record!=null && !"".equals(record)){
			int u=personMapper.insert(record);
			if(u>0){
				re.setCode(ResultEntity.SUCCESS);
				re.setMessage(ResultEntity.MESSAGE_SUCCESS);
				re.setData("");
			}else{
				re.setMessage(ResultEntity.MESSAGE_FAILED);
				re.setData("");
			}
		}
		return re;
	}
	
	@Override
	public ResultEntity<String> insertActive(Person record) {
		ResultEntity<String> re = new ResultEntity<String>();
		if(record.getAccount()==null || "".equals(record.getAccount()) || record.getPassword()==null || "".equals(record.getPassword()) || "".equals(record.getRecordTime()) || record.getRecordTime()==null){
			re.setCode(ResultEntity.INPUT_IS_NULL);
			re.setMessage("输入值不能为空");
			return re;
		}
		Person p=personMapper.queryByAccount(record.getAccount());
		if(p==null){
//			String password=CryptoUtils.encodeByMD5(record.getPassword());
//			record.setPassword(password);
			int u=personMapper.insertActive(record);
			if(u>0){
				re.setCode(ResultEntity.SUCCESS);
				re.setMessage(ResultEntity.MESSAGE_SUCCESS);
				re.setData("");
			}else{
				re.setMessage(ResultEntity.MESSAGE_FAILED);
				re.setData("");
			}
		}else{
			re.setCode(ResultEntity.ALREADY_EXIST);
			re.setMessage("用户已存在，无法注册！");
			re.setData("");
		}
		return re;
	}

	@Override
	public ResultEntity<Person> getOne(Integer id) {
		ResultEntity<Person> re = new ResultEntity<Person>();
		Person p=personMapper.getOne(id);
		if(p!=null){
			re.setCode(ResultEntity.SUCCESS);
			re.setMessage(ResultEntity.MESSAGE_SUCCESS);
			re.setData(p);
		}else{
			re.setMessage(ResultEntity.MESSAGE_FAILED);
		}
		return re;
	}

	@Override
	public ResultEntity<String> update(Person record) {
		ResultEntity<String> re = new ResultEntity<String>();
		int u=personMapper.update(record);
		if(u>0){
			re.setCode(ResultEntity.SUCCESS);
			re.setMessage(ResultEntity.MESSAGE_SUCCESS);
			re.setData("");
		}else{
			re.setMessage(ResultEntity.MESSAGE_FAILED);
			re.setData("");
		}
		return re;
	}

	@Override
	public ResultEntity<List<Person>> getAll(String countryCode,String type) {
		ResultEntity<List<Person>> re=new ResultEntity<List<Person>>();
		Long total=personMapper.getNewTotal(countryCode,type);
		List<Person> per=personMapper.getAll(countryCode,type);
		if(per!=null){
			re.setCode(ResultEntity.SUCCESS);
			re.setMessage(ResultEntity.MESSAGE_SUCCESS);
			re.setData(per);
			re.setTotal(total);
			re.setRows(per);
		}else{
			re.setMessage(ResultEntity.MESSAGE_FAILED);
		}
		return re;
	}

	@Override
	public ResultEntity<String> updateActive(Person record) {
		ResultEntity<String> re = new ResultEntity<String>();
//		String password=CryptoUtils.encodeByMD5(record.getPassword());
//		record.setPassword(password);
		if(record.getHeight()==null || "".equals(record.getHeight()) || record.getBirthday()==null || "".equals(record.getBirthday()) || record.getGender()==null || "".equals(record.getGender())
				|| record.getTargetWeight()==null || "".equals(record.getTargetWeight()) || record.getHeightUnit()==null || "".equals(record.getHeightUnit()) || record.getWeightUnit()==null || "".equals(record.getWeightUnit())
				|| record.getUsername()==null || "".equals(record.getUsername())){
			re.setCode(ResultEntity.INPUT_IS_NULL);
			re.setMessage("输入值不能为空");
			return re;
		}
		Person per=new Person();
		per.setAccount(record.getAccount());
		List<Person> p=personMapper.queryForList(per);
		if(p!=null && p.size()>0){
			int u=personMapper.updateActive(record);
			if(u>0){
				re.setCode(ResultEntity.SUCCESS);
				re.setMessage(ResultEntity.MESSAGE_SUCCESS);
				re.setData("");
			}else{
				re.setMessage(ResultEntity.MESSAGE_FAILED);
				re.setData("");
			}
		}else{
			re.setCode(ResultEntity.ACCOUNT_NOT_EXIST);
			re.setMessage("用户不存在，无法更新！");
			re.setData("");
		}
		return re;
	}
	
	@Override
	public ResultEntity<String> updateEasyui(Person record) {
		ResultEntity<String> re = new ResultEntity<String>();
		Person p=personMapper.queryByAccountAndPassword(record);
		if(p!=null){
			int u=personMapper.updateActive(record);
			if(u>0){
				re.setCode(ResultEntity.SUCCESS);
				re.setMessage(ResultEntity.MESSAGE_SUCCESS);
				re.setData("");
			}else{
				re.setMessage(ResultEntity.MESSAGE_FAILED);
				re.setData("");
			}
		}else{
			re.setCode(ResultEntity.NOT_EXIST);
			re.setMessage("用户不存在，无法更新！");
			re.setData("");
		}
		return re;
	}

	@Override
	public ResultEntity<Person> queryByAccountAndPassword(Person record) {
		ResultEntity<Person> re = new ResultEntity<Person>();
//		String password=CryptoUtils.encodeByMD5(record.getPassword());
//		record.setPassword(password);
		if(record.getAccount()==null || "".equals(record.getAccount()) || record.getPassword()==null || "".equals(record.getPassword())){
			re.setCode(ResultEntity.ACCOUNT_OR_PASSWD_IS_NULL);
			re.setMessage("账户和密码不能为空");
			return re;
		}else if(personMapper.queryByAccount(record.getAccount())==null){
			re.setCode(ResultEntity.ACCOUNT_NOT_EXIST);
			re.setMessage("账户不存在");
			return re;
		}
		Person p=personMapper.queryByAccountAndPassword(record);
		if(p!=null){
			re.setCode(ResultEntity.SUCCESS);
			re.setMessage(ResultEntity.MESSAGE_SUCCESS);
			re.setData(p);
		}else{
			re.setCode(ResultEntity.ACCOUNT_OR_PASSWD_ERROR);
			re.setMessage("密码错误");
		}
		return re;
	}
	
	
	@Override
	public ResultEntity<String> toResetPwd(String account) {
		ResultEntity<String> re = new ResultEntity<String>(ResultEntity.FAILD,"","");
		if (account==null || account.equals("")) {
			re.setCode(ResultEntity.INPUT_IS_NULL);
			re.setMessage("账户不能为空");
            return re;
        }
		Person p = new Person();
		p.setAccount(account);
		List<Person> persons = personMapper.getForList(p);
		if(persons==null || persons.size()==0){
			re.setCode(ResultEntity.ACCOUNT_NOT_EXIST);
			return re;
		}
		Person person = persons.get(0);
		String key = account+"$"+person.getRecordTime().getTime();//数字签名
        String requestUrl = request.getRequestURL().toString();
        String sid = CryptoUtils.encoder(key);// 数字签名
        String url = requestUrl.substring(0,requestUrl.lastIndexOf("/"))+"/resetPwd?account="+account+"&sid="+sid+"&timeFlag="+System.currentTimeMillis();
        if(person.getAccount()==null || "".equals(person.getAccount())){
        	re.setMessage("user not bound email address");
        	return re;
        };
        return emailClientService.sendEmail(person.getAccount(), buildContent(url), Constants.EMAIL_SEND_ACCT, Constants.EMAIL_SEND_PASSWORD);

	}
	
	@Override
	public ResultEntity<String> resetPwd(String account, String sid,Long timeFlag) {
		ResultEntity<String> re = new ResultEntity<String>(ResultEntity.FAILD,"","");
		String newPassword = null;
		if (sid==null || sid.equals("") || account==null || account.equals("")) {
            return re;
        }
		Person p = new Person();
		p.setAccount(account);
		List<Person> persons = personMapper.getForList(p);
		if(persons==null || persons.size()==0){
			re.setCode(ResultEntity.ACCOUNT_NOT_EXIST);
			return re;
		}
		Person person = persons.get(0);
		String key = account+"$"+person.getRecordTime().getTime();//数字签名
        String digitalSignature = CryptoUtils.encoder(key);// 数字签名
		if(!sid.equals(digitalSignature)){
			re.setMessage("sid not match!");
			return re;
		}
		//3600秒后超时失效
		if((System.currentTimeMillis()-timeFlag)>3600*1000){
			re.setMessage("timeout");
			return re;
		}else{
			/*newPassword = StringUtils.randomNum(6);
			Person tempUser = new Person();
			tempUser.setPassword(newPassword);
			tempUser.setId(person.getId());
			personMapper.updateForPerson(tempUser);
			re.setCode(ResultEntity.SUCCESS);
			re.setData(newPassword);*/
			re.setCode(ResultEntity.SUCCESS);
			re.setData(person.getPassword());
		}
		return re;
	}

	private String buildContent(String url){  
		StringBuilder sb = new StringBuilder();
		sb.append("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">");
		sb.append("<html>");
		sb.append("<head>");
		sb.append("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />");
		sb.append("<title>Find password</title>");
		sb.append("<style type=\"text/css\">");
		sb.append("</style>");
		sb.append("</head>");
		sb.append("您的App账号正在申请找回密码，请点击以下链接来找回您的密码。");
		sb.append("<a href='");
		sb.append(url);
		sb.append("'>立即找回密码»</a>（本链接有效期为1小时）");
		sb.append("如果您点击上述链接无效，请将下面的链接复制到浏览器地址栏中访问：");
		sb.append("<br/>");
		sb.append(url);
		sb.append(" <p/>");
		//sb.append("找回密码成功后，请您登录到APP并尽快修改密码。");
		sb.append(" <p/><p/>");
		sb.append("The password for your App account is being Retrieved,please click the below link:");
		sb.append("<a href='");
		sb.append(url);
		sb.append("'>Retrieve the password immediatly»</a>(the link is valid within 1 hour)");
		sb.append("If no work after clicking,please copy the link to explorer and search it.");
		sb.append("<br/>");
		sb.append(url);
		sb.append(" <p/>");
		//sb.append("Please log in App and revise your password once password successfully reseted.");
		sb.append("<p/></html>");
		//System.out.println("--------------:"+sb.toString());
		return sb.toString();
    }

	@Override
	public ResultEntity<List<Person>> queryForList(Person record) {
		ResultEntity<List<Person>> re=new ResultEntity<List<Person>>();
		Long total=personMapper.getTotal(record);
		/*if(record==null){
			return re;
		}*/
		List<Person> list=personMapper.queryForList(record);
		re.setCode(ResultEntity.SUCCESS);
		re.setMessage(ResultEntity.MESSAGE_SUCCESS);
		re.setData(list);
		re.setTotal(total);
		re.setRows(list);
		return re;
	}

	@Override
	public ResultEntity<List<Long>> getChartsData(int intervals,String countryCode,String type) {
		ResultEntity<List<Long>> re=new ResultEntity<List<Long>>();
		ArrayList<Long> arr=new ArrayList<Long>();
		ArrayList<String> arrayList=GetPastDateUtils.dateArray(intervals);//得到过去intervals天日期数组
		//System.out.println(arrayList);
		for(int i=0;i<arrayList.size();i++){
			arr.add(personMapper.getSum(arrayList.get(i),countryCode,type));//获取每天注册的总人数
		}
		re.setCode(ResultEntity.SUCCESS);
		re.setMessage(ResultEntity.MESSAGE_SUCCESS);
		re.setData(arr);
		return re;
	}
	
	@Override
	public ResultEntity<List<Long>> getPersons(int intervals,String countryCode,String type) {
		ResultEntity<List<Long>> re=new ResultEntity<List<Long>>();
		ArrayList<Long> arr=new ArrayList<Long>();
		ArrayList<String> arrayList=GetPastDateUtils.dateArray(intervals);//得到过去intervals天日期数组
		//System.out.println(arrayList);
		for(int i=0;i<arrayList.size();i++){
			arr.add(personMapper.getPersons(arrayList.get(i),countryCode,type));//获取某天之前的总人数
		}
		re.setCode(ResultEntity.SUCCESS);
		re.setMessage(ResultEntity.MESSAGE_SUCCESS);
		re.setData(arr);
		return re;
	}

	@Override
	public ResultEntity<List<Device>> getCountry() {
		ResultEntity<List<Device>> re=new ResultEntity<List<Device>>();
		List<Device> list=personMapper.getCountry();
		re.setCode(ResultEntity.SUCCESS);
		re.setMessage(ResultEntity.MESSAGE_SUCCESS);
		re.setData(list);
		return re;
	}

	@Override
	public ResultEntity<String> personNum(String countryCode,String type) {
		ResultEntity<String> re=new ResultEntity<String>();
		Long personNum;
		try {
			personNum = personMapper.getNewTotal(countryCode,type);
			re.setCode(ResultEntity.SUCCESS);
			re.setMessage(ResultEntity.MESSAGE_SUCCESS);
			re.setTotal(personNum);
		} catch (Exception e) {
			e.printStackTrace();
			re.setCode(ResultEntity.FAILD);
			re.setMessage("查询数据库异常");
		}
		
		return re;
	}

	@Override
	public ResultEntity<String> todayActivePerN(String countryCode, String type) {
		ResultEntity<String> re=new ResultEntity<String>();
		Long active;
		try {
			active = personMapper.todayActivePerN(countryCode, type);
			re.setCode(ResultEntity.SUCCESS);
			re.setMessage(ResultEntity.MESSAGE_SUCCESS);
			re.setTotal(active);
		} catch (Exception e) {
			e.printStackTrace();
			re.setCode(ResultEntity.FAILD);
			re.setMessage("查询数据库异常");
		}
		return re;
	}

}
