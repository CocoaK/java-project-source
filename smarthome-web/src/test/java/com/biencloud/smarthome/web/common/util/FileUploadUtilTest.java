package com.biencloud.smarthome.web.common.util;

import java.io.File;

import org.junit.Test;

import com.biencloud.smarthome.web.base.BaseTest;

/**
 * 
 * @author: jsun  
 * @since: 1.0 2012-5-28
 */
public class FileUploadUtilTest extends BaseTest {
	@Test
	public void testUploadByService() {
		String uploadUrl = "http://localhost:8080/smarthome-fileserver/upload/fileAction_webUpload.action";
		String fileFieldName = "uploadFile";
		File file = new File("E:/_孙军_工作目录/SOA组/孙军/2012/05/图片.jpg");

//		logger.info(FileUploadUtil.uploadByService(uploadUrl, fileFieldName, file, ""));
	}
}
