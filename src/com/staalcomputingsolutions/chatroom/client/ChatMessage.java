/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.staalcomputingsolutions.chatroom.client;

/**
 *
 * @author Charles
 */
public class ChatMessage {
    
    private final String fromUUID;
    private String message;
    
    public ChatMessage(String fromUUID){
        this.fromUUID = fromUUID;
    }
    
    public ChatMessage setMessage(String message){
        this.message = message;
        return this;
    }
    
    public String getMessage(){
        return this.message;
    }
    
    public String getFromUUID(){
        return this.fromUUID;
    }
}
