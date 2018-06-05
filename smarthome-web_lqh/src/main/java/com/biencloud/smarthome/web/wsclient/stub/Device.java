
package com.biencloud.smarthome.web.wsclient.stub;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for device complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="device">
 *   &lt;complexContent>
 *     &lt;extension base="{http://service.cxfservice.smarthome.biencloud.com/}baseEntity">
 *       &lt;sequence>
 *         &lt;element name="areaIsNull" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="buildingCellInfo" type="{http://service.cxfservice.smarthome.biencloud.com/}buildingCellInfo" minOccurs="0"/>
 *         &lt;element name="cellHouseholdInfo" type="{http://service.cxfservice.smarthome.biencloud.com/}cellHouseholdInfo" minOccurs="0"/>
 *         &lt;element name="coordinate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="createdTime" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="createdUser" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="deviceAlias" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="deviceBgUrl" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="deviceCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="deviceId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="deviceIp" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="deviceMac" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="deviceName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="devicePwd" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="unitDoorNo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="deviceStatus" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="deviceType" type="{http://service.cxfservice.smarthome.biencloud.com/}deviceType" minOccurs="0"/>
 *         &lt;element name="deviceTypeList" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="districtGateFlag" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="housingDistrictInfo" type="{http://service.cxfservice.smarthome.biencloud.com/}housingDistrictInfo" minOccurs="0"/>
 *         &lt;element name="housingDistrictRegionInfo" type="{http://service.cxfservice.smarthome.biencloud.com/}housingDistrictRegionInfo" minOccurs="0"/>
 *         &lt;element name="loginStatus" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="loginUuserId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="position" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="regionBuildingInfo" type="{http://service.cxfservice.smarthome.biencloud.com/}regionBuildingInfo" minOccurs="0"/>
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
@XmlType(name = "device", propOrder = {
    "areaIsNull",
    "buildingCellInfo",
    "cellHouseholdInfo",
    "coordinate",
    "createdTime",
    "createdUser",
    "deviceAlias",
    "deviceBgUrl",
    "deviceCode",
    "deviceId",
    "deviceIp",
    "deviceMac",
    "deviceName",
    "devicePwd",
    "unitDoorNo",
    "deviceStatus",
    "deviceType",
    "deviceTypeList",
    "districtGateFlag",
    "housingDistrictInfo",
    "housingDistrictRegionInfo",
    "loginStatus",
    "loginUuserId",
    "position",
    "regionBuildingInfo",
    "updatedTime",
    "version",
    "sipid",
    "updatedUser"
})
public class Device
    extends BaseEntity
{

    protected boolean areaIsNull;
    protected BuildingCellInfo buildingCellInfo;
    protected CellHouseholdInfo cellHouseholdInfo;
    protected String coordinate;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar createdTime;
    protected String createdUser;
    protected String deviceAlias;
    protected String deviceBgUrl;
    protected String deviceCode;
    protected String deviceId;
    protected String deviceIp;
    protected String deviceMac;
    protected String deviceName;
    protected String devicePwd;
    protected String unitDoorNo;
    protected String deviceStatus;
    protected DeviceType deviceType;
    @XmlElement(nillable = true)
    protected List<String> deviceTypeList;
    protected String districtGateFlag;
    protected HousingDistrictInfo housingDistrictInfo;
    protected HousingDistrictRegionInfo housingDistrictRegionInfo;
    protected String loginStatus;
    protected String loginUuserId;
    protected String position;
    protected RegionBuildingInfo regionBuildingInfo;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar updatedTime;
    protected String updatedUser;
    protected String version;
    protected String sipid;

    /**
     * Gets the value of the areaIsNull property.
     * 
     */
    public boolean isAreaIsNull() {
        return areaIsNull;
    }

    /**
     * Sets the value of the areaIsNull property.
     * 
     */
    public void setAreaIsNull(boolean value) {
        this.areaIsNull = value;
    }

    /**
     * Gets the value of the buildingCellInfo property.
     * 
     * @return
     *     possible object is
     *     {@link BuildingCellInfo }
     *     
     */
    public BuildingCellInfo getBuildingCellInfo() {
        return buildingCellInfo;
    }

    /**
     * Sets the value of the buildingCellInfo property.
     * 
     * @param value
     *     allowed object is
     *     {@link BuildingCellInfo }
     *     
     */
    public void setBuildingCellInfo(BuildingCellInfo value) {
        this.buildingCellInfo = value;
    }

    /**
     * Gets the value of the cellHouseholdInfo property.
     * 
     * @return
     *     possible object is
     *     {@link CellHouseholdInfo }
     *     
     */
    public CellHouseholdInfo getCellHouseholdInfo() {
        return cellHouseholdInfo;
    }

    /**
     * Sets the value of the cellHouseholdInfo property.
     * 
     * @param value
     *     allowed object is
     *     {@link CellHouseholdInfo }
     *     
     */
    public void setCellHouseholdInfo(CellHouseholdInfo value) {
        this.cellHouseholdInfo = value;
    }

    /**
     * Gets the value of the coordinate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCoordinate() {
        return coordinate;
    }

    /**
     * Sets the value of the coordinate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCoordinate(String value) {
        this.coordinate = value;
    }

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
     * Gets the value of the deviceAlias property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDeviceAlias() {
        return deviceAlias;
    }

    /**
     * Sets the value of the deviceAlias property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDeviceAlias(String value) {
        this.deviceAlias = value;
    }

    /**
     * Gets the value of the deviceBgUrl property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDeviceBgUrl() {
        return deviceBgUrl;
    }

    /**
     * Sets the value of the deviceBgUrl property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDeviceBgUrl(String value) {
        this.deviceBgUrl = value;
    }

    /**
     * Gets the value of the deviceCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDeviceCode() {
        return deviceCode;
    }

    /**
     * Sets the value of the deviceCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDeviceCode(String value) {
        this.deviceCode = value;
    }

    /**
     * Gets the value of the deviceId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDeviceId() {
        return deviceId;
    }

    /**
     * Sets the value of the deviceId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDeviceId(String value) {
        this.deviceId = value;
    }

    /**
     * Gets the value of the deviceIp property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDeviceIp() {
        return deviceIp;
    }

    /**
     * Sets the value of the deviceIp property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDeviceIp(String value) {
        this.deviceIp = value;
    }

    /**
     * Gets the value of the deviceMac property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDeviceMac() {
        return deviceMac;
    }

    /**
     * Sets the value of the deviceMac property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDeviceMac(String value) {
        this.deviceMac = value;
    }

    /**
     * Gets the value of the deviceName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDeviceName() {
        return deviceName;
    }

    /**
     * Sets the value of the deviceName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDeviceName(String value) {
        this.deviceName = value;
    }

    /**
     * Gets the value of the devicePwd property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDevicePwd() {
        return devicePwd;
    }

    /**
     * Sets the value of the devicePwd property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDevicePwd(String value) {
        this.devicePwd = value;
    }

    /**
     * Gets the value of the unitDoorNo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUnitDoorNo() {
        return unitDoorNo;
    }

    /**
     * Sets the value of the unitDoorNo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUnitDoorNo(String value) {
        this.unitDoorNo = value;
    }

    /**
     * Gets the value of the deviceStatus property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDeviceStatus() {
        return deviceStatus;
    }

    /**
     * Sets the value of the deviceStatus property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDeviceStatus(String value) {
        this.deviceStatus = value;
    }

    /**
     * Gets the value of the deviceType property.
     * 
     * @return
     *     possible object is
     *     {@link DeviceType }
     *     
     */
    public DeviceType getDeviceType() {
        return deviceType;
    }

    /**
     * Sets the value of the deviceType property.
     * 
     * @param value
     *     allowed object is
     *     {@link DeviceType }
     *     
     */
    public void setDeviceType(DeviceType value) {
        this.deviceType = value;
    }

    /**
     * Gets the value of the deviceTypeList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the deviceTypeList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDeviceTypeList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getDeviceTypeList() {
        if (deviceTypeList == null) {
            deviceTypeList = new ArrayList<String>();
        }
        return this.deviceTypeList;
    }

    /**
     * Gets the value of the districtGateFlag property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDistrictGateFlag() {
        return districtGateFlag;
    }

    /**
     * Sets the value of the districtGateFlag property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDistrictGateFlag(String value) {
        this.districtGateFlag = value;
    }

    /**
     * Gets the value of the housingDistrictInfo property.
     * 
     * @return
     *     possible object is
     *     {@link HousingDistrictInfo }
     *     
     */
    public HousingDistrictInfo getHousingDistrictInfo() {
        return housingDistrictInfo;
    }

    /**
     * Sets the value of the housingDistrictInfo property.
     * 
     * @param value
     *     allowed object is
     *     {@link HousingDistrictInfo }
     *     
     */
    public void setHousingDistrictInfo(HousingDistrictInfo value) {
        this.housingDistrictInfo = value;
    }

    /**
     * Gets the value of the housingDistrictRegionInfo property.
     * 
     * @return
     *     possible object is
     *     {@link HousingDistrictRegionInfo }
     *     
     */
    public HousingDistrictRegionInfo getHousingDistrictRegionInfo() {
        return housingDistrictRegionInfo;
    }

    /**
     * Sets the value of the housingDistrictRegionInfo property.
     * 
     * @param value
     *     allowed object is
     *     {@link HousingDistrictRegionInfo }
     *     
     */
    public void setHousingDistrictRegionInfo(HousingDistrictRegionInfo value) {
        this.housingDistrictRegionInfo = value;
    }

    /**
     * Gets the value of the loginStatus property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLoginStatus() {
        return loginStatus;
    }

    /**
     * Sets the value of the loginStatus property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLoginStatus(String value) {
        this.loginStatus = value;
    }

    /**
     * Gets the value of the loginUuserId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLoginUuserId() {
        return loginUuserId;
    }

    /**
     * Sets the value of the loginUuserId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLoginUuserId(String value) {
        this.loginUuserId = value;
    }

    /**
     * Gets the value of the position property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPosition() {
        return position;
    }

    /**
     * Sets the value of the position property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPosition(String value) {
        this.position = value;
    }

    /**
     * Gets the value of the regionBuildingInfo property.
     * 
     * @return
     *     possible object is
     *     {@link RegionBuildingInfo }
     *     
     */
    public RegionBuildingInfo getRegionBuildingInfo() {
        return regionBuildingInfo;
    }

    /**
     * Sets the value of the regionBuildingInfo property.
     * 
     * @param value
     *     allowed object is
     *     {@link RegionBuildingInfo }
     *     
     */
    public void setRegionBuildingInfo(RegionBuildingInfo value) {
        this.regionBuildingInfo = value;
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
