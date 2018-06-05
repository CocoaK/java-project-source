package com.biencloud.smarthome.service.hdfstask.service.impl;

import java.util.List;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.biencloud.smarthome.service.base.service.impl.BaseService;
import com.biencloud.smarthome.service.common.model.Paging;
import com.biencloud.smarthome.service.hdfstask.model.HdfsTask;
import com.biencloud.smarthome.service.hdfstask.service.IHDFSTaskService;

/**
 * 
 * 类名称：HDFSFileServiceImpl 类描述： HDFS本地文件业务实现接口
 * 
 * @author: kouy
 * @version: 0.1
 * @date: 2012-4-26 下午5:18:29
 */
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class HDFSTaskServiceImpl extends BaseService<HdfsTask, Long> implements IHDFSTaskService {

	@Override
	public List<HdfsTask> findTask(Integer taskStatus) {

		return super.find("from HdfsTask where taskStatus=" + taskStatus);
	}

	@Override
	public Paging<HdfsTask> list(int pageNum, int pageSize,String condition,String orderString) {
		// TODO Auto-generated method stub
		return super.queryForPagingByEntityNameAndCondition(pageNum, pageSize, "HdfsTask",condition,orderString);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public boolean saveOrUpdate(HdfsTask hdfsTask) {
		boolean isSuccess = false;
		if (hdfsTask != null) {
			if (hdfsTask.getId() == null) {
				super.save(hdfsTask);
				isSuccess = true;
			} else {
				super.update(hdfsTask);
				isSuccess = true;
			}
		}
		return isSuccess;
	}

	@Override
	public HdfsTask findById(Long id) {
		if (id != null) {
			return super.get(id);
		}
		return null;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public boolean deleteById(Long id) {
		boolean isDeleteSuccess = false;
		if (id != null) {
			HdfsTask ht = this.findById(id);
			if (ht != null) {
				super.remove(ht);
				isDeleteSuccess = true;
			}

		}
		return isDeleteSuccess;
	}

	@Override
	public List<HdfsTask> findTask() {
		
		return super.find("from HdfsTask");
	}

	@Override
	public Paging<HdfsTask> list(int pageNum, int pageSize, String condition, String orderString, Object... values) {
		return super.queryForPagingByEntityNameAndCondition(pageNum, pageSize, "HdfsTask",condition,orderString,values);
	}

}
