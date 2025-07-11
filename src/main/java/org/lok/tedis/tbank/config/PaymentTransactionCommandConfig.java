package org.lok.tedis.tbank.config;

import java.util.HashMap;
import java.util.Map;

import org.lok.tedis.tbank.model.enums.PaymentTransactionCommand;
import org.lok.tedis.tbank.service.handler.CancelPaymentTransactionHandler;
import org.lok.tedis.tbank.service.handler.CreatePaymentTransactionHandler;
import org.lok.tedis.tbank.service.handler.PaymentTransactionCommandHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PaymentTransactionCommandConfig {
    @Bean
    public Map<PaymentTransactionCommand, PaymentTransactionCommandHandler> commandHandlers(
            CreatePaymentTransactionHandler createPaymentTransactionHandler,
            CancelPaymentTransactionHandler cancelPaymentTransactionHandler) {
        Map<PaymentTransactionCommand, PaymentTransactionCommandHandler> commandHandlers = new HashMap<>();
        commandHandlers.put(PaymentTransactionCommand.CREATE, createPaymentTransactionHandler);
        commandHandlers.put(PaymentTransactionCommand.REFUND, cancelPaymentTransactionHandler);
        return commandHandlers;
    }
}
