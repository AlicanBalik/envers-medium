package com.example.demo.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.time.Instant;
import java.util.Objects;
import java.util.UUID;

public final class UserDto {

    private final UUID id;

    @NotBlank(message = "Name must not be empty.")
    private final String name;

    @NotBlank(message = "Surname must not be empty.")
    private final String surname;

    @NotBlank(message = "Email address must not be empty.")
    @Email(message = "Invalid format for email address.")
    private final String email;

    private final Instant lastModifiedDate;
    private final String lastModifiedBy;

    public UserDto(final UUID id, final String name, final String surname, final String email, final Instant lastModifiedDate, final String lastModifiedBy) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.lastModifiedDate = lastModifiedDate;
        this.lastModifiedBy = lastModifiedBy;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final UserDto userDto = (UserDto) o;
        return Objects.equals(id, userDto.id) && Objects.equals(name, userDto.name) && Objects.equals(surname, userDto.surname) && Objects.equals(email, userDto.email) && Objects.equals(lastModifiedDate, userDto.lastModifiedDate) && Objects.equals(lastModifiedBy, userDto.lastModifiedBy);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, surname, email, lastModifiedDate, lastModifiedBy);
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getEmail() {
        return email;
    }

    public Instant getLastModifiedDate() {
        return lastModifiedDate;
    }

    public String getLastModifiedBy() {
        return lastModifiedBy;
    }

    public static final class Builder {
        private UUID id;
        private String name;
        private String surname;
        private String email;
        private Instant lastModifiedDate;
        private String lastModifiedBy;


        public Builder id(UUID id) {
            this.id = id;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder surname(String surname) {
            this.surname = surname;
            return this;
        }

        public Builder email(String email) {
            this.email = email;
            return this;
        }

        public Builder lastModifiedDate(Instant lastModifiedDate) {
            this.lastModifiedDate = lastModifiedDate;
            return this;
        }

        public Builder lastModifiedBy(String lastModifiedBy) {
            this.lastModifiedBy = lastModifiedBy;
            return this;
        }

        public UserDto build() {
            return new UserDto(id, name, surname, email, lastModifiedDate, lastModifiedBy);
        }
    }
}
