package com.biencloud.smarthome.service.rest.service.impl;

import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.biencloud.smarthome.service.base.mapper.BaseMapper;
import com.biencloud.smarthome.service.base.service.impl.BaseResService;
import com.biencloud.smarthome.service.common.constants.Constants;
import com.biencloud.smarthome.service.common.model.ResultEntity;
import com.biencloud.smarthome.service.common.utils.CryptoUtils;
import com.biencloud.smarthome.service.rest.mapper.UserMapper;
import com.biencloud.smarthome.service.rest.model.User;
import com.biencloud.smarthome.service.rest.service.IAuthSessionService;
import com.biencloud.smarthome.service.rest.service.IEmailClientService;
import com.biencloud.smarthome.service.rest.service.IUserService;
import com.biencloud.smarthome.service.sip.model.SipRegister;
import com.biencloud.smarthome.service.sip.service.ISipRegisterService;

@Service
public class UserService extends BaseResService<User> implements
		IUserService {

	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	private HttpServletRequest request;  
	
	@Autowired
	private IEmailClientService emailClientService;
	
	@Autowired
	private ISipRegisterService sipRegisterService;
	
	@Autowired
	private IAuthSessionService authSessionService;

	@Override
	public BaseMapper<User> getBaseMapper() {
		return userMapper;
	}

	@Override
	public List<User> queryList(User user) {
		return userMapper.getForList(user);
	}
	
	@Override
	public ResultEntity<User> queryByUsername(String username) {
		ResultEntity<User> re = new ResultEntity<User>(ResultEntity.FAILD,"",null);
		User u = new User();
		u.setUsername(username);
		List<User> list = userMapper.getForList(u);
		if(list!=null && list.size()>0){
			re.setCode(ResultEntity.SUCCESS);
			re.setData(list.get(0));
		}
		return re;
	}

	@Override
	public ResultEntity<User> save(User user) {
		ResultEntity<User> re = new ResultEntity<User>(ResultEntity.FAILD,"",null);
		if(user==null){
			re.setCode(ResultEntity.INPUT_IS_NULL);
			return re;
		}
		if(StringUtils.isBlank(user.getUsername())||StringUtils.isBlank(user.getPassword())){
			re.setCode(ResultEntity.USERNAME_OR_PASSWD_IS_NULL);
			return re;
		}
		User u = new User();
		u.setUsername(user.getUsername());
		//判断用户名是否已经存在
		List<User> list = userMapper.getForList(u);
		if(list!=null && list.size()>0){
			re.setCode(ResultEntity.ALREADY_EXIST);
			return re;
		}
		user.setPassword(CryptoUtils.encodeByMD5(user.getPassword()));
		//user.setType("1");
		//user.setStatus(1);
		user.setCreateTime(new Date());
		int i = userMapper.insertGetId(user);
		if(i>0){
			re.setCode(ResultEntity.SUCCESS);
			re.setMessage(ResultEntity.MESSAGE_SUCCESS);
			re.setData(user);
		}
		return re;
	}

	@Override
	public ResultEntity<User> verify(String username,String password) {
		ResultEntity<User> re = new ResultEntity<User>(ResultEntity.FAILD,"",null);
		if(StringUtils.isBlank(username) || StringUtils.isBlank(password)){
			re.setCode(ResultEntity.INPUT_IS_NULL);
			return re;
		}
		User user = new User();
		user.setUsername(username);
		List<User> ls = userMapper.getForList(user);
		if(ls==null || ls.size()==0){
			re.setCode(ResultEntity.USERNAME_NOT_EXIST);
			return re;
		}
		user.setPassword(CryptoUtils.encodeByMD5(password));
		List<User> list = userMapper.getForList(user);
		if(list!=null && list.size()>0){
			User u = list.get(0);
			HttpSession httpSession = request.getSession();
			String sessionKey = httpSession.getId();
			u.setSessionKey(sessionKey);
			//authSessionService.save(sessionKey, u.getId());
			re.setCode(ResultEntity.SUCCESS);
			re.setMessage(ResultEntity.MESSAGE_SUCCESS);
			re.setData(u);
		}else{
			re.setCode(ResultEntity.PASSWD_ERROR);
		}
		return re;
	}

	@Override
	public ResultEntity<User> updatePwd(String username, String password, String newPasswd) {
		ResultEntity<User> re = new ResultEntity<User>(ResultEntity.FAILD,"",null);
		if(StringUtils.isBlank(username) || StringUtils.isBlank(password)){
			re.setCode(ResultEntity.INPUT_IS_NULL);
			return re;
		}
		User user = new User();
		user.setUsername(username);
		List<User> ls = userMapper.getForList(user);
		if(ls==null || ls.size()==0){
			re.setCode(ResultEntity.USERNAME_NOT_EXIST);
			return re;
		}
		user.setPassword(CryptoUtils.encodeByMD5(password));
		List<User> list = userMapper.getForList(user);
		if(list!=null && list.size()>0){
			User u = list.get(0);
			u.setPassword(CryptoUtils.encodeByMD5(newPasswd));
			super.updateOnActiveById(u);
			re.setCode(ResultEntity.SUCCESS);
			re.setMessage(ResultEntity.MESSAGE_SUCCESS);
			re.setData(u);
		}else{
			re.setCode(ResultEntity.PASSWD_ERROR);
		}
		return re;
	}
	
	@Override
	public ResultEntity<String> toResetPwd(String username) {
		ResultEntity<String> re = new ResultEntity<String>(ResultEntity.FAILD,"","");
		if (username==null || username.equals("")) {
            return re;
        }
		User u = new User();
		u.setUsername(username);
		List<User> users = userMapper.getForList(u);
		if(users==null || users.size()==0){
			re.setCode(ResultEntity.NOT_EXIST);
			return re;
		}
		User user = users.get(0);
		String key = username+"$"+user.getUpdateTime().getTime();//数字签名
        String requestUrl = request.getRequestURL().toString();
        String sid = CryptoUtils.encoder(key);// 数字签名
        String url = requestUrl.substring(0,requestUrl.lastIndexOf("/"))+"/resetPwd?username="+username+"&sid="+sid+"&timeFlag="+System.currentTimeMillis();
        if(user.getEmail()==null || "".equals(user.getEmail())){
        	re.setMessage("user not bound email address");
        	return re;
        };
        return emailClientService.sendEmail(user.getEmail(), buildContent(url), Constants.EMAIL_SEND_ACCT, Constants.EMAIL_SEND_PASSWORD);

	}
	
	@Override
	public ResultEntity<String> resetPwd(String username, String sid,Long timeFlag) {
		ResultEntity<String> re = new ResultEntity<String>(ResultEntity.FAILD,"","");
		String newPassword = null;
		if (sid==null || sid.equals("") || username==null || username.equals("")) {
            return re;
        }
		User u = new User();
		u.setUsername(username);
		List<User> users = userMapper.getForList(u);
		if(users==null || users.size()==0){
			re.setCode(ResultEntity.NOT_EXIST);
			return re;
		}
		User user = users.get(0);
		String key = username+"$"+user.getUpdateTime().getTime();//数字签名
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
			newPassword = com.biencloud.smarthome.service.common.utils.StringUtils.randomNum(6);
			User tempUser = new User();
			tempUser.setPassword(CryptoUtils.encodeByMD5(newPassword));
			tempUser.setId(user.getId());
			userMapper.updateOnActive(tempUser);
			re.setCode(ResultEntity.SUCCESS);
			re.setData(newPassword);
		}
		return re;
	}

	private String buildContent(String url){  
		StringBuilder sb = new StringBuilder();
		sb.append("<html>");
		sb.append("您的App账号正在申请重置密码，请点击以下链接来重置您的密码。");
		sb.append("<a href='");
		sb.append(url);
		sb.append("'>立即重置密码»</a>（本链接有效期为1小时）");
		sb.append("如果您点击上述链接无效，请将下面的链接复制到浏览器地址栏中访问：");
		sb.append("<br/>");
		sb.append(url);
		sb.append(" <p/>");
		sb.append("重置密码成功后，请您登录到APP并尽快修改密码。");
		sb.append(" <p/><p/>");
		sb.append("The password for your App account is being reseted,please click the below link:");
		sb.append("<a href='");
		sb.append(url);
		sb.append("'>Reset the password immediatly»</a>(the link is valid within 1 hour)");
		sb.append("If no work after clicking,please copy the link to explorer and search it.");
		sb.append("<br/>");
		sb.append(url);
		sb.append(" <p/>");
		sb.append("Please log in App and revise your password once password successfully reseted.");
		sb.append("<p/></html>");
		return sb.toString();
    }

	@Override
	public ResultEntity<User> saveAndRegSip(User user) {
		ResultEntity<User> re = new ResultEntity<User>(ResultEntity.FAILD,"",null);
		if(user==null){
			re.setCode(ResultEntity.INPUT_IS_NULL);
			return re;
		}
		if(StringUtils.isBlank(user.getUsername())||StringUtils.isBlank(user.getPassword())){
			re.setCode(ResultEntity.USERNAME_OR_PASSWD_IS_NULL);
			return re;
		}
		//判断SIP账户是否存在，若存在则返回
		ResultEntity<SipRegister> result = sipRegisterService.getByUsername(user.getUsername());
		if(result.getCode()==ResultEntity.SUCCESS){
			re.setCode(ResultEntity.ALREADY_EXIST);
			re.setMessage("SIP user alreay exist");
			return re;
		}
		User u = new User();
		u.setUsername(user.getUsername());
		//判断用户名是否已经存在
		List<User> list = userMapper.getForList(u);
		
		if(list!=null && list.size()>0){
			re.setCode(ResultEntity.ALREADY_EXIST);
			return re;
		}
		SipRegister sipReg = new SipRegister();
		sipReg.setUsername(user.getUsername());
		ResultEntity<SipRegister> sipRe= sipRegisterService.register(sipReg);
		if(sipRe!=null && sipRe.getCode()==ResultEntity.SUCCESS && sipRe.getData()!=null){
			user.setSipid(sipRe.getData().getUsername());
			user.setSipPasswd(sipRe.getData().getPassword());
		}else{
			re.setCode(ResultEntity.FAILD);
			re.setMessage("SIP account failed");
			return re;
		}
		if(user.getMobile()==null || "".equals(user.getMobile())){
			user.setMobile(user.getUsername());
		}
		user.setPassword(CryptoUtils.encodeByMD5(user.getPassword()));
		
		user.setCreateTime(new Date());
		int i = userMapper.insertGetId(user);
		if(i>0){
			re.setCode(ResultEntity.SUCCESS);
			re.setMessage(ResultEntity.MESSAGE_SUCCESS);
			re.setData(user);
		}
		return re;
	}
	
	//@Override
	public ResultEntity<User> sipVerify(String username,String password) {
		ResultEntity<User> re = new ResultEntity<User>(ResultEntity.FAILD,"",null);
		if(StringUtils.isBlank(username) || StringUtils.isBlank(password)){
			re.setCode(ResultEntity.INPUT_IS_NULL);
			return re;
		}
		User user = new User();
		user.setUsername(username);
		List<User> ls = userMapper.getForList(user);
		if(ls==null || ls.size()==0){
			re.setCode(ResultEntity.USERNAME_NOT_EXIST);
			return re;
		}
		user.setPassword(CryptoUtils.encodeByMD5(password));
		List<User> list = userMapper.getForList(user);
		if(list!=null && list.size()>0){
			User u = list.get(0);
			HttpSession httpSession = request.getSession();
			String sessionKey = httpSession.getId();
			u.setSessionKey(sessionKey);
			ResultEntity<SipRegister> sipResult = sipRegisterService.getByUsername(username);
			if(sipResult.getCode()==ResultEntity.SUCCESS){
				u.setSipid(sipResult.getData().getUsername());
				u.setSipPasswd(sipResult.getData().getPassword());
			}
			if(sessionKey!=null && !"".equals(sessionKey)){
				authSessionService.save(sessionKey, u.getId());
			}
			re.setCode(ResultEntity.SUCCESS);
			re.setMessage(ResultEntity.MESSAGE_SUCCESS);
			re.setData(u);
		}else{
			re.setCode(ResultEntity.PASSWD_ERROR);
		}
		return re;
	}
}  
