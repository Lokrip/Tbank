package org.lok.tedis.tbank.model.entity;

import java.time.LocalDateTime;

import org.antlr.v4.runtime.atn.SemanticContext.AND;
import org.hibernate.Hibernate;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.processing.SQL;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Version;
import lombok.Getter;
import lombok.Setter;

@MappedSuperclass
@Getter
@Setter
public class BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    // Оптимистическая блокировка (optimistic locking) — это механизм защиты данных
    // от одновременного изменения несколькими пользователями или процессами, без
    // использования блокировок на уровне базы данных.
    // 📌 Суть:
    // Когда два пользователя одновременно читают одну и ту же запись, а затем
    // пытаются её обновить — оптимистическая блокировка позволяет обнаружить
    // конфликт и предотвратить перезапись изменений друг друга.

    // 🧠 Принцип работы:
    // При чтении записи из базы вместе с данными берётся специальное поле,
    // например, version = 1.
    // Пользователь изменяет данные.
    // При сохранении Hibernate формирует SQL вида:
    // UPDATE product SET name = ?, version = version + 1 WHERE id = ? AND version =
    // 1;
    // Если кто-то другой уже обновил эту запись (например, версия стала 2), тогда:
    // Никакая строка не обновляется (WHERE version = 1 не сработал),
    // И Hibernate выбрасывает OptimisticLockException.
    // Чтобы реализовать оптимистическую блокировку в Java (с использованием JPA и
    // Hibernate), тебе нужно добавить в базовую сущность специальное поле — версию
    // объекта. Это поле помечается аннотацией @Version.

    // ❗ Изменения пользователя A просто затёрты действиями пользователя B — без
    // уведомления.
    // 🤔 Почему это проблема?
    // Всё зависит от цели и контекста приложения:

    // ✅ Это приемлемо, если:
    // Данные не критичны, и потеря предыдущих изменений не страшна.
    // Нет требований к "точности" или отслеживанию истории.
    // Ты осознанно хочешь, чтобы последнее изменение просто затирало старое
    // (например, для черновиков).

    // ❌ Это опасно, если:
    // Обновляются важные данные (цены, балансы, статусы заказов).
    // Требуется, чтобы пользователь знал, что кто-то уже изменил объект.
    // Конфликт изменений может вызвать потерю денег или ошибок в логике.

    @Version
    private Long version;
}
