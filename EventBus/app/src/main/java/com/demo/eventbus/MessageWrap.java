package com.demo.eventbus;

/**
 * @author 29003
 * @description
 * @date 2019/10/12
 */
public class MessageWrap {
    public final String message;

    public static MessageWrap getInstance(String message) {
        return new MessageWrap(message);
    }

    private MessageWrap(String message) {
        this.message = message;
    }
}
