
package com.biencloud.smarthome.web.wsclient.stub;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for operationLog complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="operationLog">
 *   &lt;complexContent>
 *     &lt;extension base="{http://service.cxfservice.smarthome.biencloud.com/}baseEntity">
 *       &lt;sequence>
 *         &lt;element name="beginTime" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="endTime" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="ip" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="logId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="menuCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="operateResult" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="operateTime" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="operateUser" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="operationCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="remark" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "operationLog", propOrder = {
    "beginTime",
    "endTime",
    "ip",
    "logId",
    "menuCode",
    "operateResult",
    "operateTime",
    "operateUser",
    "operationCode",
    "remark"
})
public class OperationLog
    extends BaseEntity
{

    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar beginTime;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar endTime;
    protected String ip;
    protected String logId;
    protected String menuCode;
    protected int operateResult;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar operateTime;
    protected String operateUser;
    protected String operationCode;
    protected String remark;

    /**
     * Gets the value of the beginTime property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getBeginTime() {
        return beginTime;
    }

    /**
     * Sets the value of the beginTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setBeginTime(XMLGregorianCalendar value) {
        this.beginTime = value;
    }

    /**
     * Gets the value of the endTime property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getEndTime() {
        return endTime;
    }

    /**
     * Sets the value of the endTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setEndTime(XMLGregorianCalendar value) {
        this.endTime = value;
    }

    /**
     * Gets the value of the ip property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIp() {
        return ip;
    }

    /**
     * Sets the value of the ip property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIp(String value) {
        this.ip = value;
    }

    /**
     * Gets the value of the logId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLogId() {
        return logId;
    }

    /**
     * Sets the value of the logId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLogId(String value) {
        this.logId = value;
    }

    /**
     * Gets the value of the menuCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMenuCode() {
        return menuCode;
    }

    /**
     * Sets the value of the menuCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMenuCode(String value) {
        this.menuCode = value;
    }

    /**
     * Gets the value of the operateResult property.
     * 
     */
    public int getOperateResult() {
        return operateResult;
    }

    /**
     * Sets the value of the operateResult property.
     * 
     */
    public void setOperateResult(int value) {
        this.operateResult = value;
    }

    /**
     * Gets the value of the operateTime property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getOperateTime() {
        return operateTime;
    }

    /**
     * Sets the value of the operateTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setOperateTime(XMLGregorianCalendar value) {
        this.operateTime = value;
    }

    /**
     * Gets the value of the operateUser property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOperateUser() {
        return operateUser;
    }

    /**
     * Sets the value of the operateUser property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOperateUser(String value) {
        this.operateUser = value;
    }

    /**
     * Gets the value of the operationCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOperationCode() {
        return operationCode;
    }

    /**
     * Sets the value of the operationCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOperationCode(String value) {
        this.operationCode = value;
    }

    /**
     * Gets the value of the remark property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRemark() {
        return remark;
    }

    /**
     * Sets the value of the remark property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRemark(String value) {
        this.remark = value;
    }

}
