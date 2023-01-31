package com.example.demo.entity;

import com.example.demo.entity.base.LocalBaseAuditEntity;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.envers.Audited;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Entity
@Audited
@Table(name = "users")
public class UserEntity extends LocalBaseAuditEntity {

    @Id
    @GeneratedValue(generator = "uuid2", strategy = GenerationType.SEQUENCE)
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    private UUID id;

    private String name;
    private String surname;

    @Column(name = "email_address")
    private String email;

    public UserEntity() {
    }

    public UserEntity(final String name, final String surname, final String email) {
        this.name = name;
        this.surname = surname;
        this.email = email;
    }

    public UUID getId() {
        return id;
    }

    public void setId(final UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(final String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(final String email) {
        this.email = email;
    }
}
