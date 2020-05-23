package com.greatyun.springbootredis.controller;

import com.greatyun.springbootredis.repository.Account;
import com.greatyun.springbootredis.repository.AccountRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
public class HomeController {

    @Autowired
    private AccountRepository accountRepository;
    
    @GetMapping("/")
    public Iterable<Account> home() {
        Iterable<Account> all = accountRepository.findAll();
        all.forEach(account -> log.info(account.getId() + " " + account.getName() + " "  + account.getEmail()));
        return all;
    }

    @GetMapping("/delete/{id}")
    public Iterable<Account> delete(@PathVariable (value = "id") Long id) {
        accountRepository.deleteById(id);
        Iterable<Account> all = accountRepository.findAll();
        return all;
    }
}
