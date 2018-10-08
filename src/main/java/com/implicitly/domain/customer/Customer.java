/*
 * ©  Implicitly86 All Rights Reserved
 */

package com.implicitly.domain.customer;

import com.implicitly.domain.IdentifiedEntity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Доменная модель сущности <strong>Клиент</strong>.
 *
 * @author Emil Murzakaev.
 */
@Data
@Builder
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "`customer`")
public class Customer implements IdentifiedEntity {

    /**
     * serialVersionUID.
     */
    private static final long serialVersionUID = -4640186599507173911L;

    /**
     * Уникальный идентификатор.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "customer_gen")
    @SequenceGenerator(name = "customer_gen", sequenceName = "sq_customer", allocationSize = 1)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;

    /**
     * Имя клиента.
     */
    @Column(name = "first_name")
    private String firstName;

    /**
     * Фамилия клиента.
     */
    @Column(name = "last_name")
    private String lastName;

    /**
     * Отчество клиента.
     */
    @Column(name = "middle_name")
    private String middleName;

    /**
     * Адрес клиента.
     */
    @Column(name = "address")
    private String address;

    /**
     * Телефон клиента.
     */
    @Column(name = "phone")
    private String phone;

    /**
     * Почта клиента.
     */
    @Column(name = "email")
    private String email;

}
