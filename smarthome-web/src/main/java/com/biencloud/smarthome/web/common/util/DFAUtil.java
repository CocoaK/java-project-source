package com.biencloud.smarthome.web.common.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DFAUtil {
private static final Logger logger = LoggerFactory.getLogger(DFAUtil.class);
	
	/**
	 * 根节点
	 */
	private static TreeNode rootNode = new TreeNode();
	
	/**
	 * 关键词缓存
	 */
	private static ByteBuffer keywordBuffer = ByteBuffer.allocate(1024);	
	
	/**
	 * 关键词编码
	 */
	private static String charset = "UTF-8";

	/**
	 * 创建DFA
	 * @param keywordList
	 * @throws UnsupportedEncodingException 
	 */
	public static void createKeywordTree(List<String> keywordList) throws UnsupportedEncodingException{
		
		for (String keyword : keywordList) {
			if(keyword == null) continue;
			keyword = keyword.trim();
			byte[] bytes = keyword.getBytes(charset);
			
			TreeNode tempNode = rootNode;
			//循环每个字节
			for (int i = 0; i < bytes.length; i++) {
				int index = bytes[i] & 0xff; //字符转换成数字
				TreeNode node = tempNode.getSubNode(index);
				
				if(node == null){ //没初始化
					node = new TreeNode();
					tempNode.setSubNode(index, node);
				}
				
				tempNode = node;
				
				if(i == bytes.length - 1){
					tempNode.setKeywordEnd(true);	 //关键词结束， 设置结束标志
					//logger.debug("DFA:{}", keyword);
				}
			}//end for
		}//end for
		
	}
	
	/**
	 * 搜索关键字
	 */
	public static String searchKeyword(String text) throws UnsupportedEncodingException,IOException{
		StringBuffer sb = new StringBuffer();
		//读取资源文件
		InputStream in = DFAUtil.class.getClassLoader().getResourceAsStream("words.properties");
        Properties properties = new Properties();
        BufferedReader bf = new BufferedReader(new InputStreamReader(in));
        properties.load(bf);
        Enumeration<?> enu = properties.propertyNames();
        while (enu.hasMoreElements()) {
            sb.append((String) enu.nextElement());    //读取所有properties里的词，以 | 分隔
        }
        sb.deleteCharAt(sb.length() - 1);
        String str = sb.toString();
        String[] arr = str.split(",");
        List<String> keywordList = Arrays.asList(arr);
        //默认下，properties文件读取编码： ISO8859-1
        //pattern = Pattern.compile(new String(patternBuf.toString().getBytes("ISO8859-1"), "UTF-8"));
        
        createKeywordTree(keywordList);	
		return searchKeyword(text.getBytes(charset));
	}
	
	/**
	 * 搜索关键字
	 */
	public static String searchKeyword(byte[] bytes){
		StringBuilder words = new StringBuilder();
		
		if(bytes == null || bytes.length == 0){
			return words.toString();
		}
		
		TreeNode tempNode = rootNode;
		int rollback = 0;	//回滚数
		int position = 0; //当前比较的位置
		
		while (position < bytes.length) {
			int index = bytes[position] & 0xFF;
			keywordBuffer.put(bytes[position]);	//写关键词缓存
			tempNode = tempNode.getSubNode(index);
			
			//当前位置的匹配结束
			if(tempNode == null){ 
				position = position - rollback; //回退 并测试下一个字节
				rollback = 0;
				tempNode = rootNode;  	//状态机复位
				keywordBuffer.clear();	//清空
			}
			else if(tempNode.isKeywordEnd()){  //是结束点 记录关键词
				keywordBuffer.flip();
				String keyword = Charset.forName(charset).decode(keywordBuffer).toString();
				logger.debug("Find keyword:{}", keyword);
				keywordBuffer.limit(keywordBuffer.capacity());
				
				if( words.length() == 0 ) words.append(keyword);
				else words.append(":").append(keyword);
				
				rollback = 1;	//遇到结束点  rollback 置为1
			}else{	
				rollback++;	//非结束点 回退数加1
			}
			
			position++;
		}
		
		return words.toString();
	}
	
	public void setCharset(String charset) {
		this.charset = charset;
	}
}


/**
 * 树节点
 * 每个节点包含一个长度为256的数组
 * @Date   2015-7-23 上午3:11:24
 */
class TreeNode {
	private static final int NODE_LEN = 256;
	
	/**
	 * true 关键词的终结 ； false 继续
	 */
	private boolean end = false; 
	
	private List<TreeNode> subNodes = new ArrayList<TreeNode>(NODE_LEN);
	
	public TreeNode(){
		for (int i = 0; i < NODE_LEN; i++) {
			subNodes.add(i, null);
		}
	}
	
	/**
	 * 向指定位置添加节点树
	 * @param index
	 * @param node
	 */
	public void setSubNode(int index, TreeNode node){
		subNodes.set(index, node);
	}
	
	public TreeNode getSubNode(int index){
		return subNodes.get(index);
	}
	

	public boolean isKeywordEnd() {
		return end;
	}

	public void setKeywordEnd(boolean end) {
		this.end = end;
	}
}
