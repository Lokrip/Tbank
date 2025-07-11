package org.lok.tedis.tbank.service;

import java.util.ArrayList;
import java.util.List;

import org.lok.tedis.tbank.model.dto.CreatePaymentTransactionRequest;
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

    public void validateCreatePaymentTransactionRequest(
            CreatePaymentTransactionRequest request) {
        var violations = validator.validate(request);
        List<String> errors = new ArrayList<>(violations
                .stream()
                .map(ConstraintViolation::getMessage)
                .toList());
        
        
    }
}
