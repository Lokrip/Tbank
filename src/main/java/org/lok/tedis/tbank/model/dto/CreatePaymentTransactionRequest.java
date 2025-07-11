package org.lok.tedis.tbank.model.dto;

import java.math.BigDecimal;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreatePaymentTransactionRequest {
    @NotNull
    private Long sourceBankAccountId;
    private Long destinationBankAccountId;
    @NotNull
    private BigDecimal amount;
    @NotNull
    private String currency;
    private String description;
}
