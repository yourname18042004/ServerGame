package com.Connect;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.InetAddress;
import java.util.Arrays;

public class ConnectTCP_IP {
    
    private int serverPort;

    private ServerSocket serverSocket;
    private Socket clientSocket;

    private InputStream inputStream;
    private OutputStream ouputStream;

    public ConnectTCP_IP(){}

    public ConnectTCP_IP(int serverPort) throws Exception{
        this.serverPort = serverPort;

        serverSocket = new ServerSocket(this.serverPort, 50, InetAddress.getByName("192.168.1.53"));
    }

    public void Accept() throws Exception{
        clientSocket = serverSocket.accept();
        inputStream = clientSocket.getInputStream();
        ouputStream = clientSocket.getOutputStream();
    }

    public void Send(byte[] bytes) throws Exception{
        ouputStream.write(bytes); 
    }
    
    public byte[] Receive() throws Exception{
        byte[] bytes = new byte[1024];
        int size = inputStream.read(bytes);
        byte[] tmp = Arrays.copyOf(bytes, size);
        return tmp;
    }

    public void Close() throws Exception{
        clientSocket.close();
    }
}
