package server;

import java.net.InetAddress;
import client.*;
import demo.Customer_bean;

public class ClientInfo {

    private InetAddress address;
    private int port;
    private int id;
    private String name;

    public ClientInfo(InetAddress address, int port, int id, String name) {
        this.address = address;
        this.port = port;
        this.id = id;
        this.name = name;
    }

    public InetAddress getAddress() {
        return address;
    }

    public int getPort() {
        return port;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
