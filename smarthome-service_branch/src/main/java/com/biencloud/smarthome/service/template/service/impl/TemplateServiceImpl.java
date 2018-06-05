package com.biencloud.smarthome.service.template.service.impl;

import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Vector;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;

import com.biencloud.smarthome.service.base.service.impl.BaseService;
import com.biencloud.smarthome.service.template.model.Template;
import com.biencloud.smarthome.service.template.service.ITemplateService;

public class TemplateServiceImpl extends BaseService<Template, String>
		implements ITemplateService {
	Vector students_Vector;

	@Override
	public void createTemplate() {

	}

	// 写入XML文件
	public static void callWriteXmlFile(Document doc, Writer w, String encoding) {
		try {
			Source source = new DOMSource(doc);

			Result result = new StreamResult(w);

			Transformer xformer = TransformerFactory.newInstance()
					.newTransformer();
			xformer.setOutputProperty(OutputKeys.ENCODING, encoding);
			xformer.transform(source, result);

		} catch (TransformerConfigurationException e) {
			e.printStackTrace();
		} catch (TransformerException e) {
			e.printStackTrace();
		}
	}

/*	private void writeXMLFile(String outfile) {
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = null;
		try {
			builder = dbf.newDocumentBuilder();
		} catch (Exception e) {
		}
		Document doc = builder.newDocument();

		Element root = doc.createElement("学生花名册");
		doc.appendChild(root); // 将根元素添加到文档上

		// 获取学生信息
		for (int i = 0; i < students_Vector.size(); i++) {
			Template s = (Template) students_Vector.get(i);
			// 创建一个学生
			Element stu = doc.createElement("学生");
			stu.setAttribute("性别", s.getSex());
			root.appendChild(stu);// 添加属性

			// 创建文本姓名节点
			Element name = doc.createElement("姓名");
			stu.appendChild(name);
			Text tname = doc.createTextNode(s.getName());
			name.appendChild(tname);

			// 创建文本年龄节点
			Element age = doc.createElement("年龄");
			stu.appendChild(age); // 将age添加到学生节点上
			Text tage = doc.createTextNode(String.valueOf(s.getAge()));
			age.appendChild(tage); // 将文本节点放在age节点上
		}
		try {
			FileOutputStream fos = new FileOutputStream(outfile);
			OutputStreamWriter outwriter = new OutputStreamWriter(fos);
			// ((XmlDocument)doc).write(outwriter); //出错！
			callWriteXmlFile(doc, outwriter, "gb2312");
			outwriter.close();
			fos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void readXml() {
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = dbf.newDocumentBuilder();
		Document doc = builder.parse(""); // 获取到xml文件

		// 下面开始读取
		Element root = doc.getDocumentElement(); // 获取根元素
		NodeList students = root.getElementsByTagName("学生");
		students_Vector = new Vector();
		for (int i = 0; i < students.getLength(); i++) {
			// 一次取得每一个学生元素
			Element ss = (Element) students.item(i);

			// 创建一个学生的实例
			Template template = new Template();
			template.setSex(ss.getAttribute("性别"));

			NodeList names = ss.getElementsByTagName("姓名");
			Element e = (Element) names.item(0);
			Node t = e.getFirstChild();
			template.setName(t.getNodeValue());

			NodeList ages = ss.getElementsByTagName("年龄");
			e = (Element) ages.item(0);
			t = e.getFirstChild();
			template.setAge(Integer.parseInt(t.getNodeValue()));

			students_Vector.add(template);
		}
	}
*/
}
