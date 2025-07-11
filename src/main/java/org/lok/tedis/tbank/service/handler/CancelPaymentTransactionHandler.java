package org.lok.tedis.tbank.service.handler;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class CancelPaymentTransactionHandler implements PaymentTransactionCommandHandler {
    @Override
    public void process(Long requestId, String message) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'process'");
    }

}
