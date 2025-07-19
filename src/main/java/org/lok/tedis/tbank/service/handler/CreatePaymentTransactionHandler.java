package org.lok.tedis.tbank.service.handler;

import java.util.Optional;

import org.lok.tedis.tbank.mapper.PaymentTransactionMapper;
import org.lok.tedis.tbank.model.dto.CreatePaymentTransactionRequest;
import org.lok.tedis.tbank.model.entity.BankAccount;
import org.lok.tedis.tbank.model.enums.PaymentTransactionStatus;
import org.lok.tedis.tbank.service.BankAccountService;
import org.lok.tedis.tbank.service.PaymentTransactionService;
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
    private final PaymentTransactionMapper paymentTransactionMapper;
    private final PaymentTransactionService paymentTransactionService;

    @Override
    @Transactional
    public void process(Long requestId, String message) {
        var request = JsonConvertor.toObject(message, CreatePaymentTransactionRequest.class);
        paymentTransactionValidator.validateCreatePaymentTransactionRequest(request);

        var sourceBankAccount = bankAccountService.findById(request.getSourceBankAccountId()).get();
        sourceBankAccount.setBalance(
                sourceBankAccount.getBalance().subtract(request.getAmount()));

        Optional<BankAccount> destinationBankAccount = Optional.empty();
        if (request.getDestinationBankAccountId() != null) {
            destinationBankAccount = bankAccountService.findById(request.getDestinationBankAccountId());
            destinationBankAccount
                    .get()
                    .setBalance(
                            destinationBankAccount
                                    .get()
                                    .getBalance()
                                    .add(request.getAmount()));
        }

        var entity = paymentTransactionMapper.toEntity(request);
        entity.setSourceBankAccount(sourceBankAccount);
        destinationBankAccount.ifPresent(entity::setDestinationBankAccount);
        entity.setPaymentTransactionStatus(PaymentTransactionStatus.SUCCESS);

        paymentTransactionService.save(entity); 

    }

}