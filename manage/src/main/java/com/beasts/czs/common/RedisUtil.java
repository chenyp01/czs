package com.beasts.czs.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @Auther: chenjie
 * @Date: 2019/2/19 23:44
 * @Description:redis工具类
 */
@Component
public class RedisUtil {

    @Autowired
    private RedisTemplate<String,Object> redisTemplate;
    /**
     *
     * 功能描述: 设置缓存失效时间
     *
     * @param key: redis中的key
     * @param time :数据存在在redis的时间（单位：秒）
     * @return: 是否保存成功
     * @auther: 陈杰
     * @date: 2018/10/5 15:58
     */
    public boolean expire(String key, long time){
        boolean pag = false;
        try {
            if (time > 0) {
                redisTemplate.expire(key, time, TimeUnit.SECONDS);
            }
            pag = true;
        } catch (Exception e) {
            e.printStackTrace();
            pag = false;
        }
        return pag;
    }

    /**
     *
     * 功能描述: 获取redis失效时间
     *
     * @param key: redis中的key
     * @return: 返回失效时间（单位：秒）
     * @auther: 陈杰
     * @date: 2018/10/5 16:01
     */
    public long getExpire(String key) {
        return redisTemplate.getExpire(key, TimeUnit.SECONDS);
    }

    /**
     *
     * 功能描述: 判断redis中的key是否存在
     *
     * @param key: redis中的key
     * @return: 返回是否存在
     * @auther: 陈杰
     * @date: 2018/10/5 16:05
     */
    public boolean hasKey(String key) {
        boolean pag = false;
        try {
            pag = redisTemplate.hasKey(key);
        } catch (Exception e) {
            e.printStackTrace();
            pag = false;
        }
        return pag;
    }

    /**
     *
     * 功能描述: 删除redis数据
     *
     * @param key: 需要删除的key
     * @return:空
     * @auther: 陈杰
     * @date: 2018/10/5 16:06
     */
    public void del(String... key) {
        if (key != null && key.length > 0) {
            if (key.length == 1) {
                redisTemplate.delete(key[0]);
            } else {
                redisTemplate.delete(CollectionUtils.arrayToList(key));
            }
        }
    }

    /**
     *
     * 功能描述: 获取数据
     *
     * @param key: 需要获取数据的key
     * @return: 数据对象
     * @auther: 陈杰
     * @date: 2018/10/5 16:12
     */
    public Object get(String key) {
        return key == null ? null : redisTemplate.opsForValue().get(key);
    }


    /**
     *
     * 功能描述: 写入redis
     *
     * @param key: redis中key
     * @param value: 需要写入的值对象
     * @return:
     * @auther: 陈杰
     * @date: 2018/10/5 16:14
     */
    public boolean set(String key, Object value) {
        boolean pag = false;
        try {
            redisTemplate.opsForValue().set(key, value);
            pag = true;
        } catch (Exception e) {
            e.printStackTrace();
            pag = false;
        }
        return pag;
    }

    /**
     *
     * 功能描述: 写入redis并设置失效时间
     *
     * @param key:redis中的key
     * @param value : 需要写入的值对象
     * @param time : 失效时间
     * @return:
     * @auther: 陈杰
     * @date: 2018/10/5 16:30
     */
    public boolean set(String key, Object value, long time) {
        boolean pag = false;
        try {
            if (time > 0) {
                redisTemplate.opsForValue().set(key, value, time, TimeUnit.SECONDS);
            } else {
                set(key, value);
            }
            pag = true;
        } catch (Exception e) {
            e.printStackTrace();
            pag = false;
        }
        return pag;
    }

    /**
     *
     * 功能描述:一次增加的数
     *
     * @param key:redis中的key
     * @param delta:增加的数
     * @return:
     * @auther: 陈杰
     * @date: 2018/10/5 16:42
     */
    public long incr(String key, long delta) {
        if (delta < 0) {
            throw new RuntimeException("递增因子必须大于0");
        }
        return redisTemplate.opsForValue().increment(key, delta);
    }

    /**
     *
     * 功能描述: 一次要减的数
     *
     * @param key:redis中的key
     * @param delta:减少的数
     * @return:
     * @auther: 陈杰
     * @date: 2018/10/5 16:44
     */
    public long decr(String key, long delta) {
        if (delta < 0) {
            throw new RuntimeException("递减因子必须大于0");
        }
        return redisTemplate.opsForValue().increment(key, -delta);
    }

    //=================================map========================================
    /**
     *
     * 功能描述: 获取redis中的map的值
     *
     * @param key:redis中的key
     * @param item:map中的key
     * @return:值对象
     * @auther: 陈杰
     * @date: 2018/10/5 16:47
     */
    public Object hget(String key, String item) {
        return redisTemplate.opsForHash().get(key, item);
    }

