package edu.eci.ieti.envirify.controllers.dtos;

import java.io.Serializable;

public class MessageDTO implements Serializable {

    private String message;
    private String sender;
    private String receiver;
    private String channelId;

    public MessageDTO() {
    }

    public MessageDTO(String message, String sender, String receiver, String channelId) {
        this.message = message;
        this.sender = sender;
        this.receiver = receiver;
        this.channelId = channelId;
    }


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }
}
