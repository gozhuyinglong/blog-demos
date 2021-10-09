package io.github.gozhuyinglong.config;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.util.IOUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * @author 码农StayUp
 * @date 2021/10/9 0010
 */
@Configuration
public class RedisConfig {

    /**
     * redisTemplate 配置
     *
     * @return RedisTemplate 实例
     */
    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(fastJsonRedisSerializer());
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashValueSerializer(fastJsonRedisSerializer());
        redisTemplate.afterPropertiesSet();
        return redisTemplate;
    }

    /**
     * 自定义 redis 的序列化器：使用FastJson
     *
     * @return redis 序列化器
     */
    @Bean
    public RedisSerializer<Object> fastJsonRedisSerializer() {
        return new RedisSerializer<Object>() {

            // 序列化
            @Override
            public byte[] serialize(Object o) throws SerializationException {
                if (o == null) {
                    return new byte[0];
                }
                if (o instanceof String) {
                    return ((String) o).getBytes(IOUtils.UTF8);
                }
                return JSON.toJSONString(o).getBytes(IOUtils.UTF8);
            }

            // 反序列化
            @Override
            public Object deserialize(byte[] bytes) throws SerializationException {
                if (bytes == null || bytes.length == 0) {
                    return null;
                }
                return new String(bytes, IOUtils.UTF8);
            }
        };
    }

}
