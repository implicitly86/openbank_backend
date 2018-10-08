/*
 * ©  Implicitly86 All Rights Reserved
 */

package com.implicitly.persistence.account;

import com.implicitly.domain.account.Account;
import com.implicitly.domain.customer.Customer;
import com.implicitly.persistence.BaseRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

/**
 * Репозиторий сущности {@link Account}.
 *
 * @author Emil Murzakaev.
 */
@Component
public interface AccountRepository extends BaseRepository<Account, Long> {

    /**
     * Поиск аккаунта по номеру.
     *
     * @param number номер счета.
     * @return {@link Account}
     */
    Account findByNumber(String number);

    /**
     * Поиск аккаунтов, связанных с клиентом. Вывод в виде {@link Page<Account>}.
     *
     * @param customer {@link Customer}.
     * @param pageable {@link Pageable}.
     * @return {@link }Page<Account>}.
     */
    Page<Account> findAllByCustomer(Customer customer, Pageable pageable);

}
