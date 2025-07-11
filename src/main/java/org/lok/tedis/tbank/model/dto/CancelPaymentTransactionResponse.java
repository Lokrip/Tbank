package org.lok.tedis.tbank.model.dto;

import org.lok.tedis.tbank.model.dto.enums.CommandResultStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CancelPaymentTransactionResponse {
    private Long refundId;
    private CommandResultStatus status;
    private String errorMessage;
}
