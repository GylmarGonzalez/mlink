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
@Table(name = "category")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pks_seq")
    @SequenceGenerator(name = "pks_seq", sequenceName = "pks_seq", allocationSize = 1)
    @NotNull(message = "{category.pk.notnull}")
	@Column(name = "pk", nullable = false)
	private Long pk;

    @NotNull(message = "{category.name.notnull}")
    @Size(min = 1, max = 15, message = "{category.name.size}")
    @Column(name = "name", nullable = false)
    private String name;

    @NotNull(message = "{category.description.notnull}")
    @Size(min = 1, max = 200, message = "{category.description.size}")
    @Column(name = "description", nullable = false)
    private String description;

    @NotNull(message = "{category.enabled.notnull}")
    @Size(min = 1, max = 1, message = "{category.enabled.size}")
    @Column(name = "enabled", nullable = false)
    private String enabled;

    public Long getPk() {
        return pk;
    }

    public void setPk(Long pk) {
        this.pk = pk;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEnabled() {
        return enabled;
    }

    public void setEnabled(String enabled) {
        this.enabled = enabled;
    }
    
}
