package com.im.common.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.concurrent.TimeUnit;

/**
 * @Description redis操作工具类
 * @data 2019/8/14
 * @Author: LiuBin
 * @Modified By:
 */
@Component
public class RedisUtil {

    //    @Autowired
//    @Qualifier("customStringTemplate")
    private static StringRedisTemplate stingRedisTemplate;

    //    @Autowired
//    @Qualifier("customRedisTemplate")
    private static RedisTemplate redisTemplate;

    @Autowired
    @Qualifier("customStringTemplate")
    public void setStingRedisTemplate(StringRedisTemplate stingRedisTemplate) {
        RedisUtil.stingRedisTemplate = stingRedisTemplate;
    }

    @Autowired
    @Qualifier("customRedisTemplate")
    public void setRedisTemplate(RedisTemplate redisTemplate) {
        RedisUtil.redisTemplate = redisTemplate;
    }

    /**
     * 获取redis实例
     *
     * @param index         下标
     * @param redisTemplate
     * @return
     */
    public static RedisTemplate getTemplate(RedisTemplate redisTemplate, int index) {
        JedisConnectionFactory jedisConnectionFactory = (JedisConnectionFactory) redisTemplate.getConnectionFactory();
        jedisConnectionFactory.setDatabase(index);
        redisTemplate.setConnectionFactory(jedisConnectionFactory);
        return redisTemplate;
    }


    /**
     * 切换redis数据库
     * 当存入redis的值为对象时 使用该方法
     *
     * @param index 下标
     * @return
     */
    public static RedisTemplate getRedisTemplate(int index) {
        return getTemplate(redisTemplate, index);
    }

    /**
     * 切换redis数据库
     * 当存入redis的值为字符串时 使用该方法
     *
     * @param index 下标
     * @return
     */
    public static StringRedisTemplate getStringRedisTemplate(int index) {
        return (StringRedisTemplate) getTemplate(stingRedisTemplate, index);
    }
    //============================object=============================

