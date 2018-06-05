package com.biencloud.smarthome.service.hdfs;

import java.util.Date;
import java.util.List;

import org.junit.Test;

import static org.junit.Assert.*;
import com.biencloud.smarthome.service.base.BaseTest;
import com.biencloud.smarthome.service.common.model.Paging;
import com.biencloud.smarthome.service.hdfs.model.LocalHdfs;
import com.biencloud.smarthome.service.hdfs.service.IHDFSFileService;

public class HDFSFileServiceTest extends BaseTest{
	/**
	 * 
	 * 方法的描述: 查询用户
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-4-18 下午9:23:00
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	@Test
	public void list()
	{
		Paging<LocalHdfs> page=getIHDFSFileService().list(1, 10,null,null);
		if(page.getResults().size()>0)
		{
			assertTrue(true);
		}else
		{
			assertFalse(true);
		}
			
	}
	/**
	 * 
	 * 方法的描述: 保存或更新
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-4-26 下午7:05:08
	 * @param LocalHdfs
	 * @return
	 */
	@Test
	public void saveOrUpdate()
	{
		LocalHdfs lh=new LocalHdfs();
		lh.setAddTime(new Date());
		lh.setFileName("test");
		lh.setFileSize(new Long(1111));
		lh.setHdfsPath("aaa/teee.jspg");
		lh.setLocalPath("bbb/test.jpg");
		lh.setTransferWay("upload");
		lh.setFileFormat("jpg");
		boolean isSave=getIHDFSFileService().saveOrUpdate(lh);
		//getIHDFSFileService().save(lh);
		assertTrue(isSave);
	
		
	}
	public IHDFSFileService getIHDFSFileService()
	{
		return (IHDFSFileService)this.getBean("hdfsFileService");
	}
}
