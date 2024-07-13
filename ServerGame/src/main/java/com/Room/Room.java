package com.Room;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import com.Connect.ConnectTCP_IP;
import com.Connect.ConnectionManager;
import com.Connect.LocalAddress;
import com.Math.Vector3;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

public class Room extends Thread{
    
    private Map<Short, Player> players;
    Player player;
    public Room(){
        players = new HashMap<>();
    }

    @Override
    public void run(){
        
        int count = 0;
        ConnectTCP_IP connect = null;
        try {
            connect = new ConnectTCP_IP(4015);
        } catch (Exception e) {
            System.err.println(e);
        }

        HashMap<Short, ConnectionManager> mConnect = new HashMap<>();

        while(count++ < 100){
            try {
                // cho ket noi tu client de lay thong tin nguoi choi
                System.out.println("Dang doi ket noi tu lient");
                connect.Accept();
                byte[] bytes = connect.Receive();
                String message = new String(bytes);                
                player = new Gson().fromJson(message, Player.class);
               
                // tao port moi cho nguoi choi tham gia
                int port = new Random().nextInt(27000, 27014);
                ConnectionManager connectionManager = new ConnectionManager(port, player);
                mConnect.put(player.getID(), connectionManager);
                
                // gui thong tin port duoc mo cho nguoi choi ket noi
                LocalAddress localAddress = new LocalAddress(LocalAddress.local(), port);
                String message2 = new Gson().toJson(localAddress);
                connect.Send(message2.getBytes());
                System.out.println("Server gui: " + message2); 

                sleep(200);
            } catch (Exception e) {
                System.err.println(e);
            }
        }

    }

    public void AddPlayer(Player player){
        players.put(player.getID(), player);
    }

    public void RemovePlayer(Player player){
        RemovePlayer(player.getID());
    }

    public void RemovePlayer(short ID)
    {
        players.remove(ID);
    }

    public void setPlayers(Map<Short, Player> players) {
        this.players = players;
    }
    public Map<Short, Player> getPlayers() {
        return players;
    }

}
