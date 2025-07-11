package org.lok.tedis.tbank.model.entity;

import java.math.BigDecimal;

import org.lok.tedis.tbank.model.enums.RefundStatus;
import org.lok.tedis.tbank.model.enums.converter.RefundStatusConverter;

import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Refund extends BaseEntity {
    private BigDecimal refundedAmount;
    private String reason;

    @Convert(converter = RefundStatusConverter.class)
    private RefundStatus status;

    @ManyToOne
    @JoinColumn(columnDefinition = "paymentTransactionId", referencedColumnName = "id")
    private PaymentTransaction paymentTransaction;
}
