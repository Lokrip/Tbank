package org.lok.tedis.tbank.model.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CancelPaymentTransactionRequest {
    @NotNull(message = "Transaction ID must not be null")
    private Long transactionId;
    @NotNull
    @Min(value = 1, message = "Refunded amount must not be null")
    private BigDecimal refundedAmount;
    private String reasons;
}
