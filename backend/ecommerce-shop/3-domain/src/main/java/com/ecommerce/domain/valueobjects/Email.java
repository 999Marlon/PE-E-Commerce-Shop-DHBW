package com.ecommerce.domain.valueobjects;

import java.util.Objects;
import java.util.regex.Pattern;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import jakarta.persistence.Embeddable;

@Embeddable
public class Email {

    @JsonValue
    private final String mail;
    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$");

    protected Email() {
        this.mail = "";}

    @JsonCreator
    public Email(String mail) {
        if (mail == null || !EMAIL_PATTERN.matcher(mail).matches()) {
            throw new IllegalArgumentException("Ungültige E-Mail-Adresse.");
        }
        this.mail = mail;
    }

    public String getEmail() {
        return mail;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Email mail = (Email) o;
        return Objects.equals(mail, mail.mail);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mail);
    }

    @Override
    public String toString() {
        return mail;
    }
}

