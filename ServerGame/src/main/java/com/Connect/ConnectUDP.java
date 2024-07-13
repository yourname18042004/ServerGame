package com.Connect;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Arrays;

public class ConnectUDP {
    
    private String endIP;
    private int endPort;
    private int serverPort;
    private InetAddress inetAddress;
    private DatagramSocket datagramSocket;
        
    public ConnectUDP(){}

    public ConnectUDP(int serverPort, String endIP, int endPort) throws Exception {
        this.endPort = endPort;
        this.endIP = endIP;
        this.serverPort = serverPort;

        inetAddress = InetAddress.getByName(this.endIP);
        datagramSocket = new DatagramSocket(this.serverPort);
    }

    public void Send(byte[] bytes) throws Exception {
        DatagramPacket sendData = new DatagramPacket(bytes, bytes.length, inetAddress, endPort);
        datagramSocket.send(sendData);
    }

    public byte[] Receive() throws Exception {
        byte[] bytes = new byte[1024];
        DatagramPacket sendData = new DatagramPacket(bytes, bytes.length);
        datagramSocket.receive(sendData);
        byte[] tmp = Arrays.copyOf(bytes, sendData.getLength()); 
        return tmp;
    }
        
    public void Disconnect(){
        datagramSocket.close();
    }

    public void setEndIP(String endIP) {
        this.endIP = endIP;
    }
    public void setEndPort(int endPort) {
        this.endPort = endPort;
    }public int getEndPort() {
        return endPort;
    }public String getEndIP() {
        return endIP;
    }

}
