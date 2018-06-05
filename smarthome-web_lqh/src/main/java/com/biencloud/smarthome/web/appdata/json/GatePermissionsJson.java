package com.biencloud.smarthome.web.appdata.json;

import java.util.List;

import com.biencloud.smarthome.web.appdata.vo.gate.GateDataVO;

/**
 * 门禁数据。
 * @author kouy
 * @since 1.0 2012-6-27
 */
public class GatePermissionsJson extends Json {
	
	private List<GateDataVO> list;

	public List<GateDataVO> getList() {
		return list;
	}

	public void setList(List<GateDataVO> list) {
		this.list = list;
	}	
}
