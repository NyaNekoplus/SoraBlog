package com.vincent.admin;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.vincent.admin.entity.Article;
import com.vincent.admin.entity.Comment;
import com.vincent.admin.interceptor.UserAgentInterceptor;
import com.vincent.admin.service.ArticleService;
import com.vincent.admin.service.CommentService;
import com.vincent.admin.service.RecordService;
import com.vincent.admin.util.IpUtil;
import com.vincent.admin.util.JsonUtil;
import com.vincent.admin.util.MailUtil;
import com.vincent.admin.vo.UserVO;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
@SpringBootTest
class AdminApplicationTests {

    @Autowired
    RecordService recordService;
    @Autowired
    ArticleService articleService;
    @Autowired
    CommentService commentService;
    @Autowired
    private RestTemplate restTemplate;


    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private MailUtil mailUtil;

    @Test
    void testSearch(){
        String a = articleService.getRecommend();
        log.info(a);
    }
    @Test
    void testSelect(){
        QueryWrapper<Comment> queryWrapper = new QueryWrapper<>();
        queryWrapper.select(Comment.class,i -> {
            System.out.println(i.getColumn());
            System.out.println(i.getCondition());
            System.out.println(i.getProperty());
            return i.getProperty().equals("content");
        });
        List<Comment> list = commentService.list(queryWrapper);
        list.forEach(c -> {
            log.info(c.toString());
        });
    }
    @Test
    void testGetQQ(){
        String result = "";
        BufferedReader in = null;
        try{
            String urlNameString = "https://r.qzone.qq.com/fcg-bin/cgi_get_portrait.fcg?uins=1574315089";
            URL realUrl = new URL(urlNameString);
// 打开和URL之间的连接
            URLConnection connection = realUrl.openConnection();
// 设置通用的请求属性
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
// 建立实际的连接
            connection.connect();
// 定义 BufferedReader输入流来读取URL的响应
            InputStreamReader reader = new InputStreamReader(
                    connection.getInputStream(),"gbk");
            in = new BufferedReader(reader);
            String line;
            while((line = in.readLine()) != null) {
                result += line;
            }
        } catch(Exception e) {
            System.out.println("发送GET请求出现异常！"+ e);
            e.printStackTrace();
        }
// 使用finally块来关闭输入流
        finally{
            try{
                if(in != null) {
                    in.close();
                }
            } catch(Exception e2) {
                e2.printStackTrace();
            }
        }
        log.info(result);
        /*
        Map<String, Object> map = new HashMap<>();
        map.put("keyword","1359643068");
        //restTemplate.setInterceptors(Collections.singletonList(new UserAgentInterceptor()));
        String json = restTemplate.getForObject("https://r.qzone.qq.com/fcg-bin/cgi_get_portrait.fcg?uins=1574315089",String.class);
        System.out.println(json);
        //String result=new String(json.getBytes("GBK"), StandardCharsets.UTF_8);
        log.info(result);
        */

    }
    @Test
    void testEmail(){
        mailUtil.sendEmail("2142585130@qq.com","test","Test Mail Template");
        //mailUtil.sendAuthCode("2142585130@qq.com","127.0.0.1");
        /*
        mailUtil.sendEmail("2142585130@qq.com","<div id=\"contentDiv\"\n" +
                "     style=\"position:relative;font-size:14px;height:auto;padding:15px 15px 10px 15px;z-index:1;zoom:1;line-height:1.7;\"\n" +
                "     class=\"body\">\n" +
                "    <div id=\"qm_con_body\">\n" +
                "        <div id=\"mailContentContainer\" class=\"qmbox qm_con_body_content qqmail_webmail_only\" style=\"\">\n" +
                "            <div style=\"\n" +
                "                    background: white;\n" +
                "                    width: 95%;\n" +
                "                    max-width: 800px;\n" +
                "                    margin: auto auto;\n" +
                "                    border-radius: 5px;\n" +
                "                    border:orange 1px solid;\n" +
                "                    overflow: hidden;\n" +
                "                    -webkit-box-shadow: 0px 0px 20px 0px rgba(0, 0, 0, 0.12);\n" +
                "                    box-shadow: 0px 0px 20px 0px rgba(0, 0, 0, 0.18);\"\n" +
                "            >\n" +
                "                <header style=\"overflow: hidden;\">\n" +
                "                    <img style=\"width:100%;z-index: 666;\" src=\"file:///C:/Users/Kasugano Sora/Desktop/head.jpg\">\n" +
                "                </header>\n" +
                "                <div style=\"padding: 5px 20px;\">\n" +
                "                    <p style=\"\n" +
                "                            position: relative;\n" +
                "                            color: white;\n" +
                "                            float: left;\n" +
                "                            z-index: 999;\n" +
                "                            background: orange;\n" +
                "                            padding: 5px 30px;\n" +
                "                            margin: -25px auto 0 ;\n" +
                "                            box-shadow: 5px 5px 5px rgba(0, 0, 0, 0.30)\"\n" +
                "                    >\n" +
                "                        Dear 悠\n" +
                "                    </p>\n" +
                "                    <br>\n" +
                "                    <h3>您有一条来自\n" +
                "                        <a style=\"text-decoration: none;color: orange\" target=\"_blank\" href=\"https://sora.vin/\" rel=\"noopener\">\n" +
                "                            樱花庄</a>的回复\n" +
                "                    </h3>\n" +
                "                    <br>\n" +
                "                    <p style=\"font-size: 14px;\">您在文章《GraphQL 实现递归查询》上发表的评论：</p>\n" +
                "                    <p style=\"border-bottom:#ddd 1px solid;border-left:#ddd 1px solid;padding-bottom:20px;background-color:#eee;margin:15px 0px;padding-left:20px;padding-right:20px;border-top:#ddd 1px solid;border-right:#ddd 1px solid;padding-top:20px\">\n" +
                "                        顶顶</p>\n" +
                "                    <p style=\"font-size: 14px;\">悠 给您的回复如下：</p>\n" +
                "                    <p style=\"border-bottom:#ddd 1px solid;border-left:#ddd 1px solid;padding-bottom:20px;background-color:#eee;margin:15px 0px;padding-left:20px;padding-right:20px;border-top:#ddd 1px solid;border-right:#ddd 1px solid;padding-top:20px\">\n" +
                "                        ~~~~</p>\n" +
                "                    <p style=\"font-size: 14px;\">\n" +
                "                        <a style=\"text-decoration: none;color: orange\" target=\"_blank\"\n" +
                "                                                   href=\"https://sora.vin/\" rel=\"noopener\">\n" +
                "                            さくら荘の白猫</a>&nbsp;双手呈上~\n" +
                "                    </p>\n" +
                "\n" +
                "                    <div style=\"text-align: center;\">\n" +
                "                        <img src=\"file:///C:/Users/Kasugano Sora/Desktop/hr.png\"\n" +
                "                             style=\"width:100%;\n" +
                "                             margin:5px auto 5px auto;\n" +
                "                             display: block;\">\n" +
                "                        <a style=\"\n" +
                "                                text-transform: uppercase;\n" +
                "                                text-decoration: none;\n" +
                "                                font-size: 14px;\n" +
                "                                border: 2px solid #6c7575;\n" +
                "                                color: #2f3333;\n" +
                "                                padding: 10px;\n" +
                "                                display: inline-block;\n" +
                "                                margin: 10px auto 0; \" target=\"_blank\"\n" +
                "                                href=\"https://sora.vin/2019/12/31/graphql-recursion/comment-page-3/#comment-8798\"\n" +
                "                                rel=\"noopener\">点击查看回复的完整內容\n" +
                "                        </a>\n" +
                "                    </div>\n" +
                "                    <img src=\"https://view.moezx.cc/images/2018/06/08/qrcode-02.png\"\n" +
                "                         style=\"margin:5px auto 5px auto; width:  300px; display: block;\">\n" +
                "                    <p style=\"font-size: 12px;text-align: center;color: #999;\">本邮件为系统自动发出，请勿直接回复<br>\n" +
                "                        © 2021 樱花庄的白猫</p>\n" +
                "                </div>\n" +
                "            </div>\n" +
                "\n" +
                "            <style type=\"text/css\">\n" +
                "                .qmbox style, .qmbox script, .qmbox head, .qmbox link, .qmbox meta {\n" +
                "                    display: none !important;\n" +
                "                }\n" +
                "            </style>\n" +
                "        </div>\n" +
                "    </div><!-- -->\n" +
                "    <style>\n" +
                "        #mailContentContainer .txt {\n" +
                "            height: auto;\n" +
                "        }\n" +
                "    </style>\n" +
                "</div>");*/
    }
    @Test
    void testReg(){
        String t = "portraitCallBack({\"1574315089\":[\"http://qlogo2.store.qq.com/qzone/1574315089/1574315089/100\",725,-1,0,0,0,\"今天的你也安睡着\",0]})";
        Pattern p = Pattern.compile("portraitCallBack\\(\\{\"\\d+\":(.*)}\\)");
        Matcher m = p.matcher(t);
        if (m.find()){
            log.info(m.group(1));
        }
        //Map<String,Object> map = JsonUtil.jsonToMap(m.group(1));
        //log.info(map.toString());
        List<String> list = JsonUtil.jsonToList(m.group(1),String.class);
        log.info(list.toString());
    }
    @Test
    public void testObj() throws Exception{
        String picGoUrl = "http://127.0.0.1:36677/upload";
        String data = "{list: ['C:\\Users\\Kasugano Sora\\Desktop\\Reimu\\reimon\\34631407_p0.jpg']}";
        Map<String,Object> map = new HashMap<>();
        List<String> imgPath = new ArrayList<>();
        imgPath.add("C:\\Users\\Kasugano Sora\\Desktop\\Reimu\\reimon\\34631407_p0.jpg");
        map.put("list",imgPath);
        HttpEntity<Map<String,Object>> request = new HttpEntity<>(map);
        //restTemplate.post
        Map json = restTemplate.postForObject(picGoUrl,request, Map.class);
        log.info("success:"+ json.get("success").getClass());
        log.info("result:"+ json.get("result").getClass());
        System.out.println("上传至GitHub："+json);
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
        System.out.println("+++++:  "+recordService.getVisitorCountOfToday());
    }

}
