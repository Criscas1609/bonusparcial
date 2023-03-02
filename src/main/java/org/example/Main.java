package org.example;

import org.example.utils.Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {
    public static void main(String[] args) throws IOException {

        ServerSocket serverSocket = new ServerSocket(5050);
        System.out.println("Servidor iniciado y puerto 5050");

        while (true) {

            Socket socket = serverSocket.accept();
            System.out.println("Cliente conectado: " + socket.getInetAddress().getHostAddress());
            //Hilos
            Server server = new Server(socket);
            Thread thread = new Thread(server);
            thread.start();
        }
    }
}