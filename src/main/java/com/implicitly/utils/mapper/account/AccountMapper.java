/*
 * ©  Implicitly86 All Rights Reserved
 */

package com.implicitly.utils.mapper.account;

import com.implicitly.domain.account.Account;
import com.implicitly.dto.account.AccountDTO;
import com.implicitly.utils.mapper.EntityMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

/**
 * Реализация {@link EntityMapper} для сущности {@link Account}
 *
 * @author Emil Murzakaev.
 */
@Component
public class AccountMapper implements EntityMapper<Account, AccountDTO> {

    /**
     * Маппинг Entity -> Dto.
     *
     * @param source доменная сущность.
     * @return модель передачи данных, соответствующая доменной модели.
     */
    @Override
    public AccountDTO toDto(Account source) {
        if (source == null) {
            return null;
        }
        AccountDTO target = new AccountDTO();
        BeanUtils.copyProperties(source, target, "customer");
        return target;
    }

    /**
     * Маппинг Dto -> Entity.
     *
     * @param source модель передачи данных, соответствующая доменной модели.
     * @return доменная сущность.
     */
    @Override
    public Account toEntity(AccountDTO source) {
        if (source == null) {
            return null;
        }
        Account target = new Account();
        BeanUtils.copyProperties(source, target, "customer");
        return target;
    }

}
