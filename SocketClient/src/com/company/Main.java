package com.company;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Main {

    public static void main(String[] args) throws IOException{
        Socket socket=new Socket("127.0.0.1",8888);
        Date dd= new Date();
        SimpleDateFormat ff= new SimpleDateFormat("EEEE yyyy-MM-dd hh:mm:ss");
        DataInputStream inStream=new DataInputStream(socket.getInputStream());
        DataOutputStream outStream=new DataOutputStream(socket.getOutputStream());
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        String clientMessage="",serverMessage="";
        while(!clientMessage.equalsIgnoreCase("bye")){
            System.out.println("Enter a message :");
            clientMessage=br.readLine();
            outStream.writeUTF(clientMessage);
            outStream.flush();
            serverMessage=inStream.readUTF();
            System.out.println("Server => "+ serverMessage+" "+" ["+ff.format(dd)+"]");
        }
        outStream.close();
        outStream.close();
        socket.close();
    }
}
