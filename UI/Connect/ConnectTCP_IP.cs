using System;
using System.Net;
using System.Net.Sockets;

class ConnectTCP_IP : IConnect
{
    public int serverPort { get; set; }
    public string serverIP { get; set; }
    public Socket socket { get; set;}

    public void Connect() 
    {
        socket = new Socket(AddressFamily.InterNetwork, SocketType.Stream, ProtocolType.Tcp);
        socket.Connect(serverIP, serverPort);
    }

    public void Send(byte[] bytes)
    {
        socket.Send(bytes, bytes.Length, SocketFlags.None);
    }

    public byte[] Receive()
    {
        byte[] bytes = new byte[1024];
        int size = socket.Receive(bytes);
        byte[] tmp = new byte[size];
        Array.Copy(bytes, tmp, size);
        return tmp;
    }

    public void Disconnect()
    {
        socket.Close();
    }
}
