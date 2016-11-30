/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.simon.fincrm.service.facade;

/**
 * @author jinshihao
 * @version $Id: RedisClient.java, v 0.1 2016-11-30 10:45 jinshihao Exp $$
 */
public interface RedisClient {
    /**
     * 添加缓存
     * @param key
     * @param value
     */
    void add(String key, Object value);

    /**
     * 添加缓存，指定超时时间，单位秒
     * @param key
     * @param value
     * @param timeOutSeconds
     */
    void add(String key, Object value, long timeOutSeconds);

    /**
     * 获取缓存
     * @param key
     * @return
     */
    Object get(String key);

    /**
     * 删除缓存
     * @param key
     */
    void delete(String key);
}
