package com.Connect;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

public class LocalAddress {
    private String IP;
    private int port;

    public LocalAddress(){}

    public LocalAddress(String IP, int port){
        this.IP = IP;
        this.port = port;
    }

    public void setPort(int port) {
        this.port = port;
    }
    public void setIP(String iP) {
        IP = iP;
    }
    public int getPort() {
        return port;
    }
    public String getIP() {
        return IP;
    }
    
    public static String local(){
        try {
            Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
            
            while (interfaces.hasMoreElements()) {
                NetworkInterface iface = interfaces.nextElement();
                
                // Lọc các card mạng không phải loopback và có địa chỉ IPv4
                if (iface.isLoopback() || !iface.isUp()) {
                    continue;
                }
                
                Enumeration<InetAddress> addresses = iface.getInetAddresses();
                while (addresses.hasMoreElements()) {
                    InetAddress addr = addresses.nextElement();
                    
                    // Lấy địa chỉ IPv4
                    if (addr.getHostAddress().contains(".")) {
                        return addr.getHostAddress();
                    }
                }
            }
        } catch (SocketException e) {
            e.printStackTrace();
        }

        return null;
    }
    
}
