package com.biencloud.smarthome.web.login.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.apache.commons.lang.StringUtils;

import com.biencloud.smarthome.web.base.service.BaseService;
import com.biencloud.smarthome.web.common.util.Constants;
import com.biencloud.smarthome.web.login.service.ILoginService;
import com.biencloud.smarthome.web.login.vo.LoginVO;
import com.biencloud.smarthome.web.menu.service.IMenuService;
import com.biencloud.smarthome.web.menu.vo.MenuVO;
import com.biencloud.smarthome.web.permissions.service.IPermissionsService;
import com.biencloud.smarthome.web.wsclient.stub.Login;


/**
 * 
 * 类名称：LoginServiceImpl 类描述： 登陆业务处理接口实现类
 * 
 * @author: kouy
 * @version: 0.1
 * @date: 2012-5-2 下午4:22:58
 */
public class LoginServiceImpl extends BaseService<LoginVO> implements ILoginService {
	
	private IMenuService menuService;
	private IPermissionsService permissionsService;
	private boolean validCodeOpenFlag;
	private boolean multiIpLoginFlag;

	@Override
	public boolean isValidCodeOpenFlag() {
		return validCodeOpenFlag;
	}

	public void setValidCodeOpenFlag(boolean validCodeOpenFlag) {
		this.validCodeOpenFlag = validCodeOpenFlag;
	}

	public boolean isMultiIpLoginFlag() {
		return multiIpLoginFlag;
	}

	public void setMultiIpLoginFlag(boolean multiIpLoginFlag) {
		this.multiIpLoginFlag = multiIpLoginFlag;
	}
	
	
	@Override
	public String getRandomCode() {
		StringBuilder randomCode = new StringBuilder();
		
		// 生成随机类
		Random random = new Random();

		// 随机产生6位数字的认证码
		for (int i = 0; i < 6; i++)
			randomCode.append(random.nextInt(10));
		
		return randomCode.toString();
	}

	@Override
	public LoginVO login(LoginVO loginVO) {
		Login login = new Login();
		copyProperties(loginVO, login, new String[]{"validCode", "createdTime", "lastLoginTime"}, false);
		Login retLogin = getSmartHomeService().logon(login);	
		copyProperties(retLogin, loginVO, true, "createdTime", "lastLoginTime");
		return loginVO;
	}
	
	@Override
	public LoginVO getLoginByLoginName(String loginName) {
		Login login = getSmartHomeService().getLoginByLoginName(loginName);
		if(login == null)
			return null;
		LoginVO loginVO = new LoginVO();
		copyProperties(login, loginVO, true, "createdTime", "lastLoginTime");
		return loginVO;
	}

	@Override
	public void resetPassword(String loginName) {
		getSmartHomeService().resetPassword(loginName);
	}

	@Override
	public void updateStatus(String loginId, String status) {
		getSmartHomeService().updateStatus(loginId, status);
	}

	@Override
	public void updateOnlineFlag(String loginName, String onlineFlag, String ip) {
		getSmartHomeService().updateOnlineFlag(loginName, onlineFlag, ip);
	}

	@Override
	public long countUsersByOnlineFlag(String onlineFlag) {
		return getSmartHomeService().countUsersByOnlineFlag(onlineFlag);
	}

	@Override
	public void updatePassword(String loginName, String password) {
		getSmartHomeService().updatePassword(loginName, password);
	}

	@Override
	public boolean checkValidationCode(String userValidCode, String sysValidCode) {
		if(!validCodeOpenFlag)
			return true;
		return StringUtils.equals(StringUtils.lowerCase(userValidCode), 
				StringUtils.lowerCase(sysValidCode));
	}

