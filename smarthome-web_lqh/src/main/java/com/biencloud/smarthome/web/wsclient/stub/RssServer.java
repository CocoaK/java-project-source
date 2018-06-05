
package com.biencloud.smarthome.web.wsclient.stub;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for rssServer complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="rssServer">
 *   &lt;complexContent>
 *     &lt;extension base="{http://service.cxfservice.smarthome.biencloud.com/}baseEntity">
 *       &lt;sequence>
 *         &lt;element name="createdTime" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="createdUser" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="rssId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="rssName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="rssServerCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="serverUrl" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="status" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="userLogin" type="{http://service.cxfservice.smarthome.biencloud.com/}login" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "rssServer", propOrder = {
    "createdTime",
    "createdUser",
    "rssId",
    "rssName",
    "rssServerCode",
    "serverUrl",
    "status",
    "userLogin"
})
public class RssServer
    extends BaseEntity
{

    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar createdTime;
    protected String createdUser;
    protected Long rssId;
    protected String rssName;
    protected String rssServerCode;
    protected String serverUrl;
    protected String status;
    protected Login userLogin;

    /**
     * Gets the value of the createdTime property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getCreatedTime() {
        return createdTime;
    }

    /**
     * Sets the value of the createdTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setCreatedTime(XMLGregorianCalendar value) {
        this.createdTime = value;
    }

    /**
     * Gets the value of the createdUser property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCreatedUser() {
        return createdUser;
    }

    /**
     * Sets the value of the createdUser property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCreatedUser(String value) {
        this.createdUser = value;
    }

    /**
     * Gets the value of the rssId property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getRssId() {
        return rssId;
    }

    /**
     * Sets the value of the rssId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setRssId(Long value) {
        this.rssId = value;
    }

    /**
     * Gets the value of the rssName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRssName() {
        return rssName;
    }

    /**
     * Sets the value of the rssName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRssName(String value) {
        this.rssName = value;
    }

    /**
     * Gets the value of the rssServerCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRssServerCode() {
        return rssServerCode;
    }

    /**
     * Sets the value of the rssServerCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRssServerCode(String value) {
        this.rssServerCode = value;
    }

    /**
     * Gets the value of the serverUrl property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getServerUrl() {
        return serverUrl;
    }

    /**
     * Sets the value of the serverUrl property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setServerUrl(String value) {
        this.serverUrl = value;
    }

    /**
     * Gets the value of the status property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStatus() {
        return status;
    }

    /**
     * Sets the value of the status property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStatus(String value) {
        this.status = value;
    }

    /**
     * Gets the value of the userLogin property.
     * 
     * @return
     *     possible object is
     *     {@link Login }
     *     
     */
    public Login getUserLogin() {
        return userLogin;
    }

    /**
     * Sets the value of the userLogin property.
     * 
     * @param value
     *     allowed object is
     *     {@link Login }
     *     
     */
    public void setUserLogin(Login value) {
        this.userLogin = value;
    }

}
