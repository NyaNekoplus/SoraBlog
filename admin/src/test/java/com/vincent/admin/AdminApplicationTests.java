package com.vincent.admin;

import com.vincent.admin.entity.Comment;
import com.vincent.admin.service.CommentService;
import com.vincent.admin.util.JsonUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@SpringBootTest
class AdminApplicationTests {

    @Autowired
    CommentService commentService;

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
