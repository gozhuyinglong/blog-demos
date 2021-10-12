package io.github.gozhuyinglong;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import io.github.gozhuyinglong.pojo.Person;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * @author 码农StayUp
 * @date 2021/10/9 0009
 */
@SpringBootTest
public class RedisTest {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Test
    public void testSet() {
        Person person = new Person();
        person.setId(1L);
        person.setName("码农StayUp");
        person.setAge(18);
        redisTemplate.opsForValue().set("person", person);
    }

    @Test
    public void testGet() {
        Object o = redisTemplate.opsForValue().get("person");
        System.out.println(o);
        // 转换为 Person 对象
        Person person = JSON.parseObject((String) o, Person.class);
        assert person != null;
        System.out.println(person.toString());
    }
}
