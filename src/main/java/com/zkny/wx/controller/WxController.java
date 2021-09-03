package com.zkny.wx.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.zkny.wx.dto.*;
import com.zkny.wx.feign.WxFeign;
import com.zkny.wx.utils.RedisUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName: WxController
 * @Description: 微信处理controller
 * @Author 李景星
 * @Date 2021/8/21
 * @Version 1.0
 */
@RestController
@RequestMapping("/api/wx")
@Slf4j
public class WxController {


    @Autowired
    private WxFeign wxFeign;

    @Value("${wx.appId}")
    private String appId;

    @Value("${wx.grantType}")
    private String grantType;

    @Value("${wx.secret}")
    private String secret;

    @Autowired
    private RedisUtils redisUtils;

    @GetMapping("/getToken")
    public void getToken(){
        String token = wxFeign.getToken(grantType, appId, secret);
        JSONObject jsonObject = JSON.parseObject(token);
        String access_token = jsonObject.getString("access_token");
        redisUtils.set("wx:token:access_token", access_token);
        log.info("当前token = " + access_token);
    }

    /**
     * 发送文本数据接口
     * @param recvSendDataDto
     */
    @PostMapping("/sendText")
    public void sendText(@RequestBody RecvSendDataDto recvSendDataDto){

        String token = String.valueOf(redisUtils.getValue("wx:token:access_token"));
        //提取openid
        String openIds = recvSendDataDto.getOpenIds();
        String[] strings = openIds.split(",");
        for (String openId : strings) {
            //组装待发送消息
            SendTextDto sendTextDto = new SendTextDto(openId, "text", new Text(recvSendDataDto.getContent()));
            String s = wxFeign.sendText(token, sendTextDto);
            log.info("发送数据为->" + sendTextDto.toString() + "》》》》》》本次数据发送结果->" + s);
        }
    }


    /**
     * 发送模板数据
     * @param recvSendTemplateDao
     */
    @PostMapping("/sendTemplate")
    public void sendTemplate (@RequestBody RecvSendTemplateDao recvSendTemplateDao){
        String token = String.valueOf(redisUtils.getValue("wx:token:access_token"));
        //提取模板数据
        Object data = JSONObject.toJSON(recvSendTemplateDao.getData());
        //提取openid
        String openIds = recvSendTemplateDao.getOpenIds();
        String[] strings = openIds.split(",");
        for (String openId : strings) {

            //组装待发送消息
            SendTemplateDto sendTemplateDto = new SendTemplateDto(openId, recvSendTemplateDao.getTemplate_id(), recvSendTemplateDao.getUrl(), data);
            String s = wxFeign.sendTemplate(token, sendTemplateDto);
            log.info("发送数据为->" + sendTemplateDto.toString() + "》》》》》》本次数据发送结果->" + s);
        }
    }

}
