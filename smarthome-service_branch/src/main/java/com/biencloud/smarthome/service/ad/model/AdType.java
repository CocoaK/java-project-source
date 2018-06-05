package com.biencloud.smarthome.service.ad.model;

import javax.persistence.*;

import com.biencloud.smarthome.service.base.model.BaseEntity;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;

/**
 * 广告类型实体类。
 * @author kouy
 * @since 1.0 2012-5-29
 * @see BaseEntity
 */
@Entity
@Table(name="t_ad_type")
public class AdType extends BaseEntity {

	private static final long serialVersionUID = 5393251218662995034L;

	@Id
	@Column(name="type_code")
	private String typeCode;//广告类型代码

	@Column(name="type_name")
	private String typeName;//广告类型名称

    public AdType() {
    }

	public String getTypeCode() {
		return this.typeCode;
	}

	public void setTypeCode(String typeCode) {
		this.typeCode = typeCode;
	}

	public String getTypeName() {
		return this.typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder(1496388723, -2095429639)
				.append(this.typeCode).toHashCode();
	}

	@Override
	public boolean equals(Object object) {
		if (!(object instanceof AdType)) {
			return false;
		}
		AdType at = (AdType) object;
		return new EqualsBuilder()
				.append(this.typeCode, at.typeCode)
				.isEquals();
	}
}