package org.lok.tedis.tbank.service.handler;

public interface PaymentTransactionCommandHandler {
    void process(Long requestId, String message);
}
