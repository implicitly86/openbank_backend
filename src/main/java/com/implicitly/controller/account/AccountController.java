/*
 * ©  Implicitly86 All Rights Reserved
 */

package com.implicitly.controller.account;

import static org.springframework.http.ResponseEntity.noContent;
import static org.springframework.http.ResponseEntity.ok;

import com.implicitly.dto.account.AccountDTO;
import com.implicitly.dto.account.TransferDataDTO;
import com.implicitly.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Реализация {@link RestController}, обслуживающая запросы к сущности {@link AccountDTO}.
 *
 * @author Emil Murzakaev.
 */
@RestController
public class AccountController {

    /**
     * {@link AccountService}.
     */
    private final AccountService accountService;

    /**
     * Конструктор.
     *
     * @param accountService {@link AccountService}
     */
    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    /**
     * Получение всех сущностей {@link AccountDTO}, относящихся к текущему пользователю.
     *
     * @param pageable {@link Pageable}.
     * @return список {@link AccountDTO}.
     */
    @GetMapping(value = "/account", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Page<AccountDTO>> getAllAccounts(Pageable pageable) {
        return ok(accountService.getAllAccounts(pageable));
    }

    /**
     * Получение {@link AccountDTO} по уникальному идентификатору.
     *
     * @param id уникальный идентификатор.
     * @return {@link AccountDTO}.
     */
    @GetMapping(value = "/account/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<AccountDTO> getAccount(@PathVariable("id") Long id) {
        return ok(accountService.getAccount(id));
    }

    /**
     * Добавление нового счета.
     *
     * @param account {@link AccountDTO}.
     */
    @PostMapping(value = "/account", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Void> addAccount(@RequestBody AccountDTO account) {
        accountService.addAccount(account);
        return noContent().build();
    }

    /**
     * Удаление {@link AccountDTO} по уникальному идентификатору.
     *
     * @param id уникальный идентификатор.
     */
    @DeleteMapping(value = "/account/{id}")
    public ResponseEntity<Void> deleteAccount(@PathVariable("id") Long id) {
        accountService.deleteAccount(id);
        return noContent().build();
    }

    /**
     * Перевод денежных средств с одного счета на другой.
     *
     * @param transferData {@link TransferDataDTO}
     */
    @PostMapping(value = "/account/transfer", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Void> transfer(@RequestBody TransferDataDTO transferData) {
        accountService.transfer(transferData.getFrom(), transferData.getTo(), transferData.getAmount());
        return noContent().build();
    }

    /**
     * Добавить сумму на указанный счет.
     *
     * @param transferData {@link TransferDataDTO}
     */
    @PostMapping(value = "/account/add-amount", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Void> addAmount(@RequestBody TransferDataDTO transferData) {
        accountService.addAmount(transferData.getTo(), transferData.getAmount());
        return noContent().build();
    }

    /**
     * Вычесть сумму с указанного счета.
     *
     * @param transferData {@link TransferDataDTO}
     */
    @PostMapping(value = "/account/subtract-amount", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Void> subtractAmount(@RequestBody TransferDataDTO transferData) {
        accountService.subtractAmount(transferData.getTo(), transferData.getAmount());
        return noContent().build();
    }

}
