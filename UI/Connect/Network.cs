using System.Net;
using System.Net.Sockets;
using System.Net.NetworkInformation;

class Network
{
    public string GetLocalIP(){
        NetworkInterface[] interfaces = NetworkInterface.GetAllNetworkInterfaces();

        foreach (NetworkInterface iface in interfaces)
        {
            // Lọc các card mạng Ethernet và không phải loopback
            if (iface.NetworkInterfaceType == NetworkInterfaceType.Ethernet &&
                    !iface.Description.ToLower().Contains("virtual") &&
                    !iface.Description.ToLower().Contains("loopback"))
            {
                // Lấy địa chỉ IP của card mạng đầu tiên
                foreach (UnicastIPAddressInformation ip in iface.GetIPProperties().UnicastAddresses)
                {
                    if (ip.Address.AddressFamily == AddressFamily.InterNetwork) // Chỉ lấy IPv4
                    {
                        return ip.Address.ToString();
                    }
                }
            }
        }

        return null;
    }

}
