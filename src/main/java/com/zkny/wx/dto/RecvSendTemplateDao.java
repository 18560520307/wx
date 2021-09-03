package com.zkny.wx.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName: RecvSendTemplateDao
 * @Description: 接收模板数据
 * @Author 李景星
 * @Date 2021/8/24
 * @Version 1.0
 */
@Data
public class RecvSendTemplateDao implements Serializable {

    private String openIds;//指定openid用，分割
    private String template_id; //模板id
    private String url;//详情跳转连接
    private Object data;//模板数据

}
