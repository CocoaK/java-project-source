package com.biencloud.smarthome.service.push.service.impl;

import java.util.Date;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.biencloud.smarthome.service.base.service.impl.BaseService;
import com.biencloud.smarthome.service.common.model.Paging;
import com.biencloud.smarthome.service.push.dao.IPushDao;
import com.biencloud.smarthome.service.push.model.Push;
import com.biencloud.smarthome.service.push.model.PushFinish;
import com.biencloud.smarthome.service.push.service.IPushFinishService;

/**
 * 
 * 类名称：PushFinishServiceImpl 类描述：推送完成业务接口 实现类
 * 
 * @author: kouy
 * @version: 0.1
 * @date: 2012-5-9 上午11:27:48
 */
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class PushFinishServiceImpl extends BaseService<PushFinish, Long> implements IPushFinishService {
	private IPushDao pushDao;

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public boolean insertPushFinish(PushFinish pushFinish) {
		boolean flag = false;
		PushFinish backObj = super.save_update(pushFinish);// 保存或更新
		if (backObj != null) {
			flag = true;
		}
		return flag;
	}

	@Override
	public PushFinish findById(Long id) {
		PushFinish pushFinish = super.get(id);
		return pushFinish;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public boolean deleteById(Long id) {
		boolean flag = false;
		super.removeByIds(id);// 删除
		flag = true;
		return flag;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public boolean deleteByEntity(PushFinish pushFinish) {
		boolean flag = false;
		if (pushFinish != null) {
			super.remove(pushFinish);// 删除
		}
		flag = true;
		return flag;
	}

	@Override
	public Paging<PushFinish> queryPushFinishForPaging(int pageNum, int pageSize, String condition, String orderString) {

		return super.queryForPagingByEntityNameAndCondition(pageNum, pageSize, "PushFinish", condition, orderString);
	}

	@Override
	public Paging<PushFinish> queryPushFinishForPaging(int pageNum, int pageSize, String condition, String orderString, Object... values) {
		return super.queryForPagingByEntityNameAndCondition(pageNum, pageSize, "PushFinish", condition, orderString, values);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public boolean savePushFinish(Long pushId) {
		boolean flag = false;
		try {
			if (pushId != null) {
				// 推送对象
				Push push = pushDao.get(pushId);
				
				if (push != null) {
					// 封装新的推送完成对象
					PushFinish pf = new PushFinish(push.getPushName(), push.getPushContent(), push.getPushKind(), push.getAddTime(),
							new Date(), push.getPushClientId(), push.getSize(), push.getPushVersion());
					
					// 保存
					super.save_update(pf);
					
					pushDao.remove(push);
					
					flag = true;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	public IPushDao getPushDao() {
		return pushDao;
	}

	public void setPushDao(IPushDao pushDao) {
		this.pushDao = pushDao;
	}

}