	@Override
	public List<String> getTabs(String userType, String roleCode, String position) {
		List<MenuVO> menus = permissionsService.queryMenusByRole(roleCode);
		String[] tabPermissions = null;
		List<String> tabTopList = new ArrayList<String>();
		List<String> tabBottomList = new ArrayList<String>();
		tabTopList.add("tab0");
		tabTopList.add("tab1");
		tabTopList.add("tab2");
		tabTopList.add("tab3");
		tabTopList.add("tab8");
		tabBottomList.add("tab4");
		tabBottomList.add("tab5");
		tabBottomList.add("tab6");
		tabBottomList.add("tab7");
		if(menus!=null && menus.size()!=0){
			if(Constants.LOGIN_USER_TYPE_OWNER.equals(userType)){
				tabBottomList.remove("tab5");
				tabBottomList.remove("tab7");
				tabTopList.remove("tab8");
				//业主拥有全部tab需要的权限。个人信息发布，个人客服，个人设备管理，报修管理，个人收费
				tabPermissions = new String[]{"M003005","M003006","M003002","M003017","M003004"};
				//业主首页tab依次为：未读信息，最近投诉，最近访客，最近报修，最近缴费，最近信息
				if(!menus.contains(menuService.getMenuDetail(tabPermissions[0]))){	//个人信息发布
					tabTopList.remove("tab0");	//未读信息，需要个人信息发布权限
					tabBottomList.remove("tab5");	//最近信息
				}
				if(!menus.contains(menuService.getMenuDetail(tabPermissions[1]))){	//个人客服
					tabTopList.remove("tab1");  //最近投诉，需要个人客服权限
				}
				if(!menus.contains(menuService.getMenuDetail(tabPermissions[2]))){	//个人设备管理
					tabTopList.remove("tab2");	//最近访客，需要个人设备管理权限
				}
				if(!menus.contains(menuService.getMenuDetail(tabPermissions[3]))){	//报修管理
					tabTopList.remove("tab3");	//最近报修，需要个人报修权限
				}
				if(!menus.contains(menuService.getMenuDetail(tabPermissions[4]))){	//个人收费
					tabBottomList.remove("tab4"); //最近缴费，需要个人收费权限
				}
			}
			if(Constants.LOGIN_USER_TYPE_PAUSER.equals(userType)){
				//tabTopList.remove("tab0");
				tabTopList.remove("tab1");
				tabTopList.remove("tab2");
				tabTopList.remove("tab8");
				tabBottomList.remove("tab4");
				tabBottomList.remove("tab6");
				tabBottomList.remove("tab7");
				//物业管理员拥有全部tab需要的权限。信息发布，客服管理，报警管理，设备管理,门禁管理,业主信息管理,报修管理
				//tabPermissions = new String[]{"M002003","M002007","M002006","M002011","M002008","M002002","M002014"};
				tabPermissions = new String[]{"M002003","","","","","M002002",""};
				//物业管理员首页tab依次为：未审核信息，今日投诉，今日报警，最近访客，今日发卡，今日入住，在线设备，今日报修,身份证刷卡
				if(!menus.contains(menuService.getMenuDetail(tabPermissions[0]))){	//信息发布
					tabTopList.remove("tab0");	//未审核信息
				}
				if(!menus.contains(menuService.getMenuDetail(tabPermissions[1]))){	//客服管理
					tabTopList.remove("tab1");  //今日投诉
				}
				if(!menus.contains(menuService.getMenuDetail(tabPermissions[2]))){	//报警管理
					tabTopList.remove("tab2");	//今日报警
				}
				if(!menus.contains(menuService.getMenuDetail(tabPermissions[3]))){	//设备管理
					tabTopList.remove("tab3");	//最近访客
					tabBottomList.remove("tab6"); //在线设备
				}
				if(!menus.contains(menuService.getMenuDetail(tabPermissions[4]))){	//门禁管理
					tabTopList.remove("tab8");	//身份证刷卡
					tabBottomList.remove("tab4"); //今日发卡
				}
				if(!menus.contains(menuService.getMenuDetail(tabPermissions[5]))){	//业主管理
					tabBottomList.remove("tab5"); //今日入住
				}
				if(!menus.contains(menuService.getMenuDetail(tabPermissions[6]))){	//报修管理
					tabBottomList.remove("tab7"); //今日报修
				}
			}
			if(Constants.LOGIN_USER_TYPE_SYSTEM.equals(userType)){
				tabBottomList.remove("tab5");
				tabBottomList.remove("tab6");
				tabBottomList.remove("tab7");
				//系统管理员拥有全部tab需要的权限。信息发布，客服管理，设备软件管理，广告投放,数据推送,传输管理
				tabPermissions = new String[]{"M001003","M001005","M001010","M001014","M001012","M001013"};
				//系统管理员首页tab依次为：最近信息，未读投诉，最近软件，最近广告，数据推送，文件传输
				if(!menus.contains(menuService.getMenuDetail(tabPermissions[0]))){	//信息发布
					tabTopList.remove("tab0");	//最近信息
				}
				if(!menus.contains(menuService.getMenuDetail(tabPermissions[1]))){	//客服管理
					tabTopList.remove("tab1");  //未读投诉
				}
				if(!menus.contains(menuService.getMenuDetail(tabPermissions[2]))){	//设备软件管理
					tabTopList.remove("tab2");	//最近软件
				}
				if(!menus.contains(menuService.getMenuDetail(tabPermissions[3]))){	//广告投放
					tabTopList.remove("tab3");	//最近广告
				}
				if(!menus.contains(menuService.getMenuDetail(tabPermissions[4]))){	//数据推送
					tabBottomList.remove("tab4"); //数据推送
				}
				if(!menus.contains(menuService.getMenuDetail(tabPermissions[4]))){	//传输管理
					tabBottomList.remove("tab5"); //文件传输
				}
			}
		}else{
			tabTopList.clear();	//没有权限则清空
			tabBottomList.clear();//没有权限则清空
		}
		if("top".equals(position)){
			return tabTopList;	
		}
		if("bottom".equals(position)){
			return tabBottomList;
		}
		return new ArrayList<String>();
	}

	@Override
	public String validatePassing(String loginName, String ip) {
		LoginVO login = getLoginByLoginName(loginName);
		if(login == null)
			return Constants.ACC_ERROR;
		
		if(!Constants.LOGIN_ONLINE.equals(login.getOnlineFlag()))
			return Constants.ACC_OFFLINE;
			
		if(isMultiIpLoginFlag() || StringUtils.equals(ip, login.getIp()))
			return Constants.LOGIN_SUCCESS;
		
		return Constants.LOGIN_SYNC;
	}
	
	
	public IMenuService getMenuService() {
		return menuService;
	}

	public void setMenuService(IMenuService menuService) {
		this.menuService = menuService;
	}

	public IPermissionsService getPermissionsService() {
		return permissionsService;
	}

	public void setPermissionsService(IPermissionsService permissionsService) {
		this.permissionsService = permissionsService;
	}
}
