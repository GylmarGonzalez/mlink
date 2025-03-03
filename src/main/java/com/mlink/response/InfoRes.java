package com.mlink.response;

import org.springframework.hateoas.RepresentationModel;


public class InfoRes extends RepresentationModel<InfoRes>{

	private Long pk;
    private String currentVersion;
    private String supportsVersions;
    private String creator;
    public Long getPk() {
        return pk;
    }
    public String getCurrentVersion() {
        return currentVersion;
    }
    public String getSupportsVersions() {
        return supportsVersions;
    }
    public String getCreator() {
        return creator;
    }
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((pk == null) ? 0 : pk.hashCode());
        result = prime * result + ((currentVersion == null) ? 0 : currentVersion.hashCode());
        result = prime * result + ((supportsVersions == null) ? 0 : supportsVersions.hashCode());
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
        if (supportsVersions == null) {
            if (other.supportsVersions != null)
                return false;
        } else if (!supportsVersions.equals(other.supportsVersions))
            return false;
        if (creator == null) {
            if (other.creator != null)
                return false;
        } else if (!creator.equals(other.creator))
            return false;
        return true;
    }

}
