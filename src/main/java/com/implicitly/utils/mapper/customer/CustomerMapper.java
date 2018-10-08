/*
 * ©  Implicitly86 All Rights Reserved
 */

package com.implicitly.utils.mapper.customer;

import com.implicitly.domain.customer.Customer;
import com.implicitly.dto.customer.CustomerDTO;
import com.implicitly.utils.mapper.EntityMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

/**
 * Реализация {@link EntityMapper} для сущности {@link Customer}
 *
 * @author Emil Murzakaev.
 */
@Component
public class CustomerMapper implements EntityMapper<Customer, CustomerDTO> {

    /**
     * Маппинг Entity -> Dto.
     *
     * @param source доменная сущность.
     * @return модель передачи данных, соответствующая доменной модели.
     */
    @Override
    public CustomerDTO toDto(Customer source) {
        if (source == null) {
            return null;
        }
        CustomerDTO target = CustomerDTO.builder().build();
        BeanUtils.copyProperties(source, target);
        return target;
    }

    /**
     * Маппинг Dto -> Entity.
     *
     * @param source модель передачи данных, соответствующая доменной модели.
     * @return доменная сущность.
     */
    @Override
    public Customer toEntity(CustomerDTO source) {
        if (source == null) {
            return null;
        }
        Customer target = Customer.builder().build();
        BeanUtils.copyProperties(source, target);
        return target;
    }

}
