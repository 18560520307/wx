package com.zkny.wx.crontab;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.zkny.wx.feign.WxFeign;
import com.zkny.wx.utils.RedisUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @ClassName: WxContab
 * @Description: 微信定时任务
 * @Author 李景星
 * @Date 2021/8/21
 * @Version 1.0
 */
@Component
@Slf4j
public class WxContab {

    @Autowired
    private WxFeign wxFeign;

    @Autowired
    private RedisUtils redisUtils;

    @Value("${wx.appId}")
    private String appId;

    @Value("${wx.grantType}")
    private String grantType;

    @Value("${wx.secret}")
    private String secret;

    @Scheduled(cron = "0 0 0/1 * * ?")
    public void getToken(){
        String token = wxFeign.getToken(grantType, appId, secret);
        JSONObject jsonObject = JSON.parseObject(token);
        String access_token = jsonObject.getString("access_token");
        redisUtils.set("wx:token:access_token", access_token);
        log.info("当前token = " + access_token);
    }
}
