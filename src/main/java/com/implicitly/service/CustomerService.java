/*
 * ©  Implicitly86 All Rights Reserved
 */

package com.implicitly.service;

import com.implicitly.dto.customer.CustomerDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Сервис работы с сущностью {@link CustomerDTO}
 *
 * @author Emil Murzakaev.
 */
public interface CustomerService {

    /**
     * Получение всех сущностей {@link CustomerDTO}.
     *
     * @param pageable {@link Pageable}
     * @return список {@link CustomerDTO}
     */
    Page<CustomerDTO> getAllCustomers(Pageable pageable);

    /**
     * Получение {@link CustomerDTO} по уникальному идентификатору.
     *
     * @param id уникальный идентификатор.
     * @return {@link CustomerDTO}.
     */
    CustomerDTO getCustomer(Long id);

    /**
     * Сохранение {@link CustomerDTO}
     *
     * @param customer {@link CustomerDTO}.
     * @return {@link CustomerDTO}
     */
    CustomerDTO saveCustomer(CustomerDTO customer);

    /**
     * Редактирование {@link CustomerDTO} по уникальному идентификатору.
     *
     * @param id уникальный идентификатор.
     * @param customer {@link CustomerDTO}.
     * @return {@link CustomerDTO}
     */
    CustomerDTO updateCustomer(Long id, CustomerDTO customer);

    /**
     * Удаление {@link CustomerDTO} по уникальному идентификатору.
     *
     * @param id уникальный идентификатор.
     */
    void deleteCustomer(Long id);

}
