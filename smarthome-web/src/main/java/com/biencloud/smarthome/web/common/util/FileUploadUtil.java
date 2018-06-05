package com.biencloud.smarthome.web.common.util;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.biencloud.smarthome.web.common.vo.UploadResult;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

/**
 * 通过第3方的HTTP上传服务来上传文件
 * 
 * @author jsun
 * @since 1.0 2012-5-28
 * @see com.biencloud.smarthome.fileserver.file.action.FileAction
 * @link http://lapulande.iteye.com/blog/719581
 */
public class FileUploadUtil {
	private static final Logger LOGGER = LoggerFactory.getLogger(FileUploadUtil.class);
	
	/**
	 * 通过外部的HTTP上传服务来上传文件，并返回上传结果对象。
	 * @param uploadUrl HTTP上传服务的URL
	 * @param fileFieldName 表单file input的名字
	 * @param file 待上传的文件
	 * @param renameFile 重命名文件
	 * @return
	 */
	public static UploadResult uploadByService(String uploadUrl, String fileFieldName, File file,
			String renameFile, String userIp){
		UploadResult result = new UploadResult();
		
		if (StringUtils.isBlank(renameFile)) {
			renameFile = file.getName();
		}

		try {
			String BOUNDARY = "---------7d4a6d158c9"; // 定义数据分隔线
			URL url = new URL(uploadUrl);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			

			// 发送POST请求必须设置如下两行
			conn.setDoOutput(true);
			conn.setDoInput(true);
			conn.setUseCaches(false);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("connection", "Keep-Alive");
			conn.setRequestProperty("Charset", "UTF-8");
			conn.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + BOUNDARY);
			conn.addRequestProperty("clientIp", userIp);

			OutputStream out = new DataOutputStream(conn.getOutputStream());
			DataInputStream in = new DataInputStream(new FileInputStream(file));

			byte[] end_data = ("\r\n--" + BOUNDARY + "--\r\n").getBytes(); // 定义最后数据分隔线
			StringBuilder sb = new StringBuilder();
			sb.append("--");
			sb.append(BOUNDARY);
			sb.append("\r\n");
			sb.append("Content-Disposition: form-data;name=\"" + fileFieldName + "\";filename=\"" + renameFile + "\"\r\n");
			sb.append("Content-Type:application/octet-stream\r\n\r\n");
			byte[] data = sb.toString().getBytes("utf-8");
			
			out.write(data);

			int bytes = 0;
			byte[] bufferOut = new byte[1024];
			while ((bytes = in.read(bufferOut)) != -1) {
				out.write(bufferOut, 0, bytes);
			}
			in.close();
			out.write(end_data);
			out.flush();
			out.close();

			// 定义BufferedReader输入流来读取URL的响应
			BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			StringBuffer responseText = new StringBuffer();
			String line = null;
			while ((line = reader.readLine()) != null) {
				responseText.append(line);
			}

			// JSON样例
			// {"backResuslt":"1","file":{"fileAbsolutePath":"","fileFomat":"jpg","fileName":"foo","fileRelativePath":"webUploadFile\/image\/file.jpg","fileSize":335305,"rootPath":"E:\/Program Files\/"}}
			JsonParser jsonParser = new JsonParser();
			JsonObject json = jsonParser.parse(responseText.toString()).getAsJsonObject();

			
			result.setRetFlag(json.get("backResuslt").getAsString());
			result.setSuccess(Constants.FILE_UPLOAD_SUCCESS.equals(result.getRetFlag()));				
			JsonElement fileEle = json.get("file");
			if(fileEle != null){
				JsonObject fileObj = fileEle.getAsJsonObject();
				result.setFileName(fileObj.get("fileName").getAsString());
				result.setFileFormat(fileObj.get("fileFomat").getAsString());
				result.setFileSize(fileObj.get("fileSize").getAsString());
				result.setRootPath(fileObj.get("rootPath").getAsString());
				result.setFileRelativePath(fileObj.get("fileRelativePath").getAsString());
				result.setFileAbsolutePath(fileObj.get("fileAbsolutePath").getAsString());
			}
		} catch (Exception e) {
			LOGGER.error("********************上传文件出现异常：{}", e);
			result.setSuccess(false);
			result.setRetFlag(Constants.FILE_UPLOAD_FAILURE);
		}

		return result;
	}
	
	/**
	 * 
	 * 方法的描述: 从文件路径中解析出文件名
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-7-4 下午2:27:29
	 * @param path
	 * @return
	 */
    public static String getFileName(String path)
    {
    	String fileName=null;
    	if(path.contains("/"))
    	{
    		int index=path.lastIndexOf("/");
    		fileName=path.substring(index+1);
    	}else if(path.contains("\\"))
    	{
    		int index=path.lastIndexOf("\\");
    		fileName=path.substring(index+1);
    	}
    	return fileName;
    }
    
    /**
     * 方法的描述: 将smarthome-fileserver文件的全路径替换为只取文件名
     * @author: ykou  
     * @version: 0.1
     * @date: 2012-10-23 上午11:09:09
     * @param fullFilePath
     * @return
     */
	public static String replaceFilePath(String fullFilePath){
		if(StringUtils.isNotBlank(fullFilePath)){
			int index = fullFilePath.lastIndexOf("=");
			if(index>0){
				return fullFilePath.substring(index+1,fullFilePath.length());
			}
		}
		return null;
	}
	
	/**获取文件扩展名
	 * @param filename
	 * @return
	 */
	public static String getExtName(String filename) {   
        if ((filename != null) && (filename.length() > 0)) {   
            int dot = filename.lastIndexOf('.');   
            if ((dot >-1) && (dot < (filename.length() - 1))) {   
                return filename.substring(dot + 1);   
            }   
        }   
        return filename;   
    }
	
	/**获取文件名。不含扩展名
	 * @param filename
	 * @return
	 */
	public static String getPreFileName(String filename) {   
        if ((filename != null) && (filename.length() > 0)) {   
            int dot = filename.lastIndexOf('.');   
            if ((dot >-1) && (dot < (filename.length() - 1))) {   
                return filename.substring(0,dot);   
            }   
        }   
        return filename;   
    }
	
	/**
	 * 重命名文件名
	 * @param fileName
	 * @return newFileName
	 */
	public static String renameFileName(String fileName){
		//拼接新文件名:xxx-1221212112.xxx
		return getPreFileName(fileName)+"-"+System.currentTimeMillis()+"."+getExtName(fileName);
	}
	
    //测试
    public static void main(String [] arg)
    {
    	//String path="webUploadFile/image/示例图片_03_1341023380028.jpg";
    	//System.out.println(getFileName(path));
    	File file=new File("C:/复件 复件冰雹1.png");
    	System.out.println("0000------:"+getExtName("复件 复件冰雹1.png"));
    	System.out.println("0000------:"+getPreFileName("复件 复件冰雹1.png")+System.currentTimeMillis());
//    	uploadByService("http://192.168.0.94:9090/smarthome-fileserver" + Constants.UPLOAD_FILE_RELATIVE_PATH, 
//				"uploadFile", file, "复件 复件冰雹1.png", "192.168.0.67");
    }
}
