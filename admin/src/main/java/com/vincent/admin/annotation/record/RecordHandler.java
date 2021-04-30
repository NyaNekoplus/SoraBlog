package com.vincent.admin.annotation.record;

import com.vincent.admin.entity.Record;
import com.vincent.admin.holder.AbstractRequestAwareRunnable;
import com.vincent.admin.holder.RequestHolder;
import com.vincent.admin.util.IpUtil;
import com.vincent.admin.util.JsonUtil;
import com.vincent.admin.util.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author Vincent Tsai
 * @date 2021/4/17 12:12
 */
@Slf4j
@Component
public class RecordHandler extends AbstractRequestAwareRunnable {
    @Autowired
    private RedisUtil redisUtil;

    private String operation;
    private String addOn;

    public void setRecordHandler(String operation, String addOn){
        this.addOn = addOn;
        this.operation = operation;
    }

    @Override
    protected void onRun() {
        HttpServletRequest request = RequestHolder.getRequest();
        Map<String, String> map = IpUtil.getOsAndBrowserInfo(request);
        String os = map.get("OS");
        String browser = map.get("BROWSER");
        Record record = new Record();
        String ip = IpUtil.getIpAddr(request);
        log.info("onRun ip: " +ip);
        record.setIp(ip);

        String address2 = IpUtil.getCityInfo(ip);
        log.info("address2: "+ address2);

        //从Redis中获取IP来源
        String jsonResult = redisUtil.get("IP_SOURCE" + ":" + ip);
        if (StringUtils.isEmpty(jsonResult)) {

            String address = IpUtil.getCityInfo(ip);
            log.info("address: "+ address);
            if (!StringUtils.isEmpty(address)) {
                record.setIpSource(address);
                redisUtil.setExpire("IP_SOURCE" + ":" + ip, address, 24, TimeUnit.HOURS);
            }
        }
        else {
            record.setIpSource(jsonResult);
        }

        String token = request.getHeader("Authorization");
        log.info("token: "+token);
        if (token != null) {
            String user = redisUtil.get("LOGIN_TOKEN"+':'+token);
            if (StringUtils.isEmpty(user)){
                record.setUserUid(null);
            }else {
                Map<String,Object> info = JsonUtil.jsonToMap(user);
                log.info("用户信息： "+info.get("uid"));
                record.setUserUid(Long.parseLong(info.get("uid").toString()));
            }
        }
        record.setOs(os);
        record.setBrowser(browser);
        record.setOperation(operation);
        record.setAddOn(addOn);
        record.insert();
    }

}
