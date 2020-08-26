package com.company;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class HandlingClass extends Thread{
    Socket client;
    int client_number;

    public HandlingClass(Socket client, int client_number) {
        this.client = client;
        this.client_number = client_number;
    }

    public void run() {
        DataInputStream inStream = null;
        try {
            inStream = new DataInputStream(client.getInputStream());
            DataOutputStream outStream = new DataOutputStream(client.getOutputStream());
            String clientMessage = "", serverMessage = "";
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            while (!clientMessage.equalsIgnoreCase("bye")) {
                clientMessage = inStream.readUTF();
                System.out.println("Client " + client_number + " said => " + clientMessage);
                System.out.print("Reply to " + client_number + ":");
                clientMessage = br.readLine();
                outStream.writeUTF(clientMessage.toUpperCase());

                outStream.flush();
            }
            inStream.close();
            outStream.close();
            client.close();
        } catch (IOException ex) {
            Logger.getLogger(HandlingClass.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                inStream.close();
            } catch (IOException ex) {
                Logger.getLogger(HandlingClass.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