    /**
     * 指定缓存失效时间
     *
     * @param key   键
     * @param time  时间(秒)
     * @param index 下标
     * @return
     */
    public static boolean expire(int index, String key, long time) {
        try {
            if (time > 0) {
                getRedisTemplate(index).expire(key, time, TimeUnit.SECONDS);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 根据key 获取过期时间
     *
     * @param key   键 不能为null
     * @param index 下标
     * @return 时间(秒) 返回0代表为永久有效 失效时间为负数，说明该主键未设置失效时间（失效时间默认为-1）
     */
    public static long getExpire(int index, String key) {
        return getRedisTemplate(index).getExpire(key, TimeUnit.SECONDS);
    }

    /**
     * 判断key是否存在
     *
     * @param key   键
     * @param index 下标
     * @return true 存在 false 不存在
     */
    public static boolean hasKey(int index, String key) {
        try {
            return getRedisTemplate(index).hasKey(key);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 删除缓存
     *
     * @param key   可以传一个值 或多个
     * @param index 下标
     */
    public static void del(int index, String... key) {
        if (key != null && key.length > 0) {
            if (key.length == 1) {
                getRedisTemplate(index).delete(key[0]);
            } else {
                getRedisTemplate(index).delete(CollectionUtils.arrayToList(key));
            }
        }
    }

    /**
     * 普通缓存获取
     *
     * @param key   键
     * @param index 下标
     * @return 值
     */
    public static <T> T get(int index, String key) {
        return key == null ? null : (T) getRedisTemplate(index).opsForValue().get(key);
    }

    /**
     * 普通缓存放入
     *
     * @param key   键
     * @param value 值
     * @param index 下标
     * @return true成功 false失败
     */
    public static boolean set(int index, String key, Object value) {
        try {
            getRedisTemplate(index).opsForValue().set(key, value);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    /**
     * 普通缓存放入并设置时间
     *
     * @param key   键
     * @param value 值
     * @param time  时间(秒) time要大于0 如果time小于等于0 将设置无限期
     * @param index 下标
     * @return true成功 false 失败
     */
    public static boolean set(int index, String key, Object value, long time) {
        try {
            getRedisTemplate(index).opsForValue().set(key, value, time, TimeUnit.SECONDS);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 递增 此时value值必须为int类型 否则报错
     *
     * @param key   键
     * @param delta 要增加几(大于0)
     * @param index 下标
     * @return
     */
    public static long incr(int index, String key, long delta) {
        if (delta < 0) {
            throw new RuntimeException("递增因子必须大于0");
        }
        return getRedisTemplate(index).opsForValue().increment(key, delta);
    }

    /**
     * 递减
     *
     * @param key   键
     * @param delta 要减少几(小于0)
     * @param index 下标
     * @return
     */
    public static long decr(int index, String key, long delta) {
        if (delta < 0) {
            throw new RuntimeException("递减因子必须大于0");
        }
        return getRedisTemplate(index).opsForValue().increment(key, -delta);
    }

    //============================String=============================

    /**
     * 指定缓存失效时间
     *
     * @param key   键
     * @param time  时间(秒)
     * @param index 下标
     * @return
     */
    public static boolean expireString(int index, String key, long time) {
        try {
            if (time > 0) {
                getStringRedisTemplate(index).expire(key, time, TimeUnit.SECONDS);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 根据key 获取过期时间
     *
     * @param key   键 不能为null
     * @param index 下标
     * @return 时间(秒) 返回0代表为永久有效 失效时间为负数，说明该主键未设置失效时间（失效时间默认为-1）
     */
    public static long getExpireString(int index, String key) {
        return getStringRedisTemplate(index).getExpire(key, TimeUnit.SECONDS);
    }

    /**
     * 判断key是否存在
     *
     * @param key   键
     * @param index 下标
     * @return true 存在 false 不存在
     */
    public static boolean hasKeyString(int index, String key) {
        try {
            return getStringRedisTemplate(index).hasKey(key);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 删除缓存
     *
     * @param key   可以传一个值 或多个
     * @param index 下标
     */
    public static void delString(int index, String... key) {
        if (key != null && key.length > 0) {
            if (key.length == 1) {
                getStringRedisTemplate(index).delete(key[0]);
            } else {
                getStringRedisTemplate(index).delete(CollectionUtils.arrayToList(key));
            }
        }
    }

    /**
     * 普通缓存获取
     *
     * @param key   键
     * @param index 下标
     * @return 值
     */
    public static <T> T getString(int index, String key) {
        return key == null ? null : (T) getStringRedisTemplate(index).opsForValue().get(key);
    }

    /**
     * 普通缓存放入
     *
     * @param key   键
     * @param value 值
     * @param index 下标
     * @return true成功 false失败
     */
    public static boolean setString(int index, String key, String value) {
        try {
            getStringRedisTemplate(index).opsForValue().set(key, value);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    /**
     * 普通缓存放入并设置时间
     *
     * @param key   键
     * @param value 值
     * @param time  时间(秒) time要大于0 如果time小于等于0 将设置无限期
     * @param index 下标
     * @return true成功 false 失败
     */
    public static boolean setString(int index, String key, String value, long time) {
        try {
            getStringRedisTemplate(index).opsForValue().set(key, value, time, TimeUnit.SECONDS);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 递增 此时value值必须为int类型 否则报错
     *
     * @param key   键
     * @param delta 要增加几(大于0)
     * @param index 下标
     * @return
     */
    public static long incrString(int index, String key, long delta) {
        if (delta < 0) {
            throw new RuntimeException("递增因子必须大于0");
        }
        return getStringRedisTemplate(index).opsForValue().increment(key, delta);
    }

    /**
     * 递减
     *
     * @param key   键
     * @param delta 要减少几(小于0)
     * @param index 下标
     * @return
     */
    public static long decrString(int index, String key, long delta) {
        if (delta < 0) {
            throw new RuntimeException("递减因子必须大于0");
        }
        return getStringRedisTemplate(index).opsForValue().increment(key, -delta);
    }
}
