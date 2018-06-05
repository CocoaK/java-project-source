package com.biencloud.smarthome.service.rest.model;

import com.biencloud.smarthome.service.base.model.BaseEntity;

@SuppressWarnings("serial")
public class RoomMobileEntry extends BaseEntity{
	
    private Long id;

    private String districtNo;

    private String reginNo;

    private String buildingNo;

    private String unitNo;

    private String roomNo;

    private String targetUid;

    private Integer type;

    private String iosToken;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDistrictNo() {
        return districtNo;
    }

    public void setDistrictNo(String districtNo) {
        this.districtNo = districtNo == null ? null : districtNo.trim();
    }

    public String getReginNo() {
        return reginNo;
    }

    public void setReginNo(String reginNo) {
        this.reginNo = reginNo == null ? null : reginNo.trim();
    }

    public String getBuildingNo() {
        return buildingNo;
    }

    public void setBuildingNo(String buildingNo) {
        this.buildingNo = buildingNo == null ? null : buildingNo.trim();
    }

    public String getUnitNo() {
        return unitNo;
    }

    public void setUnitNo(String unitNo) {
        this.unitNo = unitNo == null ? null : unitNo.trim();
    }

    public String getRoomNo() {
        return roomNo;
    }

    public void setRoomNo(String roomNo) {
        this.roomNo = roomNo == null ? null : roomNo.trim();
    }

    public String getTargetUid() {
        return targetUid;
    }

    public void setTargetUid(String targetUid) {
        this.targetUid = targetUid == null ? null : targetUid.trim();
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getIosToken() {
        return iosToken;
    }

    public void setIosToken(String iosToken) {
        this.iosToken = iosToken == null ? null : iosToken.trim();
    }

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((buildingNo == null) ? 0 : buildingNo.hashCode());
		result = prime * result
				+ ((districtNo == null) ? 0 : districtNo.hashCode());
		result = prime * result + ((reginNo == null) ? 0 : reginNo.hashCode());
		result = prime * result + ((roomNo == null) ? 0 : roomNo.hashCode());
		result = prime * result
				+ ((targetUid == null) ? 0 : targetUid.hashCode());
		result = prime * result + ((unitNo == null) ? 0 : unitNo.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RoomMobileEntry other = (RoomMobileEntry) obj;
		if (buildingNo == null) {
			if (other.buildingNo != null)
				return false;
		} else if (!buildingNo.equals(other.buildingNo))
			return false;
		if (districtNo == null) {
			if (other.districtNo != null)
				return false;
		} else if (!districtNo.equals(other.districtNo))
			return false;
		if (reginNo == null) {
			if (other.reginNo != null)
				return false;
		} else if (!reginNo.equals(other.reginNo))
			return false;
		if (roomNo == null) {
			if (other.roomNo != null)
				return false;
		} else if (!roomNo.equals(other.roomNo))
			return false;
		if (targetUid == null) {
			if (other.targetUid != null)
				return false;
		} else if (!targetUid.equals(other.targetUid))
			return false;
		if (unitNo == null) {
			if (other.unitNo != null)
				return false;
		} else if (!unitNo.equals(other.unitNo))
			return false;
		return true;
	}
    
}