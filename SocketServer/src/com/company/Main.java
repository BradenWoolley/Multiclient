package com.company;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {

    public static void main(String[] args) throws IOException{

        ServerSocket server= new ServerSocket(8888);
        System.out.println("Server is running..........");

        int counter=0;

        while(true)
        {
            counter++;
            Socket serverClient= server.accept();
            System.out.println("Client " + counter +" is connected....." );
            HandlingClass hc= new HandlingClass(serverClient, counter);
            hc.start();

        }
    }
}
