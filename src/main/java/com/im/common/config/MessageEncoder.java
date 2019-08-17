package com.im.common.config;

import com.alibaba.fastjson.JSON;
import com.im.entity.vo.MessageVO;

import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;

/**
 * @Description 消息编码器
 * @data 2019/7/29 14:10
 * @Author: LiuBin
 * @Modified By:
 */
public class MessageEncoder implements Encoder.Text<MessageVO> {
    @Override
    public String encode(MessageVO messageVO) throws EncodeException {
        try {
            return JSON.toJSONString(messageVO);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return "";
        }
    }

    @Override
    public void init(EndpointConfig endpointConfig) {

    }

    @Override
    public void destroy() {

    }
}
