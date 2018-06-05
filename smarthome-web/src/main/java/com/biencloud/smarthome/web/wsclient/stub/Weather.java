
package com.biencloud.smarthome.web.wsclient.stub;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for weather complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="weather">
 *   &lt;complexContent>
 *     &lt;extension base="{http://service.cxfservice.smarthome.biencloud.com/}baseEntity">
 *       &lt;sequence>
 *         &lt;element name="cityName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="content" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="countryName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="dayImage" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="nightImage" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="provinceName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="reportDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="serviceUrl" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="status" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "weather", propOrder = {
    "cityName",
    "content",
    "countryName",
    "dayImage",
    "id",
    "nightImage",
    "provinceName",
    "reportDate",
    "serviceUrl",
    "status"
})
public class Weather
    extends BaseEntity
{

    protected String cityName;
    protected String content;
    protected String countryName;
    protected String dayImage;
    protected Long id;
    protected String nightImage;
    protected String provinceName;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar reportDate;
    protected String serviceUrl;
    protected String status;

    /**
     * Gets the value of the cityName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCityName() {
        return cityName;
    }

    /**
     * Sets the value of the cityName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCityName(String value) {
        this.cityName = value;
    }

    /**
     * Gets the value of the content property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getContent() {
        return content;
    }

    /**
     * Sets the value of the content property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setContent(String value) {
        this.content = value;
    }

    /**
     * Gets the value of the countryName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCountryName() {
        return countryName;
    }

    /**
     * Sets the value of the countryName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCountryName(String value) {
        this.countryName = value;
    }

    /**
     * Gets the value of the dayImage property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDayImage() {
        return dayImage;
    }

    /**
     * Sets the value of the dayImage property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDayImage(String value) {
        this.dayImage = value;
    }

    /**
     * Gets the value of the id property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setId(Long value) {
        this.id = value;
    }

    /**
     * Gets the value of the nightImage property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNightImage() {
        return nightImage;
    }

    /**
     * Sets the value of the nightImage property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNightImage(String value) {
        this.nightImage = value;
    }

    /**
     * Gets the value of the provinceName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProvinceName() {
        return provinceName;
    }

    /**
     * Sets the value of the provinceName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProvinceName(String value) {
        this.provinceName = value;
    }

    /**
     * Gets the value of the reportDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getReportDate() {
        return reportDate;
    }

    /**
     * Sets the value of the reportDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setReportDate(XMLGregorianCalendar value) {
        this.reportDate = value;
    }

    /**
     * Gets the value of the serviceUrl property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getServiceUrl() {
        return serviceUrl;
    }

    /**
     * Sets the value of the serviceUrl property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setServiceUrl(String value) {
        this.serviceUrl = value;
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

}
