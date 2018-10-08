/*
 * ©  Implicitly86 All Rights Reserved
 */

package com.implicitly.service;

import com.implicitly.dto.account.AccountDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Сервис работы с сущностью {@link AccountDTO}
 *
 * @author Emil Murzakaev.
 */
public interface AccountService {

    /**
     * Получение всех сущностей {@link AccountDTO}, относящихся к текущему пользователю.
     *
     * @param pageable {@link Pageable}.
     * @return список {@link AccountDTO}.
     */
    Page<AccountDTO> getAllAccounts(Pageable pageable);

    /**
     * Получение {@link AccountDTO} по уникальному идентификатору.
     *
     * @param id уникальный идентификатор.
     * @return {@link AccountDTO}.
     */
    AccountDTO getAccount(Long id);

    /**
     * Добавление нового счета.
     *
     * @param account {@link AccountDTO}.
     */
    void addAccount(AccountDTO account);

    /**
     * Удаление {@link AccountDTO} по уникальному идентификатору.
     *
     * @param id уникальный идентификатор.
     */
    void deleteAccount(Long id);

    /**
     * Перевод денежных средств с одного счета на другой.
     *
     * @param from счет, с которого будет списана сумма.
     * @param to счет, на который будет зачислена сумма.
     * @param amount сумма.
     */
    void transfer(String from, String to, Double amount);

}
