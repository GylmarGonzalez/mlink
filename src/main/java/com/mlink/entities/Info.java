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
@Table(name = "info")
public class Info {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pks_seq")
    @SequenceGenerator(name = "pks_seq", sequenceName = "pks_seq", allocationSize = 1)
    @NotNull(message = "{system.pk.notnull}")
	@Column(name = "pk", nullable = false)
	private Long pk;

    @Column(name = "currentVersion", length = 10, nullable = false)
    @NotNull(message = "{system.currentVersion.notnull}")
    @Size(min = 1, max = 10, message = "{system.currentVersion.size}")
    private String currentVersion;

    @Column(name = "supportsversion", length = 100, nullable = false)
    @NotNull(message = "{system.supportsVersions.notnull}")
    @Size(min = 1, max = 100, message = "{system.supportsVersions.size}")
    private String supportsVersion;

    @Column(name = "creator", length = 30, nullable = false)
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

    public String getSupportsVersion() {
        return supportsVersion;
    }

    public void setSupportsVersion(String supportsVersion) {
        this.supportsVersion = supportsVersion;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }
    

}
