package com.mlink.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "link")
public class Link {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pks_seq")
    @SequenceGenerator(name = "pks_seq", sequenceName = "pks_seq", allocationSize = 1)
	@Column(name = "pk", nullable = false)
    @NotNull(message = "{link.pk.notnull}")
	private Long pk;

    @NotNull(message = "{link.urlText.notnull}")
    @Size(min = 1, max = 500, message = "{link.urlText.size}")
    @Column(name = "urlText", nullable = false)
    private String urlText;

    @NotNull(message = "{link.description.notnull}")
    @Size(min = 1, max = 500, message = "{link.description.size}")
    @Column(name = "description", nullable = false)
    private String description;

    @NotNull(message = "{link.fkCategory.notnull}")
    @Column(name = "fkCategory", nullable = false)
    private Long fkCategory; //is not necesary the relation, because the creation of database its manual

    @NotNull(message = "{link.enabled.notnull}")
    @Size(min = 1, max = 1, message = "{link.enabled.size}")
    @Column(name = "enabled", nullable = false)
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
