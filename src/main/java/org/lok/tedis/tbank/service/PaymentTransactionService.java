package org.lok.tedis.tbank.service;

import org.lok.tedis.tbank.model.dto.CreatePaymentTransactionRequest;
import org.lok.tedis.tbank.model.entity.PaymentTransaction;
import org.lok.tedis.tbank.repository.PaymentTransactionRepository;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class PaymentTransactionService {
    private final PaymentTransactionRepository paymentTransactionRepository;

    public PaymentTransaction save(CreatePaymentTransactionRequest request) {

    }
}
