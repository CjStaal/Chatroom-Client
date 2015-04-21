/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.staalcomputingsolutions.chatroom.client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Charles
 */
public class ServerConnection {

    private String serverIP;
    private int serverPort;
    private Socket serverConnect;
    private boolean connected;
    private InputStream inputStream;
    private OutputStream outputStream;
    private DataInputStream dataInputStream;
    private DataOutputStream dataOutputStream;
    private Thread listeningThread;
    private MessageManager mm;
    private static ServerConnection instance = null;

    private ServerConnection() {
    }

    public static synchronized ServerConnection getInstance() {
        if (instance == null) {
            instance = new ServerConnection();
        }
        return instance;
    }

    public void sendPublicChatMessage(String message) {
        sendToServer("CHAT{PUBLIC:MSG=" + message + "}");
    }

    public void sendPrivateChatMessage(String message, String recipiantPublicUUID) {
        sendToServer("CHAT{PRIVATE:MSG=" + message + ",TO=" + recipiantPublicUUID + "}");
    }

    public void sendLoginData(String username, String password) {
        sendToServer("LOGIN:USER=" + username + ",PASSWORD=" + password);
    }

    private void sendToServer(String message) {
        try {
            dataOutputStream.writeUTF(message);
        } catch (IOException ex) {
            Logger.getLogger(ServerConnection.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void logout() {
        try {
            serverIP = "";
            serverPort = -1;
            dataInputStream.close();
            dataOutputStream.close();
            inputStream.close();
            outputStream.close();
            serverConnect.close();
            listeningThread.stop();
            inputStream = null;
            outputStream = null;
            dataInputStream = null;
            dataOutputStream = null;
            serverConnect = null;
            connected = false;
            mm.manageMessage("CHAT{PUBLIC:MSG=Logged out.,FROM=SELF}");
        } catch (IOException ex) {
            Logger.getLogger(ServerConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public class Listener implements Runnable {

        @Override
        public void run() {
            while (connected) {
                try {
                    String message = dataInputStream.readUTF();
                    mm.manageMessage(message);
                } catch (IOException ex) {
                    Logger.getLogger(ServerConnection.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    public void connectToServer(String serverIP, int serverPort) {
        if (!connected) {
            try {
                this.serverIP = serverIP;
                this.serverPort = serverPort;
                serverConnect = new Socket(serverIP, serverPort);
                connected = true;
                inputStream = serverConnect.getInputStream();
                outputStream = serverConnect.getOutputStream();
                dataInputStream = new DataInputStream(inputStream);
                dataOutputStream = new DataOutputStream(outputStream);
                listeningThread = new Thread(new Listener());
                listeningThread.start();
                mm = new MessageManager(this);
            } catch (IOException ex) {
                Logger.getLogger(ServerConnection.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
