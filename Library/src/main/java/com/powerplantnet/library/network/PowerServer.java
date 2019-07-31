package com.powerplantnet.library.network;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;
import com.powerplantnet.library.logger.LogLevel;
import com.powerplantnet.library.logger.LoggerService;
import com.powerplantnet.library.network.packet.AcceptPacket;
import com.powerplantnet.library.network.packet.KeyPacket;
import lombok.Getter;
import lombok.var;

import java.io.IOException;

@Getter
public class PowerServer {

    private int tcp,udp;
    private Server server;
    private LoggerService logger;
    private String address;

    public PowerServer(String address,int tcp,int udp, LoggerService logger){

        this.tcp = tcp;
        this.udp = udp;
        this.server = new Server();
        this.logger = logger;
        this.address = address;
    }

    public boolean start(){

        var kryo = this.server.getKryo();
        kryo.register(KeyPacket.class);
        kryo.register(AcceptPacket.class);

        try {
            this.logger.Log(LogLevel.WARNING,"Create a PowerServer bind to " + this.address +". [TCP:" + tcp + "/UDP:" + udp + "]");
            this.server.start();
            this.server.bind(tcp,udp);

        } catch (IOException e) {
            this.logger.Log(LogLevel.ERROR, "PowerServer create binding failed.");
        }

        server.addListener(new Listener(){
            public void received(Connection connection, Object object) {
                if(object instanceof KeyPacket){
                    var key = ((KeyPacket)object);
                    var packet = new AcceptPacket();
                    if(key.key == 100){
                        logger.Log(LogLevel.WARNING, "PowerClient connection completed. [IP:" + key.address + "/TCP:" + tcp + "/UDP:" + udp + "]");

                        packet.accepted = true;

                        connection.sendTCP(packet);

                    }else{
                        logger.Log(LogLevel.WARNING, "Password invaild from PowerClient. [IP:" + key.address + "/TCP:" + tcp + "/UDP:" + udp + "]");

                        packet.accepted = false;

                        connection.sendTCP(packet);
                        connection.close();
                    }
                }
            }
        });

        return false;
    }
}
