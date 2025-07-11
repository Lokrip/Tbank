package org.lok.tedis.tbank.service.handler;

import org.lok.tedis.tbank.model.dto.CreatePaymentTransactionRequest;
import org.lok.tedis.tbank.util.JsonConvertor;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class CreatePaymentTransactionHandler implements PaymentTransactionCommandHandler {

    @Override
    public void process(Long requestId, String message) {
        var request = JsonConvertor.toObject(message, CreatePaymentTransactionRequest.class);
        
    }

}
