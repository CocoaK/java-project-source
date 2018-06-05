package com.biencloud.smarthome.cxfservice.cxfmap;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

import com.biencloud.smarthome.service.monitor.model.SceneDevice;
/**
 * 
 * 类名称：MapConvertor 
 * 类描述： Map类型转换
 * @author: kouy  
 * @version: 0.1
 * @date: 2012-6-6 下午3:18:32
 */
@XmlType(name = "MapConvertor")
@XmlAccessorType(XmlAccessType.FIELD)
public class MapConvertor {
	private List<MapEntry> entries = new ArrayList<MapEntry>();

	public void addEntry(MapEntry entry) {
		entries.add(entry);
	}

	public List<MapEntry> getEntries() {
		return entries;
	}

	public static class MapEntry {

		private String key;

		private List<SceneDevice> value;

		public MapEntry() {
			super();
		}

		public MapEntry(Map.Entry<String, List<SceneDevice>> entry) {
			super();
			this.key = entry.getKey();
			this.value = entry.getValue();
		}

		public MapEntry(String key,List<SceneDevice> value) {
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

		public List<SceneDevice> getValue() {
			return value;
		}

		public void setValue(List<SceneDevice> value) {
			this.value = value;
		}
	}

}
