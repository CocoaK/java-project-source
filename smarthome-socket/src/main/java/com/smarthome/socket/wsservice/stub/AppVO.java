package com.smarthome.socket.wsservice.stub;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for appVO complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name="appVO">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="conflictMac" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="dataServerIP" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="deviceNo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="fileServerIP" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="result" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="socketServerIP" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "appVO", propOrder = { "conflictMac", "dataServerIP",
		"deviceNo", "fileServerIP", "result", "socketServerIP" ,"newFlag"})
public class AppVO {

	protected String conflictMac;
	protected String dataServerIP;
	protected String deviceNo;
	protected String fileServerIP;
	protected String result;
	protected String socketServerIP;
	protected boolean newFlag;

	/**
	 * Gets the value of the conflictMac property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getConflictMac() {
		return conflictMac;
	}

	/**
	 * Sets the value of the conflictMac property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setConflictMac(String value) {
		this.conflictMac = value;
	}

	/**
	 * Gets the value of the dataServerIP property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getDataServerIP() {
		return dataServerIP;
	}

	/**
	 * Sets the value of the dataServerIP property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setDataServerIP(String value) {
		this.dataServerIP = value;
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
	 * Gets the value of the fileServerIP property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getFileServerIP() {
		return fileServerIP;
	}

	/**
	 * Sets the value of the fileServerIP property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setFileServerIP(String value) {
		this.fileServerIP = value;
	}

	/**
	 * Gets the value of the result property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getResult() {
		return result;
	}

	/**
	 * Sets the value of the result property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setResult(String value) {
		this.result = value;
	}

	/**
	 * Gets the value of the socketServerIP property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getSocketServerIP() {
		return socketServerIP;
	}

	/**
	 * Sets the value of the socketServerIP property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setSocketServerIP(String value) {
		this.socketServerIP = value;
	}

	public boolean isNewFlag() {
		return newFlag;
	}

	public void setNewFlag(boolean newFlag) {
		this.newFlag = newFlag;
	}
	
}
