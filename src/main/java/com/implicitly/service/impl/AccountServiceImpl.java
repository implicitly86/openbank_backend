/*
 * ©  Implicitly86 All Rights Reserved
 */

package com.implicitly.service.impl;

import com.implicitly.domain.account.Account;
import com.implicitly.domain.security.User;
import com.implicitly.dto.account.AccountDTO;
import com.implicitly.exceptions.NotFoundException;
import com.implicitly.persistence.account.AccountRepository;
import com.implicitly.persistence.security.UserRepository;
import com.implicitly.service.AccountService;
import com.implicitly.utils.UserUtils;
import com.implicitly.utils.mapper.account.AccountMapper;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Реализация сервиса работы с сущностью {@link AccountDTO}
 *
 * @author Emil Murzakaev.
 */
@Service
public class AccountServiceImpl implements AccountService {

    /**
     * {@link AccountRepository}
     */
    private final AccountRepository accountRepository;
    /**
     * {@link UserRepository}
     */
    private final UserRepository userRepository;
    /**
     * {@link AccountMapper}
     */
    private final AccountMapper accountMapper;

    /**
     * Конструктор.
     *
     * @param accountRepository {@link AccountRepository}.
     * @param userRepository {@link UserRepository}.
     * @param accountMapper {@link AccountMapper}.
     */
    @Autowired
    public AccountServiceImpl(AccountRepository accountRepository, UserRepository userRepository, AccountMapper accountMapper) {
        this.accountRepository = accountRepository;
        this.userRepository = userRepository;
        this.accountMapper = accountMapper;
    }

    /**
     * Получение всех сущностей {@link AccountDTO}, относящихся к текущему пользователю.
     *
     * @param pageable {@link Pageable}.
     * @return список {@link AccountDTO}.
     */
    @Override
    public Page<AccountDTO> getAllAccounts(Pageable pageable) {
        User currentUser = currentUser();
        return accountRepository.findAllByCustomer(currentUser.getCustomer(), pageable).map(accountMapper::toDto);
    }

    /**
     * Получение {@link AccountDTO} по уникальному идентификатору.
     *
     * @param id уникальный идентификатор.
     * @return {@link AccountDTO}.
     */
    @Override
    public AccountDTO getAccount(Long id) {
        User currentUser = currentUser();
        Account account = accountRepository.findOne(id);
        if (account == null || !account.getCustomer().equals(currentUser.getCustomer())) {
            throw new NotFoundException();
        }
        return accountMapper.toDto(account);
    }

    /**
     * Добавление нового счета.
     *
     * @param account {@link AccountDTO}.
     */
    @Override
    public void addAccount(AccountDTO account) {
        Account existAccount = accountRepository.findByNumber(account.getNumber());
        if (existAccount != null) {
            throw new RuntimeException(String.format("Account with number %s already exists", account.getNumber()));
        }
        User currentUser = currentUser();
        Account entity = accountMapper.toEntity(account);
        entity.setBalance(0.0);
        entity.setCustomer(currentUser.getCustomer());
        accountRepository.save(entity);
    }

    /**
     * Удаление {@link AccountDTO} по уникальному идентификатору.
     *
     * @param id уникальный идентификатор.
     */
    @Override
    public void deleteAccount(Long id) {
        User currentUser = currentUser();
        Account account = accountRepository.findOne(id);
        if (account == null || !account.getCustomer().equals(currentUser.getCustomer())) {
            throw new NotFoundException();
        }
        accountRepository.delete(account);
    }

    /**
     * Перевод денежных средств с одного счета на другой.
     *
     * @param from счет, с которого будет списана сумма.
     * @param to счет, на который будет зачислена сумма.
     * @param amount сумма.
     */
    @Override
    @Transactional
    public void transfer(String from, String to, Double amount) {
        User currentUser = currentUser();
        Account fromAccount = accountRepository.findByNumber(from);
        if (fromAccount == null || !fromAccount.getCustomer().equals(currentUser.getCustomer())) {
            throw new NotFoundException();
        }
        Account toAccount = accountRepository.findByNumber(to);
        if (toAccount == null) {
            throw new NotFoundException();
        }
        validateAmount(fromAccount, amount);
        fromAccount.setBalance(fromAccount.getBalance() - amount);
        toAccount.setBalance(toAccount.getBalance() + amount);
        accountRepository.save(fromAccount);
        accountRepository.save(toAccount);
    }

    /**
     * Получение текущего аутентифицированного пользователя.
     *
     * @return {@link User}.
     */
    private User currentUser() {
        Optional<Long> userId = UserUtils.getCurrentUserId();
        if (!userId.isPresent()) {
            throw new RuntimeException("User not authenticated");
        }
        if (!userRepository.exists(userId.get())) {
            throw new RuntimeException("User not found");
        }
        return userRepository.findOne(userId.get());
    }

    /**
     * Валидация суммы, которая должна быть списана со счета.
     *
     * @param account аккаунт.
     * @param amount сумма, списываемая со счета.
     */
    private void validateAmount(Account account, Double amount) {
        if (amount < 0) {
            throw new RuntimeException("Amount should be positive");
        }
        Double currentBalance = account.getBalance();
        if (currentBalance - amount < 0) {
            throw new RuntimeException("Amount not valid");
        }
    }

}
