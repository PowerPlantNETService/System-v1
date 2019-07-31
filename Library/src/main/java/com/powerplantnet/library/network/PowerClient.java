package com.powerplantnet.library.network;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.powerplantnet.library.logger.LogLevel;
import com.powerplantnet.library.logger.LoggerService;
import com.powerplantnet.library.network.packet.AcceptPacket;
import com.powerplantnet.library.network.packet.KeyPacket;
import lombok.var;

import java.io.IOException;

public class PowerClient {

    private int tcp,udp;
    private String address;
    private Client client;
    private LoggerService logger;

    public PowerClient(String address, int tcp, int udp, LoggerService logger){

        this.tcp = tcp;
        this.udp = udp;
        this.client = new Client();
        this.address = address;
        this.logger = logger;

    }

    public boolean start(){

        var kryo = client.getKryo();
        kryo.register(KeyPacket.class);
        kryo.register(AcceptPacket.class);

        try {

            this.client.start();
            this.client.connect(5000,this.address,this.tcp,this.udp);

        } catch (IOException e) {
            e.printStackTrace();
        }

        var keyPacket = new KeyPacket();
        keyPacket.key = 100;
        keyPacket.address = this.address;
        this.client.sendTCP(keyPacket);

        client.addListener(new Listener() {

            @Override
            public void connected(Connection connection) {

                logger.Log(LogLevel.WARNING,"Try connect to PowerClient bind by " + address +". [TCP:" + tcp + "/UDP:" + udp + "]");

            }

            @Override
            public void disconnected(Connection connection) {

                logger.Log(LogLevel.WARNING,"Disconnected from PowerServer. [TCP:" + tcp + "/UDP:" + udp + "]");

            }

            public void received(Connection connection, Object object) {
                if (object instanceof AcceptPacket) {
                    var accepted = ((AcceptPacket) object).accepted;
                    if(accepted){

                        logger.Log(LogLevel.WARNING, "PowerClient connection completed. [IP:" + keyPacket.address + "/TCP:" + tcp + "/UDP:" + udp + "]");

                    }else{

                        logger.Log(LogLevel.ERROR, "PowerClient connection failed.");

                    }
                }
            }
        });

        return false;
    }
}
