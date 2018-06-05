package com.smarthome.socket.service.business.service.push.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.smarthome.socket.common.util.DateTimeUtil;
import com.smarthome.socket.service.business.service.BaseService;
import com.smarthome.socket.service.business.service.push.IPushService;
import com.smarthome.socket.service.vo.PushVO;
import com.smarthome.socket.wsservice.stub.MapEntry;
import com.smarthome.socket.wsservice.stub.Push;

/**
 * 
 * 类名称：PushServiceImpl 类描述： 推送接口实现类
 * 
 * @author: kouy
 * @version: 0.1
 * @date: 2012-5-9 下午3:51:03
 */
public class PushServiceImpl extends BaseService implements IPushService {

	@Override
	public List<PushVO> listPush(String whereCondition) {
		List<PushVO> _list = null;

		List<Push> list = super.getSmartHomeTcpService().listPush(whereCondition);

		if (list != null && !list.isEmpty()) {
			_list = new ArrayList<PushVO>();
			for (Push p : list) {
				if (p != null) {
					PushVO pv = new PushVO(p.getId(), p.getPushName(), p.getPushKind(), p.getPushContent(), p.getPushVersion(), null,
							p.getPushClientId(), p.getSize(), p.getPushDescription());

					_list.add(pv);

				}
			}
		}

		return _list;
	}

	@Override
	public boolean deleteById(Long id) {
		return super.getSmartHomeTcpService().deleteById(id);
	}

	@Override
	public PushVO findById(Long id) {
		PushVO pv = null;

		Push p = super.getSmartHomeTcpService().findById(id);
		if (p != null) {
			pv = new PushVO(p.getId(), p.getPushName(), p.getPushKind(), p.getPushContent(), p.getPushVersion(),
					DateTimeUtil.convertXMLGregorianCalendarToDate(p.getAddTime(), "yyyy-MM-dd HH:mm:ss"), p.getPushClientId(),
					p.getSize(), p.getPushDescription());
		}

		return pv;
	}

	@Override
	public List<PushVO> listPushByClientID(String ids) {
		List<PushVO> _list = null;

		if (ids != null) {
			List<Push> list = super.getSmartHomeTcpService().listPushByClientId(ids);
			if (list != null && !list.isEmpty()) {
				_list = new ArrayList<PushVO>();
				for (Push p : list) {
					if (p != null) {
						PushVO pv = new PushVO(p.getId(), p.getPushName(), p.getPushKind(), p.getPushContent(), p.getPushVersion(), null,
								p.getPushClientId(), p.getSize(), p.getPushDescription());

						_list.add(pv);

					}
				}
			}
		}

		return _list;
	}

	@Override
	public Map<String, List<PushVO>> listPushByClientIDForMap(String ids,String pushKinds) throws Exception {
		Map<String, List<PushVO>> map = null;
		List<MapEntry> entries = super.getSmartHomeTcpService().listPushByClientIDForMap(ids,pushKinds).getEntries();
		if (entries != null && !entries.isEmpty()) {
			map = new HashMap<String, List<PushVO>>();
			for (MapEntry me : entries) {
				if (me != null) {
					String key = me.getKey();
					List<Push> list = me.getValue();

					if (list != null && !list.isEmpty()) {
						List<PushVO> _list = new ArrayList<PushVO>();
						for (Push p : list) {
							if (p != null) {
								PushVO pv = new PushVO(p.getId(), p.getPushName(), p.getPushKind(), p.getPushContent(), p.getPushVersion(),
										null, p.getPushClientId(), p.getSize(), p.getPushDescription());

								_list.add(pv);

							}
						}
						map.put(key, _list);
					}
				}
			}
		}
		return map;
	}

	@Override
	public String queryCronTimeUpdate() {
		// TODO Auto-generated method stub
		return super.getSmartHomeTcpService().queryCronTimeUpdate();
	}

}
