package com.javarush.task.task30.task3008;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Server {
    private static Map<String, Connection> connectionMap = new ConcurrentHashMap<>();

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(ConsoleHelper.readInt())) {
            System.out.println("Server has started");
            while (true) {
                new Handler(serverSocket.accept()).start();
            }
        } catch (IOException e) { e.printStackTrace(); }
    }

    public static void sendBroadcastMessage(Message message){
        for (Map.Entry<String, Connection> m : connectionMap.entrySet()) {
            try {
                m.getValue().send(message);
            } catch (IOException e) { }
        }
    }

    private static class Handler extends Thread {
        private Socket socket;

        public Handler(Socket socket) {
            this.socket = socket;
        }

        public void run(){
            ConsoleHelper.writeMessage("Connection with: " + this.socket.getRemoteSocketAddress());
            String userName = "";
            try (Connection connection = new Connection(socket)){
                 userName = serverHandshake(connection);
                 sendBroadcastMessage(new Message(MessageType.USER_ADDED, userName));
                notifyUsers( connection, userName);
                serverMainLoop(connection, userName);
            } catch (IOException | ClassNotFoundException e) {
                ConsoleHelper.writeMessage("Exception");
            }finally {
                if(!userName.equals("")) {
                    connectionMap.remove(userName);
                    sendBroadcastMessage(new Message(MessageType.USER_REMOVED, userName));
                }
                ConsoleHelper.writeMessage("The end.");
            }
        }

        private String serverHandshake(Connection connection) throws IOException, ClassNotFoundException{
            Message message;
            while (true) {
                connection.send(new Message(MessageType.NAME_REQUEST));
                 message = connection.receive();

                if (message.getType().equals(MessageType.USER_NAME) && !message.getData().equals("") &&
                        !connectionMap.containsKey(message.getData())) {
                    connectionMap.put(message.getData(), connection);
                    connection.send(new Message(MessageType.NAME_ACCEPTED));
                    break;
                }
            }
            return message.getData();
        }

        private void notifyUsers(Connection connection, String userName) throws IOException{
            for (Map.Entry<String, Connection> m : connectionMap.entrySet()) {
                if (!m.getKey().equals(userName)) {
                    Message message = new Message(MessageType.USER_ADDED, m.getKey());
                    connection.send(message);
                }
            }
        }

        private void serverMainLoop(Connection connection, String userName) throws IOException, ClassNotFoundException{
            Message message;
            while (true) {
                message = connection.receive();
                if (message.getType() == MessageType.TEXT) {
                    String str = String.format("%s: %s", userName, message.getData());
                    sendBroadcastMessage(new Message(MessageType.TEXT, str));
                } else ConsoleHelper.writeMessage("Error");
            }
        }
    }
}
