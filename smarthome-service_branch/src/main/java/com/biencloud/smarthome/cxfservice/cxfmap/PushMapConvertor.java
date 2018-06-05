package com.biencloud.smarthome.cxfservice.cxfmap;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

import com.biencloud.smarthome.service.monitor.model.SceneDevice;
import com.biencloud.smarthome.service.push.model.Push;
/**
 * 
 * 类名称：MapConvertor 
 * 类描述： Map类型转换
 * @author: kouy  
 * @version: 0.1
 * @date: 2012-6-6 下午3:18:32
 */
@XmlType(name = "PushMapConvertor")
@XmlAccessorType(XmlAccessType.FIELD)
public class PushMapConvertor {
	private List<MapEntry> entries = new ArrayList<MapEntry>();

	public void addEntry(MapEntry entry) {
		entries.add(entry);
	}

	public List<MapEntry> getEntries() {
		return entries;
	}

	public static class MapEntry {

		private String key;

		private List<Push> value;

		public MapEntry() {
			super();
		}

		public MapEntry(Map.Entry<String, List<Push>> entry) {
			super();
			this.key = entry.getKey();
			this.value = entry.getValue();
		}

		public MapEntry(String key,List<Push> value) {
			super();
			this.key = key;
			this.value = value;
		}

		public String getKey() {
			return key;
		}

		public void setKey(String key) {
			this.key = key;
		}

		public List<Push> getValue() {
			return value;
		}

		public void setValue(List<Push> value) {
			this.value = value;
		}
	}

}
