package com.biencloud.smarthome.service.rest.model;

public class Version {
    private Long id;

    private String type;

    private Integer status;

    private String curVersion;

    private String latestVersion;

    private String downloadUrl;

    private String description;
    
    private String version;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getCurVersion() {
        return curVersion;
    }

    public void setCurVersion(String curVersion) {
        this.curVersion = curVersion == null ? null : curVersion.trim();
    }

    public String getLatestVersion() {
        return latestVersion;
    }

    public void setLatestVersion(String latestVersion) {
        this.latestVersion = latestVersion == null ? null : latestVersion.trim();
    }

    public String getDownloadUrl() {
        return downloadUrl;
    }

    public void setDownloadUrl(String downloadUrl) {
        this.downloadUrl = downloadUrl == null ? null : downloadUrl.trim();
    }

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

}