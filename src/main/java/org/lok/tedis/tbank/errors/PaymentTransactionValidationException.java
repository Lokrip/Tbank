package org.lok.tedis.tbank.errors;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PaymentTransactionValidationException extends RuntimeException {
    public List<String> errors;
}
