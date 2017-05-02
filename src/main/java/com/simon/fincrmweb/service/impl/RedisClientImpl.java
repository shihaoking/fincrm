/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.simon.fincrmweb.service.impl;

import com.simon.fincrmweb.service.facade.RedisClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * @author jinshihao
 * @version $Id: CacheClientImpl.java, v 0.1 2016-11-30 10:45 jinshihao Exp $$
 */
@Service
public class RedisClientImpl implements RedisClient {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    /**
     * 默认缓存60秒
     */
    private static final long DEFAULT_TIMEOUT_SECONDS = 60;

    @Resource(name = "redisTemplate")
    private ValueOperations<String, Object> valueOperations;

    public void add(String key, Object value) {
        valueOperations.set(key, value, DEFAULT_TIMEOUT_SECONDS, TimeUnit.SECONDS);
    }

    public void add(String key, Object value, long timeOutSeconds) {
        valueOperations.set(key, value, timeOutSeconds, TimeUnit.SECONDS);
    }

    public Object get(String key) {
        return valueOperations.get(key);
    }

    public void delete(String key){
        redisTemplate.delete(key);
    }
}
