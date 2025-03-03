package com.mlink.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class InfoReq {
    
    @NotNull(message = "{system.pk.notnull}")
	private Long pk;

    @NotNull(message = "{system.currentVersion.notnull}")
    @Size(min = 1, max = 10, message = "{system.currentVersion.size}")
    private String currentVersion;

    @NotNull(message = "{system.supportsVersions.notnull}")
    @Size(min = 1, max = 100, message = "{system.supportsVersions.size}")
    private String supportsVersions;

    @NotNull(message = "{system.creator.notnull}")
    @Size(min = 1, max = 30, message = "{system.creator.size}")
    private String creator;

    public Long getPk() {
        return pk;
    }

    public void setPk(Long pk) {
        this.pk = pk;
    }

    public String getCurrentVersion() {
        return currentVersion;
    }

    public void setCurrentVersion(String currentVersion) {
        this.currentVersion = currentVersion;
    }

    public String getSupportsVersions() {
        return supportsVersions;
    }

    public void setSupportsVersions(String supportsVersions) {
        this.supportsVersions = supportsVersions;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

}
