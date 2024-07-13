interface IConnect
{
    void Connect();
    void Send(byte[] bytes);
    byte[] Receive();
    void Disconnect();
}
