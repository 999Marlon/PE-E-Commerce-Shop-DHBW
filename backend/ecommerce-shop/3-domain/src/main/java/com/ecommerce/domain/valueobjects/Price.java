package com.ecommerce.domain.valueobjects;

import jakarta.persistence.Embeddable;
import lombok.Value;
import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;

@Value  
@Embeddable 
public class Price {

    private final BigDecimal amount;

    @JsonCreator
    public Price(@JsonProperty("amount") BigDecimal amount) {
        if (amount == null || amount.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Preis muss größer als 0 sein.");
        }
        this.amount = amount;
    }

    public BigDecimal getAmout(){
        return amount;
    }

    public Price add(Price other) {
        return new Price(this.amount.add(other.amount));
    }

    public Price subtract(Price other) {
        if (this.amount.compareTo(other.amount) < 0) {
            throw new IllegalArgumentException("Der Preis kann nicht negativ werden");
        }
        return new Price(this.amount.subtract(other.amount));
    }

    public Price multiply(int quantity) {
        if (quantity < 0) {
            throw new IllegalArgumentException("Quantity must be positive.");
        }
        return new Price(this.amount.multiply(BigDecimal.valueOf(quantity)));
    }
    
    @Override
    public String toString() {
        return amount + " EUR";
    }
}

