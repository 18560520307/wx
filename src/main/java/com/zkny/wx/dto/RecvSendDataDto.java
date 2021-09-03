package com.zkny.wx.dto;

import lombok.Data;

/**
 * @ClassName: RecvSendDataDto
 * @Description: 对外提供接口需要发送的数据信息
 * @Author 李景星
 * @Date 2021/8/21
 * @Version 1.0
 */
@Data
public class RecvSendDataDto {

    private String content;
    private String openIds;//指定openid用，分割
}
