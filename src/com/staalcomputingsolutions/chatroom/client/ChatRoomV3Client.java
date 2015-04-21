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
public class ChatRoomV3Client {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        GUI gui = new GUI();
        gui.setVisible(true);
        Controller controller = Controller.getInstance();
        controller.setGUI(gui);
    }
    
}
