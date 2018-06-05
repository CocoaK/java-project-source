
package com.biencloud.smarthome.web.wsclient.stub;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for menu complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="menu">
 *   &lt;complexContent>
 *     &lt;extension base="{http://service.cxfservice.smarthome.biencloud.com/}baseEntity">
 *       &lt;sequence>
 *         &lt;element name="menuCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="menuDesc" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="menuName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="menuOrder" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="menuUrl" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="parentCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="status" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="updatedTime" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="updatedUser" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "menu", propOrder = {
    "menuCode",
    "menuDesc",
    "menuName",
    "menuOrder",
    "menuUrl",
    "parentCode",
    "status",
    "updatedTime",
    "updatedUser"
})
public class Menu
    extends BaseEntity
{

    protected String menuCode;
    protected String menuDesc;
    protected String menuName;
    protected int menuOrder;
    protected String menuUrl;
    protected String parentCode;
    protected String status;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar updatedTime;
    protected String updatedUser;

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
     * Gets the value of the menuDesc property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMenuDesc() {
        return menuDesc;
    }

    /**
     * Sets the value of the menuDesc property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMenuDesc(String value) {
        this.menuDesc = value;
    }

    /**
     * Gets the value of the menuName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMenuName() {
        return menuName;
    }

    /**
     * Sets the value of the menuName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMenuName(String value) {
        this.menuName = value;
    }

    /**
     * Gets the value of the menuOrder property.
     * 
     */
    public int getMenuOrder() {
        return menuOrder;
    }

    /**
     * Sets the value of the menuOrder property.
     * 
     */
    public void setMenuOrder(int value) {
        this.menuOrder = value;
    }

    /**
     * Gets the value of the menuUrl property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMenuUrl() {
        return menuUrl;
    }

    /**
     * Sets the value of the menuUrl property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMenuUrl(String value) {
        this.menuUrl = value;
    }

    /**
     * Gets the value of the parentCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getParentCode() {
        return parentCode;
    }

    /**
     * Sets the value of the parentCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setParentCode(String value) {
        this.parentCode = value;
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
     * Gets the value of the updatedTime property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getUpdatedTime() {
        return updatedTime;
    }

    /**
     * Sets the value of the updatedTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setUpdatedTime(XMLGregorianCalendar value) {
        this.updatedTime = value;
    }

    /**
     * Gets the value of the updatedUser property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUpdatedUser() {
        return updatedUser;
    }

    /**
     * Sets the value of the updatedUser property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUpdatedUser(String value) {
        this.updatedUser = value;
    }

}
