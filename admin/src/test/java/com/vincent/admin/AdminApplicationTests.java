package com.vincent.admin;

import com.vincent.admin.entity.Comment;
import com.vincent.admin.service.CommentService;
import com.vincent.admin.util.JsonUtil;
import com.vincent.admin.vo.UserVO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@SpringBootTest
class AdminApplicationTests {

    @Autowired
    CommentService commentService;


    @Autowired
    private RedisTemplate redisTemplate;
    @Test
    public void testObj() throws Exception{
        UserVO userVo = new UserVO();
        userVo.setUsername("测试dfas");
        ValueOperations<String,Object> operations = redisTemplate.opsForValue();
        /*
        redisService.expireKey("name",20, TimeUnit.SECONDS);
        String key = RedisKeyUtil.getKey(UserVo.Table,"name",userVo.getName());
        UserVo vo = (UserVo) operations.get(key);
        System.out.println(vo);

         */
    }
    @Test
    void testDate(){
        Date date =new Date();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        System.out.println("percent time： "+df.format(new Date()));// new Date()为获取当前系统时间
        System.out.println("Date: "+date.toString());
        System.out.println(date);
    }



    @Test
    void testFindComment(){
        List<Comment> comments = commentService.findAllComment();
        System.out.println(comments);
    }

}
