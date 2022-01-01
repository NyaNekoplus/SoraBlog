package com.vincent.admin.util;


import freemarker.template.Template;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Vincent Tsai
 * @date 2021/6/17 20:36
 */
@Slf4j
@Component
public class RabbitUtil {
    private static final String EXCHANGE_DIRECT = "exchange.direct";
    private static final String EXCHANGE_FANOUT = "exchange.fanout";

    private static final String ROUTING_KEY_EMAIL = "sora.email";


    @Autowired
    private FreeMarkerConfigurer configurer;
    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void sendEmail(String receiver,String subject,String html){
        Map<String,String> map = new HashMap<>(3);
        map.put("receiver",receiver);
        map.put("html",html);
        map.put("subject",subject);
        rabbitTemplate.convertAndSend(EXCHANGE_FANOUT,"",map);
        //rabbitTemplate.convertAndSend(EXCHANGE_DIRECT,ROUTING_KEY_EMAIL,map);
    }


    public void sendVerificationCode(String receiver,Map<String,String> info){
        Map<String,Object> values = new HashMap<>();
        values.put("email",info.get("email"));
        values.put("verificationCode",info.get("verificationCode"));
        try{
            Template template = configurer.getConfiguration().
                    getTemplate("VerificationCodeTemplate.html");
            String result = FreeMarkerTemplateUtils.processTemplateIntoString(template,values);
            sendEmail(receiver,"Confirm your Email address",result);
        }catch (Exception e){
            log.error(e.getMessage());
        }
    }

    public void sendCommentNotification(String receiver,Map<String,String> info){
        Map<String,Object> values = new HashMap<>();
        values.put("username",info.get("username"));
        values.put("toUsername",info.get("toUsername"));
        values.put("content",info.get("content"));
        values.put("toContent",info.get("toContent"));
        values.put("commentSource",info.get("commentSource"));
        values.put("link",info.get("link"));
        try{
            Template template = configurer.getConfiguration().
                    getTemplate("CommentTemplate.html");
            String result = FreeMarkerTemplateUtils.processTemplateIntoString(template,values);

            sendEmail(receiver,"你的评论有新的回复",result);
        }catch (Exception e){
            log.error(e.getMessage());
        }
    }
}
