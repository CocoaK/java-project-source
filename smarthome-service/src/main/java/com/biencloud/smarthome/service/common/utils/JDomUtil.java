package com.biencloud.smarthome.service.common.utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringReader;
import org.jdom.Document;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;
import org.xml.sax.InputSource;

/**
 * 
 * description: Dom4j工具�?
 * 
 * @fileName:Dom4jUtil.java
 * @createTime:2010-11-24 上午10:46:45
 * @author:kouy
 * @version 1.0.0
 * 
 */
public class JDomUtil {

	/**
	 * 
	 * description: 读取xml文件生成Document对象
	 * 
	 * @param filePath
	 * @return
	 * @throws DocumentException
	 *             Document
	 * @author:kouy
	 * @throws IOException
	 * @throws JDOMException
	 * @since 1.0.0
	 */
	public static Document readXML(String filePath) throws JDOMException, IOException

	{
		Document document = null;

		File file = new File(filePath);
		if (file.exists()) {
			SAXBuilder reader = new SAXBuilder();
			document = reader.build(file);

		}
		return document;
	}

	/**
	 * 
	 * description: 保存文件
	 * 
	 * @param savePath
	 *            ：保存路�?
	 * @param doc
	 *            ：文档对�?
	 * @param encoding
	 *            编码
	 * @author:kouy
	 * @since 1.0.0
	 */
	public static void writerXML(String savePath, Document doc, String encoding) {

		try {

			// XMLOutputter outputter = new XMLOutputter();
			// OutputFormat format = OutputFormat.createPrettyPrint();
			Format format = Format.getCompactFormat();
			// 设置XML文件的编码格�?
			format.setEncoding(encoding);
			XMLOutputter outp = new XMLOutputter(); // 利用Format类
			outp.setFormat(format);
			format.setIndent("  ");            //设置xml文件的缩进
			FileWriter writer = new FileWriter(savePath);
			//outp.output(doc, new FileOutputStream(savePath));
			outp.output(doc, writer);
			
			// XMLWriter writer = new XMLWriter(new FileWriter(savePath),
			// format);
			// XMLWriter writer = new XMLWriter(new FileWriter(savePath));
			// writer.write(doc);
			 writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void writerXML(String savePath, Document doc) {

		try {
			/*
			 * FileOutputStream outputStream = new FileOutputStream(savePath);
			 * OutputFormat format = OutputFormat.createPrettyPrint();
			 * format.setEncoding("utf-8"); XMLWriter writer = new
			 * XMLWriter(format); writer.setOutputStream(outputStream);
			 * writer.write(doc); writer.close();
			 */
			Format format = Format.getPrettyFormat();
			// 设置XML文件的编码格�?
			format.setEncoding("utf-8");
			XMLOutputter outp = new XMLOutputter(Format.getPrettyFormat()); // 利用Format类
			outp.setFormat(format);
			//outp.output(doc, new FileOutputStream(savePath));
			FileWriter writer = new FileWriter(savePath);
			//outp.output(doc, new FileOutputStream(savePath));
			outp.output(doc, writer);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * 
	 * description: 将xml字符串转为xml文件
	 * 
	 * @param xml
	 * @param filePath
	 * @throws DocumentException
	 *             void
	 * @author:kouy
	 * @throws IOException
	 * @throws JDOMException
	 * @since 1.0.0
	 */
	public static void xmlStringToXmlFile(String xml, String filePath) throws JDOMException, IOException  {
		Document doc = xmlStringToXML(xml);
		if (doc != null) {
			// writerXML(filePath, doc, "utf-8");
			writerXML(filePath, doc);
		}
	}

	/**
	 * 
	 * description: 将xml字符串转为Document对象
	 * 
	 * @param xml
	 * @return Document
	 * @author:kouy
	 * @throws IOException
	 * @throws JDOMException
	 * @throws DocumentException
	 * @since 1.0.0
	 */
	public static Document xmlStringToXML(String xml) throws JDOMException, IOException {
		if (xml != null && !"".equals(xml)) {
			StringReader read = new StringReader(xml);
			// 创建新的输入源SAX 解析器将使用 InputSource 对象来确定如何读取 XML 输入
			InputSource source = new InputSource(read);
			// 创建一个新的SAXBuilder
			SAXBuilder sb = new SAXBuilder();

			// 通过输入源构造一个Document
			Document doc = sb.build(source);

			return doc;

		}
		return null;

	}

	/**
	 * 
	 * description:将xml文件转为字符�?
	 * 
	 * @param filePath
	 * @return String
	 * @author:kouy
	 * @throws IOException 
	 * @throws JDOMException 
	 * @since 1.0.0
	 */
	public static String readXmlFileToString(String filePath) throws JDOMException, IOException {
		String xmlString = "";
		if (filePath != null) {
			
				Document doc = readXML(filePath);
				if (doc != null) {
					XMLOutputter out = new XMLOutputter();
					out.setFormat(Format.getCompactFormat().setEncoding("utf-8"));
					xmlString = out.outputString(doc);
					
				}
			
		}
		return xmlString;
	}

	public static void main(String[] args) throws JDOMException, IOException {
//		String s = readXmlFileToString("D:/xmltest.xml");
//		System.out.println(s);
	}
}
