package com.biencloud.smarthome.web.common.util;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

import com.biencloud.smarthome.web.template.VO.ModuleVO;
import com.biencloud.smarthome.web.template.VO.TemplateVO;

public class XmlUtil {
	public XmlUtil(){}

	public void creatXml() throws Exception {
		// 创建文档并设置文档的根元素节点 ：第一种方式
		// 创建文档并设置文档的根元素节点 ：第一种方式
		// Document document = DocumentHelper.createDocument();
		//
		// Element root = DocumentHelper.createElement("student");
		//
		// document.setRootElement(root);
		// 创建文档并设置文档的根元素节点 ：第二种方式
		//List<ModuleVO> list = template.getTemplate();
		
		Element root = DocumentHelper.createElement("template1");
		Document document = DocumentHelper.createDocument(root);
		
		Element name = root.addElement("name");
		Element module = root.addElement("module");
		Element sourceUrl = root.addElement("sourceUrl");
		
		Element moduleId = module.addElement("moduleId");
		Element type = module.addElement("type");
		Element imageUrl = module.addElement("imageUrl");
		Element linkUrl = module.addElement("linkUrl");
		Element topModuleId = module.addElement("topModuleId");
		Element leftModuleId = module.addElement("leftModuleId");
		sourceUrl.setText("http://soource.jpg");
		name.setText("t00001");
		moduleId.setName("id111111");
		type.setText("button");
		imageUrl.setText("http://img.jpg");
		linkUrl.setText("http://link.jpg");
		topModuleId.setText("top11111");
		leftModuleId.setText("left1111");
		
		XMLWriter xmlWriter = new XMLWriter();
		xmlWriter.write(document);

		OutputFormat format = new OutputFormat("    ", true);

		XMLWriter xmlWriter2 = new XMLWriter(new FileOutputStream(
				"E:\\template.xml"), format);
		xmlWriter2.write(document);

		xmlWriter2.close();
	}
	
	public static void creatXml(TemplateVO template) throws Exception {
		// 创建文档并设置文档的根元素节点 ：第一种方式
		// 创建文档并设置文档的根元素节点 ：第一种方式
		// Document document = DocumentHelper.createDocument();
		//
		// Element root = DocumentHelper.createElement("student");
		//
		// document.setRootElement(root);
		// 创建文档并设置文档的根元素节点 ：第二种方式
		
		Element root = DocumentHelper.createElement("template1");
		Document document = DocumentHelper.createDocument(root);
		Element name = root.addElement("name");
		//Element sourceUrl = root.addElement("sourceUrl");
		//sourceUrl.setText("http://soource.jpg");
		name.setText("t00001");
		List<ModuleVO> list = template.getTemplate();
		
		if(list != null && list.size() > 0){
			for(ModuleVO mo:list){
				Element module = root.addElement("module");
				Element moduleId = module.addElement("moduleId");
				Element type = module.addElement("type");
				Element imageUrl = module.addElement("imageUrl");
				Element linkUrl = module.addElement("linkUrl");
				Element topModuleId = module.addElement("topModuleId");
				Element leftModuleId = module.addElement("leftModuleId");
				
				moduleId.setText(mo.getModuleId());
				type.setText(mo.getType());
				imageUrl.setText(mo.getImageUrl());
				linkUrl.setText(mo.getLinkUrl());
				topModuleId.setText(mo.getTopModuleId());
				leftModuleId.setText(mo.getLeftModuleId());
			}
		}

		
		
		XMLWriter xmlWriter = new XMLWriter();
		xmlWriter.write(document);

		OutputFormat format = new OutputFormat("    ", true);

		XMLWriter xmlWriter2 = new XMLWriter(new FileOutputStream(
				"E:\\template.xml"), format);
		xmlWriter2.write(document);

		xmlWriter2.close();
	}
	
	public static String crtXml() throws IOException{
//		TemplateVO temp = new TemplateVO();
//		temp.setName("name1");
//		temp.setSourceUrl("source1");
//		ModuleVO vo1 = new ModuleVO();
//		ModuleVO vo2 = new ModuleVO();
//		vo1.setImageUrl("im1");
//		vo1.setLeftModuleId("lf1");
//		vo1.setLinkUrl("li1");
//		vo1.setModuleId("mo1");
//		vo1.setTopModuleId("to1");
//		vo1.setType("ty1");
//		vo2.setImageUrl("im2");
//		vo2.setLeftModuleId("lf2");
//		vo2.setLinkUrl("li2");
//		vo2.setModuleId("mo2");
//		vo2.setTopModuleId("to2");
//		vo2.setType("ty2");
//		List<ModuleVO> list = new ArrayList<ModuleVO>();
//		list.add(vo1);
//		list.add(vo2);
//		temp.setTemplate(list);
		//XmlUtil x = new XmlUtil();
		Element root = DocumentHelper.createElement("template1");
		Document document = DocumentHelper.createDocument(root);
		Element name = root.addElement("name");
		//Element sourceUrl = root.addElement("sourceUrl");
		//sourceUrl.setText("http://soource.jpg");
		
		name.setText("t00001");
		XMLWriter xmlWriter = new XMLWriter();
		xmlWriter.write(document);

		OutputFormat format = new OutputFormat("    ", true);

		XMLWriter xmlWriter2 = new XMLWriter(new FileOutputStream(
				"E:\\template.xml"), format);
		xmlWriter2.write(document);

		xmlWriter2.close();
		return "add";
	}
	

	public static void main(String[] args) throws Exception {
		TemplateVO temp = new TemplateVO();
		temp.setName("name1");
		temp.setSourceUrl("source1");
		ModuleVO vo1 = new ModuleVO();
		ModuleVO vo2 = new ModuleVO();
		vo1.setImageUrl("im1");
		vo1.setLeftModuleId("lf1");
		vo1.setLinkUrl("li1");
		vo1.setModuleId("mo1");
		vo1.setTopModuleId("to1");
		vo1.setType("ty1");
		vo2.setImageUrl("im2");
		vo2.setLeftModuleId("lf2");
		vo2.setLinkUrl("li2");
		vo2.setModuleId("mo2");
		vo2.setTopModuleId("to2");
		vo2.setType("ty2");
		List<ModuleVO> list = new ArrayList<ModuleVO>();
		list.add(vo1);
		list.add(vo2);
		temp.setTemplate(list);
		//XmlUtil.creatXml(temp);
		XmlUtil.crtXml();
	}
	
	
}