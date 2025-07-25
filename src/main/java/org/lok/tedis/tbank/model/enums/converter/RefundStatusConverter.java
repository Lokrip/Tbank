package org.lok.tedis.tbank.model.enums.converter;

import org.lok.tedis.tbank.model.enums.RefundStatus;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class RefundStatusConverter implements AttributeConverter<RefundStatus, String> {

    @Override
    public String convertToDatabaseColumn(RefundStatus attribute) {
        return attribute == null ? null : attribute.name();
    }

    @Override
    public RefundStatus convertToEntityAttribute(String dbData) {
        return dbData == null ? null : RefundStatus.fromString(dbData);
    }

}
