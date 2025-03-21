package com.ride.share.aad.storage.entity.chat;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ride.share.aad.storage.entity.User;
import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

import java.util.StringJoiner;


@Entity
@Table(name = "chat_messages")
public class ChatMessage {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(columnDefinition = "VARCHAR(36)")
    String chatMessageId;

    @ManyToOne
    User fromUserId;
    String message;
    Long timeStamp;
    @JsonIgnore
    @ManyToOne
    Chat chat;

    public ChatMessage() {
    }

    public ChatMessage(User fromUserId, String message, long timeStamp) {
        this.fromUserId = fromUserId;
        this.message = message;
        this.timeStamp = timeStamp;
    }

    public User getFromUserId() {
        return fromUserId;
    }

    public void setFromUserId(User fromUserId) {
        this.fromUserId = fromUserId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getChatMessageId() {
        return chatMessageId;
    }

    public void setChatMessageId(String chatMessageId) {
        this.chatMessageId = chatMessageId;
    }

    public Chat getChat() {
        return chat;
    }

    public void setChat(Chat chat) {
        this.chat = chat;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", ChatMessage.class.getSimpleName() + "[", "]").add("fromUserId='" + fromUserId + "'").add("message='" + message + "'").add("timeStamp=" + timeStamp).toString();
    }
}
