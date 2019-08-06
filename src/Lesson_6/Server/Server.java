package Lesson_6.Server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.Vector;

public class Server {
    private Vector<ClientHandler> clients;

    public Server() throws SQLException {
        clients = new Vector<>();
        ServerSocket server = null;
        Socket socket = null;

        try {
            AuthService.connect();
//            String test = AuthService.getNickByLoginAndPass("login1", "pass1");
//            System.out.println(test);
            server = new ServerSocket(8189);
            System.out.println("Сервер запущен!");

            while (true) {
                socket = server.accept();
                new ClientHandler(this, socket);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                server.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            AuthService.disconnect();
        }
    }

    public void broadcastMsg(String msg, boolean forAll) {
        if(forAll) {
            for (ClientHandler o : clients) {
                o.sendMsg(msg);
            }
        }
    }

    public void whoIsOnline(){
        for(ClientHandler o: clients){
            System.out.println(o.getNickname());
            for (ClientHandler c: clients) {
                c.sendMsg(o.getNickname());
            }
        }
    }

    public void privateMassage(String msg, String nickTo, String nickFrom){
            for (ClientHandler o : clients) {
                if (nickTo.equals(o.getNickname())) {
                    o.sendMsg(nickFrom + " >>: " + msg);
                }
                if (nickFrom.equals(o.getNickname())) {
                    o.sendMsg(nickTo + " <<: " + msg);
                }


            }
    }

    public boolean onlineCheck(String nick){
        boolean clientOnline = false;
        for(ClientHandler o: clients){
            if(nick.equals(o.getNickname())){
                clientOnline = true;
                break;
            }
        }
        return clientOnline;
    }

    public void subscribe(ClientHandler client) {
        clients.add(client);
    }

    public void unsubscribe(ClientHandler client) {
        clients.remove(client);
    }

}
