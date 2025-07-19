package org.lok.tedis.tbank.controller.kafka.consumer;

import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.lok.tedis.tbank.model.enums.PaymentTransactionCommand;
import org.lok.tedis.tbank.service.handler.PaymentTransactionCommandHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class PaymentTransactionCommadListener {
    private final Map<PaymentTransactionCommand, PaymentTransactionCommandHandler> commandHandlers;

    @KafkaListener(topics = "payment-command", containerFactory = "kafkaListenerContainerFactory")
    public void consumePaymentTransactionCommand(ConsumerRecord<String, String> record) {
        var command = getPaymentTransactionCommand(record);
        var handler = commandHandlers.get(command);

        if (handler == null) {
            throw new IllegalArgumentException("Unsupported payment transaction command, record: " + record);
        }

        // handler.process();
    }

    private PaymentTransactionCommand getPaymentTransactionCommand(ConsumerRecord<String, String> record) {
        var commandHeader = record.headers().lastHeader("command");
        if (commandHeader != null) {
            return PaymentTransactionCommand.fromString(new String(commandHeader.value()));
        }
        return PaymentTransactionCommand.UNKNOWN; 
    }
}
