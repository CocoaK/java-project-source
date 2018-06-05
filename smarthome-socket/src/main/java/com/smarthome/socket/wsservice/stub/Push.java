package com.smarthome.socket.wsservice.stub;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;

/**
 * <p>
 * Java class for push complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name="push">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="addTime" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="pushClientId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="pushContent" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="pushDescription" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="pushKind" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="pushName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="pushVersion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="size" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "push", propOrder = { "addTime", "id", "pushClientId",
		"pushContent", "pushDescription", "pushKind", "pushName",
		"pushVersion", "size" })
public class Push {

	@XmlSchemaType(name = "dateTime")
	protected XMLGregorianCalendar addTime;
	protected Long id;
	protected String pushClientId;
	protected String pushContent;
	protected String pushDescription;
	protected String pushKind;
	protected String pushName;
	protected String pushVersion;
	protected Long size;

	/**
	 * Gets the value of the addTime property.
	 * 
	 * @return possible object is {@link XMLGregorianCalendar }
	 * 
	 */
	public XMLGregorianCalendar getAddTime() {
		return addTime;
	}

	/**
	 * Sets the value of the addTime property.
	 * 
	 * @param value
	 *            allowed object is {@link XMLGregorianCalendar }
	 * 
	 */
	public void setAddTime(XMLGregorianCalendar value) {
		this.addTime = value;
	}

	/**
	 * Gets the value of the id property.
	 * 
	 * @return possible object is {@link Long }
	 * 
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Sets the value of the id property.
	 * 
	 * @param value
	 *            allowed object is {@link Long }
	 * 
	 */
	public void setId(Long value) {
		this.id = value;
	}

	/**
	 * Gets the value of the pushClientId property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getPushClientId() {
		return pushClientId;
	}

	/**
	 * Sets the value of the pushClientId property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setPushClientId(String value) {
		this.pushClientId = value;
	}

	/**
	 * Gets the value of the pushContent property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getPushContent() {
		return pushContent;
	}

	/**
	 * Sets the value of the pushContent property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setPushContent(String value) {
		this.pushContent = value;
	}

	/**
	 * Gets the value of the pushDescription property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getPushDescription() {
		return pushDescription;
	}

	/**
	 * Sets the value of the pushDescription property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setPushDescription(String value) {
		this.pushDescription = value;
	}

	/**
	 * Gets the value of the pushKind property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getPushKind() {
		return pushKind;
	}

	/**
	 * Sets the value of the pushKind property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setPushKind(String value) {
		this.pushKind = value;
	}

	/**
	 * Gets the value of the pushName property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getPushName() {
		return pushName;
	}

	/**
	 * Sets the value of the pushName property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setPushName(String value) {
		this.pushName = value;
	}

	/**
	 * Gets the value of the pushVersion property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getPushVersion() {
		return pushVersion;
	}

	/**
	 * Sets the value of the pushVersion property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setPushVersion(String value) {
		this.pushVersion = value;
	}

	/**
	 * Gets the value of the size property.
	 * 
	 * @return possible object is {@link Long }
	 * 
	 */
	public Long getSize() {
		return size;
	}

	/**
	 * Sets the value of the size property.
	 * 
	 * @param value
	 *            allowed object is {@link Long }
	 * 
	 */
	public void setSize(Long value) {
		this.size = value;
	}

}
