package com.atguigu.jedis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * 测试jedis
 */
public class JedisDemo {
    public static void main(String[] args) {
        //Jedis jedis = getJedis();

        Jedis jedis = getJedisFromPool();

        jedis.set("user:name", "尚硅谷");

        String result = jedis.ping();
        System.out.println(result);


        String name = jedis.get("user:name");
        System.out.println(name);

        jedis.close();
    }

    //注意一个事情
    /**
     * 测试字符串类型的方法
     */
    public static void testString(){
//Jedis jedis = getJedis();

        Jedis jedis = getJedisFromPool();

        jedis.set("user:name", "尚硅谷");

        String result = jedis.ping();
        System.out.println(result);


        String name = jedis.get("user:name");
        System.out.println(name);

        jedis.close();
    }
    public static void testList(){

    }
    public static void testSet(){

    }
    public static void testZset(){

    }

    public static void testHash(){

    }




    /**
     * 使用连接池技术，不用来回进行开链接关连接
     * @return
     */

    public static JedisPool jedisPool = null;

    public static Jedis getJedisFromPool(){
        if (jedisPool == null) {
            String host = "centos";
            int port = 6379;

            JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
            jedisPoolConfig.setMaxTotal(10);
            jedisPoolConfig.setMaxIdle(5);
            jedisPoolConfig.setMinIdle(5);
            jedisPoolConfig.setBlockWhenExhausted(true);
            jedisPoolConfig.setMaxWaitMillis(2000);
            jedisPoolConfig.setTestOnBorrow(true);
            jedisPool = new JedisPool(jedisPoolConfig,host,port);
        }

        //从池中过去jedis对象
        Jedis jedis = jedisPool.getResource();
        return jedis;
    }

    public static Jedis getJedis(){
        String host = "centos";
        int port = 6379;
        Jedis jedis = new Jedis(host,port);

        return jedis;

    }
}
