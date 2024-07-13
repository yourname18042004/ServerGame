using Godot;
using System;
using System.Text;
using System.Text.Json;
using System.Threading;

public partial class character : CharacterBody3D
{
	public const float Speed = 5.0f;
	public const float JumpVelocity = 4.5f;

	public float gravity = ProjectSettings.GetSetting("physics/3d/default_gravity").AsSingle();

    public Player player;
    
   
    public override void _Ready()
    {
        player = new Player{
            port = new Random().Next(27016, 27090),
            ID = (short)new Random().Next(0, 100),
            IP = new Network().GetLocalIP(),
            position = new JVector3(){ X = Position.X, Y=Position.Y, Z=Position.Z},
            lookAt = new JVector3(){ X = 1, Y = 1, Z = 1},
        };

        ConnectTCP_IP connect = new ConnectTCP_IP(){
            serverPort = 4015,
            serverIP = "192.168.1.53"
        };
        
        string message = JsonSerializer.Serialize<Player>(player);
        byte[] bytes = Encoding.UTF8.GetBytes(message);
        connect.Connect();
        connect.Send(bytes);

        string message2 = Encoding.UTF8.GetString(connect.Receive());
        GD.Print(message2.Length + "Helo");
        LocalAddress localAddress = JsonSerializer.Deserialize<LocalAddress>(message2);  
        connect.Disconnect();


        try
        {
            Thread thread = new Thread(() => {
                KetNoi(localAddress, player.port, player.IP);
            });
            thread.Start();
        }
        catch (System.Exception e)
        {
            GD.Print(e);   
        }

    }

    public void KetNoi(LocalAddress localAddress, int port, string IP){
        try
        {            
            ConnectUDP connectUDP = new ConnectUDP(){
                serverPort = localAddress.port,
                serverIP = localAddress.IP,
                receivePort = player.port
            };
            connectUDP.Connect();

            int count = 0;
            while(count++ < 20000)
            {
                string message = JsonSerializer.Serialize<Player>(player);
                byte[] bytes = Encoding.UTF8.GetBytes(message);
                GD.Print(message);
                connectUDP.Send(bytes);

                byte[] bytes2 = connectUDP.Receive();
                string message2 = Encoding.UTF8.GetString(bytes2);
                Player tmp = JsonSerializer.Deserialize<Player>(message2);
                GD.Print("Vi tri hien tai cua ban: " + tmp.position.ToString());
                Thread.Sleep(10);
            }

            connectUDP.Disconnect();
        }
        catch (System.Exception e)
        {
            GD.Print("Khong the ket noi !! \n" + e );    
        }

    }

    public override void _PhysicsProcess(double delta)
	{
		Vector3 velocity = Velocity;

		if (!IsOnFloor())
			velocity.Y -= gravity * (float)delta;

		if (Input.IsActionJustPressed("ui_accept") && IsOnFloor())
			velocity.Y = JumpVelocity;

		Vector2 inputDir = Input.GetVector("ui_left", "ui_right", "ui_up", "ui_down");
		Vector3 direction = (Transform.Basis * new Vector3(inputDir.X, 0, inputDir.Y)).Normalized();
		if (direction != Vector3.Zero)
		{
			velocity.X = direction.X * Speed;
			velocity.Z = direction.Z * Speed;
		}
		else
		{
			velocity.X = Mathf.MoveToward(Velocity.X, 0, Speed);
			velocity.Z = Mathf.MoveToward(Velocity.Z, 0, Speed);
		}

		Velocity = velocity;
		MoveAndSlide();

        player.position = new JVector3(){ X = Position.X, Y=Position.Y, Z=Position.Z};
	}
}