    /**
     *
     * 功能描述:获取redis中的map对象
     *
     * @param key: redis中的key
     * @return: map对象值
     * @auther: 陈杰
     * @date: 2018/10/5 16:48
     */
    public Map<Object, Object> hmget(String key) {
        return redisTemplate.opsForHash().entries(key);
    }

    /**
     *
     * 功能描述: 将map对象写入redis中
     *
     * @param key : redis中的key
     * @param map : map对象
     * @return:是否写入成功
     * @auther: 陈杰
     * @date: 2018/10/5 16:53
     */
    public boolean hmset(String key, Map<String, Object> map) {
        boolean pag = false;
        try {
            redisTemplate.opsForHash().putAll(key, map);
            pag = true;
        } catch (Exception e) {
            e.printStackTrace();
            pag = false;
        }
        return pag;
    }

    /**
     *
     * 功能描述: 将map对象写入redis中，并设置失效时间
     *
     * @param key:redis中的key
     * @param map : map对象
     * @param time : 失效时间（单位：秒）
     * @return: 是否写入成功
     * @auther: 陈杰
     * @date: 2018/10/5 16:54
     */
    public boolean hmset(String key, Map<String, Object> map, long time) {
        boolean pag = false;
        try {
            redisTemplate.opsForHash().putAll(key, map);
            if (time > 0) {
                expire(key, time);
            }
            pag = true;
        } catch (Exception e) {
            e.printStackTrace();
            pag = false;
        }
        return pag;
    }

