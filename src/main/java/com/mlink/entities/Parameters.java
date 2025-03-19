package com.mlink.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "parameters")
public class Parameters {

    @Id
    @NotNull(message = "{parameters.pk.notnull}")
    @Column(name = "pk", nullable = false)
    private String pk;

    @Column(name = "valuetext", length = 10, nullable = false)
    @NotNull(message = "{parameters.valuetext.notnull}")
    @Size(min = 1, max = 1000, message = "{parameters.valuetext.size}")
    private String valueText;

    @NotNull(message = "{parameters.enabled.notnull}")
    @Size(min = 1, max = 1, message = "{parameters.enabled.size}")
    @Column(name = "enabled", nullable = false)
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
