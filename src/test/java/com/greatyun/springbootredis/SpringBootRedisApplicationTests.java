package com.greatyun.springbootredis;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class SpringBootRedisApplicationTests {

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    public void before() throws Exception{
        ZSetOperations zSetOperations = redisTemplate.opsForZSet();
        zSetOperations.removeRange("order:list:test" , 0 , -1);
        List<Order> list = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            Order order = new Order();
            order.setId(Long.parseLong(String.valueOf(i)));
            order.setName("jisang");
            zSetOperations.add("order:list:test" ,  objectMapper.writeValueAsString(order), i);
        }
    }

    @Test
    void contextLoads() throws Exception{
        ZSetOperations zSetOperations = redisTemplate.opsForZSet();

        Set<String> range = zSetOperations.range("order:list:test", 0, -1);
        for (String order : range) {
            //System.out.println(order);
            Order order1 = objectMapper.readValue(order , Order.class);
            System.out.println(order1.getId() + " " + order1.getName());
        }

    }



}