    /**
     *
     * 功能描述: 向redis中的hash对象写入值
     *
     * @param key:redis中的key
     * @param item : 值中的key
     * @param value : 值
     * @return: 是否写入成功
     * @auther: 陈杰
     * @date: 2018/10/5 16:57
     */
    public boolean hset(String key, String item, Object value) {
        try {
            redisTemplate.opsForHash().put(key, item, value);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     *
     * 功能描述: 向redis中的hash对象写入值，并设置时间
     *
     * @param key:redis中的key
     * @param item : 值中的key
     * @param value : 值
     * @param time : 失效时间（单位：秒）
     * @return: 是否写入成功
     * @auther: 陈杰
     * @date: 2018/10/5 16:57
     */
    public boolean hset(String key, String item, Object value, long time) {
        try {
            redisTemplate.opsForHash().put(key, item, value);
            if (time > 0) {
                expire(key, time);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     *
     * 功能描述: 删除redis中的map中某一个值
     *
     * @param key : redis中的key
     * @param item : map中的key
     * @return:
     * @auther: 陈杰
     * @date: 2018/10/5 17:00
     */
    public void hdel(String key, Object... item) {
        redisTemplate.opsForHash().delete(key, item);
    }

    /**
     *
     * 功能描述: 判断redis中是否包含该map
     *
     * @param key:redis中的key
     * @param item : map中的key
     * @return:
     * @auther: 陈杰
     * @date: 2018/10/5 17:01
     */
    public boolean hHasKey(String key, String item) {
        return redisTemplate.opsForHash().hasKey(key, item);
    }

    /**
     *
     * 功能描述: 向redis中的map增加数
     *
     * @param key:redis中的key
     * @param item : map 中的key
     * @param by : 需要增加的数
     * @return:
     * @auther: 陈杰
     * @date: 2018/10/5 17:02
     */
    public double hincr(String key, String item, double by) {
        return redisTemplate.opsForHash().increment(key, item, by);
    }

    /**
     *
     * 功能描述: 向redis中的map减少数
     *
     * @param key:redis中的key
     * @param item : map 中的key
     * @param by : 需要减少的数
     * @return:
     * @auther: 陈杰
     * @date: 2018/10/5 17:02
     */
    public double hdecr(String key, String item, double by) {
        return redisTemplate.opsForHash().increment(key, item, -by);
    }
    //=================================map========================================

    //=================================set========================================

    /**
     *
     * 功能描述: 取redis中的所有set的值
     *
     * @param key : redis中的key
     * @return:
     * @auther: 陈杰
     * @date: 2018/10/5 17:11
     */
    public Set<Object> sGet(String key) {
        try {
            return redisTemplate.opsForSet().members(key);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     *
     * 功能描述: 根据value，从一个set中查询，是否存在
     *
     * @param key : redis中的key
     * @param value : 值
     * @return:是否存在
     * @auther: 陈杰
     * @date: 2018/10/5 17:12
     */
    public boolean sHasKey(String key, Object value) {
        try {
            return redisTemplate.opsForSet().isMember(key, value);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     *
     * 功能描述: 将所有值放入redis中的set对象
     *
     * @param key : redis中的key
     * @param values : 一组值
     * @return:
     * @auther: 陈杰
     * @date: 2018/10/5 17:13
     */
    public long sSet(String key, Object... values) {
        try {
            return redisTemplate.opsForSet().add(key, values);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    /**
     *
     * 功能描述: 将所有值放入redis中的set对象，并设置失效时间
     *
     * @param key : redis中的key
     * @param values : 一组值
     * @param time : 失效时间
     * @return:
     * @auther: 陈杰
     * @date: 2018/10/5 17:13
     */
    public long sSetAndTime(String key, long time, Object... values) {
        try {
            Long count = redisTemplate.opsForSet().add(key, values);
            if (time > 0)
                expire(key, time);
            return count;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    /**
     *
     * 功能描述: 获取redis中的set长度
     *
     * @param key:redis中的key
     * @return:set长度
     * @auther: 陈杰
     * @date: 2018/10/5 17:19
     */
    public long sGetSetSize(String key) {
        try {
            return redisTemplate.opsForSet().size(key);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    /**
     *
     * 功能描述: 移除redis中set对象中的某一组值
     *
     * @param key : redis中的key
     * @param values : 一组值
     * @return:
     * @auther: 陈杰
     * @date: 2018/10/5 17:20
     */
    public long setRemove(String key, Object... values) {
        try {
            Long count = redisTemplate.opsForSet().remove(key, values);
            return count;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    //=================================set========================================

    //=================================list========================================

    /**
     *
     * 功能描述: 根据范围获取redis中的list对象值，如果取所有值为 0 到 -1
     *
     * @param key : redis中的key
     * @param start : 开始
     * @param end : 结束
     * @return: list对象
     * @auther: 陈杰
     * @date: 2018/10/5 17:22
     */
    public List<? extends Object> lGet(String key, long start, long end) {
        try {
            return redisTemplate.opsForList().range(key, start, end);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     *
     * 功能描述: 获取redis中的list对象的长度
     *
     * @param key : redis中的key
     * @return: list长度
     * @auther: 陈杰
     * @date: 2018/10/5 17:24
     */
    public long lGetListSize(String key) {
        try {
            return redisTemplate.opsForList().size(key);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    /**
     *
     * 功能描述: 通过索引值，获取redis中的list中的值
     *
     * @param key:redis中的key
     * @param index : 索引值，0为第一个值，1为第二个值，-1倒数第一个值，-2倒数第二个值
     * @return:
     * @auther: 陈杰
     * @date: 2018/10/5 17:26
     */
    public Object lGetIndex(String key, long index) {
        try {
            return redisTemplate.opsForList().index(key, index);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     *
     * 功能描述: 将List写入缓存
     *
     * @param key : redis中的key
     * @param value : list中的值
     * @return:
     * @auther: 陈杰
     * @date: 2018/10/5 17:28
     */
    public boolean lSet(String key, Object value) {
        try {
            redisTemplate.opsForList().rightPush(key, value);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     *
     * 功能描述: 将List写入缓存，并设置失效时间
     *
     * @param key : redis中的key
     * @param value : list中的值
     * @param time : 失效时间（单位：秒）
     * @return:
     * @auther: 陈杰
     * @date: 2018/10/5 17:28
     */
    public boolean lSet(String key, Object value, long time) {
        try {
            redisTemplate.opsForList().rightPush(key, value);
            if (time > 0)
                expire(key, time);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     *
     * 功能描述: 将List写入缓存
     *
     * @param key : redis中的key
     * @param value : list对象
     * @return:
     * @auther: 陈杰
     * @date: 2018/10/5 17:28
     */
    public boolean lSet(String key, List<Object> value) {
        try {
            redisTemplate.opsForList().rightPushAll(key, value);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     *
     * 功能描述: 将List写入缓存，并设置失效时间
     *
     * @param key : redis中的key
     * @param value : list对象
     * @param time : 失效时间（单位：秒）
     * @return:
     * @auther: 陈杰
     * @date: 2018/10/5 17:28
     */
    public boolean lSet(String key, List<Object> value, long time) {
        try {
            redisTemplate.opsForList().rightPushAll(key, value);
            if (time > 0)
                expire(key, time);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     *
     * 功能描述: 根据索引值修改redis中list的值
     *
     * @param key : redis中的key
     * @param index : 索引值
     * @param value : 修改的值
     * @return:
     * @auther: 陈杰
     * @date: 2018/10/5 17:31
     */
    public boolean lUpdateIndex(String key, long index, Object value) {
        try {
            redisTemplate.opsForList().set(key, index, value);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     *
     * 功能描述: 根据索引值，移除redis中的list的值
     *
     * @param key : redis中的key
     * @param count : 需要移除的个数
     * @param value : 需要移除的值
     * @return:
     * @auther: 陈杰
     * @date: 2018/10/5 17:36
     */
    public long lRemove(String key, long count, Object value) {
        try {
            Long remove = redisTemplate.opsForList().remove(key, count, value);
            return remove;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
}

