package org.lok.tedis.tbank.model.entity;

import java.math.BigDecimal;
import java.util.List;

import org.lok.tedis.tbank.model.enums.PaymentTransactionStatus;
import org.lok.tedis.tbank.model.enums.converter.PaymentTransactionStatusConverter;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PaymentTransaction extends BaseEntity {
    private BigDecimal amount;
    private String currency;

    // Hibernate знает про enum и умеет сохранять его в базу двумя стандартными
    // способами:
    // По имени enum-константы (String) — например, "PROCESSING".
    // По порядковому номеру (ordinal, int) — например, 0 для PROCESSING, 1 для
    // SUCCESS и т.д.
    // Если стандартные способы подходят — конвертер не нужен! Hibernate сам умеет с
    // этим работать.
    // Когда нужен конвертер?
    // Если ты хочешь нестандартное хранение enum — например:
    // хранить короткие коды: "P" вместо "PROCESSING",
    // хранить описания, цифры не по порядку,
    // или что-то ещё более специфичное, что не покрывается стандартом.
    // Тогда ты пишешь AttributeConverter, который переводит enum в строку (или
    // другое поле в БД) и обратно.

    // Если ты не укажешь ни @Enumerated, ни конвертер, Hibernate по умолчанию будет
    // использовать ORDINAL (число).
    // Если ты ничего не указываешь, Hibernate сохраняет enum как ordinal — то есть
    // число, соответствующее позиции константы в enum (0, 1, 2 и т.д.).
    // Это не всегда удобно и безопасно, потому что если ты изменишь порядок
    // констант в enum, данные в базе перестанут соответствовать.
    @Convert(converter = PaymentTransactionStatusConverter.class)
    private PaymentTransactionStatus paymentTransactionStatus;

    private String errorMessage;

    @ManyToOne
    @JoinColumn(name = "sourceBankAccountId")
    private BankAccount sourceBankAccount;

    @ManyToOne
    @JoinColumn(name = "destinationBankAccountId")
    private BankAccount destinationBankAccount;

    @OneToMany(mappedBy = "paymentTransaction", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Refund> refunds;
}
