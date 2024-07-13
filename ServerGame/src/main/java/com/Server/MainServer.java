package com.Server;

import com.Math.Vector3;
import com.Room.Room;
import com.fasterxml.jackson.databind.ObjectMapper;

public class MainServer {
    
    public static void main(String[] args) {
        new Room().start();
    }
}

