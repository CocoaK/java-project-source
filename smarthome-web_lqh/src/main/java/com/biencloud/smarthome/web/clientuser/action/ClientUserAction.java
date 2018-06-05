package com.biencloud.smarthome.web.clientuser.action;

import java.util.List;

import com.biencloud.smarthome.web.base.action.BaseAction;
import com.biencloud.smarthome.web.clientuser.service.IClientUserService;
import com.biencloud.smarthome.web.clientuser.vo.ClientUserVO;
import com.biencloud.smarthome.web.common.vo.PagingVO;
import com.biencloud.smarthome.web.common.vo.ResultEntity;
import com.biencloud.smarthome.web.device.vo.DeviceVO;

@SuppressWarnings("serial")
public class ClientUserAction extends BaseAction<ClientUserVO>{
	
	private List<ClientUserVO> clientUsers;
	private IClientUserService clientUserService;
	private ClientUserVO user;
	private boolean PromptFlag;
	
	public ClientUserVO getUser() {
		return user;
	}
	public void setUser(ClientUserVO user) {
		this.user = user;
	}
	public String queryList() throws Exception{
		if(user==null){
			user=new ClientUserVO();
		}else{
			if("".equals(user.getUsername())){
				user.setUsername(null);
			}
			if("".equals(user.getType())){
				user.setType(null);
			}
			if("".equals(user.getStatus())){
				user.setStatus(null);
			}
			if("".equals(user.getMobile())){
				user.setMobile(null);
			}	
		}
		if(super.getPage()==null){
			super.setPage(new PagingVO());
		}
		PagingVO<ClientUserVO> pages = clientUserService.queryPaging(user, super.getPage());
		this.setPage(pages);
		return SUCCESS;
	}

	public String updateDetail() throws Exception{
		return SUCCESS;
	}
	public String userDetail() throws Exception{
		ResultEntity<ClientUserVO> re = clientUserService.get(user.getId().intValue());
		if(re!=null && re.getCode()==ResultEntity.SUCCESS){
			ClientUserVO clientUser = re.getData();
			setUser(clientUser);
		}
		return SUCCESS;
	}
	public String update() throws Exception{
		ResultEntity<ClientUserVO> rs = clientUserService.get(user.getId().intValue());
		ClientUserVO clientUser=rs.getData();
		setUser(clientUser);
		return SUCCESS;
	}
	public String updateResult() throws Exception{
		
		user.setUsername(user.getUsername());
		user.setEmail(user.getEmail());
		user.setPassword(user.getPassword());
		user.setMobile(user.getMobile());
		ResultEntity<String> re = clientUserService.update(user);
		setPromptFlag(true);
		return update();
	}
	public boolean isPromptFlag() {
		return PromptFlag;
	}
	public void setPromptFlag(boolean promptFlag) {
		PromptFlag = promptFlag;
	}
	public String delete() throws Exception{
		ResultEntity<String> re = clientUserService.delete(user.getId().intValue());
		//如果删除不成功
		if(re.getCode()!=ResultEntity.SUCCESS){
			return queryList();
		}
		return queryList();
	}
	public List<ClientUserVO> getClientUsers() {
		return clientUsers;
	}
	public void setClientUsers(List<ClientUserVO> clientUsers) {
		this.clientUsers = clientUsers;
	}
	public IClientUserService getClientUserService() {
		return clientUserService;
	}
	public void setClientUserService(IClientUserService clientUserService) {
		this.clientUserService = clientUserService;
	}
	
}
