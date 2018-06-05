
package com.biencloud.smarthome.web.wsclient.stub;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for systemGroup complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="systemGroup">
 *   &lt;complexContent>
 *     &lt;extension base="{http://service.cxfservice.smarthome.biencloud.com/}baseEntity">
 *       &lt;sequence>
 *         &lt;element name="createTime" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="createUser" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="deep" type="{http://www.w3.org/2001/XMLSchema}short" minOccurs="0"/>
 *         &lt;element name="desc" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="groupEqualName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="groupName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="groupNo" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="groupParentNo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="hasChild" type="{http://www.w3.org/2001/XMLSchema}short" minOccurs="0"/>
 *         &lt;element name="updateTime" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="updateUser" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "systemGroup", propOrder = {
    "createTime",
    "createUser",
    "deep",
    "desc",
    "groupEqualName",
    "groupName",
    "groupNo",
    "groupParentNo",
    "hasChild",
    "updateTime",
    "updateUser"
})
public class SystemGroup
    extends BaseEntity
{

    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar createTime;
    protected String createUser;
    protected Short deep;
    protected String desc;
    protected String groupEqualName;
    protected String groupName;
    protected Long groupNo;
    protected String groupParentNo;
    protected Short hasChild;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar updateTime;
    protected String updateUser;

    /**
     * Gets the value of the createTime property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getCreateTime() {
        return createTime;
    }

    /**
     * Sets the value of the createTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setCreateTime(XMLGregorianCalendar value) {
        this.createTime = value;
    }

    /**
     * Gets the value of the createUser property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCreateUser() {
        return createUser;
    }

    /**
     * Sets the value of the createUser property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCreateUser(String value) {
        this.createUser = value;
    }

    /**
     * Gets the value of the deep property.
     * 
     * @return
     *     possible object is
     *     {@link Short }
     *     
     */
    public Short getDeep() {
        return deep;
    }

    /**
     * Sets the value of the deep property.
     * 
     * @param value
     *     allowed object is
     *     {@link Short }
     *     
     */
    public void setDeep(Short value) {
        this.deep = value;
    }

    /**
     * Gets the value of the desc property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDesc() {
        return desc;
    }

    /**
     * Sets the value of the desc property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDesc(String value) {
        this.desc = value;
    }

    /**
     * Gets the value of the groupEqualName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGroupEqualName() {
        return groupEqualName;
    }

    /**
     * Sets the value of the groupEqualName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGroupEqualName(String value) {
        this.groupEqualName = value;
    }

    /**
     * Gets the value of the groupName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGroupName() {
        return groupName;
    }

    /**
     * Sets the value of the groupName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGroupName(String value) {
        this.groupName = value;
    }

    /**
     * Gets the value of the groupNo property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getGroupNo() {
        return groupNo;
    }

    /**
     * Sets the value of the groupNo property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setGroupNo(Long value) {
        this.groupNo = value;
    }

    /**
     * Gets the value of the groupParentNo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGroupParentNo() {
        return groupParentNo;
    }

    /**
     * Sets the value of the groupParentNo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGroupParentNo(String value) {
        this.groupParentNo = value;
    }

    /**
     * Gets the value of the hasChild property.
     * 
     * @return
     *     possible object is
     *     {@link Short }
     *     
     */
    public Short getHasChild() {
        return hasChild;
    }

    /**
     * Sets the value of the hasChild property.
     * 
     * @param value
     *     allowed object is
     *     {@link Short }
     *     
     */
    public void setHasChild(Short value) {
        this.hasChild = value;
    }

    /**
     * Gets the value of the updateTime property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getUpdateTime() {
        return updateTime;
    }

    /**
     * Sets the value of the updateTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setUpdateTime(XMLGregorianCalendar value) {
        this.updateTime = value;
    }

    /**
     * Gets the value of the updateUser property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUpdateUser() {
        return updateUser;
    }

    /**
     * Sets the value of the updateUser property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUpdateUser(String value) {
        this.updateUser = value;
    }

}
