package com.zkny.wx.dto;

import lombok.Data;

/**
 * @ClassName: sendTemplateDto
 * @Description: 模板消息发送dto
 * @Author 李景星
 * @Date 2021/8/24
 * @Version 1.0
 */
@Data
public class SendTemplateDto {

    private String touser;  //openid
    private String template_id; //模板id
    private String url; //跳转url
    private String topcolor="#FF0000";
    private Object data;//模板数据

    public SendTemplateDto() {
    }

    public SendTemplateDto(String touser, String template_id, String url, Object data) {
        this.touser = touser;
        this.template_id = template_id;
        this.url = url;
        this.data = data;
    }
}
