package client;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

import demo.Customer_bean;
import demo.Product_View;
import demo.Product_bean;
import server.*;

public class Client {

    private DatagramSocket socket;
    private InetAddress address;
    private String name;
    private int port;
    private boolean running;

    public Client(String name,String address, int port){
        try{
            this.name=name;
            this.address=InetAddress.getByName(address);
            this.port=port;

            socket=new DatagramSocket();
        }catch(Exception e){
            e.printStackTrace();
        }

        running=true;
        listen();
        send("\\con:"+name);
    }

    public void send(String message){
        System.out.println("send from client");
        if(!message.startsWith("\\")){
            message=name+": "+message;
        }

        try{
            message+="\\e";
            byte[] data=message.getBytes();
            DatagramPacket packet=new DatagramPacket(data,data.length,address,port);
            socket.send(packet);
            System.out.println("send-->"+message);
            System.out.println("send message to, "+address.getHostAddress()+" : "+port);
        }catch(Exception e){
            e.printStackTrace();
        }

    }

    private void listen(){
        Thread listenThread = new Thread("Chat program listener"){
            @SuppressWarnings("static-access")
			public void run(){
                try{
                    while(running){
                        byte[] data=new byte[1024];
                        DatagramPacket packet=new DatagramPacket(data,data.length);
                        socket.receive(packet);

                        String message=new String(data);
                        message=message.substring(0,message.indexOf("\\e"));
                        System.out.println("outside-->"+message);
                        //MANAGE MESSAGE
                        message.trim();
                        System.out.println("last char -->"+message.charAt(message.length()-2));
                        if(message.startsWith("\\")){
                            String f_price=message.substring(message.indexOf(":")+1);
                            System.out.println("summa-->"+f_price+", "+message);
                            String f_p[]=f_price.split(" ");
                            new Product_View(Product_View.product,Product_View.customer).change_price(Double.parseDouble(f_p[0]));
                            System.out.println("listen "+f_p[0]);
                            Chat_buyer_side.printToConsole("Your product price changed to : "+f_price);
                        }

                        else if(message.charAt(message.length()-2)=='s'){
                            System.out.println(message);
                            message=message.substring(0,message.indexOf("\\s"));
                            System.out.println(message);
                            Chat_seller_side.printToConsole(message);
                        }
                        else if(!is_command(message,packet)){
                            //PRINT MESSAGE
                            //message=message.substring(0,message.indexOf("\\c"));
                            System.out.println("here-->"+message);
                            Chat_buyer_side.printToConsole(message);
                        }

                    }
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        };
        listenThread.start();
    }

    private boolean is_command(String message,DatagramPacket packet){
        if(message.startsWith("\\con:")){
            //RUN CONNECTION CODE

            return true;
        }
        return false;
    }



}
