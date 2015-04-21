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
public class MessageManager {

    private Controller controller;
    private ServerConnection sc;
    private MessageSorter ms;

    public MessageManager(ServerConnection sc) {
        controller = Controller.getInstance();
        this.sc = sc;
        this.ms = new MessageSorter(sc);
    }

    public void manageMessage(String message) {
        String from = "";
        if (message.contains("CHAT")) {
            if (message.contains("PUBLIC")) {
                message = message.substring(message.indexOf("{") + 1, message.indexOf("}"));
                message = message.split(":")[1];
                String[] info = message.split(",");
                message = info[0].split("=")[1];
                from = info[1].split("=")[1];
                ms.sort(new ChatMessage(from).setMessage(message));
            } else if(message.contains("PRIVATE")){
                
            }
        }
    }
}
