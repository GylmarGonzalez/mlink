package com.mlink.response;

import org.springframework.hateoas.RepresentationModel;

public class ParametersRes extends RepresentationModel<ParametersRes>{

    private String pk;
    private String valueText;
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

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((pk == null) ? 0 : pk.hashCode());
        result = prime * result + ((valueText == null) ? 0 : valueText.hashCode());
        result = prime * result + ((enabled == null) ? 0 : enabled.hashCode());
        return result;
    }

    @Override
    public boolean equals(@SuppressWarnings("null") Object obj) {
        if (this == obj)
            return true;
        if (!super.equals(obj))
            return false;
        if (getClass() != obj.getClass())
            return false;
        ParametersRes other = (ParametersRes) obj;
        if (pk == null) {
            if (other.pk != null)
                return false;
        } else if (!pk.equals(other.pk))
            return false;
        if (valueText == null) {
            if (other.valueText != null)
                return false;
        } else if (!valueText.equals(other.valueText))
            return false;
        if (enabled == null) {
            if (other.enabled != null)
                return false;
        } else if (!enabled.equals(other.enabled))
            return false;
        return true;
    }

    
    
}
