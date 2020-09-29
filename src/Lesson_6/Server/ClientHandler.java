package Lesson_6.Server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.sql.SQLException;

public class ClientHandler {
    private Socket socket;
    private DataOutputStream out;
    private DataInputStream in;
    private Server server;
    private String nick;

    public ClientHandler(Server server, Socket socket) {
        try {
            this.socket = socket;
            this.server = server;
            this.in = new DataInputStream(socket.getInputStream());
            this.out = new DataOutputStream(socket.getOutputStream());

            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        while (true) {
                            String str = in.readUTF();
                            if (str.startsWith("/auth")) {
                                String[] tokens = str.split(" ");
                                String newNick = AuthService.getNickByLoginAndPass(tokens[1], tokens[2]);
                                if (newNick != null) {
                                    if(!server.onlineCheck(newNick)) {
                                        sendMsg("/authok");
                                        nick = newNick;
                                        server.subscribe(ClientHandler.this);
                                        break;
                                    }else{
                                        sendMsg("Такой пользователь уже в сети");
                                    }
                                } else {
                                    sendMsg("Неверный логин/пароль!");
                                }
                            }
                        }

                        while (true) {
                            boolean forAll = true;
                            String str = in.readUTF();
                            System.out.println(nick + ": " + str);

                            if (str.equals("/end")) {
                                out.writeUTF("/serverClosed");
                                break;
                            }
                            if (str.startsWith("/w")) {
                                forAll = false;
                                if(!str.equals("/w") && !str.equals("/w ")) {
                                    String[] pmTokens = str.split(" ");
                                    String nickTo = pmTokens[1];
                                    if (server.onlineCheck(nickTo)) {
                                        server.privateMassage(personalMsgBuilder(pmTokens), nickTo, nick);
                                    } else {
                                        sendMsg("Такого пользователья нет в сети");
                                    }
                                }else
                                    sendMsg("Задайте имя");

                            }
                            if (str.startsWith("/online")) {
                                forAll = false;
                                server.whoIsOnline();
                            }
                            server.broadcastMsg(nick + ": " + str, forAll);


                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    } finally {
                        try {
                            in.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        try {
                            out.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        try {
                            socket.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        server.unsubscribe(ClientHandler.this);
                        System.out.println(nick + " отключился от сервера");
                    }
                }
            }).start();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendMsg(String msg) {
        try {
            out.writeUTF(msg);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getNickname(){
        return nick;
    }

    public String personalMsgBuilder(String[] pmTokens){

        String msg = "";
        for(int i = 2; i<pmTokens.length; i++){
            msg += pmTokens[i]+ " ";
        }
        return msg;
    }


}
