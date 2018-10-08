/*
 * ©  Implicitly86 All Rights Reserved
 */

package com.implicitly.dto.account;

import com.implicitly.dto.IdentifiedDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Модель передачи данных сущности <strong>Банковский счет</strong>.
 *
 * @author Emil Murzakaev.
 */
@Data
@EqualsAndHashCode
public class AccountDTO implements IdentifiedDTO {

    /**
     * serialVersionUID.
     */
    private static final long serialVersionUID = 3365726615380640830L;

    /**
     * Уникальный идентификатор.
     */
    private Long id;

    /**
     * Номер счета.
     */
    private String number;

    /**
     * Баланс.
     */
    private Double balance;

}
