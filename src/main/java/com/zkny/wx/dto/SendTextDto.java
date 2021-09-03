package com.zkny.wx.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName: SendTextDto
 * @Description: 微信公众号推送文本消息
 * @Author 李景星
 * @Date 2021/8/21
 * @Version 1.0
 */
@Data
public class SendTextDto implements Serializable {
    private String touser;
    private String msgtype;
    private Text text;

    public SendTextDto() {
    }

    public SendTextDto(String touser, String msgtype, Text text) {
        this.touser = touser;
        this.msgtype = msgtype;
        this.text = text;
    }

    @Override
    public String toString() {
        return "SendTextDto{" +
                "touser='" + touser + '\'' +
                ", msgtype='" + msgtype + '\'' +
                ", text=" + text +
                '}';
    }
}
