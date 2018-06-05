package com.biencloud.smarthome.web.net.action;

import com.biencloud.smarthome.web.base.action.BaseAction;
import com.biencloud.smarthome.web.common.util.DateTimeUtil;
import com.biencloud.smarthome.web.net.service.INetService;
import com.biencloud.smarthome.web.net.vo.NetVO;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Date;

/**
 * 
 * 类名称：NetAction 类描述：网络测试Action
 * 
 * @author: kouy
 * @version: 0.1
 * @date: 2012-10-17 下午2:27:03
 */
@SuppressWarnings("serial")
public class NetAction extends BaseAction<NetVO> {
	private NetVO net;
	private INetService netService;
    /**
     * 
     * 方法的描述: 跳转到Ping ip页面
     * @author: kouy  
     * @version: 0.1
     * @date: 2012-10-25 上午10:01:00
     * @return
     */
	public String toPing() {
		return "toPing";
	}

	/**
	 * 
	 * 方法的描述: 执行ping命令
	 * 
	 * @author: kouy
	 * @version: 0.1
	 * @date: 2012-10-17 下午2:54:26
	 * @return
	 */
	public String ping() throws Exception{

		//判断传入的IP是否为null或空
		if (net != null && net.getIp() != null&&!"".equals(net.getIp().trim())) {
            //调用service中ping方法进行ping逻辑处理
			String result = netService.ping(net.getIp());
			//封装ping结果
			net.setPingResult(result);

		}

		return "ping";
	}

	public NetVO getNet() {
		return net;
	}

	public void setNet(NetVO net) {
		this.net = net;
	}

	public INetService getNetService() {
		return netService;
	}

	public void setNetService(INetService netService) {
		this.netService = netService;
	}

}
