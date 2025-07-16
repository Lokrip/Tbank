package org.lok.tedis.tbank.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.lok.tedis.tbank.errors.PaymentTransactionValidationException;
import org.lok.tedis.tbank.model.dto.CreatePaymentTransactionRequest;
import org.lok.tedis.tbank.model.entity.BankAccount;
import org.lok.tedis.tbank.model.entity.PaymentTransaction;
import org.springframework.stereotype.Service;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class PaymentTransactionValidator {

    private final Validator validator;
    private final BankAccountService bankAccountService;

    public void validateCreatePaymentTransactionRequest(
            CreatePaymentTransactionRequest request) {
        var violations = validator.validate(request);
        List<String> errors = new ArrayList<>(violations
                .stream()
                .map(ConstraintViolation::getMessage)
                .toList());

        Optional<BankAccount> sourceBank = Optional.empty();

        if (request.getSourceBankAccountId() != null) {
            sourceBank = bankAccountService.findById(request.getSourceBankAccountId());

            if (sourceBank == null) {
                errors.add("Source bank account not found, source account id: " + request.getSourceBankAccountId());
            }
        }

        if (request.getDestinationBankAccountId() != null) {
            var destinationBank = bankAccountService.findById(request.getDestinationBankAccountId());
            if (destinationBank.isEmpty()) {
                errors.add("Destionation bank account not found, destination account id: "
                        + request.getSourceBankAccountId());
            }
        }

        if (request.getAmount() != null && sourceBank.isPresent()) {
            if (sourceBank.get().getBalance().compareTo(request.getAmount()) < 0) {
                errors.add("Source bank account balance less then requested amount, source account id: "
                        + request.getSourceBankAccountId());
            }
        }

        if (!errors.isEmpty()) {
            throw new PaymentTransactionValidationException(errors);
        }
    }
}
