package com.biencloud.smarthome.service.hdfstask;

import java.util.Date;
import java.util.List;

import org.junit.Test;

import com.biencloud.smarthome.service.base.BaseTest;
import com.biencloud.smarthome.service.common.model.Paging;
import com.biencloud.smarthome.service.hdfstask.model.HdfsTask;
import com.biencloud.smarthome.service.hdfstask.service.IHDFSTaskService;
import static org.junit.Assert.*;

public class HDFSTaskServiceTest extends BaseTest {
	/**
	 * 
	 * 方法的描述: 查询任务
	 * 
	 * @author: kouy
	 * @version: 0.1
	 * @date: 2012-4-26 下午7:18:08
	 * @param uploadStatus
	 *            上
	 * @return
	 */
	@Test
	public void findTask() {
		List<HdfsTask> list = getIHDFSTaskService().findTask();
		if (list.size() > 0) {
			assertTrue(true);
		} else {
			assertFalse(true);
		}

	}

	/**
	 * 
	 * 方法的描述: 分页
	 * 
	 * @author: kouy
	 * @version: 0.1
	 * @date: 2012-4-26 下午7:28:18
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	@Test
	public void list() {
		Paging<HdfsTask> page = getIHDFSTaskService().list(1, 10,null,null);
		if (page.getResults().size() > 0) {
			assertTrue(true);
		} else {
			assertFalse(true);
		}
	}

	/**
	 * 
	 * 方法的描述: 保存或更新
	 * 
	 * @author: kouy
	 * @version: 0.1
	 * @date: 2012-4-26 下午7:28:38
	 * @param hdfsTask
	 * @return
	 */
	@Test
	public void saveOrUpdate() {
		HdfsTask ht = new HdfsTask();
		ht.setFileFormat("jpg");
		ht.setFileName("test");
		ht.setFileSize(new Long(123));
		ht.setHdfsPath("aaa/bbb");
		ht.setTaskAddTime(new Date());
		ht.setTaskStatus(0);
		ht.setUploadPath("sss/bbb");
		getIHDFSTaskService().saveOrUpdate(ht);
	}

	/**
	 * 
	 * 方法的描述: 根据id查询
	 * 
	 * @author: kouy
	 * @version: 0.1
	 * @date: 2012-4-26 下午9:15:58
	 * @param id
	 * @return
	 */
	@Test
	public void findById() {
		HdfsTask ht = getIHDFSTaskService().findById(new Long(1));
		if (ht != null) {
			assertTrue(true);
		} else {
			assertFalse(true);
		}
	}

	/**
	 * 
	 * 方法的描述: 根据id进行删除
	 * 
	 * @author: kouy
	 * @version: 0.1
	 * @date: 2012-4-26 下午9:51:06
	 * @param id
	 * @return
	 */
	@Test
	public void deleteById() {
		boolean flag = getIHDFSTaskService().deleteById(new Long(1));
		if (flag) {
			assertTrue(true);
		} else {
			assertFalse(true);
		}
	}

	public IHDFSTaskService getIHDFSTaskService() {
		return (IHDFSTaskService) this.getBean("hdfsTaskService");
	}
}
