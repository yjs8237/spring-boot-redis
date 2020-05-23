package com.greatyun.springbootredis.repository;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@RedisHash("account")
@Data
public class Account {

    @Id
    private Long id;

    private String name;

    private String email;

}
