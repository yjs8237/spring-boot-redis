package com.greatyun.springbootredis;

import com.greatyun.springbootredis.repository.Account;
import com.greatyun.springbootredis.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

@Component
public class AppRunner implements CommandLineRunner {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public void run(String... args) throws Exception {

        ValueOperations<String, String> operations = redisTemplate.opsForValue();
        operations.set("name" , "jisang");

        Account account = new Account();
        account.setEmail("email");
        account.setName("greatyun");

        accountRepository.save(account);
    }
}
