/*
 * ©  Implicitly86 All Rights Reserved
 */

package com.implicitly.domain.account;

import com.implicitly.domain.IdentifiedEntity;
import com.implicitly.domain.customer.Customer;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Доменная модель сущности <strong>Банковский счет</strong>.
 *
 * @author Emil Murzakaev.
 */
@Data
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "`account`")
public class Account implements IdentifiedEntity {

    /**
     * serialVersionUID.
     */
    private static final long serialVersionUID = 4435398303003335465L;

    /**
     * Уникальный идентификатор.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "account_gen")
    @SequenceGenerator(name = "account_gen", sequenceName = "sq_account", allocationSize = 1)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;

    /**
     * Номер счета.
     */
    @Column(name = "number", nullable = false)
    private String number;

    /**
     * Баланс.
     */
    @Column(name = "balance", nullable = false)
    private Double balance;

    /**
     * Учетная запись клиента.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

}
