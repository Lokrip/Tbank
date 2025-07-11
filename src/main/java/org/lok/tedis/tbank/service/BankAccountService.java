package org.lok.tedis.tbank.service;

import java.util.Optional;

import org.lok.tedis.tbank.model.entity.BankAccount;
import org.lok.tedis.tbank.repository.BankAccountRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class BankAccountService {
    private final BankAccountRepository bankAccountRepository;

    @Transactional
    public Optional<BankAccount> findById(Long id) {
        return bankAccountRepository.findById(id);
    }
}
 