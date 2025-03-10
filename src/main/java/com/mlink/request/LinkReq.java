package com.mlink.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class LinkReq {
    

	private Long pk;

    @NotNull(message = "{link.urlText.notnull}")
    @Size(min = 1, max = 500, message = "{link.urlText.size}")
    private String urlText;

    @NotNull(message = "{link.description.notnull}")
    @Size(min = 1, max = 500, message = "{link.description.size}")
    private String description;

    @NotNull(message = "{link.fkCategory.notnull}")
    private Long fkCategory; 

    @NotNull(message = "{link.enabled.notnull}")
    @Size(min = 1, max = 1, message = "{link.enabled.size}")
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

}


