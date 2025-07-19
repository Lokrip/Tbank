package org.lok.tedis.tbank.mapper;

import org.lok.tedis.tbank.model.dto.CreatePaymentTransactionRequest;
import org.lok.tedis.tbank.model.dto.CreatePaymentTransactionResponse;
import org.lok.tedis.tbank.model.entity.PaymentTransaction;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PaymentTransactionMapper {
    PaymentTransaction toEntity(CreatePaymentTransactionRequest request);
    CreatePaymentTransactionResponse toResponse(PaymentTransaction paymentTransaction); 
}
