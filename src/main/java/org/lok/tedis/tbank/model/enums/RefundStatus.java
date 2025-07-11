package org.lok.tedis.tbank.model.enums;

import lombok.Getter;

@Getter
public enum RefundStatus {
    COMPLETED,
    FAILED;

    public static RefundStatus fromString(String status) {
        for (RefundStatus refundStatus : RefundStatus.values()) {
            if (refundStatus.toString().equalsIgnoreCase(status)) {
                return refundStatus;
            }
        }
        throw new IllegalArgumentException("Unknown status: " + status);
    }
}
