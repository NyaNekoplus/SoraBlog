package com.vincent.admin.util;

import com.vincent.admin.entity.Comment;
import freemarker.template.Template;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Vincent Tsai
 * @date 2021/5/9 15:49
 */
@Slf4j
@Component
public class MailUtil {
    @Autowired
    private JavaMailSender mailSender;
    @Autowired
    private FreeMarkerConfigurer configurer;


    public void sendAuthCode(String receiver, String ip){
        sendEmail(receiver,"test","code");
    }
    public void sendReplyCommentNotification(String receiver, Map<String,String> info){
        Map<String,Object> values = new HashMap<>();
        values.put("blogTitle","test-title");
        values.put("comment","test-comment");
        values.put("reply","comment.getContent()");
        try{
            Template template = configurer.getConfiguration().
                    getTemplate("MailTemplate.html");
            String result = FreeMarkerTemplateUtils.processTemplateIntoString(template,values);

            sendEmail(receiver,"test",result);
        }catch (Exception e){
            log.error(e.getMessage());
        }
    }
    /**
     * 发送邮件
     *
     * @param receiver
     * @param text
     */
    public void sendEmail(String receiver, String subject,String text) {
        try{
            //创建一个复杂的消息邮件
            MimeMessage mimeMessage = mailSender.createMimeMessage();

            //multipart:true
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

            helper.setSubject(subject);

            helper.setText(text, true);
            //邮件接收人
            helper.setTo(receiver);
            //邮件发送者
            helper.setFrom(new InternetAddress("no-reply@sora.vin","Vincent Tsai","UTF-8"));

            mailSender.send(mimeMessage);

            log.info("邮件发送成功");
            /*添加邮件附件
            String path = ""; //文件路径
            String fileName = ""; //文件名
            helper.addAttachment(fileName, new File(path));
            */
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }
}
