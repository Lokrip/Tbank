package org.lok.tedis.tbank.model.entity;

import java.math.BigDecimal;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BankAccount extends BaseEntity {
    private String number;
    private BigDecimal balance;
    private Long customerId;
    private String currency;
}
