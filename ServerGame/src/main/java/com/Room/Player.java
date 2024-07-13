package com.Room;

import com.Math.Vector3;

public class Player {
    private int port;
    private String IP;
    private short ID;
    private Vector3 position;
    private Vector3 lookAt;

    public Player(){}

    public Player(int port, String IP, short ID, Vector3 position, Vector3 lookAt){
        this.port = port;
        this.IP = IP;
        this.ID = ID;
        this.position = position;
        this.lookAt = lookAt;
    }

    public void setID(short iD) {
        ID = iD;
    }public void setIP(String iP) {
        IP = iP;
    }public void setPort(int port) {
        this.port = port;
    }public void setLookAt(Vector3 lookAt) {
        this.lookAt = lookAt;
    }public void setPosition(Vector3 position) {
        this.position = position;
    }public short getID() {
        return ID;
    }public int getPort() {
        return port;
    }public String getIP() {
        return IP;
    }public Vector3 getLookAt() {
        return lookAt;
    }public Vector3 getPosition() {
        return position;
    }

    @Override
    public String toString() {
        return "ID:"+ID+", Position: " + position;
    }
}
