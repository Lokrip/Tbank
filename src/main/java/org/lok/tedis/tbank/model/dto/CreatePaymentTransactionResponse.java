package org.lok.tedis.tbank.model.dto;

import java.time.LocalDateTime;

import org.lok.tedis.tbank.model.dto.enums.CommandResultStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreatePaymentTransactionResponse {
    private Long paymentTransactionId;
    private CommandResultStatus status;
    private String errorMessage;
    private LocalDateTime executedAt;
}
