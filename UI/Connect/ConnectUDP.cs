using System;
using System.Net;
using System.Net.Sockets;
class ConnectUDP : IConnect
{
    public int serverPort { get; set; }
    public string serverIP { get; set; }
    public int receivePort { get; set; }
    public Socket socket { get; set;}

    public UdpClient receive { get; set; }
    
    public void Connect() 
    {
        socket = new Socket(AddressFamily.InterNetwork, SocketType.Dgram, ProtocolType.Udp);
        socket.Connect(serverIP, serverPort);

        receive = new UdpClient(receivePort);
    }

    public void Send(byte[] bytes)
    {
        socket.Send(bytes, bytes.Length, SocketFlags.None);
    }

    public byte[] Receive()
    {
        byte[] bytes = new byte[1024];
        IPEndPoint remoteEndPoint = new IPEndPoint(IPAddress.Any, 0);
        bytes = receive.Receive(ref remoteEndPoint);
        return bytes;
    }

    public void Disconnect()
    {
        socket.Close();
    }
  
}
