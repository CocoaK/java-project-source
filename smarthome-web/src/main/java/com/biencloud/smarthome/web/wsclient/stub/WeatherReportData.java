
package com.biencloud.smarthome.web.wsclient.stub;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for weatherReportData complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="weatherReportData">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="date" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="dayTemp" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="dayWeatherDesc" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="nightTemp" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="nightWeatherDesc" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="week" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "weatherReportData", propOrder = {
    "date",
    "dayTemp",
    "dayWeatherDesc",
    "nightTemp",
    "nightWeatherDesc",
    "week"
})
public class WeatherReportData {

    protected String date;
    protected String dayTemp;
    protected String dayWeatherDesc;
    protected String nightTemp;
    protected String nightWeatherDesc;
    protected String week;

    /**
     * Gets the value of the date property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDate() {
        return date;
    }

    /**
     * Sets the value of the date property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDate(String value) {
        this.date = value;
    }

    /**
     * Gets the value of the dayTemp property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDayTemp() {
        return dayTemp;
    }

    /**
     * Sets the value of the dayTemp property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDayTemp(String value) {
        this.dayTemp = value;
    }

    /**
     * Gets the value of the dayWeatherDesc property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDayWeatherDesc() {
        return dayWeatherDesc;
    }

    /**
     * Sets the value of the dayWeatherDesc property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDayWeatherDesc(String value) {
        this.dayWeatherDesc = value;
    }

    /**
     * Gets the value of the nightTemp property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNightTemp() {
        return nightTemp;
    }

    /**
     * Sets the value of the nightTemp property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNightTemp(String value) {
        this.nightTemp = value;
    }

    /**
     * Gets the value of the nightWeatherDesc property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNightWeatherDesc() {
        return nightWeatherDesc;
    }

    /**
     * Sets the value of the nightWeatherDesc property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNightWeatherDesc(String value) {
        this.nightWeatherDesc = value;
    }

    /**
     * Gets the value of the week property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWeek() {
        return week;
    }

    /**
     * Sets the value of the week property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWeek(String value) {
        this.week = value;
    }

}
