package org.lok.tedis.tbank.model.enums;

import lombok.Getter;

@Getter
public enum PaymentTransactionStatus {
    PROCESSING,
    SUCCESS,
    FAILED;

    public static PaymentTransactionStatus fromString(String status) {
        for (PaymentTransactionStatus paymentTransactionStatus : PaymentTransactionStatus.values()) {
            if (paymentTransactionStatus.toString().equalsIgnoreCase(status)) {
                return paymentTransactionStatus;
            }
        }
        throw new IllegalArgumentException("Unknown status: " + status);
    }
}
