package org.lok.tedis.tbank.model.enums.converter;

import org.lok.tedis.tbank.model.enums.PaymentTransactionStatus;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

// autoApply true Автоматически применять этот конвертер ко всем полям 
// соответствующего типа в вашем проекте, 
// без явного указания @Convert(converter = ...).
// Если вы указали autoApply = true, то все поля типа PaymentTransactionStatus в сущностях 
// JPA будут автоматически использовать этот конвертер при сохранении и загрузке из базы данных.
// Без autoApply = true:
// @Convert(converter = PaymentTransactionStatusConverter.class)
// private PaymentTransactionStatus status;
// С autoApply = true:
// private PaymentTransactionStatus status; // конвертер будет применён автоматически
@Converter(autoApply = true)
// Converter он должен конвертировать в String таким образом указываем в
// AttributeConverter то что будем конвертировать и указываем по что в даннолм
// примере String
public class PaymentTransactionStatusConverter implements AttributeConverter<PaymentTransactionStatus, String> {

    @Override
    public String convertToDatabaseColumn(PaymentTransactionStatus attribute) {
        return attribute == null ? null : attribute.name();
    }

    @Override
    public PaymentTransactionStatus convertToEntityAttribute(String dbData) {
        return dbData == null ? null : PaymentTransactionStatus.fromString(dbData);
    }
    
}
