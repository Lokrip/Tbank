package org.lok.tedis.tbank.service.handler;

import org.lok.tedis.tbank.model.dto.CreatePaymentTransactionRequest;
import org.lok.tedis.tbank.service.BankAccountService;
import org.lok.tedis.tbank.service.PaymentTransactionValidator;
import org.lok.tedis.tbank.util.JsonConvertor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class CreatePaymentTransactionHandler implements PaymentTransactionCommandHandler {
    private final PaymentTransactionValidator paymentTransactionValidator;
    private final BankAccountService bankAccountService;

    @Override
    @Transactional
    public void process(Long requestId, String message) {
        var request = JsonConvertor.toObject(message, CreatePaymentTransactionRequest.class);
        paymentTransactionValidator.validateCreatePaymentTransactionRequest(request);
        
        var sourceBankAccount = bankAccountService.findById(request.getSourceBankAccountId()).get();
        sourceBankAccount.setBalance(
                sourceBankAccount.getBalance().subtract(request.getAmount()));
        if (request.getDestinationBankAccountId() != null) {
            var destinationBankAccount = bankAccountService.findById(request.getDestinationBankAccountId()).get();
            destinationBankAccount.setBalance(
                destinationBankAccount.getBalance().add(request.getAmount())
            );
        }
    }

}