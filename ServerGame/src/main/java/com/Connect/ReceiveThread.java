package com.Connect;

import com.Room.Player;
import com.google.gson.Gson;

public class ReceiveThread extends Thread{
    private Player player;
    private ConnectUDP connectUDP;
    private Boolean setRun = true;

    public ReceiveThread(Player player, ConnectUDP connectUDP){
        this.player = player;
        this.connectUDP = connectUDP;
    }

    @Override
    public void run(){
        
        while(setRun){
            try {
                byte[] bytes = new  byte[1024];

                bytes = connectUDP.Receive();
                String message = new String(bytes);
                Player tmp= new Gson().fromJson(message, Player.class);
                player.setPosition(tmp.getPosition());
                sleep(10);
            } catch (Exception e) {
                System.err.println(player.getID() + " Receive: " +e); 
            } 

        }

    }

    public void setSetRun(Boolean setRun) {
        this.setRun = setRun;
    }
    public Boolean getSetRun() {
        return setRun;
    }
    
}
