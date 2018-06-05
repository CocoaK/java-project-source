package com.biencloud.smarthome.service.common.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Date;

/**
 * 
 * 类名称：FileUtil 类描述：文件工具类
 * 
 * @author: kouy
 * @version: 0.1
 * @date: 2012-5-23 下午2:11:12
 */
public class FileUtil {
	/**
	 * 
	 * 方法的描述: 新建目录
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-5-23 下午2:13:30
	 * @param folderPath
	 */
	public static void newFolder(String folderPath) {
		try {
			String filePath = folderPath;
			filePath = filePath.toString();
			java.io.File myFilePath = new java.io.File(filePath);
			if (!myFilePath.exists()) {
				myFilePath.mkdir();
			}
		} catch (Exception e) {
			//System.out.println("新建目录操作出错");
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * 方法的描述: 新建文件
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-5-23 下午2:13:46
	 * @param filePathAndName
	 * @param fileContent
	 */
	public static void newFile(String filePathAndName, String fileContent) {

		try {
			String filePath = filePathAndName;
			filePath = filePath.toString(); // 取的路径及文件名
			File myFilePath = new File(filePath);
			/** 如果文件不存在就建一个新文件 */
			if (!myFilePath.exists()) {
				myFilePath.createNewFile();
			}
			FileWriter resultFile = new FileWriter(myFilePath); // 用来写入字符文件的便捷类,
																// 在给出 File
																// 对象的情况下构造一个
																// FileWriter 对象
			PrintWriter myFile = new PrintWriter(resultFile); // 向文本输出流打印对象的格式化表示形式,使用指定文件创建不具有自动行刷新的新
																// PrintWriter。
			String strContent = fileContent;
			myFile.println(strContent);
			resultFile.close();

		} catch (Exception e) {
			//System.out.println("新建文件操作出错");
			e.printStackTrace();

		}

	}

	/**
	 * 
	 * 方法的描述:删除文件 
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-5-23 下午2:14:20
	 * @param filePathAndName
	 */
	public static void delFile(String filePathAndName) {
		try {
			String filePath = filePathAndName;
			filePath = filePath.toString();
			java.io.File myDelFile = new java.io.File(filePath);
			myDelFile.delete();

		} catch (Exception e) {
			System.out.println("删除文件操作出错");
			e.printStackTrace();

		}

	}

	/**
	 * 
	 * 方法的描述: 删除文件夹
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-5-23 下午2:14:32
	 * @param folderPath
	 */
	public static void delFolder(String folderPath) {
		try {
			delAllFile(folderPath); // 删除完里面所有内容
			String filePath = folderPath;
			filePath = filePath.toString();
			java.io.File myFilePath = new java.io.File(filePath);
			myFilePath.delete(); // 删除空文件夹

		} catch (Exception e) {
			//System.out.println("删除文件夹操作出错");
			e.printStackTrace();

		}

	}

	/**
	 * 
	 * 方法的描述: 删除文件夹里面的所有文件
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-5-23 下午2:14:43
	 * @param path
	 */
	public static void delAllFile(String path) {
		File file = new File(path);
		if (!file.exists()) {
			return;
		}
		if (!file.isDirectory()) {
			return;
		}
		String[] tempList = file.list();
		File temp = null;
		for (int i = 0; i < tempList.length; i++) {
			if (path.endsWith(File.separator)) {
				temp = new File(path + tempList[i]);
			} else {
				temp = new File(path + File.separator + tempList[i]);
			}
			if (temp.isFile()) {
				temp.delete();
			}
			if (temp.isDirectory()) {
				delAllFile(path + "/" + tempList[i]);// 先删除文件夹里面的文件
				delFolder(path + "/" + tempList[i]);// 再删除空文件夹
			}
		}
	}

	/**
	 * 
	 * 方法的描述: 复制单个文件
	 * 
	 * @author: kouy
	 * @version: 0.1
	 * @date: 2012-5-23 下午2:12:03
	 * @param oldPath
	 * @param newPath
	 */
	public static void copyFile(String oldPath, String newPath) {
		try {
			
			int byteread = 0;
			File oldfile = new File(oldPath);
			if (oldfile.exists()) { // 文件存在时
				InputStream inStream = new FileInputStream(oldPath); // 读入原文件
				FileOutputStream fs = new FileOutputStream(newPath);
				byte[] buffer = new byte[1444];
				// int length;
				while ((byteread = inStream.read(buffer)) != -1) {					
					fs.write(buffer, 0, byteread);
				}
				inStream.close();
			}
		} catch (Exception e) {
			//System.out.println("复制单个文件操作出错");
			e.printStackTrace();

		}

	}
	
	/**
	 * 
	 * 方法的描述: 复制整个文件夹内容
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-5-23 下午2:14:58
	 * @param oldPath
	 * @param newPath
	 */
	public static void copyFolder(String oldPath, String newPath) {

		try {
			(new File(newPath)).mkdirs(); // 如果文件夹不存在 则建立新文件夹
			File a = new File(oldPath);
			String[] file = a.list();
			File temp = null;
			for (int i = 0; i < file.length; i++) {
				if (oldPath.endsWith(File.separator)) {
					temp = new File(oldPath + file[i]);
				} else {
					temp = new File(oldPath + File.separator + file[i]);
				}

				if (temp.isFile()) {
					FileInputStream input = new FileInputStream(temp);
					FileOutputStream output = new FileOutputStream(newPath + "/" + (temp.getName()).toString());
					byte[] b = new byte[1024 * 5];
					int len;
					while ((len = input.read(b)) != -1) {
						output.write(b, 0, len);
					}
					output.flush();
					output.close();
					input.close();
				}
				if (temp.isDirectory()) {// 如果是子文件夹
					copyFolder(oldPath + "/" + file[i], newPath + "/" + file[i]);
				}
			}
		} catch (Exception e) {
			//System.out.println("复制整个文件夹内容操作出错");
			e.printStackTrace();

		}

	}

	/**
	 * 
	 * 方法的描述: 移动文件到指定目录
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-5-23 下午2:15:11
	 * @param oldPath
	 * @param newPath
	 */
	public  static void moveFile(String oldPath, String newPath) {
		copyFile(oldPath, newPath);
		delFile(oldPath);

	}

	/**
	 * 
	 * 方法的描述:移动文件到指定目录
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-5-23 下午2:15:22
	 * @param oldPath
	 * @param newPath
	 */
	public static void moveFolder(String oldPath, String newPath) {
		copyFolder(oldPath, newPath);
		delFolder(oldPath);

	}

	/**
	 * 
	 * 方法的描述: 根据最后修改时间删除文件夹里面的所有文件
	 * @author: ykou  
	 * @version: 0.1
	 * @date: 2012-09-03 下午2:14:43
	 * @param path,time
	 */
	public static void delAllFileByLastModifyTime(String path,Date time) {
		File file = new File(path);
		if (!file.exists()) {
			return;
		}
		if (!file.isDirectory()) {
			return;
		}
		String[] tempList = file.list();
		File temp = null;
		for (int i = 0; i < tempList.length; i++) {
			if (path.endsWith(File.separator)) {
				temp = new File(path + tempList[i]);
			} else {
				temp = new File(path + File.separator + tempList[i]);
			}
			if (temp.isFile() && temp.lastModified()<time.getTime()) {
				try{
					temp.delete();
				}catch(Exception e){
					System.out.println("文件【"+temp+"】删除失败！");
				}
			}
			if (temp.isDirectory()) {
				delAllFileByLastModifyTime(path +tempList[i],time);
			}
		}
	}

	

}
