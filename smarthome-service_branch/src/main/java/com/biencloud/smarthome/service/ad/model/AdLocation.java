package com.biencloud.smarthome.service.ad.model;

import javax.persistence.*;

import com.biencloud.smarthome.service.base.model.BaseEntity;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.EqualsBuilder;


/**
 * 广告投放位置实体类。
 * @author kouy
 * @since 1.0 2012-5-29
 * @see BaseEntity
 */
@Entity
@Table(name="t_ad_location")
public class AdLocation extends BaseEntity {

	private static final long serialVersionUID = 3706988626906332511L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="location_code")
	private String locationCode;//广告投放位置代码

	@Column(name="location_coordinate")
	private String locationCoordinate;//广告投放位置坐标

	@OneToOne
	@JoinColumn(name="sys_code", updatable=false)
	private AdSys adSys;//广告投放目标系统
	
	@Column(name="location_name")
	private String locationName;//广告投放位置名称

    public AdLocation() {
    }

	public String getLocationCode() {
		return this.locationCode;
	}

	public void setLocationCode(String locationCode) {
		this.locationCode = locationCode;
	}

	public String getLocationCoordinate() {
		return this.locationCoordinate;
	}

	public void setLocationCoordinate(String locationCoordinate) {
		this.locationCoordinate = locationCoordinate;
	}

	public String getLocationName() {
		return this.locationName;
	}

	public AdSys getAdSys() {
		return adSys;
	}

	public void setAdSys(AdSys adSys) {
		this.adSys = adSys;
	}

	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder(344809177, -1122804063)
				.append(this.locationCode).toHashCode();
	}

	@Override
	public boolean equals(Object object) {
		if (!(object instanceof AdLocation)) {
			return false;
		}
		AdLocation al = (AdLocation) object;
		return new EqualsBuilder()
				.append(this.locationCode, al.locationCode)
				.isEquals();
	}	
}