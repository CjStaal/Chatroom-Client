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
public class Controller {
    
    private static GUI gui;
    
    private static Controller instance;
    public Controller(){
    }
    
    public void setGUI(GUI gui){
        this.gui = gui;
    }
    
    public void addToChatWindow(ChatMessage message){
        gui.addToChatWindow(message);
    }
    
    public static synchronized Controller getInstance(){
        if(instance == null){
            instance = new Controller();
        }
        return instance;
    }
}
