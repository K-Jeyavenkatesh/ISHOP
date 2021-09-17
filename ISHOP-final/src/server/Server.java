package server;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.ArrayList;

import client.*;

public class Server {

    private static DatagramSocket socket;
    private static boolean running;

    private static ArrayList<ClientInfo> clients = new ArrayList<>();
    private static int clientId=0;

    public static void start(int port){
        try{
            socket = new DatagramSocket(port);

            running=true;
            listen();
            System.out.println("server started on port, "+port);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    private static void broadcast(String message){
        System.out.println("in broadcast-->"+message);
        for(ClientInfo info : clients){
            String to_client[]= message.split(" ");
//            System.out.println("message = "+message+" & index = "+to_client.length);
//            System.out.println("-----> "+to_client[to_client.length-1]+" "+info.getName());
            if(to_client[to_client.length-2].equals(info.getName())){
                String msg="";
                for(int i=0;i<to_client.length;i++)if(i!=to_client.length-2)msg+=to_client[i]+" ";
                System.out.println("out broadcast-->"+message);
                send(msg,info.getAddress(),info.getPort());
            }
        }
    }

    private static void send(String message, InetAddress address,int port){
        try{
            System.out.println("front try -->"+message);
            message+="\\e";
            System.out.println("back try -->"+message);
            byte[] data=message.getBytes();
            DatagramPacket packet=new DatagramPacket(data,data.length,address,port);
            socket.send(packet);
            System.out.println("serever send --> "+message);
            System.out.println("send message to, "+address.getHostAddress()+" : "+port);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    private static void listen(){
        Thread listenThread = new Thread("Chat program listener"){
            public void run(){
                try{
                    while(running){
                        byte[] data=new byte[1024];
                        DatagramPacket packet=new DatagramPacket(data,data.length);
                        socket.receive(packet);

                        String message=new String(data);
                        message=message.substring(0,message.indexOf("\\e"));
                        System.out.println("server outside --> "+message);
                        //MANAGE MESSAGE
                        if(!is_command(message,packet)){
                            System.out.println("broadcast-->"+message);
                            broadcast(message);
                        }

                    }
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        };
        listenThread.start();
    }

    private static boolean is_command(String message,DatagramPacket packet){
        if(message.startsWith("\\con:")){
            //RUN CONNECTION CODE
            System.out.println("inside iscommad-->"+message);
            String name=message.substring(message.indexOf(":")+1);
            clients.add(new ClientInfo(packet.getAddress(),packet.getPort(),clientId++,name));
            //broadcast("User "+name+" connected!!");
            return true;
        }
//        if(message.startsWith("\\fin:")){
//            System.out.println("inside fin cmd -->"+message);
//            //String f_price=message.substring(message.indexOf(":")+1);
//            broadcast(message);
//        }
        return false;
    }


    public static void close(){
        running=false;
    }

    public static void remove_from_server(String Client_id){
        for(int i=0;i<clients.size();i++){
            if(clients.get(i).getName().equals(Client_id))clients.remove(i--);
        }
    }
    
}
