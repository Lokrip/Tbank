package org.lok.tedis.tbank.util;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

// Аннотация @UtilityClass из библиотеки Lombok делает класс утилитарным. 
// Это означает следующее:
// Что делает @UtilityClass:
// Класс становится final — его нельзя наследовать.
// Добавляется приватный конструктор — 
// класс нельзя инстанцировать (new JsonConvertor() вызовет ошибку).
// Все методы и поля автоматически становятся static, даже если вы забудете явно указать static.
@Slf4j
@UtilityClass
public class JsonConvertor {
    private final ObjectMapper mapper = new ObjectMapper();

    public <T> T toObject(String json, Class<T> clazz) {
        // текущий json мы переобразуем объект CreatePaymentTransactionRequest
        // он возврощает исключение нам надо их обработать например мы передали
        // не верный json при мапинге
        try {
            return mapper.readValue(json, clazz);
        } catch (JsonProcessingException e) {
            log.error("Json deserializing exception", e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
