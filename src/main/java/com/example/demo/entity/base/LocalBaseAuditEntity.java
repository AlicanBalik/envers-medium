package com.example.demo.entity.base;

import org.hibernate.envers.Audited;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.Instant;
import java.util.Objects;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class LocalBaseAuditEntity {

    @CreatedBy
    @Column(name = "created_by", updatable = false)
    private String createdBy;

    @CreatedDate
    @Column(name = "created_date", nullable = false, updatable = false)
    // You can use any date-time object.
    private Instant createDate;

    @Audited
    @LastModifiedBy
    @Column(name = "last_modified_by")
    private String lastModifiedBy;

    @Audited
    @LastModifiedDate
    @Column(name = "last_modified_date", nullable = false)
    // You can use any date-time object.
    private Instant lastModifiedDate;

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(final String createdBy) {
        this.createdBy = createdBy;
    }

    public Instant getCreateDate() {
        return createDate;
    }

    public void setCreateDate(final Instant createDate) {
        this.createDate = createDate;
    }

    public String getLastModifiedBy() {
        return lastModifiedBy;
    }

    public void setLastModifiedBy(final String lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

    public Instant getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(final Instant lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final LocalBaseAuditEntity that = (LocalBaseAuditEntity) o;
        return Objects.equals(createdBy, that.createdBy) && Objects.equals(createDate, that.createDate) && Objects.equals(lastModifiedBy, that.lastModifiedBy) && Objects.equals(lastModifiedDate, that.lastModifiedDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(createdBy, createDate, lastModifiedBy, lastModifiedDate);
    }

    @Override
    public String toString() {
        return "LocalBaseAuditEntity{" +
                "createdBy='" + createdBy + '\'' +
                ", createDate=" + createDate +
                ", lastModifiedBy='" + lastModifiedBy + '\'' +
                ", lastModifiedDate=" + lastModifiedDate +
                '}';
    }
}
