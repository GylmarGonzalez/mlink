package com.mlink.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class ParametersReq {

    @NotNull(message = "{parameters.pk.notnull}")
    private String pk;

    @NotNull(message = "{parameters.valuetext.notnull}")
    @Size(min = 1, max = 1000, message = "{parameters.valuetext.size}")
    private String valueText;

    @NotNull(message = "{parameters.enabled.notnull}")
    @Size(min = 1, max = 1, message = "{parameters.enabled.size}")
    private String enabled;

    public String getPk() {
        return pk;
    }

    public void setPk(String pk) {
        this.pk = pk;
    }

    public String getValueText() {
        return valueText;
    }

    public void setValueText(String valueText) {
        this.valueText = valueText;
    }

    public String getEnabled() {
        return enabled;
    }

    public void setEnabled(String enabled) {
        this.enabled = enabled;
    }

    
}
