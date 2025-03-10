package com.mlink.response;

import org.springframework.hateoas.RepresentationModel;


public class InfoRes extends RepresentationModel<InfoRes>{

	private Long pk;
    private String currentVersion;
    private String supportsVersion;
    private String creator;
    public Long getPk() {
        return pk;
    }
    public String getCurrentVersion() {
        return currentVersion;
    }
    public String getSupportsVersion() {
        return supportsVersion;
    }
    public String getCreator() {
        return creator;
    }

    public void setPk(Long pk) {
        this.pk = pk;
    }
    public void setCurrentVersion(String currentVersion) {
        this.currentVersion = currentVersion;
    }
    public void setSupportsVersion(String supportsVersion) {
        this.supportsVersion = supportsVersion;
    }
    public void setCreator(String creator) {
        this.creator = creator;
    }
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((pk == null) ? 0 : pk.hashCode());
        result = prime * result + ((currentVersion == null) ? 0 : currentVersion.hashCode());
        result = prime * result + ((supportsVersion == null) ? 0 : supportsVersion.hashCode());
        result = prime * result + ((creator == null) ? 0 : creator.hashCode());
        return result;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!super.equals(obj))
            return false;
        if (getClass() != obj.getClass())
            return false;
        InfoRes other = (InfoRes) obj;
        if (pk == null) {
            if (other.pk != null)
                return false;
        } else if (!pk.equals(other.pk))
            return false;
        if (currentVersion == null) {
            if (other.currentVersion != null)
                return false;
        } else if (!currentVersion.equals(other.currentVersion))
            return false;
        if (supportsVersion == null) {
            if (other.supportsVersion != null)
                return false;
        } else if (!supportsVersion.equals(other.supportsVersion))
            return false;
        if (creator == null) {
            if (other.creator != null)
                return false;
        } else if (!creator.equals(other.creator))
            return false;
        return true;
    }

}
