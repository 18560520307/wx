package com.zkny.wx.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName: Text
 * @Description: 文本
 * @Author 李景星
 * @Date 2021/8/21
 * @Version 1.0
 */
@Data
public class Text implements Serializable {
    private String content;

    public Text() {
    }

    public Text(String content) {
        this.content = content;
    }
}
