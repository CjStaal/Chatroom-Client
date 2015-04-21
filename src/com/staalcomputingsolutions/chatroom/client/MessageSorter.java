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
public class MessageSorter {

    private Controller controller;
    private ServerConnection sc;

    public MessageSorter(ServerConnection sc) {
        controller = Controller.getInstance();
        this.sc = sc;
    }

    public void sort(ChatMessage message) {
        controller.addToChatWindow(message);
    }
}
