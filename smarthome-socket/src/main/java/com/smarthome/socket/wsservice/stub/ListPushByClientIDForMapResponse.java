package com.smarthome.socket.wsservice.stub;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for listPushByClientIDForMapResponse complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name="listPushByClientIDForMapResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="return" type="{http://service.cxfservice.smarthome.biencloud.com/}PushMapConvertor" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "listPushByClientIDForMapResponse", propOrder = { "_return" })
public class ListPushByClientIDForMapResponse {

	@XmlElement(name = "return")
	protected PushMapConvertor _return;

	/**
	 * Gets the value of the return property.
	 * 
	 * @return possible object is {@link PushMapConvertor }
	 * 
	 */
	public PushMapConvertor getReturn() {
		return _return;
	}

	/**
	 * Sets the value of the return property.
	 * 
	 * @param value
	 *            allowed object is {@link PushMapConvertor }
	 * 
	 */
	public void setReturn(PushMapConvertor value) {
		this._return = value;
	}

}
