package com.im.common.config;

import com.im.entity.vo.MessageVO;

import javax.websocket.DecodeException;
import javax.websocket.Decoder;
import javax.websocket.EndpointConfig;

/**
 * @Description 消息解码器
 * @data 2019/7/29 14:15
 * @Author: LiuBin
 * @Modified By:
 */
public class MessageDecoder implements Decoder.Text<MessageVO> {
    @Override
    public MessageVO decode(String s) throws DecodeException {
        return new MessageVO();
    }

    @Override
    public boolean willDecode(String s) {
        return false;
    }

    @Override
    public void init(EndpointConfig endpointConfig) {

    }

    @Override
    public void destroy() {

    }
}
