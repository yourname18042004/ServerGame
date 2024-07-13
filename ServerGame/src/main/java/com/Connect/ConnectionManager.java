package com.Connect;

import com.Room.Player;

public class ConnectionManager {
    
    private SendThread sendThread;
    private ReceiveThread receiveThread;
    private Player player;
    private ConnectUDP connectUDP;

    public ConnectionManager(){}

    public ConnectionManager(int port, Player player) throws Exception{
        this.player = player;
         
        connectUDP = new ConnectUDP(port, this.player.getIP(), this.player.getPort());
    
         receiveThread = new ReceiveThread(this.player, connectUDP);
         sendThread = new SendThread(this.player, connectUDP);

         receiveThread.start();
         sendThread.start();
    }

    public void Stop(){
        receiveThread.setSetRun(false);
        sendThread.setSetRun(false);
    }


}
