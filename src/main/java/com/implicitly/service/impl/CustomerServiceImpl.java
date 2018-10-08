/*
 * ©  Implicitly86 All Rights Reserved
 */

package com.implicitly.service.impl;

import com.implicitly.domain.customer.Customer;
import com.implicitly.dto.customer.CustomerDTO;
import com.implicitly.exceptions.NotFoundException;
import com.implicitly.persistence.customer.CustomerRepository;
import com.implicitly.service.CustomerService;
import com.implicitly.utils.mapper.customer.CustomerMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * Реализация сервиса работы с сущностью {@link CustomerDTO}
 *
 * @author Emil Murzakaev.
 */
@Slf4j
@Service
public class CustomerServiceImpl implements CustomerService {

    /**
     * {@link CustomerRepository}
     */
    private final CustomerRepository repository;
    /**
     * {@link CustomerMapper}
     */
    private final CustomerMapper mapper;

    /**
     * Конструктор.
     *
     * @param repository {@link CustomerRepository}.
     * @param mapper {@link CustomerMapper}.
     */
    @Autowired
    public CustomerServiceImpl(CustomerRepository repository, CustomerMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    /**
     * Получение всех сущностей {@link CustomerDTO}.
     *
     * @param pageable {@link Pageable}
     * @return список {@link CustomerDTO}
     */
    @Override
    public Page<CustomerDTO> getAllCustomers(Pageable pageable) {
        return repository.findAll(pageable).map(mapper::toDto);
    }

    /**
     * Получение {@link CustomerDTO} по уникальному идентификатору.
     *
     * @param id уникальный идентификатор.
     * @return {@link CustomerDTO}.
     */
    @Override
    public CustomerDTO getCustomer(Long id) {
        Customer customer = repository.findOne(id);
        if (customer == null) {
            throw new NotFoundException();
        }
        return mapper.toDto(customer);
    }

    /**
     * Сохранение {@link CustomerDTO}
     *
     * @param customer {@link CustomerDTO}.
     */
    @Override
    public CustomerDTO saveCustomer(CustomerDTO customer) {
        Customer entity = mapper.toEntity(customer);
        Customer result = repository.saveAndFlush(entity);
        return mapper.toDto(result);
    }

    /**
     * Редактирование {@link CustomerDTO} по уникальному идентификатору.
     *
     * @param id уникальный идентификатор.
     * @param customer {@link CustomerDTO}.
     * @return {@link CustomerDTO}
     */
    @Override
    public CustomerDTO updateCustomer(Long id, CustomerDTO customer) {
        if (!repository.exists(id)) {
            throw new NotFoundException();
        }
        Customer entity = mapper.toEntity(customer);
        Customer result = repository.saveAndFlush(entity);
        return mapper.toDto(result);
    }

    /**
     * Удаление {@link CustomerDTO} по уникальному идентификатору.
     *
     * @param id уникальный идентификатор.
     */
    @Override
    public void deleteCustomer(Long id) {
        if (!repository.exists(id)) {
            throw new NotFoundException();
        }
        repository.delete(id);
    }

}
