package zhou6_work04;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.concurrent.TimeUnit;


//（必做）给前面课程提供的 Student/Klass/School 实现自动配置和 Starter。

@RunWith(SpringJUnit4ClassRunner.class)
public class myRedisServerTest {
    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    public void play() {
        redisTemplate.boundValueOps("yx").set("yx",1, TimeUnit.MINUTES);

        System.out.println(redisTemplate.hasKey("yx"));
    }

}
