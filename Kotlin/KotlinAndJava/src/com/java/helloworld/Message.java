package com.java.helloworld;

import org.jetbrains.annotations.NotNull;

public class Message implements Comparable {
    private String body;
    private long priority;
    public String getBody() {
        return body;
    }
    public Message() {}
    public Message(String body, long priority) {
        this.body = body;
        this.priority = priority;
    }
    @Override
    public int compareTo(@NotNull Object o) {
        Message message = (Message) o;
        return message.priority > this.priority? 1 : -1;
    }
}
