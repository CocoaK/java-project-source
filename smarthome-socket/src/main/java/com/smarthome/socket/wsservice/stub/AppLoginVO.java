package com.smarthome.socket.wsservice.stub;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for appLoginVO complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name="appLoginVO">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="areaNo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="buildingNo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="deviceName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="deviceNo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="devicePassword" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="deviceType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="houseNo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="houseState" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ip" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ipState" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="mac" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="position" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="regionNo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="unitNo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "appLoginVO", propOrder = { "areaNo", "buildingNo",
		"deviceName", "deviceNo", "devicePassword", "deviceType", "houseNo",
		"houseState", "ip", "ipState", "mac", "position", "regionNo", "unitNo","version","sipid" })
public class AppLoginVO {

	protected String areaNo;
	protected String buildingNo;
	protected String deviceName;
	protected String deviceNo;
	protected String devicePassword;
	protected String deviceType;
	protected String houseNo;
	protected String houseState;
	protected String ip;
	protected String ipState;
	protected String mac;
	protected String position;
	protected String regionNo;
	protected String unitNo;
	protected String version;
	protected String sipid;

	/**
	 * Gets the value of the areaNo property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getAreaNo() {
		return areaNo;
	}

	/**
	 * Sets the value of the areaNo property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setAreaNo(String value) {
		this.areaNo = value;
	}

	/**
	 * Gets the value of the buildingNo property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getBuildingNo() {
		return buildingNo;
	}

	/**
	 * Sets the value of the buildingNo property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setBuildingNo(String value) {
		this.buildingNo = value;
	}

	/**
	 * Gets the value of the deviceName property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getDeviceName() {
		return deviceName;
	}

	/**
	 * Sets the value of the deviceName property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setDeviceName(String value) {
		this.deviceName = value;
	}

	/**
	 * Gets the value of the deviceNo property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getDeviceNo() {
		return deviceNo;
	}

	/**
	 * Sets the value of the deviceNo property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setDeviceNo(String value) {
		this.deviceNo = value;
	}

	/**
	 * Gets the value of the devicePassword property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getDevicePassword() {
		return devicePassword;
	}

	/**
	 * Sets the value of the devicePassword property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setDevicePassword(String value) {
		this.devicePassword = value;
	}

	/**
	 * Gets the value of the deviceType property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getDeviceType() {
		return deviceType;
	}

	/**
	 * Sets the value of the deviceType property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setDeviceType(String value) {
		this.deviceType = value;
	}

	/**
	 * Gets the value of the houseNo property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getHouseNo() {
		return houseNo;
	}

	/**
	 * Sets the value of the houseNo property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setHouseNo(String value) {
		this.houseNo = value;
	}

	/**
	 * Gets the value of the houseState property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getHouseState() {
		return houseState;
	}

	/**
	 * Sets the value of the houseState property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setHouseState(String value) {
		this.houseState = value;
	}

	/**
	 * Gets the value of the ip property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getIp() {
		return ip;
	}

	/**
	 * Sets the value of the ip property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setIp(String value) {
		this.ip = value;
	}

	/**
	 * Gets the value of the ipState property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getIpState() {
		return ipState;
	}

	/**
	 * Sets the value of the ipState property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setIpState(String value) {
		this.ipState = value;
	}

	/**
	 * Gets the value of the mac property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getMac() {
		return mac;
	}

	/**
	 * Sets the value of the mac property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setMac(String value) {
		this.mac = value;
	}

	/**
	 * Gets the value of the position property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getPosition() {
		return position;
	}

	/**
	 * Sets the value of the position property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setPosition(String value) {
		this.position = value;
	}

	/**
	 * Gets the value of the regionNo property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getRegionNo() {
		return regionNo;
	}

	/**
	 * Sets the value of the regionNo property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setRegionNo(String value) {
		this.regionNo = value;
	}

	/**
	 * Gets the value of the unitNo property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getUnitNo() {
		return unitNo;
	}

	/**
	 * Sets the value of the unitNo property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setUnitNo(String value) {
		this.unitNo = value;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getSipid() {
		return sipid;
	}

	public void setSipid(String sipid) {
		this.sipid = sipid;
	}

}
