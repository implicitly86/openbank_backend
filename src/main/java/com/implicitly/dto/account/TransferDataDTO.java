/*
 * ©  Implicitly86 All Rights Reserved
 */

package com.implicitly.dto.account;

import lombok.Data;

/**
 * Модель передачи данных сущности, отвечающей за трансфер денежных средств.
 *
 * @author Emil Murzakaev.
 */
@Data
public class TransferDataDTO {

    /**
     * Номер счета, с которого списывается сумма.
     */
    private String from;

    /**
     * Номер счета, на который зачисляется сумма.
     */
    private String to;

    /**
     * Сумма.
     */
    private Double amount;

}
