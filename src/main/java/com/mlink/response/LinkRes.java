package com.mlink.response;

import org.springframework.hateoas.RepresentationModel;

public class LinkRes extends RepresentationModel<LinkRes>{

	private Long pk;
    private String urlText;
    private String description;
    private Long fkCategory; 
    private String enabled;

    public Long getPk() {
        return pk;
    }
    public void setPk(Long pk) {
        this.pk = pk;
    }
    public String getUrlText() {
        return urlText;
    }
    public void setUrlText(String urlText) {
        this.urlText = urlText;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public Long getFkCategory() {
        return fkCategory;
    }
    public void setFkCategory(Long fkCategory) {
        this.fkCategory = fkCategory;
    }
    public String getEnabled() {
        return enabled;
    }
    public void setEnabled(String enabled) {
        this.enabled = enabled;
    }
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((pk == null) ? 0 : pk.hashCode());
        result = prime * result + ((urlText == null) ? 0 : urlText.hashCode());
        result = prime * result + ((description == null) ? 0 : description.hashCode());
        result = prime * result + ((fkCategory == null) ? 0 : fkCategory.hashCode());
        result = prime * result + ((enabled == null) ? 0 : enabled.hashCode());
        return result;
    }
    @SuppressWarnings("null")
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!super.equals(obj))
            return false;
        if (getClass() != obj.getClass())
            return false;
        LinkRes other = (LinkRes) obj;
        if (pk == null) {
            if (other.pk != null)
                return false;
        } else if (!pk.equals(other.pk))
            return false;
        if (urlText == null) {
            if (other.urlText != null)
                return false;
        } else if (!urlText.equals(other.urlText))
            return false;
        if (description == null) {
            if (other.description != null)
                return false;
        } else if (!description.equals(other.description))
            return false;
        if (fkCategory == null) {
            if (other.fkCategory != null)
                return false;
        } else if (!fkCategory.equals(other.fkCategory))
            return false;
        if (enabled == null) {
            if (other.enabled != null)
                return false;
        } else if (!enabled.equals(other.enabled))
            return false;
        return true;
    }
    
}
