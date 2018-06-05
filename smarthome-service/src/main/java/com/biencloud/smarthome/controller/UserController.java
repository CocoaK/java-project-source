package com.biencloud.smarthome.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.biencloud.smarthome.service.base.service.IBaseResService;
import com.biencloud.smarthome.service.common.model.Paging;
import com.biencloud.smarthome.service.common.model.ResultEntity;
import com.biencloud.smarthome.service.common.model.ResultList;
import com.biencloud.smarthome.service.common.page.Page;
import com.biencloud.smarthome.service.rest.model.User;
import com.biencloud.smarthome.service.rest.service.IUserService;

@Controller
@RequestMapping("/user")
public class UserController extends BaseResController<User>{
		
	@Autowired
	private IUserService userService;
	
	@Override
	public IBaseResService<User> getBaseResService() {
		return userService;
	}

	@RequestMapping(value="/queryList", method={RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody List<User> queryList(User user) {
		return userService.queryList(user);
	}
	
	@RequestMapping(value="/queryForPage", method={RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody ResultEntity<ResultList<List<User>>> queryForPage(HttpServletRequest request,
			HttpServletResponse response, Page<User> p, User user) {
		return userService.getForResultList(p, user);
	}
	
	@RequestMapping(value="/queryByUsername", method={RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody ResultEntity<User> queryList(String username) {
		return userService.queryByUsername(username);
	}
	
	@RequestMapping(value="/register", method={RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody ResultEntity<User> register(User user) {
		return userService.save(user);
	}
	
	/**带SIP注册的用户注册*/
	@RequestMapping(value="/sipRegister", method={RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody ResultEntity<User> sipRegister(User user) {
		return userService.saveAndRegSip(user);
	}
	
	//用户验证
	@RequestMapping(value="/verify", method={RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody ResultEntity<User> verify(User user) {
		if(user==null){
			return new ResultEntity<User>(ResultEntity.INPUT_IS_NULL,"",null);
		}
		return userService.verify(user.getUsername(),user.getPassword());
	}
	
	//带用户信息的SIP用户验证
		@RequestMapping(value="/sipVerify", method={RequestMethod.GET, RequestMethod.POST})
		public @ResponseBody ResultEntity<User> sipVerify(User user) {
			if(user==null){
				return new ResultEntity<User>(ResultEntity.INPUT_IS_NULL,"",null);
			}
			return userService.sipVerify(user.getUsername(),user.getPassword());
		}
	
	//修改密码
	@RequestMapping(value="/updatePwd", method={RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody ResultEntity<User> updatePwd(String username, String password,String newPasswd) {
		return userService.updatePwd(username, password, newPasswd);
	}

	//重置密码
	@RequestMapping(value="/toResetPwd", method={RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody ResultEntity<String> toResetPwd(String username) {
		return userService.toResetPwd(username);
	}
	
	//重置密码
	@RequestMapping(value="/resetPwd", method={RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody ModelAndView resetPwd(String username, String sid,Long timeFlag) {
		ModelAndView view = new ModelAndView();
		ResultEntity<String> re = userService.resetPwd(username, sid, timeFlag);
		if(re.getCode()==1){
			view.addObject("data", re.getData());
			view.setViewName("passwd");
		}else{
			view.setViewName("error");
		}
		return view;
	}
	
	@RequestMapping(value="/queryPage", method={RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody ResultList<List<User>> queryPage(@RequestBody Map map) {
		return userService.queryPaing(map);
	}
	
	@RequestMapping(value="/get", method={RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody ResultEntity<User> get(@RequestBody Integer userId) {
		return userService.getForOneResultEntity(userId);
	}
	
	@RequestMapping(value="/del", method={RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody ResultEntity<String> del(@RequestBody Integer userId) {
		return userService.deleteByIdForResultEntity(userId);
	}
	
	@RequestMapping(value="/updt", method={RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody ResultEntity<String> updt(@RequestBody User user) {
		return userService.updateForResultEntity(user);
	}
}
