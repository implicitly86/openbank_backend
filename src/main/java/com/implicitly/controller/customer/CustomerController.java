/*
 * ©  Implicitly86 All Rights Reserved
 */

package com.implicitly.controller.customer;

import static org.springframework.http.ResponseEntity.noContent;
import static org.springframework.http.ResponseEntity.ok;

import com.implicitly.dto.customer.CustomerDTO;
import com.implicitly.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Реализация {@link RestController}, обслуживающая запросы к сущности {@link CustomerDTO}.
 *
 * @author Emil Murzakaev.
 */
@RestController
public class CustomerController {

    /**
     * {@link CustomerService}
     */
    private final CustomerService customerService;

    /**
     * Конструктор.
     *
     * @param customerService {@link CustomerService}
     */
    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    /**
     * Получение всех сущностей {@link CustomerDTO}.
     *
     * @return список {@link CustomerDTO}
     */
    @GetMapping(value = "/customer", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Page<CustomerDTO>> getAllCustomers(Pageable pageable) {
        return ok(customerService.getAllCustomers(pageable));
    }

    /**
     * Получение {@link CustomerDTO} по уникальному идентификатору.
     *
     * @param id уникальный идентификатор.
     * @return {@link CustomerDTO}.
     */
    @GetMapping(value = "/customer/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<CustomerDTO> getCustomer(@PathVariable("id") Long id) {
        return ok(customerService.getCustomer(id));
    }

    /**
     * Сохранение {@link CustomerDTO}
     *
     * @param customer {@link CustomerDTO}.
     */
    @PostMapping(value = "/customer", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<CustomerDTO> saveCustomer(@RequestBody CustomerDTO customer) {
        return ok(customerService.saveCustomer(customer));
    }

    /**
     * Редактирование {@link CustomerDTO} по уникальному идентификатору.
     *
     * @param id уникальный идентификатор.
     * @param customer {@link CustomerDTO}.
     */
    @PutMapping(value = "/customer/{id}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<CustomerDTO> updateCustomer(
            @PathVariable("id") Long id, @RequestBody CustomerDTO customer) {
        return ok(customerService.updateCustomer(id, customer));
    }

    /**
     * Удаление {@link CustomerDTO} по уникальному идентификатору.
     *
     * @param id уникальный идентификатор.
     */
    @DeleteMapping(value = "/customer/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable("id") Long id) {
        customerService.deleteCustomer(id);
        return noContent().build();
    }

}
