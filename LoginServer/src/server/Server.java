package server;

import sample.ReadThread;
import util.NetworkUtil;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Set;

public class Server {

    ReadThreadServer t;
    private ServerSocket serverSocket;
    public static HashMap<String, String> userMap;
    private final  String filename="src/sample/car_seller.txt";

    Server() {
        userMap = new HashMap<>();
        /*userMap.put("a", "a");
        userMap.put("b", "b");
        userMap.put("c", "c");
        userMap.put("d", "d");
        userMap.put("e", "e");*/
        try{
            String line;
            BufferedReader br=new BufferedReader(new FileReader(filename));

            while(true){
                line=br.readLine();
                if(line==null)break;
                String sellerinfo[]=line.split(",");
                //System.out.println(sellerinfo[0]+","+sellerinfo[1]);
                userMap.put(sellerinfo[0],sellerinfo[1]);


            }
            /*for( String s:userMap.keySet()){
                System.out.println(s);
                System.out.println("1");
            }*/
            br.close();


        }catch (Exception e){
            e.printStackTrace();
        }

        try {
            serverSocket = new ServerSocket(33333);
            while (true) {
                new write_server();
                Socket clientSocket = serverSocket.accept();
                serve(clientSocket);
            }
        } catch (Exception e) {
            System.out.println("Server starts:" + e);
        }
    }

    public void serve(Socket clientSocket) throws IOException {
        NetworkUtil networkUtil = new NetworkUtil(clientSocket);
        t=new ReadThreadServer(userMap, networkUtil);


    }


    public static void main(String[] args) {
        Server s=new Server();



    }
}
