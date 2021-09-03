package com.zkny.wx.feign;

import com.zkny.wx.dto.SendTemplateDto;
import com.zkny.wx.dto.SendTextDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @InterfaceName: WxFeign
 * @Description: 微信远程调用接口
 * @Author 李景星
 * @Date 2021/8/21
 * @Version 1.0
 */
@FeignClient(name = "WxFeign", url = "${wx.url}")
public interface WxFeign {

    /**
     * 微信公众号获取token
     * @param grant_type
     * @param appid
     * @param secret
     * @return
     */
    @GetMapping("/cgi-bin/token")
    public String getToken(@RequestParam("grant_type") String grant_type, @RequestParam("appid") String appid, @RequestParam("secret") String secret);

    /**
     * 微信公众号推送文本消息
     * @param token
     * @param sendTextDto
     * @return
     */
    @PostMapping("/cgi-bin/message/custom/send")
    public String sendText(@RequestParam("access_token") String token, @RequestBody SendTextDto sendTextDto);


    /**
     * 微信公众号推送模板消息
     * @param token
     * @param sendTemplateDto
     * @return
     */
    @PostMapping("/cgi-bin/message/template/send")
    public String sendTemplate(@RequestParam("access_token") String token, @RequestBody SendTemplateDto sendTemplateDto);
}
